## SEO-TW-二手房-公交地铁SEO列表设计

### 设计路由

* listTools.php -> set_list_type()修改 

    >* list_type需要能够区分仅有 `esf/` 的情况和 `esf/metro-[d|m](\d{6})/` 的url格式

    >* list_type需要能够区分仅有 `esf/` 的情况和 `esf/bus-d(\d{6})/` 的url格式

* User_Touch_List_Process_ListSeoProcess::getSeoData()

    >* 需要添加 m,d 的适配

* route.php修改

* 地铁找房

```
<!-- 站点 url:  -->
m.anjuke.com/sh/esf/metro-b123456/

<!-- 线路 url:  -->
m.anjuke.com/sh/esf/metro-m123456/
```

* 公交找房

```
<!-- 站点 url:  -->
m.anjuke.com/sh/esf/bus-d123456/
```

#### 设计process

process命名 User_Touch_List_Process_List{$name}Process，基本结构类似参照ListNormalProcess.php

* 地铁找房

    >* 站点 $name = 'MetroStation'

    >* 线路 $name = 'MetroLine'

* 公交找房

    >* 站点 $name = 'BusStation'

#### 设计推荐模块

* 获取 地铁站点，公交站点 坐标

* Community_Core_Comm_Service_CommunityService::getNearbyIdsByPoint() 

    >* 上面的方法存在数据维护的问题，小区数据没有及时维护的机制，所以可能出现信息不及时的问题，是否需要考虑在Service中添加查找小区Solr的方法，保证数据的时效性，不过可能需要增加开发时间。

--- 

* 地铁找房

    >* 上海地铁沿线 [线路列表](http://shanghai.anjuke.com/sitemap/esf/metro/)

    >* 站点附近小区 需要新增 type=30

    >* 线路附近小区 需要新增 type=30

* 公交找房

    >* 站点附近站点 需要新增 type=21

    >* 站点附近小区 需要新增 type=30

---

* 实际实现

    >* getStationNearbyComms(\$coordinate, $distance)

    >   * <del>该方法负责调用Community_Core_Comm_Service_CommunityService::getNearbyIdsByPoint()方法</del>

    >   * 新增Service方法，使用community35的Solr，拼写类似getNearbyIdsByPoint()的方法获取数据

---

#### 逻辑设计

>* 获取搜索词的坐标
>   * 地铁站点
>   * 地铁线路
>   * 公交站点
>   * 以上三类信息都由seo分词接口通过url解析获得的kw参数来获取
>* kw为`公交`站点时，通过nearby接口获取公交站点坐标，再次查询nearby接口获得站点周边其他站点的数据和小区数据
>* kw为`地铁`站点时，通过nearby接口获取地铁站点坐标，再次查询nearby接口获得站点周边小区列表
>* kw为`地铁线路`时，通过getMetroFanglist方法获取该线路的一个地铁站点(需求)，在调用上面的地铁站点方法获取数据
>* 地铁相关的查询，都需要显示地铁部件，通过getMetroUrls方法获取城市的地铁列表
>   * 地铁部件将显示所有的地铁线路
>* 本期所有的小区推荐都需要取出6套小区和该小区的均价，所以需要针对小区相关需求新增使用 community35 的 Solr service 方法

#### 关键代码

```php
<?php
/**
 * ListMetroStationProcess.php
 * 地铁站点 Process
 */
class User_Touch_List_Process_ListMetroStationProcess extends User_Touch_List_Process_AbstractListProcess {

    // other functions

    protected function setOther () {
        return array(
            // ...
            'metro_lines' => $this->getMetroLines(),    // 获取城市所有的地铁路线和构造url
            'nearby_comms' => $this->getNearbyComms()   // 获取地铁站点附近的小区
        );
    }

}

/**
 * nearbylist/CommunityListBiz.php
 * Biz Seo 周边业务类
 * 获取坐标点附近的小区列表
 */
class Biz_Seo_Nearbylist_CommunityListBiz extends Apf_BaseService {
    public function getNearbyComms($coordinate, $distance, $start=0, $rows=6) {
        // 调用 Solr Service 为 community35 的 Service function
    }
}

/**
 * nearbylist/MatchingListBiz.php
 * Biz Seo 周边业务类
 * 获取坐标点附近的站点列表
 */
class Biz_Seo_Nearbylist_MatchingListBiz extends Apf_BaseService {

    const MATCHING_TYPE_HOSPITAL = 1;
    const MATCHING_TYPE_MARKET = 2;
    const MATCHING_TYPE_BANK = 3;
    const MATCHING_TYPE_RESTAURANT = 4;
    const MATCHING_TYPE_PARK = 5;
    const MATCHING_TYPE_ENTERTAINMENT = 6;
    const MATCHING_TYPE_SCHOOL = 20;
    const MATCHING_TYPE_BUS = 21;
    const MATCHING_TYPE_METRO = 22;
    const MATCHING_TYPE_COMMUNITY = 30;

    public function getNearbyBus($coordinate, $distance, $start=0, $rows=6) {
        // 调用 Solr Service 为 ajk-commnearby 的 Service function
    }
}

// CommunityService.php
class Community_Core_Comm_Service_CommunityService extends Apf_BaseDaoService{
    public function getNearbyCommsByPoint($point, $distance, $start=0, $rows=6) {}
}

?>
```

#### api

* url分词接口

```
http://beijin.anjuke.com/seo/api/?city_id=11&search_id=144834&stype=1
```

* 周边Solr

小区周边信息Community_Core_Comm_Service_CommunityService::getNearbyIdsByPoin(point, type, distance)调用的Solr如下例：

```
http://sc10-001.a.ajkdns.com:8983/ajk-commnearby/select?q=type:21&pt=31.216204,121.52946&wt=json&sfield=latlng&start=0&rows=10&d=2&fl=_dist_:geodist(),*&fq={!geofilt}&sort=geodist()%20asc
```

小区周边信息调用参数说明

**参数** | **说明**
--- | ---
point | 坐标信息，数组{lat, lng}
type | 信息类型，具体关系见type 参数说明表
distance | 相对pt点的距离，单位为千米


type 参数说明表

**值** | **说明**
--- | ---
1 | 医院
2 | 超市
3 | 银行
4 | 餐厅
5 | 公园
6 | 娱乐
20 | 学校
21 | 公交
22 | 地铁
30 | 小区


* 小区Solr

```
http://sc10-001.a.ajkdns.com:8983/community35/select/?q=*:*&pt=31.216204,121.52946&wt=json&sfield=latlng&start=0&rows=6&d=2&fl=_dist_:geodist(),*&fq={!geofilt}&sort=geodist()%20asc
```
