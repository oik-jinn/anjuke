
 



 物业列表的更新
==========================

## 一、物业solr更新

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
/home/www/logs/jinpu/solr/
```
### 4.封装为shell文件

##### 4.1 生产者，发送消息程序solr/property_solr.sh

```
#!/bin/bash
#生产者，发送消息程序（物业-包括楼盘更新solr）

d=$(date +%F)
building_log="/home/www/logs/jinpu/solr/building_send_solr_${d}.log"
property_log="/home/www/logs/jinpu/solr/property_send_solr_${d}.log"
cities="11 12 13 14 15 16 17 18 19 20 26"
for city_id in ${cities}
do
    /usr/local/php-5.3.26/bin/php app-jobs/launcher.php Property_Solr --action=building --city_id=${city_id} >> ${building_log}
    /usr/local/php-5.3.26/bin/php app-jobs/launcher.php Property_Solr --action=property --city_id=${city_id} >> ${property_log}
done

```
##### 4.2 消费者，处理消息程序solr/property_daemon.sh
```
#!/bin/bash
#消费者，处理消息程序（物业-包括楼盘更新solr）

function PROCESS_CREATOR() {
    for i in $(seq 1 ${3})
    do
        nohup ${1} 1>>${2} 2>&1 &
    done
}

NUM_BUIL=5
NUM_PROP=10

DATE_TODAY=$(date +"%F")
LOG_DIR="/home/www/logs/jinpu/solr"

LOG_BUIL="${LOG_DIR}/building_process_solr_${DATE_TODAY}.log"
LOG_PROP="${LOG_DIR}/property_process_solr_${DATE_TODAY}.log"

CMD_BUIL="/usr/local/php-5.3.26/bin/php app-jobs/launcher.php Amqp_Consumer_Building --action=consume"
CMD_PROP="/usr/local/php-5.3.26/bin/php app-jobs/launcher.php Amqp_Consumer_Property --action=consume"

NUM_PROC_BUIL=$(pgrep -f "${CMD_BUIL}" | wc -l)
if [ ${NUM_PROC_BUIL} -lt ${NUM_BUIL} ]
then
    DIFF_BUIL=$(echo ${NUM_BUIL}-${NUM_PROC_BUIL} | bc)
    PROCESS_CREATOR "${CMD_BUIL}" "${LOG_BUIL}" "${DIFF_BUIL}"
fi

NUM_PROC_PROP=$(pgrep -f "${CMD_PROP}" | wc -l)
if [ ${NUM_PROC_PROP} -lt ${NUM_PROP} ]
then
    DIFF_PROP=$(echo ${NUM_PROP}-${NUM_PROC_PROP} | bc)
    PROCESS_CREATOR "${CMD_PROP}" "${LOG_PROP}" "${DIFF_PROP}"
fi
```

### 5.布置job到job管理系统

* 发送的job：每天早上7:30开始运行。
* 处理消息的job：实时


## 二、检查错误的物业solr数据

### 1.环境确认

* 确认rabbitmq服务是否开启。
* 确认amqp的php扩展是否安装以及版本。
* 若没有安装扩展，确认是否ini文件中允许动态加载扩展项配置。
* 依赖物业solr更新的守护进程

### 2.配置文件，需要外层覆盖

* 无

### 3.需要确认文件夹是否存在
```
/home/www/logs/jinpu/solr/
```
### 4.封装为shell文件

##### 4.1 生产者，发送消息程序solr/property_recheck.sh

```
#!/bin/bash
#检查错误的物业solr数据

d=$(date +%F)
lp=/home/www/logs/jinpu/solr/property_recheck_solr_${d}.log
lb=/home/www/logs/jinpu/solr/building_recheck_solr_${d}.log
for i in "11 12 13 14 15 16 17 18 19 20 26"
do
    /usr/local/php-5.3.26/bin/php /home/www/jinpu/my-job/launcher.php Property_Solr --action=recheck --type=property --city-id=${i} >> ${lp}
    /usr/local/php-5.3.26/bin/php /home/www/jinpu/my-job/launcher.php Property_Solr --action=recheck --type=building --city-id=${i} >> ${lb}
done

```


### 5.布置job到job管理系统

* 每天早上7:00开始运行。


## 三、新盘滚动分组

### 1.环境确认

* 无，不依赖监听程序，直接更新solr

### 2.配置文件，需要外层覆盖

* 无

### 3.需要确认文件夹是否存在
```
/home/www/logs/jinpu/solr/
```
### 4.封装为shell文件 xinpan_roll.sh

```
#!/bin/bash
#新盘滚动分组,不依赖守护进程，直接更新solr

d=$(date +%F)
xinpan_roll_log="/home/www/logs/jinpu/solr/xinpan_roll_${d}.log"
cities="11 12 13 14 15 16 17 18 19 20 26"
for city_id in ${cities}
do
    echo "$city_id"
    /usr/local/php-5.3.26/bin/php app-jobs/launcher.php Property_Solr --action=revise_stage --city_id=${city_id} >> ${xinpan_roll_log}
done


sleep 300

d=$(date +%F)
xinpan_roll_log="/home/www/logs/jinpu/solr/xinpan_roll_${d}.log"
for city_id in ${cities}
do      
    echo "$city_id"
    /usr/local/php-5.3.26/bin/php app-jobs/launcher.php Property_Solr --action=set_sub_stage --city_id=${city_id} >> ${xinpan_roll_log}
done

```


### 5.布置job到job管理系统

* 每天早上7:30开始运行。




## 四、新盘各房源总数

### 1.环境确认

* 无

### 2.配置文件，需要外层覆盖

* 无

### 3.需要确认文件夹是否存在
```
/home/www/logs/jinpu/solr/
```
### 4.命令

```
/usr/local/php-5.3.26/bin/php app-jobs/launcher.php Xin_Amount --action=rebuild > /home/www/logs/jinpu/solr/xin_count.log 

```


### 5.布置job到job管理系统

* 每天早上7:30开始运行