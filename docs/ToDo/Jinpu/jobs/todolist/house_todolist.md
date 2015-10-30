
一、  直接rebuild房源solr
==========================
### 1.配置文件，需要外层覆盖

* 线上：

```
//写字楼和商铺solr地址
$config['house_update_solr_url'] = array(
    'xizhilou_zu' => 'http://10.10.6.51:8983/jp-xiezilou-zu/update/',
    'xizhilou_shou' => 'http://10.10.6.51:8983/jp-xiezilou-shou-v36/update/',
    'xizhilou_shou_v2' => 'http://10.10.6.51:8983/jp-xiezilou-shou-v36/update/',
    'shangpu_zu' => 'http://10.10.6.51:8983/jp-shangpu-zu/update/',
    'shangpu_shou' => 'http://10.10.6.51:8983/jp-shangpu-shou/update/'
);

//屏蔽房源进列表的经纪人
$config['ban_to_list_brokers'] = array(338932,400088,399948,400000,400027,400037);

//写字楼和商铺重建日志文件（存放上次处理的最后一个计划id）
$config['house_rebuild_progress_file'] = '/home/www/jinpu/house_rebuild_progress_file.log';
```
* 线下：

```
//写字楼和商铺solr地址
$config['house_update_solr_url'] = array(
    'xizhilou_zu' => 'http://solr.anjuke.test:8983/jp-xiezilou-zu/update/',
    'xizhilou_shou_v2' => 'http://solr.anjuke.test:8983/jp-xiezilou-shou-v36/update/',
    'shangpu_zu' => 'http://solr.anjuke.test:8983/jp-shangpu-zu/update/',
    'shangpu_shou' => 'http://solr.anjuke.test:8983/jp-shangpu-shou/update/'
);

//屏蔽房源进列表的经纪人
$config['ban_to_list_brokers'] = array(338932,400088,399948,400000,400027,400037);

//写字楼和商铺重建日志文件（存放上次处理的最后一个计划id）
$config['house_rebuild_progress_file'] = '/home/www/jinpu/house_rebuild_progress_file.log';
```

* 备注：pg上不需要外层覆盖，线上线下文件设置相同，线上只需要外层覆盖solr的配置

### 2.需要确认文件夹是否存在
```
/home/www/logs/result/
/home/www/jinpu/house_rebuild_progress_file.log
/tmp/rebuild_spread_over
/tmp/max_spread_id
/home/www/logs/solr/update_error.log
```
### 3.封装为shell文件
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
### 4.布置job到job管理系统

* 每天早上5:30开始运行。



二、  通过队列update房源solr
==========================
### 1.配置文件，需要外层覆盖

* 线上：

```
//写字楼和商铺solr地址
$config['house_update_solr_url'] = array(
    'xizhilou_zu' => 'http://10.10.6.51:8983/jp-xiezilou-zu/update/',
    'xizhilou_shou' => 'http://10.10.6.51:8983/jp-xiezilou-shou-v36/update/',
    'xizhilou_shou_v2' => 'http://10.10.6.51:8983/jp-xiezilou-shou-v36/update/',
    'shangpu_zu' => 'http://10.10.6.51:8983/jp-shangpu-zu/update/',
    'shangpu_shou' => 'http://10.10.6.51:8983/jp-shangpu-shou/update/'
);

//屏蔽房源进列表的经纪人
$config['ban_to_list_brokers'] = array(338932,400088,399948,400000,400027,400037);

//写字楼和商铺更新日志文件(文件里存放的是队列id)
$config['queue_house_update_progress_file'] = '/home/www/jinpu/queue_house_update_progress_file.log';
```
* 线下：

```
//写字楼和商铺solr地址
$config['house_update_solr_url'] = array(
    'xizhilou_zu' => 'http://solr.anjuke.test:8983/jp-xiezilou-zu/update/',
    'xizhilou_shou_v2' => 'http://solr.anjuke.test:8983/jp-xiezilou-shou-v36/update/',
    'shangpu_zu' => 'http://solr.anjuke.test:8983/jp-shangpu-zu/update/',
    'shangpu_shou' => 'http://solr.anjuke.test:8983/jp-shangpu-shou/update/'
);

//屏蔽房源进列表的经纪人
$config['ban_to_list_brokers'] = array(338932,400088,399948,400000,400027,400037);

//写字楼和商铺更新日志文件(文件里存放的是队列id)
$config['queue_house_update_progress_file'] = '/home/www/jinpu/queue_house_update_progress_file.log';
```

* 备注：pg上不需要外层覆盖，线上线下文件设置相同，线上只需要外层覆盖solr的配置

### 2.需要确认文件夹是否存在

```
/home/www/logs/result/
/home/www/jinpu/queue_house_update_progress_file.log
/home/www/logs/solr/update_error.log
```
### 3.布置job到job管理系统

* 实时job。

