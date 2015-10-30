# 经纪人列表页白皮书

## 页面url

    ```
    
        http://{pinyin}.anjuke.com/tycoon/
    
    ```
    
### 页面模块列表
 
* 1 筛选项
* 2 经纪人列表
* 3 中房榜明星经纪人
* 4 seo


### 配置

anjuke-map/app-anjuke/config/ufs.php

new_ufs_city_is_on 

app-anjuke/config/brokercondition.php

skills

birthprovince

app-anjuke/config/multicity.php

false_bulletin_show

false_bulletin_status_unshow

tycoon_broker_open

app-anjuke/config/common.php

solrBrokerServer

### 数据库和表:
|序号|数据库|表名称|作用|读写|是否独有|使用量|
| ---|--- |  --- | --- | --- | --- | --- |
|2 |anjuke_db|ajk_brokerextend|经纪人信息|读|否|1486190*89%*10% = 12w|
|2 |anjuke_db|ajk_commtype|区域/板块|读|否|1486190*89%*10% = 12w|
|2 |anjuke_db|ajk_communitys|小区表|读|否|1486190*89%*10% = 12w|
|2 |ban_words|ban_words|黑名单词语|读|否|1486190*89%*10% = 12w|
|2 |anjuke_db|ajk_broker_ufs_rank_list|最受欢迎经纪人|读|否|1486190*89%*10% = 12w|
|2 |anjuke_db|ajk_broker_evaluate|全部ufs数据|读|否|1486190*89%*10% = 12w|
|2 |anjuke_db|ajk_ufs||读|否|1486190*89%*10% = 12w|

