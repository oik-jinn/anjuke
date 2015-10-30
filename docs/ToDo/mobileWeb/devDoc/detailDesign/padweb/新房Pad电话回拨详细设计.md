### 新房Pad电话回拨详细设计

#### 城市对应座机号

配置文件路径：

app-aifang-core/config/ajkmulticity.php

调取格式举例：

上海座机号 = $config['city_set'][上海城市id]['citycode'];

#### 时间段配置类

用于配置当天的时间和时间段

app-aifang-pad/classes/util/CallbackTimeFieldPad.php

```php
<?php
class CallbackTimeFieldPad {
/* 时间段 */
public $this->day_times = array(32400, 43200, 54000, 64800);
/**
 * day_times 表示每个时间段的起点到当日0点的偏移(秒)，最后一个偏移为当日全部时间段的结束时间
 */
}
?>
```

构造一天的所有时段，返回一个数组
$date int 日期，默认为当天0点的时间戳

```php
<?php
public build_daily_time($date){
/**
 * 利用$this->day_times构造1天的时间段数组
 */
  $r = array();
  $cur_time = time();
/* 在循环中构造每个时间段的信息 */
  $times = $this->day_times;
  foreach($times as $k => $v) {
    if ($times[$k+1]){
      $r[$k]['time'] = $k;
      $r[$k]['start_time'] = $date + $v;
      $r[$k]['end_time'] = $date + $times[$k+1];
      if ($cur_time > $r[$k]['end_time']) {
        $r[$k]['status'] = 0;
      } elseif ($r[$k]['start_time'] < $cur_time && $cur_time < $r[$k]['end_time']) {
        $r[$k]['status'] = 1;
      } else {
        $r[$k]['status'] = 2;
      }
    } else {
      break;
    }
  }
}
?>
```

**变量键名** | **类型** | 说明
--- | --- | --- 
time | int | 一天的 时间段 序号
start_time | int | 时间段开始时间
end_time | int | 时间段结束时间
status | int | 时间段状态 0-灰色已过时，1-当前所处时间段，2-未来的时间段

返回数组的结构举例

```php
<?php
array(
    0 => array(
        'time' => 0,
        'start_time' => 1393203600,
        'end_time' => 1393214400,
        'status' => 0,
    ),
    1 => array(
        'time' => 1,
        'start_time' => 1393214400,
        'end_time' => 1393225200,
        'status' => 1,
    ),
    2 => array(
        'time' => 2,
        'start_time' => 1393225200,
        'end_time' => 1393236000,
        'status' => 2,
    ),
)
?>
```

调用方法举例

```php
<?php
$today = Util_CallbackTimeFieldPad::build_daily_time(strtotime("today"));

?>
```

#### 单页控制器

引用类

```php
<?php
apf_require_class('Util_CallbackTimeFieldPad');
?>
```

前端变量

**变量名** | **类型** | 说明
--- | --- | --- 
cur_time | int | 当前服务器的unix时间戳
time_fields | array | 今天和明天时间段的信息数组
city_code | string | 当前城市的座机号


#### ajax请求电话回拨

URL请求举例：

http://sh.fang.anjuke.com/ajax/callback400pad/?ctype=1&ptype=1&phone=18011118888&loupan_id=248893&time=1&callback=J.get.callback

用于接受传递的用户提交信息并返回处理状态和结果

**参数名** | **类型** | 是否必须 | 过滤 | 说明
--- | --- | --- | --- | ---
ctype | int | 是 | intval | 回拨类型，0-立即，1-时段
ptype | int | 是 | intval | 电话类型，0-座机，1-手机
phone | string | 是 | 仅数字正则 | 用户的电话号码，固话如"02161821155" / 手机号如"13671537253"
loupan_id | int | 是 | intval | 楼盘的id
time | int | 是 | intval | 时间段标记
callback | string | 否 | addslashes | js回调函数

