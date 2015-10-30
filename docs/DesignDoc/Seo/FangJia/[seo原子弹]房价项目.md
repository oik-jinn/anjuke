## [SEO原子弹]房价设计方案


版本 | 日期 | 设计 | 备注
:----------- | :-----------: | :-----------: | -----------:
v0.9   | 2014-05-19      | 李超平 王梓丞 | 初稿

## 项目背景&目标
* 问题:新建seo频道，并通过多种方式吸引蜘蛛爬去
* 目标:Q2底UV翻倍（17W-30W）

## 项目成员
人员 | 岗位 | 职责 
:----------- | :-----------: | :----------- 
汪琳   | 产品     | SEO房价产品负责
李超平 | 后端开发     | SEO房价频道依赖数据(4个job)和3个API
王梓丞 | 后端开发     | SEO房价频道模块现实
江维   | 后端开发     | SEO房价Sitemap API和模块实现
安冬雪 |  前端开发    | SEO房价频道交互开发
陈佳杰 |  前端开发    | SEO房价频道房价趋势svg效果开发


## 涉及功能
* 房价频道页面
* 房价Sitemap 

## 开发具体工作
* SEO房价数据生成,4个JOB
* SEO房价频道Service, 3个service
* SEO房价频道模块现实
* 房价Sitemap API和模块实现


### 房价SEO数据生成

* `已有数据`
	* 词源 城市A1,区域/版块A2,小区A3第一期已经生成


* `所需工作`  
* job1. 房价G1,G2,G3词入库根表(修改job SourceToBase)
* job2. 房价三种组合保存到draft表 (修改job BaseToDraft)

组词规则 | 中文描述 | 案例
:----------- | :-----------: | :-----------:
A1+ G1   | 城市+房价       | 上海房价
A2+ G2   | 区域/版块+房价  | 徐汇房价
A3+ G3   | 小区+房价		  | 东方花园房价

* job3. 调用房价service,把有效组合入库到online表 (修改DraftToOnline) 

参考流程图
![toonlineapi](http://git.corp.anjuke.com/chennyli/markdownpic/browse/master/price_toonline.png)
* job4. 生成房价相关推荐数据到online_ext表 (修改OnlineToRelation) 
定期执行: 每周更新一次感兴趣小区,相关趋势等数据.
参考流程图
![toextapi](http://git.corp.anjuke.com/chennyli/markdownpic/browse/master/md_seo_price_toext.png)


### 房价SEO频道Service

#### 根据id获取基础关联数据 `getRelationInfo($id)`
数据包含, id,中文名,拼音,上下文数据,相关趋势,感兴趣小区.

* 方法
> function getRelationInfo($id)
* 参数及返回约定

参数 | 类型 | 描述
:----------- | :-----------: | :-----------:
$id   | integer       | id
return       | array         | 正常:array(或false)

* 调用
> Ershou_Core_Seo_Service_FangPriceService::getRelationInfo($id);
#### 根据area_code获取热门城市 `getHotCity($area_id)`

* 方法
> function getHotCity($area_code)
* 参数及返回约定

参数 | 类型 | 描述
:----------- | :-----------: | :-----------:
$area_code   | integer       | area_code
return       | array         | 正常:array(或false)

* 调用
> Ershou_Core_Seo_Service_FangPriceService::getHotCity($area_code);


#### 根据area_code获取感兴趣小区 `getInterestedCommunity($id,$area_code)`

* 方法
> function getInterestedCommunity($id,$area_code)
* 参数及返回约定

参数 | 类型 | 描述
:----------- | :-----------: | :-----------:
$id   | integer       | online_id
$area_code   | integer       | area_code
return       | array         | 正常:array(或false)

* 调用
> Ershou_Core_Seo_Service_FangPriceService::getInterestedCommunity($area_code);

#### 根据city_id获取区域版块信息 `getAreaInfo($city_id)`

* 方法
> function getAreaInfo($city_id)
* 参数及返回约定

参数 | 类型 | 描述
:----------- | :-----------: | :-----------:
$city_id   | integer       | city_id
return       | array         | 正常:array(或false)

* 调用
> Ershou_Core_Seo_Service_FangPriceService::getAreaInfo($city_id);
* 思路
	*1. 先根据city_id从a 

### 房价SEO频道模块划分


#### 新版头部组件
>     global_header_component
>     使用新版头部，没有城市筛选

#### 面包屑组件
>     breadcrump_component
>     1.小区：首页>{城市}二手房>{城市}房价>{区域板块}房价>{小区}房价
>     2.区域板块：首页>{城市}二手房>{城市}房价>{区域板块}房价
>     3.城市：首页>{城市}二手房>{城市}房价

#### 热门城市tab组件
>     cityselect_component
>     tab内的城市由产品配置，详细见prd

#### 区域筛选组件
>     region_filter_component
>     区域词是通过词库获取的,与线上不一致

#### 板块直达
>     逻辑与区域筛选相同，显示展示为XXX房价

#### 房源列表组件
>     proplist_component
>     直接将id对应的搜索词丢入solr获取房源列表

#### 房价组件
>     pricetrend_component
>     房价的tab的显示是根据当前页的id决定的，
>     1.小区显示的tab依次是小区=>板块=>区域=>城市，
>     2.板块显示的tab依次是板块=>区域=>城市，
>     3.区域同板块
>     城市只显示1条房价趋势线
>     分享模块根据不同的id分享不同的文案

#### 相关趋势模块
>     该模块显示10个，默认只展示3个
>     1.当前页是小区，随机显示10个当前小区所在板块的小区
>     2.当前页是板块，随机显示10个当前板块所在区域的板块
>     3.当前页是区域，随机显示10个当前区域所在城市的区域
>     4.当前页是城市，先随机12个城市中的3个，再随机其余城市中的7个

#### 感兴趣小区组件
>     如果当前页是小区，随机取小区所在板块下的5个小区
>     如果当前页是板块，随机取当前板块下的5个小区
>     区域，城市逻辑同板块

#### footer组件

###  房价Sitemap入口 API和模块

#### sitemap地图底部房价字母导航
>     字母列表中没有房价的需要过滤
>     拼音字母无法获取的,统一放到其他里面

#### 根据字母获取的二手房房价sitemap列表
> 城市url : shanghai.anjuke.com/fangjia/zsc-xxx-d674090
> 
> 区域url : shanghai.anjuke.com/fangjia/zsq-xxx-d678086
> 
> 小区url : shanghai.anjuke.com/fangjia/zsx-xxx-d694193
> 
>     需要分页
>     
#### 问题明确
* 房价小区源被删除或者合并,已受理的url暂不做处理.

### ToDoList
* DB-RT (已添加)
* B词增加类型: ALTER TABLE `seo_base_words_extensionb` CHANGE `source_id` `type` TINYINT(4)  NOT NULL  DEFAULT '0'  COMMENT '房价类型(1房价,2,涨幅,3跌幅)';
新框架
