## 商业地产商铺出租单页白皮书
### 1 页面URL
```
http://sh.sp.anjuke.com/zu/17372145
```

### 2 页面说明
```
商业地产商铺出租单页，展示商铺出租信息和经纪人相关信息，楼盘信息及新铺推荐信息等
```

### 3 页面模块列表
|URL|仓库|机器|负责人|功能|
| --- | --- | --- | --- | --- |
| http://sh.sp.anjuke.com/s?p=3&ci=11&f1=381909&f2=2&f3=26 | http://ifx.jinpu.com/s | app10-237,app10-275 | 周乐钦,Peggy | 新盘ifx推荐 |


### 4 依赖的内部服务(指的是其它仓库的服务)
|服务名|仓库地址|负责人(或部门)|功能|
| --- | --- | --- | --- |

### 5 依赖的外部URL
|配置名|URL|功能|其它|
| --- | --- | --- | --- |

### 6 数据库和表:
---
* 数据库 : anjuke_db
* 地址 : 10.10.8.75:3306(master),10.10.8.94:3306(slave),10.10.8.24:3306(master),10.10.8.80:3306(slave)
* owner : 兰春

|表名称|作用|读写|是否独有|
| --- | --- | --- | --- |
| ajk_brokerextend | 获取经纪人扩展信息 | 读 | 否 |

---
* 数据库 : jinpu_db
* 地址 : 10.10.8.3:3306(master),10.10.8.124:3306(slave)
* owner : 兰春

|表名称|作用|读写|是否独有|
| --- | --- | --- | --- |
| d_block | 获取商业地产区域板块信息 | 读 | 否 |
| d_city | 获取开放了商业地产的城市信息 | 读 | 否 |
| d_district | 获取商业地产商圈信息 | 读 | 否 |
| d_house_image_category | 房源图片分类 |  读 | 否 |
| d_new_shop_business | 拼音与区域板块关系 | 读 | 否 |
| d_new_shop_business_block_map | 老商铺板块映射表 | 读 | 否 |
| d_new_shop_district | 商铺区域板块 | 读 | 否 |
| d_property_type | 获取房源类型 | 读 | 否 |
| e_adv_item | 广告信息 | 读 | 否 |
| e_building_img | 楼盘图片 | 读 | 否 |
| e_house_img_order | 房源图片排序 | 读 | 否 |
| e_map_property | 房源坐标 | 读 | 否 |
| e_map_shop | 商铺坐标 | 读 | 否 |
| e_member | 户主基本信息 | 读 | 否 |
| e_member_ext | 户主基本扩展信息 | 读 | 否 |
| e_member_score | 户主打分 | 读 | 否 |
| e_number | 获取电话信息 | 读 | 否 |
| e_property | 物业基本信息 | 读 | 否 |
| e_property_ext | 商业地产房源扩展信息 | 读 | 否 |
| e_shop_rent | 商铺出租特有信息 | 读 | 否 |
| e_shop_rent_desc | 商铺出租描述 | 读 | 否 |
| e_shop_rent_img_v2 | 商铺出租图片 | 读 | 否 |
| i_house | 推广状态查询 | 读 | 否 |


---
* 数据库 : jp_dw_stats
* 地址 : 10.10.8.90:3306(slave)
* owner : 兰春

|表名称|作用|读写|是否独有|
| --- | --- | --- | --- |
| dw_jp_midprice_monthly | --- | 读 | 否 |
| hu_guanlian_jp_prop | --- | 读 | 否 |
| hu_guanlian_jp_prop_new | --- | 读 | 否 |


### 7 Memcache&Redis

* 列表页与[商铺出租单页](Xzl_Zu_View.md)使用相同的cache配置相同
