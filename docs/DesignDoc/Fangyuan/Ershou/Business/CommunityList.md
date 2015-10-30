# 小区列表页白皮书

## 页面url

```
http://{pinyin}.anjuke.com/community/
```

### 配置文件

app-anjuke/config/multicity.php
price_trends
community_tese

### 依赖的外部URL
|序号|配置名|URL|功能|其它|
| ---| --- | --- | --- | --- |
|6||http://{pinyin}.anjuke.com/rec/sale/profilerec/|根据历史浏览|
|4||http://{pinyin}.anjuke.com/ajax/aifang/tuangou/api|新盘团购|
||lll_seo_partners_aifang_api|http://service.kfs.a.ajkdns.com/app-loupan/rest/FriendLinkApi/getFriendLink|友链

### 页面模块列表

* 1 筛选项
* 2 小区列表
* 3 看过的小区
* 4 新盘团购
* 5 热销新盘
* 6 最近浏览推荐
* 7 您感兴趣的新房
* 8 seo 

### 数据库和表:
|序号|数据库|表名称|作用|读写|是否独有|访问量|
| ---|--- |  --- | --- | --- | --- | --- |
|2 |anjuke_db|community_sale_rent_count|小区房源数|读|否| 1486190*89%*10% = 12w|
|2 |ajk_dw_stats|price_trend_comm_monthly|小区均价|读|否| 1486190*89%*10% = 12w|
|2 |anjuke_db|ajk_communitys|小区表|读|否|1486190*89%*10%= 12w|
|1|anjuke_db|ajk_commtype|区域/板块|读|否| 1486190*89%*10%10 = 12w|


