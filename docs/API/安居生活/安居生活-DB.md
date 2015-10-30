## 1 用户中心

> 用户中心数据表加前缀life_

### 1.1 用户信息表

| users	| 	| 		| 	| 	|
| :------- | -------- | --------- | ----------- | ----- |
| **字段名称**	| **字段类型**	| **非空**	| **是否主键**	| **描述**	|
| user_id	| int(10) unsigned	| Y	| 主键	| 用户ID，自增	|
| nickname	| varchar(10)		| 	| 	| 昵称	|
| password	| varchar(32)		| 	| 	| 密码（MD5）	|
| sex		| tinyint(1) unsigned	| 	| 	| 性别 0无 1男 2女	|
| phone	| int(16)		| 	| 	| 手机号	|
| birthday	| timestamp			| 	| 	| 出生年月	|
| signature	| varchar(24)		| 	| 	| 个性签名	|
| photo_id		| varchar(100)	| 	| 	| 用户头像图片id	|
| photo_host	| smallint(2)	| 	| 	| 用户头像图片host	|
| background_id	| varchar(100)	| 	| 	| 背景图片id	|
| background_host	| smallint(2)	| 	| 	| 背景图片host	|
| status	| tinyint(1) unsigned | Y | | 用户状态 1正常 2拉黑 3注销 |
| last_login | timestamp		| 	| | 上次登录时间 |
| create_time	| timestamp		| 	| 	| 创建时间	|
| update_time	| timestamp		| 	| 	| 最后更新时间	|
| **索引**
| **主键/索引**	| **字段**	| **索引类型**	|
| PRIMARY	| user_id	| 主键	|

### 1.2 用户小区关联

| user_community	| 	| 		| 	| 	|
| :------- | -------- | --------- | ----------- | ----- |
| **字段名称**	| **字段类型**	| **非空**	| **是否主键**	| **描述**	|
| id		| bigint(20) unsigned		| Y			| 主键			| 自增	|
| user_id	| int(10) unsigned		| Y			| 				| 用户id	|
| community_id	| int(10) unsigned |	Y	|				| 小区id |
| **索引**
| **主键/索引**	| **字段**	| **索引类型**	|
| PRIMARY	| id	| 主键	|

### 1.3 爱好表

| hobby|||||
| :------- | -------- | --------- | ----------- | ----- |
|**字段名称**|**字段类型**|**非空**|**是否主键**|**描述**|
|hobby_id	| smallint(5) unsigned	|Y|主键|爱好ID，自增|
|hobyy_name	| varchar(10)|	Y|		|爱好名称|
|is_display	| tinyint(1) unsigned |	Y|	|展示状态 0隐藏 1展示|
|**索引**
|**主键/索引**|**字段**	|**索引类型**|
|PRIMARY|hobby_id	|主键|

### 1.4 用户爱好表

|user_hobby|||||
| :------- | -------- | --------- | ----------- | ----- |
|**字段名称**	|**字段类型**	|**非空**	|**是否主键**	|**描述**|
|id	|bigint(20) unsigned	|Y	|主键	|自增id|
|user_id	|int(10) unsigned	|Y	|	|用户id|
|hobby_id	|smallint(5) unsigned	|Y	|	|爱好id|
|**索引**
|**主键/索引**	|**字段**	|**索引类型**|
|PRIMARY	|id	|主键|

### 1.5 第三方登录关联表

|user_open_mapping|||||
| :------- | -------- | --------- | ----------- | ----- |
|**字段名称**	|**字段类型**	|**非空**	|**是否主键**	|**描述**|
|user_id	|int(10) unsigned	|Y	|Y	|用户ID|
|open_id	|varchar(32)	|Y	|	|第三方用户名，例微博账号|
|open_type	|tinyint(1) unsigned	|Y	|	|第三方类型 1:新浪微博 2:微信|
|索引|
|主键/索引	|字段	|索引类型|
|PRIMARY	|user_id	|主键|
|index_openid	|open_type,open_id	|唯一索引|

### 1.6 设备表

|device|||||
| :------- | -------- | --------- | ----------- | ----- |
|**字段名称**	|**字段类型**	|**非空**	|**是否主键**	|**描述**|
| device_id	| bigint(20) unsigned | Y | Y | 设备id |
| device_type | varchar(60) | Y | | 设备类型 Android/iphone/ipad. |
| device_token | varchar(36) | Y | | 设备唯一标识 |
| app_version | varchar(10) | Y | | app版本 |
| os_version  | varchar(10) | Y | | 系统版本 |
| app_src   | varchar(10) | Y | | 渠道号 |
| imei      | char(15)   |   | | IMEI |
| mac_id    | char(32)   |   | | 移动设备MAC地址md5值 |
| last_login | timestamp |  | | 最后登录时间 |
| last_user | int(10) unsigned | | | 最后登录用户id |
| create_time | timestamp | Y | | 创建时间 |
| update_time | timestamp |   | | 最新采集时间 |

