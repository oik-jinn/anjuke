# 商铺出租列表页筛选项DB

* [商业地产筛选项公共db](Common_DB.md)

* 开通商铺的商圈聚合城市

```
配置文件路径: /jinpu-site/app-common/config/multicity.php
```

* 商铺出租开通商圈聚合的城市

```
`上海、北京、广州、深圳、成都、重庆`, 对应配置文件key=clues_search_condition_shop_rent_list_switch
```

* 商铺出租|出售`区域`专用DB

```
只有上海、北京、广州、深圳、成都、重庆这6个城市开通了商圈
```

|业务名称|database|table|详情描述|
|---    |---     |---  |---    |
|区域表|jinpu_db|d_new_shop_district|---|
|商圈表|jinpu_db|d_new_shop_business|字段new_district_id对应d_new_shop_district表的主键id|
|关系表|jinpu_db|d_new_shop_business_block_map|---|

* 商铺出租`面积`db

```
jinpu_db.d_shop_filter_area_rent
```

* 商铺出租`月租金`db

```
jinpu_db.d_shop_filter_monthly_rent
```

* 商铺出租`行业`读取的是代码层配置

```
Model_Shop_AI::get_emun_shop_industry
```

* 商铺出租`更多筛选-状态`读取代码层配置

```
Model_Shop_AI::get_emun_business_state
```

* 商铺出租`更多筛选-类型`读取代码层配置

```
Model_Shop_AI::get_emun_shop_type
```

