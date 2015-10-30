## PC二手房soj格式、发送方式

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


### PC二手房单页

* 发送方式

http://s.anjuke.com/stb?

* 发送数据

```
    p:Ershou_Web_Property_ViewPage
    h:http://shanghai.anjuke.com/prop/view/A296279944?from=structured_dict-saleMetro-salesxq&spread=commsearch_p&equid=201506238294095f-2b
    r:http://shanghai.anjuke.com/sale/?from=navigation
    sc:{"w":"1280","h":"800","r":"1"}
    site:anjuke
    guid:AE37768B-A2E2-7B86-6DE4-06FE42D978A5
    ctid:11
    luid:
    ssid:8C224A99-61F3-258C-FD1A-FA7029C024D6
    uid:0
    t:1435047728847
    pn:Anjuke_View_Property
    cp:{"v":"3.0","channel":1,"userId":0,"userType":0,"tradeType":1,"proId":296279944,"COMMID":6531,"brokerId":1649721,"brokerType":2,"hpType":1,"entry":3,"uniqid":"558917318f6b56.64235402","romar_item":"00000001460006531","equid":"201506238294095f-2b","userProId":"A296279944"}

```
