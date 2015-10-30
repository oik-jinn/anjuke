# 安居客首页job
    
### 页面模块列表
|URL|仓库|机器|负责人|功能|
|--- | --- | --- | --- | --- |
|http://{pinyin} . xzl.anjuke.com ./api/recomend/?cid={$city_id}&limit=18&tamp=. time()|jingpu|首页商业地产数据|
|http://{pinyin} . zu.anjuke.com . /ajax/homepage/?cid={$city_id}&tamp= . time()}|haozu|首页租房数据|

### 配置
house.php

pay_type
pay_type_58
spider_pay_type
fitment
list_fit
toward_type
deployment
rent_type
house_type
ajk_fitment_type

app-community-core/config/api.php
api_community_image_citys 确定获取小区图片是否使用新的api

app-user-common/config/seo.php
api_jinpu_search_and_hot


seo新房相关
http://api.fang.anjuke.com/m/iphone/1.3/loupan/list/?city_id=11&page_size=20


### 依赖的内部服务(指的是其它仓库的服务)

|服务名|仓库地址|负责人(或部门)|功能|
| --- | --- | --- | --- |
|Biz_Home_SeoBiz | user-site/app-biz|石兆媛|城市首页小区大全|
|Biz_Home_ShouYeModeBiz | user-site/app-biz |石兆媛|首页模块|
|Public_Core_Home_Service_ShouYeModeService|user-site/app-public-core |石兆媛|对首页模块memcache key的操作|
|Shangpu_Core_Xzl_Service_LoupanService| user-site/app-shangpu-core |石兆媛|写字楼楼盘service|
|Biz_Home_SearchBiz|user-site/app-biz |石兆媛| 首页搜索biz 主要获得首页搜索框的数据|
|Public_Core_Home_Service_HotTagService|user-site/app-public-core |石兆媛|获取首页热门标签数据|
|Public_Core_Home_Service_SearchService|user-site/app-ershou-core |石兆媛|搜索框筛选项数据|
|Biz_Home_LandlordBiz|user-site/app-biz|石兆媛|小区大全|
|Biz_Home_HotTagBiz|user-site/app-biz |石兆媛|首页模块|
|Maifang_Core_Sell_Bll_SellBll| user-site/app-maifang-core|石兆媛|房源销售信息|
|Maifang_Core_Sell_Service_MfPropHomeService| user-site/app-maifang-core|石兆媛|房源销售列表|
|Maifang_Core_Sell_Service_MfPropCntService| user-site/app-maifang-core|石兆媛|房源销售信息|
|Ershou_Core_Service_PropertyService| user-site/app-maifang-core|石兆媛|房源信息|
|Ershou_Core_Service_BrokerService| user-site/app-maifang-core|石兆媛|经纪人信息|
|Ershou_Core_Property_Service_PropertyDataService| user-site/app-maifang-core|石兆媛|房源信息|
|Maifang_Core_Sell_Bll_SellBll| user-site/app-maifang-core|石兆媛|房源销售信息|
|Biz_Home_SeoExtensionHomeBiz| user-site/app-biz |石兆媛|首页seo|
|Biz_Seo_SeoExtensionBiz|user-site/app-biz |石兆媛|seo|


### 依赖的外部URL
|配置名|URL|功能|其它|
| --- | --- | --- | --- |
### 数据库和表:
|数据库|地址|表名称|作用|读写|是否独有|
| --- | --- | --- | --- | --- | --- |
|user_prop_db||cityhome_mcache|存储首页模块memcache key|读/写|是
|ajk_dw_stats||solly_hot_price_region|存储二手房热门区域热门板块购房预算|读|否|
|ajk_dw_stats||suggest_comm_old_user|老用户推荐|读|否|
|ajk_dw_stats||suggest_comm_new_user|新用户推荐|读|否|
|anjuke_db||ajk_commtype|存储区域板块|读|是|
|ajk_dw_stats||kant_web_homepage_hz_hot_comm|租房小区排行|读|否|
|ajk_dw_stats||kant_web_homepage_hot_tags|存储二手房推荐小区　二手房精选房源　租房精选房源|读|否|
|cms_user||city_home_cfg|问答|读|否|
|rent_db||prop|租房房源|读|否|
|propertys_bj_db||ajk_propertys|北京二手房房源|读|否|
|propertys_sh_db||ajk_propertys|上海二手房房源|读|否|
|propertys_other_db||ajk_propertys|城市id小于42二手房房源|读|否|
|propertys_db_04||ajk_propertys|城市id大于等于42二手房房源|读|否|
|person_db||mf_prop_count|房源点击量|读|否|
|anjuke_db||ajk_brokerextend|经纪人基础信息|读|否|
|anjuke_db||cst_company|公司信息|读|否|
|anjuke_db||cst_broker_company|门店信息|读|否|
|person_db||ajk_propertys|城市id大于等于42二手房房源|读|否|
|person_db||ajk_propertys|城市id大于等于42二手房房源|读|否|
|ajk_dw_stats||price_trend_comm_monthly|小区单月均价|读|否|
|anjuke_db||ajk_communitys|小区表|读|否|
|user_prop_db||crawl_images|抓取房源图片|读|否|
|anjuke_db||ajk_pprops_attachments|二手房个人房源默认图|读|否|
|anjuke_db||ajk_attachments_comm|小区图片|读|否|
|anjuke_db||ajk_property_data|二手房房源数据|读|否|
|image_db||t_attachment_{propid%3}|二手房经纪人房源所有图片|读|否|
|user_prop_sh_db|zf_wuba_prop_{city_id%3}|租房58房源表|读|否|   
||zf_wuba_prop_extend_{city_id%3}|租房58房源扩展表|读|否|

### Memcache&Redis
|名称|类型|地址|申请大小|使用率|是否统一管理|功能|负责人|
|--- | --- | --- | --- | --- | --- | --- | --- |
｜servers｜
### 关联job
|job名称|job管理地址|功能|负责人|
|--- | --- | --- | --- |
|Ershou_Job_Home_NoticUpdateSource|http://drone.corp.anjuke.com/scheduler/job/163/view |首页模块更新通知（半小时一次）定时 |石兆媛
|Ershou_Job_Home_UpdateDataSource|http://drone.corp.anjuke.com/daemon/job/66/view|首页模块更新job （常驻）|石兆媛
|Ershou_Job_Home_UpdateDataSourceForXinFang|http://drone.corp.anjuke.com/daemon/job/239/view|首页新房模块更新job（常驻）|石兆媛

