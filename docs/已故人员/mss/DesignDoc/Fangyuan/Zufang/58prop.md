# 58租房房源录入设计

## question
    * solr 上下架时 存到HBASE 记日志 让DW知道
    * 配置字典表

### 数据导入流程

```
58.com的二手房POST数据到
↓
api.anjuke.com
↓
写到user_prop_*_db（房源相关DB）
↓
api.anjuke.com发信息到中间件
↓
读取flag（1:新增(上架),2:删除(下架),3：更新）
↓
build solr/更新/删除solr
```

* 通知展示端中间件
    * 测试环境：http://nydus.dev.anjuke.com/publish?tunnel=zf_wuba_prop_updown
    * 线上环境：http://nydus.a.ajkdns.com/publish?tunnel=zf_wuba_prop_updown_2
    * 消息格式:JSON
    
        ```
        ｛
                "pro_id":房源ID(int),
                "city_id":城市ID(int),
                "flag":操作类型(int)1:新增,2:更新,
                "update_time":更新时间(int),
                "from_type":标记类型，默认为0，非必要字段,建议用(100-199之间)(int)
          ｝
        ```

## API PARAMS

| FIELD    | 类型 | 可否为空 | 注释 | 
| :------:  | :-------: | :-------:  |  :-------:  | 
| id | bigint  | N | 房源ID |
| city_id | int | N | 城市ID |
| source_type | int | N | 房源类型 0:个人,1:安居客经纪人,2:个人,3:好租经纪人,13:大业主,14:个人抓取,15:经纪人抓取,16:58经纪人房源  17:58个人认证人审房源 18:58个人认证机审房源 19:58个人未认证房源 20:58抓取房源 |
| uname | string | N | 发布人姓名 |
| phone | string | N | 发布人手机号 |
| photo | string | N | 发布人头像地址 |
| community_id | int | Y | 安居客小区ID(对应不上小区ID时 58小区ID必填) |
| wuba_community_id | int | N | 58小区ID |
| title | strint | N | 房源标题 |
| use_type_id | int | N | 房屋类型id 1:公寓 8:普通住宅 4:别墅 6:酒店公寓 5:其他 |
| area_num | int | N | 面积 |
| rent_type | int | N | 租赁类型，1:整租 2:合租 |
| share_sex | int | N | 合租男女限制，0-2分别表示男女不限、仅限男、仅限女 |
| price | int | N | 租金，单位元 |
| pay_type | int | N | 付款方式,1-7分别代表面议、付3押1、付1押1、付2押1、付1押2、年付不押、半年付不押，不选或多选 |
| room_num | int | N | 房间数量，几室 1-5分别代表 1室、2室、3室、4室、5室及以上  |
| hall_num | int | N | 客厅数量 |
| toilet_num | int | N | 厕所数量 |
| fitment_id | int | N | 装修情况：1毛坯 ；2普通装修；3精装修；4豪华装修; 9其它 |
| house_orient | int | N | 房屋朝向,1-11分别代表东、南、西、北、南北、东西、东南、西南、东北、西北、不知道朝向 |
| floor | int | N | 所在楼层数 |
| floor_num | int | N | 总楼层数 |
| desc | string | N | 房源描述 |
| ext_field | string | N | 扩展json字段：deployment（如房屋配置等） |
| prop_img | string | N | 房源图片地址 |
| create_time | timestamp | Y | 发布时间 |
| expiration_time | timestamp | Y | 过期时间 |

## DB

