## 安居客9.0版本推荐API设计说明
##### 说明:在以下API中的入参指的是除去[公共参数](http://gitlab.corp.anjuke.com/Beckyxu/api/blob/master/common/app%E5%85%AC%E5%85%B1%E5%8F%82%E6%95%B0%E8%AF%B4%E6%98%8E.md)后所需要的参数
### 租房单页猜你喜欢
##### 请求地址:
##### (线上) http://api.anjuke.com/mobile/v5/recommend/rent/view/like
##### (线下) http://api.anjuke.test/mobile/v5/recommend/rent/view/like
##### 请求方式: GET
##### 入参
|参数|类型|是否必须|说明|
| --- | --- | --- | --- |
|city_id|int|是|城市id|
|item |string|是|房源id|
|type |int|是|房源类型,0 是个人房源,3是经纪人房源,13是大业主房源,16是58经纪人房源,17是58个人认证人审房源,18是58个人认证机审房源,19是58个人未认证房源,20是58抓取房源|
|limit|int|否|返回记录个数,默认20个|
|offset|int|否|偏移量,默认为0|
##### [租房出参说明](http://gitlab.corp.anjuke.com/Beckyxu/api/blob/master/recommend/%E7%A7%9F%E6%88%BF%E5%87%BA%E5%8F%82%E8%AF%B4%E6%98%8E.md)
##### [BI接口](http://gitlab.corp.anjuke.com/_microdata/recommend-api-doc/blob/master/rent-app-pro-history.md)
### 帮你找房是否发布过找房需求
##### 请求地址:
##### (线上) http://api.anjuke.com/mobile/v5/sale/prop/require/hasRequired
##### (线下) http://api.anjuke.com/mobile/v5/sale/prop/require/hasRequired
##### 请求方式: GET
##### 入参(无)
#### 出参
|参数名称|类型|说明|
|---|---|---|
| status |string|返回状态：正常ok、错误error|
| msg |string|错误信息，仅当status为error时返回|
| results |bool|返回结果，仅当status为ok时返回,true,或者false|
### 帮你找房大家在看
##### 请求地址:
##### (线上) http://api.anjuke.com/mobile/v5/recommend/sale/help/find
##### (线下) http://api.anjuke.test/mobile/v5/recommend/sale/help/find
##### 请求方式: GET
##### 入参
|参数|类型|是否必须|说明|
| --- | --- | --- | --- |
|city_id|int|是|城市id|
|map_type|int|否|地图坐标类型,1为baidu,2为google|
|lat|double|否|纬度|
|lng |double|否|经度|
|limit|int|否|返回记录个数,默认20个|
|offset|int|否|偏移量,默认为0|
##### [二手房出参说明](http://gitlab.corp.anjuke.com/Beckyxu/api/blob/master/recommend/%E4%BA%8C%E6%89%8B%E6%88%BF%E5%87%BA%E5%8F%82%E8%AF%B4%E6%98%8E.md)
##### [BI接口](http://gitlab.corp.anjuke.com/_microdata/recommend-api-doc/blob/master/sale-app-index-find.md)
### 首页猜你喜欢(只针对开放了新房的城市)(由新房提供)
### 通过房源id和房源类型获得二手房的详细信息接口(供新房接口调用)
##### 地址
##### 线上:api.a.ajkdns.com/mobile/v5/sale/prop/getProperties
##### 线下:api.a.anjuke.test/mobile/v5/sale/prop/getProperties
##### 请求方式:GET
##### 入参
|参数|类型|是否必须|说明|
| --- | --- | --- | --- |
|city_id|int|是|城市id|
|prop_ids|string|是|房源标识,比如1-123,3-3345455,5-4566,6-4345645,其中1,3,5,6代表房源类型,1代表安居客经纪人,3代表安居客抓取,5代表58经纪人房源,6 代表58抓取房源,'-' 后的数字表示房源的id|
##### 出参
详见[二手房出参](http://gitlab.corp.anjuke.com/_site/docs/blob/master/API/%E5%AE%89%E5%B1%85%E5%AE%A2/recommend/%E4%BA%8C%E6%89%8B%E6%88%BF%E5%87%BA%E5%8F%82%E8%AF%B4%E6%98%8E.md)

