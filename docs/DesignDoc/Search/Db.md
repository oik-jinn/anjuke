#### 索引数据源表创建：

* 建表策略
   *  数据库：uesearch_db
   *  数据表: index\_source_{分表}
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
		  PRIMARY KEY (`id`),
		  UNIQUE KEY `info_id` (`info_id`) 
		) ENGINE=MyISAM DEFAULT CHARSET=utf8;

		```
		
#### 流水表创建：

* 建表策略
   *  数据库：uesearch\_db
   *  信息表: sesauto\_index\_source\_0和sesauto\_index\_source\_1
       * 有0和1两个流水表
       * 同时写入信息到这两个流水表
	* 表结构

		```

		CREATE TABLE `sesauto_index_source_0` (
		  `id` bigint(20) NOT NULL auto_increment,
		  `infoid` bigint(64) NOT NULL,
		  `addtime` datetime NOT NULL,
		  `isexec` tinyint(4) NOT NULL default '0' COMMENT '0,1',
		  `type` tinyint(4) NOT NULL default '0' COMMENT '0:insert,1:update,2:delete',
		  PRIMARY KEY  (`id`)
		) ENGINE=InnoDB AUTO_INCREMENT=2281557 DEFAULT CHARSET=utf8;


		```