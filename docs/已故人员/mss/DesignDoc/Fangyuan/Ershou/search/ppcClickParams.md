##PPC点击参数重构设计文档
---

### 背景

我们的房源点击到单页，会通过soj发送PPC相关房源参数，后台通过这些参数进行扣费等系列的操作，而这些点击扣费参数代码很久，是时候重构一下了，以适应新业务需求。

### 目标

* 解决之前点击扣费参数js报错导致部份参数丢失问题
* 让代码结构更清晰，方便其它频道的soj点击扣费的扩展

### 涉及到的业务
* 业务频道：二手房
* 上线城市：上海（随新框架中的房源单页同步推向全国）

### 点击扣费流程和参数说明：

 * 参数示例
 
```
pn:Anjuke_View_Property
cp:{"tradeType":"1","channel":"1","proId":"220529826","COMMID":"3006","brokerId":"1429363","brokerType":"2","uniqid":"539ecff6559810.38700328","userId":"6088876","userType":1,"scdstep":16,"hpType":1,"entry":3,"v":"2.0","romar_item":"00000901780003006"}
```

 * 参数发送流程 

```
点击房源单页
↓
php后端生成点击扣费参数
↓
前端引入soj框架的bb.js
↓
POST到s.anjuke.com
↓
POST到solr

```

 * 相关文档见：http://wiki.corp.anjuke.com/index.php?title=SOJ_for_%E5%A5%BD%E7%9B%98%E5%8D%8F%E8%AE%AE


#### 代码结构

 * 点击扣费基类
 
        app-user-common/classes/user/common/ppc/
        ├── User_Common_Ppc_Click（生成点击扣费的参数）
        ├── User_Common_Ppc_Rule（过滤规则）
        ├── User_Common_Ppc_Common（常用方法）
        └── User_Common_Ppc_Parameter（相关常量定义）
        
 * 二手房点击扣费类
        
        app-ershou-core/classes/ershou/core/ppc/
        ├── Ershou_Core_Ppc_Click
        ├── Ershou_Core_Ppc_Rule
                
 * 相关代码:http://git.corp.anjuke.com/site/user-site/browse/pmt-21405-site-anjuke_usersite/app-user-common/classes/user/common/ppc/Click.php)

 * 调用示例：

```
$ppcClick = new Ershou_Core_Ppc_Click();
$payPerClickParams = $ppcClick->getPayPerClickParams($prop,$broker);
User_Common_SojProvider::get_instance()->setCustomParams($payPerClickParams);

```



