# 地图找房搜索接口

## 路由

```
 $config['mappings'][$prefix.'Ajax_ErShouMapSearch'] = array(
    '^/ajax/mapsearch/sale',
);
```
## controller

User_Touch_Ajax_ErShouMapSearch


## 接口参数

参数 | 类型 | 是否必须 | 可否为空　| 说明
---|---|---|---|-----
comm_id| int| 可以|点击小区必须|小区id
zoom| int| 是| 不可以|当前地图的缩放等级
p| int| 是| 可以|页码
sw_lat| float| 是| 不可以|左上角纬度
sw_lng| float| 是| 不可以|左上角经度
ne_lat| float| 是| 不可以|右下角纬度
ne_lng| float| 是| 不可以|右下角经度
price_id| int| 价格筛选必须| 可以|价格段的id
area_id| int| 面积筛选必须| 可以|面积的id
room_type| int| 户型筛选必须| 可以|户型

## 要加的和需要修改文件

* app-user-touch/classes/user/touch/search/UesearchMap.php  新建

* app-ershou-core/classes/ershou/core/search/rewriter/TwMapFacetRewriter.php 新建 构造聚合查询

* app-ershou-core/classes/ershou/core/search/rewriter/TwParamsConvertRewriter.php  新增

```
 Ershou_Core_Sale_Const_SearchParams::LAT => Ershou_Core_Sale_Const_UesearchParams::BLAT,
 Ershou_Core_Sale_Const_SearchParams::LNG => Ershou_Core_Sale_Const_UesearchParams::BLNG,
 ```

* app-ershou-core/classes/ershou/core/search/UesearchDataFormater.php 判断地图找房对结果进行格式化 

* app-ershou-core/classes/ershou/core/sale/dto/ListingUesearch.php 

```
/**
 * @var bool
 * 判断是否是地图找房搜索
 */
public $is_map_search;

```

## 查询参数的构造

1.筛选条件
    
2.地标参数

```
     $poi_params = new Ershou_Core_Sale_Dto_ListingSearchPoi();
                $poi_params->distance = $this->params['distance'];
                $poi_params->lat = array(
                                                           'lower' => 31.064235,
                                                           'upper' => 31.423853,
                                                       );
                $poi_params->lng = array(
                                                           'lower' => 121.299405,
                                                           'upper' => 121.707883,
                                                       );
                $poi_params->map_type = 'baidu';
            
```
## 接口返回字段说明

* regions 区域 可为空
    
字段 | 类型 | 说明
---|---|-----
id | | 区域id
lng | | 经度
lat | | 纬度
area_name | | 区域名称
py | | 拼音
prop_count | | 房源总数
area_code | | 区域的TypeCode

* blocks 板块 可为空
    
字段 | 类型 | 说明    
---|---|-----
id | | 区域id
lng | | 经度
lat | | 纬度
block_name | | 板块名称
py | | 拼音
prop_count | | 房源总数
area_code | | 区域的TypeCode


* comms 小区 可为空

字段 | 类型 | 说明    
---|---|-----
id | | 区域id
lng | | 经度
lat | | 纬度
comm_name | | 板块名称
py | | 拼音
prop_count | | 房源总数
mid_price | | 均价

* props 房源信息 可为空

字段 | 类型 | 说明    
---|---|-----
prop_id | | 房源id
title | | 房源标题
toilet_num | | 卫
block_name | | 板块
area_name | | 区域
default_image | | 默认图
prop_url | | 房源url
room_num | | 室
hall_num | | 厅
total | | 房源总价
comm_name | | 小区名称
area_num | | 面积
broker_id | | 经纪人Id
comm_id | | 小区id
city_id | | 城市id
is_wuba_comm | | 是否是58虚拟小区
pn | | pagename
source_type | | 房源类型

## 接口返回值

```

{
    "status": "ok",
    "data": {
        "regions": [
            {
                "id": "7",
                "lng": "121.56739336405",
                "lat": "31.220104873204",
                "area_name": "浦东",
                "py": "pudong",
                "prop_count": 841809,
                "area_code": "00010007"
            },
            {
                "id": "11",
                "lng": "121.41822295874",
                "lat": "31.106018744131",
                "area_name": "闵行",
                "py": "minhang",
                "prop_count": 841809,
                "area_code": "00010007"
            }
        ],
        "blocks": [
            {
                "id": "7",
                "lng": "121.56739336405",
                "lat": "31.220104873204",
                "block_name": "浦东",
                "py": "pudong",
                "prop_count": 841809,
                "area_code": "00010007"
            },
            {
                "id": "11",
                "lng": "121.41822295874",
                "lat": "31.106018744131",
                "block_name": "闵行",
                "py": "minhang",
                "prop_count": 841809,
                "area_code": "00010007"
            }
        ],
        "comms": [
            {
                "id": 379643,
                "lat": "31.490051",
                "lng": "121.34186",
                "comm_name": "旭辉澜悦湾",
                "mid_price": "14491",
                "prop_count": 406
            },
            {
                "id": 584213,
                "lat": "31.4704086",
                "lng": "121.354379",
                "comm_name": "旭辉城",
                "mid_price": "15123",
                "prop_count": 341
            }
        ],
        "props": [
            {
                    "prop_id": "355519299",
                    "title": "唯一一套南临河在卖，稀缺下叠加别墅，送150平超值私家花园！",
                    "toilet_num": "3",
                    "broker_name": "高文强",
                    "block_name": "",
                    "area_name": "松江",
                    "default_image": "http://pic1.ajkimg.com/display/anjuke/159eab7cbc6c8fcb073e67fe98e48b36/75x100c.jpg",
                    "prop_url": "http://m.anjuke.com/sh/sale/A355519299/?isauction=200",
                    "room_num": "4",
                    "hall_num": "2",
                    "total": 265,
                    "comm_name": "绿洲长岛花园",
                    "area_num": 175.26,
                    "broker_id": "926810",
                    "comm_id": "752212",
                    "city_id": "11",
                    "is_wuba_comm": false,
                    "pn": "Ershou_Web_Property_ViewRevision_0",
                    "source_type": 1
               
            }
            
        ]
    }
}

```
