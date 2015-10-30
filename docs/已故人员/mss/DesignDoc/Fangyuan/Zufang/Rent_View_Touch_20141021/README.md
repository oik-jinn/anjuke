# tw租房单页迁移设计
---
![prd](http://gitlab.corp.anjuke.com/_site/docs/raw/master/DesignDoc/Fangyuan/Zufang/Rent_View_Touch_20141021/page_prd.png)
---
## 概要设计

### 逻辑需求整理

#### url部分

>* 客户分类
>
>   客户类型 | url参数 | isauction
>   --- | --- | ---
>   原二手房经纪人 | 1 | 0
>   套餐经纪人 | 3 | 1:ppc定价
>   好盘经纪人 | 3 | 2:ppc竞价;3:既是定价也是竞价
>   个人 | 2 | 0
>


#### photo模块

>* 返回逻辑
>   * 有refer以refer为准
>   * 无refer则跳回`租房首页`

>* 收藏五角星
>   * 右上角数字为收藏房源数
>   * 在点击下方收藏按钮时，数字自动加1
>       * 收藏失败怎么办?
>   * 点击按钮跳转至收藏页面的租房tab

>* 分享按钮
>   * 略

>* 预览图
>   * 无房源图片时展示蓝色背景的暂无图片默认图
>   * 最多10张图片，包含经纪人上传的所有图片
>   * 可左右滑动进行图片展示，滑动后对应图片的下方白点高亮
>   * 第一张不能右滑，最后一张不能左滑
>   * 暂无图片时，图片不可点击；存在图片时，点击进入大图模式
>* 大图
>   * 返回按钮
>       * 返回至租房单页
>   * 收藏按钮
>       * 收藏该房源/取消收藏房源
>   * 电话按钮
>       * 400电话，弹出拨打电话弹层
>       * 其他联系方式，手机拨号
>   * 图片分类
>       * 室内图
>       * 户型图
>       * 小区图
>       * 无类型(大业主)
>   * 附近租房推荐
>       * 4套房源，数据由推荐接口取得

#### 标题

>* 三色标签
>   * 整租
>   * 合租
>   * 个人

#### 详细信息

>* 略

#### 电话模块

>* 展示
>   * 辅助的灰色文字根据时段改变
>
>      时段 | 内容
>      --- | ---
>      24:00~08:59 | 好房不等人，做梦都在等您的电话呢，期待咨询
>      09:00~17:59 | 好房不等人，不如马上电话咨询房源详情吧
>      18:00~20:59 | 经纪人还在上班，好房不等人，期待咨询哦
>      21:00~23:59 | 好房不等人，期待您尽快来电咨询哦
>
>* 弹框
>   * 弹出电话拨打层(何时)
>   * 提示收藏弹框(何时)

#### 房屋配置

>* 略

#### 附近地铁站

>* 站点名、线路名、步行距离
>* 查看地图

#### 详细描述

>* 略

#### 小区信息

>* 略

#### 附近推荐
#### 看了还看
#### 位置及周边
#### 附近地铁房推荐

#### 经纪人/房东信息

>* 标题根据客户类型不同会有不同的文案
>
>   文案 | 客户类型
>   --- | ---
>   经纪人信息 | xx经纪人
>   房东信息 | 个人
>

#### 微聊板块

>* 根据不同的设备会有不同的交互
>
>   设备 | 交互
>   --- | ---
>   IOS | APP Store
>   Android | 直接下载安居客APP
>   微信浏览器 | 弹出提示浏览器打开
>

#### 面包屑

>* 略

#### 下一套

>* 列表页该房源的下一套

#### seo内链

>* 略

#### 底部悬停弹层

>* 根据客户类型不同会有不同的交互
>
>   * 微聊/收藏按钮
>
>      客户类型 | 交互
>      --- | ---
>      开通微聊的经纪人 | `微聊按钮`，弹出APP下载提示
>      未开通微聊的经纪人 | IOS:展示`收藏按钮`；    Android:展示`短信按钮`，点击时弹出短信页面，收件人为经纪人手机，收件内容为"你好，我在安居客上看到了这套房子{该房源url}，很感兴趣，请问房子还在出租吗？"
>      个人 | 展示`收藏按钮`
>
>   * 联系经纪人/房东按钮
>
>      点击按钮弹出电话拨打层
>
>      客户类型 | 按钮文案
>      --- | ---
>      xx经纪人 | 联系经纪人
>      个人 | 联系房东
>

---
## 详细设计

### 控制器

* 目录结构
```
app-user-touch/
    controller/
        user/
            touch/
                zufang/
                    rent/
                        public/   # 公用房源单页 (继承BaseView，负责通过Biz/Service获取展示类信息)
                            <b>View.php</b>
                        landlord/ # 个人房源单页 (继承Public_View，负责获取个人房源单页特有信息，如 soj)
                            <b>View.php</b>
                        yep/      # 经纪人房源单页 (继承Public_View，负责获取经纪人房源特有信息)
                            <b>View.php</b>
                        BaseView.php
```

* BaseView.php主要方法
```
protected function handleRequestInner() {
    // 获取房源基本信息(需要继承实现)
    $this->getPropInfo();
    // 获取经纪人信息(需要继承实现)
    $this->getBlockerInfo();
    // 获取小区基本信息(需要继承实现)
    $this->getCommunityInfo();
    // 获取小区周边信息
    $this->getCommunitySurroundingInfo();
    // 获取底部seo内链信息
    $this->getSeoFooterInfo();
    // 获取其他个性化信息(可以通过继承重写，默认返回true)
    $this->getOtherInfo();
    // 获取page文件，(需要继承实现)
    return $this->getViewPage();
}
```

* public/View.php 主要方法
```
protected setCityId() {}
protected setAreaCode() {}
protected setPropId() {}
protected setPropType() {}
protected setIsauction() {}
protected setCommId() {}
protected setBrokerId() {}
```

### 模块划分

    1. 图片模块（房源基本信息模块中提供图片信息）
        1.1 小图模块
        1.2 大图模块
    2. 标题模块
    3. 收藏模块
        3.1 收藏接口模块
        3.2 收藏展示模块（包含在图片模块、标题模块中）
    4. 房源信息模块
        4.1 基本信息模块
        4.2 详细信息模块
        4.3 房屋配置模块
    5. 小区信息模块
        5.1 基本信息模块的小区部分
        5.2 小区信息模块
        5.3 位置及周边模块
        5.4 位置及周边接口模块
    6. 客户信息模块（除专属模块外，还负责为图片模块提供客户电话数据）
        6.1 经纪人信息模块
        6.2 房东信息模块
        6.3 微聊模块
            6.3.1 微聊按钮模块
            6.3.2 微聊浮层模块
    7. 推荐模块
        7.1 附近推荐模块
        7.2 看了还看模块
        7.3 附近地铁房模块
    8. 面包屑模块
    9. 下一套模块
    10. seo内链模块
        11.1 附近小区推荐
        11.2 大家都在搜
        11.3 热门城市租房

 1. 图片模块(数据传入方式需要重构)
>* 建议数据传入结构重构，重构后二手房和租房都可方便调用该部件。
>* 估时 (4h ~ 8h)
>* Component : `User_Touch_Property_PhotoComponent`
>* Component参数和说明
>
>    参数 | 说明
>    --- | ---
>    image_list | 图片列表，结构见`image_list结构`
>    return_url | 返回按钮的url
>
>* image_list结构
>```
>    $image_list = array(
>        'images' => array(
>            1 => object( // 小区图
>                'host_id' => 1, // 兼容二手房传参
>                'file_name' => '163f7f0b502bef8d113670bb6111e135', // 兼容二手房传参
>                'img_url' => 'http://pic1.ajkimg.com/m/163f7f0b502bef8d113670bb6111e135/1035x500c.jpg' // 推荐使用传参，实际url以该参数为准
>            ),
>            2 => object(), // 室内图
>            3 => object(), // 户型图
>        )
>    )
>```
>
>* 图片列表逻辑说明
>  * 只显示10张图片，类型顺序为室内图、户型图、小区图
>  * 图片构造 User_Common_Util_Url_Public_Image::buildPropImgUrl(\$file_name, \$host_id, \$width, \$height)
>  * 图片规格 $width=562, $height=300
>


 2. 标题模块(改造SublessorTitle模块，大业主相关模块将会在迁移时使用)
>* 估时 (2h)
>* Component : `User_Touch_Property_RentTitle`
>* Component参数说明
>
>    参数 | 说明
>    --- | ---
>    prop_title | 房源标题
>    prop_type | {合租,整租,个人}
> 


 3. 收藏模块(建军已经封装过收藏接口，但是不支持租房收藏，需要增加租房兼容)
>* 估时 (6h)
>* 收藏按钮
>   * 由js请求当前收藏的信息后动态修改显示样式和交互
>   * 请求添加收藏的url:
>```
>http://m.anjuke.com/ajax/favorite?type=add&guid={guid}&fid={prop_id}&city={city_id}&pt={prop_type}&cn={cookie_name}
>```
>   * 请求取消收藏的url:
>```
>http://m.anjuke.com/ajax/favorite?type=del&guid={guid}&fid={prop_id}&city={city_id}&pt={prop_type}&cn={cookie_name}
>```
>   * 请求当前收藏的url:
>```
>http://api.anjuke.com/common/cookie/get/guid/{guid}
>```
>

 4. 房源信息模块
>* 估时 (16h)
>* 包括整个页面需要用到的房源信息
>* 主要Biz : `Biz_Zufang_Rent_RentBiz`
>* 主要Component : 
>   `User_Touch_Property_DetialRent`
>   `User_Touch_Property_Desc` (需要对入参进行重构)
>
>* Component`User_Touch_Property_DetialRent`参数说明
>
>   参数 | 说明
>   --- | ---
>   prop_base | 房源信息
>   user_info | 用户信息
>   area_name | 区域名
>   block_name | 板块名
>   comm_name | 小区名
>
>* Component`User_Touch_Property_Desc`参数说明
>
>   参数 | 说明
>   --- | ---
>   title | 详细说明左上角标题
>   desc | 房源详细信息
>
>* <span style="color:red">注</span>:房源详细信息在tw上需要去格式化，后端记住check
>

 5. 小区及周边信息模块(建议重构数据传入结构)
>* 估时 (4h ~ 8h)
>* Component : `User_Touch_Property_PropComm`
>* Component参数说明
>
>   参数 | 说明
>   --- | ---
>   commId | 小区id
>   clientParams | 包含城市缩写，用于build url
>   propview | 主要的房源信息，用于build url
>   includePrefix | 地图a标签中的prefix参数
>   entry | 控制是否显示小区信息，值不为'community'时显示小区信息块
>   completion_time | 有值时，显示竣工时间
>   prop_comm_info_type | 有值时，显示物业类型
>   baidumap_type | 地图对应的跳转url
>   baidumap_button_type | component右上角button对应的跳转url
>

 6. 客户信息模块
>* 估时 (4h)
>* 数据来源：房源信息中的经纪人信息
>* Component : `User_Touch_Property_RentBroker`<span style="color:red">new</span>
>
>   参数 | 说明
>   --- | ---
>   title | 左上角标题
>   broker_img | 客户头像
>   broker_name | 客户名
>   phone | 联系方式
>   company | 公司名
>   store | 门店名
>   warning | (灰色字)提示信息
>

 7. 推荐模块
>* 估时 (4h)
>* 推荐url
>```
>http://m.henrypang.dev.anjuke.com/ajax/rent/recommend/?city_id={city_id}&prop_id={prop_id}&type={prop_type}&metro_id={metro_id}&metro_station_id={metro_station_id}
>```
>* 推荐模块直接提供html代码，对应返回的推荐内容格式：
>
>   key | 说明
>   --- | ---
>   Nearby | 附近房源
>   Recommend | 看了还看
>   metroby | 附近地铁房
>
>* 换一批url
>   在推荐url基础上添加change参数，此时只提供Recommend数据
>
>```
>http://m.henrypang.dev.anjuke.com/ajax/rent/recommend/?city_id={city_id}&prop_id={prop_id}&type={prop_type}&metro_id={metro_id}&metro_station_id={metro_station_id}&change={1~1000}
>```
>

 8. 面包屑模块
>* 估时 (2h)
>* Component : `User_Touch_Property_Bcrumb`
>* 参数说明 :
>
>```
>$bcrumbs = array(
>   'url'=>'', // 面包屑item的url
>   'title'=>'' // 面包屑item名
>);
>```
>

 9. 下一套模块(数据传入方式需要重构/由js调用收藏接口实现数据展示)
>* 估时 (4h ~ 6h)
>* Component : `User_Touch_Property_NextPage`
>* 数据由收藏接口提供

 10. seo内链模块
>* 估时 (2h)
>* Component : `User_Touch_Seo_Common_FooterSeoA`
>* 数据调用方法 : User_Touch_Util_SeoTools提供相关方法
