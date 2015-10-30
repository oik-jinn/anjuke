### 房贷计算器页面：猜你喜欢 设计文档


* 猜你喜欢：位置在“房贷计算器”之下，一共四个推荐位，样式见UI稿

* 标题： 猜你喜欢

* 点击后新窗口打开房源单页

* 加码： from ＝fdjsq_page_rec_cnxh
    
* 右侧广告位  点击进入二手房列表页  加码： from ＝fdjsq_page_banner   
 
* 加跟踪码（commlist_recommend）便于统计该模块产生的vppv。（点击扣费）

* 模块加曝光埋点便于统计曝光量及曝光时长。

* 猜你喜欢推荐逻辑：
	
  * 由BI提供接口（地址如下）：
	
		> http://gitlab.corp.anjuke.com/_microdata/recommend-api-doc/blob/master/sale-pc-pro-history.md

		推荐位默认房源由房贷计算器默认值（100万）推荐而出