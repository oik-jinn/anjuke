百度闪投job信息  
=====================
------------------

####1 . job作用

```
将符合规则的物业的相关信息根据产品提供的相关规则生成xml
```
####2 . 执行时间

```
每天早上6：00执行，大概运行30分钟
```

####3 . 执行命令
```
php launcher.php Seo_SetBaiDuSEMXml --action=xiezilou_zu_old --page_num=2000 --part_by_city=1 --min_house_num=1 --city_ids=16
```
#### 4 . 数据
* 写字楼租：solr_property（非新盘非路有租价的含有写字楼属性的物业）
* 商铺：solr_property（非路有售价的含有商铺属性的物业）
* 生成xml地址：`/home/www/apixml/baidusitemap/jp_sem_shantou/`
* xml访问地址：
`http://shanghai.anjuke.com/apixml/baidusitemap/jp_sem_shantou/xzl_zu_16_1.xml`

####5 . 参数定义


参数|描述|值|
:---------------|:---------------|:---------------
city_ids| 默认所有允许投放闪投的城市|城市id用逗号隔开
part_by_city | 是否分城市|默认false
page_num | 每个xml文件最大的key的数目|默认5000
min_house_num| 所投放楼盘的最少房源数|默认最小为1，不能为0
action | 投放的楼盘类型|默认写字楼租，xiezilou_zu_old，写字楼租（二手盘），shangpu_shou_all，所有物业（新盘和二手盘）


####6 . xml中的参考格式
```
<urlset>
    <url>
        <loc>http://nj.xzl.anjuke.com/zu/?kw=华茂大厦城市高尔夫</loc>
        <data>
            <name>华茂大厦城市高尔夫</name>
            <sellerName> 安居客 </sellerName>
            <price>1</price>
            <title></title>
            <outerID>47003O</outerID>
            <city>南京</city>
            <category>鼓楼</category>
            <subCategory>龙江</subCategory>
            <logo>http://pages.anjukestatic.com/img/ajkglobal/2/new_logo_110x32.png</logo>
            <targetUrl>http://nj.xzl.anjuke.com/zu/?kw=华茂大厦城市高尔夫</targetUrl>
            <stock>1</stock>
            <priceUnit> RMB </priceUnit>
            <address>江东北路269号</address>
            <tags> 写字楼 </tags>
            <sellerSiteUrl>http://nj.xzl.anjuke.com/zu/</sellerSiteUrl>
            <image>http://pic1.ajkimg.com/display/jinpu/fa959739f97792f95dbac892aedf20a0/500x333c.jpg</image>
            <choice>
                <attribute>
                    <key>城市url</key><value>http://nj.xzl.anjuke.com/zu/</value>
                </attribute>
                <attribute>
                    <key>区域url</key><value>http://nj.xzl.anjuke.com/zu/gulou_nj       /</value>
                </attribute>
                <attribute>
                    <key>板块url</key><value>http://nj.xzl.anjuke.com/zu/longjiang/</value>
                </attribute>
                <attribute>
                    <key>业务</key><value>商业地产</value>
                </attribute>
                <attribute>
                    <key>租金</key><value>66元/平米/月</value>
                </attribute>
            </choice>
            <brand>南京城镇建筑设计院</brand>
        </data>
    </url>
</urlset>
```