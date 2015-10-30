### Pad二手房搜索/筛选零少结果推荐

#### 筛选零少结果推荐

1. 筛选项后跟用户筛选顺序，筛选项定义见下表

**参数** | **说明**
--- | ---
p1 | 区域板块
p2 | 价格
p3 | 房型


举个栗子：当用户选择顺序为 `价格` -> `房型` -> `区域板块` 时，区域板块的筛选url后跟参数为 `asc=p2_p3_p1`，请求回退逻辑时的期望数据为  `区域板块` <b>不限</b>，限定 `价格` 和 `房型`，且<span style="color:red">排除</span>选过的区域板块的房源。

2. 回退查找将会复制site原有的回退原理新增pad回退方法，主要对传入的筛选信息进行完全匹配，不再追加(*:*)的查找。一次回退匹配无法完成100套房源的情况下继续回退条件。下一次回退条件为所有筛选项不限时，停止回退逻辑，由前端请求，猜你喜欢和默认rank接口。

```php
<?php
//solr房源数
const PAGE_ROWS = 15;

//solr开始页
const PAGE_START = 0;

// 回退条件推荐
public static function retreatConditionPad($cityId, $searchParams, $without, $limit=0){

    $sParams = self::toSolrParams($searchParams, $without);

    $qCondition = array();
	
	// 区域版块
    if(!empty($sParams['areacode'])){
        $qCondition['areacode'] = 'area_code:'.$sParams['areacode'];
    }

    // 价格
	if(!empty($sParams['price'])){
        $qCondition['price'] = 'price:'.$sParams['price'];
    }

    // 房型
	if(!empty($sParams['room_num'])){
        $qCondition['room_num'] = 'room_num:'.$sParams['room_num'];
    }

    $q = urlencode(join(' AND ', $qCondition));

	$solrUrl = Search_SaleSearchSolr::getSolrBaseUrl($cityId);

    $sort = urlencode('order_string desc,score desc,rank_score desc');

    $solrUrl .="?start=".self::PAGE_START."&rows=".(self::PAGE_ROWS+$limit)."&sort=$sort&wt=json&q=$q";

    $data = Uri_Lucene::get_result_from_lucene($solrUrl);

    return array(
        'doc'=> empty($data['response']['docs']) ? array() : $data['response']['docs'],
        'solrUrl' => $solrUrl
    );
}

// 参数搜索转换为solr参数
private static function toSolrParams($sParams, $without){
    $q = array();
    //区域
    if(!empty($sParams['areacode'])){
        $q['areacode'] = $sParams['areacode'];
		if (strpos($without, 'areacode') !== false) {
			$q['areacode'] = '(-'.$q['areacode'].')';
		}
    }
    //价格
    if(!empty($sParams['price'])){
        $q['price'] = '['.$sParams['price']['lower'].' TO '.$sParams['price']['upper'].']';
		if (strpos($without, 'price') !== false) {
			$q['price'] = '(-'.$q['price'].')';
		}
    }
    //房型
    if(!empty($sParams['room_num'])){
        $q['room_num'] = current($sParams['room_num']);
		if (strpos($without, 'room_num') !== false) {
			$q['room_num'] = '(-'.$q['room_num'].')';
		}
    }
    return $q;
}
?>
```

原推荐接口逻辑所得的列表不包含二手房房源标签，需要添加标签方法

```php
<?php
/**
 * @param  array $props
 * @return array
 */
protected function get_tags($props,$resulttype=1){
    apf_require_class('Bll_Property_PropListInfo');
    $propListInfo = new Bll_Property_PropListInfo();
    $proids = array();
    if($props){
        foreach($props as $prop){
            $proids[] = $prop['ProId'];
        }
        $tags_data = $propListInfo->getListInfo($proids, $resulttype);
        foreach($props as &$prop){
            $prop['tags'] = isset($tags_data[$prop["ProId"]]) ? $tags_data[$prop["ProId"]]['tags']['housetag'] : array();
            foreach ($prop['tags'] as $k => &$v) {
                if (strpos($v, ',')!==false) {
                    $v = str_replace(',', '', $v);
                }
            }
        }
    }
    return $propData;
}
?>
```

回退接口新增参数$without，根据请求提供的用户筛选顺序构造的除外筛选项，用于推荐列表去重。


3. 筛选列表项后带参数逻辑

筛选项都包含自己的筛选项类型；

参数传递为筛选类型的顺序字符串，被点击时将自己所在的选项类型放在最前。

```php
<?php
/**
 * 根据当前的筛选项类型重组用户筛选顺序
 * @param  string $filter_class 传入的参数需要符合筛选项类型定义
 * @param  string $filter_asc   传入的参数为用户当前的筛选顺序，经过处理
 * @return string
 */
protected function buildFilterUrl($filter_class, $filter_asc) {
    $filter_url = '';
    if (strpos($filter_asc, $filter_class)) {
        $filter_url .= $filter_class;
    } else {
        $short_asc = str_replace($filter_class, '', $filter_asc);
        $filter_url = $short_asc . $filter_class;
    }
    return $filter_url;
}
?>
```

由于用户的筛选顺序写在url参数中，本身具有不安全性，所以传递的参数需要加工，重构顺序参数的方法如下

```php
<?php
/**
 * 重构筛选顺序
 * @param string $asc 获取的用户筛选顺序字符串
 * @return string 标准的顺序字符串
 */
protected function rebuildFilterParam($asc) {
    $filter_asc = '';
    $filter_arr = array();
    foreach ($this->filter_classes as $filter_class) {
        $pos = strrpos($asc, $filter_class);
        if ($pos !== false) {
            $filter_arr[$pos] = $filter_class;
        }
    }
    if (!empty($filter_arr)) {
        ksort($filter_arr);
        foreach ($filter_arr as $filter_class) {
            $filter_asc .= $filter_class.'_';
        }
        $filter_asc = substr($filter_asc, 0, -1);
    }
    return $filter_asc;
}
?>
```

筛选列表对应的状态和传递链接举例

**区域版块** | **价格** | 房型
--- | --- | ---
p1 | p2 | p3


选择价格筛选以后

**区域版块** | **价格** | 房型
--- | --- | ---
p2_p1 | p2(p2_p2) | p2_p3


选择房型筛选以后

**区域版块** | **价格** | 房型
--- | --- | ---
p2_p3_p1 | p3_p2(p2_p3_p2) | p2_p3


选择区域版块

**区域版块** | **价格** | 房型
--- | --- | ---
p2_p3_p1 | p3_p1_p2 | p2_p1_p3



#### 搜索零少结果推荐

* 小区词判断

Search_SaleSearchKeyword::getKeywordCategory($p_intCityId,$p_strKw,$p_intTime=100);

**参数** | **说明**
--- | ---
p_intCityId | 城市id
p_strKw | 格式化的搜索词
p_intTime | 设置超时


返回一个数组，匹配到时需要的值为$return['matchfield'][0]，匹配有效值为 commOne , commMulti，其余返回皆认为没有匹配到小区；


#### 数据需求

* 筛选加码

1. 零结果：?from=prop_filter_RecLess

2. 少结果：?from=prop_fliter_Recull

* 搜索加码

1. 搜索小区词零结果：?from=prop_searchcomm_RecLess

2. 搜索小区词少结果：?from=prop_searchcomm_RecNull

3. 搜索非小区词零结果：?from=prop_search_RecLess

4. 搜索非小区词少结果：?from=prop_search_RecNull
