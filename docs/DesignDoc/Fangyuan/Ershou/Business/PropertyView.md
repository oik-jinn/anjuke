# pc二手房单页白皮书



## 页面url

```
http://{pinyin}.anjuke.com/prop/view/{proptype+propid}
```


    
### 页面模块列表

* 1 房源信息

* 2 经纪人信息

* 3 推荐位
　
 * 3.1 猜你喜欢推荐位
 * 3.2 同月供优质房推荐
 * 3.3 同地段优选推荐
 * 3.4 投资潜力房
 * 3.5 相似小区房源
 * 3.6 主推房源推荐

* 4 小区信息

* 5 生活配套

* 6 与该房源相似的新房推荐

* 7 参考月供

* 8 房价走势

* 9 房产知识

* 10 seo


### 配置

app-user-common/config/api.php
property_java_host

api_community_image_citys

app-user-common/config/multicity/ui.php
refer_payment

app-user-common/config/multicity/ui.php
open_map_search_house

房源java接口http://java-api.a.ajkdns.com:8080/service-ppc/rest/cache/propAllInfo

生活配套
shanghai.anjuke.com/v3/ajax/nearby/?commid=559&cityid=11

房价趋势
http://shanghai.anjuke.com/v3/ajax/prop/pricetrend/?commid=559

房产问答
http://shanghai.anjuke.com/api/fangyuanquestion/?comm_id=559&comm_name=%E6%B0%B8%E4%B8%9A%E5%85%AC%E5%AF%93%EF%BC%88%E4%B8%80%E8%87%B3%E4%BA%8C%E6%9C%9F%EF%BC%89

seo新房相关
http://api.fang.anjuke.com/m/iphone/1.3/loupan/list/?city_id=11&page_size=20


### 依赖的内部服务(指的是其它仓库的服务)

|服务名|仓库地址|负责人(或部门)|功能|
| --- | --- | --- | --- |
|Biz_Home_SeoBiz | user-site/app-biz|石兆媛|城市首页小区大全|

### 依赖的外部URL
|序号|配置名|URL|功能|其它|
|--- |--- | --- | --- | --- |
|3.1|sale_view_guess_favorite|http://rcmd.a.ajkdns.com/esf-pc-pro-history/users/C3D1201D-6844-9BA6-879D-037B201BC696/recommendations/|猜你喜欢BI接口||
|3.1||http://shanghai.anjuke.com/v3/ajax/ershouview/interestrecommend/|猜你喜欢接口||
|3.2||http://shanghai.anjuke.com/v3/ajax/ershouviewrecommend/|同月供优质房接口||
|3.2|sale_view_area_price|http://xapp20-076.i.ajkdns.com:8080/sale-pc-pro-price/items/|同月供优质房BI接口||
|3.3||http://shanghai.anjuke.com/v3/ajax/ershouviewrecommend/|同地段接口||
|3.3|sale_view_block|http://xapp20-076.i.ajkdns.com:8080/sale-pc-pro-subregion/items/|同地段BI接口||
|3.4||http://shanghai.anjuke.com/v3/ajax/ershouviewrecommend/|投资潜力房接口||
|3.4|sale_view_potential|http://xapp20-076.i.ajkdns.com:8080/sale-pc-pro-value/items/|投资潜力房BI接口||
|3.5||http://shanghai.anjuke.com/v3/ajax/ershouviewrecommend/|相似小区推荐||
|3.6||http://shanghai.anjuke.com/v3/ajax/ershouviewrecommend/|主推房源推荐||
|3.5|sale_view_similar_comm|http://rcmd.a.ajkdns.com/sale-pc-pro/items/362171101/similars/|相似小区BI接口||
||

