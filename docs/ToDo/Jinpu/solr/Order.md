# 商业地产列表页solr排序整理

```
solr排序是为了更好的推广经纪人花钱买的房源，并且按照合理的顺序展示所有房源
```

## 写字楼出租、出售列表页solr排序

### 1. 筛选了非`默认排序`的其他排序

```
按照正常的筛选排序规则来排序
```

### 2. 默认排序

+ 精选

```
不排序
```

    + 套餐

        . 非搜索

            ```
            stage asc, (目前范围0-2，58房源定为99，充当排序的大段)
            score asc, (这个本来应该是random_score)
            sub_stage asc,
            final_score desc
            ```

        . 搜索

            ```
            stage asc, (目前范围0-2，58房源定为99，充当排序的大段)
            score desc, (本来应该是random_score)
            _val_: map(sub_stage,0,' . $sub_stage_i . ',99)','asc', 【dw_status.hu_rank_jp_all_score_type】
            final_score desc
            ```

------------------------------------

## 商铺出租、出售列表页排序

```
+ 套餐

        . 非搜索

            ```
            stage asc, (目前范围0-2，58房源定为99，充当排序的大段)
            score asc,
            sub_stage asc,
            final_score desc
            ```

        . 搜索

            ```
            stage asc, (目前范围0-2，58房源定为99，充当排序的大段)
            score desc,
            _val_: map(sub_stage,0,' . $sub_stage_i . ',99)','asc', 【dw_status.hu_rank_jp_all_score_type】
            final_score desc
            ```
```