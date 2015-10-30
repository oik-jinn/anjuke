### 房贷计算器页面和二手房列表页零少结果　的推荐接口：猜你喜欢 接口文档

 
* ajax请求url

    ```
    
    /v3/ajax/calculator/
    
    ```
* ajax 请求参数

    ```
    
    guid:       用户唯一标识
    city_id:    城市id
    entry:      页面entry (二手房列表页推荐可以传list）
    type:   　　推荐的类型（房贷计算的推荐不传值，二手房列表页零少结果传list）
    
    ```
* 样例

http://shanghai.pmt-27737-site.anjuke.test/ajax/calculator/?guid=46FBB22C-FA16-FA90-96BF-2E4AEDA60960&city_id=11
    
* 接口的返回格式

```

    {
    "status": "ok",
    "interest_props": [
        {
            "prop_id": "168949575",
            "title": "好房子快去抢为",
            "source_type": "1",
            "default_image": "http://c.pic1.ajkimg.com/display/anjuke/21e4fcee74ed48440337c2a310999637/240x180c.jpg",
            "prop_url": "http://shanghai.app-ershou-web.qigao-h213.dev.anjuke.com/prop/view/A168949575",
            "room_num": "2",
            "hall_num": "2",
            "prop_price": 123,
            "comm_name": "东明苑",
            "area_num": 200,
            "broker_id": "41",
            "comm_id": "1588",
            "comm_local": "东明路383弄 ",
            "area_name": "浦东",
            "block_name": "南码头",
            "broker_name": "徐律",
            "soj": "&mahout=2&spread=proprec_p&position=1",
            "hp_type": 1
        },
      
    ],
    "soj_data_interest": {
        "v": "2.0",
        "propParam": "268466774|7790088|5902|2,268466614|7790059|82254|1,268466466|36139|1786|2,169439710|856596|440550|1,168949575|41|1588|1",
        "hp": 0,
        "recomm_data_type": 1,
        "recomm_type": 3,
        "entry": 2
    },
    "request_time": 4.214066028595
}

```
* 返回结果说明:

	|Field_Name|Field_Type|Description|
	| -------- | -------- | --------- |
	|status    |          |           |
	|interest_props|array|猜你喜欢房源数据|
	|soj_data_interest||
	|request_time||