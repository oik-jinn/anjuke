# 商业地产Solr现存在问题及解决方案【2015-08-16】

```
商业地产业务由来已久，近年来没有人维护，solr字段已经不能适应新业务需求。2015.08月开通商业地产新城市期间写字楼租、售、商铺租、售出现了较大的solr问题
```

+ [写字楼租](http://search.corp.anjuke.com/service_detail.php?service_id=197)

+ [写字楼售](http://search.corp.anjuke.com/service_detail.php?service_id=196)

+ [商铺租](http://search.corp.anjuke.com/service_detail.php?service_id=195)

+ [商铺售](http://search.corp.anjuke.com/service_detail.php?service_id=194)

## 现存solr问题整理

|QId|QTitle|QDetail|
|--- |--- |--- |
|1|rank排序|房源rank排序存在较大问题，已经与庞龙修过一次rank，我们只是简单的从solr里随机，不管筛选的是不是有排序，这样会导致用户筛选排序后出现的房源展示顺序不对问题|
|2|type区分|没有哪个字段能区分房源类型，比如是ajk经纪人房源还是抓取房源|
|3|精选和套餐不滚动|精选和套餐房源不能滚动，一套房源长时间占据一个位置显示|
|4|Unique_Key问题|抓取房源存新表里，solr之前的唯一id不在唯一|

## 解决方案

|QId|QTitle|初步解决方案|初步成本|初步方案后遗症|后期解决方案|后期成本|
|--- |--- |--- |--- |--- |--- |--- |
|1|rank排序|随机生成对应的大段stage、小段random_score、sub_stage、final_score值|已快速修复rank值问题|暂无后遗症|修改rebuild job，从DW获取rank值，没有对应的rank，用户端采用与BI接近的算法自己计算Rank|需要花费大约2天时间改造solr|
|2|type区分|solr加from_type字段，不对from_type建索引，不批量build之前的数据|暂无后遗症|可以接近问题|建索引，重新build之前所用的房源数据|全量重新build花费时间较多|
|3|精选和套餐不滚动|精选房源，随机从solr获取|快速滚动|筛选排序条件时不是用户希望的展示顺序|精选随机时判断有无排序筛选条件，有则不随机，其他情况随机|花费时间较少，4个小时以内可以完成|
|4|Unique_Key问题|抓取房源自增ID+100000000作为新的房源ID,build单页URL时减去10000w,然后去对应抓取表获取数据|目前眼下是最快的解决问题方案|随着ajk经纪人房源数据量增长，10000w可能较快就用完了|下架所用抓取房源solr,然后再重新新增solr字段unique_id,它由form_type和房源id共同唯一决定，类型字符串|花费时间较长，粗略估计下至少要3天以上,主要是金铺代码没有迁移过，老代码仓库定位问题还是有些困难的，而且使用solr的地方很多，需要一一修改成unique_id，service也要修改|


