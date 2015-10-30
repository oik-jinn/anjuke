## 建表需求如下

### 经纪人电话库
	写入量：1w/d
	更新量：1w/d
	查询量：1w/d
	
#### 库名:  user_prop_db
```
DROP TABLE IF EXISTS `broker_phone`;
CREATE TABLE `broker_phone` (
  `id` int(10) unsigned NOT NULL COMMENT 'broker id',
  `mobile` varchar(20) NOT NULL DEFAULT '' COMMENT 'broker mobile',
  PRIMARY KEY (`id`),
  KEY `mobile` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经纪人电话库';
```

### 会计收入表(按月分表 如sublessor_accounting_income_ + 201409)
	写入量：1w/d
	更新量：1w/d
	查询量：1w/d
	
### 库名：user_prop_db
```
DROP TABLE IF EXISTS `sublessor_accounting_income_201409`;
CREATE TABLE `sublessor_accounting_income_201409` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `payment_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '扣费id',
  `user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '大业主id',
  `price` decimal(11,2) NOT NULL DEFAULT '0.00' COMMENT '扣费金额，安币',
  `fee` decimal(11,2) NOT NULL DEFAULT '0.00' COMMENT '扣费金额，实际收入',
  `contract_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '合同id',
  `contract_no` char(34) NOT NULL DEFAULT '' COMMENT '合同号',
  `call_time` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '拨打时间',
  `pay_time` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '扣费时间',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='会计收入表';
