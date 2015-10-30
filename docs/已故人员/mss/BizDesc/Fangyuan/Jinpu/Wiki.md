### 中间件及上下架设计

* [戳这里](http://gitlab.corp.anjuke.com/_site/docs/tree/master/DesignDoc/Fangyuan/Jinpu/UserSiteHouseUpdown_Site_20140829)

### 房源solr

* 字段说明
 
	* 定价、竞价、套餐
 
 		* spread_type == 1，如果有套餐，则为套餐房源；反之，为定价房源
 		* spread_type == 2，竞价房源
 	
	* 在线
 
 		* fq=list_time:[* TO 当前时间戳]
 
* 分组
	
	* 写字楼租
	
    http://sc10-001.a.ajkdns.com:8983/jp-xiezilou-zu/select/?q=city_id:11&fq=list_time:[* TO 当前时间戳]&spread_type=[类型]
	
	* 写字楼售
	
	http://sc10-001.a.ajkdns.com:8983/jp-xiezilou-shou-v36/select/?q=city_id:11&fq=list_time:[* TO 当前时间戳]&spread_type=[类型]
	
	* 商铺租
	
	http://sc10-001.a.ajkdns.com:8983/jp-shangpu-zu/select/?q=q=city_id:11&fq=list_time:[* TO 当前时间戳]&spread_type=[类型]
	
	* 商铺售
	
	http://sc10-001.a.ajkdns.com:8983/jp-shangpu-shou/select/?q=city_id:11&fq=list_time:[* TO 当前时间戳]&spread_type=[类型]

### 房源DB及TABLE

* [戳这里](http://gitlab.corp.anjuke.com/_site/docs/blob/master/ToDo/Jinpu/solr/house.md)