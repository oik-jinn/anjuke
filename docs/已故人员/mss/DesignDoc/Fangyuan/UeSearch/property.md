搜索接入eSearch项目
-

*  概述

  房源接入58的搜索技术, 以二手房北京这个城市接入试点,项目设计考虑所有业务的合并查询

* 项目任务

任务 | 责任人 | 时间(天) | 开始 | 结束 | 状态 | 备注
---|---|---|---|---|---|---
与58技术方案讨论         | 刘张健 | - | - | - | Done | - | -
机器采购         	    	| 秦洁 | - | - | - | Todo | 已和秦洁沟通,待采购
DBA支持         | 周枫 | - | - | - | Todo | 初步讨论过
pg环境搜索布部署         | 周乐钦 | - | - | - | Todo | - |
online环境搜索布部署         | 周乐钦 | - | - | - | Todo | - |
搜索框架搭建         | 周乐钦 | - | - | - | Todo | - |
索引数据源表创建         | 刘张健 | 3 | 05-27 | 05-28 | Doing | 待review
数据发布写入Job         | 刘张健 | - | - | - | Todo |写/更新索引源数据表子任务:<br>1.房源更新job接入<br/>2.端口房源推广接入<br/>3.精选推广接入<br/>4.rank要接入?<br/>5.rebuild所有90天内房源
MSS服务（关键词匹配服务)         | 刘张健 | - | - | - | 再讨论 | 问题:<br/>1.用现有词库?
排序打分、滚动策略         | 刘张健 | - | - | - | 再讨论&任务细化 | 此任务要再讨论具体可执行方案<br/>1.58支持?
搜索服务封装web应用         | 刘张健 | - | - | - | 再讨论 |问题:<br/>1.rank均化查询支持<br/>2.新查询语法熟悉<br/>3.搜索后筛选项聚合<br /> 4.区间查询,poi范围查询
灰度发布上线         | 刘张健 | - | - | - | Todo | 子任务:<br/>1.开关配置,回滚原solr搜索<br/>.2监控预警
后期跟进数据         | 刘张健 | - | - | - | Todo | 子任务:<br/>1.转化率<br/>2.列表页速度



#### 索引数据源表创建：

* 建表策略
   *  数据库：uesearch_db
   *  数据表: data_property
       * 所有房源信息放在一个表里
       * 分表:目前按2,4,6规则分成16个表 

* 表结构
    * 基本字段

			Field | Type | Required | Description
			---|---|---|---|---|---|---
			id         | bigint(20) unsigned primary key | Y |房源id,自增
			info_id         | bigint(20) unsigned UNIQUE key | Y |唯一信息id,生成规见文档生成唯一信息id
			city_id    | smallint(5) unsigned             |Y  | 城市id 
			broker\_id |int(10) unsigned |Y | 经纪人id
			commid | int(10) unsigned|Y|小区id
			area_code| string(10) unsigned|Y|小区id
			usetype |int(10) unsigned|Y| 房屋类型，公寓/别墅/老公房，对应use_type
			build_year| smallint(5) unsigned |Y|建造年代，对应house age
			area | int(10) unsigned |Y|面积,使用时需要/100.0
			total_price |int(10) unsigned |Y|总价，单位元
			unit_price |int(10) unsigned |Y|单价，单位元
			floor| smallint(5) unsigned|Y|   房源所在的楼层数
			floor_num |smallint(5) unsigned|Y|   楼层总数
			room_num |tinyint(3) unsigned|Y| 房间数量 几室
			hall_num |tinyint(3) unsigned|Y| 客厅数量
			toliet_num| tinyint(3) unsigned|Y| 厕所数量
			fitment_id | int(10) unsigned |Y|装修情况 
			state| tinyint(3) unsigned |Y|房源是否可见 合并operateState,IsVisible等字段,0是正常，1是经纪人下线，2是违规,etc
			creation | int(10) unsigned |Y|发房时间,精确到秒
			updated  | int(10) unsigned  |Y| 房源更新时间,精确到秒
			
		
	* 多字段
	
		  Field | Type | Required | Description
		  ---|---|---|---|---|---|---
		  bus\_id   | string | Y |json,地铁站点id
		  metro\_id   | string | Y |json,地铁id
		  metro\_station\_id   | string | Y |json,地铁站点id
		  metro\_distance   | string | Y |json,步行到地铁站的距离 
		  besttags | string | N |房源标签
		  keywords | string | N |房源关键字（经纪人姓名，门店等）
		  tags | string | N |给房源打标记,比如房产季房源

	  
	* 扩展字段
	 
	  Field | Type | Required | Description
	  ---|---|---|---|---|---|---
	  extend   | txt | Y |json存储,比如存储rank信息,例{"rank_level":1,"rank_score":533543.93}

* info_id生成规则
  * 通过不同位表示不同信息
  * {业务类型}{城市id}{房源类型}{房源id}