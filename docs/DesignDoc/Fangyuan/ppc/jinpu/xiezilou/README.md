## 写字楼扣费发送流程

#### Step 1

* 请求移动api获取房源数据和click参数

```
GET http://api.anjuke.com/jinpu/1.1/office/15023178?nosig=1&from_module=touch_web&isauction=2&macid=73580990-DB05-22DA-215C-21612AC7D907
```


* 通常的返回如下

```
{
    "status": "ok",
    "detail": {
        "house_id": "15023178",
        "title": "地铁浦电路《创业精装2-8人办公》 拎包办公，全包全配",
        "trade_type": "rent",
        "unit_price": "4.2",
        "unit_price_unit": "元/平米•天",
        "total_price": "2044",
        "total_price_unit": "元/月",
        "property_price": "6",
        "property_price_unit": "元/平米•月",
        "area_num": "16",
        "area_name": "浦东",
        "block_name": "北蔡/塘桥",
        "address": "峨山路77号",
        "ptype": "乙级写字楼",
        "floor": "中层",
        "place": "金牛大厦",
        "description": "【本案优势推荐】 刁小姐：153-0051-0552 当天满意立即免费体验 立即开始办公无需装修、无佣金、全包全配办公 小微企业2-8人服务式小型办公室 全包全配拎包即租 一站式办公，注册公司财务记账 办理一般纳税人核定税种 上海8家连锁分店，浦东陆家嘴软件园、浦东-世纪公园、 张江高科-科苑路、张江药谷-张衡路、黄埔-科技京城、人民广场、长宁-华敏翰尊国际、杨浦-五角场万达广场、 周边配套 如家连锁酒店 汉庭连锁酒店 世纪联华 星巴克 罗森 全家软件园休闲美食广场（羽毛球 乒乓球 篮球场） 中国银行、*、建设银行、浦东发展银行、宁波通商银行、上海银行、交通银行 联系人：刁小姐（2814339749）-微信 电话：153-0051-0552 热线：021-60476300 如果您在晚上12点钟的时候看到我的广告，请拨打我的电话，因为我还在工作。 ",
        "plane_pic": "http://pic1.ajkimg.com/m/cb7eb736465942747fea3e43aaeb96af/600x600c.jpg",
        "lat": "31.220833",
        "lng": "121.539944",
        "broker": {
            "type": "default",
            "name": "刁凤茹",
            "photo": "http://pic1.ajkimg.com/display/anjuke/d0f2278e4ba21fafe720e886e9577f07/348x463x0x47/100x133.jpg",
            "company": "第一际房产有秘书的办公室",
            "phone": "15300510552",
            "chat_is_active": "0",
            "chat_info": {
                "chatId": "2000667308",
                "twoCodeIcon": "http://pic1.ajkimg.com/m/dca1d8575d8c4372a7a6786c24be083c/400x300.jpg"
            }
        },
        "photos": [
            "http://pic1.ajkimg.com/m/f3030216d2469c51a719da7f4b7c4f6b/600x600c.jpg",
            "http://pic1.ajkimg.com/m/13d0adf64c305cb302f8c88afea948de/600x600c.jpg",
            "http://pic1.ajkimg.com/m/4e3cbf3cc3f82bb1df723adc4c1f5329/600x600c.jpg",
            "http://pic1.ajkimg.com/m/36f375f106099a4d84a618d610a79e39/600x600c.jpg",
            "http://pic1.ajkimg.com/m/5872edd110c2fdb4b371c69a82b9bccb/600x600c.jpg",
            "http://pic1.ajkimg.com/m/3bddd2e2ac102d93d6fc58840b31ecb7/600x600c.jpg",
            "http://pic1.ajkimg.com/m/cb7eb736465942747fea3e43aaeb96af/600x600c.jpg",
            "http://pic1.ajkimg.com/m/53d628268f8726353bdc48bcf0493148/600x600c.jpg"
        ]
    },
    "click": "%7B%22page_name%22%3A%22Mobile_Office_Detail%22%2C%22url%22%3A%22api.anjuke.com%5C%2Fjinpu%5C%2F1.1%5C%2Foffice%5C%2F%22%2C%22user_id%22%3A0%2C%22city_id%22%3A%2211%22%2C%22broker_id%22%3A%22959294%22%2C%22house_id%22%3A%2215023178%22%2C%22property_id%22%3A%2211553%22%2C%22block_id%22%3A%225%22%2C%22district_id%22%3A%221%22%2C%22pt%22%3A2%2C%22macid%22%3A%2273580990-DB05-22DA-215C-21612AC7D907%22%2C%22trade_type%22%3A6%2C%22entry%22%3A100%2C%22spread_id%22%3A%22538411%22%2C%22client_ip%22%3A%22180.166.126.94%22%7D"
}
```

* 上例中click参数urldecode结果如下

```
{
    "page_name": "Mobile_Office_Detail",
    "url": "api.anjuke.com/jinpu/1.1/office/",
    "user_id": 0,
    "city_id": "11",
    "broker_id": "959294",
    "house_id": "15023178",
    "property_id": "11553",
    "block_id": "5",
    "district_id": "1",
    "pt": 2,
    "macid": "73580990-DB05-22DA-215C-21612AC7D907",
    "trade_type": 6,
    "entry": 100,
    "spread_id": "538411",
    "client_ip": "180.166.126.94"
}
```


#### Step 2

* 解析click参数，并POST传递给移动api扣费

```
POST http://api.anjuke.com/jinpu/1.1/click/office/
```
