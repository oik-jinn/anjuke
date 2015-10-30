## 【TW】租房列表页For SEO 改版

### 项目背景

* 由于租房列表页改版，导致seo页面样式混乱，为提高用户体验，现将seo页面从java迁至PHP，
* pmt:http://p.corp.anjuke.com/project/detail?id=29247#task

### 使用到的seo接口
* 将dongwuyuangongy拼音转化为汉字接口：http://m.anjuke.com/seo/list?search_id=128136&city_id=11&debug=0，未找到线下地址
* 你是不是要找接口:http://m.anjuke.com/seo/list?search_id=128140&city_id=11&debug=0
* 相关推荐 http://m.anjuke.com/seo/list?search_id=128140&city_id=11&debug=0
* TW热门城市房产网：/home/www/workspace/user-site/app-user-touch/classes/user/touch/seo/SeoGroup.php


### 主要修该点

* 提sart线上路由，将线上入口指向user-site仓库(代码上线后执行)
* 修改路由配置/user-site/app-user-touch/config/route.php，将入口路由到正常列表页controller

```
    $config['mappings'][$prefix.'Zufang_Rent_List_RentList'] = array(
        '^/[a-z]+/rent/?',
        '^/[a-z]+/rent/([a-zA-Z]+.*)',
        '^/[a-z]+/zf/fang-([a-zA-Z]+.*)-d[0-9]+',
    );
```

* 修正ur方法

```
 /**
     * 处理当为seo方面的url时候，修正为正常列表页地址
     * @param $url
     * @return string
     */
    public function adaptUrl($url)
    {
        $fixed_url = $url;
        $pattern = '@^/[a-z]+/zf/fang-([a-zA-Z]+.)-(d[0-9]+)+@';
        if (preg_match($pattern, $url, $result)) {
            $fixed_url = '/' . $this->city_info['twdomain'] . '/rent/';
            $_SERVER['REQUEST_URI'] = $fixed_url;
        }
        return $fixed_url;
    }    
```
* 为复用正常列表页，修改组件user-site/app-user-touch/component/user/touch/seo/common/CommonSeo.phtml，使其适配seo特定页面不同的内链需求
* pc自动适配tw。
* 搜索无结果，同步请求结果。传到前台同步输出。
* 修改tkd文案 for seo
* 原pagename为seo_zf_normal_list
### 