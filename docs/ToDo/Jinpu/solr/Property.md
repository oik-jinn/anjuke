物业的solr信息  
=====================
------------------
####一、  solr概述

* 主要property的相关信息
* 线下solr地址：`http://solr.anjuke.test/service.php`
* solr名称：jp-property-v4
* 主要数据来源：

```
物业基础信息表：jinpu_db.e_property
价格表：jp_dw_stats.dw_jp_midprice_monthly
新盘新铺信息表：jinpu_db.e_property_new_info
团购表：jinpu_db.e_property_activity
新盘新铺内容信息表：jingpu_db.e_property_new_content
新盘新铺单位面积价格：jinpu_db.e_property_new_price
新盘新铺招商开发商后台统计：jp_dw_stats.stat_property_tuangou（开发商业务已经停掉）
```


------------------
####二、  字段含义

字段|描述|值|
:---------------|:---------------|:---------------
id|物业id
city_id|物业所在城市id
district_id|物业所在区域id
block_id|物业所在版块id
name|物业名称
address|物业地址
type|物业类型
match_name|物业用于匹配的物业名|数据来源于`e_property`.`name`
is_road|物业是否是道路
is_new|是否是新盘|已作废，新盘已由list_office,list_shop来标示是否为新盘新铺
office_state|物业是否有写字楼属性
shop_state|物业是否有商铺属性
is_activity|物业是否有在线的团购活动
attended|物业的团购活动的参加人数
business_rent|新盘是否有租
business_sale|新盘是否有售
business_rent_shop|新铺是否有租
business_sale_shop|新铺是否有售
new_office_state|新盘是否有内容信息
new_shop_state|新铺是否有内容信息
office_trade|新盘主交易类型
shop_trade|新铺主交易类型
new_house_rank|新盘的rank
saleprice|新盘售价|数据来源`e_property_new_price`.`office_sale_min`
rentprice|新盘租价|数据来源`e_property_new_price`.`office_rent_min`
saleprice_shop|新铺售价|数据来源`e_property_new_price`.`shop_sale_min`
rentprice_shop|新铺租价|数据来源`e_property_new_price`.`shop_rent_min`
feature_office|写字楼特性
feature_shop|商铺特性
office_open_date|写字楼开盘时间
shop_open_date|商铺开盘时间
list_office|是否为新盘
list_shop|是否为新铺
vapv|新盘+新铺vcpv|数据来源`stat_property_tuangou`.`vcpv_accum`
new_house_rank_shop|新铺的rank
block_name|物业所在版块的名称
vcpv_xinpan|新盘的vcpv
vcpv_xinpu|新铺的vcpv
saleprice_valid|物业是否有效的新盘售价
rentprice_valid|物业是否有效的新盘租价
saleprice_shop_valid|物业是否有效的新铺售价
rentprice_shop_valid|物业是否有效的新铺租价
tag_office|新盘标签集
tag_shop|新铺标签集
office_promotion|写字楼新盘推荐标题
shop_promotion|商铺新铺推荐标题
is_spider|物业是否是外部抓取的
total_listings|物业的所有房源数|数据来自dw_jp_midprice_monthly房源数总和
completion_data|物业的竣工日期
mid_price_office_sale|物业的写字楼售价|数据来源`dw_jp_midprice_monthly`.`mid_price_office_sale`
mid_price_office_rent|物业的写字楼租价
mid_price_shop_sale|物业的商铺售价
mid_price_shop_rent|物业的商铺租价
mid_price_office_sale_valid|物业是否有效的写字楼售价
mid_price_office_rent_valid|物业是否有效的写字楼租价
mid_price_shop_sale_valid|物业是否有效的商铺售价
mid_price_shop_rent_valid|物业是否有效的商铺租价
rent_price_id|物业的写字楼租金在日租金价格段中的段id
unit_price_id|物业的写字楼售价在单价价格段中的段id
new_district_id|物业的所在新区域id
new_business_id|物业所在新商圈id
district_name|物业所在区域名称
new_district_name|物业所在新区域名称
new_business_name|物业所在新商圈名称
developer_service|是否提供自营销楼盘|自营销功能已经停止
------------------
####历史更改记录：

* 待完善。