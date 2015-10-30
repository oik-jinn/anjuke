# touch 二手房单页推荐位梳理

## 现状

目前tw二手房房源单页的推荐类型主要有两种,第一种是拿bi的数据,第二种是我们查我们自己的solr,推荐位的接口除了更多推荐、同小区房源推荐，同首付房源推荐
之外，推荐接口都比较新，附近同小区房源推荐和同首付房源推荐是我们自己查solr的,并且不支持58房源单页。更多推荐这个调用BI老的推荐系统Romar系统，此系统
已经不再维护.

## 存在的问题

由于以前没有５８房源，同首付和同小区（全部同首付，全部同小区）房源，推荐底层不支持对５８房源数据的获取，更多推荐更多推荐这个调用BI老的推荐系统Romar系统，此系统
已经不再维护.

### 推荐的分类

#### 按照单页类型来分

推荐位 | 经纪人房源单页 | 58房源单页
---|---|---
附近推荐　|有　|有
猜你喜欢  |有　|有
更多推荐  |有　|无
附近同首付房源推荐  |有　| 无
全部同首付房源推荐  |有　| 无
同小区房源推荐  |有　|无
全部同小区房源推荐  |有　|无
投资潜力房  |有　|有

#### 按照数据源分

推荐位 | solr | bi接口　| bi接口文档
---|---|---|-----
附近推荐　|否　|是 |http://gitlab.corp.anjuke.com/_microdata/recommend-api-doc/blob/master/sale-tw-pro-near.md
猜你喜欢  |否　|是 |http://gitlab.corp.anjuke.com/_microdata/recommend-api-doc/blob/master/sale-tw-pro-history.md
更多推荐  |是　|否 |
附近同首付房源推荐  |是　|否 |
全部同首付房源推荐  |是　|否 |
同小区房源推荐  |是　|否 | 
全部同小区房源推荐  |是　|否 |
投资潜力房  |否　|是|http://gitlab.corp.anjuke.com/_microdata/recommend-api-doc/blob/master/sale-tw-pro-value.md


## 推荐位细化总结

### 自己的封装的推荐接口包括（附近推荐、猜你喜欢、投资潜力房）

 * 请求的url:

      /ajax/ershou/singlerecommend/
      
 * controller:
 
      User_Touch_Ajax_ErshouSinglePageRecommend


### 请求的参数

Field | 是否必须 | 参数说明
---|---|---
city_id　|是|城市id
prop_id  |是|房源ｉｄ
type　　　|是|房源类型（数字格式）
guid　　　|是|用户唯一标识
iterms　　|是| 1表示猜你喜欢，2表示附近推荐，3表示投资潜力房
community_id |是 （附近推荐必须）|小区id
num       |是|推荐的条数
soffset   |是|
prop_price |是| 当前房源的价格

#### 补充逻辑

* 首先BI的补充逻辑，必须传入city_id.

* 我们自己的补充逻辑，当前房源价格上下浮动15%.


###  更多推荐

* BI 无人维护的romar推荐 考虑优化.


###  附近同首付房源推荐

#### 请求接口

* ajax/similarprice

##### 控制器

* User_Touch_Anjuke_Property_RecommendSimilarPriceAjaxController

##### 请求参数

Field | 是否必须 | 参数说明
---|---|---
prop_id |是 |房源id


##### 推荐逻辑

* 先获得精选房源，精选房源不够了补充套餐，并且去重

###  全部同首付房源推荐

* 跳列表页带过去筛选参数.

###  同小区房源推荐

#### 请求接口

* ajax/community/periphery

##### 请求参数

Field | 是否必须 | 参数说明
---|---|---
prop_id |是 |房源id
get_json|非必须|1返回json格式不传返回html

##### 推荐逻辑

$nearby_type_info = array(

    $this->metro_key => array('distance' => 1000),
    
    $this->bus_key => array('distance' => 1000, 'count' => 10),
    
    $this->school_key => array(),
    
    $this->hospital_key => array('distance' => 3000, 'count' => 6),
    
    $this->bank_key => array('distance' => 3000, 'count' => 6),
    
    $this->supermarket_key => array('distance' => 3000, 'count' => 6),
    
    $this->restaurant_key => array('distance' => 3000, 'count' => 6),
    
);


##### 小区周边关系表

* user_prop_db.community_place_relation_{prefix}

###### 分表规则

* 小区id%100，保持两位，如果不够两位了 前面加0；

* prefix = id%100;


* 按照$nearby_type_info取出房源数据.

* 去除距离大于任何$nearby_type_info中距离的，保留不存在距离或小于 $nearby_type_info距离的.

* 按照距离升序排序.


###  全部同小区房源推荐

* 跳小区列表页.

## 推荐位的优化思想

### 推荐级别的解耦
 
 * 底层封装的时候，将调用每一个bi接口封装成各自单独的方法，在底层实现解耦

### 页面级别的解耦

* 为了减少页面的请求次数，我们可以将一个页面的推荐位封装到一个请求中。

* 不能将将好几个页面的推荐位封装到一个请求中，因为加深了页面级别的耦合度。







