# 租房现存逻辑梳理

## 列表页扣费排序相关

### 租房列表发展史
* 套餐->刷新排序-> rank排序->好盘（竞价、定价）->新套餐
### 产品先转
* 2014.8止（36+3）个PPC房源，27个旧端口房源
* 2014.8止 旧端口房源：哈尔滨、常州、嘉兴、南宁、潍坊、呼和浩特、扬州、唐山、兰州、泰州、秦皇岛、徐州、威海、泉州、南通、洛阳、吉林、宜昌、廊坊、镇江、保定、桂林、邯郸、银川、绍兴、绵阳、贵阳

### 列表排序逻辑部分名词解释

* A区房源(板块+价格)的竞价房源
* B区房源（区域或者其他BI条件）的竞价房源

### solr中房源字段解释
```
{
	id: 28352181,           房源id
	city_id: 11,            城市id
	from: 0,                来源[0：个人，1：安居客经纪人，3：好租经纪人]
	rank_type: 3,           rank大段所属类型，用于大段分层    
	rank_type_24: 68,       rank小段所属类型，用于分小段
	rank_score: 1953,       混合
	rank_random: 1957.28,   分段随机数
	rank_r: 8654.5,         随机rank值floatval(rand(0, 1000000) / 100)
	slat: 31.17179,         通过小区信息表得到的经度
	slng: 121.45174,        通过小区信息表得到的slng
	proimg_d: "http://pic1.ajkimg.com/display/hz/bb00cffaa1e069e2e5d2ad6fe760b8bf/	122x90.jpg",
	plan_id: 0,             计划id
	hp_plan_id: 0,          好盘计划类型
	plan_type: 0,           计划类型
	is_hp: 0,               是否为好盘[0： 非好盘，1：好盘]
	hp_ratio: 0,
	hp_rank: 0,             
	hp_rank_r: 8654.5,      随机rank值floatval(rand(0, 1000000) / 100)
	hp_rank_type: 3,        rank大段所属类型，用于大段分层    
	hp_rank_type_24: 68,    rank小段所属类型，用于分小段
	hp_rank_score: 1953,    混合
	bmap_lat: 31.176,       百度地图经度
	bmap_lng: 121.456,      百度地图纬度
	score: 68
}
```

### 已整理逻辑脑图
* ![mind_map](ZufangMindMap.jpg)