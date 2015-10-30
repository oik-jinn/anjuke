# 二手房板块精选SOJ文档

`唐聪 2015-08-28`

## SOJ参数处理
- 添加hptype:4,标识为：竞价（优推精选），PC二手房列表曝光使用新参数
- 添加spread=blockfiltersearch ,标识：竞价（优推精选），PC二手房房源单页url
- 添加isauction:202,标识为：竞价（优推精选），TW二手房曝光和房源单页url

## PC二手房列表页SOJ曝光格式
```p:Ershou_Web_Property_List_FilterPage
h:http://shanghai.anjuke.com/sale/biyun/m2/
r:
sc:{"w":"1600","h":"900","r":"0"}
site:anjuke
guid:FEAB9751-AB21-D3D8-979A-717F8E906825
ctid:11
luid:
ssid:4D6F3458-713E-B1AA-9002-8D78E1D41B2E
uid:0
t:1440754685366
pn:Ershou_Web_Property_List_FilterPage
cp:{"found":141,"filter_search_type":1,"proids":"328352802|1652407|415|4|201508285c23e5cd-dc,321516573|769814|207|4|2015082856a325d9-4e,330266355|766879|208|4|201508287514c57a-83,324130871|38740|38|2|20150828deaa6fbc-77,325989638|530152|38|2|20150828d814a114-76","xf_proids":"","wuba_proids":"","keyword_search_type":"structured_dict","school_ids":[],"community_ids":[],"prop_expire":0,"hp":1,"entry":50,"v":"3.0","userId":0,"userType":0,"hpPage":"Anjuke_Hp_View"}
```

## PC二手房单页SOJ扣费格式
```p:Ershou_Web_Property_ViewPage
h:http://shanghai.anjuke.com/prop/view/A329859141?from=structured_dict&spread=blockfiltersearch&equid=201508288c29587d-a2
r:http://shanghai.anjuke.com/sale/pudong/
sc:{"w":"1600","h":"900","r":"0"}
site:anjuke
guid:FEAB9751-AB21-D3D8-979A-717F8E906825
ctid:11
luid:
ssid:4D6F3458-713E-B1AA-9002-8D78E1D41B2E
uid:0
t:1440756251562
pn:Anjuke_View_Property
cp:{"v":"3.0","channel":1,"userId":0,"userType":0,"tradeType":1,"proId":329859141,"COMMID":913,"brokerId":1721537,"brokerType":2,"hpType":4,"entry":50,"uniqid":"55e0320eeaa8c3.20043645","romar_item":"00000001460000913","equid":"201508288c29587d-a2","userProId":"A329859141"}
```

## TW二手房列表页曝光SOJ格式
```p:Exp_Anjuke_Prop_List
h:http://m.anjuke.com/sh/sale/pudong/a2000000_2500000-b0-0_0-0_0-0
r:http://m.anjuke.com/sh/sale/pudong/
sc:{"w":"1600","h":"900","r":"0"}
site:m_anjuke-npv
guid:FEAB9751-AB21-D3D8-979A-717F8E906825
ctid:11
luid:
ssid:4D6F3458-713E-B1AA-9002-8D78E1D41B2E
uid:0
t:1440756496968
cp:{"exposure":{"esf_id":[329301661,331437276,328496018,327961128,329659288,329444653],"isauction":[200,200,202,202,202,202],"pos":[6,5,4,3,2,1],"source_type":[0,0,0,0,0,0]}}
```

## TW二手房单页SOJ扣费格式
```p:Anjuke_Prop_View
h:http://m.anjuke.com/sh/sale/A328342296/?isauction=202&position=9&equid=20150828c61982eb-9d&reqid=D1E41059-EB81-7376-C5B4-BB843AB303A6
r:http://m.anjuke.com/sh/sale/pudong/a2000000_2500000-b0-0_0-0_0-0
sc:{"w":"1600","h":"900","r":"0"}
site:m_anjuke
guid:FEAB9751-AB21-D3D8-979A-717F8E906825
ctid:11
luid:
ssid:4D6F3458-713E-B1AA-9002-8D78E1D41B2E
uid:0
t:1440756671402
pn:Anjuke_Prop_View
cp:{"v":"3.0","channel":7,"userId":0,"userType":0,"tradeType":1,"proId":328342296,"COMMID":748828,"brokerId":1702396,"brokerType":2,"hpType":4,"entry":123,"datappc":"/sh/prop/click/328342296?isauction=202&referer=http://m.anjuke.com/sh/sale/pudong/a2000000_2500000-b0-0_0-0_0-0&equid=20150828c61982eb-9d"}
```