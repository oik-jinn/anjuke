# 58内嵌tw单页＆经纪人微店分享单页推荐

## 看了又看

### 请求url

/ajax/ershou/appviewrecommend/

### 请求参数

city_id  城市id
prop_id  房源id
type     房源类型
entry    页面的entry值（房源单页为ershouappviewrec,经纪人店铺页面为brokershop）

### 返回结果

```
{
    "status": "ok",
    "interest_props": [
        {
            "prop_id": "168949572",   房源ｉｄ
            "title": "好房子快去抢",　　房源标题
            "source_type": "1",　　　　房源类型
            "default_image": "http://b.pic1.ajkimg.com/display/anjuke/102751451ba10cb3f6bd889329158375/240x180c.jpg",　　默认图
            "prop_url": "http://m.qigao-h213.dev.anjuke.com/sh/sale/A168949572/?isauction=0",　房源ｕｒl
            "room_num": "2",  室
            "hall_num": "1",　　厅
            "prop_price": 330,　　价格
            "comm_name": "培育苑",    小区名
            "area_num": 200,　　　面积
            "area_name": "浦东",　　　区域
            "block_name": "花木",　　　板块
            "broker_id": "41",　　经纪人id
            "comm_id": "8",    小区ｉd
            "hp_type": 1,
            "entry": 88,      发ｓｏj用的entry
            "soj": "&position=1"
        },
       
    ]
}

```

## 附近房源

### 请求url

/ajax/ershou/appviewnearby/

### 请求参数

city_id 城市id
prop_id 房源id
type    房源类型
entry   页面的entry值（房源单页为ershouappviewrec,经纪人店铺页面为brokershop）

### 返回结果

```

{
    "status": "ok",
     "interest_props": [
        {
            "prop_id": "168949572",   房源ｉｄ
            "title": "好房子快去抢",　　房源标题
            "source_type": "1",　　　　房源类型
            "default_image": "http://b.pic1.ajkimg.com/display/anjuke/102751451ba10cb3f6bd889329158375/240x180c.jpg",　　默认图
            "prop_url": "http://m.qigao-h213.dev.anjuke.com/sh/sale/A168949572/?isauction=0",　房源ｕｒl
            "room_num": "2",  室
            "hall_num": "1",　　厅
            "prop_price": 330,　　价格
            "comm_name": "培育苑",    小区名
            "area_num": 200,　　　面积
            "area_name": "浦东",　　　区域
            "block_name": "花木",　　　板块
            "broker_id": "41",　　经纪人id
            "comm_id": "8",    小区ｉd
            "hp_type": 1,
            "entry": 88,      发ｓｏj用的entry
            "soj": "&position=1"
        },
       
    ]
}

```





