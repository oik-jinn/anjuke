## 兴趣组列表solr方案

#### 存储结构：

| Field		| Type	| Indexed	| Stored	| Required	| Description |
| --------- | ----- | --------- | --------- | --------- | ----------- |
| id		| int	| Y			| Y			| Y			| 兴趣组id	  |
| user_id	| int	| Y			| Y			| Y			| 创建者id	  |
| city_id	| int	| Y			| Y			| Y			| 城市id		  |
| community_id | int	| Y		| Y			| Y			| 小区id		  |
| lat		| double | Y		| N			| N			| 纬度		  |
| lng		| double | Y		| N			| N			| 经度		  |
| create_time | int	| N			| N			| N			| 创建时间	  |
| updated_time	| int	| Y		| N			| N			| 最新更新时间  |

#### 使用场景：
1、根据小区距离范围查询，并按照更新时间倒序，获取分页结果。


## 兴趣组话题列表solr方案

#### 存储结构：

| Field		| Type	| Indexed	| Stored	| Required	| Description |
| --------- | ----- | --------- | --------- | --------- | ----------- |
| topic_id	| int	| Y			| Y			| Y			| 话题id	  |
| user_id	| int	| Y			| Y			| Y			| 话题作者id  |
| group_id	| int	| Y			| Y			| Y			| 话题兴趣组id|
| status    | int	| Y		    | Y			| N			| 话题状态	  |
| updated_time	| int	| Y		| Y			| N			| 最新更新时间|

#### 使用场景：
1、根据话题的最新更新时间（根据话题回复时间），排序话题，即热帖模工排序。