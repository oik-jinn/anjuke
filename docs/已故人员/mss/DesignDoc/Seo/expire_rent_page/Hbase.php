<?php   
    /**
     * 使用实例
     * php Hbase.php 15824507 2 
     * 
    */


    function getPropertyHBaseKey($prop_id,$type){
        if($type == 2){
            $mod_id = $prop_id % 100;
        }elseif($type == 1){
            $mod_id = $prop_id % 100;
        }else{
            return FALSE;
        }
        return $mod_id .'-'.$type.'-'.$prop_id;
    }

    function buildKey($prop_id,$type){
        $config['hz_property_servers'] = array(
            'rest_api' => 'http://10.20.3.82:8080',
            //'rest_api' => 'http://10.10.8.2:80',
            'table_name' => 'prop:hz_property',
            'column' => 'info',
        );

        $key = getPropertyHBaseKey($prop_id,$type);

        $hBase_config = $config['hz_property_servers'];

        $api = $hBase_config['rest_api'] . '/'. urlencode($hBase_config['table_name']) . '/' . urlencode($key);

        if ($hBase_config['column']) {
            $api .= "/" . urlencode($hBase_config['column']);
        }
        return $api;
    }
    

    function doGet($api,$header = array('Accept' => 'application/json')){
        $curl = curl_init();
        curl_setopt($curl, CURLOPT_URL, $api);
        curl_setopt($curl,CURLOPT_POST,0);
        curl_setopt($curl,CURLOPT_RETURNTRANSFER,1);
        curl_setopt($curl,CURLOPT_NOSIGNAL,1);
        curl_setopt($curl,CURLOPT_HTTPHEADER,$header);
        $rs = curl_exec($curl);
        echo '<pre>';var_dump($rs);exit();
    }
    //15824507 2
    $api = buildKey($argv[1],$argv[2]);

    doGet($api);    