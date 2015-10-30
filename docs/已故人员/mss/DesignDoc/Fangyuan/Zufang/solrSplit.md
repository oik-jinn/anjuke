# 租房SOLR拆分

## HOST

* PG: http://solr.anjuke.test:8983/
* ONLINE: http://10.10.6.51:8983/

## Group

* PG: zufang (统一使用这一个组)
* ONLINE
    * 说明:
        * 拆分原因：由于58房源的大量导入 导致 现有SOLR压力过大  渐有不支迹象
        * 目前SOLR覆盖业务包括：个人，经纪人，大业主，个人抓取，经纪人抓取，58导入房源  数据量大 
        * 拆分原则主要以数据量大小为基础，比如上海和北京两个一线城市是单独的一组
        * 其他二线或房源量少的城市放在一组
        * 目前有16组，当扩充城市时会依据房源量可能再增加分组

    * 分组

        城市ID | 城市名称 | 所在组
                ---|---|---
                    11       |上海       |zufang-01
                    14       |北京       |zufang-02
                    12       |广州       |zufang-03
                    13       |深圳       |zufang-03
                    15       |成都       |zufang-04
                    16       |南京       |zufang-04
                    17       |天津       |zufang-05
                    18       |杭州       |zufang-05
                    19       |苏州       |zufang-06
                    20       |重庆       |zufang-06
                    21       |大连       |zufang-06
                    22       |武汉       |zufang-06
                    23       |济南       |zufang-07
                    24       |佛山       |zufang-07
                    25       |无锡       |zufang-07
                    26       |郑州       |zufang-07
                    27       |长沙       |zufang-07
                    28       |石家庄       |zufang-08
                    29       |香港       |zufang-08
                    30       |青岛       |zufang-08
                    31       |西安       |zufang-08
                    32       |宁波       |zufang-08
                    33       |合肥       |zufang-08
                    34       |东莞       |zufang-08
                    35       |福州       |zufang-09
                    36       |昆明       |zufang-09
                    37       |贵阳       |zufang-09
                    38       |太原       |zufang-09
                    39       |沈阳       |zufang-09
                    40       |昆山       |zufang-09
                    41       |南昌       |zufang-09
                    42       |珠海       |zufang-10
                    43       |常州       |zufang-10
                    44       |中山       |zufang-10
                    45       |嘉兴       |zufang-10
                    46       |厦门       |zufang-10
                    47       |烟台       |zufang-10
                    48       |哈尔滨       |zufang-10
                    49       |海口       |zufang-10
                    50       |长春       |zufang-10
                    51       |三亚       |zufang-10
                    52       |惠州       |zufang-10
                    53       |保定       |zufang-10
                    54       |桂林       |zufang-10
                    55       |邯郸       |zufang-10
                    56       |呼和浩特       |zufang-10
                    57       |吉林       |zufang-10
                    58       |兰州       |zufang-10
                    59       |廊坊       |zufang-10
                    60       |洛阳       |zufang-10
                    61       |绵阳       |zufang-10
                    62       |南宁       |zufang-11
                    63       |南通       |zufang-11
                    64       |秦皇岛       |zufang-11
                    65       |泉州       |zufang-11
                    66       |绍兴       |zufang-11
                    67       |泰州       |zufang-11
                    68       |唐山       |zufang-11
                    69       |威海       |zufang-11
                    70       |潍坊       |zufang-11
                    71       |徐州       |zufang-11
                    72       |扬州       |zufang-11
                    73       |宜昌       |zufang-11
                    74       |银川       |zufang-11
                    75       |镇江       |zufang-11
                    76       |淄博       |zufang-11
                    77       |柳州       |zufang-11
                    78       |江门       |zufang-11
                    79       |阳江       |zufang-11
                    80       |莱芜       |zufang-11
                    81       |荆门       |zufang-11
                    82       |黄冈       |zufang-11
                    83       |永州       |zufang-11
                    84       |淮南       |zufang-11
                    85       |黄山       |zufang-11
                    86       |阜阳       |zufang-11
                    87       |六安       |zufang-11
                    88       |玉林       |zufang-11
                    89       |开封       |zufang-11
                    90       |鹤壁       |zufang-11
                    91       |锦州       |zufang-11
                    92       |景德镇       |zufang-11
                    93       |赣州       |zufang-11
                    94       |吉安       |zufang-11
                    95       |攀枝花       |zufang-11
                    96       |泸州       |zufang-11
                    97       |德阳       |zufang-11
                    98       |南充       |zufang-11
                    99       |广安       |zufang-11
                    100       |曲靖       |zufang-11
                    101       |丽江       |zufang-11
                    102       |大理       |zufang-11
                    103       |乌鲁木齐       |zufang-11
                    104       |张家口       |zufang-11
                    105       |汕头       |zufang-12
                    106       |温州       |zufang-12
                    107       |鞍山       |zufang-12
                    108       |济宁       |zufang-12
                    109       |株洲       |zufang-12
                    110       |衡阳       |zufang-12
                    111       |德州       |zufang-12
                    112       |金华       |zufang-12
                    113       |包头       |zufang-12
                    114       |沧州       |zufang-12
                    115       |南阳       |zufang-12
                    116       |滨州       |zufang-12
                    117       |日照       |zufang-12
                    118       |东营       |zufang-12
                    119       |泰安       |zufang-12
                    120       |临沂       |zufang-12
                    121       |安阳       |zufang-12
                    122       |台州       |zufang-12
                    123       |漳州       |zufang-12
                    124       |揭阳       |zufang-12
                    125       |聊城       |zufang-12
                    126       |平顶山       |zufang-12
                    127       |宝鸡       |zufang-12
                    128       |大庆       |zufang-12
                    129       |茂名       |zufang-12
                    130       |连云港       |zufang-13
                    131       |湖州       |zufang-13
                    132       |湘潭       |zufang-13
                    133       |湛江       |zufang-13
                    134       |肇庆       |zufang-13
                    135       |襄阳       |zufang-13
                    136       |枣庄       |zufang-13
                    137       |盐城       |zufang-13
                    138       |十堰       |zufang-13
                    139       |岳阳       |zufang-13
                    140       |衡水       |zufang-13
                    141       |新乡       |zufang-13
                    142       |丽水       |zufang-13
                    143       |宁德       |zufang-13
                    144       |舟山       |zufang-13
                    145       |昌吉       |zufang-13
                    146       |信阳       |zufang-13
                    147       |晋中       |zufang-13
                    148       |三门峡       |zufang-13
                    149       |娄底       |zufang-13
                    150       |咸阳       |zufang-13
                    151       |遵义       |zufang-13
                    152       |芜湖       |zufang-13
                    153       |邢台       |zufang-13
                    154       |九江       |zufang-13
                    155       |孝感       |zufang-14
                    156       |西宁       |zufang-14
                    157       |三明       |zufang-14
                    158       |韶关       |zufang-14
                    159       |焦作       |zufang-14
                    160       |赤峰       |zufang-14
                    161       |常德       |zufang-14
                    162       |乐山       |zufang-14
                    163       |汉中       |zufang-14
                    164       |鄂尔多斯       |zufang-14
                    165       |许昌       |zufang-14
                    166       |商丘       |zufang-14
                    167       |晋城       |zufang-14
                    168       |安庆       |zufang-14
                    169       |淮安       |zufang-14
                    170       |辽阳       |zufang-14
                    171       |梧州       |zufang-14
                    172       |驻马店       |zufang-14
                    173       |马鞍山       |zufang-14
                    174       |黄石       |zufang-14
                    175       |盘锦       |zufang-14
                    176       |萍乡       |zufang-14
                    177       |丹东       |zufang-14
                    178       |拉萨       |zufang-14
                    179       |忻州       |zufang-14
                    180       |蚌埠       |zufang-15
                    181       |荆州       |zufang-15
                    182       |牡丹江       |zufang-15
                    183       |菏泽       |zufang-15
                    184       |运城       |zufang-15
                    185       |临汾       |zufang-15
                    186       |郴州       |zufang-15
                    187       |宜春       |zufang-15
                    188       |抚顺       |zufang-15
                    189       |怀化       |zufang-15
                    190       |本溪       |zufang-15
                    191       |齐齐哈尔       |zufang-15
                    192       |营口       |zufang-15
                    193       |内江       |zufang-15
                    194       |榆林       |zufang-15
                    195       |宿迁       |zufang-15
                    196       |绥化       |zufang-15
                    197       |铁岭       |zufang-15
                    198       |自贡       |zufang-15
                    199       |龙岩       |zufang-15
                    200       |承德       |zufang-15
                    201       |贵港       |zufang-15
                    202       |佳木斯       |zufang-15
                    203       |衢州       |zufang-15
                    204       |长治       |zufang-15
                    205       |大同       |zufang-16
                    206       |邵阳       |zufang-16
                    207       |宜宾       |zufang-16
                    208       |漯河       |zufang-16
                    209       |濮阳       |zufang-16
                    210       |清远       |zufang-16
                    211       |莆田       |zufang-16
                    212       |淮北       |zufang-16
                    213       |达州       |zufang-16
                    214       |通辽       |zufang-16
                    215       |遂宁       |zufang-16
                    216       |葫芦岛       |zufang-16
                    217       |阜新       |zufang-16
                    218       |上饶       |zufang-16
                    219       |抚州       |zufang-16
                    220       |四平       |zufang-16
                    221       |益阳       |zufang-16
                    222       |池州       |zufang-16
                    223       |滁州       |zufang-16
                    224       |铜陵       |zufang-16
                    225       |南平       |zufang-16
                    226       |河源       |zufang-16
                    227       |北海       |zufang-16
                    228       |义乌       |zufang-16

## 租房操作SOLR相关JOB

* 58租房房源上下架JOB
    * repository:user-site
    * JOB:Zufang_Job_Rent_Spread_WubaRentRunJob
    * 中间件表:zf_wuba_event_queue_00_201503
    * 中间件发消息:curl 'http://nydus.dev.anjuke.com/publish?tunnel=zf_wuba_prop_updown' -d '{"pro_id":511, "city_id":11, "flag":2,"update_time":1426751054,"from_type":999}'
    * JOB执行命令:php bin/launcher.php --class=Zufang_Job_Rent_Spread_WubaRentRunJob --tab_split=00
    * JOB 管理地址:http://drone.corp.anjuke.com/daemon/job/209/view   job_id:[209-218]

