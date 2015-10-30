# tw首页改版文档

## 热门筛选项

```

* 数据 （Biz_Ershou_Sale_HotFilterBiz的 getHotFilter($city_id，$is_area = 1, $is_price = 1, $is_room = 1)方法）

```
Field_Name | Field_Type | Default | Description
--- | --- | --- | ---
city_id | int | NULL | 城市id
is_area | bool | 1 | 是否要区域筛选项
is_price | bool | 1 | 是否要价格筛选项
is_room | bool | 1 | 是否要房型筛选项


## 根据浏览历史推荐（二手房）

```
* 样例：
http://m.anjuke.com/ajax/ershou/homerecommend/?guid=B294B514-D65D-B633-F34E-TD6A06241501&city_id=11&__REQU_SESSION_ID=REF-4A6D9FFB-E562-4B9D-81E2-F519AF38E04
```
### 接口参数

Field_Name | Field_Type | Is_Null | Description
--- | --- | --- | ---
guid | | 必须 |  用户id
city_id | | 必须 | 城市id
__REQU_SESSION_ID | | 非必须 | 

### 返回结果

Field_Name | Field_Type | Is_Null | Description
--- | --- | --- | ---
prop_id | 
title | 
source_type | 
default_image | 
prop_url | 
room_num  | 
hall_num | 
prop_price | 
comm_name | 
area_num | 
broker_id | 
comm_id | 
area_name | 
block_name | 
tag | 


### 接口返回结果eg

```
{
    status: "ok",
    tw_home_recommend_prop: [{
         prop_id: "168993877",
         title: "房源标题,因标题太短设置为自动标题18376,序号623",
         source_type: "1",
         default_image: "http://b.pic1.ajkimg.com/display/anjuke/14512d2dd8b6fb131cfa173dad0b6639/100x75c.jpg",
         prop_url: "http://m.pmt-27507-tw.anjuke.test/sh/sale/A168993877/?isauction=0",
         room_num: "3",
         hall_num: "2",
         prop_price: 255.62,
         comm_name: "共和新路2203弄",
         area_num: 46,
         broker_id: "118452",
         comm_id: "18376",
         area_name: "闸北",
         block_name: "大宁绿地",
         tag: [ ]
       }]
    "request_time":0.22198510169983
}

```

## 房价图模块
 
```
* 样例：

     * 原来（java）http://m.anjuke.com/sh/trendency/common/?city_id=11&nearby=6&__REQU_SESSION_ID=REF-948AFCCF-65E8-47F6-BA46-C1D8E1F28170
     * php控制器（User_Touch_Ajax_PriceTrendController）

```
### 接口参数

Field_Name | Field_Type | Is_Null | Description
--- | --- | --- | ---
city_id | | 必须 |  城市id
nearby | | 必须 | 条数
__REQU_SESSION_ID | | 非必须 | 

### 返回结果

Field_Name | Field_Type | Is_Null | Description
--- | --- | --- | ---
mid_price | |  | 
month | |  | 


### 接口返回结果eg

```
{
    "status": "ok",
    "msg": "ok",
    "allcount": 0,
    "data": {
        "city": {
            "201502": {
                "mid_price": "31198",
                "month": 2
            }
        }
    }
}

```

## 房价走势模块
 
```
* 样例：
  * 原来（返回html） http://m.anjuke.com/ajax/price/trend/?city_id=11&__REQU_SESSION_ID=REF-948AFCCF-65E8-47F6-BA46-C1D8E1F28170
  * 新的（返回json）http://m.anjuke.com/ajax/price/other/?city_id=11&nearby=6&__REQU_SESSION_ID=REF-948AFCCF-65E8-47F6-BA46-C1D8E1F28170
```

### 接口参数

Field_Name | Field_Type | Is_Null | Description
--- | --- | --- | ---
city_id | | 必须 | 城市id
__REQU_SESSION_ID | | 非必须 | 

### 返回结果 

Field_Name | Field_Type | Is_Null | Description
--- | --- | --- | ---
area | |  | 
price | |  | 
rate | |  | 
range | |  | 
link | |  | 

### 接口返回结果eg

```
[
    {
        "area": "东丽",
        "price": "11002",
        "rate": 1,
        "range": "1.35%",
        "link": "http://m.anjuke.com/tj/trendency/2550/"
    },
  
]

```

## 底部seo


### 大家都在搜

```
* 随机取当前城市SEO频道页面10个

* url
http://m.anjuke.com/sh/esf/fang-shanghaidinglou-d111/

```

### 城市各区域二手房

```
* 取当前城市所有区域板块列表。

* url
http://m.anjuke.com/sh/sale/{区域拼音}/ 

```

### 城市二手房房价

```
* 取当前城市所有区域房价。

* url
http://m.anjuke.com/sh/trendency/11/ 

```

### 热门城市二手房

```

* 取固定10个城市二手房（北京、上海、广州、深圳、成都、重庆、南京、天津、杭州、苏州）

* url
http://m.anjuke.com/sh/sale/

```

### 热门房产网(新增)


```

* 内链逻辑， 每个E分组中城市，分别展示组内全部城市，详见（User_Touch_Seo_SeoGroup::getEsfInline()）

```

### 热门新房(新增)

```

* 内链逻辑，每27个城市，展示X{组号}组内链，详见(User_Touch_Seo_SeoGroup::getXinFangLink())

```
 
