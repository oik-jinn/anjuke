## TW跳转适配底层设计

#### 项目计划

>* TW跳转适配底层(ID:22582)
>   * 跳转
>       * build URL 迁移 (库)
>       * User Agent 判断 (拦截器)
>           * mobile 适配跳转 
>
>* 拦截器梳理(二期计划)
>   * 拦截
>       * 拦截器 优化
>       * set Guid
>       * landding
>       * 其他 拦截器

#### 梳理TW规则

* 现在的 tw url 构造，分 2 层

    * app-user-touch
        * classes/user/touch/util/BuildUrl.php (668)
            * 56 个方法，基本涵盖了基础、二手房、租房、用户中心等 url 的构造方法
        * classes/user/touch/util/build/BuildApiUrl.php
            * 提供 二手房、租房、商品、写字楼 api 的 url 构造方法
        * classes/user/touch/util/build/BuildErshouUrl.php
            * 提供 房价单页、房源列表、相似房源列表 url 构造方法
        * classes/user/touch/util/build/BuildHaozuUrl.php
            * 暂无方法
        * classes/user/touch/util/build/BuildShangyeUrl.php
            * 暂无方法

* 现在的 common url

    * app-user-common
        * classes/user/common/util/BuildUrl.php 
            * 主要提供图片 url 的构造方法
        * classes/user/common/util/url/BaseUrl.php 
            * 主要提供 base_url 的构造方法
        * classes/user/common/util/url/api/ \* .php
            * 主要提供 api url 的构造方法
        * classes/user/common/util/url/ershou/ \* .php
            * 主要提供 二手房相关 url 的构造方法
        * classes/user/common/util/url/user/ \* .php
            * 主要提供 用户中心相关 url 的构造方法
        * classes/user/common/util/url/zufang/ \* .php
            * 主要提供 租房相关 url 的构造方法

