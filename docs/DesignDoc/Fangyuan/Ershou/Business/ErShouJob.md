# 二手房房源更新Job


### 数据库和表:
|数据库|表名称|作用|读写|是否独有|
| user_prop_xx_db | broker_property_xx | 经纪人房源基础信息表|  写| |
| user_prop_xx_db | broker_property_extend_xx |经纪人房源扩展信息表 | 写 | |
| user_prop_xx_db | crawl_property_xx | 抓取房源表 | 写 |  |
| user_prop_xx_db | crawl_property_extend_xx| 抓取房源扩展表| 写 |  |
| queue_db | esf_prop_event_queue_00/09 | 对列表 | 写 | |
