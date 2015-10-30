## app 商业地产-写字楼出租,写字楼出售列表页白皮书
### 1. 页面URL
```
app无,启动安居客app,点击商业地产选择写字楼出租或者写字楼出售
```

### 2. 页面说明
```
此页面主要进行写字楼出租和者写字楼出售的搜索和筛选功能
```
    
### 3. 页面模块列表
|编号|URL|仓库|机器|负责人|功能|
| --- |--- | --- | --- | --- | --- |
|3.1|http://api.anjuke.com/jinpu/1.1/offices?trade_type=0 trade_type=0表示写字楼的出租,1表示写字楼的销售|jinpu|app10-125|徐晓路|写字楼出租,出售列表页|
### 4. 依赖的外部URL
##### 4.1 URL-3.1 的依赖
##### solr 依赖
    $config['solrcloud']['hostname'] = '10.10.6.51';
    $config['solrcloud']['port'] = 8983;
|配置名|URL|功能|其它|
| --- | --- | --- | --- |
|curl_get_chat_infos|http://api.anjuke.com/anjuke/4.0/broker/chatinfos|通过经纪人id获取经纪人的微聊信息|微聊api|
|curl_get_chat_id|http://api.anjuke.com/mobile-ajk-broker/1.0/broker/chat/getchatinfo/|通过经纪人id获得微聊id|经纪人api|
### 5. 数据库和表:
|数据库|表名称|作用|读写|是否独有|
| --- | --- | --- | --- | --- |
|jinpu_db|e_office_rent|写字楼出租信息|读|否|
|jinpu_db|e_office_sale|写字楼销售信息|读|否|
|jinpu_db|e_property|商业地产房源基本信息|读|否|
|jinpu_db|e_map_property|经纬度信息|读|否|
|jinpu_db|e_building|建筑的信息|读|否|
|jinpu_db|e_member|通过金浦经纪人id查询安居客经纪人id|读|否|
|jinpu_db|e_member_ext|户主扩展信息|读|否|
|anjuke_db|cst_company|客户(公司)的信息|读|否|
|jinpu_db|e_company_jinpu|未知|读|否|
|jinpu_db|d_district|获取区域信息|读|否|
|jinpu_db|d_block|获取板块信息|读|否|
|jinpu_db|d_district_block|获取区域内的板块|读|否|
|jinpu_db|d_new_business_block_map|获取及板块对应的新的商业圈信息|读|否|
|jinpu_db|d_new_business|新的商业圈信息|读|否|
|jinpu_db|d_new_district|新的区域信息|读|否|
|jinpu_db|e_office_rent_img_v2|出租写字楼的默认图片|读|否|
|jinpu_db|e_office_sale_img_v2|出售写字楼的默认图片|读|否|
|jinpu_db|e_office_broker|经纪人手机号|读|否|
### 6. [Memcache&Redis](http://gitlab.corp.anjuke.com/_site/docs/blob/master/API/%E5%AE%89%E5%B1%85%E5%AE%A2/cache/memcache_redis.md)
### 7. 关联job
|job名称|job管理地址|功能|负责人|
|--- | --- | --- | --- |

