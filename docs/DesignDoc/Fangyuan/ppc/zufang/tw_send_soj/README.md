## TouchWeb 租房单页发送soj流程

#### Step 1

* 单页请求`http://m.anjuke.com/sh/rent/click/45499541-3?isauction=2`

#### Step 2

* 在java封装请求移动api的必要参数，请求参数如下

```
id=>42355782,
uuid=>null,
isauction=>1,
equid=>,
user_id=>0,
user_type=>0,
brokerId=>null,
commId=>62202,
cityId=>11,
macid=>73580990-DB05-22DA-215C-21612AC7D907,
type=>3,
invalidtype=>0
```

#### Step 3

* 请求移动api

```
POST http://api.haozu.com/mobile/2.0/property.haopan_haozu_broker_prop_click?is_nocheck=1&from_module=touch_web
```

* ps

```
1. soj中的p及pn参数，在移动api中作为"好盘通点击量"静态方法调用，于代码中显示为固定值"View_Prop_YepPage_Zheng"
```

