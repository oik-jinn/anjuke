## 学区房设计文档
---

### 学区房概念
> 学校所在的区就是学区，教育部门会结合学生家庭的户口、房产所在地来分配学校学位，该小区对口的小区称之为学区房。

> 参见：[http://baike.baidu.com/view/2812019.htm](http://baike.baidu.com/view/2812019.htm)

### 数据来源
* 抓取页面 
* * 学校数据：[http://esf.sh.soufun.com/school/o31/](http://esf.sh.soufun.com/school/o31/)
* * 学校对应小区：[http://esf.sh.soufun.com/school/4253.htm/](http://esf.sh.soufun.com/school/4253.htm)

### 数据表结构

#### 新建数据库
* user_prop_db 用户端房源相关db

#### 基础数据表
* 学校数据表
    
```
CREATE TABLE `upp_schools` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学校ID',
  `city_id` smallint(6) NOT NULL COMMENT '城市ID',
  `area_id` int(11) DEFAULT NULL COMMENT '区域或板块ID',
  `name` varchar(80) DEFAULT NULL COMMENT '学校名称',
  `category` int(11) DEFAULT NULL COMMENT '学校类别',
  `level` int(11) DEFAULT NULL COMMENT '学校级别',
  `nature` int(11) DEFAULT NULL COMMENT '学校性质',
  `feature` varchar(50) DEFAULT NULL COMMENT '学校特色,多个特色以|隔开',
  `host_id` int(1) DEFAULT '1' COMMENT '学校照片hostID',
  `image_id` char(32) DEFAULT NULL COMMENT '学校照片ID',
  `address` varchar(150) DEFAULT NULL COMMENT '学校地址',
  `is_del` tinyint(1) DEFAULT '0' COMMENT '操作类型 0:正常，1：删除',
  `update_time` int(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `city_id` (`city_id`),
  KEY `area_id` (`area_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学校基本信息';
```

* 学校经纬度表
    
```
CREATE TABLE `upp_school_latlng` (
  `school_id` int(11) NOT NULL COMMENT '学校ID',
  `type` tinyint(1) NOT NULL COMMENT '1:baidu,2:google',
  `lat` double NOT NULL COMMENT '纬度',
  `lng` double NOT NULL COMMENT '经度',
  `zoom` tinyint(3) unsigned NOT NULL COMMENT '地图级别',
  `update_time` int(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`school_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学校经纬度';
```
* 学校扩展表
    
```
CREATE TABLE `upp_school_ext` (
  `school_id` int(11) NOT NULL COMMENT '学校ID',
  `phone_number` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `about` varchar(500) DEFAULT NULL COMMENT '学校简介',
  `zip_code` int(6) DEFAULT NULL COMMENT '邮编',
  `condition` varchar(500) DEFAULT NULL COMMENT '入学条件',
  `admissions` text COMMENT '招生简单',
  `detail` text COMMENT '学校详情',
  `update_time` int(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`school_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学校扩展信息';

```


* 学校字典表

表结构

```
CREATE TABLE `upp_school_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` tinyint(1) NOT NULL COMMENT '1:类型,2:级别,3:性质,4:特色',
  `name` varchar(80) DEFAULT NULL COMMENT '名称',
  `is_del` tinyint(1) DEFAULT '0' COMMENT '操作类型 0:正常，1：删除',
  `update_time` int(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学校字典';
```

初始化数据

```
insert into school_dict(name,type,update_time) values ('幼儿园',1,UNIX_TIMESTAMP());
insert into school_dict(name,type,update_time) values ('小学',1,UNIX_TIMESTAMP());
insert into school_dict(name,type,update_time) values ('初中',1,UNIX_TIMESTAMP());
...

insert into school_dict(name,type,update_time) values ('国家重点',1,UNIX_TIMESTAMP());
insert into school_dict(name,type,update_time) values ('市重点',2,UNIX_TIMESTAMP());
insert into school_dict(name,type,update_time) values ('区重点',2,UNIX_TIMESTAMP());
...

insert into school_dict(name,type,update_time) values ('市立',3,UNIX_TIMESTAMP());
insert into school_dict(name,type,update_time) values ('单位办',3,UNIX_TIMESTAMP());
insert into school_dict(name,type,update_time) values ('部队办',3,UNIX_TIMESTAMP());
...

insert into school_dict(name,type,update_time) values ('双语',4,UNIX_TIMESTAMP());
insert into school_dict(name,type,update_time) values ('小班教学',4,UNIX_TIMESTAMP());
insert into school_dict(name,type,update_time) values ('寄宿制',4,UNIX_TIMESTAMP());
...
    
```

#### 关系数据表
* 学校和小区关联表

```
CREATE TABLE `upp_school_community` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `school_id` int(11) NOT NULL COMMENT '学校ID',
  `community_id` int(11) NOT NULL COMMENT '小区ID',
  `city_id` smallint(6) NOT NULL COMMENT '城市ID',
  `update_time` int(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `school_community_id` (`school_id`,`community_id`),
  KEY `community_id` (`community_id`),
  KEY `city_id` (`city_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学校小区关联';
```

* 学校类别和城市或级别或性质关联表

```
CREATE TABLE `upp_school_relevance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `a_id` int(11) NOT NULL COMMENT 'aID',
  `type` tinyint(1) NOT NULL COMMENT '1:城市和类别,2:类别和级别,3:类别和性质',
  `b_id` int(11) NOT NULL COMMENT 'bID',
  `update_time` int(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `a_id_type_b_id` (`a_id`,`type`,`b_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='城市学校类型级别性质关联';
```

表详细说明：

type=1 时，a_id则为城市ID，b_id为学校类别ID

type=2 时，a_id则为城市ID+类别ID，例如11001，表示城市id为11，后三位是类别id，不足用0左位移增加，b_id为学校级别ID

type=3 时，a_id则为城市ID+类别ID，例如11001，表示城市id为11，后三位是类别id，不足用0左位移增加，b_id为学校性质ID



###抓取job
* 获得城市下的所有学校

    * 来源:[http://esf.sh.soufun.com/school/](http://esf.sh.soufun.com/school/)
    录入学校表schools

* 学校和小区对应关系

    * 来源:[http://esf.sh.soufun.com/school/4231.htm](http://esf.sh.soufun.com/school/4231.htm)

    * 抓取方法：通过学校id获得对应学校单页url，再抓取该学校下面的对口小区，录入学校和小区对应表school_community


###更新job

* 更新学校和小区对应关系

    * 解决问题
        * 学校迁移走了或该学校关闭，需要重新学校和小区的对应关系  
        * 新小区如果是学区房，可以重新建立关系

    * 流程：通过学校ID获得对应学校单页
    
        * 如查学校单页返回状态是200，则将schools.is_del值置为1隐藏,删除school_community的对应关系
        * 如查学校单页返回状态非200，重建该学校和小区的对应关系，school_community有记录

    * 更新时间：一个星期一次，但抓取sleep的成本很高，更新慢
    
* 新增学校自动更新

    * 解决问题
        * 搜房新增学校后，这边也能自动增加该学样和学校和小区的对应关系

    * 流程：获得已有最大学校ID，每次在最大id是加1，可获得最新的学校单页
    
        * 如查学校单页返回状态是200，则将schools.is_del值置为1隐藏,删除school_community的对应关系
        * 如查学校单页返回状态非200，重建该学校和小区的对应关系，school_community有记录

    * 更新时间：一个星期一次，但抓取sleep的成本很高，更新慢

###遇到的问题

* 新增的小区的确是学区房，而SOUFUN是没有这个小区的怎么办？
    

### 后续功能规划
####学区房勾选
* 更新solr字段
    * school_info_ids 对应学校id
    * school_info_tags 对应学校标签
    * school_info_names 对应学样名称
    
* job修改
    * 竞价job：Sale_AuctionUpdateSolr
    * 定价job：Sale_PricingUpdateSolr

* rebuild房源
    * 执行命令 /usr/bin/php /home/www/v2/jobs/launcher.php sale/pricing_update_solr.php 2

#### 按学校找房
* 示例：[http://esf.sh.soufun.com/school/](http://esf.sh.soufun.com/school/)
* 建立学校solr















