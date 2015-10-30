### Dao信息
------------
* 小区基本信息表: anjuke_db.ajk_communitys
```
查询小区名,小区地址,城市id,区域板块等,区域板块关联ajk_commtype查询;
typeflag=0表示可用的小区
```

* 小区扩展信息表: anjuke_db.ajk_commextend
```
小区描述,建造年代,面积,物业公司,绿化率,容积率等
```
* 小区类型表: anjuke_db.ajk_commtype
```
Community_Core_City_Dao_CommtypeDao
```
* 小区图片表: anjuke_db.ajk_attachments_comm

* 小区经纬度表(百度经纬度): anjuke_db.map_communities_baidu

* 小区经纬度表(谷歌经纬度,移动app会用到,我们不会): anjuke_db.map_communities

* 小区均价表: dw_stats.price_trend_comm_monthly

* 小区周边数据表: user_prop_db.place_info
```
小区周边信息,从百度抓取,具体内容查看设计文档.
1:医院,2:超市,3:银行,4:餐馆,5:公园,6:景点,7:道路,8:地标,20:学校,21:公交,22:地铁
```

* 小区周边关系表: user_prop_db.community_place_relation_xx
```
由于数据量太大,根据小区id后两位分表
```

* 公交站点及其经纬度表: user_prop_db.bus_stations 
* 对应公交线表(user_prop_db.bus_line);
* 公交线-公交站对应关系表user_prop_db.bus_relation;

* 地铁站点及其经纬度表: anjuke_db.sw_metro_stations
* 对应地铁线表(anjuke_db.sw_metros)

* 小区周边得分表: user_prop_db.ajk_nearby_score 
```
通过job将dw数据导到我们自己使用的库,
TODO,建一个统一个库将dw表数据导入,供用户端使用.
```

* 小区单页-小区概况
```
ajk_commextend ajk_communitys frm_communitys
```



