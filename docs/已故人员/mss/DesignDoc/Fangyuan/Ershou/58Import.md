## 58二手房源导入方案

#### 数据导入流程

```
58.com的二手房POST数据到
↓
api.anjuke.com
↓
写到user_prop_*_db（房源相关DB）
↓
api.anjuke.com发信息到中间件
↓
读取flag（1:新增(上架),2:更新（根据房源基本表state字段，判断房源信息更新or房源下架）
↓
更新/删除solr

```
	
* 通知展示端中间件
    * 测试环境：http://nydus.dev.anjuke.com/publish?tunnel=esf_prop_from_wuba
    * 线上环境：http://nydus.a.ajkdns.com/publish?tunnel=esf_prop_from_wuba_2
    * 消息格式:JSON
    
	    ```
	    ｛
			    "pro_id":房源ID(int),
			    "city_id":城市ID(int),	
			    "flag":操作类型(int,1:房源新增,2:房源更新),
			    "update_time":更新时间(int),
			    "from_type":标记类型，默认为0，非必要字段,建议用(100-199之间)(int)
		  ｝
		```

#### 数据存储方案
* 说明：
	* 以每个城市一张表的策略建表
	* 上海北京各单独一个
	* 18个重点二三线城市分散在余下的3个db里
	* 剩余的城市以 city_id mod 3 分在余下的3个db,以后新开的城市也以这个规则来分配

* db名称(已有)
	* user\_prop\_sh\_db
	* user\_prop\_bj\_db
	* user\_prop\_s00\_db
	* user\_prop\_s01\_db
	* user\_prop\_s02\_db
	
* 房源基本表:wuba\_property\_{城市ID}

	Field | Type | Required | Description
	---|---|---|---|---|---|---
	id     		  |int(自增)         |Y      | 房源id、
	source\_type        |int        |Y      | 房源类型,58的房源用5
	source\_id        |int        |Y      | 原始房源ID,直接用58的房源id
	city\_id        |int        |Y      | 城市id
	owner\_name      |string        |Y      |经纪人名称
	owner\_phone     |string     |Y      |经纪人电话 
	owner\_photo     |string     |Y      |经纪人图像
	community\_id    |int        |Y      |小区id
	source\_community\_id    |int        |Y      |原始小区id，这是58的房源id
	title           |string     |Y      |房源标题 
	use\_type\_id     |int        |Y      |房屋类型，公寓/别墅/老公房
	build\_year      |int        |Y      | 建造年代
	area\_num        |float      |Y      |面积（单位：平米）   
	unit\_price      |float      |Y      |单价(单位:元) 
	total\_price     |float      |Y      |总价(单位:元) 
	room\_num        |int        |Y         |房间数量 几室
	hall\_num		   |int        |Y         |客厅数量
	toilet\_num      |int        |Y         |厕所数量 
	fitment\_id       |int        |Y         |装修类型，58的直接用0
	house\_orient    |string     |Y         |房屋朝向 	floor           |int        |Y         |房源所在的楼层数
	floor\_num       |int        |Y         |楼层总数  
	tag             |string     |N         |房源标签
	post_timestamp      |int        |Y         |发房时间,精确到秒
	state           |int        |Y         |默认为0有效,1:违规,2:删除
	default_image  |string        |Y         |发房时间,精确到秒
	update_datetime     |timestamp       |N         |更新时间
	
* 房源扩展表:wuba\_property\_extend_{城市ID}

	Field | Type | Required | Description
	---|---|---|---|---|---|---
	id     		  |int         |Y      | 房源id，对应基本表中的id
	add_explain           |test        |N         |房源描述
	extend_field  |text        |N         |方便以后扩展，可以为空
	update_datetime     |timestamp       |N         |更新时间
	
* 房源发布表:wuba\_property\_publicsh_{城市ID}

	Field | Type | Required | Description
	---|---|---|---|---|---|---
	id     		  |int         |Y      | 房源id，对应基本表中的id
	created           |timestamp        |N         |发布时间
	expiration_time  |timestamp        |N         |过期时间
	updated     |timestamp       |N         |更新时间
	
* 房源发布表:wuba\_property\_publicsh_{城市ID}

	Field | Type | Required | Description
	---|---|---|---|---|---|---
	id     		  |int         |Y      | 房源id，对应基本表中的id
	created           |timestamp        |N         |发布时间
	expiration_time  |timestamp        |N         |过期时间
	updated     |timestamp       |N         |更新时间
    


#### 房源展示的solr方案

* 目前暂定新建一组solr给58导入的房源使用，
* <b>如果使用二手房同组solr，字段会和二手房四组solr一致</b>
* 以下数据由job读取中间件数据更新到solr中
* Solr基本字段

	Field | Type | Indexed | Stored | Required | Description
	---|---|---|---|---|---|---
	id     		  |int        |Y         |Y        |Y      | 房源ID  
	usetype        |int        |Y         |Y         |Y      |类型ID(老公房／公寓....)  
	city\_id        |int        |Y         |Y         |Y      |城市ID
	creation       |int        |Y         |Y         |Y      |发布时间(时间戳) 
	lastedit       |int        |Y         |Y         |Y      |上次编辑时间(时间戳)
	area\_code      |int        |Y         |Y         |Y      |区域代码  
	price          |int        |Y         |Y         |Y      |总价格（单位：元）   
	unit\_price     |tint        |Y         |Y         |Y      |单价（单位：元）
	area           |tfloat        |Y         |Y         |Y      |面积（单位：平米）   
	room\_num       |tint        |Y         |Y         |Y      |房间数(几室) 
	hall\_num       |int        |Y         |Y         |Y      |客厅数(几厅) 
	toilet\_num     |int        |Y         |Y         |Y      |卫生间(几卫)  
	house_age		  |int        |Y         |Y         |Y      |房屋年龄
	title          |text        |Y         |Y         |Y      |房源标题  
	address        |text       |Y         |Y         |Y      |小区地址
	commid         |int        |Y         |Y         |Y      |小区（楼盘）ID
	community\_name |int        |Y         |Y         |Y      |小区（楼盘）名称
	floor          |string        |Y         |Y         |Y      |楼层（第多少层）  
	floor\_total    |string        |Y         |Y         |Y      |楼层总数    
	ishq           |int        |Y         |Y         |Y      |是否优质房源（多图）
	host\_id        |string        |Y         |Y         |Y      |默认图HostId
	image\_id       |string     |Y         |Y         |Y      |默认图Hash
	region\_name    |string     |Y         |Y         |Y      |区域名称
	block\_name     |string     |Y         |Y         |Y      |版块名称

	* 其它solr字段说明

	Field | Type | Indexed | Stored | Required | Description
	---|---|---|---|---|---|---
	source\_type    |string        |Y         |Y        |Y      | 房源类型，58是E 
	unique\_id        |string        |Y         |Y         |Y      |唯一键，即source\_type+id
	islist    |boolean        |Y         |Y        |Y      | 在列表页显示的房源 














