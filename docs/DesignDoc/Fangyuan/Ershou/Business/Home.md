# 安居客首页白皮书

## 页面url

```
http://{pinying}.anjuke.com/
```
    
### 页面模块列表
|URL|仓库|机器|负责人|功能|
|--- | --- | --- | --- | --- |
|http://{pinyin} . xzl.anjuke.com ./api/recomend/?cid={$city_id}&limit=18&tamp=. time()| |jingpu|首页商业地产数据|
|http://{pinyin} . zu.anjuke.com . /ajax/homepage/?cid={$city_id}&tamp= . time()| |haozu|首页租房数据|
|http://www.anjuke.com/getNavInfo/?cityId=128| user-site| |石兆媛| 获取导航|


### 依赖的内部服务(指的是其它仓库的服务)

|服务名|仓库地址|负责人(或部门)|功能|
| --- | --- | --- | --- |


### 依赖的外部URL
|配置名|配置文件名|URL|功能|其它|
| --- | --- | --- | --- |---|
|xinfang_jixact_open|app-ershou-core/config/multicity.php||新房极限抢房节开通城市||
|xinfang_act_time|app-ershou-core/config/multicity.php||新房极限抢房节开通时间||
|anjuke_base_domain|app-ershou-web/config/common.php||base_domain配置||
|home_city_type|app-ershou-core/config/multicity.php||城市首页类型配置||
|jinpu_open_city|app-ershou-core/config/multicity.php||金铺开通城市配置||
|chatdaren_city_is_on|app-ershou-core/config/multicity.php||微聊达人开通城市配置||
|replace_chatdaren_city_is_on|app-ershou-core/config/multicity.php||替换微聊达人开通城市配置||
||app-user-common/config/navigation.php||导航配置||

### 数据库和表:
|数据库|地址|表名称|作用|读写|是否独有|
| --- | --- | --- | --- | --- | --- |


### Memcache&Redis
|名称|类型|地址|IP/Port|申请大小|已使用|使用率|是否统一管理|功能|负责人|
|--- | --- | --- | --- | --- | --- | --- | --- |---|---|---|
|user_home_page|memcache|http://mc.corp.anjuke.com/memcacheds-19|10.10.3.106:11228 10.10.3.107:11228 | 8G| 0.02G| 0.25% | 是| 缓存首PC页模块数据| 石兆媛

### 关联job
|job名称|job管理地址|功能|负责人|
|--- | --- | --- | --- |
|Ershou_Job_Home_NoticUpdateSource|http://drone.corp.anjuke.com/scheduler/job/163/view |首页模块更新通知（半小时一次）定时 |石兆媛
|Ershou_Job_Home_UpdateDataSource|http://drone.corp.anjuke.com/daemon/job/66/view|首页模块更新job （常驻）|石兆媛
|Ershou_Job_Home_UpdateDataSourceForXinFang|http://drone.corp.anjuke.com/daemon/job/239/view|首页新房模块更新job（常驻）|石兆媛

