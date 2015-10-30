小区相关Controller

--------------

* 小区大全

```
二手房:/shanghai/cm/,SEO_CommBooks,anjuke-site
租房:/shanghai/cm-zu/,SEO_CommBooksZu,anjuke-site
tw:/sh/xiaoqu/,User_Touch_Seo_XiaoquSitemap,user-site

以下是从小区大全页面跳转出去的页面：

二手房,单页:/shanghai/cm2015/,SEO_CommSingle,anjuke-site
租房，单页:/shanghai/cm2830-zu/,SEO_CommSingleZu,anjuke-site
tw,跳转往小区单页，/sh/community/27/
```

* 小区筛选页面

```
url:/community/
过滤:Listing_Communitynew_SearchFilterComponent,anjuke-site/app-anjuke
筛选结果：Listing_Communitynew_CommunityNewHomeItemComponent,anjuke-site/app-anjuke
右侧内容：入口：anjuke-site/app-anjuke/component/listing/communitynew/SearchFilter.phtml
感兴趣新房：Global_Ad_IfxComponent,anjuke-site/app-common/component
```

* 小区单页

```
url:/community/view/346324，user-site
主体：Ershou_Web_Community_CommViewController
房产知识：Ershou_Web_Property_Knowledge_EncyclopediaComponent
右侧新房推荐：User_Component_Ad_Ifx
```

* 小区单页-二手房

```
url:/community/props/sale/346324/
Ershou_Web_Community_SaleViewController
感兴趣的小区:saleview.js
右侧地图：User_Component_Community_Map_Map
```

* 小区单页-租房

```
url:/community/props/rent/440550
Ershou_Web_Community_RentViewController
```

* 小区单页-价格行情

```
url:/community/trends/440550
Community_PriceTrendsController
```

* 小区单页-房型图

```
url:/community/photos/model/440550，anjuke-site/app-anjuke
Community_CommunityModelListController
```
* 小区单页-房型图-详情页

```
url:/community/photos/model/details/712657W0QQmZ2215660#navigation
Community_CommunityModelDetailsController
```

* 小区单页-实景图

```
url:/community/photos2/b/440550，anjuke-site/app-anjuke
Community_CommunityPhotoList2Controller
```

* 小区单页-生活配套

```
url:/community/round/440550，anjuke-site/app-anjuke
Community_CommunityRoundController
```

### PS：
-------
* 小区单页跟多是调用老框架中的ajax接口获取数据,涉及到的接口(/v3/* 是新框架中的请求):
    * 获取小区下的二手房房源数据:
        * http://shanghai.anjuke.com/ajax/getcommprop/?comm_id=?&city_id=?&limit=?&p=? 
    * 获取二手房房源数量
        * http://shanghai.anjuke.com/ajax/communityext/?commid=?&useflg=onlyForAjax
    * 周边配套
        * http://shenzhen.anjuke.com/v3/ajax/nearby/?commid=?&cityid=?&callback=life_url_callback&J1417420366359
    * 获取看了又看数据
        * http://shenzhen.anjuke.com/v3/ajax/viewandview/?comm_id=?&city_id=? 
    * 房产百科&热门问题 
        * http://shenzhen.anjuke.com/api/fangyuanquestion/?comm_id=?&comm_name=? 


