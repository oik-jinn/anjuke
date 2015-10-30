### 大业主房源发布api基础设计文档

#### need to do

```
1. 安全设计
    1.1 请求频率 
        规则:每秒请求限制10次
        依据:每家公司的KEY
        范围:DML操作
    1.2及时性和一致性
        数据来源:solr
    1.3安全性
        sign和key匹配验证用户合法性
        demo:
            sign:7g699zRmXoyHVQVAlYkLEJpcTw5DvRkVMRUvxxDh24WfsNX9
            key:APIKEY123456
2. 入参设计
3. act_log设计
4. 出参设计
```

#### 经纪人通过api发房流程

1. 发送`房源基本信息`，成功是获取大业主房源id
2. 发送房源的`图片`

3. 发送需要上架的`房源ids`

* 发布房源流程

![发布房源](sublessor_rent_save_process.png)

* 推广房源流程

![推广房源](sublessor_rent_publish_process.png)


#### REST设计

```
添加大业主房源 POST /sublessor/add   (new)
删除大业主房源 GET  /sublessor/delete   (new)
更新大业主房源 POST /sublessor/update   (new)
大业主房源推广 GET /sublessor/spread   (new)

上传图片 POST /photos/sublessor/upload         注:设置 multipart/form-data (new)
删除房源图片 GET /photos/sublessor/delete/{id}  (new)
设置房源默认图片 GET /photos/sublessor/setDefault  (new)
```

#### 请求

POST / GET

#### 响应

* 格式 json

```
{
    status : 100,
    code : "",
}
```

* 状态码定义

状态码 | 含义
--- | ---
100 | 身份验证错误
101 | 请求错误
102 | 参数缺失
103 | 参数错误
104 | 请求超时
200 | 请求成功

---

#### POST /sublessor/add 新增房源

field | detail
--- | ---
mobile | 大业主手机号
community_id | 小区id
title | 房源信息的标题
use_type_id | 房屋类型
area_num | 面积
rent_type | 出租类型
share_sex | 合租性别要求
price | 租金
pay_type | 押付
room_num | 室
hall_num | 厅
toilet | 卫
fitment_id | 装修
house_orient | 朝向
floor | 所在层数
floor_num | 整楼层数
desc | 房源信息的内容
ext_field | 配置


---

#### GET  /sublessor/delete 删除房源

field | detail
--- | ---
mobile | 大业主手机号
prop_id | 房源id


---

#### POST /sublessor/update 更新房源

field | detail
--- | ---
mobile | 大业主手机号
prop_id | 房源id
title | 标题
use_type_id | 建筑类型
area_num | 面积
rent_type | 出租类型
share_sex | 合租性别要求
price | 租金
pay_type | 押付
room_num | 室
hall_num | 厅
toilet | 卫
fitment_id | 装修
house_orient | 朝向
floor | 所在层数
floor_num | 整楼层数
desc | 描述
ext_field | 配置


---

#### POST /sublessor/spread  推广(上下架)

field | detail
--- | ---
mobile | 大业主手机号
method | 操作
prop_ids | 房源ids


---

#### POST /photos/sublessor/upload 上传图片

field | detail
--- | ---
mobile | 大业主手机号
file | 图片文件
prop_id | 房源id
is_default | 默认图
type_id | 图片类型


---

#### GET /photos/sublessor/delete 删除房源的图片

field | detail
--- | ---
mobile | 大业主手机号
prop_id | 房源id
image_id | 图片id


---

#### GET /photos/sublessor/setDefault 设置房源默认图片

field | detail
--- | ---
mobile | 大业主手机号
prop_id | 房源id
image_id | 图片id



