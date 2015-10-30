# 小区单页白皮书

## 页面url

```
http://{pinyin}.anjuke.com/community/view/27
```
    
### 页面模块列表

* 1 小区概况
    * 1.1 小区概况
    * 1.2 二手房房价
    * 1.3 房产知识
* 2 二手房
    * 2.1 二手房列表
    * 2.2 筛选项
* 3 租房
* 4 价格行情
* 5 房型图
* 6 实景图 走api
* 7 生活配套
* 8 小区问答

### 配置

生活配套
http://{pinyin}.anjuke.com/v3/ajax/nearby/?commid=559&cityid=11

房价趋势
http://{pinyin}.anjuke.com/v3/ajax/prop/pricetrend/?commid=559

您可能感兴趣的小区
http://{pinyin}.anjuke.com/ajax/communityext/

获取小区图片api
api_community_image_url
http://java-api.a.ajkdns.com/service-internal/rest/communities/getCommCategory?json

app-community-core/config/label.php
community_label_const

### 数据库和表:
|序号|数据库|表名称|作用|读写|是否独有|使用量|
| ---|--- |  --- | --- | --- | --- | --- |
|1.1|anjuke_db|ajk_communitys|小区表|读|否| 1486190*89%*5% = 6w|
|1.1|anjuke_db|ajk_commtype|区域板块表|读|否|1486190*89%*5% = 6w|
|1.1|anjuke_db|ajk_attachments_comm|小区信息|读|否|1486190*89%*5% = 6w|
|1.1|anjuke_db|map_communities_baidu|小区百度地图信息|读|否|1486190*89%*5% = 6w|
|1.1|anjuke_db|map_communities_soso_pano|小区街景|读|否|1486190*89%*5% = 6w|
|1.1|user_prop_db|ajk_nearby_score|小区周边得分|读|否|1486190*89%*5% = 6w|
|1.1|user_prop_db|commPlaceRelationDao|二手房小区周边对应关系|读|否|1486190*89%*5% = 6w|
|1.1|anjuke_db|map_communities_soso_pano|小区街景|读|否|1486190*89%*5% = 6w|
|2.2|anjuke_db|ajk_housemodel|房型|读|否|1486190*89%*5% = 6w|
|2.2|anjuke_db|ajk_saleprice|售价|读|否|1486190*89%*5% = 6w|
|2.1|user_prop_sh_db|broker_property_{city_id}|用户端上海二手房房源表|读|是|1486190*89%*5% = 6w|
|2.1|user_prop_bj_db|broker_property_{city_id}|用户端北京二手房房源表|读|是|1486190*89%*5% = 6w|
|2.1|user_prop_s0{city_id%3}_db|broker_property_{city_id}|用户端二手房房源表|读|是|1486190*89%*5% = 6w|
|2.1|user_prop_s0{city_id%3}_db|wuba_property_{city_id}|用户端二手房房源表|读|否|1486190*89%*5% = 6w|
|2.1|user_prop_s0{city_id%3}_db|wuba_property_image_{city_id}|用户端二手房58房源图片表|读|否|1486190*89%*5% = 6w|
|2.1|propertys_bj_db|ajk_propertys|北京二手房房源|读|否|1486190*89%*5% = 6w|
|2.1|propertys_sh_db|ajk_propertys|上海二手房房源|读|否|1486190*89%*5% = 6w|
|2.1|propertys_other_db|ajk_propertys|城市id小于42二手房房源|读|否|1486190*89%*5% = 6w|
|2.1|propertys_db_04|ajk_propertys|城市id大于等于42二手房房源|读|否|1486190*89%*5% = 6w|
|2.1|anjuke_db|ajk_propertysale|房源信息表|读|否|1486190*89%*5% = 6w|
|2.1|user_prop_db|crawl_images|抓取房源图片表|读|否|1486190*89%*5% = 6w|
|2.1|anjuke_db|ajk_pprops_attachments|个人房源信息表|读|否|1486190*89%*5% = 6w|
|2.1|anjuke_db|ajk_attachments_comm|小区图片信息|读|否|1486190*89%*5% = 6w|
|2.1|anjuke_db|ajk_property_data|经纪人房源信息表|读|否|1486190*89%*5% = 6w|
|2.1|image_db|t_attachment_{propid%200}|房源的所有图片|读|否|1486190*89%*5% = 6w|
|2.1|anjuke_db|ajk_communitys|小区表|读|否|1486190*89%*5% = 6w|
|2.1|anjuke_db|frm_communitys|小区图片|读|否|1486190*89%*5% = 6w|
|4|ajk_dw_stats|dw_pricing_comm_quartile|小区均价|读|否|1486190*89%*5% = 6w|
|4|ajk_dw_stats|price_trend_comm_monthly|小区月均价|读|否|1486190*89%*5% = 6w|
|4|ajk_dw_stats|price_trend_comm_enabled|小区白名单|读|否|1486190*89%*5% = 6w|
|4|ajk_dw_stats|hu_guanlian_xq_all_sale|感兴趣的小区|读|否|1486190*89%*5% = 6w|
|1.1|user_prop_db|community_label_relation|二手房小区标签|读|否|1486190*89%*5% = 6w|
|5. 6|image_ext_db|community_housemodel|小区户型图|读|否|1486190*89%*5% = 6w|
|2.2|anjuke_db|ajk_saleprice|售价|读|否|1486190*89%*5% = 6w|
|2.2|anjuke_db|ajk_housemodel|房型|读|否|1486190*89%*5% = 6w|
















