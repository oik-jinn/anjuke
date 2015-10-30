##橱窗主推队列设计
---

### 背景

随着经纪人根据自身情况改变房源橱窗和主推状态，而该状态值放置在memcache中，过期时间为30天，不能及时刷新该状态值,所以设计该消息队列，主要解决以下问题：
```
及时更新memcache中的对应值保证数据的及时性和准确性
```

### 数据更新通知方式
* 走amqp消息中间件
* 流程：经纪人动态改变主推状态->通知消息到中间件->接收消息中间件->更新缓存信息
* 消息格式，以json格式通知，具体如下：
```
{
“broker_id”:经纪人ID(int),
"prop_ids":经纪人更新的所有主推状态值,例如1,2,3
“broker_type”:判断是从sale还是rent来，即房源类型(1:二手房 2.租房)(int)，
“updated_time”:通知数据的时间戳
“flag”:用于标记橱窗显示和主推动态变化的标记值(1:购买橱窗服务2:取消橱窗服务3:(设置对应主推房源4:取消对应主推房源)
} 
```


### 处理队列数据详细流程
* 执行流程

```
常驻job进程执行
↓
获取更新状态数据
↓
更新相关缓存数据
```

### amqp测试环境地址
```
nydus.dev.anjuke.com/publish?tunnel=broker_ad_props
exchange nydus.32.broker_ad_props
```

### amqp线上环境地址
```
nydus.a.ajkdns.com/publish?tunnel=broker_ad_props
exchange nydus.12.broker_ad_props
```

###amqp返回值
```
{"status":"ok","message":"Success","msgId":"537c3d65ebb268df976b99a6”}

status=ok的时候会返回全局唯一的msgId，你们可以记录下这个id方便后面比对数据

status!=ok证明有异常情况

http请求不通的时候建议重试请求一次
```

### 中间件使用实例
```
> curl -d '{"msg":"hello world"}' nydus.dev.anjuke.com/publish?tunnel=broker_ad_props
```
