# 二手房房源表

## 安居客经纪人端房源表

#### 个人房源表
* <span style="color:#ff0000;">所在表</span>anjuke_db.ajk_pprops_sale

#### 经纪人房源表

* 所在表
* propertys_db.ajk_propertys
* propertys_sh_db.ajk_propertys
* propertys_db_04.ajk_propertys
* propertys_other_db.ajk_propertys
* 说明：
经纪人房源数据是分库的，分库规则如下：
* 上海（cityId=11）:propertys_sh_db.ajk_propertys
* 北京（cityId=14）:propertys_db.ajk_propertys
* other（cityId=<41）:propertys_other_db.ajk_propertys
* four（cityId>=42）:propertys_db_04.ajk_propertys
房源状态标识：
* OperateState 正常是1，删除12

#### 经纪人抓取房源表 

* 所在表：同上经纪人房源表，通过字段sowooid>0 说明是抓取房源
* 抓取房源经纪人信息获得
* 所在表：anjuke_db.ajk_swsale
* 查询方式：通过sowooid(即抓取经纪人id)

##  安居客用户端隔离表设计

* 说明：
* 以每个城市一张表的策略建表
* 上海北京各单独一个db
* 18个重点二三线城市分散在余下的3个db里
* 剩余的城市以 city_id mod 3 分在余下的3个db,以后新开的城市也以这个规则来分配

* db名称(已有)
* user_prop_sh_db
* user_prop_bj_db
* user_prop_s00_db
* user_prop_s01_db
* user_prop_s02_db
* user_prop_db

常用及较小的字段放在基础表，大字段放扩展表
经纪人房源，抓取房源，和个人三种类型的房源表都是一个结构


### 表名称

* 基础表 xxx_property_{cityid}  
* 扩展表 xxx_property_extend_{cityid}
* 房源ID生成器表 xxx_index

### 基础表字段 xxx_property_{cityid} 

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
update_datetime  | timestamp  |Y|


### 扩展表字段

Field | Type | Required | Description
---|---|---|---|---|---|---
id|	bigint(20) unsigned|Y|	PRI			
add_explain|	text	|Y|				房源描述
extend_field|	text	|Y|				房源扩展字段,json类型
update_datetime|	timestamp|Y|		CURRENT_TIMESTAMP	on update CURRENT_TIMESTAMP	


### 房源ID生成器表

Field | Type | Required | Description
---|---|---|---|---|---|---
id|	bigint(20) unsigned|Y|	PRI		auto_increment	房源ID
source_type|	tinyint(3) unsigned|Y|		房源类型,1:安居客经纪人,3:安居客抓取房源,4:新房房源5:58经纪人房源,6:58抓取房源
city_id|	smallint(5) unsigned|Y|	城市id

## 不同房源类型隔离表

#### 安居客用户端经纪人隔离表

* user_prop_xx_db.broker_property_{城市ID}
* user_prop_xx_db.broker_property_extend_{cityid}
* user_prop_db.property_index

* 图片信息存储在redis
```
pg环境配置
$config['erhsou_prop_img_cluster'] = array(
        array('host'=>'192.168.1.24','port'=>6379,'alias'=>'master','master'=>true),
        array('host' => '192.168.1.24', 'port' => 6379, 'alias'=>'slave')
        );
line环境配置
$config['erhsou_prop_img_cluster'] = array(
        array('host'=>’10.10.3.101’,port' =>6471,'alias'=>'master','master'=>true),
        array('host'=>'10.10.3.123','port’=>6741,'alias'=>'slave')
        );
存储格式
```

#### 安居客用户端抓取房源表

* user_prop_xx_db.crawl_property_xx
* user_prop_xx_db.crawl_property_extend_xx
* user_prop_db.crawl_propperty

```
source_type => 3
extend 里包含图片信息
```
#### 58房源

* user_prop_xx_db.wuba_property_${city_id}
* user_prop_xx_db.wuba_property_extend_${city_id}
* user_prop_xx_db.wuba_property_publish_${city_id}
* user_prop_xx_db.wuba_property_image_${city_id}

```
source_type:  5  => 58经纪人房源， 6 => 58抓取房源
```

