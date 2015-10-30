## 商业地产写字楼出租单页白皮书
### 1 页面URL
```
http://sh.xzl.anjuke.com/zu/17372145
```

### 2 页面说明
```
商业地产写字楼出租单页，展示写字楼出租信息和经纪人相关信息，楼盘信息及新盘推荐信息等
```

### 3 页面模块列表
|URL|仓库|机器|负责人|功能|
| --- | --- | --- | --- | --- |
| http://sh.xzl.anjuke.com/s?p=1&ci=11&f1=338502&f2=1&f3=10 | http://ifx.jinpu.com/s | app10-046,app10-047 | 未知 | 新盘ifx推荐 |


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
| cst_broker_company | 获取经纪人门店信息 | 读 | 否 |
| cst_company | 获取经纪人公司信息 | 读 | 否 |

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
| d_line | 获取地铁线 | 读 | 否 |
| d_new_business | 获取特殊商圈信息 | 读 | 否 |
| d_new_business_block_map | 获取与d_block的映射 | 读 | 否 |
| d_new_district | 获取特殊区域板块信息 | 读 | 否 |
| d_property_type | 获取房源类型 | 读 | 否 |
| d_station | 获取站点信息 | 读 | 否 |
| e_adv_item | 广告信息 | 读 | 否 |
| e_building | 楼盘基本信息 | 读 | 否 |
| e_building_decorate | 楼盘装修 | 读 | 否 |
| e_building_environment | 楼盘环境 | 读 | 否 |
| e_building_facility | 楼盘设施 | 读 | 否 |
| e_building_img | 楼盘图片 | 读 | 否 |
| e_building_traffic | 楼盘交通 | 读 | 否 |
| e_house_img_order | 房源图片排序 | 读 | 否 |
| e_map_property | 房源坐标 | 读 | 否 |
| e_member | 户主基本信息 | 读 | 否 |
| e_member_ext | 户主基本扩展信息 | 读 | 否 |
| e_member_score | 户主打分 | 读 | 否 |
| e_number | 获取电话信息 | 读 | 否 |
| e_office_rent | 写字楼租特有基本信息 | 读 | 否 |
| e_office_rent_desc | 写字楼租描述 | 读 | 否 |
| e_office_rent_img_v2 | 写字楼租图片 | 读 | 否 |
| e_property | 物业基本信息 | 读 | 否 |
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
| salome_jp_recomm_rank | --- | 读 | 否 |


### 7 Memcache&Redis
|名称|类型|地址|申请大小|使用率|是否统一管理|功能|负责人|
| --- | --- | --- | --- | --- | --- | --- | --- |
| 0 | memcache | 10.10.8.125:11228 | 64G | 58% | 统一 | 默认cache机器 | 马明 |
| broker_info_servers | memcache | 10.10.3.106:11211 | 64G | 71% | 统一 | 经纪人信息cache | 马明 |
| broker_info_servers | memcache | 10.10.8.125:11220 | 64G | 57% | 统一 | 经纪人信息cache | 马明 |
| ipshield_servers    | memcache | 10.10.3.13 :11212 | 16G | 77% | 统一 | 拦截爬取ip | 马明 |
| ipshield_servers    | memcache | 10.10.3.13 :11213 | 16G | 77% | 统一 | 拦截爬取ip | 马明 |
| ipshield_servers    | memcache | 10.10.3.75 :11217 | 32G | 74% | 统一 | 拦截爬取ip | 马明 |
| ipshield_servers    | memcache | 10.10.3.106:11212 | 64G | 57% | 统一 | 拦截爬取ip | 马明 |
| ipshield_servers    | memcache | 10.10.3.106:11219 | 64G | 57% | 统一 | 拦截爬取ip | 马明 |
| ipshield_servers    | memcache | 10.10.3.108:11212 | 64G | 76% | 统一 | 拦截爬取ip | 马明 |
| ipshield_servers    | memcache | 10.10.8.125:11213 | 64G | 57% | 统一 | 拦截爬取ip | 马明 |
| ipshield_servers    | memcache | 10.10.8.125:11214 | 64G | 57% | 统一 | 拦截爬取ip | 马明 |
| ipshield_servers    | memcache | 10.10.8.125:11217 | 64G | 57% | 统一 | 拦截爬取ip | 马明 |
| ipshield_servers    | memcache | 10.10.8.125:11218 | 64G | 57% | 统一 | 拦截爬取ip | 马明 |
| ipshield_servers    | memcache | 10.10.8.125:11219 | 64G | 57% | 统一 | 拦截爬取ip | 马明 |
| ipshield_servers    | memcache | 10.10.3.170:11212 | 64G | 70% | 统一 | 拦截爬取ip | 马明 |


### 8 关联job
|job名称|job管理地址|功能|负责人|
| --- | --- | --- | --- |
