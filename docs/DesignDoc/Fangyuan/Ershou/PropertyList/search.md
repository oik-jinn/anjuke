# 二手房搜索设计

## 使用说明


## 整体流程图
![image](http://a.pic1.ajkimg.com/display/xinfang/99c55d6dfd16a1eff66ecf5931ba523b/800x400c.jpg)

![image](http://a.pic1.ajkimg.com/display/xinfang/c0228216eb6f22474581cd3f1bb3a70d/800x300c.jpg)
## 敏感词处理
Ershou_Core_Sale_Service_KeywordHandlerService

* isSensitiveKeyword($keyword)
	*  bbs_slave.ABW_WORD 合并到mss服务中

参考：[http://infosec.corp.anjuke.com/monitor/index](http://infosec.corp.anjuke.com/monitor/index)

## 无关键词筛选
Ershou_Core_Sale_Service_FilterSearchService

* search($city_id, $filters, $rows, $start, $sort, $facet_fields) 
* Rewriter
	*  Ershou_Core_Sale_Search_PpcRewriter
	*  Ershou_Core_Sale_Search_FilterRewriter
	*  Ershou_Core_Sale_Search_ParamsConvertRewriter
	
## 普通关键词搜索
Ershou_Core_Sale_Service_KeywordSearchService

* search($city_id, $keyword, $filters, $rows, $start, $sort, $facet_fields) 
* Rewriter
	*  Ershou_Core_Sale_Search_PpcRewriter
	*  Ershou_Core_Sale_Search_FilterRewriter
	*  Ershou_Core_Sale_Search_KeywordSplitRewriter
	*  Ershou_Core_Sale_Search_ParamsConvertRewriter

## POI搜索
Ershou_Core_Sale_Service_PoiSearchService

* search($city_id, $keyword, $filters, $rows, $start, $sort, $facet_fields)
* Rewriter
	*  Ershou_Core_Sale_Search_PpcRewriter
	*  Ershou_Core_Sale_Search_FilterRewriter
	*  Ershou_Core_Sale_Search_PoiRewriter
	*  Ershou_Core_Sale_Search_ParamsConvertRewriter 

## 分类词搜索
Ershou_Core_Sale_Service_CategorySearchService

* search($city_id, $keyword, $filters, $rows, $start, $sort, $facet_fields)
* Rewriter
	*  Ershou_Core_Sale_Search_PpcRewriter
	*  Ershou_Core_Sale_Search_CategoryRewriter
	*  Ershou_Core_Sale_Search_FacetRewriter
	*  Ershou_Core_Sale_Search_ParamsConvertRewriter 

## 零结果搜索
Ershou_Core_Sale_Service_ZeroResultHandleService

* handleCateoryZeroResult($params) // 分类词无结果删词逻辑
	* baseSearch.rewriter
		* Ershou_Core_Sale_Search_RemoveParamsRewriter
		* Ershou_Core_Sale_Search_FacetRewriter
		* Ershou_Core_Sale_Search_ParamsConvertRewriter
* handleKeywordZeroResult($params) // 普通关键词无结果进行Poi搜索
	* Ershou_Core_Sale_Service_PoiSearchService 
* handleZeroResultRecommend($params) // 无结果推荐（默认列表页）
	* Ershou_Core_Sale_Service_FilterSearchService

## 参数过滤
Ershou_Core_Sale_Search_ParamsFilteredRewriter

* 支持的筛选项
	* 区域•版块•售价•面积•房型•房源特色
	* 建造年代•房屋类型•楼层
	* 个人房源•地铁房•学区房
	* 小区ID•学校ID•地铁站点ID

* 支持的排序
	* 默认
	* 面积（大＝>小，小=>大）
	* 总价（高＝>低，低=>高）
	* 最新

## 竞价定价显示
Ershou_Core_Sale_Search_PpcRewriter // 获取竞价定价参数

* rewrite($params) 
* 列表页
  * 首页-只定价
  * 筛选-竞价+定价
* 搜索页
  * 竞价+定价

## 分类词处理
Ershou_Core_Sale_Search_CategoryRewriter 

* rewrite($params) 
* getKeywordCategory($keyword) // 调用分类服务
	* MSS服务
* formatKeywordCategory($params) // 分类词格式化
	* 关键词冲突时的取舍
	![image](http://a.pic1.ajkimg.com/display/xinfang/9b115acf6825c1056ca938f63d704718/800x400c.jpg)
	* 小区、学校、地铁站点有筛选条件时，以筛选条件优先
	
## 标点处理
Ershou_Core_Sale_Search_PoiRewriter

* rewrite($params) 
* getKeywordPoi($keyword) // 获取标点
	* getClassifierFromBayes 贝叶斯分词获取地标词 ('landmark','address','city','district','block','community')
	* getPoisFromGoogle 通过google接口获取标点
* formatKeywordPoi($params)

## 关键字处理
Ershou_Core_Sale_Search_KeywordSplitRewriter

* rewrite($params) 
* getSplitWords($keyword) // 分词
* setKeywordBoost() // 设置权重

## 筛选条件处理
Ershou_Core_Sale_Search_FilterRewriter // 供列表页和一些不需要过滤空结果筛选项的特殊搜索使用

* rewrite($params) 
* 默认筛选项
	* 区域（如果指定了区域，同时显示该区域下的板块）
	* 售价
	* 面积
	* 房型
	* 建造年代
	* 房屋类型
	* 楼层
	
## 聚合条件处理
Ershou_Core_Sale_Search_FacetRewriter

* rewrite($params) 
* 基本筛选（区域、版块、售价、面积、户型）+更多筛选（建造年代、房屋类型、楼层）
* 小区（售价、户型、特色）无更多筛选
* 搜索词中包含区域，不显示区域筛选
* 搜索词中包含地标（版块、地铁站、公交站、学校、医院、公园、景点），不显示区域版块
* 搜索词中包含户型，不显示户型筛选
* 搜索词中包含房屋类型，房屋类型筛选只显示全部和关键词

## 传入参数和搜索参数转换
Ershou_Core_Sale_Search_ParamsConvertRewriter

* rewrite($params) 
* convertQueryParams($params)
	* 生成q&fq条件 
* convertFacetParams($params)
	* 生成facet.query、facet.field条件 
* convertGeoParams($params)
	* 生成地理坐标查询条件 pt&d
* convertSortParams($params)
	* 生成排序条件（包括rank，滚动逻辑） 
* convertPageParams($params)
	* 生成分页条件 

## 删词处理
Ershou_Core_Sale_Search_RemoveParamsRewriter

![image](http://a.pic1.ajkimg.com/display/xinfang/9e2f374ba47a22581ee12ac4bf6d4f7a/800x800c.jpg)

## 搜索接口
Ershou_Core_Sale_Search_SolrRequestProcessor

* request($params) 
* requestAuction($params)
* requestPricing($params)

## 结果格式化
Ershou_Core_Sale_Search_DataFormater

* format($data, $params)
* return
	* results
		* id
		* hpdata
	* facets
		* facet.query
		* facet.field 
	* search_type
		* CommonOne
		* CommonMulti
		* Area
		* School
	* params

## rewriter参数定义

* 城市ID（city_id）
* 搜索词（keyword）
* 筛选项（filters）
	* 区域 areacode
	* 版块 block
	* 售价 price
	* 面积 area
	* 房型 room_num
	* 建造年代 house_age
	* 房屋类型 use_type
	* 楼层 floor_index
	* 房源特色 besttages
	* 小区 comm_id
	* 个人房源 tags
	* 地铁房 is_metro_prop
	* 学区房 is_school_prop
* 返回结果数（rows）
* 翻页（page）
* 排序方式（sort）具体说明
* 需要做facet的字段（facet_fields）
	* 区域 
	* 售价
	* 面积
	* 房型
	* 建造年代
	* 房屋类型
	* 楼层
	* 房源特色
* hpscroll
* isauction
* 分类结果（category）
	* commid = array(1,2,3)
	* bus_id
	* school_info_ids
* 关键词权重设置 (kwsearchfields)
* 坐标 (poi)
	* lat
	* lng
	* distance
	* maptype
* solr查询参数(solr_params)
	* keyword
	* keyword_fmt
	* kwsearchfields
	* facet
	* filterfields
	* querytype
	* bosstfunc
	* queryfields
	* query
	* facet
	* sortstring
	* rows
	* page
	* geo
	* pricing_sortstring
	* auction_sortstring
 
## 参考文档
* [http://wiki.corp.anjuke.com/Anjuke-Sale](http://wiki.corp.anjuke.com/Anjuke-Sale)
* [搜索逻辑流程图](http://git.corp.anjuke.com/_user_site/doc/raw/master/fangyuan/ershou/ListingSearchLogic.png)
* [http://shanghai.anjuke.com/sale/](http://shanghai.anjuke.com/sale/)
* [http://git.corp.anjuke.com/_user_site/doc/browse/master/mobileWeb/devDoc/detailDesign/touchWeb/listService%E9%9C%80%E6%B1%82%E5%8A%9F%E8%83%BD%E6%95%B4%E7%90%86.md](http://git.corp.anjuke.com/_user_site/doc/browse/master/mobileWeb/devDoc/detailDesign/touchWeb/listService%E9%9C%80%E6%B1%82%E5%8A%9F%E8%83%BD%E6%95%B4%E7%90%86.md)