### 租房房源推广&信息变更消息通知接口
___

#### 说明

我们房源修改或参加推广等，这些消息由客户端通知用户端，消息以RabbitMQ消息中间件方式通知，现分为两大块，如

* 房源推广中间件
  * 目前提供根据业务提供三组，见如下推广中间件
  * 房源上下架以通知falg为准，房源上下架的顺序以通知的先后顺序
* 房源更新中间件
    * 任何房源自身信息变化都通知此中间件
    * 为了房源数据cache等的一致性


#### 推广竞价中间件

* 消息接口：

    * 测试环境：http://nydus.dev.anjuke.com/publish?tunnel=zf_prop_auction_updown
    * 线上环境：http://nydus.a.ajkdns.com/publish?tunnel=zf_prop_auction_updown
    
* 消息格式:

```
｛
    "city_id":城市ID(int),
    "broker_id":租房经纪人ID(int),
    "pro_type":房源类型;1:个人房源, 2:租房经纪人房源, 13:大业主房源, 14:个人抓取房源,15: 经纪人抓取房源
    "business_type":业务类型1:竞价/精选
    "pro_id":房源ID(int),
    "flag":操作类型(int,1:上架,2:下架),
    "update_time":更新时间(int),
    "from_type":标记类型，默认为0，非必要字段(int)
｝

```
* 中间件数据通过job分发到表
    * 所在表：quque_db.zf\_spread\_queue\_{分表}\_{年月}
    * 分表逻辑：以经纪人id除以10后的余数
    
#### 推广定价中间件

* 消息接口：

    * 测试环境：http://nydus.dev.anjuke.com/publish?tunnel=zf_prop_pricing_updown
    * 线上环境：http://nydus.a.ajkdns.com/publish?tunnel=zf_prop_pricing_updown
    
* 消息格式:

```
｛
    "city_id":城市ID(int),
    "broker_id":租房经纪人ID(int),
    "pro_type":房源类型;1:个人房源, 2:租房经纪人房源, 13:大业主房源, 14:个人抓取房源,15: 经纪人抓取房源
    "business_type":业务类型;2:定价
    "pro_id":房源ID(int),
    "flag":操作类型(int,1:上架,2:下架),
    "update_time":更新时间(int),
    "from_type":标记类型，默认为0，非必要字段(int)
｝

```
* 中间件数据通过job分发到表
    * 所在表：quque_db.zf\_spread\_queue\_{分表}\_{年月}
    * 分表逻辑：以经纪人id除以10后的余数
    

#### 推广端口中间件

* 消息接口：

    * 测试环境：http://nydus.dev.anjuke.com/publish?tunnel=zf_prop_package_updown
    * 线上环境：http://nydus.a.ajkdns.com/publish?tunnel=zf_prop_package_updown
    
* 消息格式:

```
｛
    "city_id":城市ID(int),
    "broker_id":租房经纪人ID(int),
    "pro_type":房源类型;1:个人房源, 2:租房经纪人房源, 13:大业主房源, 14:个人抓取房源,15: 经纪人抓取房源
    "pro_id":房源ID(int),
    "business_type":业务类型;3:端口,
    "flag":操作类型(int,1:上架,2:下架),
    "update_time":更新时间(int),
    "from_type":标记类型，默认为0，非必要字段(int)
｝

```
* 中间件数据通过job分发到表
    * 所在表：quque_db.zf\_spread\_queue\_{分表}\_{年月}
    * 分表逻辑：以经纪人id除以10后的余数
    
___
 
#### 租房消息变更中间件

- 曹阳会通知推广中的房源更新 from_type = 111
- 王旭峰 经纪人新增、更新、删除都会通知 from_type = 222
- 胡侃 个人新增、更新、删除都会通知 from_type = 333

* 消息接口：

    * 测试环境：http://nydus.dev.anjuke.com/publish?tunnel=zf_property_event
    * 线上环境：http://nydus.a.ajkdns.com/publish?tunnel=zf_property_event
    
* 消息格式:

```
｛
    "city_id":城市ID(int),
    "type":房源类型;1:个人房源, 2:租房经纪人房源, 13:大业主房源, 14:个人抓取房源,15: 经纪人抓取房源
    "pro_id":房源ID(int|array),支持多个
    "flag":1 新增 2 更新 3 删除
    "from_type": 111 曹阳 222 王旭峰 333 胡侃
    "update_time":更新时间(int),
｝

```    

    
    