* 租房纪纪人(端口)上下架JOB
    * solr url key:v2_haozu_rank_update_urls
    * repository:haozu-site
    * 命令:sh /home/www/bin/zufang/rent_prop_run.sh spread 00,100
    * 中间件:curl 'http://nydus.dev.anjuke.com/publish?tunnel=zf_prop_package_updown' -X POST -d '{"city_id":11,"broker_id":1168365,"pro_type":2,"pro_id":888894808,"business_type":3,"flag":1,"from_type":123}'
    * 中间件JOB user-site:php bin/launcher.php --class=Zufang_Job_Rent_Spread_PackageQueueAccept
    * 中间件表:queue_db.zf_spread_queue_{分表}_{年月}
    * 入口文件:app-haozu-jobs/bin/rent/prop_run.php
    * JOB:app-haozu-jobs/classes/rent/PropSpreadSolr.php
    * JOB命令:php bin/launcher.php rent/prop_run.php spread 05 [00~09]
    * 文档:http://gitlab.corp.anjuke.com/_site/docs/blob/master/BizDesc/Fangyuan/Zufang/SpreadMiddleware.md
    * JOB 管理地址:http://drone.corp.anjuke.com/daemon/job/79/view   job_id:[79-88]

* 租房-个人房源上下架JOB
    * repository:haozu-site
    * 注意所有log文件新建 && 游标同步
    * 命令:sh /home/www/bin/zufang/hz_landlord_prop_to_solr.sh 01[solr group:01-16] 0[是否rebuild]
    * 队列表:rent_db.prop_lucene_updated
    * PHP执行命令:php /home/www/config/v2/jobs/zu/launcher.php solr/hz_rank_landlord.php 01 0
    * 入口文件:app-haozu-jobs/bin/solr/hz_rank_landlord.php
    * JOB:app-haozu-jobs/classes/rank/HzLandlord.php
    * JOB 管理地址:http://drone.corp.anjuke.com/daemon/job/23/view  job_id:[23-28]  
    * drone搜索:http://drone.corp.anjuke.com/daemon/job?keyword=hz_landlord_prop_to_solr

* 租房-经纪人精选房源上下架
    * repository:haozu-site
    * 注意所有log文件新建 && 游标同步
    * 队列表:rent_db.prop_lucene_updated    type = 2
    * 命令:sh /home/www/bin/zufang/hz_prop_to_solr.sh 01[solr group:01-16] 0[0为实时，1为数据部的房源rank更新, 2为重新rebuild当前所有房源]
    * php执行命令:php bin/launcher.php solr/hz_rank_broker_haozu.php  01 0
    * 入口文件:app-haozu-jobs/bin/solr/hz_rank_broker_haozu.php
    * JOB:app-haozu-jobs/classes/rank/HzBroker.php
    * drone搜索:http://drone.corp.anjuke.com/daemon/job?keyword=hz_prop_to_solr

* 租房经纪人房源更新JOB
    * repository:user-site
    * JOB:Zufang_Job_Rent_Spread_PropertyEventToSolr 
    * 中间件表:queue_db.zf_event_queue_09_201503
    * 命令:php bin/launcher.php --load_path=pmt-26690-site --job_path=app-zufang-job --class=Zufang_Job_Rent_Spread_PropertyEventToSolr --tab_split=00
    * path:app-zufang-job/classes/zufang/job/rent/spread/PropertyEventToSolr.php
    * drone搜索:http://drone.corp.anjuke.com/daemon/job?keyword=Zufang_Job_Rent_Spread_PropertyEventToSolr

* 租房-经纪人精选Rebuild Job
    * 线上SHELL修改
    * repository:haozu-site
    * 启动:sh /home/www/config/v2/jobs/zu/bin/hz_rank_broker_haozu/rebuild.sh 01[solr group] 2[2为重新rebuild当前所有房源]
    * php bin/launcher.php solr/hz_rank_broker_haozu.php 01[solr group] 2 [2为重新rebuild当前所有房源]
    * class:app-haozu-jobs/classes/rank/HzBroker.php
    * drone搜索:http://drone.corp.anjuke.com/scheduler/job?keyword=hz_rank_broker_haozu

* 租房-个人Rebuild Job
    * 线上SHELL修改
    * repository:haozu-site
    * 启动:sh /home/www/config/v2/jobs/zu/bin/hz_rank_landlord/rebuild.sh 01[solr group] 2[2为重新rebuild当前所有房源]
    * php bin/launcher.php solr/hz_rank_landlord.php 01[solr group] 2[2为重新rebuild当前所有房源]
    * anjuke-zu/haozu/app-haozu-jobs/bin/solr/rebuild_hz_landlord_prop.php
    * drone搜索:http://drone.corp.anjuke.com/scheduler/job?keyword=hz_rank_landlord

## 修改线上配置

* 线上SHELL修改
    * 线上经纪人rebuild JOB 启动shell:/home/www/config/v2/jobs/zu/bin/hz_rank_broker_haozu/rebuild.sh
    * 线上个人rebuild JOB 启动shell:/home/www/config/v2/jobs/zu/bin/hz_rank_landlord/rebuild.sh

* user-site 修改文件:wuba_multicity.php  增加允许导入的新城市

