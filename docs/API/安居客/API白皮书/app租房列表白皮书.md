## app租房列表白皮书

### 1、页面URL
```
打开安居客app,点击首页的`租房`按钮
```

### 2、页面说明
租房列表页面主要有以下接口：

* 2.1、区域板块接口
* 2.2、其他筛选项接口
* 2.3、猜你喜欢推荐接口
* 2.4、列表接口
    
### 3、页面模块列表
|序号|URL|仓库|负责人|功能|
| --- |--- | --- | --- | --- | --- |
|2.1|http://api.anjuke.com/haozu/mobile/2.0/city.get|haozu|晓路|区域板块接口|
|2.2|http://api.anjuke.com/haozu/mobile/2.0/city.getFilters|haozu|晓路|其他筛选项接口|
|2.3|http://api.anjuke.com/mobile/v5/recommend/rent/list/history|users-site|晓路|猜你喜欢推荐接口|
|2.4|http://api.anjuke.com/haozu/mobile/2.0/prop/search|haozu|晓路|列表接口|


### 4、依赖的内部服务或config(指的是其它仓库的服务)
|序号|服务名|仓库地址|负责人(或部门)|功能|配置列表|内层/外层|
|---| --- | --- | --- | --- | --- | --- | --- |
|2.1|common.php|haozu|晓路|坐标偏移量|map_offset|内层|
||multicity.php|haozu|晓路|城市配置|cities、city_set|内层|
||cache.php|haozu|晓路|缓存服务器配置|orm_servers|外层|
|2.2|mobile_api.php|haozu|晓路|api相关配置|price|内层|
||house.php|haozu|晓路|筛选项配置|bedroom、source、ajk_fitment、renttype、orderby|内层|

### 5、依赖的外部URL
|序号|配置名|URL|功能|其它|
|---| --- | --- | --- | --- |
|2.1|nlogger.php|shipper.logger.a.ajkdns.com|app log通过nlogger上报记录|端口24225/24226|
    
### 6、数据库和表
|序号|数据库|表名称|作用|读写|是否独有|
|---| --- | --- | --- | --- | --- |
|2.1|rent_db|area|区域板块表|读|否|
|||map_areas_baidu|坐标信息|读|否|
|||map_metros_baidu|地铁线路信息|读|否|
|||map_metro_stations_baidu|地铁站点信息|读|否|
|2.2|rent_db|zf_price|租房价格|读|否|
||anjuke_db|ajk_usetype|房屋类型|否|
|||sw_metros|地铁信息|读|否|
|||sw_metro_stations|站点信息|读|否|
||mobile_api_db|metro_prop_stat|站点租房数据|读|否|

### 7、Memcache&Redis
|名称|类型|地址|申请大小|使用率|是否统一管理|功能|负责人|key|
|--- | --- | --- | --- | --- | --- | --- | --- | --- |
| orm_servers |memcache|10.10.3.51::11213、10.10.3.54::11213||||||MetrosBaidu_get_metros_by_cityid${city_id}_${status}|
|||||||||MetroStationsBaidu_get_stations_by_metroid${metro_id}_${status}||||
|||||||||ORMZfPrice_get_city_price${cityid}_${type
|||||||||'haozu_comm_usetype_'.$cityid

### 8、关联job
|job名称|job管理地址|功能|负责人|
|--- | --- | --- | --- |