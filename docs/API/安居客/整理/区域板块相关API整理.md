## 区域板块相关API整理

### 二手房：（7个）
* 二手房区域板块筛选项
http://api.anjuke.com/mobile/1.3/city.get?cityid=168&api_key=eb8cd4ef60fde7580260cf9cf4250a24&sig=c1e366debe0da9413534a96600b24547&v=5.0.2&i=867068022325251&cv=8.8&o=leo-user+5.0.2+LRX22G+V6.6.5.0.LXHCNCF+release-keys&macid=33cd5109c031dccffadb4fcfa277dfea&cid=11&pm=b135&m=Android-MI+NOTE+Pro&app=a-ajk

    ```
    返回结果中的相关字段：
    区域：region
    板块：region.block
    ```
*  首页推荐－猜你喜欢
http://api.anjuke.com/mobile/v5/recommend/sale/home/history?city_id=168&limit=15&uuid=07d8c377-5d8d-4b30-b3a6-b12015071523&cid=-1&pm=b135&m=Android-MI+NOTE+Pro&qtime=20150804115359&from=mobile&app=a-ajk&v=5.0.2&i=867068022325251&_pid=5074&cv=8.8&o=leo-user+5.0.2+LRX22G+V6.6.5.0.LXHCNCF+release-keys&macid=33cd5109c031dccffadb4fcfa277dfea&_chat_id=0
    
    ```
    返回结果中的相关字段：
    区域：properties.area
    板块：properties.block
    ```

* 二手房列表页推荐－猜你喜欢
http://api.anjuke.com/mobile/v5/recommend/sale/list/history?city_id=168&limit=25&offset=0&uuid=07d8c377-5d8d-4b30-b3a6-b12015071523&cid=-1&pm=b135&m=Android-MI+NOTE+Pro&qtime=20150804115515&from=mobile&app=a-ajk&v=5.0.2&i=867068022325251&_pid=5074&cv=8.8&o=leo-user+5.0.2+LRX22G+V6.6.5.0.LXHCNCF+release-keys&macid=33cd5109c031dccffadb4fcfa277dfea&_chat_id=0

    ```
    返回结果中的相关字段：
    区域：properties.area
    板块：properties.block
    ```

* 二手房列表页－列表（一个接口包括新老两个仓库代码都有）
http://api.anjuke.com/anjuke/4.0/property/list?is_nocheck=1&map_type=baidu&area_id=5765&city_id=168&with_broker_recommend=0&page_size=15&block_id=0&uuid=07d8c377-5d8d-4b30-b3a6-b12015071523&cid=-1&pm=b135&m=Android-MI+NOTE+Pro&qtime=20150804115616&from=mobile&app=a-ajk&v=5.0.2&i=867068022325251&_pid=5074&cv=8.8&o=leo-user+5.0.2+LRX22G+V6.6.5.0.LXHCNCF+release-keys&macid=33cd5109c031dccffadb4fcfa277dfea&_chat_id=0

    ```
    返回结果中的相关字段：
    区域：properties.area
    板块：properties.block
    ```

* 二手房单页－房源信息
http://api.anjuke.com/mobile/1.3/property.getdescandcomms?id=168-26415734&guid=95a3bbdc7eaae1ca7215b2c469fa51e5&source_type=5&api_key=eb8cd4ef60fde7580260cf9cf4250a24&sig=8f6968456ac04dc3b8a4e5d93e1c16cb&v=5.0.2&i=867068022325251&cv=8.8&o=leo-user+5.0.2+LRX22G+V6.6.5.0.LXHCNCF+release-keys&macid=33cd5109c031dccffadb4fcfa277dfea&cid=11&pm=b135&m=Android-MI+NOTE+Pro&app=a-ajk

    ```
    返回结果中的相关字段：
    区域：property.area_id
    板块：property.block_id
    ```

* 二手房单页推荐－猜你喜欢
http://api.anjuke.com/mobile/v5/recommend/sale/view/look?city_id=168&price=39&type=5&item=26415734&limit=2&offset=0&uuid=07d8c377-5d8d-4b30-b3a6-b12015071523&cid=-1&pm=b135&m=Android-MI+NOTE+Pro&qtime=20150804115724&from=mobile&app=a-ajk&v=5.0.2&i=867068022325251&_pid=5074&cv=8.8&o=leo-user+5.0.2+LRX22G+V6.6.5.0.LXHCNCF+release-keys&macid=33cd5109c031dccffadb4fcfa277dfea&_chat_id=0

    ```
    返回结果中的相关字段：
    区域：property.area_id
    板块：property.block_id
    ```

