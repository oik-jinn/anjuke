## API整理－二手房

### 获取区域板块
* api接口

    ```
    http://api.beckyxu.dev.anjuke.com/mobile/1.3/city.get?cityid=11&is_nocheck=1
    
    anjuke_mobile_api.V1b_City_getController
    
    1.根据id获取城市信息
    select cityid as id,CityName as name from ajk_citytype where cityid=?
    如果需要pinyin（pinyin＝1），则同时查询拼音的信息
    select cityid as id,CityName as name, PinYin from ajk_citytype where cityid=
    
    2.获取区域板块
    anjuke_db
    select TypeId as id, TypeName as name, ParentId as parentId, NameCode,TypeRank from ajk_commtype where TypeFlag=0 and ParentId = ? order by TypeRank
    
    select TypeId from ajk_commtype where CityId=?
    
    问题：
    select TypeId from ajk_commtype where CityId=?
    以上查询没有限定条件 查出来是多条 当数据无序时 拿取第一条不一定是城市的 之前没问题有点侥幸了 
    
    ```

### 获取列表页信息

* api接口

    ```
    http://api.anjuke.com/anjuke/4.0/property/list?app=i-ajk&area_id=108&block_id=115&cid=-1&city_id=79&cv=ver8.7&from=mobile&i=59ADAA8A-E3FA-4CA5-BA6A-201505071611&lat=0.000000&lng=0.000000&m=iPhone%2520Simulator&macid=756a10e765d51c40064a16349c0da742&o=iPhone%2520OS&ostype2=ios8&page=1&page_size=25&pm=A01&qtime=20150507165928&udid2=F46C3148-AC7C-4FD7-81D3-201505071110&uuid2=59ADAA8A-E3FA-4CA5-BA6A-201505071611&uuid=F46C3148-AC7C-4FD7-81D3-201505071110&v=8.3&is_nocheck=1
    
    solr数据调用pc接口：
    http://shanghai.anjuke.com/api/search/?verify=bc9cdcf54017cf002739a8153f5c90b9&searchtype=sale&city_id=79&areacode=001601080115&page=1&pagesize=25&resulttype=1&divrule=500&maptype=1&isauctionrec=2&sortdistinceauction=0&hpcount=15&hpscroll=1&facet_mincount=1&islist=1&source_type=A-C-E-F
    
    房源信息完善
    个人房源：anjuke_db.ajk_pprops_sale
    经纪人房源
        DB:
            11:property_db_sh
            14:property_db_bj
            >=42:property_db_04
            other:propertys_other_db
        TB:
            ajk_propertys
    ```

### 获取单页房源信息
* api接口

    ```
    http://api.anjuke.com/mobile/1.3/property.getdescandcomms?api_key=d945dc04a511fcd7e6ee79d9bf4b9416&app=i-ajk&cid=-1&cv=ver8.7&from=mobile&guid=756a10e765d51c40064a16349c0da742&i=EAB8B0B0-42CF-4AA4-B15B-201504231610&id=11-278851709&m=iPhone%2520Simulator&macid=756a10e765d51c40064a16349c0da742&note=%7B%0A%20%20%22type%22%20%3A%20%22%22%0A%7D&o=iPhone%2520OS&ostype2=ios8&pm=A01&qtime=20150427185212&source_type=1&udid2=E220732A-888B-4A3D-829A-201504231608&uuid2=EAB8B0B0-42CF-4AA4-B15B-201504231610&uuid=EAB8B0B0-42CF-4AA4-B15B-201504231610&v=8.3&sig=f46dc8a689bc6f266829b2511201d6e7&is_nocheck=1
    
    街景信息street_info
    
    当只有城市id和房源id时 调用下面的接口（如猜你喜欢列表点进单页）
    http://api.anjuke.com/mobile/1.3/property.get?api_key=d945dc04a511fcd7e6ee79d9bf4b9416&app=i-ajk&cid=-1&cv=ver8.7&from=mobile&i=DFEB0AA3-3F4D-4F49-A147-201505121704&id=11-283680515&m=iPhone%2520Simulator&macid=756a10e765d51c40064a16349c0da742&o=iPhone%2520OS&ostype2=ios8&pm=A01&qtime=20150513174057&udid2=715CA979-2530-47F0-8107-201505110939&uuid2=DFEB0AA3-3F4D-4F49-A147-201505121704&uuid=DFEB0AA3-3F4D-4F49-A147-201505121704&v=8.3&sig=1cc4c2e0cd5bbad268eb8e0c4cb063bd&is_nocheck=1
    
    
    ```
    
### 获取单页推荐
* api接口

    ```
    http://api.anjuke.com/anjuke/4.0/property/rec/page?debug=1&is_nocheck=1&app=i-ajk&cid=13&cityid=78&cv=8.6&from=mobile&i=A4358EA7-8507-4EF5-B242-201505261633&limit=18&m=iPhone&macid=0f607264fc6318a92b9e13c65db7cd3c&o=iPhone%252520OS&ostype2=ios8&pm=A01&proid=275177645&qtime=20150526163912&rec_from=app_sale_page&show_distance=1&udid2=A4358EA7-8507-4EF5-B242-201505261633&uuid2=A4358EA7-8507-4EF5-B242-201505261633&uuid=A4358EA7-8507-4EF5-B242-201505261633&v=8.3
    
    http://www.anjuke.com/rec/sale/pagerec?cityid=78&proid=275177645&limit=18&from=app_sale_page

    ```
    
