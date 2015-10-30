## app二手房单页白皮书
### 1. 页面URL
```
app无,启动安居客app,点击二手房,点击列表中的任意房源,即进入app二手房单页
```

### 2. 页面说明
```
此页面包含了同小区房源,猜你喜欢,附近相似房源等模块
```
    
### 3. 页面模块列表
|编号|URL|仓库|机器|负责人|功能|
| --- |--- | --- | --- | --- | --- |
|3.1|http://api.anjuke.com/anjuke/4.0/property/list|user-site/app-mobile-api|xapp10-196,app10-199|王其春|二手房搜索筛选,用于显示同小区房源|
|3.2|http://api.anjuke.com/mobile/v5/recommend/sale/view/look|user-site/app-mobile-api|xapp10-196,app10-199|王其春|app二手房单页猜你喜欢|
|3.3|http://api.anjuke.com/mobile/v5/recommend/sale/view/near|user-site/app-mobile-api|xapp10-196,app10-199|王其春|app二手房单页附近相似房源|
|3.4|http://api.anjuke.com/anjuke/4.0/broker/info|anjuke-mobile-api|app10-125,app10-127,app10-128,app10-129|徐晓路|获取经纪人信息|
|3.5|http://api.anjuke.com/mobile/1.3/property.getdescandcomms|anjuke-mobile-api|app10-125,app10-127,app10-128,app10-129|徐晓路|获取房源的详细描述以及评论信息|
|3.6|http://api.anjuke.com/mobile/1.3/haopantong.click|anjuke-mobile-api|app10-125,app10-127,app10-128,app10-129|徐晓路|点击付费|
|3.7|http://api.anjuke.com/mobile/1.3/property.view|anjuke-mobile-api|app10-125,app10-127,app10-128,app10-129|徐晓路|记录用户的设备近期访问的房源|
### 4. 依赖的内部服务(指的是其它仓库的服务)
##### 4.1 URL-3.1,3.2,3.3的依赖
|服务名|仓库地址|负责人(或部门)|功能|
| --- | --- | --- | --- |
| Biz_Ershou_Property_Search_SearchBiz | user-site/app-biz|石兆媛|二手房搜索和筛选(solr)|
|Ershou_Core_Service_PropertyInfoService | user-site/app-ershou-core |石兆媛|获取安居客抓取房源的经纪人信息|
|Ershou_Core_Service_DictService|user-site/app-ershou-core |石兆媛|根据装修id获得装修类型|
|Broker_Core_Broker_Service_BrokerBaseService| user-site/app-broker-core |石兆媛|获取经纪人信息|
|Ershou_Core_Service_PropertyService|user-site/app-ershou-core |石兆媛| 获取anjuke房源图片信息|
|Ershou_Core_Property_Service_UserPropertyService|user-site/app-ershou-core |石兆媛| 获取房源基本信息|
|Ershou_Core_Property_Service_UserPropertyWuBaService|user-site/app-ershou-core |石兆媛|获取58房源的图片信息|
##### 4.2 URL-3.4的依赖
|服务名|仓库地址|负责人(或部门)|功能|
| --- | --- | --- | --- |