* 二手房单页推荐－附近相似房源
http://api.anjuke.com/mobile/v5/recommend/sale/view/near?city_id=168&price=39&type=5&item=26415734&limit=15&offset=0&uuid=07d8c377-5d8d-4b30-b3a6-b12015071523&cid=-1&pm=b135&m=Android-MI+NOTE+Pro&qtime=20150804115724&from=mobile&app=a-ajk&v=5.0.2&i=867068022325251&_pid=5074&cv=8.8&o=leo-user+5.0.2+LRX22G+V6.6.5.0.LXHCNCF+release-keys&macid=33cd5109c031dccffadb4fcfa277dfea&_chat_id=0

    ```
    返回结果中的相关字段：
    区域：property.area_id
    板块：property.block_id
    ```

### 租房：（5个）
* 租房区域板块筛选项
http://api.anjuke.com/haozu/mobile/2.0/city.get?cityid=186&sig=aeb7334c87c001e5eb52ea033e7fa261&cityId_forapilog=186&api_key=0b9c7391e3fe30db8938251220770b7b&v=5.0.2&i=867068022325251&cv=8.8.1&o=leo-user+5.0.2+LRX22G+V6.6.8.0.LXHCNCF+release-keys&macid=33cd5109c031dccffadb4fcfa277dfea&cid=11&pm=b135&m=Android-MI+NOTE+Pro&app=a-ajk
    
    ```
    返回结果中的相关字段：
    区域：areas
    板块：areas.blocks
    ```

* 租房列表页推荐——猜你喜欢
http://api.anjuke.com/mobile/v5/recommend/rent/list/history?city_id=11&limit=25&offset=0&uuid=07d8c377-5d8d-4b30-b3a6-b12015071523&cid=11&pm=b135&m=Android-MI+NOTE+Pro&qtime=20150817170254&from=mobile&app=a-ajk&v=5.0.2&i=867068022325251&_pid=5417&cv=8.8.1&o=leo-user+5.0.2+LRX22G+V6.6.8.0.LXHCNCF+release-keys&macid=33cd5109c031dccffadb4fcfa277dfea&_chat_id=0

    ```
    返回结果中的相关字段：
    区域：properties.area
    板块：properties.block
    ```
* 租房列表页——列表
http://api.anjuke.com/haozu/mobile/2.0/prop/search?page_size=25&page=1&city_id=11&area_id=7&sig=6990e10970c0193f9872d5523b538988&cityId_forapilog=11&api_key=0b9c7391e3fe30db8938251220770b7b&v=5.0.2&i=867068022325251&cv=8.8.1&o=leo-user+5.0.2+LRX22G+V6.6.8.0.LXHCNCF+release-keys&macid=33cd5109c031dccffadb4fcfa277dfea&cid=11&pm=b135&m=Android-MI+NOTE+Pro&app=a-ajk

    ```
    返回结果中的相关字段：
    区域：properties.area
    板块：properties.block
    ```
* 租房单页——房源信息
http://api.anjuke.com/haozu/mobile/2.0/property.get?source_type=1&tracker=&type=3&id=49331137&cityid=11&sig=273351a42c48c4b627d59ecc5bf109c5&cityId_forapilog=11&api_key=0b9c7391e3fe30db8938251220770b7b&v=5.0.2&i=867068022325251&cv=8.8.1&o=leo-user+5.0.2+LRX22G+V6.6.8.0.LXHCNCF+release-keys&macid=33cd5109c031dccffadb4fcfa277dfea&cid=11&pm=b135&m=Android-MI+NOTE+Pro&app=a-ajk

    ```
    返回结果中的相关字段：
    区域：property.area
    板块：property.block
    ```    
