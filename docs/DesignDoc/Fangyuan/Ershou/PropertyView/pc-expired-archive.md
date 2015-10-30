### 二手房源归档项目
___

#### 项目背景:

* 产品需求：延长房源展示时间，增加房源单页seo的收录
* 技术升级：启用新HBase的归档机制，节省之前用mysql归档的资源，解决由于mysql保存太多房源数据成的慢查询
* 项目地址：http://p.corp.anjuke.com/project/detail?id=23700

### 后端归档方案
___

##### 归档逻辑

* 二手房源发布时间超过90天归档
* 发房时间超过一年的数据删除

##### 归档方案
   
   * 归档存储介质：HBase
   * 归档内容：
   
       * 房源基本信息和扩展信息
       * 用户端独立的房源数据，见:http://gitlab.corp.anjuke.com/_site/docs/blob/master/DesignDoc/Public/UserArchitecture/prop_table_design.md
       
   * 归档的表
       * 经纪人房源
       
           * 基本表：user_prop\_{城市ID}\_db.broker_property_城市ID
           * 扩展表：user_prop\_{城市ID}\_db.broker_property_extend_城市ID
            
       * 抓取房源
       
           * 基本表：user_prop\_{城市ID}\_db.crawl_property_城市ID
           * 扩展表：user_prop\_{城市ID}\_db.crawl_property_extend_城市ID
       
   * 通过后台job运行来处理房源归档，具体job说明如下：


job名称|运行时间|描述|路径
---|---|---|---
归档房源到HBase|零晨2点|读取隔离数据线表中的数据|Ershou_Job_Property_PropertyArchive
删除超过1年的数据|零晨2点|将HBase中的房源超过1年的数据删除|Ershou_Job_Property_PropertyClearUp

##### HBase归档说明 

   * 新建HBase的表配置如下：
   
   ```
   app-user-common/config/hbase.php
   
   $config['property_servers'] = array(
    'rest_api' => 'http://10.20.3.82:8080',
    'table_name' => 'property',
    'column' => 'info',
   );

   ```
   * 获得和写房源接口
 
       * pg: http://10.20.3.82:8080/property/38-A168946738/info
       * online：http://10.10.8.2:20550/property/47-A237834647/info
       * 参数说明
           * 以房源数字id取100的模，再中横杠连接这个房源id
           * 这样id的处理方式是为了让HBase存储分布的机器更均衡
           
##### 过期房源数据Service


* Biz接口类:Ershou_Core_Property_Service_UserPropertyService

```
    /**
     * 获得房源基本信息
     * @param  $full_id  为A123111  B12311这类
     * @return object 
     *  pro_id=> 房源id
     *  archive => 1:归档，0未归档
     */
    public function getPropertyBaseByFullId($full_id)
    {
        ...
    }
    
    /**
     * 获得房源归档信息
     * @param $city_id int
     * @param $type string
     * @param $id int
     * @return array | object
     */
    private function getPropertyArchiveByCityIdAndTypeAndId($city_id, $type, $id)
    {
        ....
    }
    
```
* 实现方案
   * 单页controller调用getPropertyBaseByFullId，通过判断此属性archive来处理到不同的页面


### 前台模块展示
_____

##### 单页数据读取流程图

![](DataFlow.png)

##### 小区其它房源推荐

* 需求简述：取该小区rank最高的4套房源
* 实现方案：调用现上已有的房源solr服务，通过solr取出该小区的在线房源，cache一天的时间
* Biz方法：Biz_Ershou_Property_RelatedPropertyBiz::getCommunityProperty($communityId)

##### 小区推荐

* 需求简述：显示该小区所以区域的10个小区
* 实现方案：调用现上已有小区solr服务，通过传小区域代码获得同区域的其它小区，cache一天的时间
* Biz方法：Biz_Community_Comm_CommunityBiz::getAreaCommnuityBySolr

##### 新房推荐

* 由新房提供接口
* 接口超时时间:20ms,请求到数据cache一天的时间

##### 区域找房

* 需求简述：该城市的10个区域，链接到列表页
* 调用接口(已有)：Community_Core_City_Service_AreaBlockService::getAreaByCityId

#### 新增代码文件

名称|类型 |描述
---|---|---
Ershou_Web_Property_View_ArchiveViewPage | page | 过期房源页面名称
User_Component_Module_SmallListComponent|组件|以上三个推荐模块都用些组，标签和内容由调用组时传入
Biz_Ershou_Property_RelatedPropertyBiz| Biz|与房源属性相关的，比如同小区房源接口
Ershou_Job_Property_PropertyArchive | job | 读取隔离数据线表中的数据
Ershou_Job_Property_PropertyClearUp | job | 删除超过1年的数据


#### TodoList
* 通知BI
    * 增加二手过期房源单页：Ershou_Web_Property_ArchiveViewPage

