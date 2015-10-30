##房源地标词/交通线路搜索相关设计
---

### 需求背景

在二手房列表页增加地标词搜索，例如搜索“789号”公交车会显示这条公交线路3公里的房子，目前增加的地标词有如下类型：

* 公交线路
* 公交站点附近
* 医院附近
* 地铁线路

### 搜索逻辑流程
![搜索逻辑流程](ListingSearchLogic.png)

### 数据关联表

* 医院信息：user_prop_db.place_info
* 地铁信息：anjuke_db.sw_metros
* 地铁站点信息：anjuke_db.sw_metro_stations
* 地铁站点和小区关联表：anjuke_db.sw_metro_community_distances
* 公交线路信息：user_prop_db.bus_line
* 公交站点信息：user_prop_db.bus_stations
* 公交站点和小区关联表：ajk_dw_stats.ajk_nearby_relation

### 搜索词库文件修改
* 说明：我们搜索的词库目前是没有公交线路、站点、医院等关键词，如果要支持，则要将这些关键詞录入词库
* 词库地址：http://git.corp.anjuke.com/liangshan/mss-dict/browse/master/anjuke_sale_11.txt
* 词库文件格式：
    * 公交线路：789路:bus,789路,121(公交ID)
    * 公交站点：东方路浦电路:busstation,221(站点ID),10.23,123.45(lat,lng经纬度)
    * 医院：仁济医院:hospital,321(医院ID),10.23,123.45(lat,lng经纬度)
    * 地铁线路：1号线:metro,1号线,321(地铁ID)
* job增加地标词等功能
    * 位置：app-jobs/bin/tools/update_search_dict.php

### solr修改
* 增加字段
    * 公交线路：bus_id int
    * 地铁线路：metro_id int
* 查询
    * 点附近的放源，包括公交站点、医院
        * 获得经纬度，通过范围查询
        * [http://10.10.6.51:8983/ajk-saleprop11/select/...](http://10.10.6.51:8983/ajk-saleprop11/select?start=0&rows=24&fl=score,*&wt=json&qt=standard&fq=%7B%21geofilt%7D&fq=city_id%3A11&fq=islist%3A1&fq=hpstarttime%3A%5B0+TO+1399175861%5D&pt=31.337837292098,121.5446749549&sfield=blocation&d=1&hl=false&sort=ceil%28div%28geodist%28%29%2C0.5%29%29+asc%2Corder_string+desc%2Crank_level+asc%2Cscore+desc%2Crank_sub_level+asc%2Crank_score+desc&q=*:*&fq=hpplanid%3A0)
    * 线附近的放源，包括公交线路、地铁线路
        * 获得对应id，通过id查询
        * [http://10.10.6.51:8983/ajk-saleprop11/select/...](http://10.10.6.51:8983/ajk-saleprop11/select?start=0&rows=15&fl=score,*&wt=json&qt=standard&hl=false&sort=order_string+desc%2Crank_level+asc%2Cscore+desc%2Crank_sub_level+asc%2Crank_score+desc&q=*:*&fq=city_id%3A11&fq=bus_id%3A7360&fq=islist%3A1&fq=hpstarttime%3A%5B0+TO+1399177793%5D&fq=hpplanid%3A0)
* job更新房源solr修改
    * 修改逻辑    
        * 增加公交id
            * 读取公交线路和小区关联表->存在3km的对应关系->写入solr字段bus_id
        * 增加地铁id
            * 读取地铁和小区关联表->存在3km的对应关系->写入solr字段metro_id
    
    * 对应文件
        * 竞价job
            * app-jobs/classes/sale/AuctionUpdateSolr.php
        * 定价job：
            * app-jobs/classes/sale/PricingToSolr.php
            * app-jobs/classes/sale/PricingUpdateSolr.php
        * 个人job
            * app-jobs/classes/sale/PersonalUpdateSolr.php







