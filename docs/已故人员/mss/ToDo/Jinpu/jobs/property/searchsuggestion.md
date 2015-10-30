搜索建议job信息  
=====================
------------------

####1 . job作用

```
更新搜索建议solr信息
```
####2 . 执行时间

```
每周一7:30执行
```

####3 . 执行命令
* 发送程序：
```
php launcher.php Building_SearchSuggestion --operation=rebuild
```

* 依赖监听程序：
```
php launcher.php Amqp_Consumer_SearchSuggestion --action=consume
```

#### 4 . 执行命令
参数|描述|值|
:---------------|:---------------|:---------------
operation|操作|rebuild
month_id|可选，某个月份的价格信息|默认当月，格式为“2014M05”
city_id|可选，城市id

#### 5 . 数据
* 搜索建议solr信息：[http://git.corp.anjuke.com/_user_site/doc/browse/master/jinpu/solr/SearchSuggestion.md]

#### 6 . 主要处理流程

* 1.获取当月有价格信息的有效楼盘，每次取100个楼盘循环
 
* 2.组装数据，如果支持amqp则发送amqp信息，如果不支持amqp，则直接更新solr
 
* 3.amqp监听程序则监听并处理得到的数据

### 备注
* 脚本迁移todolist（包括环境、配置、封装shell文件）

```
http://git.corp.anjuke.com/_user_site/doc/browse/master/jinpu/jobs/todolist/search_suggeation_todolist.md
```
