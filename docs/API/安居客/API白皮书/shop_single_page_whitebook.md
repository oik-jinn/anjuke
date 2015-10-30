## app 商业地产-商铺出租,商铺出售单页白皮书
### 1. URL
##### 
### 1. 页面URL
```
app无,启动安居客app,点击商业地产选择商铺出租或者商铺出售,搜索出结果后,点击搜索结果,进入单页
```

### 2. 页面说明
```
商铺出租,商铺出售单页
```
    
### 3. 页面模块列表
|编号|URL|仓库|机器|负责人|功能|
| --- |--- | --- | --- | --- | --- |
|3.1|http://api.anjuke.com/jinpu/1.1/shop/17717238?trade_type=0 trade_type=0表示商铺的出租,1表示商铺的销售|jinpu|app10-125|徐晓路|商铺单页|
|3.2|http://api.anjuke.com/jinpu/1.1/prop/recommend|jinpu|app10-125|徐晓路|商铺单页推荐|
### 4. 依赖的外部URL
##### 4.1 URL-3.2 的依赖
|配置名|URL|功能|其它|
| --- | --- | --- | --- |
|hard code 在代码里面|http://www.sp.anjuke.com/api/house_interest/|获取商业地产和写字楼的推荐||
|curl_get_chat_infos|http://api.anjuke.com/anjuke/4.0/broker/chatinfos|通过经纪人id获取经纪人的微聊信息|微聊api|

### 5. 数据库和表:
##### 5.1 URL-3.1 依赖
|数据库|表名称|作用|读写|是否独有|
| --- | --- | --- | --- | --- |
|jinpu_db|e_user_house|用户房源基本信息|读|否|
|jinpu_db|e_shop_rent_desc|商业地产描述|读|否|
|jinpu_db|i_house|推广状态查询|读|否|
|jinpu_db|e_property|房源基本信息|读|否|
|jinpu_db|d_property_type|房源类型信息|读|否|
|jinpu_db|e_user_house_ext|用户房源扩展信息|读|否|
|jinpu_db|e_map_house|房源经纬度信息|读|否|
|jinpu_db|e_shop_broker|经纪人信息|读|否|
##### URL-3.2 依赖
|数据库|表名称|作用|读写|是否独有|
| --- | --- | --- | --- | --- |
|jinpu_db|e_shop_rent|商铺出租信息|读|否|
|jinpu_db|e_shop_sale|商铺出售信息|读|否|
|jinpu_db|e_shop_rent_desc|商铺出租描述|读|否|
|jinpu_db|e_shop_sale_desc|商铺出售描述|读|否|
|jinpu_db|e_office_sale|写字楼出售信息|读|否|
|jinpu_db|e_office_rent|写字楼出租信息|读|否|
|jinpu_db|e_office_sale_desc|写字楼出售描述|读|否|
|jinpu_db|e_office_rent_desc|写字楼出租描述|读|否|
|jinpu_db|e_property|房源该信息|读|否|
|jinpu_db|e_map_property|房源坐标信息|读|否|
|jinpu_db|e_building|建筑物信息|读|否|
|jinpu_db|e_member|用户信息|读|否|
|jinpu_db|e_member_ext|用户扩展信息|读|否|
|jinpu_db|e_office_broker|写字楼经纪人信息|读|否|
|jinpu_db|e_shop_broker|商铺经纪人信息|读|否|
|jinpu_db|e_office_rent_img_v2|出租写字楼图片信息|读|否|
|jinpu_db|e_office_sale_img_v2|出售写字楼图片信息|读|否|
|jinpu_db|e_shop_sale_img_v2|出售商铺的图片信息|读|否|
|jinpu_db|e_shop_rent_img_v2|出租商铺的图片信息|读|否|
### 6. Memcache&Redis
### 7. 关联job
|job名称|job管理地址|功能|负责人|
|--- | --- | --- | --- |