### 1.7 用户设备表

|user_device|||||
| :------- | -------- | --------- | ----------- | ----- |
|**字段名称**	|**字段类型**	|**非空**	|**是否主键**	|**描述**|
| user_id   | int(10) unsigned | Y | | 用户id |
| device_id	| bigint(20) unsigned | Y |  | 设备id |
| device_type | varchar(60) | Y | | 设备类型 Android/iphone/ipad. |
| device_token | varchar(36) | Y | | 设备唯一标识 |
| app_version | varchar(10) | Y | | app版本 |
| os_version  | varchar(10) | Y | | 系统版本 |
| app_src   | varchar(10) | Y | | 渠道号 |
| imei      | char(15)   |   | | IMEI |
| mac_id    | char(32)   |   | | 移动设备MAC地址md5值 |
| last_login | timestamp |  | | 最后登录时间 |
| last_active | timestamp |  | | 最后激活时间 |
| create_time | timestamp | Y | | 创建时间 |
| update_time | timestamp |   | | 最新采集时间 |

## 2 兴趣组

> 兴趣组数据表加前缀life_

### 2.1 兴趣组表

|interest_groups|||||
| :------- | -------- | --------- | ----------- | ----- |
|**字段名称**	|**字段类型**	|**非空**	|**是否主键**	|**描述**|
|group_id	|int(10) unsigned	|Y	|主键	|兴趣组ID，自增|
|group_name	|varchar(15)	|Y	|	|兴趣组名|
|user_id	|int(10) unsigned	|Y	|	|创建者ID|
|description	|varchar(150)	|Y	|	|兴趣组描述|
|category_id	| smallint(4) unsigned	| Y	|	| 分类 |
|logo_id	|varchar(100)	|	|	|兴趣组logo |
|logo_host	|smallint(2)	|	|	|logo host|
|status	|tinyint(1) unsigned	|Y	|	|审核状态 0审核中 1审核通过 2审核未通过|
|create_time	|timestamp	|Y	|	|创建时间|
|update_time	|timestamp	|	|	|最后更新时间|
|**索引**|
|**主键/索引**	|**字段**	|**索引类型**|
|PRIMARY	|group_id	|主键|

### 2.2 兴趣组小区关联

|interest_group_community|||||
| :------- | -------- | --------- | ----------- | ----- |
|**字段名称**	|**字段类型**	|**非空**	|**是否主键**	|**描述**|
| group_id	| int(10) unsigned	| Y	| 	| 兴趣组id |
| community_id	| int(10) unsigned	| Y	|	| 小区id |
|**索引**|
|**主键/索引**	|**字段**	|**索引类型**|
|PRIMARY	|group_id	|主键|

### 2.3 兴趣组成员

|interest_group_member|||||
| :------- | -------- | --------- | ----------- | ----- |
|**字段名称**	|**字段类型**	|**非空**	|**是否主键**	|**描述**|
| id		| bigint(20)	|Y	|主键	|自增|
| group_id	| int(10) unsigned	| Y	| 	| 兴趣组id |
| user_id	| int(10) unsigned	| Y	|	| 用户id |
| status    | tinyint(1)        | Y |   | 状态 1加入 2退出 |
|**索引**|
|**主键/索引**	|**字段**	|**索引类型**|
|PRIMARY	|id	|主键|

### 2.4 话题表

|topics|||||
| :------- | -------- | --------- | ----------- | ----- |
|**字段名称**	|**字段类型**	|**非空**	|**是否主键**	|**描述**|
|topic_id	|bigint(10) unsigned	|Y	|主键	|话题ID，自增|
|group_id	|int(10) unsigned	|Y	|	|兴趣组ID|
|user_id	|int(10) unsigned	|Y	|	|作者ID|
|title	|varchar(32)	|Y	|	|话题标题|
|image_id	|varchar(100)	|	|	|默认图片id|
|image_host	|smallint(2)	|	|	|图片所在host|
|status	|tinyint(1) unsigned	|Y	|	|状态 1正常发布 2正常删除 3违规删除|
|view_count | int(10) unsigned | | | 浏览数量 |
|reply_count | int(10) unsigned | |  | 回复数量 |
|create_time	|timestamp	|Y	|	|创建时间|
|update_time	|timestamp	|	|	|最后更新时间|
|**索引**|
|**主键/索引**	|**字段**	|**索引类型**|
|PRIMARY	|topic_id	|主键|

