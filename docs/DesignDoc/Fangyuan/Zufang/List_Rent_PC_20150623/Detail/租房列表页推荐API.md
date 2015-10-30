## 租房列表页`猜你喜欢`推荐API

### `猜你喜欢`推荐
```
根据用户的guid推荐数据，由BI提供最原始的防御ID,额外字段需要自己封装
```

#### BI API

* URL

    http://xapp20-076.i.ajkdns.com:8080/rent-pc-pro-history/recommend/

* 入参

| param | desc |
| ---   | ---  |
|city_id | 城市id |
|guid | 用户的guid |
|limit | 数据条数 |

#### `猜你喜欢`API


* URL

```
线上
    http://sh.zu.anjuke.com/v3/ajax/listrecommend/?city_id=11&prop_num=5&rec_type=list

本地
    http://sh.yongding.zu.dev.anjuke.com/v3/ajax/listrecommend/?city_id=11&prop_num=5&rec_type=list
```

* 入参

| param | desc | 必传参数 |
| ---   | ---  | --- |
|city_id | 城市id | yes |
|guid | 用户的guid | yes |
|limit | 数据条数 | no 默认5条 |
|rec_type| 推荐页面 | yes list:租房列表页|

* json result

```
{
    status: "ok",
    data: {
        guess_like: [
        {
            id: 888895735,
            title: "房源标题房源标题房源标题",
            type: 3,
            img: "http://b.pic1.ajkimg.com/display/hj/1bd0a62b76efb926ffbe8602565b45a5/240x180c.jpg",
            url: "http://sh.yongding.zu.dev.anjuke.com/fangyuan/888895735",
            room_num: 2,
            hall_num: 2,
            fitment_name: "普通装修",
            price: 1111,
            comm_name: "丽园路1弄",
            from: "home_rent_list_rec",
            is_hp: 0
        },
        {
            id: 888895396,
            title: "租房精选*房源5租房精选*房源5租房精选*房源5",
            type: 3,
            img: "http://b.pic1.ajkimg.com/display/hj/59125e9192568693f559320344be4a16/240x180c.jpg",
            url: "http://sh.yongding.zu.dev.anjuke.com/fangyuan/888895396",
            room_num: 2,
            hall_num: 2,
            fitment_name: "普通装修",
            price: 3100,
            comm_name: "好日子大家园D区",
            from: "home_rent_list_rec",
            is_hp: 1
        },
        {
            id: 888895397,
            title: "租房精选*房源6租房精选*房源6租房精选*房源",
            type: 3,
            img: "http://d.pic1.ajkimg.com/display/hj/34d8bcaad4a731199c2d130709fa3148/240x180c.jpg",
            url: "http://sh.yongding.zu.dev.anjuke.com/fangyuan/888895397",
            room_num: 3,
            hall_num: 2,
            fitment_name: "精装修",
            price: 4000,
            comm_name: "好日子大家园D区",
            from: "home_rent_list_rec",
            is_hp: 1
        },
        {
            id: 888895393,
            title: "租房精选*房源2租房精选*房源2租房精",
            type: 3,
            img: "http://b.pic1.ajkimg.com/display/hj/9c316292a0619fea874bd9b7c6c82334/240x180c.jpg",
            url: "http://sh.yongding.zu.dev.anjuke.com/fangyuan/888895393",
            room_num: 3,
            hall_num: 1,
            fitment_name: "普通装修",
            price: 3500,
            comm_name: "好日子大家园D区",
            from: "home_rent_list_rec",
            is_hp: 1
        },
        {
            id: 888895392,
            title: "租房精选*房源1租房精选*房源1租房精选*房源1",
            type: 3,
            img: "http://b.pic1.ajkimg.com/display/hj/9c316292a0619fea874bd9b7c6c82334/240x180c.jpg",
            url: "http://sh.yongding.zu.dev.anjuke.com/fangyuan/888895392",
            room_num: 2,
            hall_num: 1,
            fitment_name: "普通装修",
            price: 3200,
            comm_name: "好日子大家园D区",
            from: "home_rent_list_rec",
            is_hp: 1
        }
    ]
    }
}
```

* result字段说明

|字段|含义|描述|
|---|---|---|
|id|房源id|租房房源id|
|title|房源标题|不做任何截取|
|type|租房房源类型|~~|
|img|房源默认图片|~~|
|url|房源url|不带query string|
|room_num|房间数量|~~|
|hall_num|大厅数量|~~|
|fitment_name|装修描述|~~|
|price|月租价格|单位元|
|comm_name|小区名称|~~|
|from|加码|~~|
|is_hp|是否是精选| 1:精选  0:套餐|
|user_id|经纪人id| ~~ |
|community_id|小区id| 没有小区id为空 |
