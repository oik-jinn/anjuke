### TW二手房房源单页数据隔离 & 过期房源归档项目
___

#### 项目背景:

* 产品需求：增加房源收录
* 项目地址：http://p.corp.anjuke.com/project/detail?id=24285

### 后端归档方案
___
* 归档方案同[pc二手房房源归档方案](http://gitlab.corp.anjuke.com/_site/docs/blob/master/BizDesc/Fangyuan/Ershou/ExpiredArchive.md)

### 前台模块展示
_____

#### 单页数据读取流程图

![](http://gitlab.corp.anjuke.com/_site/docs/raw/master/BizDesc/Fangyuan/Ershou/DataFlow.png)


#### 正常单页

###### 房源信息
* 获取房源信息service方法：Ershou_Core_Property_Service_UserPropertyService::getPropertyByFullId($full_id)

###### 房源验证和跳转(见流程图)
````
if(empty($property) || intval($property->state) === Ershou_Core_Const_Property::STATE_NOT_VISIBLE){	return APF::get_instance()->get_controller("User_Common_Error_HTTP404");}
//url的id仅是数字&发房时间大于xxxx的跳转到带字母的url$matches = $this->request->get_router_matches();if(is_numeric(trim($matches [1])) && User_Common_MultiCity_City::isPropertySplitOpen($prop->city_id,$prop->post_time)){	APF::get_instance()->get_response()->redirect($propUrl,true);}//域名不一致跳转if(isset($_SERVER["HTTP_HOST"]) && $_SERVER["HTTP_HOST"] != parse_url($propUrl,PHP_URL_HOST)){	APF::get_instance()->get_response()->redirect($propUrl,true);}


````

#### 过期单页

###### 该小区其它房源推荐

* 需求简述：取该小区rank最高的4套房源
* 实现方案：调用现上已有的房源solr服务，通过solr取出该小区的在线房源，cache一天的时间
* Biz方法：Biz_Ershou_Property_RelatedPropertyBiz::getCommunityProperty($communityId)


### 二手房列表页url
* 生成二手房房源urll逻辑图：

![](create_prop_url.png)



### TodoList
* 通知BI
    * 增加二手房过期房源单页pagename：Anjuke_Prop_ArchiveView

