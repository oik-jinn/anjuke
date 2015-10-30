## 租房房源上下架job依赖服务整理
### 页面URL
```
job无
```

### 说明
```
1. job对使用表的延迟不敏感
2. 表的访问量从zf_spread_queue_00_201509，总行数：645247。8月份存在大量回插数据，故排除。访问量：645247*10/30
```

### 页面模块列表

### 依赖的内部服务(指的是其它仓库的服务)

|上下架类型 |服务名 |仓库地址 |负责人(或部门) | 功能|
|--- |--- |--- |--- |--- |
|精选 |Biz_Broker_BrokerRentBiz | user-site/app-biz|石兆媛|获取经纪人信息|
|精选 |Community_Core_Comm_Service_CommunityService | user-site/app-community-core |石兆媛|获取小区信息|
|精选 |Community_Core_City_Service_AreaBlockService|user-site/app-community-core |石兆媛|获取区域板块信息|
|精选 |Biz_Community_Comm_CommunityMetroBiz| user-site/ ||获取地铁信息|
|精选 |Broker_Core_Broker_Service_BrokerWeidianService| user-site/ ||获取经纪人微信二维码|
|58房源 |Broker_Core_Broker_Service_BrokerWeidianService| user-site/ ||获取经纪人微信二维码|
### 依赖的外部URL

|上下架类型|配置名|URL|功能|其它 |
|--- | --- | --- | --- | --- |
|精选　　　 	|hz_property_servers    |http://10.20.3.82:8080     |获取过期房源信息				|该配置线上未覆盖|
|精选　　　　	|api_community_image_url|http://10.10.6.6:8080/service-internal/rest/communities/getCommCategory?json|获取小区图片信息				||
|58房源　　  |pin_yin                |tcp://10.10.6.219:50000                                                     |获取区域板块拼音				||
|58房源，个人	|pku_seg_url            |http://10.10.6.219:8999/seg/pkuseg?text=                                    |对小区名，房源标题，区域，板块分词||
|套餐　　　	|pkuseg_url             |http://chseg.a.ajkdns.com/seg/pkuseg?text=                                  |对小区名，房源标题，区域，板块分词||

