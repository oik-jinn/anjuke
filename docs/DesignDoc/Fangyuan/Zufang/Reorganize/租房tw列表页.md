## 租房tw列表页白皮书


### 说明
```
1.58bd列表和该列表页数据源均一样
2.pv参照http://knowing.corp.anjuke.com/chart/7372,2015年10月18日（周末）pv
```
### 1、页面URL
```
http://m.anjuke.com/{城市简拼}/rent
```

### 2、页面模块

* 2.1、筛选项
* 2.2、房源数据
* 2.4、零少结果推荐
* 2.5、seo
* 2.6、搜索框自动提示

### 3、页面模块列表

|序号|URL|仓库|负责人|功能|
|--- | --- | --- | --- | --- |
|2.4 |http://sh.zu.anjuke.com/v3/ajax/recommend/?type|list       |user-site|||
|2.5 |http://sh.zu.anjuke.com/v3/ajax/listrecommend?rec_type|list|user-site|||


### 4、依赖的外部URL
|序号|配置名|URL|功能|其它|
|---| --- | --- | --- | --- |
|2.2.1|online_status_api|http://site-api.a.ajkdns.com/ajkbroker/|查询该房源是套餐还是精选||
|2.2.2|mss_domain|http://10.10.6.36:8080/mss/multiDictSearch|ifx 地址|分类词服务||
|2.2.3|scws_url|http://10.10.3.46:8999/seg/pkuseg|分割普通词词服务|线上未提sart|
|2.2.4|poi_url|http://10.10.3.46/poi/geocode.php|poi服务|线上未提sart|
|2.2.5|bayes_url|http://10.10.3.46/bayes/guess.php|分类：地理：城市city、区域district|线上未提sart|
|2.2.6|api_community_image_url|http://10.10.6.6:8080/service-internal/rest/communities/getCommCategory?json|获取小区图片信息				||
|2.2.7|api_get_equid_url|http://10.10.6.6:8080/service-exp/rest/exp/getEquid|获取equid信息				||
|2.4|recommend/services的rent_tw_home|http://rcmd.a.ajkdns.com/rent-tw-home/users/579CC868-F5DE-F5AD-17ED-2B6478D63DB8/recommendations/|BI推荐接口||
|2.5|new_api_aifang_friend_link_base_url|http://api.fang.anjuke.com/seo/seofriend/|新的友链新房接口 区域、板块、小区、房价页面友链||
|2.5|lll_seo_partners_aifang_api|http://service.kfs.a.ajkdns.com/app-loupan/rest/FriendLinkApi/getFriendLink|老的友链新房接口 区域、板块、小区、房价页面友链||
|2.6||http://m.anjuke.com/sh/rent/suggest/?q|as&limit|8|搜索自动提示词接口|该接口在app10-052机器上|


### 5、数据库和表
|序号|数据库|表名称|作用|读写|是否独有|计算公式|访问量（单位天）|
|---| --- | --- | --- | --- | --- |--- |--- |
|2.1|rent_db|area|读取区域板块|读|否|948231(pv)*30(条)*0.2(非命中率)|569w|
|2.1|anjuke_db|sw_metros|读取地铁线路信息|读|否|948231(pv)*30(条)*0.2(非命中率)|569w|
|2.1|anjuke_db|sw_metro_stations|读取地铁站点信息|读|否|948231(pv)*30(条)*0.2(非命中率)|569w|
|2.1|rent_db|zf_price|读取城市价格信息|读|否|948231(pv)*0.2(非命中率)|19w|
|2.2|rent_db|area|读取区域板块|读|否|同上|同上|
|2.2|anjuke_db|ajk_attachments_comm|读取小区图片|读|否|948231(pv)*30(条)*0.2(非命中率)|569w|
|2.2|anjuke_db|ajk_communitys|读取小区信息|读|否|948231(pv)*30(条)*0.2(非命中率)|569w|
|2.2|anjuke_db|ajk_commtype||读|否|948231(pv)*30(条)*0.2(非命中率)|569w|
|2.2|anjuke_db|sw_metro_community_distances|获得 站点 定位|读|否|948231(pv)*30(条)*0.2(非命中率)|569w|
|2.2|anjuke_db|ajk_owner_prop|获得免佣标签（该表可删除）|读|否|948231(pv)*30(条)*0.2(非命中率)|569w|
|2.2|hz_dw_stats|hu_rank_all_score_type_rent|获取定价map函数排序规则|读|否|948231(pv)*0.2(非命中率)|18w|
|2.2|rent_db|prop|如果隔离表未读取到房源数据从经纪人表读取|读|否|948231(pv)*30(条)*0.2(非命中率)*0.01(隔离数据丢失率)|5.69w|
|2.2|rent_db|broker_mapping|读取经纪人映射表（隔离表未读取到数据后，补充逻辑才会使用）|读|否|948231(pv)*30(条)*0.2(非命中率)*0.01(隔离数据丢失率)|5.69w|
|2.2|rent_db|prop_images|先读取redis中的的房源图片，隔未读取到数据后，从表获取数据）|读|否|948231(pv)*30(条)*0.1(非命中率)|284w,图片先主要存在redis中|
|2.2|user_prop_sh_db，user_prop_bj_db，user_prop_s0{index}_db|rent_{city_id}|读取房源基本信息     |读写     |是     |948231(pv)*30(条)*0.2(非命中率)|569w|
|2.2|user_prop_sh_db，user_prop_bj_db，user_prop_s0{index}_db|rent_extend_{city_id}|读取房源扩展基本信息     |读写     |是     |948231(pv)*30(条)*0.2(非命中率)|569w|
|2.2|user_prop_sh_db，user_prop_bj_db，user_prop_s0{index}_db|zf_wuba_prop_{city_id}|读取58房源基本信息     |读写     |否     |948231(pv)*30(条)*0.2(非命中率)|569w，假设全部为58房源|

### 6、Memcache&Redis
|名称|类型|名称|地址|申请大小|使用率|是否统一管理|功能|负责人|
|--- | --- | --- |--- | --- | --- | --- | --- | --- |
|经纪人房源图片|redis |zufang_images_master|10.10.8.26:6387|10G    |5.38G|          是|租房隔离图片| 程启明|
|获取城市下个人租房房源总数|memcache |__get_total_rent_num_2015_{city_id}|框架默认memcache|    ||          是|| |
|获取列表页底部友链|memcache |'lll_api_for_partners_cid_'.$city_id.'_tag_'.$type|框架默认memcache|    ||          是|| |
### 7、关联job
|job名称|job管理地址|功能|负责人|
|--- | --- | --- | --- |
|58房源上下架||||
|个人上下架||||
|精选上下架||||
|套餐上下架||||