* 租房单页——推荐
http://api.anjuke.com/mobile/v5/recommend/rent/view/look?area_id=7&city_id=11&price=6500&type=3&item=49331137&uuid=07d8c377-5d8d-4b30-b3a6-b12015071523&cid=11&pm=b135&m=Android-MI+NOTE+Pro&qtime=20150817170448&from=mobile&app=a-ajk&v=5.0.2&i=867068022325251&_pid=5417&cv=8.8.1&o=leo-user+5.0.2+LRX22G+V6.6.8.0.LXHCNCF+release-keys&macid=33cd5109c031dccffadb4fcfa277dfea&_chat_id=0

    ```
    返回结果中的相关字段：
    区域：properties.area
    板块：properties.block
    ```

### 其他
* 推荐频道－推荐老接口
http://api.anjuke.com/mobile/v5/recommend/guess?location=31.224155%2C121.536745&city_id=11&first=20150817&uuid=07d8c377-5d8d-4b30-b3a6-b12015071523&cid=11&pm=b135&m=Android-MI+NOTE+Pro&qtime=20150817171009&from=mobile&app=a-ajk&v=5.0.2&i=867068022325251&_pid=5417&cv=8.8.1&o=leo-user+5.0.2+LRX22G+V6.6.8.0.LXHCNCF+release-keys&macid=33cd5109c031dccffadb4fcfa277dfea&_chat_id=0

    ```
    返回结果中的相关字段：
    区域：esfdata.area
    板块：无
    ```

* 推荐频道－推荐新接口
http://api.anjuke.com/mobile/v5/recommend/guess/new?location=31.224155%2C121.536745&city_id=11&first=20150817&uuid=07d8c377-5d8d-4b30-b3a6-b12015071523&cid=11&pm=b135&m=Android-MI+NOTE+Pro&qtime=20150817171009&from=mobile&app=a-ajk&v=5.0.2&i=867068022325251&_pid=5417&cv=8.8.1&o=leo-user+5.0.2+LRX22G+V6.6.8.0.LXHCNCF+release-keys&macid=33cd5109c031dccffadb4fcfa277dfea&_chat_id=0

    ```
    返回结果中的相关字段：
    区域：esf_data.area zf_data.area
    板块：esf_data.block zf_data.block
    ```
* 小区－小区信息
http://api.anjuke.com/anjuke/4.0/community/info?is_nocheck=1&commid=370146&rsl=65535&uuid=07d8c377-5d8d-4b30-b3a6-b12015071523&cid=11&pm=b135&m=Android-MI+NOTE+Pro&qtime=20150817171358&from=mobile&app=a-ajk&v=5.0.2&i=867068022325251&_pid=5417&cv=8.8.1&o=leo-user+5.0.2+LRX22G+V6.6.8.0.LXHCNCF+release-keys&macid=33cd5109c031dccffadb4fcfa277dfea&_chat_id=0
    
    ```
    返回结果中的相关字段：
    区域：community.area community.area_pinyin
    板块：community.block community.block_pinyin
    ```

* http://api.anjuke.com/anjuke/4.0/comm/get?is_nocheck=1&community_id=370146&uuid=07d8c377-5d8d-4b30-b3a6-b12015071523&cid=11&pm=b135&m=Android-MI+NOTE+Pro&qtime=20150817171359&from=mobile&app=a-ajk&v=5.0.2&i=867068022325251&_pid=5417&cv=8.8.1&o=leo-user+5.0.2+LRX22G+V6.6.8.0.LXHCNCF+release-keys&macid=33cd5109c031dccffadb4fcfa277dfea&_chat_id=0

    ```
    返回结果中的相关字段：
    区域：community.area_name
    板块：community.block_name
    ```

* 房价－附近小区房价
http://api.anjuke.com/anjuke/4.0/comm/nearby?is_nocheck=1&city_id=11&lat=31.224154&lng=121.536745&radius=3&sort_type=1&limit=10&pagenum=1&uuid=07d8c377-5d8d-4b30-b3a6-b12015071523&cid=11&pm=b135&m=Android-MI+NOTE+Pro&qtime=20150817171957&from=mobile&app=a-ajk&v=5.0.2&i=867068022325251&_pid=5417&cv=8.8.1&o=leo-user+5.0.2+LRX22G+V6.6.8.0.LXHCNCF+release-keys&macid=33cd5109c031dccffadb4fcfa277dfea&_chat_id=0

    ```
    返回结果中的相关字段：
    区域：results.data.area
    板块：results.data.block
    ```