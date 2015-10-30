## 商业地产商铺出售列表页白皮书
### 1 页面URL
```
http://sh.sp.anjuke.com/shou/
```

### 2 页面说明
```
商业地产商铺出售列表页，包含筛选项，房源基本信息，推荐内外链和广告等
```

### 3 页面模块列表
|URL|仓库|机器|负责人|功能|
| --- | --- | --- | --- | --- |
| http://sh.sp.anjuke.com/s?p=14&ci=11&f1=0&f2=0 | http://ifx.jinpu.com/s | xapp10-237 | Peggy | 本月人气新铺 |
| http://sh.sp.anjuke.com/s?p=48&ci=11&f1=0&f2=0 | http://ifx.jinpu.com/s | xapp10-237 | Peggy | 您可能感兴趣的新铺 |
| http://ifx.fang.anjuke.com/s?c=11&p=3005&r=0&sr=0&o=1 | 未知 | app10-018 | 杨勇 | 未知 |
| http://ifx.fang.anjuke.com/s?c=11&p=954&o=1 | 未知 | app10-018 | 杨勇 | APP下载广告 |
| http://ifx.fang.anjuke.com/s?c=11&p=955&o=1 | 未知 | app10-018 | 杨勇 | APP二维码广告 |
| http://ifx.fang.anjuke.com/s?c=11&p=956&o=1 | 未知 | app10-018 | 杨勇 | 百度广告 |
| http://ifx.fang.anjuke.com/s?c=11&p=969&o=1 | 未知 | app10-018 | 杨勇 | 顶通广告 |
| http://ifx.fang.anjuke.com/s?c=11&p=970&o=1 | 未知 | app10-018 | 杨勇 | 底通广告 |


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
| d_new_district | 获取特殊区域板块信息 | 读 | 否 |
| d_new_shop_district | 获取商铺区域板块信息 | 读 | 否 |
| d_property_type | 获取房源类型 | 读 | 否 |
| d_shop_filter_unit_price | 商铺<面积-价格>筛选项 | 读 | 否 |
| e_auto_title | 获取SEO标题 | 读 | 否 |
| e_building_img | 楼盘图片 | 读 | 否 |
| e_cms_catetory | 内容管理模块位置划分 | 读 | 否 |
| e_cms_content | 内容管理模块内容 | 读 | 否 |
| e_member | 户主基本信息 | 读 | 否 |
| e_office_rent | 商铺租特有基本信息 | 读 | 否 |
| e_office_rent_img_v2 | 商铺租图片 | 读 | 否 |
| e_partner | ? | 读 | 否 |
| e_property | 物业基本信息 | 读 | 否 |
| e_property_new_content | 新盘特色信息 | 读 | 否 |
| e_property_new_info | 新盘信息(交房年月等) | 读 | 否 |
| e_property_new_price | 新盘单位面积价格 | 读 | 否 |
| e_shop_rent | 商铺出售信息 | 读 | 否 |
| e_shop_rent_img_v2 | 商铺出售图片 | 读 | 否 |
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
