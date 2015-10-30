# 二手房房源上下架Job


### 数据库和表:
|数据库|表名称|作用|读写|是否独有|
| anjuke_db | ajk_propertys | 房源表|  写| |
| anjuke_db | ajk_propertysale |二手房 | 写 | |
| anjuke_db | ajk_commextend | 小区扩展 | 写 |  |
| anjuke_db | ajk_merge_comm | | 写 |  |
| anjuke_db | ajk_communitys | 小区表 | 写 |  |
| anjuke_db | ajk_brokerextend | 经纪人信息|  写| |
| anjuke_db | cst_broker_company |经纪人公司 | 写 | |
| anjuke_db | ajk_advertisements |  | 写 |  |
| anjuke_db | ajk_ad_company_store | 经纪人门店| 写 |  |
| anjuke_db | ajk_saleprice | 价格 | 写 |  |
| anjuke_db | ajk_propertys_tools | |  写| |
| anjuke_db | ajk_property_tag |房源标签 | 写 | |
| anjuke_db | ajk_private_tag |  | 写 |  |
| anjuke_db | map_communities_baidu | 小区百度地图| 写 |  |
| anjuke_db | map_communities | 小区地图 | 写 |  |
| anjuke_db | ajk_communitys | 小区表|  写| |
| anjuke_db | ajk_commtype |区域板块 | 写 | |
| anjuke_db | ajk_usetype | 房屋类型 | 写 |  |
| anjuke_db | ajk_medal_relation | | 写 |  |
| anjuke_db | sw_metro_community_distances | 抓取小区周边 | 写 |  |
| ajk_dw_stats | hu_rank_all_score_rank_list_ppc |  | 写 |  |
| ajk_dw_stats | hu_rank_all_score_rank_list |  | 写 |  |
| ajk_dw_stats | hu_rank_broker_score_ppc |  | 写 |  |
| ajk_dw_stats | hu_hp_comm_score |  | 写 |  |
| ajk_dw_stats | hu_hp_comm_fx_score |  | 写 |  |
| ajk_dw_stats | dw_pricing_comm_quartile |  | 写 |  |
| ajk_dw_stats | hu_rank_all_score_type_ppc |  | 写 |  |
| ajk_dw_stats | hu_rank_all_score_type |  | 写 |  |
| user_prop_db | up_schools|  | 写 |  |
| user_prop_db | up_school_community|  | 写 |  |
| user_prop_db | bus_relation|  | 写 |  |
| user_prop_db | community_place_relation|  | 写 |  |
| user_prop_db | broker_ext_profile|  | 写 |  |
| anjuke_db_5_slave | ajk_propertysale|  | 写 |  |
| propertys_db_04 | ajk_propertys|  | 写 |  |
| propertys_sh_db | ajk_propertys|  | 写 |  |
| propertys_other_db | ajk_propertys|  | 写 |  |
| propertys_db | ajk_propertys|  | 写 |  |
| user_prop_xx_db |broker_property_xx|  | 写 |  |
| user_prop_xx_db |broker_property_extend_xx|  | 写 |  |
| user_prop_xx_db |crawl_property_xx|  | 写 |  |
| user_prop_xx_db |crawl_property_extend_xx|  | 写 |  |