### 5. 依赖的外部URL
##### 5.1 URL-3.2,3,3的依赖
|配置名|URL|功能|其它|
| --- | --- | --- | --- |
|config['app-ershou-instance']['other_view_recommend']|'http://rcmd.a.ajkdns.com/sale-app-pro-look/recommend|app 二手房单页猜你喜欢BI推荐接口|BI部门提供|
|config['app-ershou-instance']['nearby_recommend']|'http://rcmd.a.ajkdns.com/sale-app-pro-near/recommend|app 二手房单页猜你喜欢BI推荐接口|BI部门提供|
##### 5.2 URL-3.4 的依赖
|配置名|URL|功能|其它|
| --- | --- | --- | --- |
|config['broker_chat_data']|http://api.anjuke.com/mobile-ajk-broker/1.0/broker/chat/data/|获取经纪人的微聊信息|经纪人api|
|config['focus_info']|http://api.anjuke.com/weiliao/focus/info|获取经纪人关注信息|微聊api|
|config['getOnlineStatus']|http://api.anjuke.com/weiliao/common/getOnlineStatus|获取经纪人微聊在线状态|微聊api|
##### 5.3 URL-3.5 的依赖
|配置名|URL|功能|其它|
| --- | --- | --- | --- |
|config['commonty_get_url']|http://www.anjuke.com/api/comm/pageinfo|根据小区id获取小区信息||
|config["sv_qq_map"]|http://sv.map.qq.com/xf|根据地图经纬度获取小区所在街道的经纬度||
|config["apis_qq_map"]|http://apis.map.qq.com/ws/streetview/v1/image|根据街道经纬度获得街道图片||
|config["ajk_image_upload_url"]|http://upd1.ajkimg.com/upload|上传图片||
|config['broker_info_validation']|http://www.anjuke.com/api/broker/getauth/|获得经纪人认证信息||
|config['get_community_photos']|http://www.anjuke.com/api/community/picinfo/|获取小区图片||
##### 5.4 URL-3.6 的依赖
|配置名|URL|功能|其它|
| --- | --- | --- | --- |
|config["anjuke_haopantong_url"]|http://s.anjuke.com/st|二手房好盘通点击扣费接口|经纪人部门负责|
|config["exp_property_page_init_process"]|http://10.10.6.6:8080/service-exp/rest/exp/propertyPageInitProcess|记录曝光相关信息||
|config['internal_api_url']+config['haopan_fixed_rule']|http://10.10.6.6/service-ppc/rest/ppcbroker/ppcCityInfo|判断城市是否开通定价||
|config['internal_api_url']+config['haopan_fixed_detail']|http://10.10.6.6/service-ppc/rest/ppc/getPpcPlan|定价的详情|
|config['internal_api_url']+config['haopan_fixed_relation']|http://10.10.6.6/service-ppc/rest/ppc/getRelationByProid|定价的关系|

##### nlogger配置
    __nlog_configs = array(
    array(
    'host' => 'shipper.logger.a.ajkdns.com',
    'port' => 24226,
    ),

    array(
    'host' => 'shipper.logger.a.ajkdns.com',
    'port' => 24225,
    ),

    array(
    'host' => '10.10.9.43',
    'port' => 24225,
    ),);


