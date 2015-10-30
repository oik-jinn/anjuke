-- database: rent_db 数据库

/* 租房单页底seo 校园租房数据库表 */
CREATE TABLE `zf_special_search` (
    `id` int(11) NOT NULL,
    `pid` int(11) NOT NULL,
    `title` varchar(255) NOT NULL,
    `status` tinyint(4) NOT NULL DEFAULT '0',
    `kw_id` int(11) NOT NULL DEFAULT '0',
    cityid` tinyint(2) NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`),
    KEY `kw_id` (`kw_id`),
    KEY `city_pid` (`cityid`,`pid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/* 租房区域板块表 */
CREATE TABLE `area` (
    `id` int(6) NOT NULL AUTO_INCREMENT,
    `cityid` int(2) NOT NULL DEFAULT '0',
    `typename` varchar(30) NOT NULL COMMENT '名称',
    `parentid` int(6) NOT NULL DEFAULT '0',
    `typeflag` tinyint(1) DEFAULT '0' COMMENT '1为有效 0为无效',
    `typerank` int(4) DEFAULT '0' COMMENT '排序',
    `mapx` char(10) DEFAULT NULL,
    `mapy` char(10) DEFAULT NULL,
    `mapzoom` char(10) DEFAULT NULL,
    `mapcode` varchar(20) DEFAULT NULL,
    `ajk_id` int(6) unsigned DEFAULT NULL COMMENT '对应安居客的area表的id',
    `type_code` varchar(20) DEFAULT '',
    `pinyin` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `parentid` (`parentid`,`typeflag`),
    KEY `ajk_id` (`ajk_id`),
    KEY `pinyin` (`pinyin`),
    KEY `idx_1` (`cityid`,`typename`),
    KEY `typecode` (`type_code`,`typeflag`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8i;


/* 租房更新房源job队列表 */
CREATE TABLE `prop_lucene_updated` (
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `cityid` smallint(4) DEFAULT NULL,
    `type` tinyint(1) NOT NULL DEFAULT '1',
    `proid` int(10) NOT NULL,
    `flag` tinyint(1) NOT NULL COMMENT '1=updated 2=delete',
    `fail_id` int(11) NOT NULL DEFAULT '0' COMMENT '失败的队列ID',
    `updated` int(10) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `cityid` (`cityid`),
    KEY `proid` (`proid`),
    KEY `updated` (`updated`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


/* 安居客租房队列表  */
CREATE TABLE `ajk_prop_lucene_update` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `cityid` int(11) NOT NULL COMMENT '城市ID',
      `proid` int(11) NOT NULL COMMENT '房源ID',
      `plan_id` int(11) NOT NULL COMMENT '计划ID',
      `plan_type` tinyint(4) NOT NULL COMMENT '计划类型',
      `offer` int(11) NOT NULL COMMENT '点击金额',
      `action` tinyint(4) NOT NULL COMMENT '操作',
      `flag` tinyint(1) NOT NULL COMMENT '1：更新 2：下线',
      `fail_id` int(11) NOT NULL DEFAULT '0' COMMENT '失败的队列ID',
      `created` int(11) NOT NULL COMMENT '创建时间',
      PRIMARY KEY (`id`),
      KEY `proid` (`proid`),
      KEY `created` (`created`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='安居客租房队列表' ;
