# 小区tab优化一期设计文档

* * *

## 项目背景

（Touch Web）移动站收录提升20%

## 资料

- PMT ： [http://p.corp.anjuke.com/project/detail?id=28188](http://p.corp.anjuke.com/project/detail?id=28188)

* * *

## SEO新增页面

- 周边配套
- 问答
- 经纪人
- 户型  

> 页面详情图： [http://p.corp.anjuke.com/file/download?id=35189](http://p.corp.anjuke.com/file/download?id=35189)

* * *

## SEO新增页面详情

* * *

### 周边配套

- 获取小区周边交通（地铁【距离，步行时间】，公交【距离，步行时间】，查询线路）
- 获取小区周边教育（幼儿园【距离】，小学【距离】，初中【距离】）
- 获取小区周边医疗(三级医院【距离】，一级医院【距离】，其他)
- 获取小区周边商业（超市【距离】，银行及ATM机【距离】，餐饮【距离】）

> API：http://m.anjuke.com/ajax/community/periphery?comm_id=16
> 相关文档:[PeripheryIndexDetail.md](http://gitlab.corp.anjuke.com/_site/docs/blob/master/ToDo/mobileWeb/devDoc/design/21214/PeripheryIndexDetail.md)

* * *


### 问答

- 获取小区的安居客百科
- 获取小区问答【回答数量】

> JAVA PAGE: http://m.anjuke.com/sh/qalist/search?q=%E9%98%B3%E6%98%8E%E8%8A%B1%E5%9B%AD%E5%B9%BF%E5%9C%BA 
开发方案：user-site下面PHP重写这个页面，逻辑参考tw_java QaListController.java


* * *


### 经纪人

- 获取小区经纪人【回答问题数量】
> 开发新API
> 方案：经纪人solr+问答solr 组合成Service

* * * *

### 户型

- 户型图（数量）

- 户型在售房源
> API:[http://m.anjuke.com/sh/community/houseprop/?comm_name=%25E4%25B8%2596%25E8%258C%2582%25E6%25BB%25A8%25E6%25B1%259F%25E8%258A%25B1%25E5%259B%25AD&comm_id=27&city_id=11&room_num=3&__REQU_SESSION_ID=REF-E641B7E9-03DF-4F55-8DBC-6E7A8F6B94E1](http://m.anjuke.com/sh/community/houseprop/?comm_name=%25E4%25B8%2596%25E8%258C%2582%25E6%25BB%25A8%25E6%25B1%259F%25E8%258A%25B1%25E5%259B%25AD&comm_id=27&city_id=11&room_num=3&__REQU_SESSION_ID=REF-E641B7E9-03DF-4F55-8DBC-6E7A8F6B94E1)

- 户型在租房源
> 开发API
> 开发方案：租房solr
