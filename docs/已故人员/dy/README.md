```
由于我的水平有限，写出的文档难免有漏洞出现，小伙伴们发现问题以后请及时更正文档，不要让错误的代码或者遗弃已久的产品逻辑混淆视听。
```

# 1. SEO

```
SEO是为了更好的利用搜索引擎蜘蛛爬取算法来提高自己网站的排名，从而提高访问量和流量，这个过程是免费的；而SEM则是市场部投钱投放广告，一般而言SEM效果更直接见效快和周期短，而SEO则是个持续优化的过程。
```

## 1.1 在新做一个页面时需要注意SEO的点

```
    SEO禁忌
        ├── 同一个页面有多个可以访问的URL
            ├── 保留其中的一个url,其他url做301永久重定向跳转

        ├── 301与302乱用(假设需要从A页面跳转到B页面)
            ├── 301永久重定向，可以帮A页面的权重传递到B页面，搜索引擎下次会直接将访问A的地址指向到B，一定不要乱用301重定向到404页面
            ├── 302临时跳转，不会传递A页面的权重到B页面,搜索引擎下次访问A的地址不变
            
        ├── 过多的采用带?的url，而不是使用伪静态url
            ├── 帮带?的url用伪静态方式处理(尽可能这么做)

    TKD优化
        ├── Title: 一个页面的title信息，这个会直接告诉爬出我的这个页面主题是什么，是对整个页面的一个高度概括
        ├── Keyword: 关键词描述，引导搜索引擎对抓取的页面进行大分类，方便检索信息
        ├── Description: 尽可能详细的描述这个页面的功能，方便用户检索信息

    Meta多平台适配
        ├── 在meta属性里添加对Touch、PC、PAD的支持，这个要看产品的需求，一般情况下重要的页面(如列表页、单页)都是要做平台适配的；这个可以解决一个手机端用户访问我们PC时我们可以引导用户访问体验更好的Touch页面

    CSS JS
        ├── css和js尽量引用外部文件，不要在Head里写过多js和css源代码、及时真要在模板phtml里写js和CSS源代码，最好也放到模板的后面，这样就算css和js没有加载全也能正常渲染页面

    面包屑
        ├── 面包屑分为多级，一般最后一级面包屑不需要加链接，面包屑的宗旨是为访问相关页面提供一个快速链接地址，起到二级导航(或者三级导航作用)

    No follow
        ├── 当我们不需要一个链接给搜索引擎爬取时候，需要在这个链接后面加一个属性rel = 'nofollow',这个属性会告诉爬虫不要抓我，我不是一个不需要抓取的链接

    图片与描述
        ├── 图片要加alt描述，能用CSS的就不要用图片，能用图片的就不要用flash

    内链 & 友链
        ├── 内链: 站内相关内容相互推荐，提高页面的整体权重，这在SEO里是很重要的，它的效果是长期和缓慢的
        ├── 友链: 在我们的网站底部和别人网站互换已些链接，这会引导访问别人家网站的用户来访问我们网站，增加我们的UV和PV,因此换友链的原则是尽可能的找比我们同等性质的页面排名好的网站或者大流量网站页面交换友链

    SiteMap(底部导航会用到，建龙已做好所有的siteMap)
        ├── 网站地图，告诉百度等其他搜索引起我们网站的组织结构，方便爬虫过来爬取页面

    XML文件(不是每个页面都必须的)
        ├── 写xml的宗旨有些类似siteMap，不过我们的运营人员会批量提交xml文件到搜索引擎方，那边的对接人员会读取我们的xml文件内容，写入xml，存放在HBase
```

## 1.2 已有的SEO开发文档

