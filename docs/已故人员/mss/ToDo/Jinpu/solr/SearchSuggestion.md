

搜索建议solr信息  
=====================
------------------
####一、  solr概述

* 主要用于在搜索框中搜索的时候下拉框出现的搜索建议
* 线下solr地址：`http://solr.anjuke.test/service.php`
* solr名称：jp-search-suggestion
* 主要数据来源：dw_jp_midprice_monthly
------------------
####二、  字段含义

字段|描述|值|
:---------------|:---------------|:---------------
id|楼盘一些信息的加密值||
records|楼盘的某种类型的房源数|表：dw_jp_midprice_monthly。若楼盘为新且开通了此种类型的业务则置为99999
city_id|楼盘的城市id
hits|更新次数
type|楼盘的房源类型|写字楼租1写字楼售2商铺租3商铺售4
keyword|楼盘的名称
updatetime|最近一次更新的时间戳
is_new|是否是新盘|新盘则为1
------------------
####历史更改记录：

* 由于提供汉字对应拼音的服务已经关闭，因此pinyin1、pingyin2、pinyin1_mul、pingyin2_mul四个字段不再更新。