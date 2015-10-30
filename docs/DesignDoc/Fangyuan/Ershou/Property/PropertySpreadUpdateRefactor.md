## 二手房源推广和更新job架构
---

### 背景

随着房源量的上升，ppc降权降段的大数据量问题，此次设计重构主要解决如下几个问题：

* 统一中间件：删除之前队列表的依赖，全转换成amqp中间件
* 解藕：房源更新和推广状态混搭在一起的问题，将房源推广和房源更新单独处理

### 队列表和现有逻辑
* 4个端口或房源更新队列表

    * anjuke\_db.log\_rankprop\_update\_04 
    * anjuke\_db.log\_rankprop\_update\_other
    * anjuke\_db.log\_rankprop\_update\_bj 
    * anjuke\_db.log\_rankprop\_update\_sh 
    
* 竞价队列表

    * ark\_db.log\_rankprop\_update\_auction
    
* 现有流程
    
![流程图](images/queue_table.png)


### 定价中间件

* 流程：

![流程图](images/pricing_amqp.png)

* 现状：如下：
    * 开通ppc城市直接在定价中间件，直接上下架
    

* 详细设计见：[请点击](http://git.corp.anjuke.com/\_user\_site/doc/browse/master/fangyuan/ershou/PricingAmqp.md)
    
### 竞价中间件

* 流程：

![流程图](images/auction_amqp.png)

* 现状：目前线上有两个job跑，如下：
    * 目前仅上海走到此中间件
    * 其它城市走队列表(ark\_db.log\_rankprop\_update\_auction)->验证竞价计划->post solr
* 目标：<span style="color:#ff0000">将非上海城市的竞价也迁到竞价中间件<span>

* 详细设计见：[请点击](http://git.corp.anjuke.com/\_user\_site/doc/raw/master/fangyuan/ershou/AuctionAmqp.md)

### 端口中间件

* 流程：

![流程图](images/package\_amqp.png)

* 现状：读取4个端口或房源更新队列表，读取房源信息中的recommend＝1这个字段来标记是否是端口房源
    
* 目标：<span style="color:#ff0000">删除端口房源队列表的和recommend=1字段的依赖，以中间件通知为准<span>

* 消息格式，以json格式通知，具体如下：

```
｛
	“city_id”:城市ID(int),
	"broker_id":经纪人ID(int),
	“pro_id”:房源ID(int),
	"flag":操作类型(int,1,上架 2,下架),
	"from_type":标记类型，默认为0，非必要字段(int)
	"update_time":更新时间(int),
｝
```     

### ppc中间件

* 流程：

![流程图](images/ppc_amqp.png)

* 现状：目前实时rank降权和降段是走的是定价中间件，这样大数据量会阻塞会经纪人操作的定价上下架
* 目标：<span style="color:#ff0000">将降权和降段的数据和正常经纪人操作行为隔离，实时rank不影响经纪人的操作行为<span>

* 消息格式，以json格式通知，具体如下：

```
｛
	“city_id”:城市ID(int),
	"broker_id":经纪人ID(int),
	“pro_id”:房源ID(int),
	"flag":操作类型(int,1,实时rank变更),
	"from_type":标记类型，默认为0，非必要字段(int)
	"update_time":更新时间(int),
｝
``` 

### 房源中间件（新增）

* 详细设计见：[请点击](http://git.corp.anjuke.com/_user_site/doc/browse/master/fangyuan/design/prop_table_split.md)

* 现状：监控4个端口或房源更新队列表更新房源cahce
* 目标：<span style="color:#ff0000">将队列表迁至中间件</span>



### TodoList

* 实时rank由之前通知定价中间件移至ppc中间件
  * 开新job接受ppc中间件中的数据（刘张健）
  * 将ppc实时rank迁到ppc中间件（Lukin）
* 四个队列表中的端口房源迁到端口中间件
  * 开新job接受端口中间件中的数据（刘张健）
  * 将端口数据迁到端口中间件（Lukin）
* 四个队列表中的房源更新迁至房源中间件（拱平）
  * 开新job接受端口中间件中的数据（董菲）
  * 将房源更新数据迁到房源中间件（Lukin）