+ [SEO内链开发文档](http://gitlab.corp.anjuke.com/_site/docs/tree/master/DesignDoc/Seo/seo_docs/internal_links)

+ [SEO友链开发文档](http://gitlab.corp.anjuke.com/_site/docs/tree/master/DesignDoc/Seo/seo_docs/friend_links)

## 1.3 SEO小区大全单页

+ [SEO小区大全单页原始需求文档](http://gitlab.corp.anjuke.com/_site/docs/blob/master/DesignDoc/Seo/CommBook/CommBookNeeds.md)

```
    页面URL
        ├── 二手房小区大全单页 http://www.anjuke.com/shanghai/cm8219/
        ├── 租房小区大全单页 http://www.anjuke.com/shanghai/cm8219-zu/
    Controller位置
        ├── 二手房小区单页 /user-site/app-ershou-web/controller/ershou/web/seo/commbook/EsfCommBook.php
        ├── 租房小区大全单页 /user-site/app-ershou-web/controller/ershou/web/seo/commbook/ZfCommBook.php
```

# 2. Public Api

```
public api加入参数验证功能，防止其他第三方恶意抓取或者访问；生成token是存放在header里的，而不是拼接在url里
```

[Public Api设计文档](http://gitlab.corp.anjuke.com/_site/docs/tree/master/DesignDoc/Public/Api/PublicApi)

# 3. 租房

[列表页梳理文档-朱建军提供](http://gitlab.corp.anjuke.com/_site/docs/blob/master/DesignDoc/Fangyuan/Zufang/%E5%88%97%E8%A1%A8%E9%A1%B5%E6%A2%B3%E7%90%86.md) PS:这一个梳理文档时间比较久远，仅供参考

```
    租房PC列表页和Touch列表页URL解析不同，但是调用底层数据的方法是一致的；一种是请求solr、另一种是请求ESearch；
    请求参数大同小异；请求Solr和ESearch出来的结果完全一致；
```

## 3.1 PC租房列表页

[PC列表页产品逻辑需求初步整理](http://gitlab.corp.anjuke.com/_site/docs/tree/master/DesignDoc/Fangyuan/Zufang/List_Rent_PC_20150623)

[PC列表页迁移设计文档](http://gitlab.corp.anjuke.com/_site/docs/tree/master/DesignDoc/Fangyuan/Zufang/List_Rent_PC_20150623/Detail)

```
FIX: 截止到2015-10-21日，PC列表页产品需求有较大更改

    1. 去掉7、14、21、28、35、42、49这7个固定位置的58个人认证房源

    2. 之前不同条件下精选房源数量统一为30套，搜索关键词命中小区时第一页优先展示所有精选房源(最多60条)

    3. 每页展示60套房源，之前每页50套

    4. 首页为58导流两套房源、放在最后两个位置展示，title加上`[58同城]`字样
```

## 3.2 Touch租房列表页

[touch租房列表页迁移设计文档](http://gitlab.corp.anjuke.com/_site/docs/tree/master/DesignDoc/Fangyuan/Zufang/touchweb_list_20150306)

```
    touch每页30条，最后两个位置加58推荐房源
```

## 3.3 租房搜素

```
租房搜索数据有两套方式，一套是走Solr，另一个是走ESearch；关于二者如何使用，在下面会有详细介绍；
ESearch的搜素在之后solr之后启动的，入参数据结构做了对solr的兼容处理，即你可以用请求solr的DTO直接来请求ESearch，二者出参结果完全一致，能跑通搜索的下面流程；
```

. [租房solr搜索设计原始文档--朱建军提供](http://gitlab.corp.anjuke.com/_site/docs/tree/master/DesignDoc/Fangyuan/Zufang/Search_Site_20140822)

### 3.3.1 构造Solr和ESearch搜索DTO

####  ***3.3.1.1 构造solr搜素DTP对象Zufang_Core_Rent_Dto_ListingSearch***

|dto fields| 数据类型 | 内建子字段描述 | 意义及用处 | 备注说明 |
|--- |--- |--- |--- |--- |
|step|integer|无子字段|配合设计模式使用，1:继续下一个rewriter 2或者3: 退出rewrite|通常都是默认1,只有在流程内部才会更改|
|source|integer|无|1:touch 0:PC 默认0|x|
|city_id|integer|无|城市id,必传参数,不传时会返回一个错误字符串或者空数组,返回哪个有一个开关|x|
|from_type|字符串|无|可能值为/user-site/app-zufang-core/config/from.php中配置from_relation中的key, landlord:个人房源 broker:经纪人 13:大业主 auth_personal:58个人认证 wuba_property:58房源|key对应的value为房源类型，可能的房源类型0，1，2，3，13，15，16，17，18，19，20|
|keyword|字符串|无|搜索原始关键字，传入前需要过滤特殊字符和xss攻击|原始关键字|
|keyword_fmt|字符串|无|分词后的搜素关键字|分词后关键词|
|keyword_new|字符串|无|删词后的搜素关键字|目前租房搜素没有走删词逻辑，所以这个参数在所有的rewriter执行完后肯定是空值，不需要关注|
|start|integer|无|查询的起始行|分页使用|
|rows|integer|无|精选和套餐一共查询的总套数|分页使用|
|page|integer|无|分页算法中的页码|分页|
|sort|string|无|排序字段，如‘time desc’|套餐和精选排序|
|facet_fields|array|无|聚合条件，指定哪些条件可以聚合|在XXXFactRewriter中有指定可聚合条件|
|filter_fields|Zufang_Core_Rent_Dto_ListingSearchFilterFields|列表页可供筛选的所有条件|筛选条件|
|filter_params|array|列表页支持的筛选条件|选中的筛选条件都会出现这个key-value数组里，所有可能的key都在Zufang_Core_Rent_Const_SearchParams这个类的上面定义常量里|solr通过反射获取支持筛选字段，这里有点坑人，很容易出问题|
|retrieve_params|array|无|支持的聚合参数|fact聚合参数集合|
|category_params|array|无|分类搜素参数|关键字搜素时，如果分词有结果，则会往这个字段里写入相应的分类信息|分类参数set|
|no_facet|boolean|无|false:需要聚合，true:不需要聚合，租房列表页不需要聚合，可以设置为true|聚合开关|
|facet_params|array|无|聚合参数|rewrite过程中动态赋值|
|unfacet_field|array|无|不需要参与的聚合参数集合|聚合field过滤|
|poi_params|array|无|地标搜素参数array('map_type'=>'baidu', 'lat'=>'', 'lng'=>'', 'distance'=>'')|地标搜素|
|keyword_params|array|无|处理后的可使用的关键词参数,带有权重|分词权重处理|
|solr_params|array|无|调用solr搜素最直接的参数|构建dto时不需传递，它会在rewrite过程中自动赋值绑定成能直接请求Solr封装类的数据格式|
|ext_params|Zufang_Core_Rent_Dto_ListingSearchExt|hp_count:精选数量 is_auction_rec:租房统一使用2，标示查找精选，代码内部1也是可以查找精选的, hp_page=1仅在第一页展示 0在所有页面都展示 hp_is_random_order=true对结果进行随机排序 0不排序，new_ext_params:对于solr请求请忽视该字段存在|主要是用来控制精选查询及附加功能实现|附加|
|debug|integer|无|1:开启debug 0:不开启solr debug| debug|
|list_type|未使用|未使用|未使用|未使用|

#### ***3.3.1.2 构造ESearch搜素DTP对象Zufang_Core_Rent_Dto_ListingNewSearch***

|dto fields| 数据类型 | 内建子字段描述 | 意义及用处 | 备注说明 |
|--- |--- |--- |--- |--- |
|step|integer|无子字段|配合设计模式使用，1:继续下一个rewriter 2或者3: 退出rewrite|通常都是默认1,只有在流程内部才会更改|
|source|integer|无|1:touch 0:PC 默认0|x|
|city_id|integer|无|城市id,必传参数,不传时会返回一个错误字符串或者空数组,返回哪个有一个开关|x|
|from_type|字符串|无|可能值为/user-site/app-zufang-core/config/from.php中配置from_relation中的key, landlord:个人房源 broker:经纪人 13:大业主 auth_personal:58个人认证 wuba_property:58房源|key对应的value为房源类型，可能的房源类型0，1，2，3，13，15，16，17，18，19，20|
|keyword|字符串|无|搜索原始关键字，传入前需要过滤特殊字符和xss攻击|原始关键字|
|keyword_fmt|字符串|无|分词后的搜素关键字|分词后关键词|
|keyword_new|字符串|无|删词后的搜素关键字|目前租房搜素没有走删词逻辑，所以这个参数在所有的rewriter执行完后肯定是空值，不需要关注|
|start|integer|无|查询的起始行|分页使用|
|rows|integer|无|精选和套餐一共查询的总套数|分页使用|
|page|integer|无|分页算法中的页码|分页|
|sort|string|无|排序字段，如‘time desc’|套餐和精选排序|
|facet_fields|array|无|聚合条件，指定哪些条件可以聚合|在XXXFactRewriter中有指定可聚合条件|
|filter_fields|Zufang_Core_Rent_Dto_ListingSearchFilterFields|列表页可供筛选的所有条件|筛选条件|
|filter_params|array|列表页支持的筛选条件|选中的筛选条件都会出现这个key-value数组里，key的枚举见Zufang_Core_Rent_Search_New_ParamsFilteredRewriter->_filter_fields|ESearch可支持的筛选条件|
|retrieve_params|array|无|支持的聚合参数|fact聚合参数集合|
|category_params|array|无|分类搜素参数|关键字搜素时，如果分词有结果，则会往这个字段里写入相应的分类信息|分类参数set|
|no_facet|boolean|无|false:需要聚合，true:不需要聚合，租房列表页不需要聚合，可以设置为true|聚合开关|
|facet_params|array|无|聚合参数|rewrite过程中动态赋值|
|unfacet_field|array|无|不需要参与的聚合参数集合|聚合field过滤|
|poi_params|array|无|地标搜素参数array('map_type'=>'baidu', 'lat'=>'', 'lng'=>'', 'distance'=>'')|地标搜素|
|keyword_params|array|无|处理后的可使用的关键词参数,带有权重|分词权重处理|
|search_params|array|无|调用ESearch搜素最直接的参数|构建dto时不需传递，它会在rewrite过程中自动赋值绑定成能直接请求ESearch封装类的数据格式|
|ext_params|Zufang_Core_Rent_Dto_ListingSearchExt|hp_count:精选数量 is_auction_rec:租房统一使用2，标示查找精选，代码内部1也是可以查找精选的, hp_page=1仅在第一页展示 0在所有页面都展示 hp_is_random_order=true对结果进行随机排序 0不排序，new_ext_params:内部的and操作字段标示需要对ESearch的哪些字段内部进行`与操作`|主要是用来控制精选查询及附加功能实现|实现ESearch同一字段内部进行`与操作`|
|debug|integer|无|1:开启debug 0:不开启solr debug| debug|
|list_type|未使用|未使用|未使用|未使用|
|return_fields|array|无|返回结果中包含的ESearch字段|不推荐自己附加其他返回字段，ESearch默认只会返回info_id,其他数据需要根据info_id获取|如果非要使用这个字段，没有错，但是会影响ESearch的查询速度，尤其是大量字符串返回时|
|is_poi_keyword_search|bool|表明是否时关键字和地标搜索同时进行|关键字筛选标志位|

### 3.3.2 原始的Solr搜素和ESearch搜素代码组织结构

```
    请求solr的请求dto参数最好new Zufang_Core_Rent_Dto_ListingSearch，不要去new ESearch的Dto Zufang_Core_Rent_Dto_ListingNewSearch;
    同理请求ESearch的Dto参数最好也是new Zufang_Core_Rent_Dto_ListingNewSearch，而不是new solr的dto Zufang_Core_Rent_Dto_ListingSearch;
    不过如果真的混合交叉使用，也是能请求到正确Solr和ESearch数据的，不过我自己不推荐这么做，一个Dto应为一个服务独有使用，而不是交错使用。
```

#### ***3.3.2.1 Solr搜素代码层解析***

```
入口调用：
    ├── /user-site/app-biz/classes/biz/zufang/rent/SearchBiz.php -> getSearchData(Zufang_Core_Rent_Dto_ListingSearch $params)

使用了一个代理设计模式对参数params对象进行逐个动态操作，改变值或者set value
    ├── /user-site/app-user-common/classes/user/common/search/SaleSearch.php -> search($params)

各个rewrite及其功能,所有的Rewrite都是用来处理参数的，最终构造出查询solr的dto
    ├── /user-site/app-zufang-core/classes/zufang/core/rent/search/ParamsFilteredRewriter.php                     对列表页支持的筛选项参数过滤，并做部分映射转换工作
                                                              ├── Zufang_Core_Rent_Search_FilterRewriter          列表页上面筛选项区域、板块、租金、等条件全量build，FilterRewriter内部会调用其他ReWriter,具体如下
                                                                                 ├── new Zufang_Core_Rent_Search_AreaFilterRewriter(), //区域
                                                                                 ├── new Zufang_Core_Rent_Search_BlockFilterRewriter(), //板块
                                                                                 ├── new Zufang_Core_Rent_Search_MetroLineFilterRewriter(), //地铁线路
                                                                                 ├── new Zufang_Core_Rent_Search_MetroStationFilterRewriter(), //地铁站点
                                                                                 ├── new Zufang_Core_Rent_Search_PriceFilterRewriter(), //价格
                                                                                 ├── new Zufang_Core_Rent_Search_HouseFilterRewriter(), //户型
                                                                                 ├── new Zufang_Core_Rent_Search_HouseTypeFilterRewriter(), //房屋类型
                                                                                 ├── new Zufang_Core_Rent_Search_FitmentTypeFilterRewriter(), //装修
                                                                                 ├── new Zufang_Core_Rent_Search_LeaseTypeFilterRewriter(), //租赁类型
                                                                                 ├── new Zufang_Core_Rent_Search_FromTypeFilterRewriter(), //来源类型 个人 经纪人 大业主
                                                              ├── Zufang_Core_Rent_Search_FacetRewriter           聚合Rewriter
                                                              ├── Zufang_Core_Rent_Search_PpcRewriter             请求套餐和精选Rewriter
                                                              ├── Zufang_Core_Rent_Search_CategoryRewriter        分类搜索，内部会调用分词处理逻辑
                                                              ├── Zufang_Core_Rent_Search_KeywordSplitRewriter    普通关键词搜素
                                                              ├── Zufang_Core_Rent_Search_PoiRewriter             地标搜素
                                                              ├── Zufang_Core_Rent_Search_ParamsConvertRewriter   参数转化成可以直接调用Solr SearchClient的直接形式


请求solr
    ├── /user-site/app-zufang-core/classes/zufang/core/rent/search/AuctionSolrRequestProcessor.php
                            ├── 精选查询函数 getAuctionFromSolr($params)
                            ├── 套餐查询函数 getPricingFromSolr($params)

对请求solr的结果格式化
    ├── /user-site/app-zufang-core/classes/zufang/core/rent/search/SolrRequestDataFormater.php  -> format(...)
```

#### ***3.3.2.2 UESearch搜素代码层解析***

```
入口调用：
    ├── /user-site/app-biz/classes/biz/zufang/rent/NewSearchBiz.php -> getSearchData(Zufang_Core_Rent_Dto_ListingNewSearch $params)

使用了一个代理设计模式对参数params对象进行逐个动态操作，改变值或者set value
    ├── /user-site/app-user-common/classes/user/common/search/SaleSearch.php -> search($params)

各个rewrite及其功能,所有的Rewrite都是用来处理参数的，最终构造出查询solr的dto
    ├── /user-site/app-zufang-core/classes/zufang/core/rent/search/new/ParamsFilteredRewriter.php                     对列表页支持的筛选项参数过滤，并做部分映射转换工作, 列表页所有的可以筛选参数都在这里定义，过滤其他无效请求参数
                                                              ├── Zufang_Core_Rent_Search_FilterRewriter              列表页上面筛选项区域、板块、租金、等条件全量build，FilterRewriter内部会调用其他ReWriter,具体如下，构造dto时无需关注这个
                                                                                 ├── new Zufang_Core_Rent_Search_New_AreaFilterRewriter(), //区域
                                                                                 ├── new Zufang_Core_Rent_Search_New_BlockFilterRewriter(), //板块
                                                                                 ├── new Zufang_Core_Rent_Search_New_MetroLineFilterRewriter(), //地铁线路
                                                                                 ├── new Zufang_Core_Rent_Search_New_MetroStationFilterRewriter(), //地铁站点
                                                                                 ├── new Zufang_Core_Rent_Search_New_PriceFilterRewriter(), //价格
                                                                                 ├── new Zufang_Core_Rent_Search_New_HouseFilterRewriter(), //户型
                                                                                 ├── new Zufang_Core_Rent_Search_New_HouseTypeFilterRewriter(), //房屋类型
                                                                                 ├── new Zufang_Core_Rent_Search_New_FitmentTypeFilterRewriter(), //装修
                                                                                 ├── new Zufang_Core_Rent_Search_New_LeaseTypeFilterRewriter(), //租赁类型
                                                                                 ├── new Zufang_Core_Rent_Search_New_FromTypeFilterRewriter(), //来源类型 个人 经纪人 大业主
                                                              ├── Zufang_Core_Rent_Search_New_FacetRewriter           聚合Rewriter，截止到2015-10-22这个聚合功能没有
                                                              ├── Zufang_Core_Rent_Search_New_PpcRewriter             请求套餐和精选Rewriter
                                                              ├── Zufang_Core_Rent_Search_New_CategoryRewriter        分类搜索，内部会调用分词处理逻辑
                                                              ├── Zufang_Core_Rent_Search_New_KeywordSplitRewriter    普通关键词搜素
                                                              ├── Zufang_Core_Rent_Search_New_PoiRewriter             地标搜素
                                                              ├── Zufang_Core_Rent_Search_New_ParamsConvertRewriter   参数转化成可以直接调用ESearch SearchClient的直接形式,这里定义的fields查询类型(整形、范围、多值。。。)

请求UESearch
    ├── /user-site/app-zufang-core/classes/zufang/core/rent/search/AuctionESearchRequestProcessor.php
                            ├── 精选查询函数 requestAuction($params)
                            ├── 套餐查询函数 requestPricing($params)

对请求UESearch的结果格式化
    ├── /user-site/app-zufang-core/classes/zufang/core/rent/search/new/SearchRequestDataFormater.php  -> format(...)

PS: UESearch同一个字段内部如果是JSON字符串，那么不能在该字段内部同时进行`与操作` 和 '或操作'，比如一个tags字段{2, E001, F002}，
    第一个2标示合租，第三个标示朝向，我们不能调用SearchCore的addFilter方法查找既是合租又是朝向为F002或者朝向F007或者朝向F008这种需求；此时需要拆分tags字段；
```

# 4. 商业地产

```
    新开18城商业地产数据时，58商业地产房源(写字楼出租/出售、商铺出租/出售)的房源id > 100,000,000，也就是说商业地产Solr里现在大于100000000的都是58导入的房源；在列表页展示排在最后面；

    商业地产Job除了楼盘和物业没有迁移过来，其他都已经迁移；这样是马景云迁移的
```

[景云整理的商业地产文档](http://gitlab.corp.anjuke.com/_site/docs/blob/master/%E5%B7%B2%E6%95%85%E4%BA%BA%E5%91%98/%E9%A9%AC%E6%99%AF%E4%BA%91/%E4%BA%A4%E6%8E%A5%E6%96%87%E6%A1%A3.md)

+ 新开18成商业地产上架job

    + /user-site/app-shangpu-job/classes/shangpu/job/crawler/WubaJob.php

    + 58房源id都是大于一亿的，经纪人房源都是小于一亿的

# 5. JOB & XML

    + 神马SEM JOB

        + 神马小区周边JOB /user-site/app-ershou-job/classes/ershou/job/sem/UcSmCommSurroundingXmlJob.php

            + [drone部署](http://drone.corp.anjuke.com/scheduler/job/309/view)

        + 神马小区卡片JOB /user-site/app-ershou-job/classes/ershou/job/sem/UcSmXmlCommunityIntroJob.php

            + [drone部署](http://drone.corp.anjuke.com/scheduler/job/308/view)

        + 神马问答XML JOB /user-site/app-qa-job/classes/qa/job/qa/UcsmQaXmlJob.php

            + [drone部署](http://drone.corp.anjuke.com/scheduler/job/316/view)

    + SEO JOB

        + 区域、板块批量SiteMap /user-site/app-ershou-job/classes/ershou/job/seo/AreaBlockUrlInfoJob.php

            + [drone部署](http://drone.corp.anjuke.com/scheduler/job/310/view)

        + SEO地铁线路、公交线路在线关系build /user-site/app-ershou-job/classes/ershou/job/seo/RBRelation.php

            + [drone部署](http://drone.corp.anjuke.com/scheduler/job/186/view)




