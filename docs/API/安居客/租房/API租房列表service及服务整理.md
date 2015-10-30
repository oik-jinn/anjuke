### 1.solr搜索
|目录|类名|方法|作用|
|:------|:---:|:---:|:---|
|app-biz|Biz_Zufang_Rent_SearchBiz|getSearchData|获取solr租房数据|
### 2.房源信息
|目录|类名|方法|作用|
|:------|:---:|:---:|:---|
|app-zufang-core|Zufang_Core_Const_PropTag|getPropTag|根据房源类型获取房源标识|
|app-zufang-core|Zufang_Core_Rent_Dao_RentDao|findById|通过房源ID获取房源基本信息|
|app-zufang-core|Zufang_Core_Rent_Service_RentDictService|getPayTypeInfo|获得付款类型|
|app-zufang-core|Zufang_Core_Rent_Service_RentDictService|getRentTypeInfo|获取合租类型|
|app-zufang-core|Zufang_Core_Rent_Service_RentDictService|getFitmentTypeInfo|获得装修类型|
|app-zufang-core|Zufang_Core_Rent_Service_RentDictService|getHouseTypeName|房屋类型|
|app-zufang-core|Zufang_Core_Rent_Service_RentDictService|getTowardName|获取房源朝向|
|app-zufang-core|Zufang_Core_Crawler_Service_RentImageService|getDefaultRentImage|获取房源默认图片|
|app-zufang-core|Zufang_Core_Util_ImageUtil|getHjImageUrl|获得租房新图片url|
|app-zufang-core|Zufang_Core_Service_RentService|getImages|获取租房房源图片|
### 3.小区信息
|目录|类名|方法|作用|
|:------|:---:|:---:|:---|
|app-community-core|Community_Core_Comm_Service_CommunityService|getCommunityInfo|获取在线小区基本信息|
|app-user-common|User_Common_Const_MultiCity| |获取城市ID配置|
### 4.地铁信息
|目录|类名|方法|作用|
|:------|:---:|:---:|:---|
|app-user-core|User_Core_Service_MetroTagService|getMetroTags|根据小区信息查询周围地铁线路信息|
|app-user-core|User_Core_Service_Spatial|getDistance|获取距离信息|
### 5.区域版块信息
|目录|类名|方法|作用|
|:------|:---:|:---:|:---|
|app-zufang-core|Zufang_Core_Rent_Service_RentAreaService|getAreaById|获取区域/版块信息|

