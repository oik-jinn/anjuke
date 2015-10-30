# 物业筛选项DB

* [商业地产筛选项公共db](Common_DB.md)

* 物业开通商圈聚合的城市

```
配置文件路径: /jinpu-site/app-common/config/multicity.php

`上海`, 对应配置文件key=clues_search_condition_loupan_list_switch
```

* 物业聚合专用DB

```
只有上海这一个城市开通了物业的商圈，在获取区域时需要读取jinpu_db.d_new_district表，其他城市读取jinpu_db.d_district
```

`开通物业聚合的城市读取jinpu_db.d_new_district和d_new_business, 没有开通聚合的读取d_district和d_district_block`

|业务名称|database|table|详情描述|
|---    |---     |---  |---    |
|区域表|jinpu_db|d_new_district|---|
|商圈表|jinpu_db|d_new_business|字段new_district_id对应d_new_district表的主键id|
|月租金、日租金|jinpu_db|d_filter_daily_rent|月租金与日租金都读取该表|

* 物业筛选项`售价`DB

```
jinpu_db.d_filter_unit_price
```

* 物业筛选项`月租金|日租金`代码层hard_code

```
city_id = 11、14、18(上海、北京、杭州)时，显示`日租金`，其他城市显示`月租金`，对应代码/jinpu-site/app-web/page/property/ShopList.php #line 80
```

