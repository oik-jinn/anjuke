## app 商业地产-写字楼出租,写字楼出售单页白皮书
### 1. URL
##### 
### 1. 页面URL
```
app无,启动安居客app,点击商业地产选择写字楼出租或者写字楼出售,搜索出结果后,点击搜索结果,进入单页
```

### 2. 页面说明
```
写字楼出租,写字楼出售单页
```
    
### 3. 页面模块列表
|编号|URL|仓库|机器|负责人|功能|
| --- |--- | --- | --- | --- | --- |
|3.1|http://api.anjuke.com/jinpu/1.1/office/17803677?trade_type=0 trade_type=0表示出租,1表示销售|jinpu|app10-125|徐晓路|写字楼单页|
|3.2|http://api.anjuke.com/jinpu/1.1/prop/recommend|jinpu|app10-125|徐晓路|写字楼单页推荐|
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
|jinpu_db|i_house|推广状态查询|读|否|
|jinpu_db|e_office_sale|写字楼销售信息|读|否|
|jinpu_db|e_office_rent|写字楼出租信息|读|否|
|jinpu_db|e_office_sale_desc|写字楼销售描述信息|读|否|
|jinpu_db|e_office_rent_desc|写字楼出租描述信息|读|否|
|jinpu_db|e_property|房源基本信息|读|否|
|jinpu_db|e_map_property|房源坐标信息|读|否|
|jinpu_db|e_building|建筑物信息|读|否|
|jinpu_db|d_property_type|房源类型|读|否|
|jinpu_db|e_member|用户信息|读|否|
|jinpu_db|e_member_ext|用户扩展信息|读|否|
|anjuke_db|cst_company|客户(公司)的信息|读|否|
|jinpu_db|e_company_jinpu|未知|读|否|
|jinpu_db|d_block|获取板块信息|读|否|
|jinpu_db|d_district_block|获取区域内的板块|读|否|
|jinpu_db|d_district|区域信息|读|否|
|jinpu_db|d_new_business|新的商业圈信息|读|否|
|jinpu_db|d_new_district|新的区域信息|读|否|
|jinpu_db|e_office_broker|写字楼经纪人信息|读|否|
##### 5.2 URL-3.2 [依赖](http://gitlab.corp.anjuke.com/_site/docs/blob/master/API/%E5%AE%89%E5%B1%85%E5%AE%A2/API%E7%99%BD%E7%9A%AE%E4%B9%A6/shop_single_page_whitebook.md)
### 6. Memcache&Redis
### 7. 关联job
|job名称|job管理地址|功能|负责人|
|--- | --- | --- | --- |


