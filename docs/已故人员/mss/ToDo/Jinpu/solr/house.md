房源的solr信息  
=====================
------------------
####一、  solr概述

* 房源（写字楼租售、商铺租售）的相关信息

* 线下solr地址：`http://solr.anjuke.test/service.php`

* solr名称：

```
	写字楼售：jp-xiezilou-shou-v36

	写字楼租：jp-xiezilou-zu

	商铺售：jp-shangpu-shou

	商铺租：jp-shangpu-zu
```

* 主要数据来源：

|表描述|表名|备注|
| --------   | -----  | ------|
|房源更新的队列表|q_spread_house_update|用于更新房源的solr的队列表|
|房源索引表|i_house|所有房源均在此表|
|房源详情表|e_office_rent||
||e_office_sale||
||e_shop_rent||
||e_shop_sale||
|房源图片表|e_office_sale_img_v2||
||e_office_rent_img_v2||
||e_shop_rent_img_v2||
||e_shop_sale_img_v2||
|板块表|d_block||
|区域板块对应表|d_district_block||
|区域表|d_district||
|商圈板块对应表|d_new_business_block_map||
|商圈表|d_new_business||
|新区域表|d_new_district||
|价格段表|d_filter_daily_rent|写字楼和商铺（shop）各有一套数据|
||d_filter_monthly_rent||
||d_filter_unit_price||
||d_filter_total_price||
|面积段表|d_filter_area_rent|写字楼和商铺（shop）各有一套数据|
||d_filter_area_sale|
|房源rank信息|e_house_rank||
|dw表的房源rank信息|hu_rank_jp_all_score_rank_list|jp_dw_stats库中|
|经纪人相关信息|e_member_ext||
|物业经纬度|e_map_property||
|物业的地铁公交信息表|e_building_traffic|
|房源的点击数|jp_house_click_num|jinpu_report数据库中|
|计划表|e_spread|
|在线竞价信息表|e_spread_bid_online|



------------------
####二、  字段含义

字段|描述|值|
:---------------|:---------------|---------------
id|房源id|
building_id|房源所在楼盘id|
building_name|房源所在楼盘名称|
building_level_id|房源所在楼盘等级id|
member_id|经纪人id|
city_id|城市id|
district_id|房源所在物业的区域id|
district_name|区域名称|`d_district`.`name`|
block_id|房源所在物业的版块id|
block_name|版块名称|`d_block`.`name`|
title|房源标题|
floor_id||此值暂未更新|
area|房源面积|
monthly_rent|月租金|房源详情表.`monthly_rent`
daily_rent|日租金|房源详情表.`daily_rent`
img_num|该房源下的总的图片数|房源图片表(房源对应可展示的图片数)|
is_quality||房源详情表|
is_exquisite|精品房源|房源详情表|
create_time|房源创建时间|房源详情表.`list_time`|
publish_date|发布时间|房源详情表.`create_time`|
publish_time|发布时间|房源详情表.`create_time`|
final_score|分数|房源rank信息表.`final_score`|
random_score|分数|房源rank信息表.`random_score`|
stage|分段|房源rank信息表.`stage`|
sub_stage|分组|房源rank信息表.`sub_stage`|
monthly_rent_id|月租金所在价格段id|
area_id|面积所在段id|
blat|房源所在物业的纬度|`e_map_property`.`lat`|
blng|房源所在物业的经度|`e_map_property`.`lng`|
match_building||物业的名称|
company_id||`e_member_ext`.`company_id`|
store_id||`e_member_ext`.`store_id`|
new_district_id|房源新区域id|物业所属板块对应的新区域商圈|
new_district_name|区域名称|
new_business_id|房源新商圈id|物业所属板块对应的新区域商圈|
new_business_name|商圈名称|
is_developer|废弃|
house_metro|是否是地铁房|所在楼盘500米之内是否有地铁|
house_grade_a|是否是甲级楼房|楼盘building_level_id为1|
house_high_level|是否是高区|房源详情表.`floor_id`=3|
house_new_complete|是否是新竣工楼房|
house_heat|是否是人气楼房|昨天点击数（jp_house_click_num）是否在前500|
rent_sort||房源详情表.`rent_sort`|
address|房源地址|
list_time|开始展示的时间|参考`e_spread`.`no_budget_date`|
bid_rank|推广出价|房源详情表.`spread_offer`|
bid_time|推广出价时间|房源详情表.`spread_offce_time`|
spread_type|计划类型|定价，1，竞价，2|
bid_ratio|竞价系数|`e_spread_bid_online`.`spread_ratio`|
roll_ratio|综合系数|`e_spread_bid_online`.`show_ratio`|

------------------
####三、  实际应用

##### 1. 竞价定价赋值说明
* spread_type：1，定价，2，竞价
* 当房源即为定价又为竞价的时候，spread_type=1；当房源仅为竞价的时候，spread_type=2
* list_time,意思是房源开始展示的时间，在线定价房源搜索的时候，判断次字段的值是否在当前之前
* 当房源仅为定价的时候，竞价相关字段全部置为0（bid_rank、bid_time、bid_ratio、roll_ratio）

##### 2. 房源列表页搜索说明
* 列表页搜索定价房源，solr链接：

