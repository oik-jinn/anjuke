##租房相关wiki
---

### 列表页逻辑

* 搜索逻辑@TODO


* 词库job支持
    * 数据库表
        * 公园　user_prop_db.place_info [type_id=5]
        * 医院　user_prop_db.place_info [type_id=1]
        * 景点  user_prop_db.place_info [type_id=6]
        * 学校  user_prop_db.upp_school
        * 公交站点 user_prop_db.bus_line
        * 公交线路 user_prop_db.bus_stations
        * 公交站点-公交线路关系 user_prop_db.bus_relation
        * 小区  anjuke_db.ajk_communitys(小区基本信息), anjuke_db.map_communities(小区经纬度)
    * 分词逻辑
        * 去（）［］() [] 中内容, 符号也去掉
        * 去前面的字符
            * 医院: 去掉"解放军"或"大学"前面的字符,"解放军"或"大学"也去掉
            * 去省或市或区或县前面的字符，省或市或区或县字符也去掉
            * 去掉最前端在省或市字典中的省或市名称
        * 去后面的字符
            * 医院:     去掉"医院"后面的字符, 保留"医院"字符
            * 公园:     去掉"公园"或"动物园"后面的字符, 保留"公园"或"动物园"字符
            * 学校:     去掉"幼儿园"或"小学"或"中学"或"学校"后面的字符, 保留"幼儿园"或"小学"或"中学"或"学校"字符
            * 公交站点: 去掉"路"后面的字符,保留"路"字符
            * 公交线路: 去掉"路"后面的字符,保留"路"字符
        * 去掉特殊字符
        * 不保留最后分词结果
            * 分词结果为单字汉字
            * 医院: 最后只剩"医院"
            * 公园: 最后只剩"幼儿园"或"小学"或"中学"或"学校"
            * 公交: 最后只剩"路"
        * 省市字典
    * 词库文件名称
        * anjuke_zu_cityID.txt
    * 词库文件格式
        * 医院
            * 格式：{医院名称}:hospital,{医院ID},{纬度},{经度}
            * 示例：上海邮电医院(医院名称):hospital,1,31.224674,121.459934
        * 公园
            * 格式：{医院名称}:park,{公园ID},{纬度},{经度}
            * 示例：复兴公园:park,166344,31.223346,121.475997
        * 景点
            * 格式：{景点名称}:viewport,{景点ID},{纬度},{经度}
            * 示例：复兴公园:viewport,167946,31.189232,121.421107
        * 学校
            * 格式：{学校名称}:school,{学校ID},{纬度},{经度}
            * 示例：浦泾中学:school,167946,31.189232,121.421107
        * 公交站点
            * 格式：{站点名称}:busstation,{站点ID},{纬度},{经度}
            * 示例：东明路:busstation,167946,31.144337,121.524884
        * 公交线路
            * 格式：{公交线路名称}:bus,{线路ID},{实际公交线路名称}
            * 示例：1008路:bus,41877,1008路
        * 小区:
            * 格式：{小区名称}:community,{小区ID},{实际小区名称},{纬度},{经度},{小区地址}
            * 示例：宏莲馨苑:community,2,宏莲馨苑,31.185243,121.550451,莲园路151弄
    * Job
        *命令:app-haozu-jobs/bin/launcher.php dict/update_search_dict.php


### 更新solr相关Job
* 个人房源更新
    * 队列表：rent_db.prop_lucene_updated(查询type=1)
    * 进程：/home/www/logs/haozurank/{城市分组}/update_logid.log
    * app-haozu-jobs/bin/solr/hz_rank_landlord.php 
* 经纪人房源更新
    * 队列表：rent_db.prop_lucene_updated(查询type=2)
    * 进程：/home/www/logs/haozubrokerrank/{城市分组}/update_logid.log
    * app-haozu-jobs/bin/solr/hz_rank_broker_haozu.php 
* 二手房经纪人房源
    * 队列表：rent_db.ajk_prop_lucene_update
    * 进程：/home/www/logs/ajkzfrank/{城市分组}/update_logid.log
    * 命令：app-haozu-jobs/bin/solr/hz_rank_broker_ajkzf.php 
* 修复租房个人房源
    * app-haozu-jobs/bin/solr/fix_hz_landlord_prop.php
* 修复租房经纪人房源
    * app-haozu-jobs/bin/solr/fix_hz_broker_prop.php
* 修复二手房经纪人房源
    * app-haozu-jobs/bin/solr/fix_ajkzf_broker.php  

### 房源solr说明

* 分组

    * http://sc10-001.a.ajkdns.com:8983/hz-list-11/
    * http://sc10-001.a.ajkdns.com:8983/hz-list-14/
    * http://sc10-001.a.ajkdns.com:8983/hz-list-03/
    * http://sc10-001.a.ajkdns.com:8983/hz-list-04/
    
* 中间件通知的房源以中间件通知为准

    * 推广中间件接口：[详细文档请点击](SpreadMiddleware.md)
    * 上下架处理流程：[详细文档请点击](SpreadUpdown.md)


* solr的分组

	    * 定价或端口或竞价
        * 上海（cityId=11）:http://sc10-001.a.ajkdns.com:8983/hz-list-11/
        * 北京（cityId=14）:http://sc10-001.a.ajkdns.com:8983/hz-list-14/
        * other（cityId=<41）:http://sc10-001.a.ajkdns.com:8983/hz-list-03/
        * four（cityId>=42）:http://sc10-001.a.ajkdns.com:8983/hz-list-04/
    
    
* 字段说明

Field|Type|Indexed|Stored|Description
---|---|---|---|---
from|int|true|true |3:租房经纪人 1:二手房经纪人 0:好租个人 13:大业主房源 14:个人抓取房源 15:经纪人抓取房源
islist|boolean|true|true |1:定价或端口房源
is_hp|sint|true|true |1:竞价房源

* solr在线房源查询条件
    * 竞价或精选：
    
      ```
      http://sc10-001.a.ajkdns.com:8983/hz-list-11/select?wt=json&q=city_id:城市id&fq=is_hp:1
      ```

    * 示例:查询上海经纪人id为1076178的在线精选房源
    
	  ```
      http://sc10-001.a.ajkdns.com:8983/hz-list-11/select?wt=json&q=city_id:11&fq=is_hp:1&&fq=broker_id:1076178
      ```
      
    * 定价或端口：
    
      ```
      http://sc10-001.a.ajkdns.com:8983/hz-list-11/select?wt=json&q=city_id:城市id&fq=islist:1
      ```

    * 示例:查询上海经纪人id为1076178的在线定价或端口房源

      ```
      http://10.10.6.51:8983/ajk-saleprop11/select?wt=json&q=city_id:11&fq=islist:1&fq=broker_id:1076178
      ```
