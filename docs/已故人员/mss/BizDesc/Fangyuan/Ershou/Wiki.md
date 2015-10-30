### 列表页逻辑

* 之前文档请移步：[http://wiki.corp.anjuke.com/Anjuke-Sale](http://wiki.corp.anjuke.com/Anjuke-Sale)


### 房源solr


* 概述
    * 目前二手房分精选和套餐solr，其中精选单独一组，套餐会有按城市拆分
    * 具体拆分详细见：[http://gitlab.corp.anjuke.com/_site/docs/blob/master/BizDesc/Fangyuan/Ershou/SolrSplit.md](http://gitlab.corp.anjuke.com/_site/docs/blob/master/BizDesc/Fangyuan/Ershou/SolrSplit.md)

###房源推广

* 中间件通知的房源以中间件通知为准

    * 推广中间件接口：[详细文档请点击](SpreadMiddleware.md)
    * 上下架处理流程：[详细文档请点击](SpreadUpdown.md)
    

###房源数据表

---

#### 个人房源表
* 所在表：anjuke_db.ajk_pprops_sale

#### 经纪人房源表

* 所在表
    * propertys_db.ajk_propertys
    * propertys_sh_db.ajk_propertys
    * propertys_db_04.ajk_propertys
    * propertys_other_db.ajk_propertys
* 说明：
    经纪人房源数据是分库的，分库规则如下：
    * 上海（cityId=11）:propertys_sh_db.ajk_propertys
    * 北京（cityId=14）:propertys_db.ajk_propertys
	* other（cityId=<41）:propertys_other_db.ajk_propertys
	* four（cityId>=42）:propertys_db_04.ajk_propertys
    
#### 经纪人抓取房源表 

* 所在表：同上经纪人房源表，通过字段sowooid>0 说明是抓取房源
* 抓取房源经纪人信息获得
    * 所在表：anjuke_db.ajk_swsale
    * 查询方式：通过sowooid(即抓取经纪人id)