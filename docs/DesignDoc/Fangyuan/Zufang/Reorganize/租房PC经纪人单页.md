## 租房PC经纪人单页白皮书


### 说明
```
1.页面使用的表均是同步输出，对表的延迟敏感
2.pv选取pv访问量较高的，pv使用http://knowing.corp.anjuke.com/chart/7345，2015年9月23日 pv：633871 
```

### 1、页面URL
```
http://{城市简拼}.zu.anjuke.com/fangyuan/{房源id}
```

### 2、页面模块
* 2.1、室内图
* 2.2、室外图
* 2.3、经纪人信息
* 2.4、房源信息
* 2.5、小区信息
* 2.6、看了又看，相似房源，猜你喜欢，租得起买得起，附近推荐
* 2.7、周边配套
* 2.8、seo

### 3、页面模块列表
|序号|URL|仓库|负责人|功能|
| --- |--- | --- | --- | --- | 


### 4、依赖的外部URL
|序号|配置名|URL|功能|其它|
|---| --- | --- | --- | --- |
|2.2|api_community_image_url|http://10.10.6.6:8080/service-internal/rest/communities/getCommCategory?json|获取小区图片信息				||
|2.4|rent_servers|http://10.10.8.2|经纪人单页:获取归档的租房隔离房源信息|hbase|
|2.6|ajax异步加载|http://sh.zu.anjuke.com/v3/ajax/viewrecommend/|1.http://rcmd.a.ajkdns.com/rent-pc-pro/items/53990402/similars <br> 2.http://rcmd.a.ajkdns.com/rent-pc-pro-near/recommend/ <br> 3.http://rcmd.a.ajkdns.com/rent-pc-pro-history/recommend/ <br> 4.http://rcmd.a.ajkdns.com/rent-pc-pro-buy/recommend <br> 获取推荐数据，该接口会根据不同单页返回不同推荐数据||
|2.7||http://shanghai.anjuke.com/ajax/communityext/?commid=4762&rand=0.3036617753095925&callback=communityext_callback|前端js加载||
|2.7||http://shanghai.anjuke.com/v3/ajax/nearby/?commid=574&cityid=11&callback=life_url_callback&J1445256398277|前端js加载||
|2.8|solr_esf_comm_url|http://sc10-001.a.ajkdns.com:8983/community35/|小区租房for小区大区||


### 5、数据库和表

|序号|数据库|表名称|作用|读写|是否独有|计算公式|访问量（单位天)|
|---| --- | --- | --- | --- | --- |--- |--- |
|2.2|anjuke_db|ajk_attachments_comm|读取小区图片|读|否|633871(pv)*0.2（非命中缓存）|1.2w|
|2.3|rent_db| broker_mapping |租房和安居客经纪人对应关系     	|读     |否     |633871(pv)*0.2（非命中缓存）|1.2w|
|2.3|rent_db| users_search |读取经纪人信息     	|读     |否     |633871(pv)*0.2（非命中缓存）|1.2w|
|2.3|rent_db| broker_profile |获得经纪人的扩展profile信息     	|读     |否     |633871(pv)*0.2（非命中缓存）|1.2w|
|2.3|broker_db|ad_set_rent|获取《租房》设置橱窗服务的经纪人信息    	|读     |否     |633871(pv)*0.2（非命中缓存）|1.2w|
|2.3|action_db|e_res_activity_pro_|根据房源ID获取房产季信息    	|读     |否     | 633871(pv)*0.2（非命中缓存）|1.2w|
|2.3|anjuke_db| ajk_owner_prop|获取免佣房源     |读     |否     |633871(pv)*0.2（非命中缓存）|1.2w|
|2.3|anjuke_db| cst_company|获取公司信息     |读     |否     |633871(pv)*0.2（非命中缓存）|1.2w|
|2.3|anjuke_db| cst_broker_company|获取门店信息     |读     |否     |633871(pv)*0.2（非命中缓存）|1.2w|
|2.4|user_prop_sh_db，user_prop_bj_db，user_prop_s0{index}_db	|     rent_{city_id}|读取房源基本信息     |读写     |是     |633871(pv)*0.2（非命中缓存）|1.2w|
|2.4|rent_db	|     prop|读取房源基本信息（如果隔离数据不存在，再读取该表）     |读     |是     |633871(pv)*0.2（非命中缓存）|1.2w|
|2.4|rent_db| promotion_prop_planning|for soj     	|读     |否     |633871(pv)*0.2（非命中缓存）|1.2w|
|2.4|user_prop_sh_db，user_prop_bj_db，user_prop_s0{index}_db	|     rent_extend_{city_id}|读取房源扩展信息     |读写     |是     |633871(pv)*0.2（非命中缓存）|1.2w|
|2.5|anjuke_db| sw_metro_stations|读取小区周边地铁站信息     |读     |否     |633871(pv)*0.2（非命中缓存）|1.2w|
|2.5|anjuke_db| sw_metros|读取地铁信息     |读     |否     |633871(pv)*0.2（非命中缓存）|1.2w|
|2.5|anjuke_db|ajk_commtype|获取区域板块信息|读|否|633871(pv)*0.2（非命中缓存）|1.2w|
|2.5|anjuke_db|ajk_usetype|通过ID获得房屋类型配置,eg.老公房|读|否|633871(pv)*0.2（非命中缓存）|1.2w|
|2.5|anjuke_db|map_communities_baidu|小区地标|读|否|633871(pv)*0.2（非命中缓存）|1.2w|
|2.5|anjuke_db|ajk_communitys|读取小区信息|读|否|633871(pv)*0.2（非命中缓存）|1.2w|
|2.5|anjuke_db|ajk_wuba_commtype_relation|判断该小区是否是虚拟小区|读|否|633871(pv)*0.2（非命中缓存）|1.2w|
|2.5|anjuke_db|map_communities_soso_pano|街景坐标|读|否|633871(pv)*0.2（非命中缓存）|1.2w|
|2.8|anjuke_db|ajk_commtype|获取区域板块信息|读|否|633871(pv)*0.2（非命中缓存）|1.2w|

### 6、Memcache&Redis
|名称|类型|名称|地址|申请大小|使用率|是否统一管理|功能|负责人|
|--- | --- | --- |--- | --- | --- | --- | --- | --- |
|设置同区域的随机20个小区大全|memcache |str_memcache_key_for_seo_community_v2|框架默认memcache|    ||          是|| |
|经纪人的房源图片  |zufang_images_master|redis |10.10.8.26:6387|10G    |5.38G|          是|租房隔离图片| 程启明|
|seo|memcache | 'default_memcache_key';|框架默认memcache|    ||          是|| |
|经纪人信息|memcache | broker_info_servers|10.10.3.106:11215,10.10.3.107:11215|    ||          是|| |
### 7、关联job
|job名称|job管理地址|功能|负责人|
|--- | --- | --- | --- |
