## TW二手房soj格式、发送方式

### soj来源

http://wiki.corp.anjuke.com/Sojourner_Log

### soj字段说明

Field|Description
---|---
**site**|site=anjuke来标记这条信息是统计，一个页面的soj消息只能有一条是site=anjuke，当前页再发送soj信息必须是soj=anjuke-npv,用来区分是非pv统计
**p**|page name,BI根据此值来区分是哪个页面的PV，当你换了某个页面名称是要通知BI部门
**pn**|page aliases name,如点击扣费参数二手房单页是Anjuke_View_Property
**cp**|custom parameters，这个可用来发送你自己定义的参数，以上示例中cp参数用来发送了房源点击数据
**guid**|用户唯一标识，不过期的cookie，用户首次来网站会种上此cookie，uv就是按照guid统计的

### TW二手房单页

* 发送方式

http://s.anjuke.com/stb?

* 发送数据

```
    p:Anjuke_Prop_View
    h:http://m.anjuke.com/sh/sale/A296152362/?isauction=0&position=3&equid=2015062394b50943-d7&reqid=B6872F2F-D38F-A44B-A771-579BC8B05FB8
    r:http://m.anjuke.com/sh/sale/
    sc:{"w":"1280","h":"800","r":"1"}
    site:m_anjuke
    guid:AE37768B-A2E2-7B86-6DE4-06FE42D978A5
    ctid:11
    luid:
    ssid:8C224A99-61F3-258C-FD1A-FA7029C024D6
    uid:0
    t:1435040653279
    pn:Anjuke_Prop_View
    cp:{"v":"3.0","channel":7,"userId":0,"userType":0,"tradeType":1,"proId":296152362,"COMMID":658621,"brokerId":1421298,"brokerType":2,"hpType":0,"entry":123,"datappc":"/sh/prop/click/296152362?isauction=0&referer=http://m.anjuke.com/sh/sale/&equid=2015062394b50943-d7"}

```