```php
$config['rent_from58_open'] = array(
    User_Common_Const_MultiCity::CITY_ID_HAERBIN,
    User_Common_Const_MultiCity::CITY_ID_NANNING,
    User_Common_Const_MultiCity::CITY_ID_GUIYANG,
    User_Common_Const_MultiCity::CITY_ID_WEIFANG,
    User_Common_Const_MultiCity::CITY_ID_WEIHAI,
    User_Common_Const_MultiCity::CITY_ID_XUZHOU,
    User_Common_Const_MultiCity::CITY_ID_ZHUHAI,
    User_Common_Const_MultiCity::CITY_ID_SANYA,
    User_Common_Const_MultiCity::CITY_ID_HAIKOU,
    User_Common_Const_MultiCity::CITY_ID_ZHONGSHAN,
    User_Common_Const_MultiCity::CITY_ID_QUANZHOU,
    User_Common_Const_MultiCity::CITY_ID_LANZHOU,
    User_Common_Const_MultiCity::CITY_ID_JIANGMEN,
    User_Common_Const_MultiCity::CITY_ID_NINGBO,
    User_Common_Const_MultiCity::CITY_ID_HUIZHOU,
    User_Common_Const_MultiCity::CITY_ID_LIUZHOU,
    User_Common_Const_MultiCity::CITY_ID_TANGSHAN,
    User_Common_Const_MultiCity::CITY_ID_CHANGZHOU,
    User_Common_Const_MultiCity::CITY_ID_LUOYANG,
    User_Common_Const_MultiCity::CITY_ID_ZIBO,
    User_Common_Const_MultiCity::CITY_ID_HUHEHAOTE,
    User_Common_Const_MultiCity::CITY_ID_FUZHOU,
    User_Common_Const_MultiCity::CITY_ID_XIAN,
    User_Common_Const_MultiCity::CITY_ID_SHANGHAI,
    User_Common_Const_MultiCity::CITY_ID_BEIJING,
    User_Common_Const_MultiCity::CITY_ID_HANGZHOU,
    User_Common_Const_MultiCity::CITY_ID_SHENZHEN,
    User_Common_Const_MultiCity::CITY_ID_CHENGDU,
    User_Common_Const_MultiCity::CITY_ID_CHONGQING,
    User_Common_Const_MultiCity::CITY_ID_GUANGZHOU,
    User_Common_Const_MultiCity::CITY_ID_ZHENGZHOU,
    User_Common_Const_MultiCity::CITY_ID_XIAMEN,
    User_Common_Const_MultiCity::CITY_ID_NANJING,
    User_Common_Const_MultiCity::CITY_ID_KUNSHAN,
    User_Common_Const_MultiCity::CITY_ID_DALIAN,
    User_Common_Const_MultiCity::CITY_ID_TIANJIN,
    User_Common_Const_MultiCity::CITY_ID_CHANGSHA,
    User_Common_Const_MultiCity::CITY_ID_WUHAN,
    User_Common_Const_MultiCity::CITY_ID_SHIJIAZHUANG,
    User_Common_Const_MultiCity::CITY_ID_JINAN,
    User_Common_Const_MultiCity::CITY_ID_HEFEI,
    //新开城市
    User_Common_Const_MultiCity::CITY_ID_YANGJIANG ,
    User_Common_Const_MultiCity::CITY_ID_LAIWU ,
    User_Common_Const_MultiCity::CITY_ID_JINGMEN ,
    User_Common_Const_MultiCity::CITY_ID_HUANGGANG ,
    User_Common_Const_MultiCity::CITY_ID_YONGZHOU ,
    User_Common_Const_MultiCity::CITY_ID_HUAINAN ,
    User_Common_Const_MultiCity::CITY_ID_HUANGSHAN ,
    User_Common_Const_MultiCity::CITY_ID_FUYANG ,
    User_Common_Const_MultiCity::CITY_ID_LUAN ,
    User_Common_Const_MultiCity::CITY_ID_YULINSHI ,
    User_Common_Const_MultiCity::CITY_ID_KAIFENG ,
    User_Common_Const_MultiCity::CITY_ID_HEBI ,
    User_Common_Const_MultiCity::CITY_ID_JINZHOU ,
    User_Common_Const_MultiCity::CITY_ID_JINGDEZHEN ,
    User_Common_Const_MultiCity::CITY_ID_GANZHOU ,
    User_Common_Const_MultiCity::CITY_ID_JIAN ,
    User_Common_Const_MultiCity::CITY_ID_PANZHIHUA ,
    User_Common_Const_MultiCity::CITY_ID_LUZHOU ,
    User_Common_Const_MultiCity::CITY_ID_DEYANG ,
    User_Common_Const_MultiCity::CITY_ID_NANCHONG ,
    User_Common_Const_MultiCity::CITY_ID_GUANGAN ,
    User_Common_Const_MultiCity::CITY_ID_QUJING ,
    User_Common_Const_MultiCity::CITY_ID_LIJIANG ,
    User_Common_Const_MultiCity::CITY_ID_DALI ,
    User_Common_Const_MultiCity::CITY_ID_WULUMUQI ,
    User_Common_Const_MultiCity::CITY_ID_ZHANGJIAKOU ,
    User_Common_Const_MultiCity::CITY_ID_SHANTOU ,
    User_Common_Const_MultiCity::CITY_ID_WENZHOU ,
    User_Common_Const_MultiCity::CITY_ID_ANSHAN ,
    User_Common_Const_MultiCity::CITY_ID_JINING ,
    User_Common_Const_MultiCity::CITY_ID_ZHUZHOU ,
    User_Common_Const_MultiCity::CITY_ID_HENGYANG ,
    User_Common_Const_MultiCity::CITY_ID_DEZHOU ,
    User_Common_Const_MultiCity::CITY_ID_JINHUA ,
    User_Common_Const_MultiCity::CITY_ID_BAOTOU ,
    User_Common_Const_MultiCity::CITY_ID_CANGZHOU ,
    User_Common_Const_MultiCity::CITY_ID_NANYANG ,
    User_Common_Const_MultiCity::CITY_ID_BINZHOU ,
    User_Common_Const_MultiCity::CITY_ID_RIZHAO ,
    User_Common_Const_MultiCity::CITY_ID_DONGYING ,
    User_Common_Const_MultiCity::CITY_ID_TAIAN ,
    User_Common_Const_MultiCity::CITY_ID_LINYI ,
    User_Common_Const_MultiCity::CITY_ID_ANYANG ,
    User_Common_Const_MultiCity::CITY_ID_TAIZ ,
    User_Common_Const_MultiCity::CITY_ID_ZHANGZHOU ,
    User_Common_Const_MultiCity::CITY_ID_JIEYANG ,
    User_Common_Const_MultiCity::CITY_ID_LIAOCHENG ,
    User_Common_Const_MultiCity::CITY_ID_PINGDINGSHA ,
    User_Common_Const_MultiCity::CITY_ID_BAOJI ,
    User_Common_Const_MultiCity::CITY_ID_DAQING ,
    User_Common_Const_MultiCity::CITY_ID_MAOMING ,
    User_Common_Const_MultiCity::CITY_ID_LIANYUNGANG ,
    User_Common_Const_MultiCity::CITY_ID_HUZHOU ,
    User_Common_Const_MultiCity::CITY_ID_XIANGTAN ,
    User_Common_Const_MultiCity::CITY_ID_ZHANJIANG ,
    User_Common_Const_MultiCity::CITY_ID_ZHAOQING ,
    User_Common_Const_MultiCity::CITY_ID_XIANGYANG ,
    User_Common_Const_MultiCity::CITY_ID_ZAOZHUANG ,
    User_Common_Const_MultiCity::CITY_ID_YANCHENG ,
    User_Common_Const_MultiCity::CITY_ID_SHIYAN ,
    User_Common_Const_MultiCity::CITY_ID_YUEYANG ,
    User_Common_Const_MultiCity::CITY_ID_HENGSHUI ,
    User_Common_Const_MultiCity::CITY_ID_XINXIANG ,
    User_Common_Const_MultiCity::CITY_ID_LISHUI ,
    User_Common_Const_MultiCity::CITY_ID_NINGDE ,
    User_Common_Const_MultiCity::CITY_ID_ZHOUSHAN ,
    User_Common_Const_MultiCity::CITY_ID_CHANGJI ,
    User_Common_Const_MultiCity::CITY_ID_XINYANG ,
    User_Common_Const_MultiCity::CITY_ID_JINZHONG ,
    User_Common_Const_MultiCity::CITY_ID_SANMENXIA ,
    User_Common_Const_MultiCity::CITY_ID_LOUDI ,
    User_Common_Const_MultiCity::CITY_ID_XIANYANG ,
    User_Common_Const_MultiCity::CITY_ID_ZUNYI ,
    User_Common_Const_MultiCity::CITY_ID_WUHU ,
    User_Common_Const_MultiCity::CITY_ID_XINGTAI ,
    User_Common_Const_MultiCity::CITY_ID_JIUJIANG ,
    User_Common_Const_MultiCity::CITY_ID_XIAOGAN ,
    User_Common_Const_MultiCity::CITY_ID_XINING ,
    User_Common_Const_MultiCity::CITY_ID_SANMING ,
    User_Common_Const_MultiCity::CITY_ID_SHAOGUAN ,
    User_Common_Const_MultiCity::CITY_ID_JIAOZUO ,
    User_Common_Const_MultiCity::CITY_ID_CHIFENG ,
    User_Common_Const_MultiCity::CITY_ID_CHANGDE ,
    User_Common_Const_MultiCity::CITY_ID_LESHAN ,
    User_Common_Const_MultiCity::CITY_ID_HANZHONG ,
    User_Common_Const_MultiCity::CITY_ID_EERDUOSI ,
    User_Common_Const_MultiCity::CITY_ID_XUCHANG ,
    User_Common_Const_MultiCity::CITY_ID_SHANGQIU ,
    User_Common_Const_MultiCity::CITY_ID_JINCHENG ,
    User_Common_Const_MultiCity::CITY_ID_ANQING ,
    User_Common_Const_MultiCity::CITY_ID_HUAIAN ,
    User_Common_Const_MultiCity::CITY_ID_LIAOYANG ,
    User_Common_Const_MultiCity::CITY_ID_WUZHOU ,
    User_Common_Const_MultiCity::CITY_ID_ZHUMADIAN ,
    User_Common_Const_MultiCity::CITY_ID_MAANSHAN ,
    User_Common_Const_MultiCity::CITY_ID_HUANGSHI ,
    User_Common_Const_MultiCity::CITY_ID_PANJIN ,
    User_Common_Const_MultiCity::CITY_ID_PINGXIANG ,
    User_Common_Const_MultiCity::CITY_ID_DANDONG ,
    User_Common_Const_MultiCity::CITY_ID_LASA ,
    User_Common_Const_MultiCity::CITY_ID_XINZHOU ,
    User_Common_Const_MultiCity::CITY_ID_BENGBU ,
    User_Common_Const_MultiCity::CITY_ID_JINGZHOU ,
    User_Common_Const_MultiCity::CITY_ID_MUDANJIANG ,
    User_Common_Const_MultiCity::CITY_ID_HEZE ,
    User_Common_Const_MultiCity::CITY_ID_YUNCHENG ,
    User_Common_Const_MultiCity::CITY_ID_LINFEN ,
    User_Common_Const_MultiCity::CITY_ID_CHENZHOU ,
    User_Common_Const_MultiCity::CITY_ID_YICHUN ,
    User_Common_Const_MultiCity::CITY_ID_FUSHUN ,
    User_Common_Const_MultiCity::CITY_ID_HUAIHUA ,
    User_Common_Const_MultiCity::CITY_ID_BENXI ,
    User_Common_Const_MultiCity::CITY_ID_QIQIHAER ,
    User_Common_Const_MultiCity::CITY_ID_YINGKOU ,
    User_Common_Const_MultiCity::CITY_ID_NEIJIANG ,
    User_Common_Const_MultiCity::CITY_ID_YULIN ,
    User_Common_Const_MultiCity::CITY_ID_SUQIAN ,
    User_Common_Const_MultiCity::CITY_ID_SUIHUA ,
    User_Common_Const_MultiCity::CITY_ID_TIELING ,
    User_Common_Const_MultiCity::CITY_ID_ZIGONG ,
    User_Common_Const_MultiCity::CITY_ID_LONGYAN ,
    User_Common_Const_MultiCity::CITY_ID_CHENGDE ,
    User_Common_Const_MultiCity::CITY_ID_GUIGANG ,
    User_Common_Const_MultiCity::CITY_ID_JIAMUSI ,
    User_Common_Const_MultiCity::CITY_ID_QUZHOU ,
    User_Common_Const_MultiCity::CITY_ID_CHANGZHI ,
    User_Common_Const_MultiCity::CITY_ID_DATONG ,
    User_Common_Const_MultiCity::CITY_ID_SHAOYANG ,
    User_Common_Const_MultiCity::CITY_ID_YIBIN ,
    User_Common_Const_MultiCity::CITY_ID_LUOHE ,
    User_Common_Const_MultiCity::CITY_ID_PUYANG ,
    User_Common_Const_MultiCity::CITY_ID_QINGYUAN ,
    User_Common_Const_MultiCity::CITY_ID_PUTIAN ,
    User_Common_Const_MultiCity::CITY_ID_HUAIBEI ,
    User_Common_Const_MultiCity::CITY_ID_DAZHOU ,
    User_Common_Const_MultiCity::CITY_ID_TONGLIAO ,
    User_Common_Const_MultiCity::CITY_ID_SUINING ,
    User_Common_Const_MultiCity::CITY_ID_HULUDAO ,
    User_Common_Const_MultiCity::CITY_ID_FUXIN ,
    User_Common_Const_MultiCity::CITY_ID_SHANGRAO ,
    User_Common_Const_MultiCity::CITY_ID_FUZHOUSHI ,
    User_Common_Const_MultiCity::CITY_ID_SIPING ,
    User_Common_Const_MultiCity::CITY_ID_YIYANG ,
    User_Common_Const_MultiCity::CITY_ID_CHIZHOU ,
    User_Common_Const_MultiCity::CITY_ID_CHUZHOU ,
    User_Common_Const_MultiCity::CITY_ID_TONGLING ,
    User_Common_Const_MultiCity::CITY_ID_NANPING ,
    User_Common_Const_MultiCity::CITY_ID_HEYUAN ,
    User_Common_Const_MultiCity::CITY_ID_BEIHAI ,
    User_Common_Const_MultiCity::CITY_ID_YIWU ,
);
```

* user-site 新 仓库 修改文件:solr.php

