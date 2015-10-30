# SEO 租房房源列表页



## 数据来源


### 已选条件　＆　您是不是要找
新框架下面的api

### 搜索框


### 筛选项

区域：　rend_db.area

装修：　ajk_fitment（配置）

房屋类型：housetype（配置）

价格：　rend_db.zf_price



### 附近站点附近小区

如果匹配到小区时　会获取小区周边4公里的小区５个

http://sc10-001.a.ajkdns.com:8983/haozu-metro/select?　租房地铁solr


### 相关推荐

### 相关区域租房

select * from area where typeflag='1' and parentid='0' and cityid=? order by typerank asc

获得当前城市所有区域

### 相关小区推荐


调用新框架api

 controller Ershou_Web_Seo_Api_SeoChannelApiController


seo_db.seo_online_words


随机取10条


### 相关房价推荐


调用新框架api

controller Ershou_Web_Seo_Api_SeoChannelApiController

seo_db.seo_online_words


随机取10条

### 相关地铁找房

匹配到了地铁取地铁站店

### 相关公交找房

匹配到了公交，则取公交站点


### 相关二手房信息

获得当前城市下所有区域

### 租房房源列表

用新框架下面的租房搜索底层

### 当小区房源为零时会走补充逻辑

1.获取当前小区的房源,


拆词后判断为小区词，推荐该小区周边5个小区的7天内全部房源, 推荐房源按rank分值从高到低排序



