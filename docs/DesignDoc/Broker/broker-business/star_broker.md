## 明星经纪人设计
---

#### 背景

当在列表页显示的房源是明星经纪人所发时，会显示明星经纪人的icon，明星经纪人是有BI算出来的

#### BI算出明星经纪人相关

* 明星经纪人表：dw_stats.star_intermediary_score
* 字段：is_mingxing：1表示明星经纪人
* 说明：BI每天9点生成当天的数据，需要先去查询hu_tables_upload_status中table_name=‘star_intermediary_score’记录对应的天数，表示小区明星表实际有效 数据 的日期，再去查对应的表。

#### job拉取BI提供的经纪人相关表

* 说明：为了前台显示安全和数据隔离，故我们不直接使用BI的数据，采用job拉取BI的表，生成我们自己相关明星经纪人表
* 相关表设计：
    * 所在db：user_prop_db
    * 表结构：
    
```
CREATE TABLE `broker_ext_profile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `broker_id` int(11) NOT NULL DEFAULT '0' COMMENT '经纪人ID',
  `content' txt NOT NULL DEFAULT '' COMMENT 'json字符串',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),	
  UNIQUE KEY `broker_id` (`broker_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='二手房源上下架队列';
```
    
* 新增job（user-site新框架）
    * 每天10点拉取BI的明星经费人表，来更新broker_ext_profile表，有则更新，无则新加
    * 同时设置cache，供前台使用
    * cache所在的group用broker_info
    * 明星经纪人字段，以json方式{"is_star":1}写在content字段中

#### 二手房源列表页展示

* 以sale/tg11来筛选明星经纪人房源


#### solr加tags来标记明星经纪人房源

* 新增明星经纪人处理类：app-anjuke/classes/bll/broker/StarBroker.php

    * 是否明星经纪人方法：static isStar($borker_id)
    * 先查cache,且cache是和之前的拉取job是cache是同一个key，没有的话再查db
    
* 需要修改job的路径：(老框架)

```
app-jobs/bin/sale/auction_update_solr.php
app-jobs/bin/sale/auction_to_solr.php
app-jobs/bin/sale/pricing_update_solr.php
app-jobs/bin/sale/pricing_to_solr.php
```

* 打tags的逻辑
* 如果该房源所在的经纪人是明星经纪人则打上A011的tags

