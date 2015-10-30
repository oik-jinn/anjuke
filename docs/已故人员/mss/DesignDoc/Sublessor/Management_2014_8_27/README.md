## 表结构设计
### 总体思路
* 房源相关表（基础信息、扩展信息、图片）按照城市ID分表
* 房源id使用发号器表
* 房源id在solr中加10亿
* 所有日志表均按照年月拆分表

### 讨论
* 房源id用int,20亿足够

### 房源发号器表
* 写入量:5000/d
* 更新量:2000/d

#### 库名：user_prop_db

```
CREATE TABLE `sublessor_prop_seq` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '房源ID发号器',
  `city_id` smallint(5) unsigned NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
```

### 房源基础信息表（按照城市ID分表）
* 写入量:5000/d
* 更新量:2000/d
* 查询量:20w/d
* sql:SELECT * FROM sublessor_prop_11 WHERE id =?;

#### 库名：user_prop_sh_db

```
CREATE TABLE `sublessor_prop_11` (
  `id` int(10) unsigned NOT NULL COMMENT '房源id',
  `city_id` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '城市id',
  `source_type` tinyint(3) unsigned NOT NULL DEFAULT '3' COMMENT '房源类型，1:个人,2:经纪人,3:大业主，4:个人抓取,5:经纪人抓取'',',
  `user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '大业主id',
  `community_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '小区id',
  `title` varchar(30) NOT NULL DEFAULT '' COMMENT '房源标题',
  `use_type_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '房屋类型id，分城市，老公房公寓别墅等',
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

### 房源扩展表（按照城市ID分表）
* 写入量:5000/d
* 更新量:2000/d
* 查询量:20w/d
* sql:SELECT * FROM sublessor_prop_extend_11 WHERE id =?;

#### 库名：user_prop_sh_db

```
CREATE TABLE `sublessor_prop_extend_11` (
  `id` int(10) unsigned NOT NULL COMMENT '房源id',
  `desc` text NOT NULL COMMENT '房源描述',
  `ext_field` varchar(20000) NOT NULL COMMENT '扩展json字段：deployment（房屋配置）等',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='房源描述信息';
```

### 房源索引表（按照城市ID分表）
* 写入量:5000/d
* 更新量:2w/d
* 查询量:40w/d
* sql:SELECT * FROM sublessor_prop_11 WHERE id =?;

#### 库名：user_prop_sh_db

```
CREATE TABLE `sublessor_prop_index_11` (
  `id` int(10) unsigned NOT NULL COMMENT '房源id',
  `user_id` int(10) unsigned NOT NULL COMMENT '用户id',
  `status` tinyint(3) unsigned NOT NULL COMMENT '0:删除 1:推广(展示) 2:保存(不展示)',
  `created` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_updated` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='房源索引表';
