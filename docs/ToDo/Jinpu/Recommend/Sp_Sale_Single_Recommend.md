# 商铺出售单页推荐

```
Todo
```

## `您可能感兴趣的商铺`推荐

```
APS:
    走的APS服务
code:
    $obj_aps->get_aps_client('mysql')->start_request("Bll_ShopDetailBll@@get_shop_releate_list", array($shop_info, $property_detail, $this->shop_type), $related_list);
最终走BLL:
    Bll_ShopDetailBll::get_shop_releate_list()
        <1>.首先查表jinpu_db.hu_guanlian_jp_prop,查询条件sid=$house_id,type=$dw_type
        <2>.上面表里查不到数据，再查询jinpu_db.hu_guanlian_jp_prop_new
        <3>.获取sid1的array
        <4>.去jinpu_db.i_house表批量获取数据
        <5>.pack data
```


