## app二手房列表页白皮书
### 1. 页面URL
```
app无,启动安居客app,点击二手房即进入二手房列表页
```

### 2. 页面说明
```
此页面主要进行二手房的搜索和筛选功能
```
    
### 3. 页面模块列表
|编号|URL|仓库|机器|负责人|功能|
| --- |--- | --- | --- | --- | --- |
|3.1|http://api.anjuke.com/anjuke/4.0/property/list|user-site/app-mobile-api|xapp10-196,app10-199|王其春|二手房搜索筛选|
|3.2 |http://api.anjuke.com/mobile/v5/recommend/sale/list/history|user-site/app-mobile-api|xapp10-196,app10-199|王其春|app二手房首页推荐,(二手房列表页如果用户没有筛选条件,就调用推荐接口)|
### 4. 依赖的内部服务(指的是其它仓库的服务)
|服务名|仓库地址|负责人(或部门)|功能|
| --- | --- | --- | --- |
| Biz_Ershou_Property_Search_SearchBiz | user-site/app-biz|石兆媛|二手房搜索和筛选(solr)|
|Ershou_Core_Service_PropertyInfoService | user-site/app-ershou-core |石兆媛|获取安居客抓取房源的经纪人信息|
|Ershou_Core_Service_DictService|user-site/app-ershou-core |石兆媛|根据装修id获得装修类型|
|Broker_Core_Broker_Service_BrokerBaseService| user-site/app-broker-core |石兆媛|获取经纪人信息|
|Ershou_Core_Service_PropertyService|user-site/app-ershou-core |石兆媛| 获取anjuke房源图片信息|
|Ershou_Core_Property_Service_UserPropertyService|user-site/app-ershou-core |石兆媛| 获取房源基本信息|
|Ershou_Core_Property_Service_UserPropertyWuBaService|user-site/app-ershou-core |石兆媛|获取58房源的图片信息|
### 5. 依赖的外部URL
##### 5.1 URL-3.2 的依赖
|配置名|URL|功能|其它|
| --- | --- | --- | --- |
|config['app-ershou-instance']['list_recommend']|'http://rcmd.a.ajkdns.com/sale-app-list-history/recommend|app 二手房列表页推荐BI接口|BI部门提供|
### 6. 数据库和表:
|数据库|地址|表名称|作用|读写|是否独有|
| --- | --- | --- | --- | --- | --- |
### 7. Memcache&Redis
|名称|类型|地址|申请大小|使用率|是否统一管理|功能|负责人|
|--- | --- | --- | --- | --- | --- | --- | --- |
### 8. 关联job
|job名称|job管理地址|功能|负责人|
|--- | --- | --- | --- |
