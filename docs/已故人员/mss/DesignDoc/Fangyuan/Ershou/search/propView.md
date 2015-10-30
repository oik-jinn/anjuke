房源单页改版项目设计
=====

项目背景:
----
* 房源单页改版,新的UI.代码迁移至新框架里面,包括前端和后端部分
* 范围:上海经纪人房源.
* 式样图如下: ![首页图片](http://git.corp.anjuke.com/_user_site/doc/browse/master/fangyuan/design/propViewUi.gif)

注意点:
----
* 单页的访问速度
* 代码可阅读性,可维护性

功能模块:
----
 

### 1. header 
* 等房价seo项目pmt-20426-site-anjuke_usersite分支上线之后合并,做成通用的header.
* 添加是否显示城市选择和用户登陆状态的参数,目录(只写了php):

        app-user-component/component/user/component/header/
        ├── CitySelector.php
        ├── Header.php
        ├── SearchBar.php
        ├── TabBar.php
        └── WideHeader.php
* css/js文件放置路径说明：
    * 对应组件中的css和js和php文件同名称，组件中的js交互写在对应组件中，不要写在全局js中

### 2. 收藏房源&&其他功能 tools
* 增加component,收藏房源&&其他功能(发送到手机,举报,新房源通知),目录:

        app-user-component/component/user/component/tools/
        ├── AddFavorite.php     //收藏按钮
        ├── NoticeNewProperty.php   //新房源提醒
        ├── Report.php  //举报
        └── SendToMobile.php //发送到手机

### 3. 房源信息
* 二手房房源service目录结构:

        app-ershou-core/classes/ershou/core/property
        ├── bll
        ├── dao
        ├── model
        └── service
            ├── BorkerService.php
            └── PropertyService.php

* 说明:
    * 里面方法见当前Ershou_Core_Service_PropertyService,有需要的再补充;
    * getPropertyAllInfoByCityId($city_id, $pro_id):
            调用经纪人事业部提供的api获取房源信息,小区信息,经纪人信息.//张健确认
    * getCommTypes($type_code):
            获取区域板块的信息,在面包屑部分显示.
    * getPropertyImages($prop_id):
            获取房源室内图&&房型图.
    * getPropertyTags:
            获取房源标签

* 室内图:
    * api提供,房型图按PRD说明顺序显示.
* 外景图:
    * 小区community-core中需要增加获取小区图片的方法,从anjuke_db.ajk_attachments_comm获取图片;
    * 室内图和外景图处理逻辑参考老的代码:anjuke/app-anjuke/controller/ajax/AjaxPropertyView.php:dao_get_comm_images();
* 街景图:
    * 小区community-core中需要增加获取小区街景数据的方法,从anjuke_db.map_communities_soso_pano获取街景数据;
    * 参考老代码BLL_Communitys_Community::getCommPanoInfo($comm_id);
    * app-user-component添加公共component;

            app-user-component/component/user/component
            ├── community
            │   ├── common
            │   │   ├── CommInfo.php //小区信息的公共组件
            │   │   └── CommNearbyInfo.php //小区周边信息
            │   └── map
            │       ├── Pano.js     
            │       └── Pano.php //街景组件

* 房源信息展示:
    * 基本数据展示
    * 房源标签展示
    * 房源描述html标签过滤&&字符长度截取

### 4. 经纪人信息
* 获取经纪人信息Props_Data::formatBrokerInfo($propInfo);
* 微聊在线咨询的url;(目前未上线)
* 经纪人主推房源,上线之后迁移到新框架;(目前未上线)

        app-ershou-web/component/
        └── property
            └── recommend
                └── BrokerRecommend.php //经纪人主推房源组件

### 5. 小区信息
* 小区信息service迁移到新框架,已经迁了,目录结构需要调整.

        app-community-core/classes
        └── community
            └── core
                ├── comm
                │   └── service
                │       ├── CommunityService.php

* 调用组件CommInfo,显示小区基本信息.
    * 参数:comm_info
    
### 6. 小区配套信息
* 调用组件CommNearbyInfo
    * 组件参数:comm_id,comm_name
    * 跟产品确认,各模块重复显示小区名是否有必要?
* ajax显示周边配套数据
    * 接口url:
        * http://www.anjuke.com/v3/ajax/getCommNearbyInfo/?communityId=?
    * 参数说明:
        * comm_id:小区id,例:1234
    * 返回值说明:

        //TO DO

### 7. 参考月供
* 调用组件MonthPay(命名?)

        app-ershou-web/component/
        └── property
            └── price
                └── MonthPay.php //参考月供组件 

* ajax显示月供数据
    * 接口url:
        * http://www.anjuke.com/v3/ajax/getMonthPay/
    * 参数说明:
        * prop_id:房源id,例:1234
    * 返回值说明:

        //TO DO

### 8. 房源推荐
* ajax获取,调用老的接口:
    * <http://shanghai.anjuke.com/rec/sale/proprecwithdistance/?cityid=11&proid=218298460&resulttype=2&limitfar=8&showsoj=1&limitnear=20&from=site_sale_page&r=0.47559189423918724>
* 接口文档链接; //TO DO

### 9. 房产知识,房产百科
* 房产知识:
    * ajax获取,调用老的接口:
        * <http://shanghai.anjuke.com/api/fangyuanquestion/?comm_id=263532&comm_name=中凌滨江苑>
    * 接口文档链接; //TO DO
* 房产百科: //TO DO

引入的js框架说明：
----
* APF.js 安居客js框架
* prototype.js 是否还要引入？

服务器部署
----
* 配置以下url走新框架:
    * 上海房源单页: http://shanghai.anjuke.com/prop/view/{房源ID}(注意：仅上海的房源单页)
    * 小区配套信息: http://www.anjuke.com/v3/ajax/getMonthPay/
    * 参考月供: http://www.anjuke.com/v3/ajax/getMonthPay/
