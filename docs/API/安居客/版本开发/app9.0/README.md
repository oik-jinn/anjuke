# APP9.0接口设计

### 统一出参结果
| 参数名称	| 类型	| 说明	|
| --------- | ----- | ----- |
| status | int	| 接口返回状态码，0为成功，非0为失败 |
| msg	 | string	| 接口返回信息，根据status不同，0为成功，非0返回错误原因|
| data  	| mixed	| 返回结果|

#### 错误返回格式：
```{
    "status": "10000",
    "msg": "参数错误",
    "data": {}
}```


### 接口1：热门标签（搜索时出现）

##### **api接口地址**

api_name: info_collection

* pg地址：http://api.anjuke.test/mobile/v5/sale/hot/tag?city_id=11
* 线上地址：http://api.anjuke.com/mobile/v5/sale/hot/tag?city_id=11

##### **入参说明（get）**
[基本参数](###)

| 参数名称	| 参数类型	| 参数含义	| 是否必需	| 说明| 举例|
| --- | --- | --- | --- | --- | ---	|
| city	| int	| 城市id| Y|搜索使用 |11|

##### **出参data说明**

| 参数名称	| 类型	| 说明	|
| --------- | ----- | ----- |
| data | array | 热门标签 |

```
返回格式：
{
	data: [
		"南北通透",
		"一梯两户",
		"产证满五年",
		"两房朝南",
		"小区中心位置",
		"地铁房"
	]
}
```

### 接口2：热门小区（筛选区域板块时出现）

##### **api接口地址**

api_name: info_collection

* pg地址：http://api.anjuke.test/mobile/v5/sale/hot/community?city_id=11
* 线上地址：http://api.anjuke.com/mobile/v5/sale/hot/community?city_id=11

##### **入参说明（get）**
[基本参数](###)

| 参数名称	| 参数类型	| 参数含义	| 是否必需	| 说明| 举例|
| --- | --- | --- | --- | --- | ---	|
| city	| int	| 城市id| Y|筛选使用 |11|
| area_id	| int	| 城市id| Y|筛选区域 |7|
| block_id	| int	| 板块id| N|筛选板块 |60|

##### **出参data说明**

| 参数名称	| 类型	| 说明	|
| --------- | ----- | ----- |
| `data` | array | 小区列表 |
| id | string | 小区id |
| name | string | 小区名 |

```
返回格式：
{
	data: [
		{
			id: "82254",
			name: "浦坊大楼"
		},
		{
			id: "656517",
			name: "测试小区"
		},
		{
			id: "64",
			name: "和平新村"
		}
	]
}
```