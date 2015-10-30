## 租房房源通知job依赖服务整理

### 说明
```
1. 经纪人上下架和个人上下架未使用消息中间件.
2. 58上下架和58房源更新均使用同一个消息队列表，以下简称58消息通知.
3. job对使用表的延迟不敏感
4. 表访问量重消息队列表行数得出zf_event_queue_00_201509，总行数：628161，一共10个队列表故乘以10，访问量：628161*10/30=20w
```

### 页面模块列表

### 依赖的内部服务(指的是其它仓库的服务)

### 依赖的amqp

|job类型|配置名|exchange|queue|routingkey|AMQPInstanceName|功能|其它|
|--- | --- | --- | --- | --- | --- | --- |--- |
|58消息通知|wuba_prop_event|nydus.46.zf_wuba_prop_updown_2|zf_wuba_prop_updown|zf_wuba_prop_updown|nydus_mq_l2|消息中间件消费||
|精选通知  |rent_auction|nydus.26.zf_prop_auction_updown|rent_auction_queue|prop.auction.updown.#|nydus_mq_l1|消息中间件消费||
|房源更新通知|rent_event|nydus.28.zf_property_event|site_rent_event_queue|prop.event.#|nydus_mq_l1|消息中间件消费||
|套餐通知  |rent_prop_package|nydus.44.zf_prop_package_updown|Rent_Package|prop.package.updown.#|nydus_mq_l1|消息中间件消费||

### 数据库和表:
|job类型 |数据库	|表名称 |作用 |读写   |是否独有|表的访问量(单位天)|
|--- | --- | --- | ---  | --- | ---  | --- |--- |
| 经纪人房源更新通知	|  user_prop_sh_db，user_prop_bj_db，user_prop_s0{index}_db	|     zf_event_queue_{房源id%10}_{年月}|读取更新队列信息信息     |读，写     |否     |20w|
| 58房源通知			|  user_prop_sh_db，user_prop_bj_db，user_prop_s0{index}_db	|     zf_wuba_event_queue_{房源id%10}_{年月}|读取更新队列信息信息 |读，写     |否     |20w|
| 精选通知			|  user_prop_sh_db，user_prop_bj_db，user_prop_s0{index}_db	|     zf_spread_queue_{房源id%10}_{年月}|读取更新队列信息信息     |读，写     |否     |20w|
| 套餐通知			|  user_prop_sh_db，user_prop_bj_db，user_prop_s0{index}_db	|     zf_spread_queue_{房源id%10}_{年月}|读取更新队列信息信息     |读，写     |否     |20w|