``` php

//正式配置
$config['zufang_solr_group'] = array(
    11 => 'hz-list-11, zufang-01',
    14 => 'hz-list-14, zufang-02',
    12 => 'hz-list-03, zufang-03',
    13 => 'hz-list-03, zufang-03',
    15 => 'hz-list-03, zufang-04',
    16 => 'hz-list-03, zufang-04',
    17 => 'hz-list-03, zufang-05',
    18 => 'hz-list-03, zufang-05',
    19 => 'hz-list-03, zufang-06',
    20 => 'hz-list-03, zufang-06',
    21 => 'hz-list-03, zufang-06',
    22 => 'hz-list-03, zufang-06',
    23 => 'hz-list-03, zufang-07',
    24 => 'hz-list-03, zufang-07',
    25 => 'hz-list-03, zufang-07',
    26 => 'hz-list-03, zufang-07',
    27 => 'hz-list-03, zufang-07',
    28 => 'hz-list-03, zufang-08',
    29 => 'hz-list-03, zufang-08',
    30 => 'hz-list-03, zufang-08',
    31 => 'hz-list-03, zufang-08',
    32 => 'hz-list-03, zufang-08',
    33 => 'hz-list-03, zufang-08',
    34 => 'hz-list-03, zufang-08',
    35 => 'hz-list-03, zufang-09',
    36 => 'hz-list-03, zufang-09',
    37 => 'hz-list-03, zufang-09',
    38 => 'hz-list-03, zufang-09',
    39 => 'hz-list-03, zufang-09',
    40 => 'hz-list-03, zufang-09',
    41 => 'hz-list-03, zufang-09',
    42 => 'hz-list-04, zufang-10',
    43 => 'hz-list-04, zufang-10',
    44 => 'hz-list-04, zufang-10',
    45 => 'hz-list-04, zufang-10',
    46 => 'hz-list-04, zufang-10',
    47 => 'hz-list-04, zufang-10',
    48 => 'hz-list-04, zufang-10',
    49 => 'hz-list-04, zufang-10',
    50 => 'hz-list-04, zufang-10',
    51 => 'hz-list-04, zufang-10',
    52 => 'hz-list-04, zufang-10',
    53 => 'hz-list-04, zufang-10',
    54 => 'hz-list-04, zufang-10',
    55 => 'hz-list-04, zufang-10',
    56 => 'hz-list-04, zufang-10',
    57 => 'hz-list-04, zufang-10',
    58 => 'hz-list-04, zufang-10',
    59 => 'hz-list-04, zufang-10',
    60 => 'hz-list-04, zufang-10',
    61 => 'hz-list-04, zufang-10',
    62 => 'hz-list-04, zufang-11',
    63 => 'hz-list-04, zufang-11',
    64 => 'hz-list-04, zufang-11',
    65 => 'hz-list-04, zufang-11',
    66 => 'hz-list-04, zufang-11',
    67 => 'hz-list-04, zufang-11',
    68 => 'hz-list-04, zufang-11',
    69 => 'hz-list-04, zufang-11',
    70 => 'hz-list-04, zufang-11',
    71 => 'hz-list-04, zufang-11',
    72 => 'hz-list-04, zufang-11',
    73 => 'hz-list-04, zufang-11',
    74 => 'hz-list-04, zufang-11',
    75 => 'hz-list-04, zufang-11',
    76 => 'hz-list-04, zufang-11',
    77 => 'hz-list-04, zufang-11',
    78 => 'hz-list-04, zufang-11',
    79 => 'zufang-11',
    80 => 'zufang-11',
    81 => 'zufang-11',
    82 => 'zufang-11',
    83 => 'zufang-11',
    84 => 'zufang-11',
    85 => 'zufang-11',
    86 => 'zufang-11',
    87 => 'zufang-11',
    88 => 'zufang-11',
    89 => 'zufang-11',
    90 => 'zufang-11',
    91 => 'zufang-11',
    92 => 'zufang-11',
    93 => 'zufang-11',
    94 => 'zufang-11',
    95 => 'zufang-11',
    96 => 'zufang-11',
    97 => 'zufang-11',
    98 => 'zufang-11',
    99 => 'zufang-11',
    100 => 'zufang-11',
    101 => 'zufang-11',
    102 => 'zufang-11',
    103 => 'zufang-11',
    104 => 'zufang-11',
    105 => 'zufang-12',
    106 => 'zufang-12',
    107 => 'zufang-12',
    108 => 'zufang-12',
    109 => 'zufang-12',
    110 => 'zufang-12',
    111 => 'zufang-12',
    112 => 'zufang-12',
    113 => 'zufang-12',
    114 => 'zufang-12',
    115 => 'zufang-12',
    116 => 'zufang-12',
    117 => 'zufang-12',
    118 => 'zufang-12',
    119 => 'zufang-12',
    120 => 'zufang-12',
    121 => 'zufang-12',
    122 => 'zufang-12',
    123 => 'zufang-12',
    124 => 'zufang-12',
    125 => 'zufang-12',
    126 => 'zufang-12',
    127 => 'zufang-12',
    128 => 'zufang-12',
    129 => 'zufang-12',
    130 => 'zufang-13',
    131 => 'zufang-13',
    132 => 'zufang-13',
    133 => 'zufang-13',
    134 => 'zufang-13',
    135 => 'zufang-13',
    136 => 'zufang-13',
    137 => 'zufang-13',
    138 => 'zufang-13',
    139 => 'zufang-13',
    140 => 'zufang-13',
    141 => 'zufang-13',
    142 => 'zufang-13',
    143 => 'zufang-13',
    144 => 'zufang-13',
    145 => 'zufang-13',
    146 => 'zufang-13',
    147 => 'zufang-13',
    148 => 'zufang-13',
    149 => 'zufang-13',
    150 => 'zufang-13',
    151 => 'zufang-13',
    152 => 'zufang-13',
    153 => 'zufang-13',
    154 => 'zufang-13',
    155 => 'zufang-14',
    156 => 'zufang-14',
    157 => 'zufang-14',
    158 => 'zufang-14',
    159 => 'zufang-14',
    160 => 'zufang-14',
    161 => 'zufang-14',
    162 => 'zufang-14',
    163 => 'zufang-14',
    164 => 'zufang-14',
    165 => 'zufang-14',
    166 => 'zufang-14',
    167 => 'zufang-14',
    168 => 'zufang-14',
    169 => 'zufang-14',
    170 => 'zufang-14',
    171 => 'zufang-14',
    172 => 'zufang-14',
    173 => 'zufang-14',
    174 => 'zufang-14',
    175 => 'zufang-14',
    176 => 'zufang-14',
    177 => 'zufang-14',
    178 => 'zufang-14',
    179 => 'zufang-14',
    180 => 'zufang-15',
    181 => 'zufang-15',
    182 => 'zufang-15',
    183 => 'zufang-15',
    184 => 'zufang-15',
    185 => 'zufang-15',
    186 => 'zufang-15',
    187 => 'zufang-15',
    188 => 'zufang-15',
    189 => 'zufang-15',
    190 => 'zufang-15',
    191 => 'zufang-15',
    192 => 'zufang-15',
    193 => 'zufang-15',
    194 => 'zufang-15',
    195 => 'zufang-15',
    196 => 'zufang-15',
    197 => 'zufang-15',
    198 => 'zufang-15',
    199 => 'zufang-15',
    200 => 'zufang-15',
    201 => 'zufang-15',
    202 => 'zufang-15',
    203 => 'zufang-15',
    204 => 'zufang-15',
    205 => 'zufang-16',
    206 => 'zufang-16',
    207 => 'zufang-16',
    208 => 'zufang-16',
    209 => 'zufang-16',
    210 => 'zufang-16',
    211 => 'zufang-16',
    212 => 'zufang-16',
    213 => 'zufang-16',
    214 => 'zufang-16',
    215 => 'zufang-16',
    216 => 'zufang-16',
    217 => 'zufang-16',
    218 => 'zufang-16',
    219 => 'zufang-16',
    220 => 'zufang-16',
    221 => 'zufang-16',
    222 => 'zufang-16',
    223 => 'zufang-16',
    224 => 'zufang-16',
    225 => 'zufang-16',
    226 => 'zufang-16',
    227 => 'zufang-16',
    228 => 'zufang-16',

);

```

* haozu-site 旧仓库 修改service.php

``` php

$config['solr_base_url'] = 'http://10.10.6.51:8983/';

//正式配置
$config['zufang_solr_group'] = array(
    11 => 'hz-list-11, zufang-01',
    14 => 'hz-list-14, zufang-02',
    12 => 'hz-list-03, zufang-03',
    13 => 'hz-list-03, zufang-03',
    15 => 'hz-list-03, zufang-04',
    16 => 'hz-list-03, zufang-04',
    17 => 'hz-list-03, zufang-05',
    18 => 'hz-list-03, zufang-05',
    19 => 'hz-list-03, zufang-06',
    20 => 'hz-list-03, zufang-06',
    21 => 'hz-list-03, zufang-06',
    22 => 'hz-list-03, zufang-06',
    23 => 'hz-list-03, zufang-07',
    24 => 'hz-list-03, zufang-07',
    25 => 'hz-list-03, zufang-07',
    26 => 'hz-list-03, zufang-07',
    27 => 'hz-list-03, zufang-07',
    28 => 'hz-list-03, zufang-08',
    29 => 'hz-list-03, zufang-08',
    30 => 'hz-list-03, zufang-08',
    31 => 'hz-list-03, zufang-08',
    32 => 'hz-list-03, zufang-08',
    33 => 'hz-list-03, zufang-08',
    34 => 'hz-list-03, zufang-08',
    35 => 'hz-list-03, zufang-09',
    36 => 'hz-list-03, zufang-09',
    37 => 'hz-list-03, zufang-09',
    38 => 'hz-list-03, zufang-09',
    39 => 'hz-list-03, zufang-09',
    40 => 'hz-list-03, zufang-09',
    41 => 'hz-list-03, zufang-09',
    42 => 'hz-list-04, zufang-10',
    43 => 'hz-list-04, zufang-10',
    44 => 'hz-list-04, zufang-10',
    45 => 'hz-list-04, zufang-10',
    46 => 'hz-list-04, zufang-10',
    47 => 'hz-list-04, zufang-10',
    48 => 'hz-list-04, zufang-10',
    49 => 'hz-list-04, zufang-10',
    50 => 'hz-list-04, zufang-10',
    51 => 'hz-list-04, zufang-10',
    52 => 'hz-list-04, zufang-10',
    53 => 'hz-list-04, zufang-10',
    54 => 'hz-list-04, zufang-10',
    55 => 'hz-list-04, zufang-10',
    56 => 'hz-list-04, zufang-10',
    57 => 'hz-list-04, zufang-10',
    58 => 'hz-list-04, zufang-10',
    59 => 'hz-list-04, zufang-10',
    60 => 'hz-list-04, zufang-10',
    61 => 'hz-list-04, zufang-10',
    62 => 'hz-list-04, zufang-11',
    63 => 'hz-list-04, zufang-11',
    64 => 'hz-list-04, zufang-11',
    65 => 'hz-list-04, zufang-11',
    66 => 'hz-list-04, zufang-11',
    67 => 'hz-list-04, zufang-11',
    68 => 'hz-list-04, zufang-11',
    69 => 'hz-list-04, zufang-11',
    70 => 'hz-list-04, zufang-11',
    71 => 'hz-list-04, zufang-11',
    72 => 'hz-list-04, zufang-11',
    73 => 'hz-list-04, zufang-11',
    74 => 'hz-list-04, zufang-11',
    75 => 'hz-list-04, zufang-11',
    76 => 'hz-list-04, zufang-11',
    77 => 'hz-list-04, zufang-11',
    78 => 'hz-list-04, zufang-11',
    79 => 'zufang-11',
    80 => 'zufang-11',
    81 => 'zufang-11',
    82 => 'zufang-11',
    83 => 'zufang-11',
    84 => 'zufang-11',
    85 => 'zufang-11',
    86 => 'zufang-11',
    87 => 'zufang-11',
    88 => 'zufang-11',
    89 => 'zufang-11',
    90 => 'zufang-11',
    91 => 'zufang-11',
    92 => 'zufang-11',
    93 => 'zufang-11',
    94 => 'zufang-11',
    95 => 'zufang-11',
    96 => 'zufang-11',
    97 => 'zufang-11',
    98 => 'zufang-11',
    99 => 'zufang-11',
    100 => 'zufang-11',
    101 => 'zufang-11',
    102 => 'zufang-11',
    103 => 'zufang-11',
    104 => 'zufang-11',
    105 => 'zufang-12',
    106 => 'zufang-12',
    107 => 'zufang-12',
    108 => 'zufang-12',
    109 => 'zufang-12',
    110 => 'zufang-12',
    111 => 'zufang-12',
    112 => 'zufang-12',
    113 => 'zufang-12',
    114 => 'zufang-12',
    115 => 'zufang-12',
    116 => 'zufang-12',
    117 => 'zufang-12',
    118 => 'zufang-12',
    119 => 'zufang-12',
    120 => 'zufang-12',
    121 => 'zufang-12',
    122 => 'zufang-12',
    123 => 'zufang-12',
    124 => 'zufang-12',
    125 => 'zufang-12',
    126 => 'zufang-12',
    127 => 'zufang-12',
    128 => 'zufang-12',
    129 => 'zufang-12',
    130 => 'zufang-13',
    131 => 'zufang-13',
    132 => 'zufang-13',
    133 => 'zufang-13',
    134 => 'zufang-13',
    135 => 'zufang-13',
    136 => 'zufang-13',
    137 => 'zufang-13',
    138 => 'zufang-13',
    139 => 'zufang-13',
    140 => 'zufang-13',
    141 => 'zufang-13',
    142 => 'zufang-13',
    143 => 'zufang-13',
    144 => 'zufang-13',
    145 => 'zufang-13',
    146 => 'zufang-13',
    147 => 'zufang-13',
    148 => 'zufang-13',
    149 => 'zufang-13',
    150 => 'zufang-13',
    151 => 'zufang-13',
    152 => 'zufang-13',
    153 => 'zufang-13',
    154 => 'zufang-13',
    155 => 'zufang-14',
    156 => 'zufang-14',
    157 => 'zufang-14',
    158 => 'zufang-14',
    159 => 'zufang-14',
    160 => 'zufang-14',
    161 => 'zufang-14',
    162 => 'zufang-14',
    163 => 'zufang-14',
    164 => 'zufang-14',
    165 => 'zufang-14',
    166 => 'zufang-14',
    167 => 'zufang-14',
    168 => 'zufang-14',
    169 => 'zufang-14',
    170 => 'zufang-14',
    171 => 'zufang-14',
    172 => 'zufang-14',
    173 => 'zufang-14',
    174 => 'zufang-14',
    175 => 'zufang-14',
    176 => 'zufang-14',
    177 => 'zufang-14',
    178 => 'zufang-14',
    179 => 'zufang-14',
    180 => 'zufang-15',
    181 => 'zufang-15',
    182 => 'zufang-15',
    183 => 'zufang-15',
    184 => 'zufang-15',
    185 => 'zufang-15',
    186 => 'zufang-15',
    187 => 'zufang-15',
    188 => 'zufang-15',
    189 => 'zufang-15',
    190 => 'zufang-15',
    191 => 'zufang-15',
    192 => 'zufang-15',
    193 => 'zufang-15',
    194 => 'zufang-15',
    195 => 'zufang-15',
    196 => 'zufang-15',
    197 => 'zufang-15',
    198 => 'zufang-15',
    199 => 'zufang-15',
    200 => 'zufang-15',
    201 => 'zufang-15',
    202 => 'zufang-15',
    203 => 'zufang-15',
    204 => 'zufang-15',
    205 => 'zufang-16',
    206 => 'zufang-16',
    207 => 'zufang-16',
    208 => 'zufang-16',
    209 => 'zufang-16',
    210 => 'zufang-16',
    211 => 'zufang-16',
    212 => 'zufang-16',
    213 => 'zufang-16',
    214 => 'zufang-16',
    215 => 'zufang-16',
    216 => 'zufang-16',
    217 => 'zufang-16',
    218 => 'zufang-16',
    219 => 'zufang-16',
    220 => 'zufang-16',
    221 => 'zufang-16',
    222 => 'zufang-16',
    223 => 'zufang-16',
    224 => 'zufang-16',
    225 => 'zufang-16',
    226 => 'zufang-16',
    227 => 'zufang-16',
    228 => 'zufang-16',
);

```

