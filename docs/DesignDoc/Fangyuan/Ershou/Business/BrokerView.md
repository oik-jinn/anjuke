# 经纪人单页白皮书

## 页面url

```
http://{pinyin}.anjuke.com/shop/salelist/{brokerid}/
```
    
### 页面模块列表
 
* 1 首页
    * 1.1 经纪人信息
    * 1.2 访问量
    * 1.3 回答的问题
    * 1.4 荣誉勋章
* 2 二手房
    * 2.1 筛选项
    * 2.2 房源列表
    * 2.3 经纪人信息
    * 2.4 荣誉勋章
* 3 自我介绍
* 4 问答
    * 4.1 问答列表
    * 4.2 经纪人信息
    * 4.3 荣誉勋章
* 5 服务档案

### 配置

### 数据库和表:
|序号|数据库|表名称|作用|读写|是否独有|使用量|
| ---|--- |  --- | --- | --- | --- | --- |
|2 |anjuke_db|ajk_brokerextend|经纪人信息|读|否|1486190*89%*5% = 6w|
|2 |anjuke_db|cst_broker_company|经纪人公司信息|读|否|1486190*89%*5% = 6w|
|2 |anjuke_db|ajk_commtype|区域/板块|读|否|1486190*89%*5% = 6w|
|2 |anjuke_db|cst_company|小区表|读|否|1486190*89%*5% = 6w|
|2 |anjuke_db|ajk_members|经纪人个人信息|读|否|1486190*89%*5% = 6w|
|2 |anjuke_db|usr_brokerextend|经纪人个人扩展信息|读|否|1486190*89%*5% = 6w|
|2.1 |anjuke_db|ajk_saleprice|二手房价格筛选项|读|否|1486190*89%*5% = 6w|
|2.1 |anjuke_db|ajk_communitys|小区信息|读|否|1486190*89%*5% = 6w|
|2 |anjuke_db|ajk_hireprice|租房价格筛选项|读|否|1486190*89%*5% = 6w|