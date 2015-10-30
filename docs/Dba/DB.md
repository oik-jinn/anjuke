## API DB 整理

### `ajk_dw_stats`

#### haozu(git@gitlab.corp.anjuke.com:_mobile-api/haozu.git)

|表名|读/写|说明|涉及接口
|---|---|---|---|
|hu_rank_broker_score|读|经纪人得分情况|^/rank/index/
|hu_broker_ip|读|判断guid是否是经纪人guid| '^/prop/publish/','^/prop/house_publish/','^/prop/error/([1-3])/',/prop/house_edit/([0-9]+)/([0-9]+)?$
|hu_guanlian_xq_all_ren|读|小区页面，看过这个小区的 人还看过模块 里的推荐小区数据|^/compound/broker/(.*)小区中介房源^/compound/landlord/(.*)小区个人房源^/compound/tran/(.*)小区配套交通^/compound/trend/(.*)小区 租金趋势

#### git@gitlab.corp.anjuke.com:_mobile-api/new-api.git

|表名|读/写|说明|涉及接口
|---|---|---|---|
hu_dingcai_broker_score|读||^/prop/view/([A-Za-z]+[0-9]+)$ ,'^/prop/view/([0-9]+)$','^/prop/(view2)/([0-9]+)$','^/prop/preview/([0-9]+)$',
hu_dingcai_ufs_avg|读||^/prop/view/([A-Za-z]+[0-9]+)$ ,'^/prop/view/([0-9]+)$','^/prop/(view2)/([0-9]+)$','^/prop/preview/([0-9]+)$',
ajk_nearby_relation|读|二手房小区周边对应关系|
dw_ajk_areacode_hotcomm|读|touch单页小区推荐|^/ajax/community/recommend ^/fangjia/(top[1-9])-([A-Za-z0-9]+)-d([0-9]+)
kant_esf_broker_preference|读|job使用|Mobile_Job_DealServiceRecommend
kant_esf_comm_recomm_look|读|job使用|Mobile_Job_DealServiceRecommend
kant_esf_subregion_recomm_look|读|Mobile_Job_DealServiceRecommend
tina_zdh_user_broker_match_weight_rt|读|^/weiliao/recomm/prop ^/weiliao/recomm/broker
tina_zdh_user_preference_user_tag|读|^/weiliao/recomm/prop ^/weiliao/recomm/broker
da_mobile_chat_talented_broker_upload|读|^/broker/info$  ^/api/rec$ Ershou_Job_Property_Chart_Icon
price_trend_comm_monthly|读|获取城市区域或板块的涨跌幅排名|^/fangjia/(top[1-9])-([A-Za-z0-9]+)-d([0-9]+)
dw_pricing_comm_quartile|读||^/fangjia/(top[1-9])-([A-Za-z0-9]+)-d([0-9]+)
dw_pricing_comm_rank|读|小区rank|^/fangjia/(top[1-9])-([A-Za-z0-9]+)-d([0-9]+)
dw_pricing_comm_weekly|读||^/fangjia/(top[1-9])-([A-Za-z0-9]+)-d([0-9]+)
dw_pricing_areacode_daily|读||^/fangjia/(top[1-9])-([A-Za-z0-9]+)-d([0-9]+)
price_trend_areacode_monthly|读|获取城市或区域或板块的月均价格|^/fangjia/(top[1-9])-([A-Za-z0-9]+)-d([0-9]+) ^/v3/ajax/viewandview/ ^/v3/chart/pricetrend/ ^/all/price/qs-[A-Za-z0-9]+-([0-9]+)
dw_pricing_areacode_weekly|读| /v3/chart/pricetrend/
seo_comm_internal|读|seo内连 |'^/prop/view/([0-9]+)$','^/prop/(view2)/([0-9]+)$','^/prop/preview/([0-9]+)$',
hu_rank_all_score_type_bak|读| rank分|搜索底层用
hu_rank_all_score_type|读|rank分|搜索底层用
hu_rank_all_score_type_ppc|读|rank分|搜索底层用
dw_comm_prop_heat|读|获取小区房源区域热度|^/ershou/prop/manage/commPropHeat
dw_region_heat|读|区域热度|^/ershou/prop/manage/regionHeat
da_mobile_weshop_share_daily|读|Mobile_Job_DealWeshopSharePush
da_mobile_chat_talented_broker_upload|读|获取经纪人的信息|^/broker/info$
hu_rank_all_score_rank_list|读|更新房源rank的job用|Ershou_Job_Property_UpdatePropertyRank
hu_rank_all_score_rank_list_ppc|读|更新房源rank的job用|Ershou_Job_Property_UpdatePropertyRank
star_intermediary_score|读|明星中介表的更新状态job用|Ershou_Job_Broker_BrokerStarSync
hu_tables_upload_status|读|明星中介表的更新状态job用|Ershou_Job_Broker_BrokerStarSync
kant_web_homepage_hot_tags|读|首页热门标签数据|/ajax/indexapi.*
kant_web_homepage_hz_hot_comm|读|首页热门标签数据|/ajax/indexapi.*
solly_hot_price_region|读|导航热门推荐| '^/home/?$', '^/$','^/default.*' ^/seo/api/*
suggest_comm_new_user|读|新用户推荐job使用|Ershou_Job_Home_UpdateDataSource
suggest_comm_old_user|读|老用户推荐job使用|Ershou_Job_Home_UpdateDataSource
kant_esf_comm_recomm_call|读|小区看了又看推荐使用|

### `hz_dw_stats`

#### haozu(git@gitlab.corp.anjuke.com:_mobile-api/haozu.git)
|表名|读/写|说明|涉及接口
|---|---|---|---|
hu_guanlian_key_hz|读|根据关键获取推荐的关键字|^/kw/([0-9]+)/([0-9]+)/([0-9]+)\.html  ^/navi/(0-9|[ABCDEFGHJKLMNOPQRSTWXYZ])/([0-9]+)\.html 
rate_price|读|好租rank价格因素测试|
hu_guanlian_rent_p_new_bu|读|个人新增房源推荐|'^/mobile/(2\.0)/(recommend\.([a-z][_0-9a-zA-Z]*))','^/haozu/mobile/(2\.0)/(recommend\.([a-z][_0-9a-zA-Z]*))'
hu_guanlian_rent_p|读|个人新增房源推荐|'^/mobile/(2\.0)/(recommend\.([a-z][_0-9a-zA-Z]*))','^/haozu/mobile/(2\.0)/(recommend\.([a-z][_0-9a-zA-Z]*))'
hu_guanlian_rent_p_bu|读|个人新增房源推荐|'^/mobile/(2\.0)/(recommend\.([a-z][_0-9a-zA-Z]*))','^/haozu/mobile/(2\.0)/(recommend\.([a-z][_0-9a-zA-Z]*))'
hu_guanlian_rent_b_new_bu|读|个人新增房源推荐|'^/mobile/(2\.0)/(recommend\.([a-z][_0-9a-zA-Z]*))','^/haozu/mobile/(2\.0)/(recommend\.([a-z][_0-9a-zA-Z]*))'
hu_guanlian_rent_b|读|从好租那边获得推荐|'^/mobile/(2\.0)/(recommend\.([a-z][_0-9a-zA-Z]*))','^/haozu/mobile/(2\.0)/(recommend\.([a-z][_0-9a-zA-Z]*))'
hu_guanlian_rent_b_bu|读|从好租那边获得推荐|'^/mobile/(2\.0)/(recommend\.([a-z][_0-9a-zA-Z]*))','^/haozu/mobile/(2\.0)/(recommend\.([a-z][_0-9a-zA-Z]*))'
hu_rank_all_score_rent_p_list|读|取得数据部的rank值|'^/rank/index/'
hu_rank_all_score_rent_b_list|读|根据好租经纪人房源Id获取好租经纪人房源的rank得分|'^/rank/index/'
hu_rank_all_score_type_rent|读|rank分类型|'^/rank/index/'

#### git@gitlab.corp.anjuke.com:_mobile-api/new-api.git
|表名|读/写|说明|涉及接口
|---|---|---|---|
hu_rank_all_score_rent_b_list|读|根据好租经纪人房源Id获取好租经纪人房源的rank得分|Zufang_Rent_Job_RentRunJob Zufang_Rent_Job_SingleRentRunJob Zufang_Rent_Job_SublessorRebuildRunJob
hu_rank_all_score_type_rent|读|rank分类型|Zufang_Rent_Job_RentRunJob Zufang_Rent_Job_SingleRentRunJob Zufang_Rent_Job_SublessorRebuildRunJob
solly_rank_all_score_rent_b_list|读|根据好租经纪人房源Id获取好租经纪人房源的rank得分|Zufang_Rent_Job_RentRunJob Zufang_Rent_Job_SingleRentRunJob Zufang_Rent_Job_SublessorRebuildRunJob
hu_rank_all_score_rent_d_list|读|大业主房源rank|Zufang_Rent_Job_RentRunJob Zufang_Rent_Job_SingleRentRunJob Zufang_Rent_Job_SublessorRebuildRunJob

### `jp_dw_stats`
#### git@gitlab.corp.anjuke.com:_mobile-api/jinpu.git

|表名|读/写|说明|涉及接口
|---|---|---|---|
solly_jp_ajk_comm_shop|读|为空不知道干嘛|
hu_black_mobile|读||^/gettelnumber/ ^/kitinfo ^/paypercall/
jp_bluewhale_member_flag|读|
solly_jp_broker_rank_rate|读|经纪人权重|^/jinpu/1.0/offices ^/jinpu/1.0/shops
salome_jp_recomm_rank|读||
dw_jp_midprice_city_monthly|读|当月价格趋势|
e_developer_analysis|读||
jp_house_click_num|读|点击量|
hu_rank_jp_all_score_rank_list|读|房均质量分|
hu_guanlian_jp_prop|读||
hu_guanlian_jp_prop_new|读||
jp_hp_block_supply_and_demand_sd|读||
huo_jp_hp_prop_block_recommend|读||
jp_hp_city_supply_and_demand_sd|读||
vppv_7days_per_prop|读||
huo_jp_hp_prop_district_recommend|读||
jp_hp_property_supply_and_demand_sd|读||
jp_hp_property_vacant_sd|读||
solly_jp_ajk_comm|读||
hu_jp_black_vppv|读||
t_keepalive|读||
jp_manager_info|读||
jp_manager_info_detail|读||
jp_member_analysis|读||
jp_member_click_num|读||
jp_member_flag|读||
stat_property_tuangou|读||
jp_property_completion|读||
solly_jp_loupan_cycle|读||
solly_jp_rank_building_hot_rate|读||
jp_property_new_completion|读||
hu_guanlian_jp_loupan|读||
jp_real_store_flag|读||
dw_jp_midprice_region_monthly|读||
hu_guanlian_jp_loupan|读||
dw_soj_hourly_vppv|读||
dw_soj_hourly_vppv_ratio|读||
huo_jp_spread_budget_suggestion|读||
hu_rank_jp_all_score_rank_type|读||
hu_rank_prop_s_ranktype_jp|读||
hu_rank_prop_p_ranktype_jp|读||
dw_jp_midprice_subregion_monthly|读||
hu_rank_jp_all_score_type|读||
user_house_click_num|读|房源点击数|
jp_business_cycle_vppv_sd|读|获取热门商圈|^/jinpu/1.0/shops  ^/jinpu/1.0/shop/([0-9]+)$ ^/jinpu/1.0/offices
jp_district_vppv_sd|读|获取热门区域|^/jinpu/1.0/shops  ^/jinpu/1.0/shop/([0-9]+)$ ^/jinpu/1.0/offices
jp_property_district_vppv_sd|读|获取某个地区的热门物业|^/jinpu/1.0/shops  ^/jinpu/1.0/shop/([0-9]+)$ ^/jinpu/1.0/offices
jp_property_vppv_sd|读|获取热门物业|^/jinpu/1.0/shops  ^/jinpu/1.0/shop/([0-9]+)$ ^/jinpu/1.0/offices
solly_jp_wg_broker_stop|读|为空不知道干嘛|
solly_jp_xj_prop|读|为空不知道干嘛|


### `if_dw_stats`

#### anjuke-mobile-api(git@gitlab.corp.anjuke.com:_mobile-api/anjuke-mobile-api.git)

|表名|读/写|说明|涉及接口
|---|---|---|---|
dw_mobile_app_push_user|读|获取推送关系数据|^/4.0/push/sendinfo

#### new-api(git@gitlab.corp.anjuke.com:_mobile-api/new-api.git)

|表名|读/写|说明|涉及接口
|---|---|---|---|
kant_nh_comm_recomm_call|读|根据楼盘浏览历史推荐楼盘|Mobile_Job_DealServiceRecommend
kant_comm2loupan_recomm|读|根据二手房浏览历史推荐楼盘|Mobile_Job_DealServiceRecommend
da_app_push_user|读||Mobile_Job_DealServicePush Mobile_Job_DealChatPush Mobile_Job_NewVersionUpdatePush Mobile_Job_OldUserPushByDevice

### `mobile_db`

#### anjuke-mobile-api(git@gitlab.corp.anjuke.com:_mobile-api/anjuke-mobile-api.git)

|表名|读/写|说明|涉及接口
|---|---|---|---|
pub_callback|读|拨打经纪人电话记录|  "^/mobile/1.2/admin.recordPhoneCall$","^/mobile/1.3/admin.recordPhoneCall$"
pub_feedback|读|市场活动|^/mobile/1.2/admin.postFeedback$ ^/mobile/1.3/admin.postFeedback$
pub_report_self|写|保存用户访问的房源|^/4\.0/reportself$ ^/anjuke/4\.0/reportself$
version|读|获得在线版本|^/4\.0/admin/versionupgrade$
version_upgrade|读|获得在线版本|^/4\.0/admin/versionupgrade$
version_history|读|获得在线版本|^/4\.0/admin/versionupgrade$
device_prop_log|写|记录浏览历史|^/mobile/1.3/property.viewrecommend$
market_activity_logs|读|获取实时动态|^/4.0/marketing/clear$ ^/4.0/marketing/home$ ^/4.0/marketing/stream$
market_app_rec|读|获取市场活动|^/mobile/1.2/admin.postFeedback$ ^/mobile/1.3/admin.postFeedback$
pub_report_self_new|写|保存用户访问|^/4\.0/reportself$ ^/anjuke/4\.0/reportself$

#### new-api(git@gitlab.corp.anjuke.com:_mobile-api/new-api.git)

|表名|读/写|说明|涉及接口
|---|---|---|---|
app_login_message|读|applog信息|Mobile_Job_DealActivity1405
broker_chatinfo|读|获取经纪人是否开通了微聊状态|^/[a-z]+/sale/([0-9]+).*
broker_user_relation|读|默认根据BI推荐信息推荐经纪人,其次是用户附近经纪人|^/weiliao/broker/block
confirm_kanfang|读|保存卡片内容|^/kf/apply
confirm_kanfang_ext|读|保存卡片内容|^/kf/apply
market_activity_daily_stat|读|统计信息|Mobile_Job_DealMarketActivity
market_activity_logs|读|log信息|Mobile_Job_DealActivity1403 Mobile_Job_DealActivity1405 Mobile_Job_DealMarketActivity
pub_report_self_new|读|反馈信息对应的设备信息|Mobile_Job_DealActivity1403 Mobile_Job_DealActivity1405 Mobile_Job_DealMarketActivity
spam_b8_texts|读|B8训练用的chat信息|^/spam/b8/sample/pending$ ^/spam/b8/classify$
ad_statistics|读|广告统计 |^/chat/content$
app_mkt1403_award_log|读|用户兑奖记录表|^/mkt1403/award/list/
app_mkt1403_play_log|读|用户刷金币信息|^/mkt1403/post/gensms/
app_mkt1403_recommend_log|读|用户推荐记录表|Mobile_Job_DealActivity1403
app_mkt1403_total_stat|读|用户基本数据表|Mobile_Job_DealActivity1403
app_mkt1405_award_log|读|用户兑奖记录表|^/mkt1403/award/list/
app_mkt1405_play_log|读|用户刷金币信息|^/mkt1405/post/gensms/ ^/mkt1405/post/exchange ^/mkt1405/activityend/
app_mkt1405_recommend_award_history|读|用户推荐记录统计表|Mobile_Job_DealActivity1405Stat
app_mkt1405_recommend_log|读|1405活动|Mobile_Job_DealActivity1405
app_mkt1405_recommend_stat|读|1405活动推荐者的统计信息|
app_mkt1405_total_stat|读|1405活动|检查规则0 参与码 无效 定为作弊|Mobile_Job_DealActivity1405
app_mkt1408_device|读|设备信息|Mobile_Job_InitActive1408Award
app_mkt1408_play_log|读|1408活动中奖信息|Mobile_Job_InitActive1408Award
app_mkt1408_reward|读|1408活动|Mobile_Job_InitMkt1408RewardReport
app_mkt1409_award_count|读|1409活动|^/mkt1409/index
app_mkt1409_device|读|1409活动设备信息记录表|^/mkt1409/index
app_mkt1409_play_log|读|1409活动play记录表|^/mkt1409/index
app_mkt1410_banner_stat|读|1410活动广告业统计信息表|^/mkt1410/getAward$ ^/mkt1410/bannerStat$ ^/mkt1410/getInfo$
app_mkt1410_device|读|1410活动设备信息记录表|^/mkt1410/getAward$ ^/mkt1410/bannerStat$ ^/mkt1410/getInfo$
app_mkt1501_feedback|读|活动信息|Mobile_Job_FeedbackActivity1501
app_mkt1501_order|读|所有的充值订单|^/recharge/callback
app_mkt1501_recharge|读|活动1501 recharge记录表|^/recharge/callback
ad_welcome_image|读|banner图片信息|^/device/setting/adimage$
ad_welcome_rule|读|查询banner配置项信息|^/device/setting/adimage$
banner_item_1|读|查询banner图片信息|^/device/setting/adimage$
banner_setting_1|读|banner图片信息|^/device/setting/adimage
device_prop_log|读||Mobile_Job_DealServiceRecommend
require_prop|读|同城市所有的找房需求|^/ershou/prop/require/allRequire
require_prop_broker_relation|读|同城市所有的找房需求经纪人|^/ershou/prop/require/allRequire
spam_b8_texts|读|B8信息|^/spam/b8/classify$
spam_b8_wordlist|读|B8文本信息|^/spam/b8/classify$

### `mobile_api_db`

#### anjuke-mobile-api(git@gitlab.corp.anjuke.com:_mobile-api/anjuke-mobile-api.git)

|表名|读/写|说明|涉及接口
|---|---|---|---|
fortune_item|读|物品列表|^/4.0/comm/identify/
fortune_price_period|读|价格区间|^/4.0/comm/identify/
identify_text|读|鉴定信息|^/4.0/comm/identify/
user_focus|读|设备对小区的关注状态|^/4.0/comm/focusstatus/$ ^/4.0/comm/focus/$
cms_material|读|素材详情|^/4.0/chat/content$
msg_push_history|读|消息|^/4.0/push/message/$
push_msg|读|推送关系数据|^/4.0/push/home/
tag_msg|读|推送关系数据|^/4.0/push/home/
user_view|读|物品的浏览信息|

#### new-api(git@gitlab.corp.anjuke.com:_mobile-api/new-api.git)

|表名|读/写|说明|涉及接口
|---|---|---|---|
cms_material|读| |^/weiliao/service/push$ ^/weiliao/service/([0-9]+)/action/([0-9]+)$
cms_msg_sent|读|查询未完成处理的群发消息|^/chat/content$
id_from_phone_number|读|检查规则0 参与码 无效 定为作弊|Mobile_Job_DealMarketActivity
id_generator|读|挂挂乐 砸金蛋|^/mkt1403/get/logincoin
service_push_card_log|读|推送过卡片信息|Mobile_Job_DealServiceRecommend
chat_service_recommend_times|读|服务号向用户推荐信息|
device_android_info|写|设备信息|Mobile_Job_DeviceInfoPersistence
device_info_act1411|读|1408 1410 1411 1412 1501活动 |^/mkt1410/bannerStat$ ^/mkt1410/getInfo
device_info_act_{date(ym)}|读|活动按月分|
device_info_act1505|读|1505|1505活动|
device_info_activity|读|手机设备信息|^/device/setting/banner$
device_ios_info|写| ios设备信息|Mobile_Job_DeviceInfoPersistence
metro_prop_stat|写|地铁沿线房源|Mobile_Job_MetroPropStat
user_device|读||Mobile_Job_UserDeviceInfoPersistence
user_device_history|读|用户设备历史|^/user/currentdevice  Mobile_Job_JavaUserDeviceInfoPersistence

#### haozu(git@gitlab.corp.anjuke.com:_mobile-api/haozu.git)

|表名|读/写|说明|涉及接口
|---|---|---|---|
metro_prop_stat|读|读取地铁线路附近所有房源总数| ^/mobile/(2\.0)/(city\.([a-z][_0-9a-zA-Z]*)) ^/haozu/mobile/(2\.0)/(city\.([a-z][_0-9a-zA-Z]*))