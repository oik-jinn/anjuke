#二手房房价SEO详细设计

###1.需求背景
    1.Touchweb SEO 房价项目，与网站SEO-房价项目挂钩，以u通过SEO房价频道引流

###2.需求分析
    1.房价分为3个频道：城市/区域板块/小区
    2.3个频道的数据部分相同，部分不同
    3.页面展示相同
    基于以上3点，controller设计上采用1父3子的设计方式，3个房价频道子类继承一个公共房价频道。

###3.页面模块解析
* 1.topbar
    * 全站通用
    * User_Touch_Common_Header_LogoBar
    
* 2.导航栏
    * 全站通用 根据不同页面的属性配置
    * User_Touch_Common_Header_BackTopBar
    
* 3.小区搜索栏
    * 同 http://m.anjuke.com/sh/trendency/ 中小区搜索功能
    * 从java迁移过来@王欢
    
* 4.房价走势图
    * 走势图tab
    * 走势图
    * ajax方式调用
    * 参考二手房单页房价走势api：http://m.anjuke.com/ajax/propprice?comm_id=153&areacode=000100070061 改进可以共用
    
* 5.房价描述

* 6.热门二手房源

* 7.房价相关走势
    * Ershou_Core_Seo_Service_FangPriceService::getRelationInfo
    * data.relation_trend

* 8.可能感兴趣的小区
    * Ershou_Core_Seo_Service_FangPriceService::getRelationInfo
    * data.interested_community

* 9.区域板块房价
    * Ershou_Core_Seo_Service_FangPriceService::getAreaInfo
    * 缺少typeid来build老房价url：http://m.anjuke.com/sh/trendency/7_60/

* 10.底部
    * 全站通用
    * User_Touch_Common_Footer_Footer


###4.流程图

![flowChart](flowChart.png)

###5.controller

* 1.父类

```php
<?php
class User_Touch_Anjuke_price_PriceController extends User_Touch_Anjuke_AbstractAnjukeController {
    
    protected function handleRequestInner() {
    
    }
    
}
?>

```

2.城市区房价子类

```php
<?php
class User_Touch_Anjuke_price_PriceCityController extends User_Touch_Anjuke_price_PriceController {
    
    protected function handleRequestInner() {
    
    }
    
}
?>

```

3.区域/板块房价子类

```php
<?php
class User_Touch_Anjuke_price_PriceRegionController extends User_Touch_Anjuke_price_PriceController {
    
    protected function handleRequestInner() {
    
    }
    
}
?>

```

4.小区房价子类

```php
<?php
class User_Touch_Anjuke_price_PriceCommController extends User_Touch_Anjuke_price_PriceController {
    
    protected function handleRequestInner() {
    
    }
    
}
?>

```
