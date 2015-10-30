#作用

更新物业（包括楼盘和商铺）相关solr

#数据源

- 楼盘数据源

表描述|数据库.表名|备注|
:---------------|:---------------|:---------------|
楼盘基础信息表|jinpu_db.e_property|
价格表|jp_dw_stats.dw_jp_midprice_monthly|

- 物业数据源

表描述|数据库.表名|备注|
:---------------|:---------------|:---------------|
楼盘基础信息表|jinpu_db.e_property|
价格表|jp_dw_stats.dw_jp_midprice_monthly|
新盘新铺信息表|jinpu_db.e_property_new_info|
团购表|jinpu_db.e_property_activity|
新盘新铺内容信息表|jingpu_db.e_property_new_content|
新盘新铺单位面积价格|jinpu_db.e_property_new_price|

#solr信息

- building：[http://git.corp.anjuke.com/_user_site/doc/browse/master/jinpu/solr/Building.md]
- property:[http://git.corp.anjuke.com/_user_site/doc/browse/master/jinpu/solr/Property.md]

#命令

php launcher.php Property_Solr --action=property --city_id=16

#参数说明

参数|描述|值|
:---------------|:---------------|:---------------|
action|building|更新某个城市非路楼盘solr列表（building）
 |property|更新某个城市非路物业solr列表（property+xinpan）
 |road|更新路的solr
 |rebuild_one|更新某个物业的solr信息（property+building）
 |check|使某个城市的无效的物业的solr的下线（building+property）
 |recheck|使某个城市物业solr中无效的物业下线，跟参数type(building,property)共用
 |delete|使某个物业无效（building+property）
 |revise_stage|
 |set_sub_stage|纠正新盘的分段分组（xinpan）
 |delete_shop_state|下掉某个城市的所有的纯商铺（xinpan）
city_id|城市id|
id|某个物业的id（可选）|
is_new|是否是新盘（可选）|默认否

#详细
- 更新某个城市非路物业solr列表
 - 执行时间
 
    每天的7：00执行

 - 执行命令
 
    php launcher.php Property_Solr --action=building --city_id=16
    
    php launcher.php Property_Solr --action=property --city_id=16
 - 流程图
 
  ![流程图](img/sp_building_updown.png)
 - 依赖监听程序，封装为shell
 
生产者，发送消息程序solr/property_solr.sh

>
    #!/bin/bash
    #生产者，发送消息程序（物业-包括楼盘更新solr）
    <br/>
    d=$(date +%F)
    building_log="/home/www/logs/jinpu/solr/building_send_solr_${d}.log"
    property_log="/home/www/logs/jinpu/solr/property_send_solr_${d}.log"
    cities="11 12 13 14 15 16 17 18 19 20 26"
    for city_id in ${cities}
    do
        /usr/local/php-5.3.26/bin/php app-jobs/launcher.php Property_Solr --action=building --city_id=${city_id} >> ${building_log}
        /usr/local/php-5.3.26/bin/php app-jobs/launcher.php Property_Solr --action=property --city_id=${city_id} >> ${property_log}
    done

消费者，处理消息程序solr/property_daemon.sh

>
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