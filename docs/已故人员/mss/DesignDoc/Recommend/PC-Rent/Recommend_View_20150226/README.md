## 租房单页推荐改版

### 项目背景
* 目前单页推荐数据质量较差，目的提高推荐质量，从而提升推荐的点击和转化。

### 项目需求
* 详见PMT：http://p.corp.anjuke.com/project/detail?id=25845
* 此次需求只修改：经纪人房源单页


### 现状调研：
 
* 推荐模块&api接口——根据不同的页面类型：
    * 个人房源
        * 页面底部：`XXX附近房源推荐`
        
            ```
            like_props:
            http://sh.zu.anjuke.com/api/rec/getcommlastestprop/?proid=&type=3&limit=4&from=&r=0.25635769101791084&city_id=11&comm_id=3511&min=4&del_prop_id=39633615
            ```
        * 页面右侧：`您可能还敢兴趣的房子`
            
            ```
            like_props:
            http://sh.zu.anjuke.com/api/rec/recommendpropall/?proid=39633615&type=1&limit=5&from=SITE_RENT_PAGE&r=0.05677075148560107
            ```
    * 经纪人房源
        * 页面底部：
            * `XXX附近房源推荐`
            
                ```
                near_props:
                http://sh.zu.anjuke.com/api/rec/pagerec/?cityid=11&id=37838981&type=3&user_id=&limit=9&resulttype=2&from=SITE_RENT_PAGE&r=0.5729074440896511
                ```
            * `您可能还感兴趣的房子`
                
                ```
                like_props:
                http://sh.zu.anjuke.com/api/rec/recommendpropall/?proid=37838981&type=3&limit=4&from=SITE_RENT_PAGE&r=0.6158447465859354
                ```
        * 页面右侧：
            * 经纪人购买了橱窗服务:`主推房源`
                
                ```
                main_push_props:
                http://sh.zu.anjuke.com/api/rec/pagerec/?cityid=11&id=37838981&type=3&user_id=&limit=9&resulttype=2&from=SITE_RENT_PAGE&r=0.5729074440896511
                ```
            * 经纪人未购买橱窗服务:`看了该房源的人还看了`
                
                ```
                far_props:
                http://sh.zu.anjuke.com/api/rec/pagerec/?cityid=11&id=37838981&type=3&user_id=&limit=9&resulttype=2&from=SITE_RENT_PAGE&r=0.5729074440896511
                ```
 
    * 大业主房源
        * 页面右侧：`XXX的更多房源`
        
            ```
            sublessor_props：
            http://sh.zu.anjuke.com/api/rec/pagerec/?cityid=11&id=D9568&type=4&user_id=12295911&limit=9&resulttype=2&from=SITE_RENT_PAGE&r=0.19598456099629402
            ```
    
    * 抓取房源

* 老接口逻辑梳理
    * 接口地址：
        
        ```
        http://sh.zu.anjuke.com/api/rec/pagerec/?cityid=11&id=D9568&type=4&user_id=12295911&limit=9&resulttype=2&from=SITE_RENT_PAGE&r=0.19598456099629402
        ```
    * 代码位置：
    
        ```
    haozu-site::Api_Rec_PageRecController
        ```
    * 接口输入参数：
  
      |参数名称|是否必须|参数说明|
      |---|---|---|
      |cityid|是|城市id|
      |type|是|推荐接口所需参数，经纪人为3，大业主为4|
      |id|是|房源id|
      |limit|否|默认为5，需要的推荐数据条数|
      |resulttype|否|默认为1，推荐接口所需参数|
      
    * 接口返回格式
        
        ```
        {
        	status: "ok",
			results: {
			   is_main_push: 0,
			   main_push_props: [ ],
			   sublessor_show_tag: 0,
			   sublessor_props: [ ],
			   near_props: [],
			   far_props: [],
			   sojDataMainPush: {},
			   sojDataNear: "",
			   sojDataFar: {}
			},
			requestTime: 0.15591287612915
		}
    	```
 
    * 程序逻辑
        * 1.根据传入的参数id 获取房源信息
         
            ```
    Orm_Prop_Prop::get_prop_content_basic(rent_db.prop.content_basic)
            ```
                    
        * 2.根据房源信息中的userid 获取经纪人主推房源信息
            
            ```
            Bll_Broker_MainPushRentProp::getMainPushSalePorp
            ```
            
        * 3.获取大业主的房源
             
            ```
            user_prop_sh_db.sublessor_prop_index_11
            select id from ".$tableName." where user_id=:user_id and status=:status order by updated desc limit $limit
            ```

### 新接口设计
* 针对经纪人房源单页
* 给页面标识：经纪人是否购买了橱窗服务
    * service	
    
        ```
    broker_db.ad_set_rent
    Ershou_Core_Broker_Service_MainPushService:findAdSetRent($id,$is_stop,$end_date)
    eg:select * from ad_set_rent where hz_user_id=1851640 and is_stop=0 and end_date>=20150227
    ```        
    * 购买了橱窗服务
        * 接口A：提供`附近推荐`＋`主推房源`
        * 接口B：提供`感兴趣的房子`
    * 未购买橱窗服务
        * 接口A：提供`附近推荐`
        * 接口B：提供`感兴趣的房子`+`看了又看`
* api接口说明：
    * 接口A：（老接口，保持不变）
        
        ```
        附近推荐：near_props
        主推房源：main_push_props
        http://sh.zu.anjuke.com/api/rec/pagerec/?cityid=11&id=37838981&type=3&user_id=&limit=9&resulttype=2&from=SITE_RENT_PAGE&r=0.5729074440896511
        ``` 
        
    * 接口B：（新接口）
    
        ```
        感兴趣的房子：like_list
        看了又看：look_list
        http://sh.beckyxu.zu.dev.anjuke.com/v3/ajax/viewrecommend/?type=view&view_type=yep&num=18&are_id=7
        ```

* 接口B设计
    * 优先获取推荐接口数据@文宗
    * 不足时使用补充逻辑 

* OTHER
    * 获取经纪人的主推房源
        
        ```
        broker_db.ad_props_show_rent
        select * from ad_props_show_rent where hz_user_id=1851640 order by id desc limit 0,5
        ``` 
        
    * 附近房源推荐接口：
        
        ```
        prop_id:
            调用接口：http://www.anjuke.com/rec/rent/pagerec?cityid=11&id=888894295&type=3&limit=9
            用到的romar服务：http://10.10.9.40:53549/items/similars
        prop_info:
            获取房源信息：http://api.anjuke.com/haozu/mobile/2.0/property.getPropsInfo
        ```

