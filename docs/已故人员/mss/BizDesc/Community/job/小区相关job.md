小区Job相关文档
=====

## 小区solr
* 小区每天rebuild solr job
    * 上海 http://drone.corp.anjuke.com/scheduler/job/180/view
    * 北京 http://drone.corp.anjuke.com/scheduler/job/181/view
    * other http://drone.corp.anjuke.com/scheduler/job/148/view
    * four http://drone.corp.anjuke.com/scheduler/job/132/view

* 根据消息通知队列增加删除修改小区solr数据,更新小区缓存
    * 接口文档 http://git.corp.anjuke.com /_user_site/doc/browse/master/community/小区更新接口文档.md

* 修复单个小区job
    * 修改 job 的小区 id 参数; http://drone.corp.anjuke.com/scheduler/job/179/view

## 小区周边抓取,计算 可以参考小区周边设计文档

* 抓取job,由于抓取耗时较长,都是线下抓取,然后经产品|运营校验后直接导入线上,没有在线上部署
    * path : app-ershou-job/classes/ershou/job/community/nearby/data/Producer.php

* 抓取的数据同步到可以使用的 db job
    * path : app-ershou-job/classes/ershou/job/community/nearby/SyncCrawlData.php
* 小区周边数据(公园,景点,医院...)写入solr job
    * http://drone.corp.anjuke.com/scheduler/job/128/view

* 小区周边关系计算job
    * 按类型分了几个job分别运行 job id 155~160
    * http://drone.corp.anjuke.com/scheduler/job/155/view


## 小区得分

* 同步 BI 计算得分,dw下的ajk_nearby_score同步到user_prop_db
    * http://drone.corp.anjuke.com/scheduler/job/155/view




## 小区标签

* 计算小区标签 job path : app-ershou-job/classes/ershou/job/community/label/CreateCommLabelData.php

* 从文件导入小区标签 job path :app-ershou-job/classes/ershou/job/community/label/ImportCommLabelData.php