### 数据库和表:
|上下架类型 |数据库	|地址  |表名称 |作用 |读写   |是否独有|计算公式|访问量（单位天）|
|--- | --- | --- | ---  | --- | ---  | --- |--- |--- |
| 精选，个人，套餐		|  rent_db   												|     | prop    |读取房源信息     |读     |否     |单表×10个表/30天|21w|
| 精选,套餐      		|  rent_db   												|     | users_search    |读取经纪人信息     |读     |否     |单表×10个表/30天/30天|21w|
| 精选　　　      		|  anjuke_db   												|     | cst_company    |读取经纪人公司信息     |读     |否     |单表×10个表/30天|21w|
| 精选　　　     			|  anjuke_db  												|     | cst_broker_company    |读取经纪人门店信息     |读     |否     |单表×10个表/30天|21w|
| 精选　　　             	|  anjuke_db   												|     | ajk_attachments_comm    |读取小区图     |读     |否     |单表×10个表/30天|21w|
| 精选，58房源，个人，套餐	|  anjuke_db   												|     | map_communities_baidu    |获取经纬度信息     |读     |否     |单表×10个表/30天|21w|
| 精选，套餐　　　		|  rent_db   												|     | prop_images    |读取房源图片信息     	|读     |否     |单表×10个表/30天|21w|
| 58房源　　				|  user_prop_sh_db，user_prop_bj_db，user_prop_s0{index}_db	|     | zf_wuba_prop_{city_id}|读取房源基本信息     |读     |否     |单表×10个表/30天|21w|
| 58房源　　				|  user_prop_sh_db，user_prop_bj_db，user_prop_s0{index}_db	|     | zf_wuba_prop_{city_id}|读取房源基本信息     |读     |否     |单表×10个表/30天|21w|
| 58房源　　				|  anjuke_db												|     | ajk_wuba_commtype_relation|读取虚拟小区信息     |读     |否     |单表×10个表/30天|21w|
| 58房源，个人，精选　　	|  anjuke_db												|     | ajk_communitys|读取小区信息     |读     |否     |单表×10个表/30天|21w|
| 58房源　　				|  anjuke_db												|     | ajk_commtype|读取区域板块信息     |读     |否     |单表×10个表/30天|21w|
| 58房源，个人，套餐　　	|  anjuke_db												|     | sw_metro_community_distances|读取小区周边地铁信息|读     |否     |单表×10个表/30天|21w|
| 58房源，个人,套餐　　	|  anjuke_db												|     | sw_metro_stations|读取小区周边地铁站信息     |读     |否     |单表×10个表/30天|21w|
| 58房源　　				|  anjuke_db												|     | map_communities_soso_pano|读取小区街景信息     |读     |否     |单表×10个表/30天|21w|
| 58房源　　				|  queue_db													|     | zf_wuba_event_queue_00_201501|读取队列消息     |读     |否     |单表×10个表/30天|21w|
| 个人房源　				|  rent_db													|     | prop_search|读取房源是否在线     |读     |否     |单表×10个表/30天|21w|
| 个人房源　				|  rent_db													|     | prop_lucene_updated|上下架消息队列表     |读     |否     |单表×10个表/30天|21w|
| 个人房源，套餐　		|  hz_dw_stats												|     | hu_rank_rent_price_end|个人rank计算     |读     |否     |单表×10个表/30天|21w|
| 个人房源　				|  hz_dw_stats												|     | hu_rank_all_score_rank_rent_list|个人rank计算     |读     |否     |单表×10个表/30天|21w|
| 个人房源　				|  hz_dw_stats												|     | hu_rank_all_score_rent_p_list|个人rank计算     |读     |否     |单表×10个表/30天|21w|
| 个人房源　				|  hz_dw_stats|     | hu_rank_all_score_type_rent|个人rank计算     |读     |否     |单表×10个表/30天|21w|
| 个人房源，套餐　		|  hz_dw_stats|     | solly_rank_all_score_rent_b_list|个人rank计算     |读     |否     |单表×10个表/30天|21w|
| 个人房源　				|  hz_dw_stats|     | solly_rank_all_score_type_rent|个人rank计算     |读     |否     |单表×10个表/30天|21w|
| 个人房源　				|  hz_dw_stats|     | solly_rank_all_score_type_rent|个人rank计算     |读     |否     |单表×10个表/30天|21w|
| 个人房源　				|  hz_dw_stats|     | zf_prop_paid|个人rank计算     |读     |否     |单表×10个表/30天|21w|
| 个人房源　				|  rent_db|     | zf_personal_promo|个人rank计算     |读     |否     |单表×10个表/30天|21w|
| 个人房源　				|  rent_db|     | zf_personal_prop_promo|根据proid获取当前的付费记录     |读     |否     |单表×10个表/30天|21w|
| 个人房源　				|  rent_db|     | zf_personal_prop_pkg|获取个人房源套餐二次推广是否也无效了     |读     |否     |单表×10个表/30天|21w|
| 个人房源				|  rent_db|     | prop_report| 举报得分     |读     |否     |单表×10个表/30天|21w|
| 个人房源，套餐　		|  rent_db|     | zf_price| 用于个人计算rank得分     |读     |否     |单表×10个表/30天|21w|
| 个人房源　				|  rent_db|     | area| 获取区域板块信息     |读     |否     |单表×10个表/30天|21w|
| 个人房源　				|  rent_db|     | traffic_line_station| 获取bus信息     |读     |否     |单表×10个表/30天|21w|
| 个人房源　				|  rent_db|     | traffic_station_community| 获取bus信息     |读     |否     |单表×10个表/30天|21w|
| 个人房源,套餐　			|  anjuke_db|     | map_communities|读取谷歌坐标     |读     |否     |单表×10个表/30天|21w|
| 精选和套餐　			|  queue_db|     | zf_spread_queue_{进程号}_{当前年月}|读取消息队列     |读     |否     |单表×10个表/30天|21w|
| 套餐　					|  queue_db|     | zf_spread_{进程号}_{当前年月}|读取消息队列     |读     |否     |单表×10个表/30天|21w|
| 套餐　					|  anjuke_db|     | ajk_commextend|读取小区开展信息     |读     |否     |单表×10个表/30天|21w|
| 套餐　					|  anjuke_db|     | ajk_merge_comm|判断是否合并小区     |读     |否     |单表×10个表/30天|21w|
| 套餐　					|  anjuke_db|     | ajk_usetype|  获取物业类型     |读     |否     |单表×10个表/30天|21w|
| 套餐　					|  rent_db|     | broker_account|  经纪人帐务信息     |读     |否     |单表×10个表/30天|21w|
| 套餐　					|  rent_db|     | users|  经纪人信息     |读     |否     |单表×10个表/30天|21w|
| 套餐　					|  rent_db|     | zf_landmark_community|  地标信息     |读     |否     |单表×10个表/30天|21w|
| 套餐　					|  rent_db|     | promotion_prop_planning|  获取推广计划     |读     |否     |单表×10个表/30天|21w|
| 套餐　					|  anjuke_db|     | ajk_attachments_comm|  小区图片     |读     |否     |单表×10个表/30天|21w|
| 套餐　					|  hz_dw_stats|     | hu_rank_all_score_rent_b_list|  读取rank     |读     |否     |单表×10个表/30天|21w|
| 套餐　					|  anjuke_db|     | ajk_owner_prop|  获取免佣房源id    |读     |否     |单表×10个表/30天|21w|
| 套餐　					|   hz_dw_stats|     | solly_hz_rank_broker_rate|  获取免佣房源id    |读     |否     |单表×10个表/30天|21w|
| 经纪人房源更新　		|   queue_db|     | zf_event_queue_{进程号}_{当前年月}|读取消息队列     |读     |否     |单表×10个表/30天|21w|

### Memcache&Redis
|上下架类型 |名称 |类型  |地址 |申请大小|使用率|是否统一管理|功能|负责人|
|--- |---  | ---  | --- | ---   | --- | --- | --- | --- |
|个人房源  |zufang_images_master|redis |10.10.8.26:6387|10G    |5.38G|          是|租房隔离图片| 程启明|
|套餐  |servers|memcache |http://gitlab.corp.anjuke.com/_site/docs/blob/master/DesignDoc/Config/HaozuSite/Cache/Memcache.md|43G    |37G|          是|租房房源信息缓存| 程启明|

