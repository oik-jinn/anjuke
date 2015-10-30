### 1.项目背景
##### 由于app二手房列表页在老的仓库(anjuke-mobile-api)中,和现有的pc,touch的二手房列表页搜索逻辑不在同一仓库中,导致维护成本增加,所以需要迁移. 
### 2.项目设计
#### 2.1 APP二手房列表页URL
##### http://api.anjuke.com/anjuke/4.0/property/list
#### 2.2 参数
#### 2.2.1 APP二手房列表页输入参数
##### 以下的参数是除去[公共参数](http://gitlab.corp.anjuke.com/Beckyxu/api/blob/master/common/app%E5%85%AC%E5%85%B1%E5%8F%82%E6%95%B0%E8%AF%B4%E6%98%8E.md)之外的参数
| 参数名     | 参数类型 | 是否必须   |意义 |
| :-------  | :---: | :---: | :-----| 
|map_type|string|NO|地图类型,只有google和baidu两种值,默认google|
|broker_id|int|NO|经纪人id|
|city_id|int|YES|城市id|
|comm_id|int|NO|小区id|
|area_id|int|NO|区域id|
|block_id|int|NO|板块id|
|page|int|NO|第几页|
|page_size|int|NO|每页的数据量,最多不超过100,如果超过,就设置成100,默认为25|
|min_price|int|NO|如果传递了max_price,则默认为1|
|max_price|int|NO|如果传递了min_price,默认为10亿,如果超过10亿,那么将设为10亿|
|q|string|NO|关键字|
|min_area|int|NO|最小面积,如果只传递了max_area,默认值是1|
|max_area|int|NO|最大面积,如果只传递了min_area,默认值是10亿|
|use_type|int|NO|房屋类型,1代表公寓,2代表别墅,3代表新里洋房,4代表其它,5老公房|
|room_num|int|NO|卧室的个数|
|min_age|int|NO|当只传递max_age时,默认是1|
|max_age|int|NO|当只传递min_age时,默认是1000|
|lat|double|NO|纬度,在按照距离搜索的时候使用|
|lng|double|NO|经度,在按照距离搜索的时候使用|
|distance|int|NO|附近搜索的距离|
|orderby|int|NO|排序规则,默认为1,1 代表默认排序,2代表面积从大到小排序,3表示面积从小到大排序,4代表价格从低到高,5代表价格从高到低|
|is_hmodels|int|NO|是否返回筛选户型,只有0,1两个值|
#### 2.2.2 APP二手房列表页输出参数
##### 成功时返回参数说明
| 参数名     | 参数类型 | 意义   |
| :-------  | :---: | :--- |
| status    | string |状态,成功状态是”ok”|
| total     | string   | 一共有多少数据(并不是返回多少数据) |
| properties | Array of Object    |  存储房源的信息  |
|hmodels|Array of Object| 存储房屋筛选类型的的信息|

##### 以下是hmodels包含的元素
| 参数名     | 参数类型 | 意义   |
| :-------  | :---: | :--- |
| hmcond| string|房屋中室的个数,0代表不限制,其它的数字代表室的个数|
|hmid|string|这种类型对应的id|
|hmodels|string|这种类型对应的中文名,0对应不限,1代表一室,2代表二室,3代表三室|