```

### 房源图片表（按照城市ID分表）
* 写入量:1w/d
* 查询量:40w/d
* sql:SELECT * FROM sublessor_prop_image_11 WHERE prop_id =?;

#### 库名：user_prop_sh_db

```
CREATE TABLE `sublessor_prop_image_11` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `prop_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '房源id',
  `city_id` smallint(5) unsigned NOT NULL DEFAULT '0',
  `is_default` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否默认图，0不是，1是',
  `type_id` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '1为房源 2为房型图',
  `host_id` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `image_id` char(32) NOT NULL DEFAULT '0',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_proid` (`prop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='房源图片信息';
```
### 举报信息表
* 写入量:1000/d
* sql:insert;

#### 库名：user_prop_db

```
CREATE TABLE `sublessor_prop_report` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `prop_id` int(11) unsigned NOT NULL COMMENT '房源id',
  `city_id` tinyint(3) unsigned NOT NULL,
  `report_type` tinyint(3) unsigned NOT NULL COMMENT '举报类型',
  `user_id` int(10) unsigned NOT NULL COMMENT '大业主ID',
  `ext_info` varchar(50) NOT NULL DEFAULT '' COMMENT '备注信息',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
```
### 用户操作日志表
* 写入量:5w/d
* sql:SELECT * FROM sublessor_prop_11 WHERE pro_id =?;

#### 库名：actlog_db

```
CREATE TABLE `sublessor_opt_log_201409` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `prop_id` int(10) unsigned NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  `opt_id` int(11) NOT NULL COMMENT '操作类型，1：新建 2：修改 3：上架 4：下架',
  `opt_backup` varchar(200) NOT NULL DEFAULT '' COMMENT '操作备注',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户操作日志';
```
### 用户基础信息表
* 写入量:50/d
* 查询量：5w/d
* sql:SELECT * FROM sublessor_user WHERE id =?;

#### 库名：user_prop_db

```
CREATE TABLE `sublessor_user` (
  `id` int(10) unsigned NOT NULL COMMENT '安居客user_id',
  `account_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '帐户id',
  `city_id` tinyint(3) unsigned NOT NULL,
  `id_number` char(18) NOT NULL DEFAULT '' COMMENT '大业主身份证号',
  `company_name` varchar(30) NOT NULL DEFAULT '' COMMENT '公司名称',
  `area_name` varchar(10) NOT NULL DEFAULT '' COMMENT '区域名字',
  `block_name` varchar(10) NOT NULL DEFAULT '' COMMENT '板块名字',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```
### 用户扩展信息表
* 写入量:500/d
* 查询量：5w/d
* sql:SELECT * FROM sublessor_user WHERE id =?;

#### 库名：user_prop_db

```
CREATE TABLE `sublessor_user_ext` (
  `id` int(10) unsigned NOT NULL COMMENT '安居客user_id',
  `saler_name` varchar(5) NOT NULL COMMENT '销售姓名',
  `saler_phone` bigint(20) unsigned NOT NULL COMMENT '销售电话人电话',
  `saler_mail` varchar(30) NOT NULL COMMENT '销售email',
  `contract_no` varchar(20) NOT NULL DEFAULT '' COMMENT '合同编号',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

### 用户注册用户表
* 写入量:50/d
* 查询量：0/d

#### 库名：actlog_db

```
CREATE TABLE `sublessor_register_log` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `content` varchar(2000) NOT NULL COMMENT 'log 内容',
  `flag` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态 0 失败 1 成功',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=241 DEFAULT CHARSET=utf8 COMMENT='注册业主帐号日志表';
```

## 400电话设计
### 400电话流程
* 大业主注册时，绑定手机号码
* 大业主房源单页，展示400总机&分机号
* 用户拨打大业主400电话，挂断后，调用push接口，记录拨打历史，并且扣费。

### push相关api功能
* controller位置
		Sublessor_Web_Api_ReceiveCallLogController
* 功能

		1、权限验证
		2、保存入库
		3、调用扣费service

### 400电话相关service

* 绑定手机号
		
		Sublessor_Core_Sublessor_Service_Call400Service::boundPhone

* 获取400总机号码
		
		Sublessor_Core_Sublessor_Service_Call400Service::getCall400SwitchNum()

* 获取分机号码
		
		Sublessor_Core_Sublessor_Service_Call400Service::getCall400ExtNum($user_id)

* PUSH模式,插入记录
		
		Sublessor_Core_Sublessor_Service_Call400Service::instertCallLog($id, $call_start, $call_end, $call_status, $call_sender)

* 获取通话记录
		
		Sublessor_Core_Sublessor_Service_Call400Service::getCallLog($user_id)

## 拉支付中心日志接口

### MD地址

* http://git.corp.anjuke.com/_broker_java_api/doc/browse/master/API-Document/threeNetTrade/api.md

### 接口地址
* pg：http://java-api.a.ajkdns.com/service-exes/rest/exes/fetchLog?json&lastId=0&month=201409&appId=80
* 线上：http://10.10.6.6:8080/service-exes/rest/exes/fetchLog?json&lastId=0&month=201409&appId=80

### 参数解释
* type:{1增加，2减少,3冻结,4解冻,5转出,6转入}
