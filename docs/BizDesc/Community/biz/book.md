### 小区大全

#### 全国小区大全、租房小区大全，城市数据来源
```
$arr_city_sets = $obj_apf->get_config('city_set', 'multicity');
```

#### 特定城市下区域板块数据来源
```
$area_list = Util_Memcache::memcached('DAO_Area','getAreaByCityId',array($this->city_id),86400);
```

#### 特定城市下小区数据来源
```
$solr_comm = new Solr_Community();
$solr_comm->set_city_id($this->city_id);
$solr_comm->set_nh(FALSE);
$params = array();
$params[Const_Listing::PARAM_N_AREAID] =$this->region['TYPEID'];
$params[Const_Listing::PARAM_N_PAGE] = $this->page;
$solr_comm->set_query_params($params);
$result = $solr_comm->get_community_list(100);
$nums =$result['response']['numFound'];
$comms =$this->build_solr_data($result,$this->city_pinyin);
```