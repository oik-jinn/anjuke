
## Business Terms

----

### PC,TOUCH,PAD,APP

### PPC

* Pay per click 即有些城市上了ppc业务，该城市房源按点击效果收费
* soj类型的一种

### RANK

* 排名，房源按rank排序，rank的值由BI提供


### 曝光

* 用户浏览到制定模块，会发一个含有anjuke-npv的http链接，在首页，单页，列表页都有。
* 目的:用户对那块更感兴趣;分析用户浏览了哪些内容，了解网站页面设计是否合理
* soj一种

### VPPV

* PV:即page view，页面浏览量，通常是衡量一个网络新闻频道或网站甚至一条网络新闻的主要指标。
* VPPV：即房源单页的PV


### PV统计

* 我们网站是通过前台js发送**SOJ**信息来统计网站pv的
* 每个页面都有一条**SOJ**的信息发送来统计pv

### SEO（Search Engine Optimization）搜索引擎优化

* Seo是专门利用搜索引擎的搜索规则来提高目前网站在有关搜索引擎内的自然排名的方式。
* SEO的目的让网站在行业内占据领先地位，从而获得品牌收益。
* http://wiki.corp.anjuke.com/USER-Department-QA-user-product-seo-js

### SEM Search Engine Marketing

* 让用户发现信息，并通过（搜索引擎）搜索点击进入网站/网页进一步了解他所需要的信息
* 需求缴费

### SOJ相关

* soj来源
* http://wiki.corp.anjuke.com/Sojourner_Log

* 什么是soj
* 是将数据按照一定格式发送到s.anjuke.com
* 统计方拿到这些数据可以做各种统计

* SOJ的应用

* 统计页面pv
* 统计某个按钮的点击数
* http://wiki.corp.anjuke.com/index.php?title=SOJ_for_%E5%A5%BD%E7%9B%98%E5%8D%8F%E8%AE%AE

* SOJ示例
* ![Soj eg](soj_eg.png)


* 常用参数说明

Field|Description
---|---
**site**|site=anjuke来标记这条信息是统计，一个页面的soj消息只能有一条是site=anjuke，当前页再发送soj信息必须是soj=anjuke-npv,用来区分是非pv统计
**p**|page name,BI根据此值来区分是哪个页面的PV，当你换了某个页面名称是要通知BI部门
**pn**|page aliases name,如点击扣费参数二手房单页是Anjuke_View_Property
**cp**|custom parameters，这个可用来发送你自己定义的参数，以上示例中cp参数用来发送了房源点击数据
**guid**|用户唯一标识，不过期的cookie，用户首次来网站会种上此cookie，uv就是按照guid统计的

### 扣费类型

* 精选，竞价
* 套餐，


## Technical Terms

----

### Outage

* 中文意思是停运：我们公司Outage一般是系统维护，db升级，比如迁移数据库，一般在零晨进行， 有时需要将网站部分切到维护页

### SA-RT
* 用来提交给运维修改线上配置，如查你想修改线上配置，请在http://ibug.corp.anjuke.com/ticket/add提交SA-RT

### DB-RT
* 跟SA-RT差不多意思，只不过是提交线dba的，比如增加数据表等

### DCT
* 为了统一管理线上数据库配置，dba做了一个修改数据配置的系统，修改好，由每台app机器去拉取最新线上db的配置
* 提交dct的参数说明
* 所在Pool：anjuke_city（目前我们只用anjuke_city）
* LDSN名称：对应代码中的database.php中$config的key
* LDSN对应数据库：数据库名称

