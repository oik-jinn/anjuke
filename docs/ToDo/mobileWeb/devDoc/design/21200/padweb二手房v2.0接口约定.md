### 二手房地图v2.0接口文档

地图数据接口统一入口: http://shanghai.anjuke.com/ajax/newmap/search/

#### 区域聚合接口说明

##### 以下为js请求服务器时的参数说明

**参数** | **类型** | 说明
--- | --- | --- 
model | int | 选择数据模式{1:聚合数据,2:可视区域数据}
p | int | 分页数，缺省时为1
order | int | 排序规则详细见`order规则详细表`
price | int | 筛选用价格参数，控制器会负责转换为对应的价格区间
room | int | 筛选用房型参数
bounds | string | 地图四点坐标


##### order规则详细表

**值** | **说明**
--- | ---
0 | 默认
1 | 价格(高到底)
2 | 价格(低到高)
3 | 面积(高到底)
4 | 面积(低到高)
5 | 建造时间(高到底)
6 | 建造时间(低到高)


##### 以下为被期望的服务器返回数据举例

```
{
    "groups": [
        {
            "id": "18",    // 区域id
            "lat": "30.717737422955",   // 经度
            "lng": "121.35614918797",   // 纬度
            "areaName": "金山",    // 区域名
            "propCount": 10    // 房源数
        }
    ],
    "props": {
        "list": {
            {
                "id": "168799011",   // 房源id
                "img_url": "http://images.anjukestatic.com/attachments_comm/2/2/1/221257a058c398acad37b252c97aaac3.jpg",    // 图片url
                "prop_url":"http://sh.anjuke.com/prop/view/", // 单页url
                "title": "测试房源  请勿联系",    // 标题
                "area_name": "浦东",
                "block_name": "陆家嘴",
                "hall_num": 3,
                "room_num": 2,
                "area": "77",       // 面积
                "price": "1770000",    // 价格
                "community_id": "124",       // 小区id
                "community_name": "由由五村"    // 小区名
            },
        }
    },
    "maxPage": 23,   // 最大分页数
    "curPage": 1,    // 当前页码
    "propNum": 568,  // 总房源数
    "sojData": {
        "propParam": "168799148|4388790|49|1,168799009|640657|99|1",
        "v": "2.0",
        "hp": 1
    }
}
```

#### 可视区域接口说明

##### 以下为js请求服务器时的参数说明

**参数** | **类型** | 说明
--- | --- | ---
model | int | 选择数据模式{1:聚合数据,2:可视区域数据}
p | int | 分页数，缺省时为1
order | int | 排序规则详细见`order规则详细表`
price | int | 筛选用价格参数，控制器会负责转换为对应的价格区间
room | int | 筛选用房型参数
bounds | string | 地图四点坐标(请求第一页时需要)
comms | string | 小区集合(请求分页时需要)
nolist | int | {0:缺省，返回数据中含有列表数据; 1:返回数据中无列表数据(选定单小区后拖动时，仅请求地图标点)}
reqcomm | int | {0:缺省，返回数据中不含小区信息(commid!=0时，仅请求单小区房源列表); 1:返回数据中含有选中小区的标点信息(单页跳转至地图时使用)}

##### 以下为被期望的服务器返回数据举例

```
{
    "comms": [
        {
            "commid": 291,    // 小区id
            "lat": "31.2869968414307",    // 经度
            "lng": "121.607528686523",    // 纬度
            "commname": "证大家园（一至...",    // 小区名
            "midPrice": "40551",    // 均价
            "propCount": 1472    // 小区房源数
        }
    ],
    "props": {
        "list": {
            {
                "id": "168799011",   // 房源id
                "img_url": "http://images.anjukestatic.com/attachments_comm/2/2/1/221257a058c398acad37b252c97aaac3.jpg",    // 图片url
                "prop_url":"http://sh.anjuke.com/prop/view/", // 单页url
                "title": "测试房源  请勿联系",    // 标题
                "area": "77",       // 面积
                "price": "1770000",    // 价格
                "community_id": "124",       // 小区id
                "community_name": "由由五村",    // 小区名
                "isper": 0,    // 是否个人房源，影响单页请求的url
                "areaCode": "7"    // 小区code
            }
        },
        "commids": "106_27_130_2145_854_1302_85_13_3471_147_38_5572_2494_318_9332_62476_153_687_3350_33_16655_468_116_5769_1149_2024_3530_6_114067_1674"
    },
    "maxPage": 376,
    "curPage": 1,
    "propNum": 9393,
    "sojData": {
        "propParam": "218571640|19763|856|2,219714289|268654|291034|2,",
        "v": "2.0",
        "hp": 1
    }
}
```

#### 列表页请求单页url

* 列表url采用pc版单页url，由js构造和请求pad版url链接

* 地图列表页访问单页参数说明

**参数** | **值** | 说明
--- | --- | ---
spread | mapprop | 竞价规则参数，不传递时列表访问的单页一律视为定价