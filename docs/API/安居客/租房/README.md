## APi整理－租房

### 获取城市列表
* api接口

    ```
    http://api.haozu.com/mobile/2.0/city.getList?is_nocheck=1
    
    multicity.php中的配置$config['region']
    
    ```
    
### 获取筛选项
* 获取区域板块／地铁站点筛选项

    ```
    http://api.haozu.com/mobile/2.0/city.get?cityid=11&is_nocheck=1&from_module=touch_web
    
    Mobile_V20_CityController::_get()
    
    从表中获取区域板块
    10.20.3.80:3307	anjuke_triger/anjuke_triger 
    rent_db.area
    
    没有坐标或者需要百度坐标（map_type＝b 这里的逻辑默认百度坐标）
    获取百度坐标
    rent_db.map_areas_baidu
    
    如果没有坐标，不展示区域板块？？？？
    （这里的逻辑没找到原因，为保持和PC一致，TW已经去掉，APP暂没处理）
    
    ```
* 获取租房其他筛选项

	```
	http://api.beckyxu.dev.anjuke.com/haozu/mobile/2.0/city.getFilters?cityid=79&os=android&is_nocheck=1&from_module=touch_web&show_metro=1

	app-haozu-core/config/multicity.php
	//获取筛选项的关键配置
	$config['cities']

	价格：
	rent_db.zf_price

	户型：统一
	app-haozu-core/config/house.php
	$config['bedroom']

	来源：统一
	app-haozu-core/config/house.php
	$config['source']

	装修：按照城市区分
	app-haozu-core/config/house.php
	$config['ajk_fitment']

	租赁方式:统一
	app-haozu-core/config/house.php
	$config['renttype']

	地铁：
	anjuke_db.sw_metros
	```
	
### 获取租房列表页
* api接口地址

	```
	线上地址：
	http://api.haozu.com/mobile/2.0/prop/search?isnocheck=1&frommodule=touchweb&sort=5&pagesize=20&cityid=11&serialVersionUID=1&pagesize=20&distance=6
	```
* 逻辑分析

    ```
    solr配置：
		app-haozu-core/config/service.php
		$config['allLuceneRentUriList']
		
	 城市对应solr分组配置：
	 	app-haozu-core/config/multicity.php
		$config['city_group_mapping']
		
	 获取房源完整信息
	 	Module_Prop_Build::build_props_list()
	 	先从redis获取
	 	再从DB获取
	 	Module_Prop_PropInfoV2::get_propsinfo()
    ```
    
### 推荐
* 看了又看-api接口

    ```
    直接请求接口：
    
    haozu.Rec_PageController
    http://api.haozu.com/mobile/2.0/rec/page?cityid=11&id=41631837&limit=15&rec_from=app_rent_page&type=3
    
    调用PC接口：
    
    anjuke-site.Rec_Rent_PageRecController
    http://www.anjuke.com/rec/rent/pagerec?cityid=25&id=41631837&type=3&limit=15&from=test
    (from来自请求中的from 没有就为test)
    
    Bll_Rec_Rent_PageRec::getRec
    
    Rec_Factory_RentIDRec::fetchResult
    
    //获取推荐的ID
    service['romar_instances']
    Rec_ItemRec::getRecItems
    Rec_ItemBase::recommendItems
    http://10.10.9.40:53549/items/similars?limit=30&item=41631837&from=test

    ```
    
* 不知道哪里用的推荐 可能是老好租app

    ```
    http://api.beckyxu.dev.anjuke.com/haozu/mobile/2.0/recommend.getProperty?cityid=19&page=1&page_size=15&is_nocheck=1&uid=330620E5-45AB-40BD-A475-20FFD37D1548
    
    uid 没有返回空结果
    
    先根据uid city_id去数据表查询
    Extend_Orm_HaoFang_HaoFang::get_all_ex_id_by_uid
    mobile_db.haofang_user_action(app传的用户行为表)
    SELECT * FROM haofang_user_action where  uid='330620E5-45AB-40BD-A475-20FFD37D1548' LIMIT 0,100
    
    如果表没数据，走推荐逻辑
    
    
    
    ```