```

* 数据库：user_prop_{$CITYID}_db
* $CITYID 范围  11-78
* 分库规则 11 和 14 是各单独一个库  上海:user_prop_sh_db  北京:user_prop_bj_db
* 其它城市 $index = $city_id % 3;   dsn='user_prop_s0'.$index.'_db_master';
                
房源基础信息表
CREATE TABLE `zf_wuba_prop_11` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ajk房源id',
    `58prop_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '58房源id',
    `city_id` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '城市id',
    `source_type` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '房源类型 1:个人,2:经纪人,13:大业主,14:个人抓取,15:经纪人抓取,16:58经纪人房源  17:58个人认证人审房源 18:58个人认证机审房源 19:58个人未认证房源 20:58抓取房源',
    `owner_name` varchar(20) NOT NULL DEFAULT '' COMMENT '发布人名',
    `owner_phone` varchar(20) NOT NULL DEFAULT '0' COMMENT '发布人手机号码',
    `owner_photo` varchar(200) NOT NULL DEFAULT '' COMMENT '发布人头像', 
    `community_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '安居客小区id',
    `wuba_community_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '58小区id',
    `title` varchar(30) NOT NULL DEFAULT '' COMMENT '房源标题',
    `use_type_id` int(10) unsigned NOT NULL DEFAULT '5' COMMENT '房屋类型id，分城市，老公房公寓别墅等1:公寓 2:老公房 3:新里洋房 4:别墅 5:其他 6:酒店公寓 7:四合院 8:普通住宅',
    `area_num` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '面积,存放时乘以100 使用时面积/100',
    `rent_type` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '租赁类型，1:整租 2:合租',
    `share_sex` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '合租男女限制，0-2分别表示男女不限、仅限男、仅限女',
    `price` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '租金，单位元',
    `pay_type` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '付款方式,1-7分别代表面议、付3押1、付1押1、付2押1、付1押2、年付不押、半年付不押，不选或多选',
    `room_num` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '房间数量，几室',
    `hall_num` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '客厅数量',
    `toilet_num` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '厕所数量',
    `fitment_id` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '装修情况id 分城市',
    `house_orient` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '房屋朝向,1-11分别代表东、南、西、北、南北、东西、东南、西南、东北、西北、不知道朝向,单选',
    `floor` smallint(5) NOT NULL DEFAULT '0' COMMENT '所在楼层数',
    `floor_num` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '总楼层数',
    `default_image` varchar(200) NOT NULL DEFAULT '' COMMENT '默认图',
    `status` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '0:删除 1:正常',
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '房源更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `unq_58prop_id` (`58prop_id`),
    KEY `idx_updated` (`updated`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='58房源基础信息';
```

```
房源扩展信息表

CREATE TABLE `zf_wuba_prop_extend_11` (
    `id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '房源id',
    `desc` text NOT NULL COMMENT '房源描述',
    `ext_field` varchar(20000) NOT NULL DEFAULT '' COMMENT '扩展json字段：deployment（房屋配置）',
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='租房58房源扩展信息';
```

```
房源图片表

CREATE TABLE `zf_wuba_prop_image_11` (
    `id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT 'ajk房源id',
    `image_id` varchar(2000) NOT NULL DEFAULT '0' COMMENT '图片id串 以逗号分隔',
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='租房58房源图片信息';
```

```

租房58房源发布表

CREATE TABLE `zf_wuba_prop_publish_11` (
  `id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '房源id',
  `created` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '发布时间',
  `expiration_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '过期时间',
  `updated` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='租房58房源发布时间表 JOB使用';

```

```

租房58房源发号器表

CREATE TABLE `wuba_rent_index` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '房源ID发号器',
  `city_id` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '城市id'
  PRIMARY KEY (`id`)
) ENGINE=Innodb DEFAULT CHARSET=utf8 COMMENT='58房源发号器表';

```

```

租房58房源上下架消息通知中间件表 

* 分表：pro_id除10取模  按月

CREATE TABLE `zf_wuba_event_queue_00_201503` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city_id` smallint(6) NOT NULL DEFAULT '0' COMMENT '城市ID',
  `pro_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '房源ID',
  `flag` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '操作类型:1:新增,2:更新',
  `from_type` smallint(4) unsigned NOT NULL DEFAULT '0' COMMENT '来源',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_pro_id` (`pro_id`)
) ENGINE=Innodb DEFAULT CHARSET=utf8 COMMENT='租房58房源消息通知队列';

```

## 房源展示solr方案

* 58房源使用原有的solr,字段和原有保持一致
* solr 中 from  做对应区别
* solr分组按城市重新拆分 新增SOLR分组
* 消息由中间键读取至solr中

    Field | Type | Indexed | Stored | Required | Description
    ---|---|---|---|---|---|---
    id            |string        |Y         |Y        |Y      | 房源ID (大业主前缀加D 58房源前缀加W)  
    creation_date        |sint        |Y         |N         |N      | 创建时间
    updated_date        |sint        |Y         |N         |N      | 更新时间
    city_id        |int        |Y         |Y         |N      | 城市ID
    area_id        |int        |Y         |N         |N      | 区域ID
    block_id        |int        |Y         |N         |N      | 板块ID
    price        |sint        |Y         |N         |N      | 价格
    price_short        |sint        |Y         |N         |N      | 未知
    square        |sfloat        |Y         |N         |N      | 面积
    room_num        |int        |Y         |N         |N      | 室
    commid        |int        |Y         |N         |N      | 小区id
    usetype        |int        |Y         |N         |N      | 房屋类型 公寓 老公房…  值需加 10000 这是个坑
    ishq        |int        |Y         |N         |N      | 未知
    lastedit        |int        |Y         |N         |N      | 未知
    companyid        |int        |Y         |N         |N      | 公司id
    broker_id        |int        |Y         |N         |N      | 经纪人id
    broker_name        |string        |Y         |N         |N      | 经纪人名字
    broker_star        |sint        |Y         |N         |N      | 未知
    surname        |string        |Y         |N         |N      | 未知
    proimg_d        |string        |N         |Y         |N      | 默认图
    order_string        |string        |Y         |N         |N      | 未知
    area_string        |string        |Y         |N         |N      | 未知
    block_string        |string        |Y         |N         |N      | 未知
    title        |text        |Y         |N         |N      | 标题
    title_init        |text_cn        |Y         |N         |N      | 未知
    community_name        |text        |Y         |N         |N      | 小区名称
    community_name_init        |text_cn        |Y         |N         |N      | 未知
    address        |text        |Y         |N         |N      | 地址
    address_init        |text_cn        |Y         |N         |N      | 未知
    metro_id        |int        |Y         |N         |N      | 地铁线路id
    metro_station_id        |int        |Y         |N         |N      | 地铁站点id


## source_type 房源类型

| key | value |
| :------: | :-------: | 
| 1  | 个人 |
| 2  | 经纪人 |
| 13  | 大业主 |
| 14  | 个人抓取 |
| 15  | 经纪人抓取 |
| 16  | 58经纪人房源 |
| 17  | 58个人认证人审房源 |
| 18  | 58个人认证机审房源 |
| 19  | 58个人未认证房源 |
| 20  | 58抓取房源 |

## rent_type 租赁类型

| key | value |
| :------: | :-------: | 
| 1  | 整租 |
| 2  | 合租 |

## share_sex 合租男女限制 

| key | value |
| :------: | :-------: | 
| 0  | 男女不限 |
| 1  | 仅限男 |
| 2  | 仅限女 |

## pay_type 付款方式

| key | value |
| :------: | :-------: | 
| 1  | 面议 |
| 2  | 付3押1 |
| 3  | 付1押1 |
| 4  | 付2押1 |
| 5  | 付1押2 |
| 6  | 年付不押 |
| 7  | 半年付不押 |
| 8  | 年付押1 |
| 9  | 半年付押1 |
| 10  | 付2押2 |

## room_num 房间数量

| key | value |
| :------: | :-------: | 
| 1  | 1室 |
| 2  | 2室 |
| 3  | 3室 |
| 4  | 4室 |
| 5  | 5室及以上 |

## house_orient 房屋朝向

| key | value |
| :------: | :-------: | 
| 1  | 东 |
| 2  | 南 |
| 3  | 西 |
| 4  | 北 |
| 5  | 南北 |
| 6  | 东西 |
| 7  | 东南 |
| 8  | 西南 |
| 9  | 东北 |
| 10  | 西北 |
| 11  | 不知道朝向 |

## 房屋配置Mapping

```
* DEMO {"deployment":[3,1,4,6,5,9,8]}
```

| key | value |
| :------: | :-------: | 
| 4  | 床 |
| 6  | 空调 |
| 7  | 宽带 |
| 9  | 电视 |
| 10 | 冰箱 |
| 11 | 洗衣机 |
| 12 | 热水器 |
| 15 | 可做饭 |
| 16 | 独立卫生间 |
| 17 | 阳台 |

## fitment_id 装修情况

## use_type_id 房屋类型Mapping


