估价频道项目设计
===

项目背景
---

* 站内增加估价频道，为房东引流，通过估价筛选出房东，并引导其卖房。
* 上线城市：[PRD中的37个城市](http://p.corp.anjuke.com/project/detail?id=23267)


功能模块
---

###1. header
* 导航增加‘估价’入口，其URL地址：http://shanghai.anjuke.com/gujia

		app-anjuke/config/navigation.php   增加代码：
				
		'gujia' => array(        	'title' => '估 价',        	'seoTitle' => '估价',        	'route' => 'gujia/',    	),
		
* 增加估价城市开关config配置

###2. 首页估价入口
* 右侧广告增加‘我要估价’入口

###3. 估价表单初始化
* 生成初始化表单需要的值

###4. 最近被估价的房源
* 显示6套估价房源，不满足6条不显示该模块

		app-biz/classes/biz/ershou/gujia/GujiaBiz.php
		Biz_Ershou_Gujia_GujiaBiz->getGujiaInfoById()

###5. 底部通栏广告
* 广告位新房代码

		线上：http://ifx.aifang.com/s?p=2007&c=11&o=1		线下：http://ifx.dev.aifang.com/s?p=2007&c=11&o=0



###6. 估价结果计算
* 异步计算结果
* 接口地址

		POST：http://shanghai.anjuke.com/v3/ajax/gujia/save/
		参数：
			city_id
			community_id
			community_name
			building_num
			pro_floor
			total_floor
			
	
	
	
###7. 估价结果页面
* 估价表单默认展开
* 我的房产总价

		小区信息：Biz_Community_Comm_CommunityBiz->getCommunityAllInfo()
		小区均价：Ershou_Core_Service_PriceService->getCommPriceMonthlyByCommId()
		估价价格：计算公式见下面
		最新均价：小区均价
		涨／跌幅：小区的涨／跌幅
		
* 均价走势：走势图片
	
		图片地址：/v3/chart/gujia/
	
* 市场热门度：通过小区热度来计算；

		新框架获取小区评分service（热门度：itemd=5）
		Community_Core_Comm_Service_CommunityService－>getNearbyCommunityScore()
	
* 我要卖房：将估价参数传入到卖房页面，使其自动填充卖房页面表单
		
		卖房页面表单参数：
		URL：http://i.anjuke.com/publish?from=gufangjia
		type：POST
		参数说明：http://gitlab.corp.anjuke.com/_broker-docs/i-doc/blob/master/personal/Doc/designDoc/publish_params.md


估价计算公式
---
* 估价计算公式

		$room_num;//几室        $living_room;//几厅               $pro_floor;//所在楼层        $total_floor;//总楼层        $house_age;//房龄        $floor_bi;//楼层比例        $house_ori_id;//房子朝向        $fitmentvalue_id;//装修        $landscape_room;//景观情况：景观房 or 侧景观房 or 一般 or 差6 or 3 or 1 or 0        $location;//小区位置：2 or 1 or 0离小区出口近 or 离小区出口远 or 适中        $parking;//自有车位：1 or 0有 or 无        $basement;//地下室面积        $area;//房子面积        $garden;//花园面积        $price_quartile;//小区均价        if($price_quartile == 0){        		$price_quartile_ln = 0;        }else{      		        $price_quartile_ln = log($price_quartile);        }        $sentence = 0.04204 +         			($room_num * (-0.01132)) +         			($living_room * (-0.00686)) +        			($pro_floor * 0.00227) +        			($total_floor * (-0.00126)) +         			(intval(date("Y"))-$house_age) * (-0.00050056) +        			($price_quartile_ln * 0.99638) +        			($floor_bi * (-0.01681)) +        			($house_ori_id * 0.00496) +        			($fitmentvalue_id * 0.00831);           $exp = exp($sentence);        $other = (1+0.01*($landscape_room-1))*(1+0.01*($location-1))*(1+0.03*$parking);        $result = ($exp * $other) + ($price_quartile * $basement / $area / 3.33) + ($price_quartile * $garden / $area / 10);

数据保存
---
* 数据库：user_prop_db

		
		 CREATE TABLE `property_valuation` (		  `id` int(10) unsigned NOT NULL auto_increment,		  `publish_id` int(10) unsigned NOT NULL default '0' COMMENT '数据保存在经纪人表中的id',		  `guid` char(37) NOT NULL default '' COMMENT '用户guid',		  `city_id` smallint(3) unsigned NOT NULL default '11' COMMENT '城市id',		  `unit_price` smallint(5) unsigned NOT NULL default '0' COMMENT '计算出每平米的价格',		  `community_id` int(10) unsigned NOT NULL default '0' COMMENT '小区id',		  `building_num` tinyint(3) unsigned NOT NULL default '0' COMMENT '楼号',		  `floor` tinyint(3) unsigned NOT NULL default '0' COMMENT '所在楼层',		  `total_floor` tinyint(3) unsigned NOT NULL default '0' COMMENT '总楼层',		  `room_num` tinyint(1) unsigned NOT NULL default '0' COMMENT '几室',		  `hall_num` tinyint(1) unsigned NOT NULL default '0' COMMENT '几厅',		  `toilet_num` tinyint(1) unsigned NOT NULL default '0' COMMENT '几卫',		  `area` smallint(4) unsigned NOT NULL default '0' COMMENT '面积',		  `room_face` tinyint(2) unsigned NOT NULL default '0' COMMENT '房子朝向',		  `use_type` tinyint(2) unsigned NOT NULL default '0' COMMENT '建筑类型',		  `fitment` tinyint(2) unsigned NOT NULL default '0' COMMENT '装修',		  `garden` smallint(4) unsigned NOT NULL default '0' COMMENT '花园面积',		  `basement` smallint(4) unsigned NOT NULL default '0' COMMENT '地下室面积',		  `landscape` tinyint(1) unsigned NOT NULL default '0' COMMENT '景观情况',		  `parking` tinyint(1) unsigned NOT NULL default '0' COMMENT '自有车位',		  `location` tinyint(1) unsigned NOT NULL default '0' COMMENT '小区位置',		  `facilities` varchar(50) NOT NULL default '' COMMENT '小区配套',		  `status` tinyint(1) unsigned NOT NULL default '1' COMMENT '是否有效：1 有效，0无效',		  `create_time` timestamp NOT NULL default CURRENT_TIMESTAMP COMMENT '估价时间',		  PRIMARY KEY  (`id`),		  KEY `city_id` USING BTREE (`city_id`),		  KEY `create_time` USING BTREE (`create_time`)		) ENGINE=InnoDB AUTO_INCREMENT=5000000 DEFAULT CHARSET=utf8 COMMENT='用户房源估价数据'
		
		id说明：为了不让用户认为我们网站的估价数据比较少 ， 故id从5000000开始
				
		
* 接口：

		接口说明地址：http://gitlab.corp.anjuke.com/_broker-docs/i-doc/blob/master/personal/Doc/Api/assess/publish.md


seo
---
* 估价首页

	* title：{城市名称}房价，｛城市名称｝卖房， ｛城市名称｝估房网-｛城市名称｝安居客
	* keyword：｛｛上海｝估房价，｛上海｝卖房
	* description：安居客｛城市名称｝估房价频道，提供｛年份｝年最全、最及时、最精准、最权威的｛城市名称｝房价、｛城市名称｝房价走势图。安居客｛城市名称｝二手房估家频道、实现你快带买房卖房的梦想。

* 估价结果页面
	
	* title：{小区名称}，｛户型｝，{面积}，房价{估算的房价总额}-｛城市名称｝安居客
	* keyword：｛小区名称｝，｛小区名称｝房价
	* description：安居客｛城市名称｝估房价频道，提供｛年份｝年最全、最及时、最精准、最权威的｛小区名称｝房价、｛小区名称｝价走势图。安居客｛城市名称｝二手房估家频道、实现你快带买房卖房的梦想。


服务器部署
---
* 1、url指到新框架：
	* http://{城市}.anjuke.com/gujia/
  	* http://{城市}.anjuke.com/gujia/{ID}.html
* 2、数据表建立:user_prop_db->property_valuation
* 3、估价顶层配置：gujia.php,添加如下配置
		
		$config['save_gujia_data_url'] = 'http:/i.anjuke.com/api/mobile/assess_publish';		$config['save_gujia_data_url_timeout'] = 50;

