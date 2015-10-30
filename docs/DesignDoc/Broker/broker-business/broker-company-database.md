# 合作伙伴及网上门店项目数据库修改

## databse
 
user_prop_db

## table

### 经纪人基本信息表 user_broker_base

        CREATE TABLE `user_broker_base` (
          `broker_id` int(11) NOT NULL COMMENT '二手房经纪人id',
          `user_id` int(11) NOT NULL default '0' COMMENT '对应的用户中心id',
          `city_id` int(11) NOT NULL default '0' COMMENT '所在城市',
          `area_id` int(11) NOT NULL default '0' COMMENT '板块id',
          `broker_photo` varchar(100) NOT NULL default '',
          `sex` tinyint(4) NOT NULL default '0' COMMENT '0表示男 ,1表示女',
          `name` varchar(70) NOT NULL default '' COMMENT '真实姓名',
          `mobile` varchar(15) NOT NULL default '' COMMENT '手机号',
          `store_id` int(11) NOT NULL default '0' COMMENT '门店id',
          `company_id` int(11) NOT NULL default '0' COMMENT '公司id',
          `update_time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '更新时间',
          `is_del` tinyint(3) unsigned NOT NULL default '0' COMMENT '是否删除 1表示删除', #edit
          PRIMARY KEY  (`broker_id`)
        )

### 公司信息 user_broker_company
        
        CREATE TABLE `user_broker_company` (
          `company_id` int(11) NOT NULL,
          `company_name` varchar(100) NOT NULL default '' COMMENT '公司名称',
          `address` varchar(50) NOT NULL default '' COMMENT '地址',
          `city_id` int(11) NOT NULL default '0',
          `update_time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '更新时间',
          `is_del` tinyint(3) unsigned NOT NULL default '0' COMMENT '是否删除 1表示删除', #edit
          `full_name` varchar(100) NOT NULL default '' COMMENT '公司全名', #new add
          `image_id` varchar(100) NOT NULL default '' COMMENT '公司logo' , #new add
          `host_id` tinyint(3) unsigned NOT NULL default '0' COMMENT '图片服务器', #new add
          `telphone` varchar(20) NOT NULL default '' COMMENT '公司电话', #new add
          `fax` varchar(20) NOT NULL default '' COMMENT '公司传真', #new add
          PRIMARY KEY  (`company_id`)
        )          
    

### 门店信息 user_broker_store
                
        CREATE TABLE `user_broker_store` (
          `store_id` int(11) NOT NULL,
          `city_id` int(11) NOT NULL default '0',
          `store_name` varchar(100) NOT NULL default '' COMMENT '门店名称',
          `address` varchar(100) NOT NULL default '' COMMENT '门店地址',
          `short_store_name:q` varchar(100) NOT NULL default '' COMMENT '门店简称',
          `update_time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '更新时间',
          `is_del` tinyint(3) unsigned NOT NULL default '0' COMMENT '是否删除 1表示删除', #edit
          `company_id` int(11) NOT NULL default '0' COMMENT '公司id', #new add
          PRIMARY KEY  (`store_id`)
        )


### 【new】公司介绍 user_broker_company_summary

        CREATE TABLE `user_broker_company_summary` (
          `company_id` int(11) NOT NULL COMMENT '公司id',
          `summary` text NOT NULL default '' COMMENT '公司介绍', 
          PRIMARY KEY  (`company_id`)
        )ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '公司介绍';


### 【new】门店与公司对应表 user_broker_store_company_mapping

        CREATE TABLE `user_broker_store_company_mapping` (
          `store_id` int(11) NOT NULL COMMENT '门店id', 
          `company_id` int(11) NOT NULL default '0' COMMENT '公司id', 
          PRIMARY KEY  (`store_id`)
        )ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '门店与公司对应';
