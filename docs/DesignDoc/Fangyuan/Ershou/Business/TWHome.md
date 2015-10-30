# tw首页白皮书

## 页面url

```
http://m.anjuke.com/{pinyin
```
    
### 页面模块列表

* 1 筛选项
* 2 热门二手房
* 3 房价走势
* 4 房产问答


### 配置
http://m.anjuke.com/cookieservice/favoritecount 获得收藏数量
http://m.anjuke.com/ajax/price/other/?city_id=11　房价排行榜
http://m.anjuke.com/sh/trendency/common/?city_id=11&nearby=6　房价趋势

### 数据库和表:
|序号|数据库|地址|表名称|作用|读写|是否独有|
|--- | --- | --- | --- | --- | --- | --- |
|2|user_prop_sh_db||broker_property_{city_id}|用户端上海二手房房源表|读|是
|2|user_prop_bj_db||broker_property_{city_id}|用户端北京二手房房源表|读|否|
|2|user_prop_s0{city_id%3}_db||broker_property_{city_id}|用户端二手房房源表|读|否|
|2|user_prop_s0{city_id%3}_db||wuba_property_{city_id}|用户端二手房房源表|读|否|
|2|user_prop_s0{city_id%3}_db||wuba_property_image_{city_id}|用户端二手房58房源图片表|读|否|
|2|anjuke_db||ajk_swsale|抓取房源表|读|否|
|2|anjuke_db||ajk_swrent|搜屋的出租房源|读|是|
|2|ajk_dw_stats||hu_dingcai_broker_score|租房小区排行|读|否|
|2|anjuke_db||ajk_wuba_commtype_relation|58安居客虚拟小区区域板块(区域商圈)对照表|读|否|
|2|anjuke_db||ajk_fitmenttype|装修类型映射|读|否|
|2|anjuke_db||ajk_usetype|房屋类型配置|读|否|
|2|anjuke_db||ajk_communitys|小区表|读|否|
|2|anjuke_db||ajk_commtype|区域板块表|读|否|
|2|anjuke_db||ajk_attachments_comm|小区信息|读|否|
|1|anjuke_db|ajk_commtype|区域/板块|读|否|
|1|anjuke_db|ajk_saleprice|售价|读|否|
|1|anjuke_db|ajk_housemodel|房型|读|否|
