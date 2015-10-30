##房源对外接口
---

#### 二手房
    * 接口地址：http://www.anjuke.com/api/finding/
    * 示例：http://www.anjuke.com/api/finding/?city_id=11&kw=浦东二室
    * 参数：
        * 请求方式：GET
        * city_id: int 城市ID
        * kw：string 搜索关键字 
    * 返回
        * 返回格式：JSON
        * total_num: int 实际房源数量
        * del_word_total_num: int 删词后数量
        * kw_match_type：string 关键字匹配类型
        
#### 租房
* 接口地址：http://www.zu.anjuke.com/api/finding/* 示例：http://www.zu.anjuke.com/api/finding/?city_id=11&kw=浦东二室&result_type＝1
* 请求方式：GET* 基本参数说明：Field|Type|Description---|---|---city_id|int|城市ID
kw|int|关键字
result_type|int|1：房源基本数据，2：房源详情数据，推荐为1，2返回的数据太多

* 筛选参数说明：
Field|Type|Description---|---|---区域或版块|string|拼音,例pudong（浦东）
租金|zj+id|例:zj286（500元以下）
房型|fx+id|例:fx1（一室）
类型|lx+id|例:lx1（公寓）
装修|zx+id|例:zj1（毛坯）
合租|x2|
分页|p|例:p2 (第二页)

组装参数方式以字母加当前id，多个筛选项以‘-’隔开，具体参考site的租房列表页

* 返回格式：JSON* 返回字段说明：Field|Type|Description---|---|---total_num|int|房源数量
kw_match_type|string|关键字匹配类型
props|二维json|房源数据* 返回props字段说明：
Field|Type|Description---|---|---id|int|房源ID
pro_name|string|房源标题
city_id|int|城市ID
comm_id|int|小区ID
from|int|3:租房经纪人 2:二手房经纪人 0:好租个人
img_url|string|房源图片

* 其它说明：

    * 由于本接口是临时解决移动app搜索跟网站端不一致而提供的，所以只是将租房列表页数据输出，当调用参数result_type=2时返回数据太多，不再对字段依个说明，不建议用2这个参数

