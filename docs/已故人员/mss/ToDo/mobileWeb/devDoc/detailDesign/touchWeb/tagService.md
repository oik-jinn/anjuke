#特色标签  Service 详细设计
##为什么需要这个 Service？
* 由于 新框架没有提供该服务 业务又需要该功能 

##特色标签 Service 有哪些标签？

* `特色标签`
    *  房屋 （如：主卧朝南、一梯两户）
    *  小区 （如：中心位置、近10号线）
    *  地铁 （如：紧邻 10号线龙柏新村）
    *  学区 （如：宛平中学）

##小区、房源标签

```php
<?php
class Ershou_Core_Service_BrokerPropertyTag extends Apf_BaseDaoService {
    /*
    * 获取经纪人 房屋标签 及 小区标签
    */
    public function getPropertyTags () {
        
    }
    
    /*
    * 获取 对应 TagId 的小区标签
    */
    public function getCommTags () {
    }
    
    /*
    * 获取 对应 TagId 房屋标签
    */
    public function getHouseTags () {
    }
    
}
?>

<?php
/*
* 特性标签DAO
*/
class Dao_Community_PropertyTag extends Apf_Db_Dao_BaseDao {
    public function getDaoInfo() {
        
    }
}
?>

```

##地铁标签
```php
<?php
class  Ershou_Core_Service_MetroTag extends Apf_BaseDaoService {
    /*
    * 获取经纪人 房屋 及 小区 标签
    */
    public function getMetroTags () {
        
    }
}
?>

<?php
/*
* 地铁相关DAO
*/
class Dao_Community_Metro extends Apf_Db_Dao_BaseDao {
    public function getDaoInfo() {
        
    }
}
?>

```

##学区标签
```php
<?php
class  Ershou_Core_Service_SchoolTag extends Apf_BaseDaoService {
    /*
    * 获取 学区 标签
    */
    public function getSchoolTags () {
        
    }
}
?>

<?php
/*
* 学区相关DAO
*/
class Dao_Community_School extends Apf_Db_Dao_BaseDao {
    public function getDaoInfo() {
        
    }
}
?>

```

##获取特色标签
###调用传参
**参数名** | **类型** | 是否必须 | 说明
--- | --- | --- | ---
commId | int | 是 | {小区ID}
isMetroTags | int | 否 | {默认获取 是否获取地铁标签 0 否 1 是}
isSchoolTags | int | 否 | {默认获取 是否获取学区标签 0 否 1 是}
brokerTags | array | 否 | {需获取经纪人特色标签时传该参数 参数格式见下面brokerTags说明}


####brokerTags 参数说明
**参数名** | **类型** | 是否必须 | 说明
--- | --- | --- | ---
brokerId | int | 是 | {经纪人ID}
proId | int | 是 | {房源ID}
isCommTag | int | 是 | {是否获取小区标签 0 否 1 是}
isHouseTag | int | 是 | {是否获取房屋标签 0 否 1 是}

```php
<?php
/*
* 获取特色标签公用 Service 
* 实现 房屋、小区、地铁、学区 标签的获取
*/
class  Ershou_Core_Service_Ershou_Core_Service_GetPropertyTags extends Apf_BaseDaoService {
   protected function getInitObjectMap() {
        return array();
   }
   
   /*
   * 获取特色标签 入口方法
   */
   public function getPropertyTags ($commId = 0, isSchoolTags = 1, brokerTags = array(), $isMetroTags = 1) {
   }
}
?>


```




