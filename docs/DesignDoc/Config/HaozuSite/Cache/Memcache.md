# Haozu-Site仓库Memcache业务整理

|名称                |作用                           |地址                                        |目前使用情况                |是否统一申请  |owner   | 目前大小 |新增 |是否需要持久化    |备注                       |comments|
|---                 |---                           |---                                        |---                        |---         |---     |---      |--- |---             |---                        |---     |
|servers             |app-haozu-jobs、DB缓存、Dao缓存 |[ms_servers][ms_servers]                   |共30G, 已使用约21G, 剩余30%  | Y          | 庞龙   |30G      |N    |Y              |需要拆分，拆分后可以废弃       |---     |
|orm_servers         |Orm缓存                        |[orm_servers][orm_servers]                 |共8G, 已使用6G,剩余约25%     | Y          | 庞     |8G       |N   |Y               | 迁移后可以废弃              |---     |
|broker_info_servers |经纪人信息cache                 |[broker_info_servers][broker_info_servers] |共5G, 已使用1G,剩余约80%     | Y          | 庞龙   |5G       |N   |Y               | 和二手房统一使用一套，可以废弃 |---     |


```
[ms_servers]:
$config['servers'] = array (                                                | 总大小 | 已使用 | 使用率 |
       array('host'=>'10.10.3.51', 'port'=>'11212' , 'persistent' => true), |  6G   | 4.36G | 72.7% |
       array('host'=>'10.10.3.51', 'port'=>'11311' , 'persistent' => true), | 3.9G  | 3.26G | 83.6% |
       array('host'=>'10.10.3.108', 'port'=>'11229' , 'persistent' => true),| 4.0G  | 3.20G | 79.9% |
       array('host'=>'10.10.8.125', 'port'=>'11215', 'persistent' => true), | 4.0G  | 2.01  | 50.0% |
       array('host'=>'10.10.8.125', 'port'=>'11216', 'persistent' => true), | 4.0G  | 1.33G | 33.3% |
       array('host'=>'10.10.3.108', 'port'=>'11238','persistent'=>true),    | 4.0G  | 3.43G | 85.6% |
       array('host'=>'10.10.3.108', 'port'=>'11239','persistent'=>true)     | 4.0G  | 3.31G | 82.8% |
);

[orm_servers]:
$config['orm_servers'] = array (                                            | 总大小 | 已使用 | 使用率 |
    array('host'=>'10.10.3.51', 'port'=>'11213' , 'persistent' => true),    | 6.0G  | 4.06  | 67.7% |
    array('host'=>'10.10.3.54', 'port'=>'11213' , 'persistent' => true)     | 2.0G  | 1.89G | 94.0% |
);

[broker_info_servers]:
$config['broker_info_servers'] = array (                                    | 总大小 | 已使用  | 使用率 |
    array('host'=>'10.10.3.106', 'port'=>'11215', 'persistent' => true),    | 4.0G  | 0.10G  | 2.5%  |
    array('host'=>'10.10.3.107', 'port'=>'11215', 'persistent' => true),    | 1.0G  | 0.085G | 8.5%  |
);
```

# zufang-usersite代码库Memecache整理

|名称                  |作用                           |地址                                     |目前使用情况                |是否统一申请  |owner    | 目前大小 |新增 |是否需要持久化    |备注     |comments|
|---                  |----                           |----                                    |----                       |---         |---      |---      |--- |---             |---      |---     | 
|dao_cache_group      |用户端Dao缓存(公共cache)         |[dao_cache_group][dao_cache_group]      | 12G, 已使用10.5G, 剩余10%  | Y           | xxx    |  12G     | N | Y               |缓存数据库| --       |
|servers              |公共默认cache                   |[servers][servers]                      | 43G, 已使用37G, 剩余15%    | Y           |xxx     |  43G     | N  | Y              |多方公用cache| --       |
|service_cache_group  |system底层Service分组           |[dao_cache_group][dao_cache_group]      | 共12G, 已使用11G,剩余8%     | Y          | xxx     | 12G     | N  | Y              |待所有频道的service_cache拆分完后，可以废弃|-- |

