## 主题频道设计(pmt-22336、pmt-22509)
### 主题频道数据流图
![主题频道数据流图](TopicDataFlow.png)
### 主题频道JOB模块图
![主题频道JOB模块图](TopicJob.png)

### 表结构
```
主题表
CREATE TABLE `ajk_prop_topic` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主题主键ID',
  `topic_name` varchar(20) NOT NULL COMMENT '主题名称',
  `flag` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '1 有效 0 无效',
  `topic_type` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '词类型 1:tag,2:title,3:description',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主题表'	
```

```
特色词表
CREATE TABLE `ajk_prop_topic_word` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主题词主键ID',
  `topic_word` varchar(50) NOT NULL COMMENT '主题词',
  `topic_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '主题类型  对应主题表主键ID',
  PRIMARY KEY (`id`),
  KEY `topic_id` (`topic_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='特色词表'
```

```
房源&特色词关系表
CREATE TABLE `ajk_prop_topic_relation` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `prop_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '房源ID',
  `topic_word_id` varchar(100) NOT NULL DEFAULT '0' COMMENT '主题词ID  多个主题词以逗号隔开',
  PRIMARY KEY (`id`),
  UNIQUE KEY `prop_id` (`prop_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='房源&特色词关系表'
```	 

### banner
	* 配置文件 根据主题类型区分banner 
 	* 一个主题一种类型 
  	* 一个主题对应两个banner 
	* 主题页banner + 单页入口链接banner

### 好户型 相关特色 （特色词）
## 特色词数据源 -- BI 数据表

## 特色词对应的房源套数
	1. 从Solr中一次性获取 然后走service cache  

## 区域分布
	1. 带上条件从Solr中一次性获取 然后走service cache

## 好户型列表

## 列表实现
	1. 根据筛选条件带上特色词从solr中搜索实现

## TODO
* 1、常驻job激活方式
* 2、LB添加规则
* 3、DBRT（ajk_dw_stats,anjuke_db）