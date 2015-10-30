楼盘的solr信息  
=====================
------------------
####一、  solr概述

* 主要building的相关信息
* 线下solr地址：`http://solr.anjuke.test/service.php`
* solr名称：jp-building-v4
* 主要数据来源：

```
楼盘基础信息表：jinpu_db.e_property

价格表：jp_dw_stats.dw_jp_midprice_monthly
```


------------------
####二、  字段含义

字段|描述|值|
:---------------|:---------------|:---------------
id|楼盘id
name|楼盘名字
city_id|楼盘所在城市id
district_id|楼盘所在区域id
block_id|楼盘所在版块id
address|楼盘地址
total_listings|楼盘所有房源数|数据来自dw_jp_midprice_monthly房源数总和
mid_price_office_sale|楼盘写字楼售价
mid_price_office_rent|楼盘写字楼租价
mid_price_shop_sale|楼盘商铺售价
mid_price_shop_rent|楼盘商铺租价
completion_data|竣工时间
new_district_id|楼盘所在新区域id
new_district_name|楼盘所在新区域名字
new_business_id|楼盘所在新商圈id
new_business_name|楼盘所在新商圈名字
rent_price_id|楼盘写字楼租价在日租金价格段中的段id
unit_price_id|楼盘写字楼售价在单价价格段中的段id
mid_price_office_sale_valid|楼盘写字楼售价是否有效
mid_price_office_rent_valid|楼盘写字楼租价是否有效
mid_price_shop_sale_valid|楼盘商铺售价是否有效
mid_price_shop_rent_valid|楼盘写字楼租价是否有效

------------------
####历史更改记录：

* 待完善。