```
http://10.10.6.51:8983/jp-xiezilou-zu/select/?version=2.2&indent=on&wt=xml&q=_val_%3A%22map%28sub_stage%2C0%2C1%2C99%29%22&fl=id&fq=city_id%3A11&fq=new_district_id%3A7&fq=new_business_id%3A423&fq=area%3A[*%20TO%2099]&fq=list_time%3A[*%20TO%201400841126]&fq=-spread_type%3A2&sort=stage%20asc%2Cscore%20asc%2Csub_stage%20asc%2Cfinal_score%20desc&start=0&rows=25
```

* 列表页搜索竞价房源，根据竞价系数倒序

```
http://10.10.6.51:8983/jp-xiezilou-zu/select/?version=2.2&indent=on&wt=xml&q=_val_%3A%22product%28roll_ratio%2Crnd_1400841126%29%22&fl=id&fq=new_district_id%3A7&fq=new_business_id%3A423&fq=area%3A[*%20TO%2099]&fq=city_id%3A11&fq=bid_rank%3A[1%20TO%20*]&sort=score%20desc%2Cbid_ratio%20asc%2Cbid_time%20asc&rows=5
```
------------------
####历史更改记录：

#### 碎碎念：
------
* 1. 商业地产的房源列表页主要有定价房源和竞价房源，竞价房源当有区域板块和价格的筛选的时候展示。竞价房源最多展示5个。
* 2. 列表页的所有房源是分段，然后段再分组，段内小组滚动，小组内定价房源按照 房源分 排序，竞价房源按照 竞价系数排序，详情可以通过debug 然后看solr的查询参数。
 ```
* 例如下：
* 默认列表只显示定价：
* [http://10.10.6.51:8983/jp-xiezilou-zu/select/?version=2.2&indent=on&wt=xml&q=_val_%3A%22map%28sub_stage%2C0%2C20%2C99%29%22&fl=id,stage,sub_stage,final_score&fq=city_id%3A14&fq=list_time%3A[*%20TO%201405675925]&fq=-spread_type%3A2&sort=stage%20asc%2Cscore%20asc%2Csub_stage%20asc%2Cfinal_score%20desc&start=0&rows=25]


```
<response>
<lst name="responseHeader">
<int name="status">0</int><int name="QTime">5</int>
<lst name="params"><str name="sort">stage asc,score asc,sub_stage asc,final_score desc</str>
<str name="fl">id,stage,sub_stage,final_score</str><str name="indent">on</str><str name="start">0</str><str name="q">_val_:"map(sub_stage,0,20,99)"</str><str name="distrib">false</str><str name="wt">xml</str>
<arr name="fq"><str>city_id:14</str><str>list_time:[* TO 1405675925]</str><str>-spread_type:2</str></arr><str name="rows">25</str><str name="version">2.2</str></lst></lst>
<result name="response" numFound="30524" start="0">
<doc><int name="id">9292490</int><int name="final_score">0</int><int name="stage">0</int><int name="sub_stage">0</int></doc>
<doc><int name="id">9294452</int><int name="final_score">0</int><int name="stage">0</int><int name="sub_stage">0</int></doc>
<doc><int name="id">9327219</int><int name="final_score">0</int><int name="stage">0</int><int name="sub_stage">0</int></doc>
<doc><int name="id">9327537</int><int name="final_score">0</int><int name="stage">0</int><int name="sub_stage">0</int></doc>
<doc><int name="id">9279226</int><int name="final_score">0</int><int name="stage">0</int><int name="sub_stage">0</int></doc>
<doc><int name="id">9295664</int><int name="final_score">0</int><int name="stage">0</int><int name="sub_stage">0</int></doc>
</result>
</response>
```
 
如图，段跟组理论上都为正整数所以此处solr中数据有问题，然后根据git的文档中的数据来源，查看实际数据表中的数据，一般数据表中的数据是由客户端在维护，dw中的数据是由BI部门维护。

* 筛选列表有竞价房源：
* http://10.10.6.51:8983/jp-xiezilou-zu/select/?version=2.2&indent=on&wt=xml&q=_val_%3A%22product%28roll_ratio%2Crnd_1405676699%29%22&fl=id,bid_ratio,bid_rank&fq=new_district_id%3A38&fq=city_id%3A14&fq=bid_rank%3A[1%20TO%20*]&sort=score%20desc%2Cbid_ratio%20asc%2Cbid_time%20asc&rows=5

```
<response>
<lst name="responseHeader">
<int name="status">0</int><int name="QTime">1</int><lst name="params"><str name="sort">score desc,bid_ratio asc,bid_time asc</str><str name="fl">id,bid_ratio,bid_rank</str><str name="indent">on</str><str name="q">_val_:"product(roll_ratio,rnd_1405676699)"</str><str name="distrib">false</str><str name="wt">xml</str><arr name="fq"><str>new_district_id:38</str><str>city_id:14</str><str>bid_rank:[1 TO *]</str></arr><str name="rows">5</str><str name="version">2.2</str></lst>
</lst>
<result name="response" numFound="249" start="0">
<doc>
<int name="id">9278075</int>
<int name="bid_rank">4180</int>
<double name="bid_ratio">38.45489</double>
</doc>
</result>
</response>
```
 	如上，竞价房源是从客户端维护的该筛选条件下所有的精选房源，随机取5个然后按照竞价系数排序。


* 待完善。
