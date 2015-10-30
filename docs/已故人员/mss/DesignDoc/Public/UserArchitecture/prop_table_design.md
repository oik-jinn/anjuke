

## 用户房源表设计


### 分库规则

逻辑上以城市分库

在分库的基础上每个城市分不同的表

初期计划5个db

* 上海北京各单独一个
* 18个重点二三线城市分散在余下的3个db里
* 剩余的城市以 city_id mod 3 分在余下的3个db,以后新开的城市也以这个规则来分配

db名称

* user_prop_sh_db
* user_prop_bj_db
* user_prop_s00_db
* user_prop_s01_db
* user_prop_s02_db


当前城市房源数,只统计大于10万的

* 46 厦门 1735112 s00
* 42 珠海 463120 s01
* 13 深圳 430847 s02
* 44 中山 425414 s02
* 50 长春 380143 s01
* 18 杭州 334981 s01
* 15 成都 277633 s02
* 62 南宁 205730 s02
* 49 海口 202812 s01
* 12 广州 182156 s01
* 19 苏州 170770 s02
* 20 重庆 170208 s02
* 21 大连 168605 s01
* 26 郑州 158686 s00
* 17 天津 150015 s01
* 65 泉州 148074 s02
* 40 昆山 136476 s02 
* 16 南京 126583 s01 
* 30 青岛 122996 s00
* 35 福州 111356 s00
* 33 合肥 108778 s01
* 23 济南 108004 s02

### 表设计

常用及较小的字段放在基础表，大字段放扩展表

经纪人房源，抓取房源，和个人三种类型的房源表都是一个结构

基础表字段

* id bigint unsigned primary key 
* city_id  smallint unsigned  城市id 
* source_type int 房源类型，经纪人，个人，抓取...
* owner_id int unsigned  根据source_type不同，经纪人id或者发房id 可能对应不同的表
* community_id int unsigned
* title varchar(120)
* use_type_id int unsigned 房屋类型，公寓/别墅/老公房，对应use_type
* build_year smallint unsigned 建造年代，对应house age
* area_num   float(8,2) 面积
* unit_price int unsigned 单价，单位元
* total_price int unsigned 总价，单位元
* room_num tinyint unsigned 房间数量 几室
* hall_num tinyint unsigned 客厅数量
* toliet_num tinyint unsigned 厕所数量
* fitment_id  int unsigned 装修情况 
* house_orient varchar(20) 房屋朝向 
* floor	smallint unsigned	房源所在的楼层数
* floor_num	smallint unsigned	楼层总数
* tag  varchar(100) 房源标签 
* state bool 房源是否可见 合并operateState,IsVisible等字段,0是正常，1是经纪人下线，2是违规,etc
* post_timestamp 	int unsigned 发房时间,精确到秒
* update_datetime	timestamp	on update CURRENT_TIMESTAMP


扩展表

* prop_id
* add_explain text  房源描述
* extend_field varchar(1000) 扩展字段，json类型。目前包括首付的价格
* update_datetime	timestamp	on update CURRENT_TIMESTAMP

表名称

xxx_property\_{cityid}和xxx_property_extend\_{cityid}

* 经纪人 broker_property\_{cityid} broker_property_extend\_{cityid}
* 抓取房源   crawl_property\_{cityid}  crawl_property_extend\_{cityid}
* 个人房源暂时不做

### 其他表

索引表，根据prop_id找city_id。按目前的情况经纪人房源和抓取房源都直接使用经纪人的表，所以这个表暂时也不用建出来
 