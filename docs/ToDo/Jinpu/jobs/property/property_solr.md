
物业solr更新  
=====================
------------------

## 1 . job作用
```
更新物业（包括楼盘和商铺）相关solr

```

## 2 . 数据源
* building ： [http://git.corp.anjuke.com/_user_site/doc/browse/master/jinpu/solr/Building.md]

* property : [http://git.corp.anjuke.com/_user_site/doc/browse/master/jinpu/solr/Property.md]

## 3 . job命令
```
php launcher.php Property_Solr --action=property

```

## 4 . job参数定义

参数|描述|值|
:---------------|:---------------|:---------------|
action|building|更新某个城市非路楼盘solr列表（building）
 | property|更新某个城市非路物业solr列表（property+xinpan）
 |road|更新路的solr
 |rebuild_one|更新某个物业的solr信息（property+building）
 |check|使某个城市的无效的物业的solr的下线（building+property）
 |recheck|使某个城市物业solr中无效的物业下线，跟参数type(building,property)共用
 |delete|使某个物业无效（building+property）
 |revise_stage
 |set_sub_stage| 纠正新盘的分段分组（xinpan）
 |delete_shop_state|下掉某个城市的所有的纯商铺（xinpan）
city_id|城市id
id|某个物业的id（可选）
is_new|是否是新盘（可选）|默认否

## 5 . 详细列表信息

### 5.1 更新某个城市非路物业solr列表

#### 1 . 执行时间

```
每天的7:00执行
```

#### 2 . 执行命令
* 需要shell文件进行封装（循环所有城市）,并生成日志
* 发送程序：

```
php launcher.php Property_Solr --action=building --city_id=16
php launcher.php Property_Solr --action=property --city_id=16

```
* 依赖监听程序（property+building）

```
[http://git.corp.anjuke.com/_user_site/doc/browse/master/jinpu/jobs/amqp/daemon.md]
```

#### 3 . 主要处理流程

* 1.获取非路的楼盘，每次取100个楼盘循环
 
* 2.组装数据，如果支持amqp则发送amqp信息，如果不支持amqp，则直接更新solr
 
* 3.amqp监听程序则监听并处理得到的数据


### 5.2 更新某个城市物业solr列表中物业的下线

#### 1 . 执行时间

```
每天的7:00执行
```

#### 2 . 执行命令
* 需要shell文件进行封装（循环所有城市）,并生成日志
* 发送程序：

```
php launcher.php Property_Solr --action=recheck --type=property --city_id=16
php launcher.php Property_Solr --action=recheck --type=building --city_id=16

```
* 依赖监听程序（property+building）

```
[http://git.corp.anjuke.com/_user_site/doc/browse/master/jinpu/jobs/amqp/daemon.md]
```

#### 3 . 主要处理流程

* 1.获取solr中在线的物业，每次取200个物业循环
 
* 2.判断物业是否无效，如果无效则需下线。如果支持amqp则发送amqp信息，如果不支持amqp，则直接更新solr
 
* 3.amqp监听程序则监听并处理得到的数据


### 5.3 更新某个物业的solr信息

#### 1 . 执行时间

```
无
```

#### 2 . 执行命令
* 更新某个物业的solr信息（building+property）：

```
php launcher.php Property_Solr --action=rebuild_one --id=16

```
* 使某个物业无效（building+property）：

```
php launcher.php Property_Solr --action=delete --id=16

```

* 依赖监听程序（property+building）

```
[http://git.corp.anjuke.com/_user_site/doc/browse/master/jinpu/jobs/amqp/daemon.md]
```

#### 3 . 主要处理流程

* 更新某个物业的solr信息（building+property）：

```
1.获取传入的物业id，如果物业有效则更新信息
 
2.如果支持amqp则发送amqp信息，如果不支持amqp，则直接更新solr
 
3.amqp监听程序则监听并处理得到的数据

```
* 使某个物业无效（building+property）：

```
1.获取传入的物业id，更改数据库中物业的状态，使之无效。
 
2.删掉solr中该物业，如果支持amqp则发送amqp信息，如果不支持amqp，则直接更新solr
 
3.amqp监听程序则监听并处理得到的数据

```

### 备注
* 脚本迁移todolist（包括环境、配置、封装shell文件）

```
http://git.corp.anjuke.com/_user_site/doc/browse/master/jinpu/jobs/todolist/property_todolist.md
```