### 2.5 话题内容表

|topic_content|||||
| :------- | -------- | --------- | ----------- | ----- |
|**字段名称**	|**字段类型**	|**非空**	|**是否主键**	|**描述**|
| topic_id	| bigint(20) | Y | | 话题id |
| content	| varchar(500)| Y | | 内容 |
| images	| varchar(500)|	 | | 图片json|
|**索引**|
|**主键/索引**	|**字段**	|**索引类型**|
|PRIMARY	|topic_id	|主键|

### 2.6 用户喜欢的话题表
|topic_like|||||
| :------- | -------- | --------- | ----------- | ----- |
|**字段名称**	|**字段类型**	|**非空**	|**是否主键**	|**描述**|
| id		| bigint(20) unsigned | Y | Y | 自增id |
| topic_id	| bigint(20) | Y | | 话题id |
| user_id	| int(10) unsigned | Y | | 用户id |
|**索引**|
|**主键/索引**	|**字段**	|**索引类型**|
|PRIMARY	|id	|主键|

### 2.7 回复表

|posts|||||
| :------- | -------- | --------- | ----------- | ----- |
|**字段名称**	|**字段类型**	|**非空**	|**是否主键**	|**描述**|
|post_id	| bigint(10) unsigned	|Y	|主键	|回复ID，自增|
|user_id	| int(10) unsigned	|Y	|	|回复者ID|
|topic_id	| bigint(10) unsigned	|Y	|	|话题ID|
|at_who		| int(10) unsigned		|Y	|	|回复给的用户|
|content	| varchar(140)	| Y	|	| 回复内容 |
|status	|tinyint(1) unsigned	|Y	|	|状态 1正常发布 2违规删除|
|create_time	|timestamp	|Y	|	|创建时间|
|update_time	|timestamp	|	|	|最后更新时间|
|**索引**|
|**主键/索引**	|**字段**	|**索引类型**|
|PRIMARY	|post_id	|主键|

### 2.8 反馈表（包含申请、举报信息）

|feedback|||||
| :------- | -------- | --------- | ----------- | ----- |
|**字段名称**	|**字段类型**	|**非空**	|**是否主键**	|**描述**|
|id	|bigint(10) unsigned	|Y	|主键	|自增|
|type | tinyint(2) unsigned | Y | | 反馈类型 1小区申请 2话题举报 3回复举报 |
|detail| varchar(100) | Y | | 反馈内容，json字符串 |
|is_handled	|tinyint(1)	| Y	|	| 是否已处理 0未处理 1已处理|
|create_time	|timestamp	|Y	|	|反馈时间|
|update_time	|timestamp	|	|	|处理时间|
|**索引**|
|**主键/索引**	|**字段**	|**索引类型**|
|PRIMARY	|id	|主键|

## 3 城市/小区

### 3.1 城市
|city|||||
| :------- | -------- | --------- | ----------- | ----- |
|**字段名称**	|**字段类型**	|**非空**	|**是否主键**	|**描述**|
| city_id       | smallint(5)   | Y         | Y             | 城市id |
| city_name     | varchar(30)   | Y         |               | 城市名 |

### 3.2 人气小区
|hot_community|||||
| :------- | -------- | --------- | ----------- | ----- |
|**字段名称**	|**字段类型**	|**非空**	|**是否主键**	|**描述**|
|id             | int(10) unsigned | Y      | Y         | 自增id |
|city_id        | smallint(4) unsigned | Y  |           | 城市id |
|community_id   | int(10) unsigned | Y      |          | 小区id |
|create_time	|timestamp	|Y	|	|反馈时间|
|update_time	|timestamp	|	|	|处理时间|
|**索引**|
|**主键/索引**	|**字段**	|**索引类型**|
|PRIMARY	|id	|主键|

### 4.1 其他数据结构

* 兴趣组关注数量。使用Redis的String存储：

	key：	group:$id:member_count

	value：	可通过incr/decr自增自减，或手动加减。

* 话题浏览/回复数量。使用Redis的Hash存储（定期刷到数据库中）：

	key：	topic:$topic_id:count （view：123， reply：123）

