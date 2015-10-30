## APP8.8需求调研

### 租房单页优化
* 房屋配置信息展示修改

    ```
    http://api.anjuke.com/haozu/mobile/2.0/property.get?id=42584846&source_type=1&tracker=&type=3&cityid=14&sig=9959ed6d88fd54671f2d884d9e78fb8e&cityId_forapilog=14&api_key=0b9c7391e3fe30db8938251220770b7b&macid=4bc980cb7351f8b96e6c3831a7df2192&app=a-ajk&v=4.4.2&pm=dev111111&o=2014501-user+4.4.2+KOT49H+KHHCNBL16.0+release-keys&m=Android-2014501&cv=8.7&cid=11&i=866231026908388
    
    deployment
    ```
    
### 推荐逻辑修改
* 首页推荐

    ```
    接口地址：
    	http://api.anjuke.com/anjuke/4.0/property/list?is_nocheck=1&macid=4bc980cb7351f8b96e6c3831a7df2192&app=a-ajk&v=4.4.2&qtime=20150527170129&pm=b135&o=2014501-user+4.4.2+KOT49H+KHHCNBL16.0+release-keys&uuid=56650163-47bd-4409-87b3-a52015052716&from=mobile&m=Android-2014501&cv=8.7.1&cid=28&i=866231026908388&city_id=28&page_size=15&lng=121.536652&with_broker_recommend=0&page=1&lat=31.224109&map_type=baidu
    	page=1
    	
    查看更多：
    	http://api.anjuke.com/anjuke/4.0/property/list?macid=4bc980cb7351f8b96e6c3831a7df2192&app=a-ajk&v=4.4.2&qtime=20150527170731&pm=b135&o=2014501-user+4.4.2+KOT49H+KHHCNBL16.0+release-keys&uuid=56650163-47bd-4409-87b3-a52015052716&from=mobile&m=Android-2014501&cv=8.7.1&cid=28&i=866231026908388&city_id=28&page_size=15&lng=121.536614&with_broker_recommend=0&page=2&lat=31.224049&map_type=baidu
    	page=2
    ```
* 二手房列表页

    ```
    猜你喜欢：（筛选不限）
    	http://api.anjuke.com/anjuke/4.0/property/list?macid=4bc980cb7351f8b96e6c3831a7df2192&app=a-ajk&v=4.4.2&qtime=20150527173547&pm=b135&o=2014501-user+4.4.2+KOT49H+KHHCNBL16.0+release-keys&uuid=56650163-47bd-4409-87b3-a52015052716&from=mobile&m=Android-2014501&cv=8.7.1&cid=28&i=866231026908388&city_id=28&page_size=25&lng=121.536614&with_broker_recommend=0&page=1&lat=31.224049&map_type=baidu
    	lng=121.536614 lat=31.224049
    	
    筛选列表：
    	http://api.anjuke.com/anjuke/4.0/property/list?macid=4bc980cb7351f8b96e6c3831a7df2192&app=a-ajk&v=4.4.2&qtime=20150527173758&pm=b135&o=2014501-user+4.4.2+KOT49H+KHHCNBL16.0+release-keys&uuid=56650163-47bd-4409-87b3-a52015052716&from=mobile&m=Android-2014501&cv=8.7.1&cid=28&i=866231026908388&city_id=28&page_size=25&area_id=3308&with_broker_recommend=0&page=1&map_type=baidu&block_id=3433
    	block_id=3433
    ```
* 二手房单页

    ```
    猜你喜欢：
    	？？？
    附近相似房源：
    	http://api.anjuke.com/anjuke/4.0/property/rec/page?is_nocheck=1&macid=4bc980cb7351f8b96e6c3831a7df2192&app=a-ajk&v=4.4.2&qtime=20150527175625&pm=b135&o=2014501-user+4.4.2+KOT49H+KHHCNBL16.0+release-keys&uuid=56650163-47bd-4409-87b3-a52015052716&from=mobile&m=Android-2014501&cv=8.7.1&cid=28&i=866231026908388&rec_from=app_sale_page&limit=10&source_type=-1&proid=283116465&show_distance=1&limit_near=3&cityid=28
    	
    ```
    
* 租房列表猜你喜欢

    ```
    猜你喜欢：
    	http://api.anjuke.com/haozu/mobile/2.0/prop/search?city_id=11&page_size=25&page=1&sig=8ed6fc6669d164f331985b83f5e7bc6d&cityId_forapilog=11&api_key=0b9c7391e3fe30db8938251220770b7b&macid=4bc980cb7351f8b96e6c3831a7df2192&app=a-ajk&v=4.4.2&pm=b135&o=2014501-user+4.4.2+KOT49H+KHHCNBL16.0+release-keys&m=Android-2014501&cv=8.7.1&cid=11&i=866231026908388
    	
    筛选之后：
    	http://api.anjuke.com/haozu/mobile/2.0/prop/search?city_id=11&page_size=25&area_id=7&page=1&block_id=70&sig=62a3df2c85eeb457093ee82f02587f66&cityId_forapilog=11&api_key=0b9c7391e3fe30db8938251220770b7b&macid=4bc980cb7351f8b96e6c3831a7df2192&app=a-ajk&v=4.4.2&pm=b135&o=2014501-user+4.4.2+KOT49H+KHHCNBL16.0+release-keys&m=Android-2014501&cv=8.7.1&cid=11&i=866231026908388
    ```