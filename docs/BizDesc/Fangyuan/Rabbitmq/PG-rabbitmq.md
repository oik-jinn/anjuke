# PG环境消息中间件

## 部署

* 机器地址 xapp20-061

* 代码目录 /home/www/message-bus

* exchange配置文件地址 /home/www/config/java/message-bus-config.json

* 日志文件目录 /data1/logs/message-bus

* 管理后台地址 http://xapp20-061.i.ajkdns.com:5776/

## 发消息

* url  http://xapp20-061.i.ajkdns.com/publish?tunnel=xx&routingKey=yy

* 协议 POST

* 数据 根据业务需要自己定义，json格式

> 备注
有routingKey必须填写

## 消费消息

* 消费端host配置

```
$config['config_name'] = array(
  'host' => 'xapp20-061.i.ajkdns.com',
  'port' => '5676',  
  'login' => ‘guest',
  'password' => 'guest',
  'vhost' => '/'
);
```

* queue配置

```
$config['config_queue_name'] = array(
  'exchange' => 'exchange_name',
  'queue' => 'queue_name',
  'routingkey' => 'routingkey_name',
);
```

## 线上部署情况参考文档

* http://gitlab.corp.anjuke.com/wbsong/message-bus-rabbitmq/tree/master
* http://gitlab.corp.anjuke.com/wbsong/message-bus/tree/master/message-bus

## Todo

* 缺少监控