* haozu-site  修改 common.php   log分组 path haozu-jobs

``` php

$config['haozu_rank_logid'] = array(
    '01' => '/home/www/logs/haozurank/01/update_logid.log',
    '02' => '/home/www/logs/haozurank/02/update_logid.log',
    '03' => '/home/www/logs/haozurank/03/update_logid.log',
    '04' => '/home/www/logs/haozurank/04/update_logid.log',
    '05' => '/home/www/logs/haozurank/05/update_logid.log',
    '06' => '/home/www/logs/haozurank/06/update_logid.log',
    '07' => '/home/www/logs/haozurank/07/update_logid.log',
    '08' => '/home/www/logs/haozurank/08/update_logid.log',
    '09' => '/home/www/logs/haozurank/09/update_logid.log',
    '10' => '/home/www/logs/haozurank/10/update_logid.log',
    '11' => '/home/www/logs/haozurank/11/update_logid.log',
    '12' => '/home/www/logs/haozurank/12/update_logid.log',
    '13' => '/home/www/logs/haozurank/13/update_logid.log',
    '14' => '/home/www/logs/haozurank/14/update_logid.log',
    '15' => '/home/www/logs/haozurank/15/update_logid.log',
    '16' => '/home/www/logs/haozurank/16/update_logid.log',
);

$config['haozu_rank_rebuild_log'] = array(
    '01' => '/home/www/logs/haozurank/01/rebuild/',
    '02' => '/home/www/logs/haozurank/02/rebuild/',
    '03' => '/home/www/logs/haozurank/03/rebuild/',
    '04' => '/home/www/logs/haozurank/04/rebuild/',
    '05' => '/home/www/logs/haozurank/05/rebuild/',
    '06' => '/home/www/logs/haozurank/06/rebuild/',
    '07' => '/home/www/logs/haozurank/07/rebuild/',
    '08' => '/home/www/logs/haozurank/08/rebuild/',
    '09' => '/home/www/logs/haozurank/09/rebuild/',
    '10' => '/home/www/logs/haozurank/10/rebuild/',
    '11' => '/home/www/logs/haozurank/11/rebuild/',
    '12' => '/home/www/logs/haozurank/12/rebuild/',
    '13' => '/home/www/logs/haozurank/13/rebuild/',
    '14' => '/home/www/logs/haozurank/14/rebuild/',
    '15' => '/home/www/logs/haozurank/15/rebuild/',
    '16' => '/home/www/logs/haozurank/16/rebuild/',
);

$config['haozu_landlord_build_solr_logdir'] = array(
    '01' => '/home/www/logs/hzlandlordbuildsolr/01/',
    '02' => '/home/www/logs/hzlandlordbuildsolr/02/',
    '03' => '/home/www/logs/hzlandlordbuildsolr/03/',
    '04' => '/home/www/logs/hzlandlordbuildsolr/04/',
    '05' => '/home/www/logs/hzlandlordbuildsolr/05/',
    '06' => '/home/www/logs/hzlandlordbuildsolr/06/',
    '07' => '/home/www/logs/hzlandlordbuildsolr/07/',
    '08' => '/home/www/logs/hzlandlordbuildsolr/08/',
    '09' => '/home/www/logs/hzlandlordbuildsolr/09/',
    '10' => '/home/www/logs/hzlandlordbuildsolr/10/',
    '11' => '/home/www/logs/hzlandlordbuildsolr/11/',
    '12' => '/home/www/logs/hzlandlordbuildsolr/12/',
    '13' => '/home/www/logs/hzlandlordbuildsolr/13/',
    '14' => '/home/www/logs/hzlandlordbuildsolr/14/',
    '15' => '/home/www/logs/hzlandlordbuildsolr/15/',
    '16' => '/home/www/logs/hzlandlordbuildsolr/16/',
);

$config['haozu_broker_rank_logid'] = array(
    '01' => '/home/www/logs/haozubrokerrank/01/update_logid.log',
    '02' => '/home/www/logs/haozubrokerrank/02/update_logid.log',
    '03' => '/home/www/logs/haozubrokerrank/03/update_logid.log',
    '04' => '/home/www/logs/haozubrokerrank/04/update_logid.log',
    '05' => '/home/www/logs/haozubrokerrank/05/update_logid.log',
    '06' => '/home/www/logs/haozubrokerrank/06/update_logid.log',
    '07' => '/home/www/logs/haozubrokerrank/07/update_logid.log',
    '08' => '/home/www/logs/haozubrokerrank/08/update_logid.log',
    '09' => '/home/www/logs/haozubrokerrank/09/update_logid.log',
    '10' => '/home/www/logs/haozubrokerrank/10/update_logid.log',
    '11' => '/home/www/logs/haozubrokerrank/11/update_logid.log',
    '12' => '/home/www/logs/haozubrokerrank/12/update_logid.log',
    '13' => '/home/www/logs/haozubrokerrank/13/update_logid.log',
    '14' => '/home/www/logs/haozubrokerrank/14/update_logid.log',
    '15' => '/home/www/logs/haozubrokerrank/15/update_logid.log',
    '16' => '/home/www/logs/haozubrokerrank/16/update_logid.log',
);

$config['haozu_broker_rank_rebuild_log'] = array(
    '01' => '/home/www/logs/haozubrokerrank/01/rebuild/',
    '02' => '/home/www/logs/haozubrokerrank/02/rebuild/',
    '03' => '/home/www/logs/haozubrokerrank/03/rebuild/',
    '04' => '/home/www/logs/haozubrokerrank/04/rebuild/',
    '05' => '/home/www/logs/haozubrokerrank/05/rebuild/',
    '06' => '/home/www/logs/haozubrokerrank/06/rebuild/',
    '07' => '/home/www/logs/haozubrokerrank/07/rebuild/',
    '08' => '/home/www/logs/haozubrokerrank/08/rebuild/',
    '09' => '/home/www/logs/haozubrokerrank/09/rebuild/',
    '10' => '/home/www/logs/haozubrokerrank/10/rebuild/',
    '11' => '/home/www/logs/haozubrokerrank/11/rebuild/',
    '12' => '/home/www/logs/haozubrokerrank/12/rebuild/',
    '13' => '/home/www/logs/haozubrokerrank/13/rebuild/',
    '14' => '/home/www/logs/haozubrokerrank/14/rebuild/',
    '15' => '/home/www/logs/haozubrokerrank/15/rebuild/',
    '16' => '/home/www/logs/haozubrokerrank/16/rebuild/',
);

$config['v2_haozu_broker_rank_header'] = array(
    '01' => '/home/www/logs/v2rank/haozubrokerrank/log_01.log',
    '02' => '/home/www/logs/v2rank/haozubrokerrank/log_02.log',
    '03' => '/home/www/logs/v2rank/haozubrokerrank/log_03.log',
    '04' => '/home/www/logs/v2rank/haozubrokerrank/log_04.log',
    '05' => '/home/www/logs/v2rank/haozubrokerrank/log_05.log',
    '06' => '/home/www/logs/v2rank/haozubrokerrank/log_06.log',
    '07' => '/home/www/logs/v2rank/haozubrokerrank/log_07.log',
    '08' => '/home/www/logs/v2rank/haozubrokerrank/log_08.log',
    '09' => '/home/www/logs/v2rank/haozubrokerrank/log_09.log',
    '10' => '/home/www/logs/v2rank/haozubrokerrank/log_10.log',
    '11' => '/home/www/logs/v2rank/haozubrokerrank/log_11.log',
    '12' => '/home/www/logs/v2rank/haozubrokerrank/log_12.log',
    '13' => '/home/www/logs/v2rank/haozubrokerrank/log_13.log',
    '14' => '/home/www/logs/v2rank/haozubrokerrank/log_14.log',
    '15' => '/home/www/logs/v2rank/haozubrokerrank/log_15.log',
    '16' => '/home/www/logs/v2rank/haozubrokerrank/log_16.log',
);

$config['v2_haozu_rank_header'] = array(
    '01' => '/home/www/logs/v2rank/haozurank/log_01.log',
    '02' => '/home/www/logs/v2rank/haozurank/log_02.log',
    '03' => '/home/www/logs/v2rank/haozurank/log_03.log',
    '04' => '/home/www/logs/v2rank/haozurank/log_04.log',
    '05' => '/home/www/logs/v2rank/haozurank/log_05.log',
    '06' => '/home/www/logs/v2rank/haozurank/log_06.log',
    '07' => '/home/www/logs/v2rank/haozurank/log_07.log',
    '08' => '/home/www/logs/v2rank/haozurank/log_08.log',
    '09' => '/home/www/logs/v2rank/haozurank/log_09.log',
    '10' => '/home/www/logs/v2rank/haozurank/log_10.log',
    '11' => '/home/www/logs/v2rank/haozurank/log_11.log',
    '12' => '/home/www/logs/v2rank/haozurank/log_12.log',
    '13' => '/home/www/logs/v2rank/haozurank/log_13.log',
    '14' => '/home/www/logs/v2rank/haozurank/log_14.log',
    '15' => '/home/www/logs/v2rank/haozurank/log_15.log',
    '16' => '/home/www/logs/v2rank/haozurank/log_16.log',
);

```