**errorcode** | **message** | 备注
--- | --- | ---
'1101' | '缺少手机号码或楼盘ID' | phone 和 loupan_id 为空
'1102' | '手机号码格式错误' | 
'1103' | '您对此楼盘拨打次数过多，请稍后再试' | 
'1104' | '您拨打次数过多，请24小时后再试' | 
'1105' | 接口message | 详细见400接口相关表
'1106' | '固定电话格式错误' | 
'1107' | '错误的时间段' | 
'1108' | '未知的预约类型' | 
'1109' | '未知的电话类型' | 
'1201' | '缺少预约类型' | 
'1202' | '缺少预约时间段' | 
'1203' | '缺少电话类型' | 


楼盘预约免费电话举例：

```php
<?php
 apf_require_class('Aifang_Core_Bll_Phone_AutocallInterface');
 $bll = new Aifang_Core_Bll_Phone_AutocallInterface();
 $bll->set_params(
 	'from' => 'site',//各平台标志参考设置参数说明
 	'phone' => '13671537253',
 	'loupan_id' => 243159,
 	'guid' => 'AEC9DD3C-D1B4-AFFE-EB93-515E57F6DD89',
 	'reserve_time' => array('20140220120000','20140220150000')
 );
 $result = $bll->free_reserve();//返回结果参考返回参数说明
 /*
 成功返回：
 Array
(
    'success' => true,
    'errorcode' => null,
    'message' => null,
    'call_id' => 0,
    'reserve_id' => 123456,
    'start_time' => null,
    'end_time' => null
)
 失败返回：
 Array
(
    'success' => false,
    'errorcode' => '0008',
    'message' => '已有待处理预约',
    'call_id' => 0,
    'reserve_id' => 0,
    'start_time' => '20140221120000',
    'end_time' => '20140221150000'
)
 */
?>
```

#### 400电话时段预约接口

新文件路径：app-aifang-pad/controller/ajax/CallbackAppointment.php

```php
<?php
/* 引入必要类 */
class CallbackAppointment extends APF_Controller {
   /**
    * 400接口调用和接受ajax请求和返回结果，符合跨域调用要求
    */
}
?>
```

#### 400电话接口参数说明

**参数名** | **类型** | 是否必须 | 说明
--- | --- | --- | ---
phone | string | 是 | 纯数字字符串，主叫电话有区号的固话如"02161821155" / 手机号如"13671537253"
loupan_id | int | 是 | 楼盘ID
from | string | 是 | 接口调用，各平台标识，见下表
guid | string | 否 | 用户GUID
re_call_id | int | 否 | 预约关联来电的通话流水
ifreserve | int | 否 | 是否是预约，1-预约，0-实时
direction | int | 否 | 呼叫方向，1-先呼用户，0-先呼商户
reserve_time | array | 否 | 预约时段，array(起始时间点,截至时间点)，时间格式yyyymmddHHiiss

#### 400电话接口返回参数说明

**参数名** | **类型** | 说明
--- | --- | ---
success | boolean | true-成功，false-失败
errorcode | string | 失败错误码，多个原因由‘;’分割，见下表
message | string | 失败错误原因，多个原因由‘;’分割，见下表
call_id | int | 直接发起通话成功，返回通话流水，默认0
reserve_id | int | 预约通话成功，返回预约流水，默认0
start_time | string | 预约时段起始时间，格式yyyymmddHHiiss，默认null
end_time | string | 预约时段截至时间，格式yyyymmddHHiiss，默认null

**errorcode** | **message** | 备注
--- | --- | ---
'0001' | '主叫号码格式不正确' |
'0002' | '请稍后再操作' | 60s内同一手机同一楼盘已有同类操作
'0003' | '预约失败' | 
'0004' | '回拨失败' |
'0005' | '楼盘为空' |
'0006' | '来源错误' |
'0007' | '预约时段已过期' |
'0008' | '已有待处理预约' | 同一手机同一楼盘已有预约操作，且未处理会在返回参数start_time和end_time中给出预约的时段
'1000' | '操作失败' | 没有指定的其他失败

更多信息请参见：
http://git.corp.anjuke.com/aifang/design-doc/browse/master/developer/400/400%E7%94%B5%E8%AF%9D%E5%9B%9E%E6%8B%A8%E6%8E%A5%E5%8F%A3.md