### 6. 数据库和表:
##### 6.1 URL-3.4 的依赖
|数据库|表名称|作用|读写|是否独有|
| --- | --- | --- | --- | --- |
|anjuke_db|ajk_brokerextend|根据经纪人手机号查询经纪人信息|读|否|
|anjuke_db|shop_list|根据经纪人id获取店铺信息|读|否|
|mobile_db|broker_chatinfo|根据经纪人broker_id或者chat_id查询经纪人信息|读|否|
##### 6.2 URL-3.5 的依赖
|数据库|表名称|作用|读写|是否独有|
| --- | --- | --- | --- | --- |
|user_prop_s01_db|wuba_property_*|根据58房源id获取房源信息|读|否|
|user_prop_s02_db|wuba_property_*|根据58房源id获取房源信息|读|否|
|user_prop_s00_db|wuba_property_*|根据58房源id获取房源信息|读|否|
|user_prop_sh_db|wuba_property_*|根据58房源id获取房源信息|读|否|
|user_prop_bj_db|wuba_property_*|根据58房源id获取房源信息|读|否|
|community_db|community_info|根据小区id获取小区信息|读|否|
|community_db|community_avg_price|根据小区id获取小区均价|读|否|
|anjuke_db|ajk_commtype|根据区域code获取区域信息|读|否|
|anjuke_db|map_communities_baidu|根据小区id获取小区经纬度|读|否|
|user_prop_s01_db|wuba_property_image_*|根据58房源id获取房源图片信息|读|否|
|user_prop_s02_db|wuba_property_image_*|根据58房源id获取房源图片信息|读|否|
|user_prop_s00_db|wuba_property_image_*|根据58房源id获取房源图片信息|读|否|
|user_prop_sh_db|wuba_property_image_*|根据58房源id获取房源图片信息|读|否|
|user_prop_bj_db|wuba_property_image_*|根据58房源id获取房源图片信息|读|否|
|anjuke_db|ajk_fitmenttype|根据装修类型id获得装修详细信息|读|否|
|anjuke_db|ajk_usetype|根据使用类型id获取使用详情|读|否|
|user_prop_s01_db|wuba_property_extend_*|根据58房源id获取房源扩展信息|读|否|
|user_prop_s02_db|wuba_property_extend_*|根据58房源id获取房源扩展信息|读|否|
|user_prop_s00_db|wuba_property_extend_*|根据58房源id获取房源扩展信息|读|否|
|user_prop_sh_db|wuba_property_extend_*|根据58房源id获取房源扩展信息|读|否|
|user_prop_bj_db|wuba_property_extend_*|根据58房源id获取房源扩展信息|读|否|
|anjuke-db|ajk_ufs_falsehouse_data|读取经纪人发布过的虚假房源数目|读|否|
|anjuke-db|ajk_street_image|根据地址查询街道图片|读写|否|
|anjuke-db|ajk_local_bind_comm|记录location和小区的绑定关系|读|否|
|anjuke-db|ajk_locations|记录公交,地铁位置信息|读|否|
##### 6.3 URL-3.6 的依赖
|数据库|表名称|作用|读写|是否独有|
| --- | --- | --- | --- | --- |
|propertys_other_db|ajk_propertys|查看房源是否有效|读|否|
|propertys_db_04|ajk_propertys|查看房源是否有效|读|否|
|propertys_sh_db|ajk_propertys|查看房源是否有效|读|否|
|anjuke_db|ajk_brokerextend|根据用户id获取经纪人id|读|否|
##### 6.4 URL-3.7 的依赖
|数据库|表名称|作用|读写|是否独有|
| --- | --- | --- | --- | --- |
|anjuke_db|ajk_propertys|根据房源id获取房源的城市id|读|否|
|mobile_db|device_prop_log|存储设备访问房源的历史记录|读写|否|
### 7. Memcache&Redis
##### 7.1 URL-3.5 的依赖
|名称|类型|key|作用|仓库|
| --- | --- | --- | ---| --- |
|servers|memcache|yanjuke_dao_get_fitment_map_.*(type id)|缓存装修类型对应的信息|app-mobile-api,[详情说明](http://gitlab.corp.anjuke.com/_site/docs/blob/master/API/%E5%AE%89%E5%B1%85%E5%AE%A2/cache/memcache_redis.md)|
|servers|memcache|yanjuke_dao_get_usetype_map_.*(type id)|缓存房源使用类型对应的信息|app-mobile-api,[详情说明](http://gitlab.corp.anjuke.com/_site/docs/blob/master/API/%E5%AE%89%E5%B1%85%E5%AE%A2/cache/memcache_redis.md)|
##### 7.2 URL-3.6 的依赖
|名称|类型|作用|仓库|详情|
| --- | --- | --- | ---| --- |
|exp_redis_server|redis|缓存曝光信息|anjuke-mobile-api|[详情](http://gitlab.corp.anjuke.com/_site/docs/blob/master/API/%E5%AE%89%E5%B1%85%E5%AE%A2/cache/memcache_redis.md)|
|redis_server|redis|缓存好盘信息|anjuke-moible-api|[详情](http://gitlab.corp.anjuke.com/_site/docs/blob/master/API/%E5%AE%89%E5%B1%85%E5%AE%A2/cache/memcache_redis.md)|

|名称|类型|key|作用|仓库|
| --- | --- | --- | ---| --- |
|servers|memcache|anjuke_mobile_api_isppc_*|缓存城市是否开通了点击付费|app-mobile-api,[详情说明](http://gitlab.corp.anjuke.com/_site/docs/blob/master/API/%E5%AE%89%E5%B1%85%E5%AE%A2/cache/memcache_redis.md)|

### 8. 关联job
|job名称|job管理地址|功能|负责人|
|--- | --- | --- | --- |