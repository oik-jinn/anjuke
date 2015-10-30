搜索建议solr
==========================

### 1.环境确认

* 确认rabbitmq服务是否开启。
* 确认amqp的php扩展是否安装以及版本。
* 若没有安装扩展，确认是否ini文件中允许动态加载扩展项配置。

### 2.配置文件，需要外层覆盖

* 需要为job新增一个 amqp.php

```
//amqp
$config['default'] = array(
    'host' => '10.10.3.222',
    'port' => '5672',
    'vhost' => '/',
    'login' => 'guest',
    'password' => 'guest'
);

```

### 3.需要确认文件夹是否存在
```
/home/www/logs/jinpu/solr/searchsuggestion_send_solr.log
/home/www/logs/jinpu/solr/searchsuggestion_process_solr.log
```
### 4.封装为shell文件

##### 4.1 生产者，发送消息程序 searchsuggestion_solr.sh

```
#!/bin/bash

log='/home/www/logs/jinpu/solr/searchsuggestion_send_solr.log'
php launcher.php Building_SearchSuggestion --operation=rebuild > ${log} 
exit;
```
##### 4.2 消费者，处理消息程序 searchsuggestion_daemon.sh
```
#!/bin/bash

log='/home/www/logs/jinpu/solr/searchsuggestion_process_solr.log'
php launcher.php Amqp_Consumer_SearchSuggestion --action=consume  > ${log} 
exit;
```

### 5.布置job到job管理系统

* 每天早上7:30开始运行。