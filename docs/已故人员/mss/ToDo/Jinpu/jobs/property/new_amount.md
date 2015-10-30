新盘房源数  
=====================
------------------

#### 1 . job作用

```
新盘新铺在线的租售房源数

```
#### 2 . 执行时间

```
脚本30分钟运行一次
```

#### 3 . 执行命令
```
php launcher.php Property_SendMsg Xin_Amount --action=rebuild
```
#### 4 . 数据

* 租售房源数表：jinpu_db.e_property_new_amount

* 新盘新铺表：e_property_new_info

####5 . 参数定义


参数|描述|
:---------------|:---------------|
id|楼盘id
action|rebuild（重新更新），rebuild_one（更新某个楼盘id）

### 备注

