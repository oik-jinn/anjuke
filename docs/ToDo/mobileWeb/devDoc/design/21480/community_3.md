#二手房房价SEO详细设计

###1.需求背景
    1.着重于增加新功能：地图+房源分布；小区单页大图交互修改。

###2.需求分析
* 新增本小区二手房源模块
    * 1.1.小区户型图
    * 1.2.小区房源推荐

###3.接口约定
* 小区户型图
    * 地址：http://m.anjuke.com/ajax/community/housepic/?comm_id={$comm_id}&house_num={$house_num}
    * 输入：
        * comm_id 小区id
        * house_num 户型（几室）
    * 输出：
        * status 状态 ok/failed
        * msg    状态信息 ok/参数有误
        * data   户型图列表信息

* 小区房源按照户型推荐列表
    * 地址：http://m.anjuke.com/ajax/community/houseprop/?city_id={city_id}&comm_name={$comm_name}&house_num={$house_num}
    * 输入：
        * city_id 城市id
        * comm_name 小区名字
        * house_num 户型（几室）
    * 输出：
        * status 状态 ok/failed
        * msg    状态信息 ok/参数有误
        * data   房源列表

###5.controller

* 1.小区户型图

```php
<?php
class User_Touch_Anjuke_Community_HousePicAjaxController extends User_Touch_AbstractController {
    
    protected function handleRequestInner() {
    
    }
}
?>

```

* 2.小区户型房源推荐

```php
<?php
class User_Touch_Anjuke_Community_HousePropAjaxControllerller extends User_Touch_AbstractController {
    
    protected function handleRequestInner() {
    
    }
}
?>

```