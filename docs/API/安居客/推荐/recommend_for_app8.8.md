## APP8.8版本－推荐

### 推荐改版说明
* app8.8版本对app整站推荐进行了改版，所有接口都为新增

### BI接口文档
[http://gitlab.corp.anjuke.com/_microdata/recommend-api-doc/blob/master/README.md](http://gitlab.corp.anjuke.com/_microdata/recommend-api-doc/blob/master/README.md)

### api接口定义
* 二手房首页猜你喜欢
[pg测试地址
](http://api.anjuke.test/mobile/v5/recommend/sale/home/history/?app=i-ajk&city_id=11&i=95D23829-CBFB-4795-9BD6-201505181053&macid=0f607264fc6318a92b9e13c65db7cd3c&udid2=95D23829-CBFB-4795-9BD6-201505181053)

    ```
    http://api.anjuke.com/mobile/v5/recommend/sale/home/history/
    默认15条，支持分页获取
    ```
* 二手房列表页猜你喜欢
[pg测试地址](http://api.anjuke.test/mobile/v5/recommend/sale/list/history/?app=i-ajk&city_id=11&i=95D23829-CBFB-4795-9BD6-201505181053&macid=0f607264fc6318a92b9e13c65db7cd3c&udid2=95D23829-CBFB-4795-9BD6-201505181053)

    ```
    http://api.anjuke.com/mobile/v5/recommend/sale/list/history/
    每页20条，总共100条，支持分页获取
    ```  
* 二手房单页附近房源
[pg测试地址](http://api.anjuke.test/mobile/v5/recommend/sale/view/near/?app=i-ajk&city_id=11&i=95D23829-CBFB-4795-9BD6-201505181053&macid=0f607264fc6318a92b9e13c65db7cd3c&udid2=95D23829-CBFB-4795-9BD6-201505181053&iterm=123344&type=1&limit=20&offset=0)

    ```
    http://api.anjuke.com/mobile/v5/recommend/sale/view/near/
    默认20条，没有分页
    ```
* 二手房单页看了又看
[pg测试地址](http://api.anjuke.test/mobile/v5/recommend/sale/view/look/?app=i-ajk&city_id=11&i=95D23829-CBFB-4795-9BD6-201505181053&macid=0f607264fc6318a92b9e13c65db7cd3c&udid2=95D23829-CBFB-4795-9BD6-201505181053&iterm=123344&type=1&limit=20&offset=0)

    ```
    http://api.anjuke.com/mobile/v5/recommend/sale/view/look/
    默认20条，没有分页
    ```
* 租房列表页猜你喜欢
[pg测试地址](http://api.anjuke.test/mobile/v5/recommend/rent/list/history/?city_id=11&app=a-ajk&i=95D23829-CBFB-4795-9BD6-201505181053&macid=0f607264fc6318a92b9e13c65db7cd3c&udid2=95D23829-CBFB-4795-9BD6-201505181053)

    ```
    http://api.anjuke.com/mobile/v5/recommend/rent/list/history/
    每页20条，总共100条，支持分页获取
    ```
* 租房单页看了又看
[pg测试地址](http://api.anjuke.test/mobile/v5/recommend/rent/view/look/?city_id=11&app=a-ajk&i=95D23829-CBFB-4795-9BD6-201505181053&macid=0f607264fc6318a92b9e13c65db7cd3c&udid2=95D23829-CBFB-4795-9BD6-201505181053)

    ```
    http://api.anjuke.com/mobile/v5/recommend/rent/view/look/
    默认20条，没有分页
    ```
* 推荐频道猜你喜欢
[pg测试地址](http://api.anjuke.test/mobile/v5/recommend/guess/new/?city_id=11&app=a-ajk&i=95D23829-CBFB-4795-9BD6-201505181053&macid=0f607264fc6318a92b9e13c65db7cd3c&udid2=95D23829-CBFB-4795-9BD6-201505181053)

    ```
    http://api.anjuke.com/mobile/v5/recommend/guess/new/
    默认20条，每次更新5条
    ```

### 入参统一说明
* app公共参数
[app公共参数说明](http://gitlab.corp.anjuke.com/Beckyxu/api/blob/master/common/app%E5%85%AC%E5%85%B1%E5%8F%82%E6%95%B0%E8%AF%B4%E6%98%8E.md)

* 推荐业务所需参数

    |参数名称|参数含义|是否必须|说明|
    |---|---|---|---|
    | city_id |  城市id|是| 列表页推荐补充逻辑使用|
    | item |房源id|单页必须|无|
    | type |房源类型|单页必须|无|
    | price |房源价格|单页必须|单页推荐补充逻辑使用|
    | area_id |房源所在区域id|单页必须|单页推荐补充逻辑使用|
    | limit |返回记录条数|否|默认20|
    | offset |偏移量|否|默认0|

### 出参统一说明
* [二手房出参说明](http://gitlab.corp.anjuke.com/Beckyxu/api/blob/master/recommend/%E4%BA%8C%E6%89%8B%E6%88%BF%E5%87%BA%E5%8F%82%E8%AF%B4%E6%98%8E.md)

* [租房出参说明](http://gitlab.corp.anjuke.com/Beckyxu/api/blob/master/recommend/%E7%A7%9F%E6%88%BF%E5%87%BA%E5%8F%82%E8%AF%B4%E6%98%8E.md)