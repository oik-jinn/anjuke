导航
==========
----------

### 导航信息接口

##### 1.api接口：

* `http://www.anjuke.com/api/nav/?cityId=14`

##### 2.接口返回数据格式：
```
说明:传cityId即可得到json格式的导航.示例如下
  
{

    "home": {
        "title": "首 页",
        "seoTitle": "首页",
        "route": "",
        "url": "http://beijing.anjuke.com/"
    },
    "fang": {
        "type": "fang",
        "title": "新 房",
        "seoTitle": "新房",
        "route": "",
        "url": "http://bj.fang.anjuke.com/",
        "navSecond": {
            "loupan": {
                "title": "新盘",
                "seoTitle": "新盘",
                "route": "loupan/",
                "url": "http://bj.fang.anjuke.com/loupan/"
            },
            "leading": {
                "title": "导购",
                "seoTitle": "导购",
                "route": "leading/",
                "url": "http://bj.fang.anjuke.com/leading/"
            },
            "tuangou": {
                "title": "团购",
                "seoTitle": "团购",
                "route": "tuangou/",
                "url": "http://bj.fang.anjuke.com/tuangou/"
            },
            "kanfangtuan": {
                "title": "看房团",
                "seoTitle": "看房团",
                "route": "kft/lookgroup/",
                "url": "http://bj.fang.anjuke.com/kft/lookgroup/"
            },
            "fangyuan": {
                "title": "房源",
                "seoTitle": "房源",
                "route": "fangyuan/",
                "url": "http://bj.fang.anjuke.com/fangyuan/"
            }
        }
    },
    "zu": {
        "zu": {
        "type": "zu",
        "title": "租 房",
        "seoTitle": "租房",
        "route": "",
        "url": "http://bj.zu.anjuke.com/",
        "navSecond": {
            "fangyuan": {
                "title": "房源",
                "seoTitle": "房源",
                "route": "fangyuan/",
                "url": "http://bj.zu.anjuke.com/fangyuan/"
            },
            "xiaoqu": {
                "title": "小区",
                "seoTitle": "小区",
                "route": "xiaoqu/",
                "url": "http://bj.zu.anjuke.com/xiaoqu/"
            },
            "ditu": {
                "title": "地图找房",
                "seoTitle": "地图找房",
                "route": "ditu/",
                "target": "_blank",
                "url": "http://bj.zu.anjuke.com/ditu/"
            }
        }
    },
    "sydc": {
        "type": "xzl",
        "title": "商业地产",
        "seoTitle": "商业地产",
        "route": "zu/",
        "url": "http://bj.xzl.anjuke.com/zu/",
        "navSecond": {
            "zu": {
                "title": "写字楼出租",
                "seoTitle": "写字楼出租",
                "route": "zu/",
                "url": "http://bj.xzl.anjuke.com/zu/"
            },
            "shou": {
                "title": "写字楼出售",
                "seoTitle": "写字楼出售",
                "route": "shou/",
                "url": "http://bj.xzl.anjuke.com/shou/"
            },
            "loupan": {
                "title": "楼盘",
                "seoTitle": "楼盘",
                "route": "loupan/",
                "url": "http://bj.xzl.anjuke.com/loupan/"
            },
            "shou_sp": {
                "title": "商铺出售",
                "seoTitle": "商铺出售",
                "route": "shou/",
                "url": "http://bj.sp.anjuke.com/shou/"
            },
            "zu_sp": {
                "title": "商铺出租",
                "seoTitle": "商铺出租",
                "route": "zu/",
                "url": "http://bj.sp.anjuke.com/zu/"
            },
            "wuye_sp": {
                "title": "物业",
                "seoTitle": "物业",
                "route": "wuye/",
                "url": "http://bj.sp.anjuke.com/wuye/"
            }
        }
    },
    "mf": {
        "title": "卖 房",
        "seoTitle": "卖房",
        "route": "maifang/",
        "url": "http://beijing.anjuke.com/maifang/"
    },
    "question": {
        "title": "问 答",
        "seoTitle": "问答",
        "route": "ask",
        "url": "http://beijing.anjuke.com/ask"
    }

}
```

### 各频道调用情况

####1. 商业地产：

* 有缓存，过期时间是一天（86400s），根据城市生成key

```
例：Bll_AjkApiBll:get_nav_infos_by_city_id(561114091e896de7fd337aa2059127b4)_V2
括号内是城市相关数据的加密串
```
* 接口配置：config['anjuke_nav_info']

* 可以通过
`http://sh.sp.anjuke.com/try/memcache` 来更新缓存。

####2. 好租：
* 有缓存，缓存一个小时，key值为md5值。

* 接口配置：$config['header_nav_api']

####3. 二手房：
* 没有使用接口，直接调用相关方法，没有缓存

