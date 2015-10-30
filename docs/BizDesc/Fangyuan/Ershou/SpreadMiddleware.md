## 二手房源推广中间件
---


### 精选中间件

* 消息接口：

    * 测试环境：http://xapp20-061.i.ajkdns.com/publish?tunnel=esf_prop_auction_updown
    * 线上环境：http://nydus.a.ajkdns.com/publish?tunnel=esf_prop_auction_updown
    
* 消息格式:

```
｛
	“city_id”:城市ID(int),
	"broker_id":经纪人ID(int),
	“pro_id”:房源ID(int),
	"flag":操作类型(int,1:上架,2:下架),
	"hpratio":好盘系数即出价(int,时间戳),
	"comms_hpratio_a":多小区下房源排名系数第二版（出价×质量度×房型热门度×小区竞争度）(int,时间戳),
	"comms_hpratio":多小区下房源排名系数(int,时间戳),
	"hpstarttime":开始展示时间(int,时间戳),
	"hpendtime":结束展示时间(int,时间戳),
	"hpplanid":推广计划ID(int),
	"from_type":标记类型，默认为0，非必要字段(int),
    "type":精选类型（int,1:小区精选，2：板块精选）
	"update_time":更新时间(int),
｝
说明：其中hpratio对应的是ark_db.ajkpropspread的offer字段
type为新增字段（2015/08/25）
```
* 中间件数据通过job分发到表
    * 所在表：quque_db.esf_auction_queue_{分表}_{年月}
    * 分表逻辑：以经纪人id除以5后的余数
    
    
### 套餐中间件    
    
#### 定价中间件

* 消息接口：

    * 测试环境：http://xapp20-061.i.ajkdns.com/publish?tunnel=esf_prop_pricing_updown
    * 线上环境：http://nydus.a.ajkdns.com/publish?tunnel=esf_prop_pricing_updown
    
* 消息格式:

```
｛
    “city_id”:城市ID(int),
    "broker_id":经纪人ID(int),
    “pro_id”:房源ID(int),
    "flag":操作类型(int,1:上架,2:下架),
    "hpstarttime":开始展示时间(int,时间戳),
    "hpendtime":结束展示时间(int,时间戳),
    "hpplanid":推广计划ID(int),
    "update_time":更新时间(int),
    "from_type":标记类型，默认为0，非必要字段(int)
｝
说明：其中hpratio对应的是ark_db.ajkpropspread的offer字段

```


#### 端口中间件

* 消息接口：

    * 测试环境：http://xapp20-061.i.ajkdns.com/publish?tunnel=esf_prop_package_updown
    * 线上环境：http://nydus.a.ajkdns.com/publish?tunnel=esf_prop_package_updown
    
* 消息格式:

```
｛
    “city_id”:城市ID(int),
    "broker_id":经纪人ID(int),
    “pro_id”:房源ID(int),
    "flag":操作类型(int,1:上架,2:下架),
    "update_time":更新时间(int),
    "from_type":标记类型，默认为0，非必要字段(int)
｝

```

#### 中间件数据通过job分发到表

    * 所在表：quque_db.esf_spread_queue_{分表}_{年月}
    * 分表逻辑：以经纪人id除以10后的余数
    

### 房源信息更新中间件    

#### 房源基础信息、扩展信息更新
    
* 消息接口：

    * 测试环境：http://xapp20-061.i.ajkdns.com/publish?tunnel=esf_property_event
    * 线上环境：http://nydus.a.ajkdns.com/publish?tunnel=esf_property_event
    
* 消息格式:

```
｛
    “city_id”:城市ID(int),
    “pro_id”:房源ID(int),
    "flag":操作类型(1:新增,2:修改,3:删除)
    "type":房源类型(1:经纪人),
    "from_type":标记类型，默认为0，非必要字段(int)
    "update_time":更新时间(int),
｝

``` 

    
#### 房源附属信息中间件

* 消息接口：

    * 测试环境：http://xapp20-061.i.ajkdns.com/publish?tunnel=esf_prop_state_update
    * 线上环境：http://nydus.a.ajkdns.com/publish?tunnel=esf_prop_state_update
    
* 消息格式:

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
    
    






