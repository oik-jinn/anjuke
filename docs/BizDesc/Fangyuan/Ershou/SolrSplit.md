### solr分组
* Host
    * pg：http://solr.anjuke.test:8983/
    * online：http://10.10.6.51:8983/

* 精选solr
    * 组：ajk-saleauction
    * 说明：全国所有精选房源都在此solr中

* 新套餐solr
    * 说明：
        * 此solr覆盖业务包括：经纪人新套餐，抓取房源，58导入的房源，数据量大
        * 拆分原则主要以数据量大小为基础，比如上海和北京两个一线城市是单独的一组
        * 其他二线或房源量少的城市放在一组
        * 目前有11组，当扩充城市时会依据房源量可能再增加分组
        
* 基本字段说明

Field  |  Type   |  Indexed  |   Stored   |  Required |  MultiValued  |   Description
---|---|---|---|---|---|---
unique_id          | string | Y | Y | Y  | - |唯一主键, 例如A123
source_type        | string | Y | Y | Y | - |房源类型,A:经纪人,C:抓取,E:58导入房源
id                 |int       |Y     | Y     | Y    | -| 房源ID
usetype            |int       | Y   | -    |  -   |-   |类型ID(老公房／公寓....)
floor_index        |sint      | Y    |    - |    - |-  |楼层ID
city_id            |int       |Y   |-    |-   |-   |城市ID
creation           |sint      | Y   |   -  |  -   |   -  |发布时间(POSTDATE)
updated            |sint     |   Y  |   -  |   -  |  -  |修改时间(MODIFYDATE)
area_code          |string  | Y  |  - |   -  |   -  |区域代码
price              |sint    |Y   |-    |-   |-   |价格
unit_price     |sint    |Y   |-    |-   |-   |单价
area     |sfloat   |Y   |-    |-   |-   |面积
room_num     |sint     |Y   |-    |-   |-   |房间数ID(几室)
hall_num     |int    |Y   |-    |-   |-   |客厅数ID(几厅)
toilet_num     |int    |Y   |-    |-   |-   |卫生间ID(几卫)
fitment_string     |string   |Y   |-    |-   |-   |装修类型(精装修)
floor     |string     |Y   |-    |-   |-   |楼层（第多少层）
floor_total     |string   |Y   |-    |-   |-   |楼层总数
ishq     |int     |Y   |-    |-   |-   |是否优质房源
lastedit     |int     |Y   |-    |-   |-   |上次更新时间
companyid     |int   |Y   |-    |-   |-   |公司ID
sowooid     |int    |Y   |-    |-   |-   |抓取房源ID
broker_id     |int     |Y   |-    |-   |-   |经纪人ID
broker_star     |int    |Y   |-    |-   |-   |经纪人星级
proimg_d     |string     |Y   |-    |-   |-   |默认图片
order_string     |string     |Y   |-    |-   |-   |即ORDERTYPE
title     |text     |Y   |-    |-   |-   |房源标题
address     |text    |Y   |-    |-   |-   |小区地址
slat     |sfloat    |Y   |-    |-   |-   |小区的经度
slng     |sfloat    |Y   |-    |-   |-   |小区的纬度
storeid     |int    |Y   |-    |-   |-   |门店ID
islist     |boolean     |Y   |-    |-   |-   |推荐房源(默认为1)
paied     |boolean     |Y   |-    |-   |-   |付费经纪人
house_age     |sint    |Y   |-    |-   |-   |房源年龄
master\_store     |int   |Y   |-    |-   |-   |主营小区ID
commid     |int    |Y   |-    |-   |-   |小区ID
community_name     |text   |Y   |-    |-   |-   |小区名称
blat     |sfloat    |Y   |-    |-   |-   |小区百度的经度
blng     |sfloat     |Y   |-    |-   |-   |小区百度的纬度
blocation     |location     |Y   |-    |-   |-   |房源的百度坐标
hpratio     |sfloat     |Y   |-    |-   |-   |精选的系数
comms\_hpratio\_a     |sdouble  |Y   |-    |-   |-   |精选房源小区系数
comms\_hpratio     |sdouble   |Y   |-    |-   |-   |备份（目前为0，为了平滑过度）
hpstarttime     |sint     |Y   |-    |-   |-   |精选开始时间
hpendtime     |sint     |Y   |-    |-   |-   |精选结束时间
hpplanid     |sint     |Y   |-    |-   |-   |精选计划id
updatetime     |sint   |Y   |-    |-   |-   |solr更新时间
rank\_level     |sint    |Y   |-    |-   |-   |大段
rank\_sub\_level     |sint   |Y   |-    |-   |-   |小段
rank\_score     |sfloat   |Y   |-    |-   |-   |Rank的分数
rank\_score2     |sfloat  |Y   |-    |-   |-   |滚动的随机数
besttags     |string    |Y   |-    |-   |Y   |房源的标签（besttags）
alltags     |string     |Y   |-    |-   |Y   |房源所有的标签
bus_id     |int     |Y   |-    |-   |Y   |公交id
metro_id     |int     |Y   |-    |-   |Y   |地铁id
metro\_station\_id     |int     |Y   |-    |-   |Y   |地铁站点id
metro\_distance     |int     |Y   |-    |-   |Y   |小区步行到地铁站的距离
metro\_line\_distance     |sint     |Y   |-    |-   |Y   |步行到地铁线的距离
metro\_station\_distance     |sint     |Y   |-    |-   |Y   |步行到地铁站的距离
metro\_distance\_min     |sint     |Y   |-    |-   |Y   |步行到地铁的最短距离
keywords     |string    |Y   |-    |-   |Y   |房源关键词(标题)
keywords2     |string    |Y   |-    |-   |Y   |房源关键字（经纪人姓名，门店等）
tags     |string     |Y   |-    |-   |Y   |给房源打的标记,比如房产季

