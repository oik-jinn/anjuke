# 二手房列表页白皮书

## 页面url

```
http://{pinyin}.anjuke.com/sale/
```
    
### 页面模块列表
 
* 1 筛选项
* 2 房源列表
* 3 热门房产问答
* 4 房价趋势
* 5 新盘团购
* 6 您可能感兴趣的新房
* 7 零少结果推荐
* 8 根据您最近浏览推荐

### 依赖的外部URL
|序号|配置名|URL|功能|其它|
| ---| --- | --- | --- | --- |
|2|community_api_service_server|xapp20-053.i.ajkdns.com:8080|获取小区信息|
|7||http://{pinyin}.anjuke.com/v3/ajax/calculator/|零少结果推荐|
|7|loan_recommend|http://rcmd.a.ajkdns.com/sale-pc-pro-history/recommend|零少结果推荐bi接口|
|8||http://{pinyin}.anjuke.com/rec/sale/profilerec/|根据历史浏览|
|2|community_api_service_server|xapp20-053.i.ajkdns.com:8080|获取小区信息|
|5||http://{pinyin}.anjuke.com/ajax/aifang/tuangou/api|新盘团购|
|3||http://shanghai.anjuke.com/ajax/listingqa|热门房产问答|

### 配置

multicity/ui.php

metro_filter

open_school

community_service_usage

### 数据库和表:
|序号|数据库|表名称|作用|读写|是否独有|访问量|
| ---|--- |  --- | --- | --- | --- | --- |
|2 7 8|user_prop_sh_db|broker_property_{city_id}|用户端上海二手房房源表|读|是|980391 * 80%=80w |
|2 7 8|user_prop_bj_db|broker_property_{city_id}|用户端北京二手房房源表|读|是|980391 * 80%=80w |
|2 7 8|user_prop_s0{city_id%3}_db|broker_property_{city_id}|用户端二手房房源表|读|是| 980391 * 80%=80w|
|2 7 8|user_prop_s0{city_id%3}_db|wuba_property_{city_id}|用户端二手房房源表|读|否| 980391 * 80%=80w|
|2 7 8|user_prop_s0{city_id%3}_db|wuba_property_image_{city_id}|用户端二手房58房源图片表|读|否| 980391 * 80%=80w|
|2 7 8|propertys_bj_db|ajk_propertys|北京二手房房源|读|否|980391 * 80%=80w |
|2 7 8|propertys_sh_db|ajk_propertys|上海二手房房源|读|否|980391 * 80%=80w |
|2 7 8|propertys_other_db|ajk_propertys|城市id小于42二手房房源|读|否| 980391 * 80%=80w|
|2 7 8|propertys_db_04|ajk_propertys|城市id大于等于42二手房房源|读|否| 980391 * 80%=80w|
|2 7 8|anjuke_db|ajk_propertysale|房源信息表|读|否| 980391 * 80%=80w|
|2 7 8|user_prop_db|crawl_images|抓取房源图片表|读|否| 980391 * 80%=80w|
|2 7 8|anjuke_db|ajk_pprops_attachments|个人房源信息表|读|否|980391 * 80%=80w |
|2 7 8|anjuke_db|ajk_attachments_comm|小区图片信息|读|否|980391*80%*60=4kw |
|2 7 8|anjuke_db|ajk_property_data|经纪人房源信息表|读|否| 980391*80%*60=4kw|
|2 7 8|image_db|t_attachment_{propid%200}|房源的所有图片|读|否|980391*80%*60=4kw |
|2 7 8|anjuke_db|ajk_communitys|小区表|读|否|980391*80%*60=4kw |
|2 7 8|anjuke_db|ajk_commtype|区域板块表|读|否| 980391*80%*60=4kw|
|2 7 8|anjuke_db|ajk_attachments_comm|小区信息|读|否|980391*80%*60=4kw |
|2 7 8|user_prop_db|bus_relation|公交站|读|否| 980391*80%*60=4kw|
|2 7 8|user_prop_db|bus_line|公交线|读|否|980391*80%*60=4kw |
|2 7 8|user_prop_db|community_place_relation|小区周边信息|读|否| 980391*80%*60=4kw|
|2 7 8|anjuke_db|map_communities_baidu|小区百度地图信息|读|否| 980391*80%*60=4kw|
|2 7 8|anjuke_db|map_communities_soso_pano|小区街景|读|否|980391*80%*60=4kw |
|2 7 8|anjuke_db|ajk_brokerextend|经纪人信息|读|否| 980391*80%*60=4kw|
|2 7 8|ajk_dw_stats|da_mobile_chat_talented_broker_upload|经纪人微聊信息|读|否|980391*80%*60=4kw |
|2 7 8|broker_db|ad_set_sale|设置橱窗服务经纪人信息|读|否| 980391*80%*60=4kw|
|2 7 8|broker_db|ad_props_show_sale|经纪人主推房源信息表|读|否| 980391*80%*60=4kw|
|2 7 8|user_prop_db|upp_school_community|学区信息|读|否|980391*80%*60=4kw |
|2 7 8|user_prop_db|upp_schools|学校信息表|读|否| 980391*80%*60=4kw|
|2 7 8|user_prop_db|sw_metro_community_distances|小区周边地铁信息表|读|否| 980391*80%*60=4kw|
|2 7 8|anjuke_db|sw_metros|地铁信息表|读|否| 980391*80%*60=4kw|
|2 7 8|anjuke_db|sw_metro_stations|地铁站点|读|否|980391*80%*60=4kw |
|1|anjuke_db|ajk_commtype|区域/板块|读|否| 980391 * 80%=80w|
|1|anjuke_db|ajk_saleprice|售价|读|否| 980391 * 80%=80w|
|1|anjuke_db|ajk_arearange|面积|读|否| 980391 * 80%=80w|
|1|anjuke_db|ajk_housemodel|房型|读|否|980391 * 80%=80w |
|1|anjuke_db|ajk_commtype|房屋类型|读|否| 980391 * 80%=80w|
|1|anjuke_db|ajk_filter_company|经济公司|读|否| 980391 * 80%=80w|
|1|配置|house_age_default|建造年代|||
|1|配置|house_floor|楼层|||











