# 58租房导入房源的发布生命流程

### 经纪人录入数据表

### 58房源表

* 除上海、北京的分库命名，user_prop_s0{城市id % 3}_db

db  | table
--- | ---
user_prop_sh_db  | zf_wuba_prop_11
user_prop_s00_db | zf_wuba_prop_12
user_prop_s01_db | zf_wuba_prop_13
user_prop_bj_db  | zf_wuba_prop_14
user_prop_s00_db | zf_wuba_prop_15
user_prop_s01_db | zf_wuba_prop_16
user_prop_s02_db | zf_wuba_prop_17
user_prop_s00_db | zf_wuba_prop_18
... | ...


### 消息队列

#### 发送上下架消息

* 请求入参说明表

key | description
--- | ---
pro_id | 房源id
city_id | 城市id
flag | 上下架标识 1:新增,2:更新
update_time | 时间戳
from_type | 跟踪错误用，标记消息来源，已知的含义见下表


* from_type 含义表

value | description
--- | ---
145 | 该下没下租房就数据消息重发


```
# pg
$ http://nydus.dev.anjuke.com/publish?tunnel=zf_wuba_prop_updown

# ga
$ http://nydus.a.ajkdns.com/publish?tunnel=zf_wuba_prop_updown_2
```

* 使用举例

```
$ curl "http://nydus.dev.anjuke.com/publish?tunnel=zf_wuba_prop_updown" \
-X POST -d '{"pro_id":1168365,"city_id":11,"flag":2,"update_time":137000000,"from_type":123}'
```

#### 落表

* 落表job

```
# 基本格式
$ php launcher.php --class=Zufang_Job_Rent_Spread_Event58QueueAccept

# pg
$ /home/www/user/job-script/while_true.sh php /home/www/usersite/jobs/launcher.php \
--load_path=pmt-26691-site --job_path=app-zufang-job --class=Zufang_Job_Rent_Spread_Event58QueueAccept (20150428运行中)

# ga(单job)
$ /home/www/user/job-script/while_true.sh php /home/www/user/usersite/app-zufang-job/launcher-beta.php \
--class=Zufang_Job_Rent_Spread_Event58QueueAccept
```

* 分表对房源id%10，按月分表

db | table
--- | ---
queue_db | zf_wuba_event_queue_00_201503
queue_db | zf_wuba_event_queue_01_201503
... | ...
queue_db | zf_wuba_event_queue_09_201503
queue_db | zf_wuba_event_queue_00_201504
... | ...


### 上下架

* 上下架job

```
# 基本
$ php launcher.php --class=Zufang_Job_Rent_Spread_WubaRentRunJob

# pg
$ /home/www/user/job-script/while_true.sh php /home/www/usersite/jobs/launcher.php \
--load_path=pmt-27116-site --job_path=app-zufang-job --class=Zufang_Job_Rent_Spread_WubaRentRunJob --tab_split=01

# ga(10个job)
$ /home/www/user/job-script/while_true.sh php /home/www/user/usersite/app-zufang-job/launcher.php \
--class=Zufang_Job_Rent_Spread_WubaRentRunJob --tab_split=00
```



