## app二手房列表页白皮书
### 页面URL
```
app无
```

### 页面说明
```
app二手房筛选页,搜索页,小区列表页,经纪人二手房列表页,均是二手房列表页,主要进行二手房的搜索和筛选功能
```
    
### 页面模块列表
|URL|仓库|机器|负责人|功能|
|--- | --- | --- | --- | --- |
|http://api.anjuke.com/anjuke/4.0/property/list|user-site/app-mobile-api|xapp10-196,app10-199|王其春|二手房搜索筛选|

### 依赖的内部服务(指的是其它仓库的服务)

|服务名|仓库地址|负责人(或部门)|功能|
| --- | --- | --- | --- |
| Biz_Ershou_Property_Search_SearchBiz | user-site/app-biz|石兆媛|二手房搜索和筛选(solr)|
|Ershou_Core_Service_PropertyInfoService | user-site/app-ershou-core |石兆媛|获取安居客抓取房源的经纪人信息|
|Ershou_Core_Service_DictService|user-site/app-ershou-core |石兆媛|根据装修id获得装修类型|
|Broker_Core_Broker_Service_BrokerBaseService| user-site/app-broker-core |石兆媛|获取经纪人信息|
|Ershou_Core_Service_PropertyService|user-site/app-ershou-core |石兆媛| 获取anjuke房源图片信息|
|Ershou_Core_Property_Service_UserPropertyService|user-site/app-ershou-core |石兆媛| 获取房源基本信息|
|Ershou_Core_Property_Service_UserPropertyWuBaService|user-site/app-ershou-core |石兆媛|获取58房源的图片信息|
### 依赖的外部URL
|配置名|URL|功能|其它|
| --- | --- | --- | --- |
### 数据库和表:
|数据库|地址|表名称|作用|读写|是否独有|
| --- | --- | --- | --- | --- | --- |
### Memcache&Redis
|名称|类型|地址|申请大小|使用率|是否统一管理|功能|负责人|
|--- | --- | --- | --- | --- | --- | --- | --- |
### 关联job
|job名称|job管理地址|功能|负责人|
|--- | --- | --- | --- |