# Search

##

* islist 1 定价
* is_hp 1 竟价
## 切换 SOLR 查询方式为 SDK

* 做到业务调用方式 传参方式不变 即对目前已有业务无影响 
* [文档](http://gitlab.corp.anjuke.com/_apf/v2-system/blob/master/classes/apf/search/SearchClient.php)

## MSS 关键词匹配服务

* [文档](http://gitlab.corp.anjuke.com/_incubator/mss/tree/master#documents)
* 待开发 词库JOB 发送词库至 新MSS服务
    * [参考代码](http://gitlab.corp.anjuke.com/_site/anjuke/blob/master/app-jobs/bin/tools/update_search_dict.php)
    * 使用 逐词录入 的方式丢进MSS服务
    * 拆分词库 按照 城市-类型的规则  如 anjuke_11_area [上海区域词库]
* 删除词典 curl 'http://10.20.6.40:9101/dict/delete' -F 'dicname=anjuke_zu_11'
* 新建词库 curl  'http://10.20.6.40:9101/dict/create?dicname=anjuke_zu_11'
* 录入词库文件  curl 'http://10.20.6.40:9101/word/upload' -F 'dicname=anjuke_zu_11' -F 'file=@anjuke_zu_11.txt' 
* 重载词库 curl 'http://10.20.6.40:9101/dict/reload' -F 'dicname=anjuke_zu_11'
* 获取匹配 curl 'http://10.20.6.40:9101/mss/search' -d 'dicname=anjuke_zu_11' -d 'text=中远两湾城'

## 词库

* 词库规则
    * kw:{"key1":"val1","key2":"val2","key3":"val3","key4":"val4"}

* 区域词 anjuke_cityid_area
    * 浦东:{"type":"area","kw":"浦东","code":"00010002","area_id":123}
* 板块词 anjuke_cityid_block
    * 陆家嘴:{"type":"block","kw":"陆家嘴","code":"000100020021","block_id":456}
* 小区词 anjuke_cityid_community
    * 嘉德公寓:{"type":"community","kw":"嘉德公寓","commid":3}
* 户型词（1室 两室…）anjuke_cityid_housemodel
    * 五室以上:{"type":"housemodel","kw":"五室以上","housemodel":1588,"condition":6,"zfcondition":5}
* 房屋类型词（公寓、老公房）anjuke_cityid_usetype
    * 公寓:{"type":"usetype","kw":"公寓","usetype":1}
* 地铁线路词 anjuke_cityid_metroline
    * 6号线:{"type":"metroline","kw":"6号线","metroid":8}
* 地铁站点词 anjuke_cityid_metrostation
    * 金桥路:{"type":"metrostation","kw":"金桥路","stationid":99}
* 公交线路词 anjuke_cityid_busline
    * 995:{"type":"busline","kw":"995","busid":1}
* 公交站点词 anjuke_cityid_busstation
    * 金桥站:{"type":"busstation","kw":"金桥路","stationid":99,"lat":31.143075,"lng":121.527033}
* 地址词 anjuke_cityid_address
    * 莲园路:{"type":"address","kw":"莲园路"}
* 景点词 anjuke_cityid_viewport
    * 上海博物馆:{"type":"viewport","kw":"上海博物馆","lat":31.143075,"lng":121.527033}
* 公园词 anjuke_cityid_park
    * 复兴公园:{"type":"park","kw":"复兴公园","lat":31.143075,"lng":121.527033}
* 学校词 anjuke_cityid_school
    * 明珠小学:{"type":"school","kw":"明珠小学","schoolid":123}
* 医院词 anjuke_cityid_hospital
    * 邮电医院:{"type":"hospital","kw":"邮电医院","lat":31.143075,"lng":121.527033}

## 筛选项 -- 待做筛选项

* 合租 (is_shared:1)
    * 独立卫生间(complex_info)
    * 阳台(complex_info)
    * 朝南(complex_info)
* 地铁房（勾选）
    * metro_distance:0 to 1000 
* 步行（至地铁站步行时间） 
    * sort:ceil(div(field, 500)) asc  注：只有在确定地铁线路 或 站点时 才使用该算法排序
    * 10分钟以内:  
        * metro_distance:0 to 800
        * metro_line_distance_min_8:0 to 800
        * metro_station_distance_min_265:0 TO 800
    * 10-20分钟:
        * metro_distance:800 to 1600 
        * metro_line_distance_min_8:800 to 1600
        * metro_station_distance_min_265:800 to 1600
    * 20-30分钟:
        * metro_distance:1600 to 2400 
        * metro_line_distance_min_8:1600 to 2400
        * metro_station_distance_min_265:1600 to 2400

## 敏感词

* 已有 目前就是使用的新的服务

## 斯坦福分词

* 已有 

## 距地铁房距离
    * 具体参考代码haozu-site BLL_Community_CommunityMetro::get_nearby_metros
    * 根据房源小区ID 去 sw_metro_community_distances 获取地铁站和该小区的距离信息
    * 根据station_id 获取站点信息 sw_metro_stations
    * 根据经度 纬度 计算距离 (没搞懂 sw_metro_community_distances 表中已有距离了 为啥还要重新计算)
    * 取最近的一个站点的信息

## 地标搜索

## Else

* 目前租房和二手房 新老框架都使用的 是 pkuseg  斯坦福分词
* Bayes(贝叶斯分词)   二手房POI（地标）搜索在使用   分类地理词   老二手房 老好租都在使用 
* GEOCode  目前 二手房 租房 新老框架都在使用  用于 根据地理词获取地标经纬度
* Chinese2Pinyin   目前我只发现 在 user-site 仓库  租房业务在用 用于 区域 板块 名称转换  貌似只有JOB在调这个方法
