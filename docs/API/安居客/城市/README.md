## API整理－城市

### 获取城市列表
* api总接口

    ```
    http://api.anjuke.com/mobile/1.3/city.mergedCity
    新开150时新增了三个标识位
    依据配置去判断location.php
    broker_related_open_cities
    map_related_open_cities
    business_related_open_cities
    flag: {
        broker_related_open: "1",
        map_related_open: "1",
        business_related_open: "1"
    }
    
    py读取配置文件
    city.city_pinyin

    ```
* 租房获取城市列表接口

    ```
    老好租app使用
    http://api.haozu.com/mobile/2.0/city.getList?is_nocheck=1
    
    id＋name＋location读取配置文件
    multicity.region
    mobile_api.open_city
    
    initial读取配置文件
    multicity.city_set(拼音pinyin和首字母initial 取pinyin是为了按照pinyin排序)
    
    metro_open
    查询城市的地铁线路rent_db.map_metros_baidu
    
    gmap_info读取配置文件
    googlemaps.gmap_city_mobile
    mobile_api.diff
    ```

### 城市列表更新

### 城市数据和筛选条件更新
* api接口

    ```
    http://api.anjuke.com/mobile/v5/update/city/178?rev=-1&macid=09de9ed2545a20ea864f4cbc16dac2b4&app=a-ajk&_pid=28600&o=omni_m7ul-userdebug+4.4.1+KOT49E+eng.moonlight.20131207.174146+test-keys&from=mobile&m=Android-One&cv=ver8.6&cid=-1&i=355653050458659&v=4.4.1&qtime=20150424103357&pm=dev111111&uuid=ef508d9b-759a-48ab-bafd-212015042410&_chat_id=0
    
    配置文件：update.php
    $config['city_update'] = array(
        'ershou' => array(
            '11' => array('city_rev' => 1399354700, 'filter_rev' => 1399354700),
        ),
        'zufang' => array(
            '11' => array('city_rev' => 1399354700, 'filter_rev' => 1399354700) 
        ) 
    );
    当有城市配置的时候，
    比较接口传过来的rev与配置文件的rev
    如果配置的大，则值为1，即通知api更新
    同时会返回此时新的rev给app，app存在本地，下次请求时候会发送最新的rev
 
    ```