* haozu-site multicity.php 修改

```php

$config['new_city_group_mapping'] = array (
    Const_MultiCity::CITY_ID_SHANGHAI => '01',
    Const_MultiCity::CITY_ID_BEIJING => '02',
    Const_MultiCity::CITY_ID_GUANGZHOU => '03',
    Const_MultiCity::CITY_ID_SHENZHEN => '03',
    Const_MultiCity::CITY_ID_CHENGDU => '04',
    Const_MultiCity::CITY_ID_NANJING => '04',
    Const_MultiCity::CITY_ID_TIANJIN => '05',
    Const_MultiCity::CITY_ID_HANGZHOU => '05',
    Const_MultiCity::CITY_ID_SUZHOU => '06',
    Const_MultiCity::CITY_ID_CHONGQING => '06',
    Const_MultiCity::CITY_ID_DALIAN => '06',
    Const_MultiCity::CITY_ID_WUHAN => '06',
    Const_MultiCity::CITY_ID_JINAN => '07',
    Const_MultiCity::CITY_ID_FOSHAN => '07',
    Const_MultiCity::CITY_ID_WUXI => '07',
    Const_MultiCity::CITY_ID_ZHENGZHOU => '07',
    Const_MultiCity::CITY_ID_CHANGSHA => '07',
    Const_MultiCity::CITY_ID_SHIJIAZHUANG => '08',
    Const_MultiCity::CITY_ID_HONGKONG => '08',
    Const_MultiCity::CITY_ID_QINGDAO => '08',
    Const_MultiCity::CITY_ID_XIAN => '08',
    Const_MultiCity::CITY_ID_NINGBO => '08',
    Const_MultiCity::CITY_ID_HEFEI => '08',
    Const_MultiCity::CITY_ID_DONGGUAN => '08',
    Const_MultiCity::CITY_ID_FUZHOU => '09',
    Const_MultiCity::CITY_ID_KUNMING => '09',
    Const_MultiCity::CITY_ID_GUIYANG => '09',
    Const_MultiCity::CITY_ID_TAIYUAN => '09',
    Const_MultiCity::CITY_ID_SHENYANG => '09',
    Const_MultiCity::CITY_ID_KUNSHAN => '09',
    Const_MultiCity::CITY_ID_NANCHANG => '09',
    Const_MultiCity::CITY_ID_ZHUHAI => '10',
    Const_MultiCity::CITY_ID_CHANGZHOU => '10',
    Const_MultiCity::CITY_ID_ZHONGSHAN => '10',
    Const_MultiCity::CITY_ID_JIAXING => '10',
    Const_MultiCity::CITY_ID_XIAMEN => '10',
    Const_MultiCity::CITY_ID_YANTAI => '10',
    Const_MultiCity::CITY_ID_HAERBIN => '10',
    Const_MultiCity::CITY_ID_HAIKOU => '10',
    Const_MultiCity::CITY_ID_CHANGCHUN => '10',
    Const_MultiCity::CITY_ID_SANYA => '10',
    Const_MultiCity::CITY_ID_HUIZHOU => '10',
    Const_MultiCity::CITY_ID_BAODING => '10',
    Const_MultiCity::CITY_ID_GUILIN => '10',
    Const_MultiCity::CITY_ID_HANDAN => '10',
    Const_MultiCity::CITY_ID_HUHEHAOTE => '10',
    Const_MultiCity::CITY_ID_JILIN => '10',
    Const_MultiCity::CITY_ID_LANZHOU => '10',
    Const_MultiCity::CITY_ID_LANGFANG => '10',
    Const_MultiCity::CITY_ID_LUOYANG => '10',
    Const_MultiCity::CITY_ID_MIANYANG => '10',
    Const_MultiCity::CITY_ID_NANNING => '11',
    Const_MultiCity::CITY_ID_NANTONG => '11',
    Const_MultiCity::CITY_ID_QINHUANGDAO => '11',
    Const_MultiCity::CITY_ID_QUANZHOU => '11',
    Const_MultiCity::CITY_ID_SHAOXING => '11',
    Const_MultiCity::CITY_ID_TAIZHOU => '11',
    Const_MultiCity::CITY_ID_TANGSHAN => '11',
    Const_MultiCity::CITY_ID_WEIHAI => '11',
    Const_MultiCity::CITY_ID_WEIFANG => '11',
    Const_MultiCity::CITY_ID_XUZHOU => '11',
    Const_MultiCity::CITY_ID_YANGZHOU => '11',
    Const_MultiCity::CITY_ID_YICHANG => '11',
    Const_MultiCity::CITY_ID_YINCHUAN => '11',
    Const_MultiCity::CITY_ID_ZHENJIANG => '11',
    Const_MultiCity::CITY_ID_ZIBO => '11',
    Const_MultiCity::CITY_ID_LIUZHOU => '11',
    Const_MultiCity::CITY_ID_JIANGMEN => '11',
    Const_MultiCity::CITY_ID_YANGJIANG => '11',
    Const_MultiCity::CITY_ID_LAIWU => '11',
    Const_MultiCity::CITY_ID_JINGMEN => '11',
    Const_MultiCity::CITY_ID_HUANGGANG => '11',
    Const_MultiCity::CITY_ID_YONGZHOU => '11',
    Const_MultiCity::CITY_ID_HUAINAN => '11',
    Const_MultiCity::CITY_ID_HUANGSHAN => '11',
    Const_MultiCity::CITY_ID_FUYANG => '11',
    Const_MultiCity::CITY_ID_LUAN => '11',
    Const_MultiCity::CITY_ID_YULINSHI => '11',
    Const_MultiCity::CITY_ID_KAIFENG => '11',
    Const_MultiCity::CITY_ID_HEBI => '11',
    Const_MultiCity::CITY_ID_JINZHOU => '11',
    Const_MultiCity::CITY_ID_JINGDEZHEN => '11',
    Const_MultiCity::CITY_ID_GANZHOU => '11',
    Const_MultiCity::CITY_ID_JIAN => '11',
    Const_MultiCity::CITY_ID_PANZHIHUA => '11',
    Const_MultiCity::CITY_ID_LUZHOU => '11',
    Const_MultiCity::CITY_ID_DEYANG => '11',
    Const_MultiCity::CITY_ID_NANCHONG => '11',
    Const_MultiCity::CITY_ID_GUANGAN => '11',
    Const_MultiCity::CITY_ID_QUJING => '11',
    Const_MultiCity::CITY_ID_LIJIANG => '11',
    Const_MultiCity::CITY_ID_DALI => '11',
    Const_MultiCity::CITY_ID_WULUMUQI => '11',
    Const_MultiCity::CITY_ID_ZHANGJIAKOU => '11',
    Const_MultiCity::CITY_ID_SHANTOU => '12',
    Const_MultiCity::CITY_ID_WENZHOU => '12',
    Const_MultiCity::CITY_ID_ANSHAN => '12',
    Const_MultiCity::CITY_ID_JINING => '12',
    Const_MultiCity::CITY_ID_ZHUZHOU => '12',
    Const_MultiCity::CITY_ID_HENGYANG => '12',
    Const_MultiCity::CITY_ID_DEZHOU => '12',
    Const_MultiCity::CITY_ID_JINHUA => '12',
    Const_MultiCity::CITY_ID_BAOTOU => '12',
    Const_MultiCity::CITY_ID_CANGZHOU => '12',
    Const_MultiCity::CITY_ID_NANYANG => '12',
    Const_MultiCity::CITY_ID_BINZHOU => '12',
    Const_MultiCity::CITY_ID_RIZHAO => '12',
    Const_MultiCity::CITY_ID_DONGYING => '12',
    Const_MultiCity::CITY_ID_TAIAN => '12',
    Const_MultiCity::CITY_ID_LINYI => '12',
    Const_MultiCity::CITY_ID_ANYANG => '12',
    Const_MultiCity::CITY_ID_TAIZ => '12',
    Const_MultiCity::CITY_ID_ZHANGZHOU => '12',
    Const_MultiCity::CITY_ID_JIEYANG => '12',
    Const_MultiCity::CITY_ID_LIAOCHENG => '12',
    Const_MultiCity::CITY_ID_PINGDINGSHA => '12',
    Const_MultiCity::CITY_ID_BAOJI => '12',
    Const_MultiCity::CITY_ID_DAQING => '12',
    Const_MultiCity::CITY_ID_MAOMING => '12',
    Const_MultiCity::CITY_ID_LIANYUNGANG => '13',
    Const_MultiCity::CITY_ID_HUZHOU => '13',
    Const_MultiCity::CITY_ID_XIANGTAN => '13',
    Const_MultiCity::CITY_ID_ZHANJIANG => '13',
    Const_MultiCity::CITY_ID_ZHAOQING => '13',
    Const_MultiCity::CITY_ID_XIANGYANG => '13',
    Const_MultiCity::CITY_ID_ZAOZHUANG => '13',
    Const_MultiCity::CITY_ID_YANCHENG => '13',
    Const_MultiCity::CITY_ID_SHIYAN => '13',
    Const_MultiCity::CITY_ID_YUEYANG => '13',
    Const_MultiCity::CITY_ID_HENGSHUI => '13',
    Const_MultiCity::CITY_ID_XINXIANG => '13',
    Const_MultiCity::CITY_ID_LISHUI => '13',
    Const_MultiCity::CITY_ID_NINGDE => '13',
    Const_MultiCity::CITY_ID_ZHOUSHAN => '13',
    Const_MultiCity::CITY_ID_CHANGJI => '13',
    Const_MultiCity::CITY_ID_XINYANG => '13',
    Const_MultiCity::CITY_ID_JINZHONG => '13',
    Const_MultiCity::CITY_ID_SANMENXIA => '13',
    Const_MultiCity::CITY_ID_LOUDI => '13',
    Const_MultiCity::CITY_ID_XIANYANG => '13',
    Const_MultiCity::CITY_ID_ZUNYI => '13',
    Const_MultiCity::CITY_ID_WUHU => '13',
    Const_MultiCity::CITY_ID_XINGTAI => '13',
    Const_MultiCity::CITY_ID_JIUJIANG => '13',
    Const_MultiCity::CITY_ID_XIAOGAN => '14',
    Const_MultiCity::CITY_ID_XINING => '14',
    Const_MultiCity::CITY_ID_SANMING => '14',
    Const_MultiCity::CITY_ID_SHAOGUAN => '14',
    Const_MultiCity::CITY_ID_JIAOZUO => '14',
    Const_MultiCity::CITY_ID_CHIFENG => '14',
    Const_MultiCity::CITY_ID_CHANGDE => '14',
    Const_MultiCity::CITY_ID_LESHAN => '14',
    Const_MultiCity::CITY_ID_HANZHONG => '14',
    Const_MultiCity::CITY_ID_EERDUOSI => '14',
    Const_MultiCity::CITY_ID_XUCHANG => '14',
    Const_MultiCity::CITY_ID_SHANGQIU => '14',
    Const_MultiCity::CITY_ID_JINCHENG => '14',
    Const_MultiCity::CITY_ID_ANQING => '14',
    Const_MultiCity::CITY_ID_HUAIAN => '14',
    Const_MultiCity::CITY_ID_LIAOYANG => '14',
    Const_MultiCity::CITY_ID_WUZHOU => '14',
    Const_MultiCity::CITY_ID_ZHUMADIAN => '14',
    Const_MultiCity::CITY_ID_MAANSHAN => '14',
    Const_MultiCity::CITY_ID_HUANGSHI => '14',
    Const_MultiCity::CITY_ID_PANJIN => '14',
    Const_MultiCity::CITY_ID_PINGXIANG => '14',
    Const_MultiCity::CITY_ID_DANDONG => '14',
    Const_MultiCity::CITY_ID_LASA => '14',
    Const_MultiCity::CITY_ID_XINZHOU => '14',
    Const_MultiCity::CITY_ID_BENGBU => '15',
    Const_MultiCity::CITY_ID_JINGZHOU => '15',
    Const_MultiCity::CITY_ID_MUDANJIANG => '15',
    Const_MultiCity::CITY_ID_HEZE => '15',
    Const_MultiCity::CITY_ID_YUNCHENG => '15',
    Const_MultiCity::CITY_ID_LINFEN => '15',
    Const_MultiCity::CITY_ID_CHENZHOU => '15',
    Const_MultiCity::CITY_ID_YICHUN => '15',
    Const_MultiCity::CITY_ID_FUSHUN => '15',
    Const_MultiCity::CITY_ID_HUAIHUA => '15',
    Const_MultiCity::CITY_ID_BENXI => '15',
    Const_MultiCity::CITY_ID_QIQIHAER => '15',
    Const_MultiCity::CITY_ID_YINGKOU => '15',
    Const_MultiCity::CITY_ID_NEIJIANG => '15',
    Const_MultiCity::CITY_ID_YULIN => '15',
    Const_MultiCity::CITY_ID_SUQIAN => '15',
    Const_MultiCity::CITY_ID_SUIHUA => '15',
    Const_MultiCity::CITY_ID_TIELING => '15',
    Const_MultiCity::CITY_ID_ZIGONG => '15',
    Const_MultiCity::CITY_ID_LONGYAN => '15',
    Const_MultiCity::CITY_ID_CHENGDE => '15',
    Const_MultiCity::CITY_ID_GUIGANG => '15',
    Const_MultiCity::CITY_ID_JIAMUSI => '15',
    Const_MultiCity::CITY_ID_QUZHOU => '15',
    Const_MultiCity::CITY_ID_CHANGZHI => '15',
    Const_MultiCity::CITY_ID_DATONG => '16',
    Const_MultiCity::CITY_ID_SHAOYANG => '16',
    Const_MultiCity::CITY_ID_YIBIN => '16',
    Const_MultiCity::CITY_ID_LUOHE => '16',
    Const_MultiCity::CITY_ID_PUYANG => '16',
    Const_MultiCity::CITY_ID_QINGYUAN => '16',
    Const_MultiCity::CITY_ID_PUTIAN => '16',
    Const_MultiCity::CITY_ID_HUAIBEI => '16',
    Const_MultiCity::CITY_ID_DAZHOU => '16',
    Const_MultiCity::CITY_ID_TONGLIAO => '16',
    Const_MultiCity::CITY_ID_SUINING => '16',
    Const_MultiCity::CITY_ID_HULUDAO => '16',
    Const_MultiCity::CITY_ID_FUXIN => '16',
    Const_MultiCity::CITY_ID_SHANGRAO => '16',
    Const_MultiCity::CITY_ID_FUZHOUSHI => '16',
    Const_MultiCity::CITY_ID_SIPING => '16',
    Const_MultiCity::CITY_ID_YIYANG => '16',
    Const_MultiCity::CITY_ID_CHIZHOU => '16',
    Const_MultiCity::CITY_ID_CHUZHOU => '16',
    Const_MultiCity::CITY_ID_TONGLING => '16',
    Const_MultiCity::CITY_ID_NANPING => '16',
    Const_MultiCity::CITY_ID_HEYUAN => '16',
    Const_MultiCity::CITY_ID_BEIHAI => '16',
    Const_MultiCity::CITY_ID_YIWU => '16',
);

$config['new_group_city_mapping'] = array(
    '01' => array(
            Const_MultiCity::CITY_ID_SHANGHAI
    ),
    '02' => array(
            Const_MultiCity::CITY_ID_BEIJING 
    ),
     '03' => array(
             Const_MultiCity::CITY_ID_GUANGZHOU, 
             Const_MultiCity::CITY_ID_SHENZHEN, 
     ),
     '04' => array(
             Const_MultiCity::CITY_ID_CHENGDU, 
             Const_MultiCity::CITY_ID_NANJING, 
     ),
     '05' => array(
             Const_MultiCity::CITY_ID_TIANJIN, 
             Const_MultiCity::CITY_ID_HANGZHOU, 
     ),
     '06' => array(
             Const_MultiCity::CITY_ID_SUZHOU, 
             Const_MultiCity::CITY_ID_CHONGQING, 
             Const_MultiCity::CITY_ID_DALIAN, 
             Const_MultiCity::CITY_ID_WUHAN, 
     ),
     '07' => array(
             Const_MultiCity::CITY_ID_JINAN, 
             Const_MultiCity::CITY_ID_FOSHAN, 
             Const_MultiCity::CITY_ID_WUXI, 
             Const_MultiCity::CITY_ID_ZHENGZHOU, 
             Const_MultiCity::CITY_ID_CHANGSHA, 
     ),
     '08' => array(
             Const_MultiCity::CITY_ID_SHIJIAZHUANG, 
             Const_MultiCity::CITY_ID_HONGKONG, 
             Const_MultiCity::CITY_ID_QINGDAO, 
             Const_MultiCity::CITY_ID_XIAN, 
             Const_MultiCity::CITY_ID_NINGBO, 
             Const_MultiCity::CITY_ID_HEFEI, 
             Const_MultiCity::CITY_ID_DONGGUAN, 
     ),
     '09' => array(
             Const_MultiCity::CITY_ID_FUZHOU, 
             Const_MultiCity::CITY_ID_KUNMING, 
             Const_MultiCity::CITY_ID_GUIYANG, 
             Const_MultiCity::CITY_ID_TAIYUAN, 
             Const_MultiCity::CITY_ID_SHENYANG, 
             Const_MultiCity::CITY_ID_KUNSHAN, 
             Const_MultiCity::CITY_ID_NANCHANG, 
     ),
     '10' => array(
             Const_MultiCity::CITY_ID_ZHUHAI, 
             Const_MultiCity::CITY_ID_CHANGZHOU, 
             Const_MultiCity::CITY_ID_ZHONGSHAN, 
             Const_MultiCity::CITY_ID_JIAXING, 
             Const_MultiCity::CITY_ID_XIAMEN, 
             Const_MultiCity::CITY_ID_YANTAI, 
             Const_MultiCity::CITY_ID_HAERBIN, 
             Const_MultiCity::CITY_ID_HAIKOU, 
             Const_MultiCity::CITY_ID_CHANGCHUN, 
             Const_MultiCity::CITY_ID_SANYA, 
             Const_MultiCity::CITY_ID_HUIZHOU, 
             Const_MultiCity::CITY_ID_BAODING, 
             Const_MultiCity::CITY_ID_GUILIN, 
             Const_MultiCity::CITY_ID_HANDAN, 
             Const_MultiCity::CITY_ID_HUHEHAOTE, 
             Const_MultiCity::CITY_ID_JILIN, 
             Const_MultiCity::CITY_ID_LANZHOU, 
             Const_MultiCity::CITY_ID_LANGFANG, 
             Const_MultiCity::CITY_ID_LUOYANG, 
             Const_MultiCity::CITY_ID_MIANYANG, 
     ),
     '11' => array(
             Const_MultiCity::CITY_ID_NANNING, 
             Const_MultiCity::CITY_ID_NANTONG, 
             Const_MultiCity::CITY_ID_QINHUANGDAO, 
             Const_MultiCity::CITY_ID_QUANZHOU, 
             Const_MultiCity::CITY_ID_SHAOXING, 
             Const_MultiCity::CITY_ID_TAIZHOU, 
             Const_MultiCity::CITY_ID_TANGSHAN, 
             Const_MultiCity::CITY_ID_WEIHAI, 
             Const_MultiCity::CITY_ID_WEIFANG, 
             Const_MultiCity::CITY_ID_XUZHOU, 
             Const_MultiCity::CITY_ID_YANGZHOU, 
             Const_MultiCity::CITY_ID_YICHANG, 
             Const_MultiCity::CITY_ID_YINCHUAN, 
             Const_MultiCity::CITY_ID_ZHENJIANG, 
             Const_MultiCity::CITY_ID_ZIBO, 
             Const_MultiCity::CITY_ID_LIUZHOU, 
             Const_MultiCity::CITY_ID_JIANGMEN, 
             Const_MultiCity::CITY_ID_YANGJIANG, 
             Const_MultiCity::CITY_ID_LAIWU, 
             Const_MultiCity::CITY_ID_JINGMEN, 
             Const_MultiCity::CITY_ID_HUANGGANG, 
             Const_MultiCity::CITY_ID_YONGZHOU, 
             Const_MultiCity::CITY_ID_HUAINAN, 
             Const_MultiCity::CITY_ID_HUANGSHAN, 
             Const_MultiCity::CITY_ID_FUYANG, 
             Const_MultiCity::CITY_ID_LUAN, 
             Const_MultiCity::CITY_ID_YULINSHI, 
             Const_MultiCity::CITY_ID_KAIFENG, 
             Const_MultiCity::CITY_ID_HEBI, 
             Const_MultiCity::CITY_ID_JINZHOU, 
             Const_MultiCity::CITY_ID_JINGDEZHEN, 
             Const_MultiCity::CITY_ID_GANZHOU, 
             Const_MultiCity::CITY_ID_JIAN, 
             Const_MultiCity::CITY_ID_PANZHIHUA, 
             Const_MultiCity::CITY_ID_LUZHOU, 
             Const_MultiCity::CITY_ID_DEYANG, 
             Const_MultiCity::CITY_ID_NANCHONG, 
             Const_MultiCity::CITY_ID_GUANGAN, 
             Const_MultiCity::CITY_ID_QUJING, 
             Const_MultiCity::CITY_ID_LIJIANG, 
             Const_MultiCity::CITY_ID_DALI, 
             Const_MultiCity::CITY_ID_WULUMUQI, 
             Const_MultiCity::CITY_ID_ZHANGJIAKOU, 
     ),
     '12' => array(
             Const_MultiCity::CITY_ID_SHANTOU, 
             Const_MultiCity::CITY_ID_WENZHOU, 
             Const_MultiCity::CITY_ID_ANSHAN, 
             Const_MultiCity::CITY_ID_JINING, 
             Const_MultiCity::CITY_ID_ZHUZHOU, 
             Const_MultiCity::CITY_ID_HENGYANG, 
             Const_MultiCity::CITY_ID_DEZHOU, 
             Const_MultiCity::CITY_ID_JINHUA, 
             Const_MultiCity::CITY_ID_BAOTOU, 
             Const_MultiCity::CITY_ID_CANGZHOU, 
             Const_MultiCity::CITY_ID_NANYANG, 
             Const_MultiCity::CITY_ID_BINZHOU, 
             Const_MultiCity::CITY_ID_RIZHAO, 
             Const_MultiCity::CITY_ID_DONGYING, 
             Const_MultiCity::CITY_ID_TAIAN, 
             Const_MultiCity::CITY_ID_LINYI, 
             Const_MultiCity::CITY_ID_ANYANG, 
             Const_MultiCity::CITY_ID_TAIZ, 
             Const_MultiCity::CITY_ID_ZHANGZHOU, 
             Const_MultiCity::CITY_ID_JIEYANG, 
             Const_MultiCity::CITY_ID_LIAOCHENG, 
             Const_MultiCity::CITY_ID_PINGDINGSHA, 
             Const_MultiCity::CITY_ID_BAOJI, 
             Const_MultiCity::CITY_ID_DAQING, 
             Const_MultiCity::CITY_ID_MAOMING, 
     ),
     '13' => array(
             Const_MultiCity::CITY_ID_LIANYUNGANG, 
             Const_MultiCity::CITY_ID_HUZHOU, 
             Const_MultiCity::CITY_ID_XIANGTAN, 
             Const_MultiCity::CITY_ID_ZHANJIANG, 
             Const_MultiCity::CITY_ID_ZHAOQING, 
             Const_MultiCity::CITY_ID_XIANGYANG, 
             Const_MultiCity::CITY_ID_ZAOZHUANG, 
             Const_MultiCity::CITY_ID_YANCHENG, 
             Const_MultiCity::CITY_ID_SHIYAN, 
             Const_MultiCity::CITY_ID_YUEYANG, 
             Const_MultiCity::CITY_ID_HENGSHUI, 
             Const_MultiCity::CITY_ID_XINXIANG, 
             Const_MultiCity::CITY_ID_LISHUI, 
             Const_MultiCity::CITY_ID_NINGDE, 
             Const_MultiCity::CITY_ID_ZHOUSHAN, 
             Const_MultiCity::CITY_ID_CHANGJI, 
             Const_MultiCity::CITY_ID_XINYANG, 
             Const_MultiCity::CITY_ID_JINZHONG, 
             Const_MultiCity::CITY_ID_SANMENXIA, 
             Const_MultiCity::CITY_ID_LOUDI, 
             Const_MultiCity::CITY_ID_XIANYANG, 
             Const_MultiCity::CITY_ID_ZUNYI, 
             Const_MultiCity::CITY_ID_WUHU, 
             Const_MultiCity::CITY_ID_XINGTAI, 
             Const_MultiCity::CITY_ID_JIUJIANG, 
     ),
     '14' => array(
             Const_MultiCity::CITY_ID_XIAOGAN, 
             Const_MultiCity::CITY_ID_XINING, 
             Const_MultiCity::CITY_ID_SANMING, 
             Const_MultiCity::CITY_ID_SHAOGUAN, 
             Const_MultiCity::CITY_ID_JIAOZUO, 
             Const_MultiCity::CITY_ID_CHIFENG, 
             Const_MultiCity::CITY_ID_CHANGDE, 
             Const_MultiCity::CITY_ID_LESHAN, 
             Const_MultiCity::CITY_ID_HANZHONG, 
             Const_MultiCity::CITY_ID_EERDUOSI, 
             Const_MultiCity::CITY_ID_XUCHANG, 
             Const_MultiCity::CITY_ID_SHANGQIU, 
             Const_MultiCity::CITY_ID_JINCHENG, 
             Const_MultiCity::CITY_ID_ANQING, 
             Const_MultiCity::CITY_ID_HUAIAN, 
             Const_MultiCity::CITY_ID_LIAOYANG, 
             Const_MultiCity::CITY_ID_WUZHOU, 
             Const_MultiCity::CITY_ID_ZHUMADIAN, 
             Const_MultiCity::CITY_ID_MAANSHAN, 
             Const_MultiCity::CITY_ID_HUANGSHI, 
             Const_MultiCity::CITY_ID_PANJIN, 
             Const_MultiCity::CITY_ID_PINGXIANG, 
             Const_MultiCity::CITY_ID_DANDONG, 
             Const_MultiCity::CITY_ID_LASA, 
             Const_MultiCity::CITY_ID_XINZHOU, 
     ),
     '15' => array(
             Const_MultiCity::CITY_ID_BENGBU, 
             Const_MultiCity::CITY_ID_JINGZHOU, 
             Const_MultiCity::CITY_ID_MUDANJIANG, 
             Const_MultiCity::CITY_ID_HEZE, 
             Const_MultiCity::CITY_ID_YUNCHENG, 
             Const_MultiCity::CITY_ID_LINFEN, 
             Const_MultiCity::CITY_ID_CHENZHOU, 
             Const_MultiCity::CITY_ID_YICHUN, 
             Const_MultiCity::CITY_ID_FUSHUN, 
             Const_MultiCity::CITY_ID_HUAIHUA, 
             Const_MultiCity::CITY_ID_BENXI, 
             Const_MultiCity::CITY_ID_QIQIHAER, 
             Const_MultiCity::CITY_ID_YINGKOU, 
             Const_MultiCity::CITY_ID_NEIJIANG, 
             Const_MultiCity::CITY_ID_YULIN, 
             Const_MultiCity::CITY_ID_SUQIAN, 
             Const_MultiCity::CITY_ID_SUIHUA, 
             Const_MultiCity::CITY_ID_TIELING, 
             Const_MultiCity::CITY_ID_ZIGONG, 
             Const_MultiCity::CITY_ID_LONGYAN, 
             Const_MultiCity::CITY_ID_CHENGDE, 
             Const_MultiCity::CITY_ID_GUIGANG, 
             Const_MultiCity::CITY_ID_JIAMUSI, 
             Const_MultiCity::CITY_ID_QUZHOU, 
             Const_MultiCity::CITY_ID_CHANGZHI, 
     ),
     '16' => array(
             Const_MultiCity::CITY_ID_DATONG, 
             Const_MultiCity::CITY_ID_SHAOYANG, 
             Const_MultiCity::CITY_ID_YIBIN, 
             Const_MultiCity::CITY_ID_LUOHE, 
             Const_MultiCity::CITY_ID_PUYANG, 
             Const_MultiCity::CITY_ID_QINGYUAN, 
             Const_MultiCity::CITY_ID_PUTIAN, 
             Const_MultiCity::CITY_ID_HUAIBEI, 
             Const_MultiCity::CITY_ID_DAZHOU, 
             Const_MultiCity::CITY_ID_TONGLIAO, 
             Const_MultiCity::CITY_ID_SUINING, 
             Const_MultiCity::CITY_ID_HULUDAO, 
             Const_MultiCity::CITY_ID_FUXIN, 
             Const_MultiCity::CITY_ID_SHANGRAO, 
             Const_MultiCity::CITY_ID_FUZHOUSHI, 
             Const_MultiCity::CITY_ID_SIPING, 
             Const_MultiCity::CITY_ID_YIYANG, 
             Const_MultiCity::CITY_ID_CHIZHOU, 
             Const_MultiCity::CITY_ID_CHUZHOU, 
             Const_MultiCity::CITY_ID_TONGLING, 
             Const_MultiCity::CITY_ID_NANPING, 
             Const_MultiCity::CITY_ID_HEYUAN, 
             Const_MultiCity::CITY_ID_BEIHAI, 
             Const_MultiCity::CITY_ID_YIWU, 
     ),
);  

$config['new_haozu_broker_tb_mapping'] = array(
    'default'=>'b_four_prop_search',
    '01'=>'b_sh_prop_search',
    '02'=>'b_bj_prop_search',
    '03'=>'b_other_prop_search',
    '04'=>'b_other_prop_search',
    '05'=>'b_other_prop_search',
    '06'=>'b_other_prop_search',
    '07'=>'b_other_prop_search',
    '08'=>'b_other_prop_search',
    '09'=>'b_other_prop_search',
    '10'=>'b_four_prop_search',
    '11'=>'b_four_prop_search',
    '12'=>'b_four_prop_search',
    '13'=>'b_four_prop_search',
    '14'=>'b_four_prop_search',
    '15'=>'b_four_prop_search',
    '16'=>'b_four_prop_search',
); //经纪人房源索引表 与 solr组对应关系

```

* 注意JOB游标同步


