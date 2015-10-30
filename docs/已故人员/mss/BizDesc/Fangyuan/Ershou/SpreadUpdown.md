##二手房源推广上下架
---

### 竞价上下架

* 处理数据流程

```
job读取消息中间件消息
↓
分发到多个小的分表
↓
多个进程读取分表数据
↓
读取flag（1:上架,2:下架）
↓
POST到solr

```

* 分表数据

    * 所在表：quque_db.esf\_pricing\_queue\_{分表}\_{年月}
    * 分表逻辑：以经纪人id除以10后的余数
    * 表结构如下：

```
CREATE TABLE `esf_auction_queue_{分表}_{年月}` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city_id` smallint(6) NOT NULL DEFAULT '0' COMMENT '城市ID',
  `broker_id` int(11) NOT NULL DEFAULT '0' COMMENT '经纪人ID',
  `pro_id` int(11) NOT NULL DEFAULT '0' COMMENT '房源ID',
  `flag` tinyint(1) unsigned DEFAULT '0' COMMENT '操作类型:1:上架,2:下架',
  `from_type` smallint(4) unsigned DEFAULT '0' COMMENT '来源',
  `hpstarttime` int(11) NOT NULL DEFAULT '0' COMMENT '展示开始时间',
  `hpendtime` int(11) NOT NULL DEFAULT '0' COMMENT '展示结束时间',
  `hpplanid` int(11) NOT NULL DEFAULT '0' COMMENT '推广计划ID',
  `hpratio` int(11) NOT NULL DEFAULT '0' COMMENT '好盘系数即出价',
  `comms_hpratio` decimal(32,5) NOT NULL DEFAULT '0.00000' COMMENT '多小区下房源排名系数',
  `comms_hpratio_a` decimal(32,5) NOT NULL DEFAULT '0.00000' COMMENT '单一小区房源排名系数第二版（出价×质量度×房型热门度）',
  `update_time` int(11) NOT NULL DEFAULT '0' COMMENT '更新时间',
  PRIMARY KEY (`id`),	
  KEY `broker_id` (`broker_id`),
  KEY `pro_id` (`pro_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='二手房源竞价上下架队列';

```

* 后台job处理：以5个常驻进程处理这5个表的数据

### 定价和端口上下架

* 处理数据流程

```
job读取消息中间件消息
↓
分发到多个小的分表
↓
多个进程读取分表数据
↓
读取flag（1:上架,2:下架）
↓
POST到solr

```

* 分表数据

    * 所在表：quque_db.esf\_spread\_queue\_{分表}\_{年月}
    * 分表逻辑：以经纪人id除以10后的余数
    * 表结构如下：

```
CREATE TABLE `esf_spread_queue_{分表}_{年月}` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city_id` smallint(6) NOT NULL DEFAULT '0' COMMENT '城市ID',
  `broker_id` int(11) NOT NULL DEFAULT '0' COMMENT '经纪人ID',
  `pro_id` int(11) NOT NULL DEFAULT '0' COMMENT '房源ID',
  `business_type` tinyint(1) unsigned DEFAULT '0' COMMENT '操作类型:2:定价,3:端口',
  `flag` tinyint(1) unsigned DEFAULT '0' COMMENT '操作类型:1:上架,2:下架',
  `hpstarttime` int(11) NOT NULL DEFAULT '0' COMMENT '展示开始时间',
  `hpendtime` int(11) NOT NULL DEFAULT '0' COMMENT '展示结束时间',
  `update_time` int(11) NOT NULL DEFAULT '0' COMMENT '更新时间',
  PRIMARY KEY (`id`),   
  KEY `broker_id` (`broker_id`),
  KEY `pro_id` (`pro_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='二手房源上下架队列';

```

* 后台job处理：以10个常驻进程处理这10个表的数据






