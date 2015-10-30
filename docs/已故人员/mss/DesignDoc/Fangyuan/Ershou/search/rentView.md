租房房源单页改版项目设计
=====

项目背景:
----
* 房源单页改版,新的UI.代码迁移至新框架里面,包括前端和后端部分
* 范围:上海租房经纪人房源.即：http://sh.zu.anjuke.com/fangyuan/28657197(房源ID)
* 式样图如下: ![首页图片](http://git.corp.anjuke.com/_user_site/doc/browse/master/fangyuan/design/rentViewUi.gif)


### 相关数据表
* 区域或版块：rent_db.area
* 房源数据表：rent_db.prop
* 房源计划表：rent_db.promotion_prop_planning
* 房源图片：prop_images
* 房源扩展图片：prop_images_ext
* 小区信息：ajk_communitys
* 小区扩展信息：ajk_commextend
* 合并小区信息：ajk_merge_comm
* 校园租房信息：zf_special_search

 
### 视图相关组件

#### 1. header(已有)
        app-user-component/component/user/component/header/
        ├── CitySelector.php
        ├── Header.php
        ├── SearchBar.php
        ├── TabBar.php
        └── WideHeader.php

#### 2. 收藏房源&&其他功能 tools(已有)
* 组件:

        app-user-component/component/user/component/tools/
        ├── AddFavorite.php //收藏按钮
        ├── Report.php  //举报
        └── SendToMobile.php //发送到手机

#### 3.小区相关组件（已有）（异步）
* 组件：

        app-user-component/component/user/component
        ├── community
        │   ├── common
        │   │   ├── CommInfo.php //小区信息的公共组件
        │   │   └── CommNearbyInfo.php //小区周边信息
        │   └── map
        │       ├── Pano.js     
        │       └── Pano.php //街景组件

* 周边配套接口：http://www.anjuke.com/v3/ajax/getCommNearbyInfo/?communityId={小区ID}
* 问题：<span style="color:#ff0000;">有js跨域问题,是解决跨域问题还是改到租房域名</span>

#### 4. 右侧主推或推荐（异步）
* 组件：  

        app-zufang-web/component/
        └── rent
            └── MainPushOrRecommend(右侧主推或推荐)

* 接口：http://sh.zu.anjuke.com/api/rec/pagerec/?cityid=11&id=28540786&type=3&limit=9&resulttype=2&from=SITE_RENT_PAGE&r=0.5044892129953951

#### 5. 底部推荐或感兴趣（异步）
* 组件：  

        app-zufang-web/component/
        └── rent
            └── recommend(右侧主推或推荐)
* 接口：底部推荐同接口4,
        底部感兴趣接口：http://sh.zu.anjuke.com/api/rec/recommendpropall/?proid=28841791&type=3&limit=4&from=SITE_RENT_PAGE

#### 6. 底部seo或广告区域
* 组件：  

        app-zufang-web/component/
        └── footer
            └── SeoAd.php 


### 后端service文件结构

#### 1. 房源和图片信息
* 房源service目录结构:

        app-zufang-core/classes/zufang/core/rent
        ├── bll
        ├── dao
        ├── model
        └── service
            ├── RentService.php
            └── RentImagesService.php

* 房源biz目录结构:

        app-biz/classes/biz/zufang/rent/
        ├── bll
        ├── dao
        ├── model
        └── service
            └── RentBiz.php

    * 方法:getPropertyImagesBrokerInfo($city_id, $pro_id):返回房源、图片、经纪人信息


#### 2.租房经纪人信息

* 经纪人service目录结构:

        app-zufang-core/classes/zufang/core/broker
        ├── bll
        ├── dao
        ├── model
        └── service
            ├── BrokerService.php

* 经纪人biz目录结构:

        app-biz/classes/biz/zufang/broker/
        ├── bll
        ├── dao
        ├── model
        └── service
            └── BrokerBiz.php

    * 方法:getBrokerInfo($broker_id): 返回经纪人信息

#### 3.点击扣费

* 类结构:

        app-zufang-web/classes/zufang/web/Click.php
        ├── class Zufang_Ppc_Click extends User_Common_Ppc_Click 

* 代码逻辑与二手房逻辑相似，集成基类中的点击扣费框架
    

### 测试重点：

    * 房源点击扣费
    * ppc的房源曝光
    * 房源基本信息
    
### 服务器部署

* 配置以下url走新框架:
    * 上海租房经纪人单页: 
        * 例如：http://sh.zu.anjuke.com/fangyuan/{房源ID}
        * 规则：
            * userAgent必须是非pad
            * urlPath是fangyuan/{id}，后面要有正则确认是数字，要不然跟列表页冲url冲突
    * 所有租房域名加v3/ajax/: 
        * 例如：http://www.zu.anjuke.com/v3/ajax/
        * 规则：只有是urlPath是v3/ajax都指向新框架
        
### TODOList

* 增加配置：common.php database.php

* 增加pagename：通知BI
