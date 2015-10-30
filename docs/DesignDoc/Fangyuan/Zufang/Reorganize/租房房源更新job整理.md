## 租房房源更新job依赖服务整理

### 说明
```
1. 58房源更新和58房源上下架job使用同一队列表,该队列表由经纪人端直接插表，未使用消息中间件.
2. 个人房源更新和个人房源上下架使用同一张消息队列表，该队列表由经纪人端直接插表，未使用消息中间件.
3. 经纪人房源更新包括精选套餐房源使用同一消息队列表，http://gitlab.corp.anjuke.com/_site/docs/blob/master/BizDesc/Fangyuan/Zufang/SpreadMiddleware.md。
4. 经纪人房源信息更新和精选房源上架job用于构造solr data所读取的表，依赖的外部api，mc和redis均一致，不再累赘重复。
5. job对使用表的延迟不敏感
6.表访问量重消息队列表行数得出zf_event_queue_00_201509，总行数：628161，一共10个队列表故乘以10，访问量：628161*10/30=20w
```

### 页面模块列表

### 依赖的内部服务(指的是其它仓库的服务)

### 依赖的外部URL

|上下架类型|配置名|URL|功能|其它 |
|--- | --- | --- | --- | --- |

### 数据库和表:
|上下架类型 |数据库	|表名称 |作用 |读写   |是否独有|访问量（单位天）|
|--- | --- | --- | ---  | --- | ---  | --- |--- |
| 经纪人房源更新			|  user_prop_sh_db，user_prop_bj_db，user_prop_s0{index}_db	|     zf_event_queue_{city_id}|读取更新队列信息信息     |读     |否     |20w|
| 经纪人房源更新   		|  user_prop_sh_db，user_prop_bj_db，user_prop_s0{index}_db	|     rent_{city_id}|写入房源基本信息     |读写     |是     |20w|
| 经纪人房源更新   		|  user_prop_sh_db，user_prop_bj_db，user_prop_s0{index}_db	|     rent_extend_{city_id}|写入房源扩展信息     |读写     |是     |20w|
| 经纪人房源更新			|  rent_db													|     prop|读取经纪人房源信息     |读     |否     |20w|
| 经纪人房源更新			|  rent_db													|     prop_images|读取经纪人房源图片信息     |读     |否     |20w|
| 经纪人房源更新			|  user_prop_db												|     rent_index|读取隔离索引     |读写    |否     |20w|

### Memcache&Redis
|上下架类型 |名称 |类型  |地址 |申请大小|使用率|是否统一管理|功能|负责人|
|--- |---  | ---  | --- | ---   | --- | --- | --- | --- |
|经纪人房源图片  |zufang_images_master|redis |10.10.8.26:6387|10G    |5.38G|          是|租房隔离图片| 程启明|

