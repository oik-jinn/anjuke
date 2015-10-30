# 经纪人查询接口文档


### 1 根据id获取经纪人信息


* 接口url

	PG：由开发补充
	线上：由开发补充

* 接口协议

    GET

* 接口参数：

  Field_Name | Field_Type | Is_Null | Description
  ---  | --- | --- | ---
  id | string | No | 经纪人id(多个id用，隔开)

* 返回数据

  Field_Name | Field_Type | Is_Null | Description
  ---  | --- | --- | ---
  status | string | No | 返回状态， "ok"/"false"
  msg | string | No | 返回信息，成功"200"，错误返回字符串提示
  data | array | No | 返回数据

  data格式

  Field_Name | Field_Type | Is_Null | Description
  ---  | --- | --- | ---
  broker_id | int | No | 经纪人id
  user_id | int | No | 用户id
  city_id | int | No | city id
  area_id | int | No | area id
  broker_photo | string | No | 头像
  sex | int | No | 性别(0-男 1-女)
  name | string | No | 姓名
  mobile | int | No | 移动电话
  store_id | int | No | 所属门店id
  company_id | int | No | 所属公司id
  update_time | timestamp | No | 最后更新时间
  is_del | int | No | 是否删除（1-删除 2-正常）

* 成功返回结果eg

          {
                "status":"ok",
                "msg":"200",
                "data":[
                {
                	"broker_id" : 123,
                    "user_id" : 456,
                    "city_id" : 11,
                    "area_id" : 789,
                    "broker_photo" : "10/04/12/6/9/1/100412691024749c48fee65f0d1e4085f04ec3_1.jpg",
                    "sex" : 0,
                    "name" : "王欢欢",
                    "mobile" : "15950402681",
                    "store_id" : 2213,
                    "company_id" : 343,
                    "update_time" : "2015-07-20 14:23:35",
                    "is_del" : 0
                },
                {
                	"broker_id" : 113,
                    "user_id" : 456,
                    "city_id" : 11,
                    "area_id" : 789,
                    "broker_photo" : "10/04/12/6/9/1/100412691024749c48fee65f0d1e4085f04ec3_1.jpg",
                    "sex" : 0,
                    "name" : "王海华",
                    "mobile" : "15950402681",
                    "store_id" : 2213,
                    "company_id" : 343,
                    "update_time" : "2015-07-20 14:23:35",
                    "is_del" : 0
                }
                ]
          }

* 失败返回eg
	    {
                "status":"false",
                "msg":"id is empty",
                "data":[]
        }

### 2 根据id获取公司信息


* 接口url

	PG：由开发补充
	线上：由开发补充

* 接口协议

    GET

* 接口参数：

  Field_Name | Field_Type | Is_Null | Description
  ---  | --- | --- | ---
  id | string | No | 公司id(多个id用，隔开)

* 返回数据

  Field_Name | Field_Type | Is_Null | Description
  ---  | --- | --- | ---
  status | string | No | 返回状态， "ok"/"false"
  msg | string | No | 返回信息，成功"200"，错误返回字符串提示
  data | array | No | 返回数据

  data格式

  Field_Name | Field_Type | Is_Null | Description
  ---  | --- | --- | ---
  company_id | int | No | 公司id
  company_name | string | No | 公司名称
  full_name | string | No | 公司全名
  address | string | No | 公司地址
  city_id | int | No | city id
  image_id | string | No | logo路径或md5
  host_id | int | No | logo的host_id，没有设为0
  telphone | string | No | 电话
  fax | string | No | 传真
  summary | string | No | 公司简介
  update_time | timestamp | No | 最后更新时间
  is_del | int | No | 是否删除（1-删除 2-正常）

* 成功返回结果eg

          {
                "status":"ok",
                "msg":"200",
                "data":[
                {
                	"company_id" : 4,
                    "company_name" : "中原地产",
                    "full_name" : "上海中原物业代理有限公司",
                    "address" : "上海市长宁区延安西路889号太平洋中心大厦23",
                    "city_id" : 11,
                    "image_id" : "10/04/12/6/9/1/100412691024749c48fee65f0d1e4085f04ec3_1.jpg",
                    "host_id" : 0,
                    "telphone" : "023-88883333",
                    "fax" : "15950402681",
                    "summary" : "上海......",
                    "update_time" : "2015-07-20 14:23:35",
                    "is_del" : 0
                },
                {
                	"company_id" : 1,
                    "company_name" : "21世纪不动产",
                    "full_name" : "上海锐丰投资管理有限公司",
                    "address" : "黄河路333号",
                    "city_id" : 11,
                    "image_id" : "569ad08f25ed6c75fd9fb95d6895b304",
                    "host_id" : 1,
                    "telphone" : "023-88883333",
                    "fax" : "15950402681",
                    "summary" : "上海....",
                    "update_time" : "2015-08-20 14:23:35",
                    "is_del" : 0
                }
                ]
          }

