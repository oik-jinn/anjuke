# 写字楼出售列表页筛选项DB

* [商业地产筛选项公共db](Common_DB.md)

* 开通写字楼的商圈聚合城市

```
配置文件路径: /jinpu-site/app-common/config/multicity.php
```

* 写字楼出售开通商圈聚合的城市

```
`上海、北京、广州、深圳、成都、重庆`, 对应配置文件key=clues_search_condition_office_sale_list_switch
```

* 写字楼出售|出售`区域`专用DB

```
只有上海、北京、广州、深圳、成都、重庆这6个城市开通了商圈
```

|业务名称|database|table|详情描述|
|---    |---     |---  |---    |
|区域表|jinpu_db|d_new_district|---|
|商圈表|jinpu_db|d_new_business|字段new_district_id对应d_new_district表的主键id|
|关系表|jinpu_db|d_new_business_block_map|---|

* 写字楼出售`面积`db

```
jinpu_db.d_filter_area_sale
```

* 写字楼出售`总价`db

```
jinpu_db.d_filter_total_price
```