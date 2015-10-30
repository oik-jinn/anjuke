##租房源推广上下架
---


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

    * 所在表：quque_db.zf\_spread\_queue\_{分表}\_{年月}
    * 分表逻辑：以经纪人id除以10后的余数
    * 表结构如下：

```
CREATE TABLE `zf_spread_queue_{分表}_{年月}` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city_id` smallint(6) NOT NULL DEFAULT '0' COMMENT '城市ID',
  `broker_id` int(11) NOT NULL DEFAULT '0' COMMENT '经纪人ID',
  `pro_id` int(11) NOT NULL DEFAULT '0' COMMENT '房源ID',
  `pro_type` tinyint(1) unsigned DEFAULT '0' COMMENT '房源类型:1:个人,2:经纪人,13:大业主房源, 14:个人抓取房源,15: 经纪人抓取房源',
  `business_type` tinyint(1) unsigned DEFAULT '0' COMMENT '操作类型:2:定价,3:端口',
  `flag` tinyint(1) unsigned DEFAULT '0' COMMENT '操作类型:1:上架,2:下架',
  `update_time` int(11) NOT NULL DEFAULT '0' COMMENT '更新时间',
  PRIMARY KEY (`id`),   
  KEY `broker_id` (`broker_id`),
  KEY `pro_id` (`pro_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='租房房源上下架队列';

```

* 后台job处理：以10个常驻进程处理这10个表的数据






