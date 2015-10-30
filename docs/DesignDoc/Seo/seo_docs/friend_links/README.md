# 友链开发文档

```
友链的目的是和其他网站互换链接，彼此起到相互推荐的、增加PV & UV的作用；目前安居客的友链服务由新房吕连军负责；二手房这边是有友链的方式为http同步请求
```

## 友链DB

```
友链DB数据库只有一个`aifanguser_db.seo_friendlink`
```

## 友链调用方式

```
    1. 基于socket的rpc调用方式(这种访问方式服务不稳定，经常会挂掉)

    2. http同步请求(较稳定，目前PC web端已全部走http)
```

## 友链接口在代码仓库分布

```
    + 友链调用按照页面类型可以分为默认列表页、首页、区域、板块、小区等调用方式

    + 只有anjuke_usersite仓库里使用到了区域、板块友链服务，其他仓库都只是使用默认列表页友链服务params($city_id, $page_identifier)

    1. anjuke-usersite仓库:

        列表页友链调用:

            `http方式` /user-site/app-biz/classes/biz/seo/SeoCommonBiz.php -> `getSeoFriendLinksByApi`($params ... act) [目前在使用方式]
            `rpc`方式  /user-site/app-biz/classes/biz/seo/SeoCommonBiz.php -> `getSeoLinkByAiFang`($params ... act)

        区域、板块友链调用:

            `http调用方式` /user-site/app-biz/classes/biz/seo/SeoFangPriceBiz.php -> `getAreaOrBlockFriendLinksFromNewApi($params ... act)`

    2. 老anjuke代码库:

        列表页友链：

            'http调用方式' /anjuke-site/app-anjuke-kernel/classes/dao/seo/common.php -> `getSeoFriendLinksByApi`
            `rpc调用`     /anjuke-site/app-anjuke-kernel/classes/dao/seo/common.php -> `getSeoLinkByAiFangData`


    3. 老hao-zu代码库:

        列表页友链：

            'http调用方式' /haozu-site/app-haozu-core/classes/dao/seo/Seo.php -> `getSeoFriendLinksByApi`
            `rpc调用`     /haozu-site/app-haozu-core/classes/dao/seo/Seo.php -> `get_friendlinks_by_cityid_and_pagetype`

    4. jinpu-site代码库:

            `http调用方式` /jinpu-site/app-common/classes/bll/AifangAdvBll.php -> `getSeoFriendLinkFromAifangApi`
            `rpc调用方式`  /jinpu-site/app-common/classes/bll/AifangAdvBll.php -> `get_friendlinks_by_cityid_and_pagetype`
```