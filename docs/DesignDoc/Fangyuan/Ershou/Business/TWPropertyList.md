# TW二手房列表页白皮书

## 页面url

```
http://m.anjuke.com/{pinyin}/sale/
```
    
### 页面模块列表
 
* 1 筛选项
* 2 房源列表
* 3 seo

### 数据库和表:
|序号|数据库|表名称|作用|读写|是否独有|访问量|
| ---|--- |  --- | --- | --- | --- | --- |
|1|anjuke_db|ajk_commtype|区域/板块|读|否| 2813739 * 80%= 23w|
|1|anjuke_db|ajk_saleprice|售价|读|否| 2813739 * 80%= 23w|
|1|anjuke_db|ajk_arearange|面积|读|否| 2813739 * 80%= 23w|
|1|anjuke_db|ajk_housemodel|房型|读|否| 2813739 * 80%= 23w|
|1|anjuke_db|ajk_commtype|房屋类型|读|否| 2813739 * 80%= 23w|
|2|user_prop_sh_db|broker_property_{city_id}|用户端上海二手房房源表|读|是|2813739 * 80%= 23w |
|2|user_prop_bj_db|broker_property_{city_id}|用户端北京二手房房源表|读|是|2813739 * 80%= 23w |
|2|user_prop_s0{city_id%3}_db|broker_property_{city_id}|用户端二手房房源表|读|是| 2813739 * 80%= 23w|
|2|user_prop_s0{city_id%3}_db|wuba_property_{city_id}|用户端二手房房源表|读|否|2813739 * 80%= 23w |
|2|user_prop_s0{city_id%3}_db|wuba_property_image_{city_id}|用户端二手房58房源图片表|读|否| 2813739 * 80%= 23w|
|2|propertys_bj_db|ajk_propertys|北京二手房房源|读|否| 2813739 * 80%= 23w|
|2|propertys_sh_db|ajk_propertys|上海二手房房源|读|否| 2813739 * 80%= 23w|
|2|propertys_other_db|ajk_propertys|城市id小于42二手房房源|读|否|2813739 * 80%= 23w |
|2|propertys_db_04|ajk_propertys|城市id大于等于42二手房房源|读|否| 2813739 * 80%= 23w|
|2|anjuke_db|ajk_propertysale|房源信息表|读|否| 2813739 * 80%= 23w|
|2|user_prop_db|crawl_images|抓取房源图片表|读|否|2813739 * 80%= 23w |
|2|anjuke_db|ajk_pprops_attachments|个人房源信息表|读|否|2813739 * 80%= 23w |
|2|anjuke_db|ajk_attachments_comm|小区图片信息|读|否| 2813739 * 80% * 60= 1.2kw|
|2|anjuke_db|ajk_property_data|经纪人房源信息表|读|否|2813739 * 80% * 60= 1.2kw |