* 动态字段

Field  |  Type   |  Indexed  |   Stored   |  Required  | MultiValued  |  Description
---|---|---|---|---|---|---
school_info*     |string   |Y     | -     | -    |Y    | 学区房相关信息,school\_info\_ids,school\_info\_tags,school\_info\_names
rnd_*     | random     | Y   | -    |  -   |  Y  |随机数,用来房源随机抽取
* 分组：
    
			城市ID | 城市名称 | 所在组
			---|---|---
			11       |上海       |ershoufang-01
			14       |北京       |ershoufang-02
			12       |广州       |ershoufang-03
			13       |深圳       |ershoufang-03
			15       |成都       |ershoufang-04
			16       |南京       |ershoufang-04
			17       |天津       |ershoufang-05
			18       |杭州       |ershoufang-05
			19       |苏州       |ershoufang-06
			20       |重庆       |ershoufang-06
			21       |大连       |ershoufang-06
			22       |武汉       |ershoufang-06
			23       |济南       |ershoufang-07
			24       |佛山       |ershoufang-07
			25       |无锡       |ershoufang-07
			26       |郑州       |ershoufang-07
			27       |长沙       |ershoufang-07
			28       |石家庄       |ershoufang-08
			29       |香港       |ershoufang-08
			30       |青岛       |ershoufang-08
			31       |西安       |ershoufang-08
			32       |宁波       |ershoufang-08
			33       |合肥       |ershoufang-08
			34       |东莞       |ershoufang-08
			35       |福州       |ershoufang-09
			36       |昆明       |ershoufang-09
			37       |贵阳       |ershoufang-09
			38       |太原       |ershoufang-09
			39       |沈阳       |ershoufang-09
			40       |昆山       |ershoufang-09
			41       |南昌       |ershoufang-09
			42       |珠海       |ershoufang-10
			43       |常州       |ershoufang-10
			44       |中山       |ershoufang-10
			45       |嘉兴       |ershoufang-10
			46       |厦门       |ershoufang-10
			47       |烟台       |ershoufang-10
			48       |哈尔滨       |ershoufang-10
			49       |海口       |ershoufang-10
			50       |长春       |ershoufang-10
			51       |三亚       |ershoufang-10
			52       |惠州       |ershoufang-10
			53       |保定       |ershoufang-10
			54       |桂林       |ershoufang-10
			55       |邯郸       |ershoufang-10
			56       |呼和浩特       |ershoufang-10
			57       |吉林       |ershoufang-10
			58       |兰州       |ershoufang-10
			59       |廊坊       |ershoufang-10
			60       |洛阳       |ershoufang-10
			61       |绵阳       |ershoufang-10
			62       |南宁       |ershoufang-11
			63       |南通       |ershoufang-11
			64       |秦皇岛       |ershoufang-11
			65       |泉州       |ershoufang-11
			66       |绍兴       |ershoufang-11
			67       |泰州       |ershoufang-11
			68       |唐山       |ershoufang-11
			69       |威海       |ershoufang-11
			70       |潍坊       |ershoufang-11
			71       |徐州       |ershoufang-11
			72       |扬州       |ershoufang-11
			73       |宜昌       |ershoufang-11
			74       |银川       |ershoufang-11
			75       |镇江       |ershoufang-11
			76       |淄博       |ershoufang-11
			77       |柳州       |ershoufang-11
			78       |江门       |ershoufang-11
			79       |阳江       |ershoufang-11
			80       |莱芜       |ershoufang-11
			81       |荆门       |ershoufang-11
			82       |黄冈       |ershoufang-11
			83       |永州       |ershoufang-11
			84       |淮南       |ershoufang-11
			85       |黄山       |ershoufang-11
			86       |阜阳       |ershoufang-11
			87       |六安       |ershoufang-11
			88       |玉林       |ershoufang-11
			89       |开封       |ershoufang-11
			90       |鹤壁       |ershoufang-11
			91       |锦州       |ershoufang-11
			92       |景德镇       |ershoufang-11
			93       |赣州       |ershoufang-11
			94       |吉安       |ershoufang-11
			95       |攀枝花       |ershoufang-11
			96       |泸州       |ershoufang-11
			97       |德阳       |ershoufang-11
			98       |南充       |ershoufang-11
			99       |广安       |ershoufang-11
			100       |曲靖       |ershoufang-11
			101       |丽江       |ershoufang-11
			102       |大理       |ershoufang-11
			103       |乌鲁木齐       |ershoufang-11
			104       |张家口       |ershoufang-11
			105       |汕头       |ershoufang-12
			106       |温州       |ershoufang-12
			107       |鞍山       |ershoufang-12
			108       |济宁       |ershoufang-12
			109       |株洲       |ershoufang-12
			110       |衡阳       |ershoufang-12
			111       |德州       |ershoufang-12
			112       |金华       |ershoufang-12
			113       |包头       |ershoufang-12
			114       |沧州       |ershoufang-12
			115       |南阳       |ershoufang-12
			116       |滨州       |ershoufang-12
			117       |日照       |ershoufang-12
			118       |东营       |ershoufang-12
			119       |泰安       |ershoufang-12
			120       |临沂       |ershoufang-12
			121       |安阳       |ershoufang-12
			122       |台州       |ershoufang-12
			123       |漳州       |ershoufang-12
			124       |揭阳       |ershoufang-12
			125       |聊城       |ershoufang-12
			126       |平顶山       |ershoufang-12
			127       |宝鸡       |ershoufang-12
			128       |大庆       |ershoufang-12
			129       |茂名       |ershoufang-12
			130       |连云港       |ershoufang-13
			131       |湖州       |ershoufang-13
			132       |湘潭       |ershoufang-13
			133       |湛江       |ershoufang-13
			134       |肇庆       |ershoufang-13
			135       |襄阳       |ershoufang-13
			136       |枣庄       |ershoufang-13
			137       |盐城       |ershoufang-13
			138       |十堰       |ershoufang-13
			139       |岳阳       |ershoufang-13
			140       |衡水       |ershoufang-13
			141       |新乡       |ershoufang-13
			142       |丽水       |ershoufang-13
			143       |宁德       |ershoufang-13
			144       |舟山       |ershoufang-13
			145       |昌吉       |ershoufang-13
			146       |信阳       |ershoufang-13
			147       |晋中       |ershoufang-13
			148       |三门峡       |ershoufang-13
			149       |娄底       |ershoufang-13
			150       |咸阳       |ershoufang-13
			151       |遵义       |ershoufang-13
			152       |芜湖       |ershoufang-13
			153       |邢台       |ershoufang-13
			154       |九江       |ershoufang-13
			155       |孝感       |ershoufang-14
			156       |西宁       |ershoufang-14
			157       |三明       |ershoufang-14
			158       |韶关       |ershoufang-14
			159       |焦作       |ershoufang-14
			160       |赤峰       |ershoufang-14
			161       |常德       |ershoufang-14
			162       |乐山       |ershoufang-14
			163       |汉中       |ershoufang-14
			164       |鄂尔多斯       |ershoufang-14
			165       |许昌       |ershoufang-14
			166       |商丘       |ershoufang-14
			167       |晋城       |ershoufang-14
			168       |安庆       |ershoufang-14
			169       |淮安       |ershoufang-14
			170       |辽阳       |ershoufang-14
			171       |梧州       |ershoufang-14
			172       |驻马店       |ershoufang-14
			173       |马鞍山       |ershoufang-14
			174       |黄石       |ershoufang-14
			175       |盘锦       |ershoufang-14
			176       |萍乡       |ershoufang-14
			177       |丹东       |ershoufang-14
			178       |拉萨       |ershoufang-14
			179       |忻州       |ershoufang-14
			180       |蚌埠       |ershoufang-15
			181       |荆州       |ershoufang-15
			182       |牡丹江       |ershoufang-15
			183       |菏泽       |ershoufang-15
			184       |运城       |ershoufang-15
			185       |临汾       |ershoufang-15
			186       |郴州       |ershoufang-15
			187       |宜春       |ershoufang-15
			188       |抚顺       |ershoufang-15
			189       |怀化       |ershoufang-15
			190       |本溪       |ershoufang-15
			191       |齐齐哈尔       |ershoufang-15
			192       |营口       |ershoufang-15
			193       |内江       |ershoufang-15
			194       |榆林       |ershoufang-15
			195       |宿迁       |ershoufang-15
			196       |绥化       |ershoufang-15
			197       |铁岭       |ershoufang-15
			198       |自贡       |ershoufang-15
			199       |龙岩       |ershoufang-15
			200       |承德       |ershoufang-15
			201       |贵港       |ershoufang-15
			202       |佳木斯       |ershoufang-15
			203       |衢州       |ershoufang-15
			204       |长治       |ershoufang-15
			205       |大同       |ershoufang-16
			206       |邵阳       |ershoufang-16
			207       |宜宾       |ershoufang-16
			208       |漯河       |ershoufang-16
			209       |濮阳       |ershoufang-16
			210       |清远       |ershoufang-16
			211       |莆田       |ershoufang-16
			212       |淮北       |ershoufang-16
			213       |达州       |ershoufang-16
			214       |通辽       |ershoufang-16
			215       |遂宁       |ershoufang-16
			216       |葫芦岛       |ershoufang-16
			217       |阜新       |ershoufang-16
			218       |上饶       |ershoufang-16
			219       |抚州       |ershoufang-16
			220       |四平       |ershoufang-16
			221       |益阳       |ershoufang-16
			222       |池州       |ershoufang-16
			223       |滁州       |ershoufang-16
			224       |铜陵       |ershoufang-16
			225       |南平       |ershoufang-16
			226       |河源       |ershoufang-16
			227       |北海       |ershoufang-16
			228       |义乌       |ershoufang-16
            229       |朝阳    |ershoufang-13
            230       |安康    |ershoufang-13
            231       |玉溪    |ershoufang-13
            232       |钦州    |ershoufang-13
            233       |石河子    |ershoufang-13
            234       |资阳    |ershoufang-13
            235       |伊犁    |ershoufang-13
            236       |凉山    |ershoufang-13
            237       |巴音郭楞    |ershoufang-13
            238       |眉山    |ershoufang-13
            239       |庆阳    |ershoufang-13
            240       |潮州    |ershoufang-13
            241       |宣城    |ershoufang-13
            242       |巴中    |ershoufang-13
            243       |红河    |ershoufang-13
            244       |顺德    |ershoufang-14
            245       |周口    |ershoufang-14
            246       |宿州    |ershoufang-14
            247       |章丘    |ershoufang-14
            248       |松原    |ershoufang-14
            249       |阳泉    |ershoufang-14
            250       |喀什    |ershoufang-14
            251       |天水    |ershoufang-14
            252       |恩施    |ershoufang-14
            253       |沭阳    |ershoufang-14
            254       |延安    |ershoufang-14
            255       |广元    |ershoufang-14
            256       |六盘水    |ershoufang-14
            257       |梅州    |ershoufang-14
            258       |安顺    |ershoufang-14
            259       |酒泉    |ershoufang-15
            260       |巴彦淖尔市    |ershoufang-15
            261       |渭南    |ershoufang-15
            262       |石嘴山    |ershoufang-15
            263       |张家界    |ershoufang-15
            264       |随州    |ershoufang-15
            265       |鄂州    |ershoufang-15
            266       |亳州    |ershoufang-15
            267       |哈密    |ershoufang-15
            268       |咸宁    |ershoufang-15
            269       |乌海    |ershoufang-15
            270       |鹰潭    |ershoufang-15
            271       |文山    |ershoufang-15
            272       |楚雄    |ershoufang-15
            273       |巢湖    |ershoufang-15
            274       |克拉玛依    |ershoufang-16
            275       |兴安盟    |ershoufang-16
            276       |武威    |ershoufang-16
            277       |延边    |ershoufang-16
            278       |鸡西    |ershoufang-16
            279       |济源    |ershoufang-16
            280       |通化    |ershoufang-16
            281       |新余    |ershoufang-16
            282       |朔州    |ershoufang-16
            283       |乌兰察布    |ershoufang-16
            284       |白银    |ershoufang-16
            285       |汕尾    |ershoufang-16
            286       |白城    |ershoufang-16
            287       |鹤岗    |ershoufang-16
            288       |辽源    |ershoufang-16
            289       |诸城    |ershoufang-16
            290       |普洱    |ershoufang-16
            291       |呼伦贝尔    |ershoufang-16
            292       |云浮    |ershoufang-16


    
* solr在线房源查询条件
    * 精选或精选：
    
      ```
      http://10.10.6.51:8983/ajk-saleauction/select?wt=json&&q=city_id:11&fq=hpstarttime:[* TO 当前时间戳]&fq=hpendtime:[当前时间戳 TO *]
      ```


    * 示例:查询上海经纪人id为1076178的在线精选房源

      ```
      http://10.10.6.51:8983/ajk-saleauction/select?wt=json&q=city_id:城市id&fq=broker_id:1076178&fq=hpstarttime:[* TO 1410431187]&fq=hpendtime:[1410431187 TO *]
      ```
      
    * 定价或端口：
    
      ```
      http://10.10.6.51:8983/ershoufang-01/select?wt=json&q=city_id:城市id&fq=islist:1&fq=hpstarttime:[0 TO 当前时间戳]
      ```


    * 示例:查询上海经纪人id为1076178的在线定价或端口房源

      ```
      
      http://10.10.6.51:8983/ershoufang-01/select?wt=json&q=city_id:11&fq=islist:1&fq=broker_id:1076178&fq=hpstarttime:[0 TO 1410431621]
      
      ```