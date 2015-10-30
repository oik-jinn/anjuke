## 商铺扣费发送流程

#### Step 1

* 请求移动api获取房源数据和click参数

```
GET http://api.anjuke.com/jinpu/1.1/shop/15012360?nosig=1&from_module=touch_web&isauction=2&macid=73580990-DB05-22DA-215C-21612AC7D907
```


* 通常的返回如下

```
{
    "status": "ok",
    "detail": {
        "house_id": "15012360",
        "isauction": "1",
        "title": "陕西南路一楼沿街餐饮门面急售现做“连锁甜品”年租金11万！",
        "trade_type": "sale",
        "unit_price": "19085",
        "unit_price_unit": "元/平米",
        "total_price": "68",
        "total_price_unit": "万元",
        "property_price": "2.5",
        "property_price_unit": "元/平米•月",
        "area_num": "35.63",
        "area_name": "徐汇",
        "block_name": "淮海西路",
        "management": "陕西南路",
        "address": "陕西南路",
        "ptype": "临街商铺",
        "status": "该商铺当前营业中,为<b>餐饮美食</b>店",
        "floor": "1 层",
        "description": "中国房地产行业龙头老大 投资请认准品牌 诚信+专业+热情=保障您快乐安全投资 24小时服务热线：136-2165-0025 中原地产商铺部资深客户经理;龙翔宇 友情提醒：投资，请找专业，找诚信，找品牌，为您节省宝贵时间和提高您的投资成功率！每日更新真实铺源24小时热情服务全程陪同实地看铺 ★推荐理由★ 1.【在售商铺】 商铺信息：房东是上海本地人，确实因公司周转资金，非常诚意急抛一套二手带租约现铺，该铺位于徐汇区，陕西南路靠近淮海中路，纯一楼沿街旺铺，属于上海市中心商圈，随着日益繁华的上海，上海市区商铺将会一铺难求，紧临内环高架，是徐汇区人流最密集的区域，商业异常繁华，产证面积：35.63平米，面宽5.5米，净深6米，层高5.2米，可做2层，目前租给了连锁知名餐饮兰州拉面馆，周边租金保持在8-20元/每平米/每天，租金每年11万（有租赁合同和租赁**证明），房东报价68万，可贷款，首付5成，独立产权证，诚信租客，房东承诺不跳价，不违约，中原地产大品牌为您提供最便捷的交易，保障您顺利安全的收租金，看中可直接签与房东签定买卖合同，即买即收租金，市中心繁华地段独一无二的绝版旺铺！ 上海中原地产诚信为您做到： 1、全程可享受商业地产私人法律顾问专业服务。 2、规范操作，银行监管账户，资金安全有保障。 3、商业地产免费咨询挂盘，专业VIP满意服务。 友情提示：不限户口，不限购，不限贷！ 金牌置业顾问 诚信置业顾问 5星级置业顾问 上海中原地产金牌商铺部经理龙翔宇带着从业5年多的投资经验和执作的专业精神为您投资保驾护航，期待您的来电：136-2165-0025（24小时实地陪同看铺）祝您及家人生活愉快，工作顺利，事事如意！",
        "plane_pic": "http://pic1.ajkimg.com/m/f875ee9b1181d6ecab9e1733cf627836/600x600c.jpg",
        "lat": "31.248139",
        "lng": "121.513278",
        "broker": {
            "type": "default",
            "name": "龙翔宇",
            "photo": "http://pic1.ajkimg.com/display/anjuke/f47d405ea408d529709a35b9e1f80ee7/360x479x148x62/100x133.jpg",
            "company": "中原地产",
            "phone": "13621650025",
            "chat_is_active": "1",
            "chat_info": {
                "chatId": "2000367734",
                "twoCodeIcon": "http://pic1.ajkimg.com/m/8e6b4bccc4da878dccb900721567b21a/400x300.jpg"
            }
        },
        "photos": [
            "http://pic1.ajkimg.com/m/865a31c108f15a80bdb15a2f1365371c/600x600c.jpg",
            "http://pic1.ajkimg.com/m/ef2e9ce11aa298ab3b4d9898e6f46e29/600x600c.jpg",
            "http://pic1.ajkimg.com/m/0447b93216362c0efbe806c2535806e4/600x600c.jpg",
            "http://pic1.ajkimg.com/m/1d7097f2322380ef67f7fdf1a7ace683/600x600c.jpg",
            "http://pic1.ajkimg.com/m/001d58040aa2e1efc5e303fe0e50986e/600x600c.jpg",
            "http://pic1.ajkimg.com/m/0200996a7c1dd0b8b042716be80fe23b/600x600c.jpg",
            "http://pic1.ajkimg.com/m/59dab9c36f5ea097b83e0e19ba55d802/600x600c.jpg",
            "http://pic1.ajkimg.com/m/f875ee9b1181d6ecab9e1733cf627836/600x600c.jpg"
        ]
    },
    "click": "%7B%22page_name%22%3A%22Mobile_Shop_Detail%22%2C%22url%22%3A%22api.anjuke.com%5C%2Fjinpu%5C%2F1.1%5C%2Fshop%5C%2F%22%2C%22user_id%22%3A0%2C%22city_id%22%3A%2211%22%2C%22broker_id%22%3A%22905806%22%2C%22house_id%22%3A%2215012360%22%2C%22property_id%22%3A%22113370%22%2C%22block_id%22%3A%2245%22%2C%22district_id%22%3A%223%22%2C%22pt%22%3A2%2C%22macid%22%3A%2273580990-DB05-22DA-215C-21612AC7D907%22%2C%22trade_type%22%3A5%2C%22entry%22%3A101%2C%22spread_id%22%3A%22545910%22%2C%22client_ip%22%3A%22180.166.126.94%22%7D"
}
```

* 上例中click参数urldecode结果如下

```
{
    "page_name": "Mobile_Shop_Detail",
    "url": "api.anjuke.com/jinpu/1.1/shop/",
    "user_id": 0,
    "city_id": "11",
    "broker_id": "905806",
    "house_id": "15012360",
    "property_id": "113370",
    "block_id": "45",
    "district_id": "3",
    "pt": 2,
    "macid": "73580990-DB05-22DA-215C-21612AC7D907",
    "trade_type": 5,
    "entry": 101,
    "spread_id": "545910",
    "client_ip": "180.166.126.94"
}
```


#### Step 2

* 解析click参数，并POST传递给移动api扣费

```
POST http://api.anjuke.com/jinpu/1.1/click/shop/
```
