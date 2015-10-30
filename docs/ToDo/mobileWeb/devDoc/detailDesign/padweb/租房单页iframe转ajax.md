### 租房单页iframe转ajax

考虑到工时原因，本次设计采用与二手房类似的方式进行迁移，通过ajax请求租房单页，返回租房单页的全部页面信息，soj需要的信息将会放置在隐藏的input中。单页所需要的样式和脚本将包含在租房列表模块中。

建议在下一次代码维护时预留足够时间重新设计，ajax应该只传递必要的变量数据，静态html以及相关代码应该由展示页面自带，提高异步效率。

租房代码库包含用户中心租房浏览历史但不包含收藏页面，暂时保留在二手房代码库中的iframe展示方式，但是浏览历史将继续ajax化。

#### 租房soj数据

租房单页的soj数据将会写在隐藏的input标签中，控制器提供给前端的参数主要为 p, pn, h, cp。

通用参数表

**参数名** | **类型** | 是否必须 | 说明
--- | --- | --- | ---
p | string | 是 | {好租扣费使用}
pn | string | 是 | {好租扣费使用}
h | url | 是 | 访问地址{好租扣费使用}
cp | json | 是 | 定制参数{好租扣费使用}，详细见cstparam表
r | url | 是 | referer{好租扣费使用}
sc | json | 是 | 屏幕信息{"w":"1024","h":"768","r":"0"}
site | string | 是 | anjuke-pad
guid | string | 是 | guid
ctid | int | 是 | 城市id
luid | 
ssid | string | 是 | 会话id
uid | int | 是 | 登陆用户id
t | int(13) | 是 | 客户端时间戳



cstparam表，以下全部是必须参数

**参数名** | **类型** | 说明
--- | --- | ---
v | string | 协议版本信息
userId | int | 登录用户id
userType | int | 点击房源的登陆用户类型
proId | int | 房源id
COMMID | int | 房源小区id
brokerId | int | 经纪人id
brokerType | int | 经纪人类型
tradeType | int | 房源类型
channel | int | 渠道来源
entry | int | 点击房源来源展示点，整型值域[1-127]
hpType | int | 点击类型


pn列表，仅列出pad用

**房源单页名称** | **pn标识定义**
--- | ---
租房Pad-经纪人整租 | View_Prop_YepPage_ZhengPad
租房Pad-经纪人合租 | View_Prop_YepPage_HePad
租房Pad-个人整租房源 | View_Landlord_IndexPage_ZhengPad
租房Pad-个人合租房源 | View_Landlord_IndexPage_HePad

userType列表

**用户类型** | **用户类型标识**
--- | ---
未登陆用户 | 0
普通用户 | 1
经纪人用户 | 2


#### ajax访问单页

新增的单页请求参数

**参数名** | **类型** | 说明
--- | --- | ---
ajax | int | 0-非ajax请求(缺省)，1-ajax请求

个人房源
http://san.zu.anjuke.com/gfangyuan/23895818?ajax=1

#### 控制器

单页控制器逻辑，由判断referer调整为同时判断ajax参数，ajax参数缺省且referer不符合pad规则时跳转至列表页。

租房pad的soj cp 构造逻辑整合到class中，构造cp需要的变量在控制器中使用request的set_attribute传递。
app-haozu-pad/classes/soj/SojFactory.php

```php
<?php
apf_require_class("Soj_Parameter"); // soj参数 app-haozu-web/classes/soj/Parameter.php

class Soj_SojFactory {
  public static $instance = null;
  /* 单例 */
  public static function get_instance() {
    if (!self::$instance) {
	self::$instance = new Soj_SojFactory();
    }
    return self::$instance;
  }

  private $cutParam = array(
    'v' => '2.0',
    'userId' => 0,
    'userType' => 0,
    'proId' => 0,
    'COMMID' => 0,
    'brokerId' => 0,
    'brokerType' => 0,
    'tradeType' => 2,
    'channel' => 14,
    'entry' => 1,
    'hpType' => 0,
  );
  /* 构造cp */
  public function getCutParam(){}
}
?>
```









