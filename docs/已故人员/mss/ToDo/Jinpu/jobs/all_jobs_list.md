##用户行为相关job

####1 . 推送信息
* 执行命令 

    `暂时停止，等待产品介入`
* 详细信息

    http://git.corp.anjuke.com/_user_site/doc/browse/master/jinpu/jobs/property/sendmsg.md 
    
 ------------------

##物业相关job

####1 . 新盘各房源总数

* 执行命令 

    `sh /home/www/config/v2/jobs/jp/bin/new_amount.sh`

* 详细信息

    http://git.corp.anjuke.com/_user_site/doc/browse/master/jinpu/jobs/property/new_amount.md
    
    

####2 . 物业solr

* 详细信息

    http://git.corp.anjuke.com/_user_site/doc/browse/master/jinpu/jobs/property
    
    
####3 . 搜索建议solr

* 发送程序：
```
php launcher.php Building_SearchSuggestion --operation=rebuild
```

* 依赖监听程序：
```
php launcher.php Amqp_Consumer_SearchSuggestion --action=consume
```

* 详细信息

    http://git.corp.anjuke.com/_user_site/doc/browse/master/jinpu/jobs/property/searchsuggestion.md


 ------------------

##房源相关job

####1 . 房源solr

* 1. 房源更新solr的监控程序（通过队列获取要处理的房源）

```
php launcher.php Spread_QueueHouseUpdate

备注：当前处理进程日志文件（/home/www/jinpu/queue_house_update_progress_file.log）
```
* 2. 按计划直接更新房源solr

```
php launcher.php Spread_HouseRebuild 
```
* 详细信息

    http://git.corp.anjuke.com/_user_site/doc/browse/master/jinpu/jobs/spread/house_solr.md

 -------------------

 ####
####备注:
* 线上入口文件路径参考（beta）：

`/opt/local/php-fpm/bin/php /home/www/config/v2/jobs/jp/launcher_beta.php`

* 线上入口文件路径参考（cookie）：

 `export JP_COOKIE_VERSION=9528_17_01;/opt/local/php-fpm/bin/php /home/www/config2/jobs/jp/launcher_cookie.php`
 
* 线上shell文件路径参考：

`/home/www/config/v2/jobs/jp/bin/`

* pg入口文件路径参考：

`php /home/www/v2/jobs/launcher.php pmt-20665-site##jinpu##Building_SearchSuggestion`


* pg上shell文件路径参考：

 `/home/www/jinpu/bin/`
    
  