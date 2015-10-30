# TW二手房列表页白皮书

## 页面url

```
http://m.anjuke.com/{pinyin}/sale/
```
    
### 页面模块列表
 
* 1 房源信息
* 2 小区信息
* 3 经纪人信息
* 4 推荐位
* 5 房贷参考
* 6 附近房价走势

### 配置
房价趋势
http://m.anjuke.com/ajax/propprice?comm_id=419643&areacode=000100070077&nearby=6

位置周边
http://m.anjuke.com/ajax/community/periphery?comm_id=419643

### 数据库和表:
|序号|数据库|地址|表名称|作用|读写|是否独有|访问量 |
|--- | --- | --- | --- | --- | --- | --- | --- |
|1 4|user_prop_sh_db||broker_property_{city_id}|用户端上海二手房房源表|读|是| 136448*80%=11w|
|1 4|user_prop_bj_db||broker_property_{city_id}|用户端北京二手房房源表|读|否| 136448*80%=11w|
|1 4|user_prop_s0{city_id%3}_db||broker_property_{city_id}|用户端二手房房源表|读|否|136448*80%=11w |
|1 4|user_prop_s0{city_id%3}_db||wuba_property_{city_id}|用户端二手房房源表|读|否| 136448*80%=11w|
|1 4|user_prop_s0{city_id%3}_db||wuba_property_image_{city_id}|用户端二手房58房源图片表|读|否| 136448*80%=11w|
|1 4|anjuke_db||ajk_swsale|抓取房源表|读|否|136448*80%=11w |
|1 4|anjuke_db||ajk_swrent|搜屋的出租房源|读|是|136448*80%=11w |
|1 4|ajk_dw_stats||hu_dingcai_broker_score|租房小区排行|读|否|136448*80%=11w |
|1 4|anjuke_db||ajk_wuba_commtype_relation|58安居客虚拟小区区域板块(区域商圈)对照表|读|否|136448*80%=11w |
|1 4|anjuke_db||ajk_fitmenttype|装修类型映射|读|否| 136448*80%=11w|
|1 4|anjuke_db||ajk_usetype|房屋类型配置|读|否| 136448*80%=11w|
|1 2 4|anjuke_db||ajk_communitys|小区表|读|否| 136448*80%=11w|
|1 2 4|anjuke_db||ajk_commtype|区域板块表|读|否| 136448*80%=11w|
|1 2 4|anjuke_db||ajk_attachments_comm|小区信息|读|否| 136448*80%=11w|
|4|anjuke_db||map_communities_baidu|小区百度地图信息|读|否| 136448*80%=11w|
|4|anjuke_db||map_communities_soso_pano|小区街景|读|否|136448*80%=11w |
|4|user_prop_db||ajk_nearby_score|小区周边得分|读|否|136448*80%=11w |
|4|user_prop_db||commPlaceRelationDao|二手房小区周边对应关系|读|否| 136448*80%=11w|
|4|anjuke_db||map_communities_soso_pano|小区街景|读|否|136448*80%=11w |
|4|anjuke_db||map_communities_soso_pano|小区街景|读|否|136448*80%=11w |

### 推荐

http://gitlab.corp.anjuke.com/_site/docs/tree/master/DesignDoc/Fangyuan/Ershou/Recommend