* 现有的 touch web 分发和 url rebuild 流程

    * ![传送门](http://gitlab.corp.anjuke.com/_site/docs/raw/master/DesignDoc/Public/Redirect/Topic_tw_2014015/old_process.png)

* 计划

   * 构造规则
       * 注释
           * 注明 url 的构造对应页面/功能
           * 提供范例方便直观查找
       * 复用原则
           * 需要编写新的 url 构造规则时，先检索最接近的url构造方法
           * 有复用关系的 url 构造方法最好依照复用关系排列
       * 可配置原则
           * 应该尽量避免构造 url 的方法中出现方法内字符串


#### 设计

* app-user-common/classes/user/common/util/ 目录结构设计

```
url/
    BaseUrl.php  #各类 base_url
    api/
        Touch.php  #api url
    community/  #小区相关 url
        ershou/
            community.php
        zufang/
            community.php
    ershou/
        Property.php  #二手房单页相关 url
        Sale.php  #列表 url (搜索、筛选)
    public/  #公用的或无法归类为api或频道的 url
        Tag.php
        Image.php
    shangpu/
        Shop.php  #商铺单页相关 url
        List.php
    user/
        Favorite.php  #收藏相关 url
        History.php  #历史相关 url
    xiezilou/
        Office.php  #写字楼单页相关 url
        List.php
    zufang/
        Rent.php  #租房单页相关 url
        List.php
```

文件名命名规则见[网站开发团队代码命名词表](http://git.corp.anjuke.com/_user_site/doc/issues/10)

* 库文件方法命名格式

build{频道}{设备}{Base,页面}{功能}Url

```
function buildEsfTouchPropUrl() {}
```

---

* 跳转设计
    * 映射方式处理多elseif的问题

```php
<?php 
// requires
/**
 + ------------------------------------------
 * 用于将 pc 的 url 转化为 touch 的 url
 * 主要方法 public function buildTouchUrl
 + ------------------------------------------
 */
class User_Common_MobileRedirect {

    // protecteds

    public function __construct() {
        $this->apf = APF::get_instance();
        $this->permanent = false;//是否走301跳转,true：是,false：否
        $this->touch_url = User_Common_Util_Url_BaseUrl::getCityTouchUrl();
        $this->current_url = $this->apf->get_request()->get_request_url();
        $this->host = parse_url($this->current_url, PHP_URL_HOST);
        $this->url_path = parse_url($this->current_url, PHP_URL_PATH);
        $this->match_config = $this->apf->get_config($this->getMatchConfigKey(), 'redirect');
    }

    /**
     * 主要方法，根据现有的pc url构造touch url
     * @return array
     */
    public function buildTouchUrl(){
        foreach ($this->match_config as $foo => $pattern) {
            if (preg_match($pattern, $this->url_path, $matches)){
                call_user_func(($this, $foo), $matches);
                break;
            }
        }
        if ($this->touch_url) { // User_Component_LayoutPage 为爬虫提供 touch url
            $this->request->set_attribute('tw_meta',$this->touch_url);
        }
        return array(
            'url' => $this->touch_url,
            'permanent' => $this->permanent
        );
    }

    /**
     * 经纪人单页url
     * pc http://sh.anjuke.com/prop/view/10086/
     * tw http://m.anjuke.com/sh/sale/10086/ 
     */
    private function jumpEsfPropView($matches=array()) {
        // $this->touch_url .= "sale/".$matches[1]."/";
        $this->touch_url .= User_Common_Util_Url_ERSHOU_SALE::buildEsfTouchPropUrl($matches[1]);
        $this->permanent = true;
        return true;
    }

    private function getDomainConfig() {
        return array(
            Const_Redirct::REDIRECT_SUB_USERCENTER => $this->apf->get_config('user_center_base_domain'),
            Const_Redirct::REDIRECT_SUB_XINFANG    => $this->apf->get_config('xinfang_base_domain'),
            Const_Redirct::REDIRECT_SUB_SHANGPU    => $this->apf->get_config('sp_base_domain'),
            Const_Redirct::REDIRECT_SUB_XIEZILOU   => $this->apf->get_config('xzl_base_domain'),
            Const_Redirct::REDIRECT_SUB_ZUFANG     => $this->apf->get_config('zu_base_domain'),
            Const_Redirct::REDIRECT_SUB_ERSHOUFANG => $this->apf->get_config('base_domain'),
        );
    }

    private function getMatchConfigKey() {
        $domain_config = $this->getDomainConfig();
        $sub_name = Const_Redirct::REDIRECT_SUB_ERSHOUFANG;
        foreach ($domain_config as $sub => $domain) {
            if (strpos($this->host, $domain)!==false) {
                $sub_name = $sub; 
                break;
            }
        }
        return Const_Redirct::REDIRECT_MOBILE.$sub_name;
    }
}

/**
 + ------------------------------------------
 * User_Common_MobileRedirect 对应配置
 * app-user-common/config/redirect.php (new)
 + ------------------------------------------
 */
$config['mobile_esf'] = array(
    'jumpEsfPropViewYz' => '/prop/viewyz/([0-9]+)/',
    'jumpEsfPropView' => '/prop/view/([0-9]+)/',
    'jumpEsfSaleMap' => '/map/sale/',
    'jumpEsfSale' => '/sale/',
    // and so on
    // User_Common_MobileRedirect 新增跳转方法和对应规则时，需要在此注册映射
    // ...
    'jumpIndex' => '/'
);
$config['mobile_zu'] = array(
    // ...
);
$config['mobile_us'] = array(
    // ...
);

/**
 + ------------------------------------------
 * Const_Redirct.php 用于配置 User_Common_MobileRedirect 的 key
 * app-user-common/classes/const/Redirect.php (new)
 + ------------------------------------------
 */
class Const_Redirct {
    const REDIRECT_SUB_ERSHOUFANG = '_esf';
    const REDIRECT_SUB_USERCENTER = '_us';
    const REDIRECT_SUB_XINFANG  = '_xf';
    const REDIRECT_SUB_SHANGPU  = '_sp';
    const REDIRECT_SUB_XIEZILOU = '_xzl';
    const REDIRECT_SUB_ZUFANG   = '_zu';

    const REDIRECT_MOBILE = 'mobile';
}

?>

```

* 通过switch case的方式解决设备类型判断的问题
