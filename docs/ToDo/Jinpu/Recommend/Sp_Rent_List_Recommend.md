# 商铺出租列表页推荐

```
出售：sale
出租：rent
```

## 底部`您可能感兴趣的新铺`推荐

```
BLL:Bll_PropertyInterestedBll->list_activity_solr

Solr config: app-common/solr.php + solr_path:jp-property-v4
```

|类型|solr地址|聚合参数|sort|
|---|---|---|---|
|office|http://solr.dev.jinpu.com/jp-property-v4/|fq:city_id=$city_id, fq:new_office_state=1,fq:is_new=1,fq:is_activity=1|new_house_rank=desc|
|shop|http://solr.dev.jinpu.com/jp-property-v4/|fq:city_id=$city_id, fq:new_shop_state=1,fq:is_new=1,fq:is_activity=1|new_house_rank_shop=desc|

## 右侧`本月人气新铺`推荐

```
List_MonthPopularityComponent->get_shop_list

Solr config: app-common/solr.php + solr_path:jp-property-v4
```

|类型|solr地址|聚合参数|sort|
|---|---|---|---|
|office|http://solr.dev.jinpu.com/jp-property-v4/|fq:city_id=$city_id, fq:list_office=1,fq:business_rent=1或者business_sale=1,fq:district_id=$district_id[可选],fq:block_id=$b_id[可选]|is_activity=desc, id=desc|
|shop|http://solr.dev.jinpu.com/jp-property-v4/|fq:city_id=$city_id, fq:list_office=1,fq:business_rent_shop=1或者business_sale_shop=1,fq:district_id=$district_id[可选],fq:block_id=$b_id[可选]|is_activity=desc, id=desc|

