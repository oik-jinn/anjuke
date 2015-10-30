房东委托房源项目设计
=====

项目背景:
----
* 以上海为例，近几个月每月平均成交2W套二手房，上海平均房价200W每套，中介市场每月的利润空间为 2万 * 200万 * 2% 约等于6亿元RMB
* 这6亿蛋糕中，中介公司占到70%，经纪人赚取25%，其他占（包括安居客）占5%
* 中介公司占比极高，但起到的作用仅仅是房源供应者和品牌提供者的角色，绝大多数工作由经纪人完成
* 中介公司赚取大头的关键原因在于中介公司的房源提供者角色
* 项目期望能壮大安居客和经纪人在蛋糕中的分层比例，压缩中介公司的占比
* 基于以上原因，启动房东委托项目，期望能吸引房东来安居客卖房，进而未来使安居客成为房源库

注意点:
----
* 房源列表的访问速度
* 房源列表代码模块独立,去掉该模块也不会有影响

页面功能模块:
----

### 1. 首页


##### 1.1 全局导航(新、老框架)
* a、 增加‘卖房’导航入口，url链接地址为 http://shanghai.anjuke.com/mf；

		app-anjuke/config/navigation.php 增加代码：
		
		'mf' => array(
        	'title' => '卖 房',
        	'seoTitle' => '卖房',
        	'route' => '',
    	)

* b、 合并‘写字楼’和‘金铺’，改为‘商业地产’，其链接地址为它下面二级导航的第一个链接地址

		app-anjuke/config/navigation.php 增加代码：
		
		'sydc' => array(
        	'title' => '商业地产',
        	'seoTitle' => '商业地产',
        	'route' => '',
    	)
    	删除 ‘写字楼’ and ‘金铺’相关配置

* c、修改导航展示的逻辑：以前一级导航下面的二级导航是它的子导航，而且它下面的子导航都属于同一类，
	现在这类关系不一定存在，所以需要修改逻辑；
	
		app-anjuke/classes/uri/Navigation.php
			
			修改 getNavByCats()
			
* d、新框架配置文件路径为
		
		app-user_common/config/navigation.php
		app-user_common/classes/uri/Navigation.php


##### 1.2 首页发房入口
* 只需要换一张静态图片

----
### 2. 房源列表

判断是否是委托房源字段：

		字段名：commition_type
		字段值：2 委托房源
			   1 普通房源

		
##### 2.1 精选房源tab页中显示5条委托房源（默认列表页）
* a、获取列表数据流程

		二手房导航入口
		↓
		获取solr数据（默认25条）
		↓
		判断是否含有5条委托房源 → 是 → 原有展示逻辑
		↓
		否
		↓
		通过solr获取5条委托房源
		↓
		委托房源去重
		↓
		获取房源详细信息
		↓
		插入到25条数据中
		↓
		原有逻辑展示

* b、代码修改

		app-anjuke/controller/finding/SaleFilter.php
			//判断是否是房源列表默认页
			private function isDefualtSale($params){}
			
			//检查是否包含5条委托房源
			private function checkEntrustPropNum($props,$num){}
			
			//获取5条委托房源（包括委托房源去重,房源详细信息）
			private function getEntrustPorps($city_id,$params,$page,$prop_ids){}
			
			//插入到25条数据中指定位置（5,8,11,14,17位置）
			private function insertEntrustPorps($props,$entrust_props,$postion_arr){}


##### 2.2 个人房源tab页显示全部委托房源
展示25条委托房源，按rank排序

* a、 该tab页其实也是筛选页面，改写该页面的url地址即可


##### 2.3 其他页面
有委托房源就显示icon


----
### 3. 单页
* a、房源单页的数据都是通过api接口获取，api接口中返回的数据中commition_type字段标识是否是委托房源：

		字段名：commition_type
		字段值：2 委托房源
			   1 普通房源



solr job更新
----
* 需要修改job的路径：(老框架)

		app-jobs/bin/sale/auction_update_solr.php
		app-jobs/bin/sale/auction_to_solr.php
		app-jobs/bin/sale/pricing_update_solr.php
		app-jobs/bin/sale/pricing_to_solr.php

* 增加逻辑：对房源进行判断

		判断房源是否是委托房源  →  否  →  跳过该判断
		↓
		是
		↓
		给房源添加tags标签：A010（代表委托房源）
		↓
		提交给solr

数据曝光
----
* 前端负责，开发配合：前端在设计


服务器部署
----
* solr job需要先上线来跑数据，所以需要单独拉一个分支:http://p.corp.anjuke.com/project/detail?id=21907
