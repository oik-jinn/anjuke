<?php
$config['servers'] = array (
    array('host'=>'10.20.6.107', 'port'=>'11214' , 'persistent' => true)
);

$config['orm_servers'] = array (
                array('host'=>'10.20.6.107', 'port'=>'11214' , 'persistent' => true)
);

$config['redis'] = array(
    'host'       => '192.168.1.170',
    'port'       => '6379',
    'timeout'    => 0,
    'persistent' => TRUE
);

$config['open_prop_redis'] = false;

$config['prop_redis'] = array(
        'host'       => '192.168.1.170',
        'port'       => '6380',
        'timeout'    => 0,
        'persistent' => TRUE
);

$config['prop_recommend_redis'] = array(
        'host'       => '192.168.1.170',
        'port'       => '9385',
        'timeout'    => 0,
        'persistent' => TRUE
);

$config['cookie'] = array(
    'host'       => '192.168.1.170',
    'port'       => '6379',
    'timeout'    => 0,
    'persistent' => TRUE
);