```

### 400通话记录&扣费(按月分表 如sublessor_call_log_ + 201409)
	写入量：1w/d
	更新量：1w/d
	查询量：1w/d
	
### 库名：user_prop_db
```
DROP TABLE IF EXISTS `sublessor_call_log_201409`;
CREATE TABLE `sublessor_call_log_201409` (
  `id` int(10) unsigned NOT NULL COMMENT 'call_id，通话流水ID',
  `user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '大业主id',
  `call_start` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '通话开始时间',
  `call_end` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '通话结束时间',
  `call_status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '通话状态：1成功，0失败',
  `call_sender` varchar(40) NOT NULL DEFAULT '' COMMENT '主叫电话',
  `call_error` int(11) unsigned NOT NULL DEFAULT '0',
  `filter_rule` int(11) NOT NULL DEFAULT '0',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_user_id_call_end` (`user_id`,`call_end`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='400通话记录&扣费'
```

### 扣费log表(按月分表 如sublessor_call_payment_log_ + 201409)
	写入量：1w/d
	更新量：1w/d
	查询量：1w/d
	
### 库名：user_prop_db
```
DROP TABLE IF EXISTS `sublessor_call_payment_log_201409`;
CREATE TABLE `sublessor_call_payment_log_201409` (
  `id` int(10) unsigned NOT NULL COMMENT 'call_id，通话流水ID',
  `user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '大业主id',
  `amount` decimal(11,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '扣费金额',
  `status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '扣费状态，1表示成功',
  `sn` varchar(30) NOT NULL DEFAULT '' COMMENT '扣费流水号',
  `real_amount` decimal(11,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '实际扣费金额',
  `code` int(11) NOT NULL DEFAULT '0' COMMENT '返回状态,0正常,-1表示服务器未返回结果,正数值表示实际返回的code',
  `call_time` int(11) NOT NULL DEFAULT '0',
  `pay_time` int(11) NOT NULL DEFAULT '0',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_user_id_call_time` (`user_id`,`call_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='扣费log表';
```

### 充值合同表
	写入量：1000/d
	更新量：1w/d
	查询量：10w/d
	
### 库名：user_prop_db
```
DROP TABLE IF EXISTS `sublessor_contract`;
CREATE TABLE `sublessor_contract` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '安居客user_id  下单人ID',
  `contract_no` char(34) NOT NULL DEFAULT '' COMMENT '合同号',
  `order_sn` char(30) NOT NULL DEFAULT '' COMMENT '订单流水号',
  `money` decimal(11,2) NOT NULL DEFAULT '0.00' COMMENT '面额（即充值金额）',
  `price` decimal(11,2) NOT NULL DEFAULT '0.00' COMMENT '售价',
  `discount` decimal(11,2) NOT NULL DEFAULT '0.00' COMMENT '折扣金额',
  `balance` decimal(11,2) NOT NULL DEFAULT '0.00' COMMENT '面额余额',
  `price_balance` decimal(11,2) NOT NULL DEFAULT '0.00' COMMENT '售价余额',
  `addtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `contract_no` (`contract_no`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8 COMMENT='充值合同表';
```

### 充值订单表
	写入量：500/d
	更新量：1000/d
	查询量：10w/d
	
### 库名：user_prop_db
```
DROP TABLE IF EXISTS `sublessor_order`;
CREATE TABLE `sublessor_order` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `order_sn` char(30) NOT NULL DEFAULT '' COMMENT '订单流水号(dyz2014100115003010xxxxx)',
  `user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '安居客user_id  下单人ID',
  `account_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '帐户id',
  `contract_no` char(34) NOT NULL DEFAULT '' COMMENT '合同号',
  `money` decimal(11,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '面额',
  `price` decimal(11,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '订单金额',
  `discount` decimal(11,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '折扣',
  `order_status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '订单状态 0 初态 1 已完成（已通知支付中心）',
  `pay_status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '支付状态 0 待支付 1 已支付 2 (0元无需支付)',
  `k3_status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '推送K3财务系统状态  0 未推送 1 已推送 2 (0元无需推送)',
  `order_type` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '订单类型 1 (在线充值) 0 (0元返还 开户赠送)',
  `pay_type` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '付款方式，支付类型1-快钱 2-财付通 3-支付宝 4-超级支付 5-银联',
  `paycenter_sn` varchar(50) NOT NULL DEFAULT '' COMMENT '支付中心订单流水号',
  `paycenter_order_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '支付中心订单ID',
  `addtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_sn` (`order_sn`),
  UNIQUE KEY `contract_no` (`contract_no`),
  KEY `idx_user_time` (`user_id`,`addtime`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8 COMMENT='充值订单表';
```

### 举报信息表
	写入量:1000/d
		
### 库名：user_prop_db
```
DROP TABLE IF EXISTS `sublessor_prop_report`;
CREATE TABLE `sublessor_prop_report` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `prop_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '房源id',
  `city_id` smallint(5) unsigned NOT NULL DEFAULT '0',
  `report_type` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '举报类型',
  `user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '大业主ID',
  `ext_info` varchar(50) NOT NULL DEFAULT '' COMMENT '备注信息',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8 COMMENT='举报信息表';
```

### 房源发号器表
	写入量：5000/d
	更新量：2000/d
	
### 库名：user_prop_db
```
DROP TABLE IF EXISTS `sublessor_prop_seq`;
CREATE TABLE `sublessor_prop_seq` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '房源ID发号器',
  `city_id` smallint(5) unsigned NOT NULL DEFAULT '0',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8 COMMENT='房源发号器表';
```

### 大业主用户表
	写入量：500/d
	更新量:500/d
	查询量：5w/d
	
### 库名：user_prop_db
```
DROP TABLE IF EXISTS `sublessor_user`;
CREATE TABLE `sublessor_user` (
  `id` int(10) unsigned NOT NULL COMMENT '安居客user_id',
  `account_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '帐户id',
  `city_id` smallint(5) unsigned NOT NULL DEFAULT '0',
  `id_number` char(18) NOT NULL DEFAULT '' COMMENT '大业主身份证号',
  `company_name` varchar(30) NOT NULL DEFAULT '' COMMENT '公司名称',
  `area_name` varchar(10) NOT NULL DEFAULT '' COMMENT '区域名字',
  `block_name` varchar(10) NOT NULL DEFAULT '' COMMENT '板块名字',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='大业主用户表';
```

### 大业主用户扩展信息表
	写入量:500/d
	查询量：5w/d
	
### 库名：user_prop_db
```
DROP TABLE IF EXISTS `sublessor_user_ext`;
CREATE TABLE `sublessor_user_ext` (
  `id` int(10) unsigned NOT NULL COMMENT '安居客user_id',
  `saler_name` varchar(5) NOT NULL COMMENT '销售姓名',
  `saler_phone` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '销售电话人电话',
  `saler_mail` varchar(30) NOT NULL DEFAULT '' COMMENT '销售email',
  `contract_no` varchar(20) NOT NULL DEFAULT '' COMMENT '合同编号',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='大业主用户扩展信息表';
```

### 房源基础信息表 (按照城市ID分表)
	写入量:5000/d
	更新量:2000/d
	查询量:20w/d	
	
### 库名：user_prop_sh_db
```
DROP TABLE IF EXISTS `sublessor_prop_11`;
CREATE TABLE `sublessor_prop_11` (
  `id` int(10) unsigned NOT NULL COMMENT '房源id',
  `city_id` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '城市id',
  `source_type` tinyint(3) unsigned NOT NULL DEFAULT '13' COMMENT '房源类型，1:个人,2:经纪人,13:大业主，14:个人抓取,15:经纪人抓取'',',
  `user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '大业主id',
  `community_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '小区id',
  `title` varchar(30) NOT NULL DEFAULT '' COMMENT '房源标题',
  `use_type_id` int(10) unsigned NOT NULL DEFAULT '5' COMMENT '房屋类型id，分城市，老公房公寓别墅等1:公寓 8:普通住宅 4:别墅 6:酒店公寓 5:其他',
  `area_num` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '面积,面积/100',
  `rent_type` tinyint(3) unsigned NOT NULL COMMENT '租赁类型，1:整租 2:合租',
  `share_sex` tinyint(3) unsigned NOT NULL COMMENT '合租男女限制，0-2分别表示男女不限、仅限男、仅限女',
  `price` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '租金，单位元',
  `pay_type` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '付款方式,1-7分别代表面议、付3押1、付1押1、付2押1、付1押2、年付不押、半年付不押，不选或多选',
  `room_num` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '房间数量，几室',
  `hall_num` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '客厅数量',
  `toilet_num` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '厕所数量',
  `fitment_id` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '装修',
  `house_orient` tinyint(3) unsigned NOT NULL COMMENT '房屋朝向,1-11分别代表东、南、西、北、南北、东西、东南、西南、东北、西北、不知道朝向，单选',
  `floor` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '所在楼层数',
  `floor_num` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '总楼层数',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '房源更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='房源基础信息';
```

### 房源描述信息表(按照城市ID分表)
	写入量:5000/d
	更新量:2000/d
	查询量:20w/d	
	
### 库名：user_prop_sh_db
```
DROP TABLE IF EXISTS `sublessor_prop_extend_11`;
CREATE TABLE `sublessor_prop_extend_11` (
  `id` int(10) unsigned NOT NULL COMMENT '房源id',
  `desc` text NOT NULL DEFAULT '' COMMENT '房源描述',
  `ext_field` varchar(20000) NOT NULL DEFAULT '' COMMENT '扩展json字段：deployment（房屋配置）等',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='房源描述信息';
```

### 房源图片信息(按照城市ID分表)
	写入量:1w/d
	查询量:40w/d
	
### 库名：user_prop_sh_db
```
DROP TABLE IF EXISTS `sublessor_prop_image_11`;
CREATE TABLE `sublessor_prop_image_11` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `prop_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '房源id',
  `city_id` smallint(5) unsigned NOT NULL DEFAULT '0',
  `is_default` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否默认图，0不是，1是',
  `type_id` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '1为房源 2为房型图',
  `host_id` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `image_id` char(32) NOT NULL DEFAULT '0',
  `status` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '图片状态 0:删除  1:正常',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_proid` (`prop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8 COMMENT='房源图片信息';
```

### 房源索引表(按照城市ID分表)
	写入量:5000/d
	更新量:2w/d
	查询量:40w/d
	
### 库名：user_prop_sh_db
```
DROP TABLE IF EXISTS `sublessor_prop_index_11`;
CREATE TABLE `sublessor_prop_index_11` (
  `id` int(10) unsigned NOT NULL COMMENT '房源id',
  `user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '用户id',
  `status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '0:删除 1:推广(展示) 2:保存(不展示)',
  `created` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_updated` (`user_id`,`updated`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='房源索引表';
```

### 用户操作日志(按月分表)
	写入量:5w/d
		
### 库名：actlog_db
```
DROP TABLE IF EXISTS `sublessor_opt_log_201409`;
CREATE TABLE `sublessor_opt_log_201409` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `prop_id` int(10) unsigned NOT NULL DEFAULT '0',
  `user_id` int(10) unsigned NOT NULL DEFAULT '0',
  `opt_id` int(11) NOT NULL DEFAULT '0' COMMENT '操作类型，1：新建 2：修改 3：上架 4：下架 5：上下架log 6：job上架 7：job下架',
  `opt_backup` varchar(200) NOT NULL DEFAULT '' COMMENT '操作备注',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=195 DEFAULT CHARSET=utf8 COMMENT='用户操作日志';
```

### 注册业主帐号日志表
	写入量：500/d
	查询量：0/d
	
### 库名：actlog_db
```
DROP TABLE IF EXISTS `sublessor_register_log`;
CREATE TABLE `sublessor_register_log` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `key` varchar(50) NOT NULL DEFAULT '' COMMENT 'log key',
  `content` varchar(2000) NOT NULL DEFAULT '' COMMENT 'log 内容',
  `flag` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态 0 失败 1 成功',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=206 DEFAULT CHARSET=utf8 COMMENT='注册业主帐号日志表';
```

### 大业主定价rank表
	写入量：2000/d
	查询量：2000/d
	该表是否有批量集中写入： 是
	批量集中写入时间与数据量估计：凌晨12点00分，约2K
	
### 库名：hz_dw_stats
```
CREATE TABLE `hu_rank_all_score_rent_d_list` (
  `pro_id` int(11) NOT NULL DEFAULT '0' COMMENT '房源ID',
  `city_id` int(11) unsigned DEFAULT '0' COMMENT '城市ID',
  `score` decimal(65,21) DEFAULT '0.000000000000000000000' COMMENT '分数',
  `rank` bigint(21) DEFAULT '0',
  `rank_random` double(19,2) DEFAULT '0.00' COMMENT '分段随机数，原来为score1',
  `rank_type` int(11) DEFAULT '0' COMMENT 'rank大段所属类型，用于大段分层',
  `rank_type_24` int(11) DEFAULT '0' COMMENT 'rank小段所属类型，用于分小段',
  `updated` date DEFAULT '0000-00-00' COMMENT '更新年月',
  PRIMARY KEY (`pro_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='大业主定价rank表' ;
```

### 大业主昨日vppv数据表
	写入量：1w/d
	查询量：1w/d
	
### 库名：ajk_dw_stats
```
DROP TABLE IF EXISTS `sublessor_daily_vppv`;
CREATE TABLE `sublessor_daily_vppv` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '大业主id',
  `date` date NOT NULL DEFAULT '0000-00-00' COMMENT '昨天的日期',
  `vppv` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '房源单页pv',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_user_market` (`user_id`,`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='大业主昨日vppv数据表';
```

### 大业主推广队列 (按月分表)
	写入量：1w/d
	查询量：1w/d
	
### 库名：queue_db
```
DROP TABLE IF EXISTS `zf_prop_queue_00_201409`;
CREATE TABLE `zf_prop_queue_00_201409` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `business_type` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '操作类型:2:定价,3:端口',
  `city_id` smallint(6) NOT NULL DEFAULT '0' COMMENT '城市ID',
  `broker_id` int(11) NOT NULL DEFAULT '0' COMMENT '租房经纪人ID',
  `pro_id` int(11) NOT NULL DEFAULT '0' COMMENT '房源ID',
  `source_type` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '房源类型:1:个人房源,2:经纪人房源',
  `flag` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '操作类型:1:上架,2:下架',
  `from_type` smallint(4) unsigned NOT NULL DEFAULT '0' COMMENT '来源',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_business_type` (`business_type`),
  KEY `idx_broker_id` (`broker_id`),
  KEY `idx_pro_id` (`pro_id`)
) ENGINE=MyISAM AUTO_INCREMENT=108 DEFAULT CHARSET=utf8 COMMENT='大业主推广队列';
```

### 大业主推广队列 (按月分表)
	写入量：1w/d
	查询量：1w/d
	
### 库名：queue_db
```
DROP TABLE IF EXISTS `zf_prop_queue_00_201410`;
CREATE TABLE `zf_prop_queue_00_201410` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `business_type` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '操作类型:2:定价,3:端口',
  `city_id` smallint(6) NOT NULL DEFAULT '0' COMMENT '城市ID',
  `broker_id` int(11) NOT NULL DEFAULT '0' COMMENT '租房经纪人ID',
  `pro_id` int(11) NOT NULL DEFAULT '0' COMMENT '房源ID',
  `source_type` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '房源类型:1:个人房源,2:经纪人房源',
  `flag` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '操作类型:1:上架,2:下架',
  `from_type` smallint(4) unsigned NOT NULL DEFAULT '0' COMMENT '来源',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_business_type` (`business_type`),
  KEY `idx_broker_id` (`broker_id`),
  KEY `idx_pro_id` (`pro_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='大业主推广队列';
```

### 大业主推广队列 (按月分表)
	写入量：1w/d
	查询量：1w/d
	
### 库名：queue_db
```
DROP TABLE IF EXISTS `zf_prop_queue_01_201409`;
CREATE TABLE `zf_prop_queue_01_201409` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `business_type` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '操作类型:2:定价,3:端口',
  `city_id` smallint(6) NOT NULL DEFAULT '0' COMMENT '城市ID',
  `broker_id` int(11) NOT NULL DEFAULT '0' COMMENT '租房经纪人ID',
  `pro_id` int(11) NOT NULL DEFAULT '0' COMMENT '房源ID',
  `source_type` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '房源类型:1:个人房源,2:经纪人房源',
  `flag` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '操作类型:1:上架,2:下架',
  `from_type` smallint(4) unsigned NOT NULL DEFAULT '0' COMMENT '来源',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_business_type` (`business_type`),
  KEY `idx_broker_id` (`broker_id`),
  KEY `idx_pro_id` (`pro_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='大业主推广队列';
```

### 大业主推广队列 (按月分表)
	写入量：1w/d
	查询量：1w/d
	
### 库名：queue_db
```
DROP TABLE IF EXISTS `zf_prop_queue_01_201410`;
CREATE TABLE `zf_prop_queue_01_201410` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `business_type` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '操作类型:2:定价,3:端口',
  `city_id` smallint(6) NOT NULL DEFAULT '0' COMMENT '城市ID',
  `broker_id` int(11) NOT NULL DEFAULT '0' COMMENT '租房经纪人ID',
  `pro_id` int(11) NOT NULL DEFAULT '0' COMMENT '房源ID',
  `source_type` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '房源类型:1:个人房源,2:经纪人房源',
  `flag` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '操作类型:1:上架,2:下架',
  `from_type` smallint(4) unsigned NOT NULL DEFAULT '0' COMMENT '来源',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_business_type` (`business_type`),
  KEY `idx_broker_id` (`broker_id`),
  KEY `idx_pro_id` (`pro_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='大业主推广队列';
```





