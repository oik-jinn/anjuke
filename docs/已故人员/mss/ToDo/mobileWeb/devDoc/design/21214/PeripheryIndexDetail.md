#小区周边指数详细设计

###1.需求背景
    以指数化的方式，将小区周边信息展示给用户，方便用户更快做出决策。 

###2.需求分析

* 小区评分：
    * 分数为0-10分
    * 展示方式为5颗星星，2分代表1颗星
    * 如果分数为0，不展示相应的周边配套信息
    * 展示最少一颗星，即1分2分都展示1颗星

* 小区配套信息：
    * 交通指数
        * 地铁
* 小区周边有那些指数
    * 交通指数地铁
        * 公交
    * 教育指数
        * 学校
    * 医疗指数
        * 医院
    * 商业指数
        * 银行
        * 超市
        * 餐饮
* 设计思路
    * 本需求主要有两个关注点:
        * 1.周边各指数数据来源（即需要哪些dao/service）；
        * 2.周边指数的数据算法，需要按照等级/距离等排序（考虑放在biz层，便于site/touch等多个站点调用）；
    
###3.数据展示方式

* 异步ajax请求
* 接口约定
    * 地址：http://m.anjuke.com/ajax/community/periphery/?comm_id=99
    * 输入：
        * comm_id 小区id
    * 输出：
        * status 状态 ok/failed
        * msg    状态信息 ok/参数有误
        * data   html
    * 本需求主要有两个关注点
        * 1.周边各指数数据来源（即需要哪些dao/service）；
        * 2.周边指数的数据算法，需要按照等级/距离等排序（考虑放在biz层，便于site/touch等多个站点调用）；

###3.概要设计

![summaryDesign](PeripheryIndexSummary.jpeg)

###4.程序调用流程图

![flowChart](flowChart.png)

###5.需要哪些biz

* 1.小区周边biz

```php
<?php
class  Biz_Public_comm_PeripheryBiz extends Apf_BaseService {
    
    /*
    * 获取交通信息
    */
    public function getTrafficInfo () {
        
    }
    
    /**
     * 获取教育信息
     */
    public function getEducationInfo($id){

    }
    
    /**
     * 获取医疗信息
     */
    public function getMedicalInfo($id){

    }
    
    /**
     * 获取商业信息
     */
    public function getBusinessInfo($id){

    }
}
?>

```



###6.需要哪些 Service（共需要2个，已有2个，方法9个，已有3个）

* 1.小区service（已有！）

```php
<?php
class  Community_Core_Comm_Service_CommunityService extends Apf_BaseDaoService {
    /*
    * 获取小区周边信息（没有！）
    */
    public function getNearbyData ($comm_id, $type_id, $city_id, $distance=null,$order=null) {
        
    }
    
    /*
    * 获取指定类型的tag（已有！）
    */
    public function getNearbyTagByTypeId () {
        
    }
    
    /**
     * 根据id获取周边信息（已有！）
     */
    public function getNearbyInfoById($id){

    }
}
?>

```

* 2.学校service（已有！）

```php
<?php
class  Community_Core_Comm_Service_SchoolService extends Apf_BaseDaoService {
    /*
    * 获取学校信息（已有！）
    */
    public function getSchoolInfoById () {
        
    }
}
?>

```

* 3.地铁service（没有！）

```php
<?php
class  Community_Core_Comm_Service_MetroService extends Apf_BaseDaoService {
    /*
    * 获取地铁信息（没有！）
    */
    public function getMetroInfo ($id) {
        
    }
    
    /*
    * 获取地铁线路信息（没有！）
    */
    public function getMetroStationInfo ($id) {
        
    }
}
?>

```

* 4.公交service（没有！）

```php
<?php
class  Community_Core_Comm_Service_BusService extends Apf_BaseDaoService {
    /*
    * 获取公交信息（没有！）
    */
    public function getBusStationInfo ($id) {
        
    }
    
    /*
    * 获取公交线路id（没有！）
    */
    public function getBusRelationIds ($id) {
        
    }
    
    /*
    * 获取公交线路名（没有！）
    */
    public function getBusLineInfo ($id) {
        
    }
}
?>

```

###7.需要哪些 Dao（共需要9个，已有7个，还需2个）

* 1.二手房小区周边对应关系DAO（已有！）

```php
<?php
/*
* 二手房小区周边对应关系DAO
*/
class Community_Core_Comm_Dao_CommPlaceRelationDao extends Apf_Db_Dao_BaseDao {
    public function getDaoInfo() {
        
    }
}
?>

```

* 2.二手房地标词典DAO（已有！）

```php
<?php
/*
* 二手房地标词典DAO
*/
class Community_Core_Comm_Dao_PlaceDictDao extends Apf_Db_Dao_BaseDao {
    public function getDaoInfo() {
        
    }
}
?>

```

* 3.学校信息DAO（已有！）

```php
<?php
/*
* 学校信息DAO
*/
class Community_Core_Comm_Dao_SchoolDao extends Apf_Db_Dao_BaseDao {
    public function getDaoInfo() {
        
    }
}
?>

```

* 4.二手房小区周边DAO（已有！）

```php
<?php
/*
* 二手房小区周边DAO
*/
class Community_Core_Comm_Dao_PlaceInfoDao extends Apf_Db_Dao_BaseDao {
    public function getDaoInfo() {
        
    }
}
?>

```

* 5.地铁线路DAO（已有！）

```php
<?php
/*
* 地铁线路DAO
*/
class Community_Core_Comm_Dao_SwMetroStationsDao extends Apf_Db_Dao_BaseDao {
    public function getDaoInfo() {
        
    }
}
?>

```

* 6.地铁信息DAO（没有！）

```php
<?php
/*
* 地铁线路DAO
*/
class Community_Core_Comm_Dao_SwMetroInfoDao extends Apf_Db_Dao_BaseDao {
    public function getDaoInfo() {
        $daoInfo->tableName = 'sw_metros';
        $daoInfo->pkName    = 'id';
        $daoInfo->dbname    = 'anjuke_db'; 
    }
}
?>

```

* 7.公交线路信息DAO（已有！）

```php
<?php
/*
* 公交线路信息DAO
*/
class Community_Core_Comm_Dao_BusStationsDao extends Apf_Db_Dao_BaseDao {
    public function getDaoInfo() {
        
    }
}

?>

```

* 8.公交线路名DAO（已有！）

```php
<?php
/*
* 公交线路名DAO
*/
class Community_Core_Comm_Dao_CrawlNearbyNewBusLineDao extends Apf_Db_Dao_BaseDao {
    public function getDaoInfo() {
        
    }
}
?>

```

* 9.公交线路id DAO（没有！）

```php
<?php
/*
* 公交线路id DAO
*/
class Community_Core_Comm_Dao_BusRelationIdsDao extends Apf_Db_Dao_BaseDao {
    public function getDaoInfo() {
        $daoInfo->tableName = 'bus_relation';
        $daoInfo->pkName    = 'station_id';
        $daoInfo->dbname    = 'user_prop_db'; 
    }
}
?>

```