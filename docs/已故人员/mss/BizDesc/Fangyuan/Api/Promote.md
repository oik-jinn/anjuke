##二手房列表页显示新房


#### Solr字段说明

* 接口推荐逻辑：

    * 推荐同城8公里内的精选房源，按距离由近及远。如精选房源数量不足10套，则推8公里内的定价房源补足
    * 按距离由近及远。如精选+定价也不足10套则有几套则显示几套


* 接口地址：

    * 测试环境：http://site-api.pmt-25421-site.anjuke.test/esf/promote/
    * 线上环境：http://site-api.a.ajkdns.com/esf/promote/
    * 说明：此接口是内部API，仅app机器之前可访问（办公室网络也可访问）
    
* 请求方式：GET

* 调用示例：

```
http://site-api.pmt-25421-site.anjuke.test/esf/promote/?p=1&city_id=11&map_type=1&longitude=121.50662942&latitude=31.15850813
```

* 调用参数说明：

Field|Type|Required|Description
---|---|---|---
p |int|Yes|推荐引流id，此接口用1
city\_id |int|Yes|城市ID
map\_type|int|Yes|经纬度类型：1:百度，2:谷歌，默认百度<br />(目前仅支持百度经纬度)
longitude |float|Yes|经度
latitude |float|Yes|纬度

* 返回数据格式：JSON
* 返回参数说明

Field|Type|Description
---|---|---
status |int|200:正常;101:调用参数错误
result |json|property:房源信息<br />owner:房源所有人信息（即经纪人）<br /> community:小区信息<br /> region:区域信息<br /> block:版块信息

* result.property返回值详细

Field|Type|Description
---|---|---
id |int|房源ID
ciy\_id |int|城市ID
owner\_id |int|所有人ID（经纪人ID）
community\_id |int|小区ID
title |string|房源标题
area\_num |int|面积，单位平方米
unit\_price |float|单价，单位元
total\_price |float|总价，单位元
room |int|几室数量
hall |int|几厅数量
toilet\_num |int|几卫数量
commission\_type |int|委托类型：2：房东认证
hight\_quality |int|1：多图
image\_id |string|AIS服务的图片ID
host\_id |int|AIS服务的图片hostId
default\_image |sring|默认图url，也可根据image\_id,host\_id自行生成
is\_school\_district |int|1:学区房;0:非学区房
is\_metro\_district |int|1:地铁房;0:非地铁房
url |string|房源单页的url，其中参数spread是业务类型，from是来源加码


* result.owner返回值详细

Field|Type|Description
---|---|---
id |int|房源所有人id，此接口即是经纪人ID
name |string|房源所有人名称

* result.community返回值详细

Field|Type|Description
---|---|---
id |int|小区ID
name |string|小区名
default\_name |string|小区默认名

* result.region返回值详细

Field|Type|Description
---|---|---
id |int|区域ID
name |string|区域名称
code |string|区域代码

* result.block返回值详细

Field|Type|Description
---|---|---
id |int|版块ID
name |string|版块名称
code |string|版块代码
说明：有些小城市，只有region，没有block
    
    






