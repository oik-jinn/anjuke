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
       * 分表:目前按2,4,6规则分成64个表 
   * 具体见: [http://gitlab.corp.anjuke.com/_search-cloud/uesearch/issues/7](http://gitlab.corp.anjuke.com/_search-cloud/uesearch/issues/7)
* 表结构

```

CREATE TABLE `index_source_{分表}` (
  `id` bigint(20) unsigned NOT NULL auto_increment,
  `info_id` bigint(64) unsigned NOT NULL COMMENT '信息id',
  `info_type` bigint(64) unsigned NOT NULL DEFAULT '0' COMMENT '信息类型',
  `city_id` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '城市id',
  `owner_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '所属人id',
  `community_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '小区id',
  `use_type` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '房屋类型id，老公房公寓别墅等',
  `build_year` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '建造年代',
  `district_ids` varchar(100) NOT NULL DEFAULT '' COMMENT 'json多值:区域和版块',
  `area_num` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '面积,平方厘米',
  `unit_price` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单价,单位元',
  `total_price` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '总价,单位元',
  `room_num` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '房间数量，几室',
  `hall_num` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '客厅数量',
  `toilet_num` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '厕所数量',
  `fitment_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '装修id',
  `floor` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '所在楼层数',
  `floor_num` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '总楼层数',
  `state` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '可见状态,0表示可见,其他为不可见',
  `post_time` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '产生时间，精确到秒',
  `update_time` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '更新时间，精确到秒',
  `area_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '面积id',
  `price_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '价格id',
  `is_list` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否列表显示',
  `hp_start_time` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '精选开始时间',
  `hp_end_time` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '精选结束时间',
  `hp_ratio` int(11) NOT NULL DEFAULT '0' COMMENT '好盘系数即出价',
  `hp_ratio2` decimal(32,5) NOT NULL DEFAULT '0.00000' COMMENT '多小区下房源排名系数',
  `best_tags` varchar(100) NOT NULL DEFAULT '' COMMENT 'json多值:标签',
  `keywords` varchar(100) NOT NULL DEFAULT '' COMMENT '提取的关键字',
  `bus_ids` varchar(100) NOT NULL DEFAULT '' COMMENT 'json多值:公交id',
  `school_ids` varchar(100) NOT NULL DEFAULT '' COMMENT 'json多值:学校id',
  `school_tags` varchar(100) NOT NULL DEFAULT '' COMMENT 'json多值:学校标签',
  `school_names` varchar(100) NOT NULL DEFAULT '' COMMENT 'json多值:学校名称',
  `metro_ids` varchar(100) NOT NULL DEFAULT '' COMMENT 'json多值:地铁id',
  `metro_station_ids` varchar(100) NOT NULL DEFAULT '' COMMENT 'json多值:地铁站点id',
  `metro_distance` varchar(100) NOT NULL DEFAULT '' COMMENT 'json多值:公交id',
  `metro_line_distance` varchar(100) NOT NULL DEFAULT '' COMMENT 'json多值:地铁线路距离',
  `metro_station_distance` varchar(100) NOT NULL DEFAULT '' COMMENT 'json多值:地铁站点距离',
  `tags` varchar(100) NOT NULL DEFAULT '' COMMENT 'json多值:打的标记',
  `rank_level` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT 'rank大段',
  `rank_sub_level` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT 'rank小段',
  `rank_score` decimal(32,5) NOT NULL DEFAULT '0.00000' COMMENT 'rank的分值',
  `rank_score2` decimal(32,5) NOT NULL DEFAULT '0.00000' COMMENT 'rank区间数',
  `company_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '经纪人所在公司',
  `store_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '经纪人所在门店',  
  PRIMARY KEY (`id`),
  UNIQUE KEY `info_id` (`info_id`) 
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

```