```
[servers]:
配置                                                                       | 总大小 | 已使用 | 使用率 |
$config['servers'] = array (                                               | ---   | ---   | ----  |
    array('host'=>'10.10.8.125', 'port'=>'11213' , 'persistent' => true),  |  4G   | 3.76G | 94%   |
    array('host'=>'10.10.8.125', 'port'=>'11214' , 'persistent' => true),  |  4G   | 3.76G | 94%   |
    array('host'=>'10.10.3.106', 'port'=>'11212' , 'persistent' => true),  |  4G   | 3.34G | 84%   |
    array('host'=>'10.10.3.170', 'port'=>'11212' , 'persistent' => true),  |  6G   | 5.51G | 92%   |
    array('host'=>'10.10.8.125', 'port'=>'11217' , 'persistent' => true),  |  2G   | 1.88G | 94%   |
    array('host'=>'10.10.8.125', 'port'=>'11218' , 'persistent' => true),  |  2G   | 1.88G | 94%   |
    array('host'=>'10.10.3.106', 'port'=>'11219' , 'persistent' => true),  |  2G   | 1.68G | 84%   |
    array('host'=>'10.10.8.125', 'port'=>'11219' , 'persistent' => true),  |  2G   | 1.87G | 93%   |
    array('host'=>'10.10.3.75', 'port'=>'11217' , 'persistent' => true),   | 4.88G | 4.60G | 94%   |
    array('host'=>'10.10.3.13', 'port'=>'11212' , 'persistent' => true),   |  4G   | 2.85G | 71%   |
    array('host'=>'10.10.3.13', 'port'=>'11213' , 'persistent' => true),   |  4G   | 3.04G | 72%   |
    array('host'=>'10.10.3.108', 'port'=>'11212' , 'persistent' => true),  |  4G   | 3.76G | 94%   |
);

[dao_cache_group]:
$config['dao_cache_group'] = array(                                         | 总大小 | 已使用 | 使用率 |
    array('host' => '10.10.3.107', 'port' => '11229','persistent' => true), |  2G   | 1.88G | 94%   |
    array('host' => '10.10.3.31', 'port' => '11215','persistent' => true),  |  4G   | 3.67G | 92%   |
    array('host' => '10.10.8.125', 'port' => '11223','persistent' => true), |  2G   | 1.88G | 94%   |
    array('host' => '10.10.3.106', 'port' => '11227','persistent' => true), |  2G   | 1.85G | 92.5% |
    array('host' => '10.10.3.170', 'port' => '11211','persistent' => true), |  2G   | 1.88G | 94%   |
);

[service_cache_group]:
$config['service_cache_group'] = array(                                          | 总大小 | 已使用 | 使用率 |
        array('host' => '10.10.3.107', 'port' => '11229','persistent' => true),  |  2G   | 1.88G | 94%   |
        array('host' => '10.10.3.31', 'port' => '11215','persistent' => true),   |  4G   | 3.67G | 91.8% |
        array('host' => '10.10.8.125', 'port' => '11223','persistent' => true),  |  2G   | 1.88G | 94%   |
        array('host' => '10.10.3.106', 'port' => '11227','persistent' => true),  |  2G   | 1.85G | 92.5% |
        array('host' => '10.10.3.170', 'port' => '11211','persistent' => true),  |  2G   | 1.88G | 94%   |
);

[seo_cache]:
$config['seo_cache'] = array(                                             | 总大小 | 已使用 | 使用率 |
    array('host' => '10.10.3.170','port' => 11213,'persistent' => true),  | 4G    | 0.59  | 14.8% |
    array('host' => '10.10.3.108','port' => 11237,'persistent' => true)   | 2G    | 1.06G | 53%   |
);
```







