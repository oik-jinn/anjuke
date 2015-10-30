##房源推广信息处理接口


#### 二手房

* 接口地址：

    * 测试环境：http://site-api.pmt-23623-site.anjuke.test/esf/spread-info/
    * 线上环境：http://site-api.a.ajkdns.com/esf/spread-info/
    
* 示例：http://site-api.pmt-23623-site.anjuke.test/esf/spread-info/?pro_id=168945636&pro_type=1
* 请求方式：GET
* 调用参数说明：

Field|Type|Description
---|---|---
pro_id|int|房源ID
pro_type|int|房源类型，经纪人房源

* 返回参数说明

Field|Type|Description
---|---|---
auction|string|竞价精选中间件信息
pricing|string|定价中间件信息
package|string|端口中间件信息

* auction返回数据说明

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
	"from_type":标记类型，默认为0，非必要字段(int)
	"update_time":更新时间(int),
	"business_type":1:竞价精选通知,
	"msg_id":中间件消息id(string),
	"handle_state":处理状态,1:表示已处理,0:未处理(int),
｝

```

* package和pricing返回数据说明:

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
    "business_type":2:定价通知，3:端口通知(string),
    "msg_id":中间件消息id(string),
	"handle_state":处理状态,1:表示已处理,0:未处理(int),
｝

``` 
    
    






