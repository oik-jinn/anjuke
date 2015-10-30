#### 需求整理

* url跳转 — `User_Common_MobileRedirect`调整
    * pc单页url http://sh.zu.anjuke.com/rent/D46
    * tw 对应url http://m.anjuke.com/sh/rent/D46/

* 单页
    基本复用二手房单页component
    * 回退按钮 — 回退到列表页
        * 有referer时用referer，没有时退回租房首页 http://m.anjuke.com/sh/rental/
    * 收藏弹层 — 可复用
    * 默认宣传图片 — <span style="color:red">新增component</span>，可复用，可配置图片和顺序 (User_Touch_Property_PublicityPic)
    * <32个字，可换行，标题后显示 整租/合租 标签 (User_Touch_Property_Title)
    * 基础信息，<span style="color:red">新增component</span>,包括{租金,押付方式,房型,面积,楼层,朝向,装修情况,类型,时间,小区,电话模块} (User_Touch_Rent_Detail)
        “查看详情” — id定位到“小区及周边”模块
        点击电话模块 — 新增，android 与 iOS 样式不同 (User_Touch_Property_Collect)
    * 配置和地铁信息
        * 配置，为空不显示
        * 地铁，为空不显示
        * 要求，无合租要求不显示
    * 详细信息
    * 附近推荐(User_Touch_Property_RecProp)
        * 附近推荐+看了还看 =更多推荐=> 猜你喜欢列表
        * 附近小区ajax http://m.anjuke.com/sh/rent/nearby/33434223-3?__REQU_SESSION_ID=REF-C4ADF0BC-9E8F-4DB5-B619-245A364E2878
        * 看了还看ajax http://m.anjuke.com/sh/rent/recommend/33434223-3?__REQU_SESSION_ID=REF-C4ADF0BC-9E8F-4DB5-B619-245A364E2878
    * 小区及周边(User_Touch_Property_PropComm)
    * 附近地铁房推荐 =更多推荐=> 线上一致
        * 附近地铁房推荐ajax http://m.anjuke.com/sh/rent/recommend/m33434223-3?__REQU_SESSION_ID=REF-C4ADF0BC-9E8F-4DB5-B619-245A364E2878
    * 租房管家 — <span style="color:red">新增component</span>，可复用，显示元素可配置(User_Touch_Property_Sublessor OR User_Touch_Rent_Broker)
        * 点击该区域出现呼叫弹层(区分android/iOS)
        * 宣传信息 — 每个大业主相同(前端写死)
    * 下一套 ? (prd里木有)
    * SEO内链 (User_Touch_Seo_Common_FooterSeoA)
        * 附近小区推荐
        * 大家都在搜
        * 热门城市租房
    * 面包屑 (User_Touch_Property_Bcrumb)
    * 曝光和SOJ (soj wiki)

    ```
    // 当前租房单页soj参数
    
    p:Rent_View
    h:http://m.anjuke.com/sh/rent/33115272-3/?isauction=2
    r:
    sc:{"w":"320","h":"568","r":"1"}
    site:m_anjuke
    guid:C18EF64B-7540-3A6F-9BA5-281774994C8D
    ctid:11
    luid:
    ssid:EBF2E934-EA3C-FDE7-2203-C7700F6795A7
    uid:0
    t:1411981016518
    pn:Rent_View
    cp:{}
    ```

    * PN参数

    房源单页名称 | PN标识定义                                      
    --- | ---
    租房频道-大业主合租 | View_Sublessor_IndexPage_Zheng
    租房频道-大业主整租 | View_Sublessor_IndexPage_He


    * 不需要发送付费click
    
    * 推荐

    ```
    http://www.anjuke.com/rec/rent/pagerecwithdistance/?id=33434223&type=3&cityid=11&limitnear=4&limitfar=46&from=touchweb_rent_page&from_module=touch_web
    ```

---
#### 环境和配置支持
* m.anjuke.com 的 rent/D00 应该分发到 php 服务器
    * route 增加了以后，touch_config 需要新增对 rent/D00 的支持
    * dev config => m.{dev_name}.dev.anjuke.com/{city_py}/rent/D{spid}/
    * pg config => m.{pmt_name}.anjuke.test/{city_py}/rent/D{spid}/
    * ga config => m.anjuke.com/{city_py}/rent/D{spid}/

    ```
    // touch.config
    
    location ~* ^/[a-z]+/rent/D[0-9]+ {
        rewrite . /index.php last;
    }
    location ~* ^/ajax/rent/ {
        rewrite . /index.php last;
    }
    ```

---
#### 设计实现

* 配置

    * devel-config/nginx/touch.conf

    ```
    location ~* ^/[a-z]+/rent/d[0-9]+ {
        rewrite . /index.php last;
    }
    location ~* ^/ajax/rent/ {
        rewrite . /index.php last;
    }
    ```

    * user-site/app-user-touch/config/route.php

    ```
    // 大业主单页url http://m.anjuke.com/sh/rent/D99/
    $config['mappings'][$prefix.'Zufang_Rent_Sublessor_View'] = array(
        '^/[a-z]+/rent/(d|D)[0-9]+'
    )
    // ajax url http://m.anjuke.com/ajax/rent/recommend/?type=0
    $config['mappings'][$prefix.'Zufang_Rent_RecommendAjax'] = array(
        '^/ajax/rent/recommend'
    )
    ```

    * app-user-common/config/redirect.php

    ```
    $config['mobile_zu'] = array(
        // ...
        'jumptSublessorRent' => '^/rent/(D[0-9]+)',
    );
    ```

    * 目录结构

    ```
    component/user/touch/property/PublicityPic.php  # 宣传图片模块(免中介...)
    component/user/touch/property/SublessorTitle.php  # 标题模块
    component/user/touch/property/Detail.php  # 房屋基础信息模块
    component/user/touch/property/SublessorCollect.php  # 大业主信息模块下的电话模块
    component/user/touch/property/Deploy.php  # 配置及地铁信息
    component/user/touch/property/SublessorBroker.php  # 大业主信息模块
    component/user/touch/property/PublicityInfo.php  # 宣传信息(我承诺...)
    component/user/touch/property/ScrollCollect.php  # 跟随滚动的电话模块
    

    controller/user/touch/zufang/rent/BaseView.php  # 基本view类，抽象类
    controller/user/touch/zufang/rent/Click.php  # 预留，大业主用不到
    controller/user/touch/zufang/rent/RecommendAjax.php  # 三个推荐走统一入口，用type值获取推荐类型的数据
    controller/user/touch/zufang/rent/sublessor/View.php  # 大业主单页类

    page/user/touch/zufang/rent/sublessor/View.php
    page/user/touch/zufang/rent/sublessor/View.phtml
    page/user/touch/zufang/rent/sublessor/View.js
    page/user/touch/zufang/rent/sublessor/View.css
    ```

    * BaseViewController设计

    ```php
    // User_Touch_Zufang_Rent_BaseViewController
    public function handleRequestInner() {
        // 获取房源基本信息(需要继承实现)
        // 获取经纪人信息(需要继承实现)
        // 获取其他个性化信息(可以通过继承重写，默认返回true)
        // 获取小区基本信息
        // 获取小区周边信息
        // 获取底部seo内链信息
    }
    ```

---
#### 历史修改
```
2014-10-08 11:00
1. 修正了大业主单页的route url 和 控制器名
2. 修正了ajax 控制器名
3. 修改了nginx的url适配
2014-10-08 16:30
1. 新增了"配置及地铁信息component"
```