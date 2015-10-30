### 发号器表

    CREATE TABLE `a_property` (
        `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
        `stub` char(1) NOT NULL,
        PRIMARY KEY (`id`),
        UNIQUE KEY `stub` (`stub`)
    ) ENGINE=InnoDB AUTO_INCREMENT=383638 DEFAULT CHARSET=utf8

    CREATE TABLE `a_property_img` (
        `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
        `stub` char(1) NOT NULL,
        PRIMARY KEY (`id`),
        UNIQUE KEY `stub` (`stub`)
    ) ENGINE=InnoDB AUTO_INCREMENT=485567 DEFAULT CHARSET=utf8

### 物业表

    CREATE TABLE `e_property` (
        `id` int(10) unsigned NOT NULL,
        `name` varchar(30) NOT NULL DEFAULT '' COMMENT '物业名称',
        `alias` varchar(100) NOT NULL COMMENT '物业别名',
        `city_id` smallint(6) NOT NULL DEFAULT '0' COMMENT '物业城市id',
        `district_id` smallint(6) NOT NULL DEFAULT '0' COMMENT '物业区域id',
        `block_id` smallint(6) NOT NULL DEFAULT '0' COMMENT '物业板块id',
        `address` varchar(200) NOT NULL DEFAULT '' COMMENT '物业地址',
        `close_road` varchar(50) NOT NULL COMMENT '物业靠近',
        `business_cycle` varchar(50) NOT NULL COMMENT '物业商圈',
        `street` varchar(50) NOT NULL COMMENT '街道',
        `type` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '物业类型 @see d_property_type',
        `office_state` tinyint(4) NOT NULL DEFAULT '0' COMMENT '物业写字楼状态1开启0关闭',
        `shop_state` tinyint(4) NOT NULL DEFAULT '0' COMMENT '物业商铺状态1开启0关闭',
        `is_road` tinyint(4) NOT NULL DEFAULT '0' COMMENT '物业是否是道路型物业1是0否',
        `is_actived` tinyint(4) NOT NULL DEFAULT '0' COMMENT '物业状态 1激活2关闭',
        `is_new` tinyint(2) unsigned NOT NULL COMMENT '物业是否是新盘1是0否',
        `developer` varchar(255) NOT NULL COMMENT '物业开发商',
        `property_company` varchar(255) NOT NULL COMMENT '物业管理公司',
        `floor` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '写字楼总楼层',
        `manage_fee` float unsigned NOT NULL DEFAULT '0' COMMENT '写字楼物业管理费',
        `completion_data` varchar(100) NOT NULL COMMENT '竣工时间json array(year month)',
        `plot_rate` float unsigned NOT NULL DEFAULT '0' COMMENT '得房率',
        `overall_area_shop` varchar(100) NOT NULL DEFAULT '' COMMENT '商铺总建筑面积',
        `manage_fee_shop` float unsigned NOT NULL DEFAULT '0' COMMENT '商铺物业管理费',
        `floor_shop` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '商铺总楼层',
        `introduction` varchar(200) NOT NULL COMMENT '物业简介',
        `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
        `create_time` timestamp NOT NULL DEFAULT '2011-06-02 16:00:00',
        `is_verified` tinyint(3) unsigned NOT NULL DEFAULT '0',
        `create_member_id` int(10) unsigned NOT NULL DEFAULT '0',
        `verify_member_id` int(10) unsigned NOT NULL DEFAULT '0',
        `point_status` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '0：无标点，1:有标点，2:已审核，3:审核不通过',
        `is_del` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除1是0否',
        `developer_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '开发商id',
        `overall_area` varchar(100) NOT NULL DEFAULT '' COMMENT '写字楼总建筑面积',
        `is_spider` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否抓取1是0否',
        `ajk_comm_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '安居客小区id',
        PRIMARY KEY (`id`),
        KEY `developer_id` (`developer_id`),
        KEY `is_new` (`is_new`),
        KEY `ajk_comm_id` (`ajk_comm_id`),
        KEY `name` (`city_id`,`name`(15)),
        KEY `is_road` (`is_road`),
        KEY `k_c_a_i` (`city_id`,`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8`

### 物业扩展表

    CREATE TABLE `e_property_ext` (
        `id` int(11) NOT NULL COMMENT '物业id',
        `shop_num` int(11) DEFAULT NULL COMMENT '商铺总套数',
        `min_area` float DEFAULT NULL COMMENT '开间大小面积 单套最小面积',
        `max_area` float DEFAULT NULL COMMENT '开间大小面积 单套最大面积',
        `call_unify` tinyint(4) DEFAULT NULL COMMENT '是否统一管理 1是2否',
        `business_state` varchar(100) DEFAULT NULL,
        `shop_feature` tinyint(4) NOT NULL,
        `business_type` varchar(100) NOT NULL COMMENT '经营业态 复选框 1超市零售2餐饮美食3服饰鞋包4休闲娱乐5生活服务6家居建材7酒店宾馆8美容美发9电子通讯10其他',
        PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 

### 物业描述表

    CREATE TABLE `e_property_desc` (
        `id` int(10) unsigned NOT NULL DEFAULT '0',
        `office_title_districtadv` varchar(20) NOT NULL DEFAULT '' COMMENT '写字楼区位优势标题',
        `office_content_districtadv` varchar(200) NOT NULL DEFAULT '' COMMENT '写字楼区位优势',
        `office_title_aroundplan` varchar(20) NOT NULL DEFAULT '' COMMENT '写字楼周边规划标题',
        `office_content_aroundplan` varchar(200) NOT NULL DEFAULT '' COMMENT '写字楼周边规划',
        `office_title_facility` varchar(20) NOT NULL DEFAULT '' COMMENT '写字楼物业配套标题',
        `office_content_facility` varchar(200) NOT NULL DEFAULT '' COMMENT '写字楼物业配套',
        `office_title_locate` varchar(20) NOT NULL DEFAULT '' COMMENT '写字楼定位/业态标题',
        `office_content_locate` varchar(200) NOT NULL DEFAULT '' COMMENT '写字楼定位/业态',
        `shop_title_districtadv` varchar(20) NOT NULL DEFAULT '' COMMENT '商铺区位优势标题',
        `shop_content_districtadv` varchar(200) NOT NULL DEFAULT '' COMMENT '商铺区位优势',
        `shop_title_aroundplan` varchar(20) NOT NULL DEFAULT '' COMMENT '商铺周边规划标题',
        `shop_content_aroundplan` varchar(200) NOT NULL DEFAULT '' COMMENT '商铺周边规划',
        `shop_title_facility` varchar(20) NOT NULL DEFAULT '' COMMENT '商圈物业配套标题',
        `shop_content_facility` varchar(200) NOT NULL DEFAULT '' COMMENT '商圈物业配套',
        `shop_title_locate` varchar(20) NOT NULL DEFAULT '' COMMENT '商圈定位/业态标题',
        `shop_content_locate` varchar(200) NOT NULL DEFAULT '' COMMENT '商圈定位/业态',
        `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
        PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8
    
### 物业入驻品牌
    
    CREATE TABLE `e_property_indoor_company_rel` (
        `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
        `property_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '物业id',
        `indoor_company_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '入驻商户id',
        `is_delete` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 0-不删除 1-删除',
        PRIMARY KEY (`id`),
        KEY `property_id` (`property_id`,`is_delete`) USING BTREE,
        KEY `indoor_company_id` (`indoor_company_id`,`is_delete`) USING BTREE
    ) ENGINE=InnoDB AUTO_INCREMENT=6458 DEFAULT CHARSET=utf8

### 物业类型表

    CREATE TABLE `d_property_type` (
        `id` int(11) NOT NULL AUTO_INCREMENT,
        `name` varchar(50) NOT NULL COMMENT '',
        `office_state` tinyint(4) NOT NULL DEFAULT '0' COMMENT '',
        `shop_state` tinyint(4) NOT NULL DEFAULT '0' COMMENT '',
        `shop_type` int(11) NOT NULL COMMENT '',
        `business_shoptype` int(11) NOT NULL COMMENT '商铺形态id',
        `display_order` int(11) NOT NULL COMMENT '',
        `is_actived` tinyint(4) NOT NULL COMMENT '',
        `is_actived_crm` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT 'crm是否有效',
        `description` varchar(255) NOT NULL DEFAULT '' COMMENT '描述',
        PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8

### 写字楼表

    CREATE TABLE `e_building` (
        `id` int(10) unsigned NOT NULL,
        `name` varchar(200) NOT NULL DEFAULT '' COMMENT '名称',
        `city_id` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '城市id',
        `district_id` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '区域id',
        `block_id` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '版块id',
        `business_cycle` varchar(20) NOT NULL DEFAULT '' COMMENT '',
        `address` varchar(200) NOT NULL DEFAULT '' COMMENT '地址',
        `building_type_id` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '物业类型id',
        `building_level_id` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '建筑levelid 1甲级2乙级3丙级',
        `overall_area` varchar(255) NOT NULL DEFAULT '' COMMENT '总建筑面积',
        `standard_area` varchar(255) DEFAULT '' COMMENT '标准层面积',
        `bay_area_min` varchar(255) DEFAULT '' COMMENT '',
        `bay_area_max` varchar(255) DEFAULT '' COMMENT '',
        `floor` varchar(255) NOT NULL DEFAULT '' COMMENT '楼层',
        `manage_fee` varchar(255) DEFAULT '' COMMENT '物业费',
        `completion_data` varchar(255) NOT NULL COMMENT '竣工时间,json array(year month)',
        `developer` varchar(255) DEFAULT '' COMMENT '开发商',
        `property_company` varchar(255) DEFAULT '' COMMENT '物业管理公司',
        `alias` varchar(15) DEFAULT '' COMMENT '物业别名',
        `introduction` varchar(15000) DEFAULT NULL COMMENT '物业简介',
        `is_foreign` tinyint(3) NOT NULL DEFAULT '-1' COMMENT '是否涉外',
        `is_register` tinyint(3) NOT NULL DEFAULT '-1' COMMENT '是否可注册',
        `standard_floor_height` varchar(255) DEFAULT '' COMMENT '标准层高',
        `clear_height` varchar(255) NOT NULL DEFAULT '' COMMENT '净高',
        `bay_area` varchar(255) DEFAULT '' COMMENT '开间面积',
        `can_be_edited` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '',
        `is_actived` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '是否有效',
        `is_new` tinyint(2) unsigned NOT NULL COMMENT '是否为一手',
        `is_verified` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '',
        `create_member_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '',
        `verify_member_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '',
        `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
        `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
        `is_del` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
        PRIMARY KEY (`id`),
        KEY `idx1` (`block_id`,`is_actived`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8
    
### 物业标点表
    
    CREATE TABLE `e_map_property` (
        `id` int(10) unsigned NOT NULL COMMENT '',
        `lng` double NOT NULL COMMENT '',
        `lat` double NOT NULL COMMENT '',
        `zoom` tinyint(2) NOT NULL COMMENT '',
        `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
        PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8

### 写字楼图片表

    CREATE TABLE `e_building_img` (
        `id` int(10) unsigned NOT NULL,
        `city_id` int(11) NOT NULL DEFAULT '0',
        `member_id` int(11) DEFAULT '0',
        `building_id` int(10) unsigned NOT NULL DEFAULT '0',
        `host_id` tinyint(3) unsigned NOT NULL DEFAULT '0',
        `key` char(32) NOT NULL DEFAULT '',
        `img_width` int(11) NOT NULL DEFAULT '0',
        `img_height` int(11) NOT NULL DEFAULT '0',
        `img_size` int(11) NOT NULL DEFAULT '0',
        `category` tinyint(3) unsigned NOT NULL DEFAULT '0',
        `sub_category` tinyint(3) unsigned NOT NULL DEFAULT '0',
        `is_default` tinyint(4) NOT NULL DEFAULT '0' COMMENT '1是',
        `is_display` tinyint(4) NOT NULL DEFAULT '0' COMMENT '1是',
        `is_verified` tinyint(3) DEFAULT '0',
        `is_from` int(11) NOT NULL DEFAULT '0',
        `illustration` varchar(255) NOT NULL DEFAULT '',
        `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
        `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
        `is_illegal` tinyint(4) NOT NULL DEFAULT '0',
        `completion_level` tinyint(4) NOT NULL DEFAULT '0',
        `comment` varchar(30) NOT NULL DEFAULT '' COMMENT '图片说明',
        `exif_model` int(11) NOT NULL DEFAULT '0',
        PRIMARY KEY (`id`),
        UNIQUE KEY `building_id` (`building_id`,`host_id`,`key`),
        KEY `city_id` (`city_id`,`category`,`is_verified`,`building_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8
    
### 写字楼设施表
    
    CREATE TABLE `e_building_facility` (
        `id` int(10) unsigned NOT NULL,
        `parking_upground_nums` varchar(255) DEFAULT '' COMMENT '地上车位数',
        `parking_underground_nums` varchar(255) DEFAULT '' COMMENT '地下车位数',
        `parking_hourly_fee` varchar(255) DEFAULT '' COMMENT '',
        `parking_monthly_fee` varchar(255) DEFAULT '' COMMENT '车位月租金',
        `passenger_lift` varchar(255) DEFAULT '' COMMENT '客梯数',
        `is_lift_partition` tinyint(3) NOT NULL DEFAULT '-1' COMMENT '电梯有无分区设置 -1无1是0否',
        `goods_lift` varchar(255) DEFAULT '' COMMENT '货梯数',
        `stair_lift` varchar(255) DEFAULT '' COMMENT '',
        `lift_brand` varchar(255) DEFAULT '' COMMENT '电梯品牌',
        `communication` varchar(255) DEFAULT '' COMMENT '网络通讯 多个逗号隔开1电信2联通3长城4铁通5移动6卫通7有线通',
        `indoor_facility` varchar(255) DEFAULT '' COMMENT '楼内配套 多个逗号隔开1银行2ATM3餐饮4便利店5食堂6干洗店7商务中心8会议室9会展中心',
        `net` varchar(255) DEFAULT '' COMMENT '', # 多个逗号隔开1光纤2ADSL3无线网络4卫星系统5微波系统
        `air` varchar(255) DEFAULT '' COMMENT '空调类型 多个逗号隔开1集中式中央空调2半集中式中央空调3分体空调',
        `air_brand` varchar(255) DEFAULT '' COMMENT '',
        `air_opening_time` varchar(255) DEFAULT '' COMMENT '空调开放时间 json object time1.time1_day_from,time1.time1_day_to,time2.time2_day_from,time2.time2_day_to 1-7 星期一-日',
        `safe_system` varchar(255) DEFAULT '' COMMENT '安防系统多个逗号隔开1IC一卡通控制系统2闭路电视监视系统3门传感器监视系统4 24小时巡逻系统5停车控制及车牌识别系统6应急电源,照明和扩音系统7智能自动火警检测系统8自动喷水灭火系统',
        `indoor_company` text COMMENT '',
        `other` varchar(255) DEFAULT '' COMMENT '',
        PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8
    
### 写字楼装修表
    
    CREATE TABLE `e_building_decorate` (
        `id` int(10) unsigned NOT NULL COMMENT '写字楼id',
        `structure_id` tinyint(2) DEFAULT '0' COMMENT '结构 0''1框架结构2剪力墙结构3框架-剪力墙结构4筒体结构',
        `outer_wall_id` tinyint(2) DEFAULT '0' COMMENT '外墙 0''1玻璃幕墙2大理石3花岗石4铝扣板5面砖6涂料',
        `outer_brick_id` tinyint(2) DEFAULT '0' COMMENT '',
        `inner_wall_id` tinyint(2) DEFAULT '0' COMMENT '',
        `inner_brick_id` tinyint(2) DEFAULT '0' COMMENT '',
        `lobby_height` varchar(255) DEFAULT '' COMMENT '大堂层高',
        `lobby_wall_brick_id` tinyint(2) DEFAULT '0' COMMENT '',
        `lobby_floor_brick_id` tinyint(2) DEFAULT '0' COMMENT '',
        `lobby_ceiling_brick_id` tinyint(2) DEFAULT '0' COMMENT '',
        `corridor_width` varchar(255) DEFAULT '' COMMENT '',
        `corridor_wall_brick_id` tinyint(2) DEFAULT '0' COMMENT '',
        `corridor_floor_brick_id` tinyint(2) DEFAULT '0' COMMENT '',
        `corridor_ceiling_brick_id` tinyint(2) DEFAULT '0' COMMENT '',
        PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8
    
### 交通状况表
    
    CREATE TABLE `e_building_traffic` (
        `id` int(10) unsigned NOT NULL,
        `metro` varchar(255) NOT NULL DEFAULT '' COMMENT '[{"line":"10","station":"171","distance":"194"}]',
        `bus` varchar(255) NOT NULL DEFAULT '' COMMENT '6、14、103、103区',
        `other` varchar(255) NOT NULL DEFAULT '' COMMENT '',
        PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8
    
### 周边信息
    
    CREATE TABLE `e_building_environment` (
        `id` int(10) unsigned NOT NULL,
        `catering` varchar(255) NOT NULL DEFAULT '' COMMENT '餐饮 多个、隔开，下同',
        `mall` varchar(255) NOT NULL DEFAULT '' COMMENT '商场',
        `supermarket` varchar(255) NOT NULL DEFAULT '' COMMENT '超市',
        `postoffice` varchar(255) NOT NULL DEFAULT '' COMMENT '邮局',
        `bank` varchar(255) NOT NULL DEFAULT '' COMMENT '银行',
        `hotel` varchar(255) NOT NULL DEFAULT '' COMMENT '酒店',
        `park` varchar(255) NOT NULL COMMENT '公园',
        `school` varchar(255) NOT NULL COMMENT '学校',
        `hospital` varchar(255) NOT NULL COMMENT '医院',
        `other` varchar(255) NOT NULL DEFAULT '' COMMENT '其它',
        PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8
    
### 写字楼入驻企业
    
    CREATE TABLE `e_building_indoor_company` (
        `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
        `building_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '',
        `company` varchar(255) NOT NULL DEFAULT '' COMMENT '企业名称',
        `industry` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '所属行业 1计算机/互联网/通信/电子2会计/金融/银行/保险3贸易/消费/制造/营运4广告/媒体5房地产/建筑6专业服务/教育/培训7物流/运输8能源/原材料9政府/非赢利机构/其他',
        `logo_key` char(32) NOT NULL DEFAULT '' COMMENT 'logo图片key' COMMENT '',
        `logo_host_id` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT 'logo图片host id' COMMENT '',
        PRIMARY KEY (`id`),
        KEY `building_id` (`building_id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=23098 DEFAULT CHARSET=utf8 COMMENT='写字楼入驻企业'