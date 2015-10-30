## [公网api]58城市转安居客域名api

### 项目背景

* 58端因为业务需要，希望安居客提供公网api，以获取安居客含城市部分的域名。

### 项目需求

![email](http://gitlab.corp.anjuke.com/_site/docs/raw/master/DesignDoc/Public/Api/img/public_api_58cityurl.png)

### 项目设计

#### 入参设计

> * 请求api入参表
> 
> Parameter | Required | Description
> --- | --- | ---
> city_id | 必填 | 58城市id
> city_py | 必填 | 58城市缩写
> biz | 必填 | 业务线，取值含义见'业务线取值约定表'
> platform | 必填 | 平台，取值含义见'平台取值约定表'
> callback | 非必填 | jsonp格式输出的回调方法名
>


<div style="color:red">ps: city_id和city_py必须至少传入其中一个，同时传入时，以`city_id`为准</div>

> * 业务线取值约定表
> 
> value | description
> --- | ---
> 1 | 新房
> 2 | 二手房
> 3 | 租房
>

> * 平台取值约定表
> 
> value | description
> --- | ---
> 1 | pc
> 2 | tw
>


#### 出参设计

> * 请求api出参表
> 
> Parameter | Description
> --- | ---
> city_id | 安居客城市id
> city_py | 安居客城市缩写
> biz | 业务线，取值含义见'业务线取值约定表'
> platform | 平台，取值含义见'平台取值约定表'
>

>
> * 出参约定统一为json格式，如下
>
> ```
> {
>     "status": "ok",
>     "msg": "success",
>     "data": {
>         "city_id": 11,
>         "city_py": "sh",
>         "biz": 1,
>         "platform": 1
>     }
> }
> ```
>

>
> * 新房频道由于开通城市集与二手房频道开通城市集不同，约定，`当出现请求的频道在安居客没有开放时，city_py字段为空字符串，msg字段显示'channel is not open'`，如下
>
> ```
> {
>     "status": "ok",
>     "msg": "channel is not open",
>     "data": {
>         "city_id": 11,
>         "city_py": "",
>         "biz": 1,
>         "platform": 1
>     }
> }
> ```
>

> * 当请求中带有非空的callback参数时，接口返回jsonp格式
>
> ```
> # curl "http://api.anjuke.com/site/public/citymapping/v1.0/city/get/
> # ?city_id=99&biz=1&platform=1&callback=showpackage"
> showpackage({
>     "status": "ok",
>     "msg": "channel is not open",
>     "data": {
>         "city_id": 11,
>         "city_py": "",
>         "biz": 1,
>         "platform": 1
>     }
> })
> ```


#### 请求设计

> * 访问限制
> 
> > 由于之后会统一为公网api提供访问限制，现在暂时不做访问限定，相对的增加api请求的监控，以供之后限制访问策略提供参考。
> 
> * cache策略
> 
> > 开启 service cache 配置
> 
> * 请求url
> 
> ```
> GET http://api.anjuke.com/site/public/citymapping/v1.0/city/get/
> ```
> 
> * 请求举例
> 
> ```
> curl "http://api.anjuke.com/site/public/citymapping/v1.0/city/get/
> ?city_id=99&biz=1&platform=1"
> ```
>

### 更新记录

date | content
--- | ---
2015-05-12 | 新增api对jsonp的支持

