## app小区单页白皮书

### 1、页面URL
```
打开安居客app ---> 点击二手房或者租房频道 ---> 点击列表页中的一套房源进入房源单页 ---> 在房源单页点击小区详情进入小区页面
```

### 2、页面说明
小区单页主要有以下接口：

* 2.1、小区信息(基本信息和扩展信息)

    ```
    小区基本信息
    小区扩展信息
    小区均价趋势信息
    小区地图信息
    小区户型图信息
    小区房源数量信息
    小区周边信息
    小区地铁信息
    小区街景信息
    ```
* 2.2、小区信息(房价信息等)

    ```
    小区单页信息
    小区浏览量
    小区关注量
    小区3年均价
    区域3年均价
    ```
* 2.3、小区下拉词匹配
* 2.4、地图找房小区列表

### 3、页面模块列表
|序号|URL|仓库|负责人|功能|
| --- |--- | --- | --- | --- | --- |
|2.1|http://api.anjuke.com/anjuke/4.0/community/info|anjuke-mobile-api|晓路|小区信息(基本信息和扩展信息)|
|2.2|http://api.anjuke.com/anjuke/4.0/comm/get|anjuke-mobile-api|晓路|小区信息(房价信息等)|
|2.3|http://api.anjuke.com/mobile/1.3/community.autoComplete|anjuke-mobile-api|晓路|小区下拉词匹配|
|2.4|http://api.anjuke.com/mobile/1.3/community.searchMap|anjuke-mobile-api|晓路|地图找房小区列表|

### 4、依赖的内部服务或config(指的是其它仓库的服务)
|序号|服务名|仓库地址|负责人(或部门)|功能|配置列表|内层/外层|
|---| --- | --- | --- | --- | --- | --- | --- |
|2.1|image.php|anjuke-mobile-api|晓路|价格趋势url地址|内层|anjuke_trend_new|
||common.php|anjuke-mobile-api|晓路|小区街景图片等|外层|ajk_image_display_url、street_info_url|
|2.2|/data1/mobile-log/view.log|anjuke-mobile-api|晓路|小区浏览添加失败记录|
|2.4|city.php|anjuke-mobile-api|晓路|坐标偏移量|baidu_offset|内层|
||common.php|anjuke-mobile-api|晓路|搜索最小距离|min_distance|内层|
|||||房源查询|anjuke_search_url_new|外层|

### 5、依赖的外部URL
|序号|配置名|URL|功能|其它|
|---| --- | --- | --- | --- |
|2.1|get_community_prop_from_site|http://www.anjuke.com/api/commpropnums/|获取小区下的房源数量|外层|
||sv_qq_map|http://sv.map.qq.com/xf|腾讯街景|外层|
||apis_qq_map|http://apis.map.qq.com/ws/streetview/v1/image|获取街景图片|内层|
||ajk_image_upload_url|http://upd1.ajkimg.com/upload|图片上传服务|内层|
|2.2|commonty_get_url|http://www.anjuke.com/api/comm/pageinfo|获取小区单页信息|外层|
|2.3|solrCommunityServer|http://sc10-001.a.ajkdns.com:8983/community35/|小区关键字搜索|外层|
|2.4|anjuke_search_url_new|http://shanghai.anjuke.com/api/newsearch/|二手房搜索|外层|
||solrCommunityServer|http://sc10-001.a.ajkdns.com:8983/community35/|小区关键字搜索|外层|
    
### 6、数据库和表
|序号|数据库|表名称|作用|读写|是否独有|
|---| --- | --- | --- | --- | --- |
|2.1|anjuke_db|ajk_communitys|小区基本信息|读|否|
|||ajk_commextend|小区扩展信息|读|否|
|||ajk_usetype|会员身份字典表|读|否|
|||map_communities_baidu|小区百度坐标|读|否|
|||ajk_attachments_comm|小区图片|读|否|
|||ajk_local_bind_comm|小区周边配置|读|否|
|||ajk_locations|获取小区周边配置|读|否|
|||ajk_school_comm|获取小区学校信息|读|否|
|||sw_metro_stations|地铁信息|读|否|
|||sw_metros|地铁信息|读|否|
|||sw_metro_community_distances|获取小区地铁信息|读|否|
|||ajk_street_image|街景图片信息|读写|是|
||community_db|community_info|小区信息表|读|否|
||ajk_dw_stats|dw_pricing_comm_quartile|小区日均价信息|读|否|
|||dw_pricing_comm_weekly|小区周均价信息|读|否|
|||price_trend_comm_monthly|小区月均价信息|读|否|
|2.2|mobile_api_db|user_view|物品的浏览信息|读写|是||
|||user_focus|物品关注量|读|是|
|||fix_comm_count|小区修正浏览量|读写删|是|
||ajk_dw_stats|comm_view_count|小区浏览量|读|否|
|||price_trend_comm_monthly|小区均价|读|否|
|||price_trend_areacode_monthly|区域板块均价|读|否|

### 7、Memcache&Redis
|名称|类型|地址|申请大小|使用率|是否统一管理|功能|负责人|key|
|--- | --- | --- | --- | --- | --- | --- | --- | --- |

### 8、关联job
|job名称|job管理地址|功能|负责人|
|--- | --- | --- | --- |