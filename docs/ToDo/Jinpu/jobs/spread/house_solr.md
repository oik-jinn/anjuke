
房源solr更新  
=====================
------------------

## 1 . job作用
```
更新房源（写字楼租售、商铺租售 暂时无个人房源）相关solr

```

## 2 . 数据源
* house ： [http://git.corp.anjuke.com/_user_site/doc/browse/master/jinpu/solr/house.md]

## 3 . job命令
```
1. 房源更新solr的监控程序（通过队列获取要处理的房源）

php launcher.php Spread_QueueHouseUpdate

备注：当前处理进程日志文件（/home/www/jinpu/queue_house_update_progress_file.log）

2. 按计划直接更新房源solr

php launcher.php Spread_HouseRebuild 

```

## 4 . 详细列表信息

### 4.1 房源更新solr的监控程序（通过队列获取要处理的房源）

#### 1 . 执行时间


* 实时运行


#### 2 . 执行命令

* 参数：

```
house_id:可选,房源id
```

* 日志文件：/home/www/logs/result/result_spread_update.log
* 配置：

```
//写字楼和商铺solr地址
$config['house_update_solr_url'] = array(
    'xizhilou_zu' => 'http://jinpu-solr.vm.qa.ajkdns.com:8983/jp-xiezilou-zu/update/',
    'xizhilou_shou' => 'http://jinpu-solr.vm.qa.ajkdns.com:8983/jp-xiezilou-shou-v36/update/',
    'xizhilou_shou_v2' => 'http://jinpu-solr.vm.qa.ajkdns.com:8983/jp-xiezilou-shou-v36/update/',
    'shangpu_zu' => 'http://jinpu-solr.vm.qa.ajkdns.com:8983/jp-shangpu-zu/update/',
    'shangpu_shou' => 'http://jinpu-solr.vm.qa.ajkdns.com:8983/jp-shangpu-shou/update/'
);

//写字楼和商铺更新日志文件(文件里存放的是队列id)
$config['queue_house_update_progress_file'] = '/home/www/jinpu/queue_house_update_progress_file.log';

//屏蔽房源进列表的经纪人
$config['ban_to_list_brokers'] = array(338932,400088,399948,400000,400027,400037);
```

* 监听程序（所有房源，依赖queue_house_update_progress_file.log中的队列id）


#### 3 . 主要处理流程

* 1.读取上次处理的最后一个队列id

* 2.取队列里50条数据，循环

* 3.如果该条数据中房源属于被屏蔽经纪人的，则直接过滤不处理

* 4.房源是删除状态，则删除solr

* 5.不在推广中的定价计划也不在推广中的竞价计划中，则删除solr

* 6.若竞价计划未在推广中，则将房源索引表中的竞价id改为0

* 7.若只有定价计划，且定价计划未在推广中，则删除solr

* 8.若只有竞价计划，且竞价计划未在推广中，则删除solr

* 9.若即有竞价计划又有定价计划：
竞价计划、定价计划均未推广，则删除solr；
房源违规或房源过期，则删除solr
* 10.房源在线，更新solr
* 11.写入处理日志，同时更改queue_house_update_progress_file.log中队列id


### 4.2 按计划直接更新房源solr

#### 1 . 执行时间

```
每天的5:30执行，大概运行5个小时左右
```

#### 2 . 执行命令
* 参数：

```
member_id:可选,经纪人id
spread_id:可选，计划id
```
* 需要shell文件进行封装（依据文件终止进程）,并生成日志
* 日志文件：/home/www/logs/result/result_spread_update.log
* 存放当前将要处理计划的最大id：/tmp/max_spread_id
* 控制是否处理完成的状态文件（==‘over’）：/tmp/rebuild_spread_over
* 配置：

```
//写字楼和商铺solr地址
$config['house_update_solr_url'] = array(
    'xizhilou_zu' => 'http://jinpu-solr.vm.qa.ajkdns.com:8983/jp-xiezilou-zu/update/',
    'xizhilou_shou' => 'http://jinpu-solr.vm.qa.ajkdns.com:8983/jp-xiezilou-shou-v36/update/',
    'xizhilou_shou_v2' => 'http://jinpu-solr.vm.qa.ajkdns.com:8983/jp-xiezilou-shou-v36/update/',
    'shangpu_zu' => 'http://jinpu-solr.vm.qa.ajkdns.com:8983/jp-shangpu-zu/update/',
    'shangpu_shou' => 'http://jinpu-solr.vm.qa.ajkdns.com:8983/jp-shangpu-shou/update/'
);

//屏蔽房源进列表的经纪人
$config['ban_to_list_brokers'] = array(338932,400088,399948,400000,400027,400037);

//写字楼和商铺重建日志文件（存放上次处理的最后一个计划id）
$config['house_rebuild_progress_file'] = '/home/www/jinpu/house_rebuild_progress_file.log';


```

* 不依赖监听程序，直接更新solr
*备注，shell文件执行的时候，先做如下处理

```
rm '/home/www/jinpu/house_rebuild_progress_file.log'
rm '/tmp/rebuild_spread_over'
rm '/tmp/max_spread_id'
```


#### 3 . 主要处理流程

* 1.从house_rebuild_progress_file.log文件中获取开始处理的计划id

* 2.从max_spread_id中获取处理的最大id，若开始处理的计划id大于最大id，则，将‘over’写入终止文件（rebuild_spread_over），并终止，返回false。

* 3.获取5个计划（不包括文件中的计划id），循环判断每个计划的经纪人是否是屏蔽房源进列表的经纪人，如果是，则跳过此计划，如果不是，则继续。

* 4.获取该经纪人创建的计划中的房源

* 5.若获取的房源数据为空，则将此时已经处理过的最大计划id写入house_rebuild_progress_file.log，并终止，放回true


* 6.循环所有房源

* 7.房源处理逻辑同监听程序

* 8.写入日志，将此时已经处理过的最大计划id写入house_rebuild_progress_file.log

#### 4. shell文件详情

```
#!/bin/bash

rm '/home/www/jinpu/house_rebuild_progress_file.log'
rm '/tmp/rebuild_spread_over'
rm '/tmp/max_spread_id'

while [ ! -e "/tmp/rebuild_spread_over" ]; 
do
    date
    /usr/local/php-fpm/bin/php /home/www/jinpu/my-job/launcher.php Spread_HouseRebuild
    sleep 1
done
```


### 备注
* 脚本迁移todolist（包括环境、配置、封装shell文件）

```
http://git.corp.anjuke.com/_user_site/doc/browse/master/jinpu/jobs/todolist/house_todolist.md
```
