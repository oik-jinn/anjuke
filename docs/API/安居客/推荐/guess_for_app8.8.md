## APP8.8版本－推荐频道猜你喜欢

### `接口说明`
```
app推荐频道的猜你喜欢展示
主要有3个tab：
	二手房／租房／新房
此次改动主要有几下几点：
	1.由于之前接口的格式不符合列表格式，所以这次会新增一个接口，接口返回格式与各种列表和推荐列表格式保持一致
	2.新增租房tab，接口中会新增zf_data数据
	
推荐逻辑：
	优先根据用户历史浏览记录进行推荐；若为新用户，则返回附近房源。
```

### `API说明`
|API来源|URL|
|---|---|
|开发环境|[http://api.beckyxu.dev.anjuke.com/mobile/v5/recommend/guess/new/?city_id=11&location=31.223932%2C121.536599&first=20150605&macid=4bc980cb7351f8b96e6c3831a7df2192&app=a-ajk&i=866231026908388&cv=8.7&cid=-1](http://api.beckyxu.dev.anjuke.com/mobile/v5/recommend/guess/new/?city_id=11&location=31.223932%2C121.536599&first=20150605&macid=4bc980cb7351f8b96e6c3831a7df2192&app=a-ajk&i=866231026908388&cv=8.7&cid=-1)|
|测试环境|[http://api.anjuke.test/mobile/v5/recommend/guess/new?city_id=11&location=31.223932%2C121.536599&first=20150605&macid=4bc980cb7351f8b96e6c3831a7df2192&app=a-ajk&i=866231026908388&cv=8.7&cid=-1](http://api.anjuke.test/mobile/v5/recommend/guess/new/?city_id=11&location=31.223932%2C121.536599&first=20150605&macid=4bc980cb7351f8b96e6c3831a7df2192&app=a-ajk&i=866231026908388&cv=8.7&cid=-1)|
|线上环境|[http://api.anjuke.com/mobile/v5/recommend/guess/new/?city_id=11&location=31.223932%2C121.536599&first=20150605&macid=4bc980cb7351f8b96e6c3831a7df2192&app=a-ajk&i=866231026908388&cv=8.7&cid=-1](http://api.anjuke.com/mobile/v5/recommend/guess/new/?city_id=11&location=31.223932%2C121.536599&first=20150605&macid=4bc980cb7351f8b96e6c3831a7df2192&app=a-ajk&i=866231026908388&cv=8.7&cid=-1)|

### `传入参数说明`
|参数名称|参数含义|是否必须|说明|
|---|---|---|---|
| location |经纬度，格式为"lat,lon"|首次调用必须|新用户返回附近房源需要|
| first |首次调用日期|首次调用必须|格式：20150417|
| city_id|城市id|是|推荐补充逻辑需要
| cv|版本号|是|推荐补充逻辑需要
| app |app名称|是|
| i| android设备码|android必须|
| macid| android设备码|android必须|
| udid2 |ios设备码|ios必须|
| tab |频道（esf, xf, zf）|频道调用必须|

### `输出字段说明`
|参数名称|参数类型|说明|
|---|---|---|
| status |string|返回状态：正常ok、错误现实错误码|
| msg|string|错误信息，仅当status非ok时返回错误码|
| data |`object`|返回结果|
| data.tab_name |string|频道，根据用户历史浏览记录得出（esf, xf, zf）|
| data.esf_data |`object`|二手房频道数据|
| data.zf_data |`object`|租房频道数据|
| data.xf_data |`object`|新房频道数据|

* [二手房频道数据格式参考](http://gitlab.corp.anjuke.com/Beckyxu/api/blob/master/recommend/%E4%BA%8C%E6%89%8B%E6%88%BF%E5%87%BA%E5%8F%82%E8%AF%B4%E6%98%8E.md)

* [租房频道数据格式参考](http://gitlab.corp.anjuke.com/Beckyxu/api/blob/master/recommend/%E7%A7%9F%E6%88%BF%E5%87%BA%E5%8F%82%E8%AF%B4%E6%98%8E.md)(result说明)

* 新房频道数据格式同首页楼盘推荐