##### 以下是properties包含的元素
| 参数名     | 参数类型 | 意义   |
| :-------  | :---: | :--- |
| id| string | 房源id:如 “17071748” |
|isauction|string|是否竞价|
|name|string|标题|
|price|string|房价,单位是万,比如返回390,说明房价是390万|
|room_num|string|房间的个数,相当于几室几厅中的室的个数|
|property_type|string|房源的类型:1:定价,2:竞价,3:套餐|
|city_id|string|城市id|
|hall_num|string|厅的个数|
|community_name|string|小区名|
|address|string|房子地址|
|area_num|string|房子面积|
|default_photo|string|默认图片|
|avg_price|string|每平方米价格,单位也是万,比如返回0.500,说明房价是5000|
|use_type|string|房屋类型,比如”公寓”,"毛坯房","四合院"|
|area|string|区域名|
|block|string|板块名|
|toilet_num|string|卫生间个数|
|floor_total|string|所在的建筑物一共几层
|floor_num|stirng|在第几层|
|fitment_name|string|装修类型:毛坯,精装修|
|house_age|string|房子所建的年代,比如1997
|house_ori|string|房屋朝向
|post_date|string|房源上架日期如:1431171009
|CommitionType|string|提交的类型,个人还是经纪人
|distance|string|推荐的房源和传入的房源之间的距离，默认空,单位是千米
|source_type|string|房屋类型:1:安居客经纪人,3:安居客抓取,5:58经纪人,6:58抓取房源
|tags|Array of string|房屋标签:比如地铁房,学区房,满二唯一
|traker|string|（字段保留 值留空）
||||
|Community|Object|小区详细信息
|id|string|小区id
|name|string|小区名称
|block_name|string|小区所在板块
|address|string|小区地址
|price|string|小区均价
|lat|string|小区位置的纬度
|lng|string|小区位置的经度
||||
|broker|object|经纪人详细信息
|id|string|经纪人id
|name|string|经纪人名字
|company|string|经纪人所在公司
|photo|string|经纪人图片
|mobile|string|经纪人手机号
|is_grab|string|是否是抓取房源,如果是,那么经纪人图片不存在,
||||
|room_photos|Array of object|房屋的图片信息
|desc|string|图片描述
|url|string|图片的地址
||||
|model_photos|Array of object|户型图片信息
|desc|stirng|图片描述
|url|string|图片地址
##### 失败时返回参数说明
| 参数名     | 参数类型 | 意义   |
| :-------  | :---: | :--- |
| status    | string |调用失败,值是“error”
|properties|Array of object|此时为空
| | | |
|error|object|错误的详细信息
|code|string|错误码
|message|string|错误信息,比如”输入参数类型不正确”
#### 2.3 api使用分类
* 筛选
    * 区域筛选
    * 板块筛选
    * 售价
    * 房型
    * 面积
    * 房龄
    * 类型
* 搜索
    * 关键字搜索
    * 小区搜索
    * 按照距离搜索(附近)
    * 经纪人搜索

#### 2.4 项目流程图
##### ![image](http://gitlab.corp.anjuke.com/Beckyxu/api/raw/master/ershoufang/app%E4%BA%8C%E6%89%8B%E6%88%BF%E5%88%97%E8%A1%A8%E9%A1%B5%E8%BF%81%E7%A7%BB%E6%96%B0%E9%80%BB%E8%BE%91.png)
#### 2.5 调用外部service及bll说明
| 服务名     | 作用 |
| :-------  | :---: |
|Ershou_Core_Property_Service_UserPropertyService    |获取房源基本信息|
|Ershou_Core_Property_Service_UserPropertyImageService|获取房源默认图像信息|
|Ershou_Core_Property_Bll_DefaultImageBll|获取房源默认图像|
|Ershou_Core_Service_PropertyService|获取房源基本信息|
|Ershou_Core_Property_Service_UserPropertySyncService|用于房源基本信息之间的转换|
|Ershou_Core_Service_DictService|根据装修类型id得到装修类型|
|Ershou_Core_Property_Service_UserPropertyImageService|得到房源的图片列表|
|Ershou_Core_Property_Service_UserPropertyWuBaService|获取58房源的图片列表|
|Broker_Core_Broker_Service_BrokerBaseService|获取经纪人的基本信息|
|Community_Core_Comm_Service_AjkWubaCommTypeRelationService|58房源小区信息|
|Community_Core_Comm_Bll_CommunityBll|获取小区的基本信息|

#### 2.6 solr参数和传入参数的对应关系
##### solr的搜索参数
| 入参     | solr查询参数 |
| :-------  | :--- |
|map_type|poi_params->map_type|
|lat|poi_params->lat|
|lng|poi_params->lng|
|distance|poi_params->distance|
|broker_id|filter_params['broker_id']|
|city_id|filter_params['city_id'],city_id|
|comm_id|filter_params['comm_id']|
|area_id|filter_params['area_id']|
|block_id|filter_params['broker_id']|
|min_price|filter_params['price']['lower']|
|max_price|filter_params['price']['upper']|
|min_area|filter_params['area']['lower']|
|max_area|filter_params['area']['upper']|
|use_type|filter_params['user_type']|
|room_num|filter_params['room_num']|
|min_age|filter_params['house_age']['lower']|
|max_age|filter_params['house_age']['upper']|
|orderby|sort|
|q|keyword|
|page|page|
|page_size|rows|
