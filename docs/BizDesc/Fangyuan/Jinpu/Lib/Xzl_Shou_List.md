## 商业地产写字楼出售列表页白皮书
### 1 页面URL
```
http://sh.xzl.anjuke.com/shou/
```

### 2 页面说明
```
商业地产写字楼出售列表页，包含筛选项，房源基本信息，推荐内外链和广告等
```

### 3 页面模块列表
|URL|仓库|机器|负责人|功能|
| --- | --- | --- | --- | --- |
| http://sh.sp.anjuke.com/s?p=12&ci=11&f1=0&f2=0 | http://ifx.jinpu.com/s | xapp10-237 | Peggy | 本月人气新盘 |
| http://sh.sp.anjuke.com/s?p=46&ci=11&f1=0&f2=0 | http://ifx.jinpu.com/s | xapp10-237 | Peggy | 您可能感兴趣的新盘 |
| http://ifx.fang.anjuke.com/s?c=11&p=3003&r=0&sr=0&o=1 | 未知 | app10-018 | 杨勇 | 未知 |
| http://ifx.fang.anjuke.com/s?c=11&p=961&o=1 | 未知 | app10-018 | 杨勇 | APP下载广告 |
| http://ifx.fang.anjuke.com/s?c=11&p=962&o=1 | 未知 | app10-018 | 杨勇 | APP二维码广告 |
| http://ifx.fang.anjuke.com/s?c=11&p=963&o=1 | 未知 | app10-018 | 杨勇 | 百度广告 |
| http://ifx.fang.anjuke.com/s?c=11&p=972&o=1 | 未知 | app10-018 | 杨勇 | 顶通广告 |


### 4 依赖的内部服务(指的是其它仓库的服务)
|服务名|仓库地址|负责人(或部门)|功能|
| --- | --- | --- | --- |

### 5 依赖的外部URL
|配置名|URL|功能|其它|
| --- | --- | --- | --- |

### 6 数据库和表:
---
* 数据库 : jinpu_db
* 地址 : 10.10.8.3:3306(master),10.10.8.124:3306(slave)
* owner : 兰春

|表名称|作用|读写|是否独有|
| --- | --- | --- | --- |
| d_block | 获取商业地产区域板块信息 | 读 | 否 |
| d_city | 获取开放了商业地产的城市信息 | 读 | 否 |
| d_district | 获取商业地产商圈信息 | 读 | 否 |
| d_filter_area_sale | 写字楼售区域筛选项 | 读 | 否 |
| d_filter_total_price | 写字楼售价格筛选项 | 读 | 否 |
| d_filter_unit_price | 写字楼售单位价格段筛选项 | 读 | 否 |
| d_new_district | 获取特殊区域板块信息 | 读 | 否 |
| d_property_type | 获取房源类型 | 读 | 否 |
| e_auto_title | 获取SEO标题 | 读 | 否 |
| e_member | 户主基本信息 | 读 | 否 |
| e_office_rent | 写字楼租特有基本信息 | 读 | 否 |
| e_office_rent_img_v2 | 写字楼租图片 | 读 | 否 |
| e_property | 物业基本信息 | 读 | 否 |
| e_shop_rent | 写字楼出售信息 | 读 | 否 |
| e_shop_rent_img_v2 | 写字楼出售图片 | 读 | 否 |
| i_house | 推广状态查询 | 读 | 否 |


---
* 数据库 : jp_dw_stats
* 地址 : 10.10.8.90:3306(slave)
* owner : 兰春

|表名称|作用|读写|是否独有|
| --- | --- | --- | --- |
| hu_rank_jp_all_score_type | --- | 读 | 否 |

### 7 Memcache&Redis

* 列表页与[商铺出租单页](Xzl_Zu_View.md)使用相同的cache配置相同
