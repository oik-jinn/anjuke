#### 房源表
* 说明：
	* 以每个城市一张表的策略建表
	* 上海北京各单独一个
	* 18个重点二三线城市分散在余下的3个db里
	* 剩余的城市以 city_id mod 3 分在余下的3个db,以后新开的城市也以这个规则来分配
	* 房源ID由统一发号器生成

* db名称(已有)
    * user\_prop\_db
	* user\_prop\_sh\_db
	* user\_prop\_bj\_db
	* user\_prop\_s00\_db
	* user\_prop\_s01\_db
	* user\_prop\_s02\_db

* 建表策略
   * 每个业务单独一个表名, 目前有如下房源类型表
   *  broker\_property\_{城市ID}-安居客经纪人
   *  wuba\_property\_{城市ID}-58的导入的房源
   *  crawl\_property\_{城市ID}-抓取的房源

* 房源基本表:broker\_property\_{城市ID}

Field | Type | Required | Description
---|---|---|---|---|---|---
id         | bigint(20) unsigned primary key | Y |房源id,自增
city_id    | smallint(5) unsigned             |Y  | 城市id 
source_type |tinyint(3) unsigned| Y|房源类型，房源类型：经纪人的为A/1, 抓取房源C/3,58经纪人E/5、58个人F/6
owner_id |int(10) unsigned |Y | 根据source_type不同，经纪人id或者发房id 可能对应不同的表
community_id| int(10) unsigned|Y|小区id
title| varchar(120)|Y|房源标题
use_type_id |int(10) unsigned|Y| 房屋类型，公寓/别墅/老公房，对应use_type
build_year| smallint(5) unsigned |Y|建造年代，对应house age
area_num | int(10) unsigned |Y|面积,使用时需要/100.0
unit_price |int(10) unsigned |Y|单价，单位元
total_price |int(10) unsigned |Y|总价，单位元
room_num |tinyint(3) unsigned|Y| 房间数量 几室
hall_num |tinyint(3) unsigned|Y| 客厅数量
toliet_num| tinyint(3) unsigned|Y| 厕所数量
fitment_id | int(10) unsigned |Y|装修情况 
house_orient |varchar(10) |Y|房屋朝向 
floor| smallint(5) unsigned|Y|   房源所在的楼层数
floor_num |smallint(5) unsigned|Y|   楼层总数
tag  |varchar(100) |Y|房源标签 
commission_type|tinyint(3) unsigned|Y|佣金类型
high_quality        |tinyint(3) unsigned |Y| 移方式存储<br>00000001    1    多图<br> 00000010    2    房产季房源<br>映射在数据表中即是 3
image_id|varchar(100)|Y|				默认图文件名
host_id	|tinyint(3) unsigned	|Y	|	默认图HostId
state| tinyint(3) unsigned |Y|房源是否可见 合并operateState,IsVisible等字段,0是正常，1是经纪人下线，2是违规,etc
post_timestamp | int(10) unsigned |Y|发房时间,精确到秒
update_datetime  | timestamp  |Y| 更新时间
	
	
* 房源扩展表:broker\_property\_extend\_{城市ID}

Field | Type | Required | Description
---|---|---|---|---|---|---
id|	bigint(20) unsigned|Y|	PRI			
add_explain|	text	|Y|				房源描述
extend_field|	text	|Y|				房源扩展字段,json类型
update_datetime|	timestamp|Y|		CURRENT_TIMESTAMP	on update CURRENT_TIMESTAMP	



* 房源ID发号器表:property_index

Field | Type | Required | Description
---|---|---|---|---|---|---
id|	bigint(20) unsigned|Y|	PRI		auto_increment	房源ID
source_type|	tinyint(3) unsigned|Y|		房源类型,1:安居客经纪人,3:安居客抓取房源,4:新房房源5:58经纪人房源,6:58抓取房源
city_id|	smallint(5) unsigned|Y|	城市id
