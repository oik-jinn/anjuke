## APP公共参数说明

### 设备参数

|参数名称|类型|说明|
--- |--- |--- |---|
|cid|int|城市id，app根据定位得到的|11|
|app|string|app名字，安卓:a-ajk/ios:i-ajk|a-ajk|
|m|string|设备名称|
|o|string|系统名称|
|v|string|系统版本|
|cv|string|app版本，debug带ver|
|pm|string|安装包渠道号，默认为dev111111|
|from|string|请求来源，app都是mobile|
|macid|string|网卡地址，安卓唯一标识|
|uuid|string|设备标识|
|uuid2|string|设备标识|
|udid2|string|设备标识，ios设备唯一标识|
|i|string|设备标识|
|ostype2|string|操作系统类型|

### 签名参数

|参数名称|类型|说明|
|---|---|---|
|qtime |timestamp|发送请求时间，签名验证需要|