### 数据库和表:
|序号|数据库|地址|表名称|作用|读写|是否独有|访问量|
|--- | --- | --- | --- | --- | --- | --- | --- |
|1 3|user_prop_sh_db||broker_property_{city_id}|用户端上海二手房房源表|读|是| 1486190 * 80% = 120w|
|1 3|user_prop_bj_db||broker_property_{city_id}|用户端北京二手房房源表|读|否| 1486190 * 80% = 120w|
|1 3|user_prop_s0{city_id%3}_db||broker_property_{city_id}|用户端二手房房源表|读|否| 1486190 * 80% = 120w|
|1 3|user_prop_s0{city_id%3}_db||wuba_property_{city_id}|用户端二手房房源表|读|否|1486190 * 80% = 120w |
|1 3|user_prop_s0{city_id%3}_db||wuba_property_image_{city_id}|用户端二手房58房源图片表|读|否| 1486190 * 80% = 120w|
|1 3|anjuke_db||ajk_swsale|抓取房源表|读|否| 1486190 * 80% = 120w|
|1 3|anjuke_db||ajk_swrent|搜屋的出租房源|读|是| 1486190 * 80% = 120w|
|1 3|ajk_dw_stats||hu_dingcai_broker_score|租房小区排行|读|否| 1486190 * 80% = 120w|
|2 |ajk_dw_stats||hu_dingcai_ufs_avg|存储二手房推荐小区　二手房精选房源　租房精选房源|读|否|1486190 * 80% = 120w |
|2 |mobile_db||broker_chatinfo|微聊相关|读|否|1486190 * 80% = 120w |
|2 |mobile_db||weshop_shops|经纪人微信相关|读|否|1486190 * 80% = 120w |
|2 |ajk_dw_stats||da_mobile_chat_talented_broker_upload|经纪人微聊相关|读|否| 1486190 * 80% = 120w|
|1 3|anjuke_db||ajk_wuba_commtype_relation|58安居客虚拟小区区域板块(区域商圈)对照表|读|否| 1486190 * 80% = 120w|
|1 |anjuke_db||ajk_fitmenttype|装修类型映射|读|否|1486190 * 80% = 120w |
|1 |anjuke_db||ajk_usetype|房屋类型配置|读|否|1486190 * 80% = 120w |
|1 3 4|anjuke_db||ajk_communitys|小区表|读|否| 1486190 * 80% = 120w|
|1 3 4|anjuke_db||ajk_commtype|区域板块表|读|否| 1486190 * 89% = 120w|
|1 4　5|anjuke_db||ajk_attachments_comm|小区信息|读|否| 1486190 * 89% = 120w|
|1 4|anjuke_db||map_communities_baidu|小区百度地图信息|读|否|1486190 * 89% = 120w |
|1 4|anjuke_db||map_communities_soso_pano|小区街景|读|否|1486190 * 89% = 120w |
|5|user_prop_db||ajk_nearby_score|小区周边得分|读|否|1486190 * 89% = 120w |
|5|user_prop_db||commPlaceRelationDao|二手房小区周边对应关系|读|否|1486190 * 89% = 120w |
|5|anjuke_db||map_communities_soso_pano|小区街景|读|否| 1486190 * 89% = 120w|
|5|anjuke_db||map_communities_soso_pano|小区街景|读|否|1486190 * 89% = 120w |




### Memcache&Redis
|名称|类型|地址|申请大小|使用率|是否统一管理|功能|负责人|
|--- | --- | --- | --- | --- | --- | --- | --- |
|property_servers|hbase|||||二手房归档房源信息|
｜erhsou_prop_img_cluster|redis||||二手房经纪人房源图片
### 关联job
|job名称|job管理地址|功能|负责人|
|--- | --- | --- | --- |
|Ershou_Job_Home_NoticUpdateSource|http://drone.corp.anjuke.com/scheduler/job/163/view |首页模块更新通知（半小时一次）定时 |石兆媛
|Ershou_Job_Home_UpdateDataSource|http://drone.corp.anjuke.com/daemon/job/66/view|首页模块更新job （常驻）|石兆媛
|Ershou_Job_Home_UpdateDataSourceForXinFang|http://drone.corp.anjuke.com/daemon/job/239/view|首页新房模块更新job（常驻）|石兆媛