* 失败返回eg
	    {
                "status":"false",
                "msg":"id is empty",
                "data":[]
        }

### 3 根据id获取门店信息


* 接口url

	PG：由开发补充
	线上：由开发补充

* 接口协议

    GET

* 接口参数：

  Field_Name | Field_Type | Is_Null | Description
  ---  | --- | --- | ---
  id | string | No | 门店id(多个id用，隔开)

* 返回数据

  Field_Name | Field_Type | Is_Null | Description
  ---  | --- | --- | ---
  status | string | No | 返回状态， "ok"/"false"
  msg | string | No | 返回信息，成功"200"，错误返回字符串提示
  data | array | No | 返回数据

  data格式

  Field_Name | Field_Type | Is_Null | Description
  ---  | --- | --- | ---
  store_id | int | No | 门店id
  store_name | string | No | 门店名称
  company_id | string | No | 所属公司id
  address | string | No | 门店地址
  city_id | int | No | city id
  short_store_name | string | No | 门店简称
  update_time | timestamp | No | 最后更新时间
  is_del | int | No | 是否删除（1-删除 2-正常）

* 成功返回结果eg

          {
                "status":"ok",
                "msg":"200",
                "data":[
                {
                	"store_id" : 129,
                    "store_name" : "中原地产XX店",
                    "company_id" : 4,
                    "address" : "上海市长宁区延安西路889号太平洋中心大厦23",
                    "city_id" : 11,
                    "short_store_name" : "七宝店",
                    "update_time" : "2015-07-20 14:23:35",
                    "is_del" : 0
                },
                {
                	"store_id" : 130,
                    "store_name" : "中原地产XS店",
                    "company_id" : 4,
                    "address" : "上海市长宁区延安西路889号太平洋中心大厦26",
                    "city_id" : 11,
                    "short_store_name" : "SS店",
                    "update_time" : "2015-07-20 14:23:35",
                    "is_del" : 0
                }
                ]
          }

* 失败返回eg
	    {
                "status":"false",
                "msg":"id is empty",
                "data":[]
        }

### 4 根据id获取经纪人拓展信息

* 接口url

	PG：由开发补充
	线上：由开发补充

* 接口协议

    GET

* 接口参数：

  Field_Name | Field_Type | Is_Null | Description
  ---  | --- | --- | ---
  id | string | No | 经纪人id(多个id用，隔开)
  source_type | int | No | 获取信息类型 1:全部信息 2：获取微店信息 3：获取微聊达人,明星中介信息

* 返回数据

  Field_Name | Field_Type | Is_Null | Description
  ---  | --- | --- | ---
  status | string | No | 返回状态， "ok"/"false"
  msg | string | No | 返回信息，成功"200"，错误返回字符串提示
  data | array | No | 返回数据

  data格式

  Field_Name | Field_Type | Is_Null | Description
  ---  | --- | --- | ---
  broker_id | int | No | 经纪人id
  has_weidian | string | No | 是否有微店 1：有 0：没有（source_type != 1/2 为0）
  chat_host_id | string | No | 微店图片host(source_type != 1/2 为0)
  chat_image_id | string | No | 微店图片id（source_type != 1/2 为""）
  is_talent | int | No |  是否微聊达人1：是 0：不是（source_type != 1/3 为0）
  is_star | string | No | 是否明星经纪人 1：是 0：不是 （source_type != 1/3 为0）

* 成功返回结果eg

          {
                "status":"ok",
                "msg":"200",
                "data":[
                {
                	"broker_id" : 129,
                    "has_weidian" : 0,
                    "chat_host_id" : 0,
                    "chat_image_id" : "",
                    "is_talent" : 0,
                    "is_star" : 1,
                },
                {
                	"broker_id" : 12,
                    "has_weidian" : 1,
                    "chat_host_id" : 1,
                    "chat_image_id" : "64ec943fffd5a80ceccbf800fc540dbd",
                    "is_talent" : 0,
                    "is_star" : 0,
                },
                ]
          }

* 失败返回eg
	    {
                "status":"false",
                "msg":"id is empty",
                "data":[]
        }