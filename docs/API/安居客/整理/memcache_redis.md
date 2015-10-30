## memcache&redis整理

### 总量汇总
|代码仓库|类别|使用总量|
|---|---|---|
|anlife|redis|11G|
||memcache|20|
|user-site|redis|7G|
||memcache|58G|
|anjuke-chat|redis|1G|
||memcache|2.1G|
|anjuke-mobile-api|redis|650MB|
||memcache|36G|
|haozu|redis|27.5G|
||memcache|28G|
|jinpu|redis|500MB|
||memcache|2G|
|总量|redis|48G|
||memcache|146G|

### 现有redis整理
|分类|owner|配置名称|业务说明|地址|使用情况|目前大小|需要大小|是否统一管理|是否持久化|备注|
|---|---|---|---|---|---|---|---|---|---|---|
|anlife|国利|anlife_master/anlife_slave|安居生活公共redis|10.10.8.26:6391|0.1%|1G|保持|Y|Y||
|user_site|晓路|default|默认公共redis|10.10.3.120:1702|0.25%|2G|1G|N|Y||
||晓路| b8_group |缓存b8训练中的文本信息|10.10.3.89:6379|0.2%|1G|100M|N|Y||
||晓路| api_redis_knowing/task |存储knowing的相关信息,主要是事件的次数|10.10.3.101:6464|37%%|1G|保持|Y|Y||
||晓路| cache |缓存设备信息|10.10.3.89:6379|10%|1G|500M|Y|Y||
||晓路| normal_queues |缓存设备信息|10.10.3.89:6379|0.2%|1G|100M|Y|Y||
||晓路| cach backup_queues|缓存任务队列,备份normal_queues|10.10.3.92:6379|0.1%|1G|100M|N|Y||
|anjuke-chat|其春| chat_block_group |缓存被举报用户的信息,缓存关注某个人的数量,缓存反馈用户列表,缓存消息队列|10.10.3.101:6464|37%|1G|保持|Y|Y||
||其春| chat_redis_knowing |缓存knowing数据(已经没用),线上没有外层配置|10.249.7.17:6379|0.14%|1G|可删除|N|Y||
|anjuke-mobile-api|晓路| redis_server |缓存好盘通点击量|10.10.3.23:6380|6%|1G|500MB|N|Y||
||晓路| redis_server_market |缓存活动邀请码,活动的榜单|10.10.3.23:6382|0.5%|1G|100MB|N|Y||
||晓路| exp_redis_server |缓存房源曝光信息|10.10.3.123:6466|0.2%|500MB|50MB|Y|Y||
|haozu|晓路| exp_redis_server |缓存房源曝光信息|10.10.3.101:6466|20%|500MB|保持|Y|Y||
||晓路| prop_redis |缓存房源信息(未做外层覆盖)|10.10.6.31:6380|60%|12G|保持|N|Y||
||晓路| redis |默认redis,缓存推荐房源|10.10.3.4:6379|100%|7G|15G|N|Y||
|jinpu|晓路| exp_redis_server |缓存房源曝光信息(未做外层覆盖|10.10.3.101:6466|20%|500MB|保持|Y|Y||
|anjuke-site/member||member_redis|记用户注册信息|10.10.3.123:6467||||Y|
### redis需要新增
|分类|owner|业务说明|需要大小|
|---|---|---|---|---|---|
|anlife|军毅|安居生活消息相关存储|10G|


### 现有memcache整理
|分类|owner|配置名称|业务说明|地址|使用情况|目前大小|需要大小|是否统一管理|备注|
|---|---|---|---|---|---|---|---|---|---|---|
|user-site|晓路| servers|缓存经纪人写字楼,商铺的数量|10.10.8.125:11213|93.98%|4G|8G|Y||
||| ||10.10.8.125:11214|94.10%|4G|8G|Y||
||| ||10.10.3.107:11236|94.04%|4G|8G|Y||
||| ||10.10.3.170:11216|41.47%|2G|保持|Y||
||| ||mem10-001.ajk.a.ajkdns.com:11211||||Y|10.10.3.13|
||| ||mem10-006.i.ajkdns.com:11216|0.00%|2G|可删除|Y||
||| ||mem10-007.ajk.a.ajkdns.com:11211|78.50%|2G|4G|Y||
||| ||mem10-007.i.ajkdns.com:11217:11217|42.27%|2G|保持|Y||
||| ||mem10-009.ajk.a.ajkdns.com:11211||||Y|10.10.3.10|
||| ||mem10-012.ajk.a.ajkdns.com:11211||||Y|10.10.3.13|
|anjuke-chat|其春| phone_code_frequency |缓存手机号进行手机验证码验证的次数|10.10.3.108:11253|1.98%|100MB|保持|Y||
||| chat_server |缓存微聊黑名单用户,缓存当天的发送的消息数量,发送短信的信息,账户发送制定内容的次数|10.10.3.120:1703|0.1%|2G|保持|N||
|anjuke-mobile-api|晓路| servers|缓存小区下房源数量,经纪人二手房数量等|10.10.8.125:11213|93.98%|4G|8G|Y||
||| ||10.10.8.125:11214|94.10%|4G|8G|Y||
||| ||10.10.3.107:11236|94.04%|4G|8G|Y||
||| ||10.10.3.170:11216|41.47%|2G|保持|Y||
||| ||mem10-001.ajk.a.ajkdns.com:11211||||Y|10.10.3.2|
||| ||mem10-006.i.ajkdns.com:11216|0.0%|2G|可删除|Y||
||| ||mem10-007.ajk.a.ajkdns.com:11211|78.50%|2G|4G|Y||
||| ||mem10-007.i.ajkdns.com:11217|42.27%|2G|保持|Y||
||| ||mem10-009.ajk.a.ajkdns.com:11211||||Y|10.10.3.10|
||| ||mem10-012.ajk.a.ajkdns.com:11211||||Y|10.10.3.13|
|haozu|晓路| servers|缓存首页经纪人推荐,缓存短信等|10.10.3.51:11212|73.94%|6G|8G|Y||
||| ||10.10.3.51:11311|83.12%|4G|8G|Y||
||| orm_server |缓存ORM信息|10.10.3.51:11213|65.62%|4G|6G|Y||
||| ||10.10.3.54:11213|94.41%|2G|6G|Y||
|jinpu|晓路| servers|默认memcache,缓存内链,banner信息等|mem10-001.ajk.a.ajkdns.com:11211||||Y|10.10.3.2|
|anjuke-site/member||servers|默认membercache|10.10.3.54:11511|88.24%|500M||Y||
|anjuke-site/member|王其春|user_register_phone_servers|记录手机号发送验证的次数|10.10.8.125:11226|0.00%|4G||Y|

### memcache需要新增
|分类|owner|业务说明|需要大小|
|---|---|---|---|---|---|
|user-site|晓路|安居客Dao cache|20G|
|anlife|国利|安居生活Dao cache|20G|


