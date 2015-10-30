# 主题找房设计文档

## 项目说明

* 项目背景：安居客二手房频道缺乏内容运营，需要新增一个“主题找房” 的产品运营模块
* 简单描述：通过运营模块，提供优质聚合内容，增加用户粘性，预期1期阅读量为3
* 具体功能 : 
    ```
    1. 二手房前台网站频道新增“主题”找房入口、定义新url、新增页面样式
    2. 二手房主导航栏增加“主题找房”频道，并增加new标示。
    3. 页面展示从db中读取的数据
    
    ```

## 主题找房相关url

 ```

* 列表页: http://www.anjuke.com/topic/

* 单页:http://www.anjuke.com/topic/{分类缩写}/{ID}/ 


 ```
 
## 主题找房的详细设计

### 相关的controller
 
 * 列表页(Ershou_Web_Topic_ListController)
 
 * 单页(Ershou_Web_Topic_ViewController)
 
### 配置

  * Nginx
  
  ```
  location ~ ^/topic/ {
              rewrite . /ajkuser-index.php last;
          }
  ```

  * Route
  
  ```
    * 列表页: 
     $config['mappings']['Ershou_Web_Topic_ListController'] =  array(
         '^/topic/$',
         '^/topic/([a-zA-Z]+)/$',
     );
  
    * 单页: 
     $config['mappings']['Ershou_Web_Topic_ViewController'] =  array(
         '^/topic/([A-Za-z]+)/([0-9]+)/'
     );
    
   ```
 
### 主题找房表设计  

db: user_prop_db

topic_type
```
CREATE TABLE `topic_type` (
 `type_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
 `name` varchar(100) NOT NULL default '' COMMENT '类型的中文全称',
 `city_id` smallint(5) UNSIGNED NOT NULL default '0' COMMENT '城市ID',
 `pinyin` varchar(100) NOT NULL default '' COMMENT '类型的拼音',
 `short_pinyin` varchar(100) NOT NULL UNIQUE default '' COMMENT '类型拼音的简称',
 `status` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否有效 1-有效 2-无效', 
 `update_time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '更新时间',
 PRIMARY KEY  (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主题类型表'
```

topic_info
```
CREATE TABLE `topic_info` (
 `topic_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
 `title` varchar(100)  NOT NULL default '' COMMENT '标题',
 `detail` varchar(200)  NULL default '' COMMENT '描述',
 `status` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否有效 1-有效 2-无效 ', 
 `post_time`timestamp  NOT NULL default '0' COMMENT '发布时间',
 `city_id` smallint(5) unsigned NOT NULL default '0' COMMENT '城市ID',
 `uid` varchar(100) NOT NULL default '0' COMMENT '作者id',
 `image_id` varchar(32) NOT NULL default '0' COMMENT '默认图文件名',
 `host_id` tinyint(3) unsigned NOT NULL default '0' COMMENT '默认图HostId',
 `type_id` int(10) unsigned NOT NULL default '0' COMMENT '类型ID',
 `update_time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '更新时间',
 PRIMARY KEY  (`topic_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主题基础信息表'
```   

topic_info_extend
```
CREATE TABLE `user_topic_extend` (
 `topic_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
 `content` text  NULL default '' COMMENT '文章内容',
 `update_time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '更新时间',
 PRIMARY KEY  (`topic_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主题扩展表'
```


## 主题找房相关模块

### 推荐

* 推荐的逻辑：

    从bi的接口里面直接拿数据，我们需要条数的2倍，补充逻辑不需要，bi会保证接口的返回数据的条数，所以不用走我们的补充逻辑。

* BI接口

 ```

 * 单页
 
 * 房源猜你喜欢:
 http://gitlab.corp.anjuke.com/_microdata/recommend-api-doc/blob/master/sale-pc-tpdetail-pro.md
 
 * 小区猜你喜欢:
 http://gitlab.corp.anjuke.com/_microdata/recommend-api-doc/blob/master/sale-pc-tpdetail-comm.md
 
 ```
 
 ```
 * 列表页
 
 * 房源猜你喜欢:
    http://gitlab.corp.anjuke.com/_microdata/recommend-api-doc/blob/master/sale-pc-tplist-pro.md
 
 * 小区猜你喜欢:
    http://gitlab.corp.anjuke.com/_microdata/recommend-api-doc/blob/master/sale-pc-tplist-comm.md
 
 ```
 
* ajax请求url

    ```
    
    v3/ajax/ershouviewrecommend/
    
    ```
* ajax 请求参数

    ```
    
    * 列表页:
    
    model_type: 推荐的类型,必须为4
    guid:       用户唯一标识
    city_id:    城市id
    
    ```
    
    ```
    
    * 单页:
    
    model_type: 推荐的类型,必须为3
    type:       文章的类型
    guid:       用户唯一标识
    city_id:    城市id
    
    ```
    
* 接口的返回格式

```

    {
    "status": "ok",
    "guess_favourite": [
        {
            "prop_id": "268466774",
            "title": "考虑考虑考虑考虑考虑考虑",
            "city_id": "11",
            "source_type": 1,
            "default_image": "http://b.pic1.ajkimg.com/display/anjuke/a25a7a8902bd6b6387ae17d6b9db2dd8/x.jpg",
            "prop_url": "http://shanghai.app-ershou-web.qigao-h213.dev.anjuke.com/prop/view/A268466774",
            "room_num": "1",
            "hall_num": 0,
            "prop_price": 208,
            "comm_name": "潍坊九村",
            "area_num": 100,
            "broker_id": "7790088",
            "comm_id": "5902",
            "soj": "&mahout=2&spread=proprec&position=1",
            "hp_type": 2
        },
      
    ],
    "guess_favourite_soj": {
        "v": "2.0",
        "propParam": "268466774|7790088|5902|2,268466614|7790059|82254|1,268466466|36139|1786|2,169439710|856596|440550|1,168949575|41|1588|1",
        "hp": 0,
        "recomm_data_type": 1,
        "recomm_type": 3,
        "entry": 2
    },
    "comm_recommend": [
        {
            "comm_id": "187",
            "city_id": "11",
            "comm_name": "天原二村",
            "area_name": "长宁",
            "region_name": "天山",
            "default_img": "http://images3.anjukestatic.com/community/20090415/9/10/85/90/9108590/240x180c.jpg",
            "price": "34107",
            "comm_url": "http://shanghai.app-ershou-web.qigao-h213.dev.anjuke.com/community/view/187"
        },
      
    ],
    "request_time": 4.214066028595
}

```
* 返回结果说明:

 
### seo

 * 城市房价

 * 城市二手房






