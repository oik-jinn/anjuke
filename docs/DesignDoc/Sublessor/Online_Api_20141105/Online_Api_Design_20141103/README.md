## 大业主房源api设计文档 ##

### 概要设计 ###

#### nginx 配置 ####

```
    location ~ ^/sublessor/* {
        include fastcgi_params;
        fastcgi_pass  unix:/var/run/php5-fpm.sock;
        fastcgi_param SCRIPT_FILENAME /vagrant/devel-config/indexes/sublessor/index-sublessor-api.php;
        fastcgi_param SCRIPT_NAME /vagrant/devel-config/indexes/sublessor/index-sublessor-api.php;
    }
```

#### url设计 ####

* domain

```
http://api.anjuke.com/sublessor/
```

* 获取房源信息

```
v1/rent/get
```

* 录入房源信息

```
v1/rent/add
```

* 修改房源信息

```
v1/rent/update
```

* 删除房源信息

```
v1/rent/delete
```

* 为房源上传图片

```
v1/photos/upload
```

* 修改默认房源图片

```
v1/photos/setdefault
```

* 删除房源图片

```
v1/photos/delete
```

* 推广与下架

```
v1/spread
```

#### 文件结构设计 ####

API统一入口controller，走工厂模式

#### 参数验证 ####

新增Biz类进行大业主相关参数验证

#### 约定与加密 ####

* 需要由服务端提供的相关参数

field | detail
--- | ---
appid | 经纪公司的唯一标识
appkey | 经纪公司密钥，与appid一一对应
sign | 签名，由请求api的参数与appkey计算得到


* 约定相关限制用户输入参数配置

[传送门](http://gitlab.corp.anjuke.com/_site/docs/blob/master/DesignDoc/Sublessor/Online_Api_20141105/Online_Api_Argument_20141105/README.md)

```
app-sublessor-web/config/house.php
```

* 约定小区表调取(solr 调取，以表的方式提供给客户端)

#### 接口文档 ####

略

---

### 详细设计 ###

#### index ####

```php
<?php
# index-sublessor-api.php
error_reporting(E_ALL & ~E_DEPRECATED & ~E_NOTICE & ~E_WARNING & ~E_USER_NOTICE);
$strHost=$_SERVER['HTTP_HOST'];
$arrHost=explode('.',$strHost);
if(3==count($arrHost)){
    echo "error";
    exit;
}else{
    $strLoadPath=$arrHost[1];
    $dev_name=$arrHost[1];
}
define('LOAD_PATH',$strLoadPath);
define('DEV_NAME',$dev_name);

define('APP_PATH', realpath(dirname(__FILE__) . '/../../../sublessor/app-sublessor-api') . '/');
define('APP_NAME', 'sublessor-api');
define('PAGE_NAME', $arrHost[1]);

require "./indexCommon.php";

$apf = APF::get_instance();
$apf->set_response_class('APF_Response');
$apf->set_request_class('Sublessor_Web_Request');
$apf->run();
?>
```

#### 目录结构 ####

```
app-sublessor-api/
    classes/
        sublessor/
            api/
                rent/
                    Get.php    # 获取房源信息
                    Add.php    # 添加房源信息
                    Edit.php    # 编辑房源信息
                    Delete.php    # 删除房源信息
                photo/
                    Upload.php    # 上传图片
                    SetDefault.php    # 设置默认图片
                    Delete.php    # 删除图片
                Base.php    # API处理基础类
    config/
        route.php    # 路由
    controller/
        sublessor/
            api/
                rent/
                    Base.php    # 统一控制器入口
                photo/
                    Base.php
    interceptor/
        sublessor/
            api/
                Auth.php
```

#### 拦截器设计 ####

* 主要功能：
    
    * 获取apikey与签名(sign)，验证访问权限

    * 记录大业主请求信息，包括url/请求类型/传递参数(json)/时间

    * 分配请求id，该id使用guid算法，跟随整个用户操作流程

* 关键算法：

```
# 签名算法
$sign = '';
$apiSecret = $this->getApiSecret();
$params = array(
    'apiKey' => $apiKey,
    'mobile' => $mobile,
    'method' => $method,
    'rent_id' => $rent_id
);
foreach($params as $k => $v) {
    if ($k != '' && $v != '') {
        $sign .= $k.$v;
    }
    $sign .= $apiSecret;
    $sign = strtoupper(md5($sign));
}
return $sign;

# request_id 算法
(User_Common_Util_Guid)->toString()
```

* DB-RT支持

    * 表`sublessor_opt_log`新增字段`request_id`

```
ALTER TABLE `actlog_db`.`sublessor_opt_log` ADD COLUMN `request_id` varchar(37) NOT NULL COMMENT 'api请求id' AFTER `updated`;
```


#### Base 设计 ####

以下对 `rent/Base.php` 进行示范

```php
<?php
# controller/sublessor/api/rent/Base.php
class Sublessor_Api_Rent_BaseController extends Apf_BaseController {
    protected $params;
    protected $requestType;
    protected $processName;
    protected function initParams() {
        $this->setParams();    # 设置提交的参数
    }
    public function handleRequestInner() {
        $this->setRequstType();    # 获取和设置请求类型
        $this->setProcessName();   # 根据 $this->requestType 匹配 classes/sublessor/api/rent/ 类名
        $this->switchProcess();    # 进入对应处理类
    }
}
?>
```


以下为 `classes/sublessor/api/Base.php` 基本结构

```php
<?php
abstract class Sublessor_Api_Base extends Apf_BaseService {
    protected $params;
    protected function run() {
        $this->getParams();
        $this->checkParams();    # 检查参数，需要继承实现
        $this->doProcess();      # 流程，需要继承实现
        return $this->output();  # 输出，需要继承实现
    }

    abstract protected function checkParams();
    abstract protected function doProcess();
    abstract protected function output();
}
?>
```


#### 主要流程整理 ####

* 获取房源信息

    * 数据来源：/* solr + mc / DB + service cache / */ DB

* 新增房源信息

    * 主要流程：(这里仅设计在验证全部通过时的流程，下同)

        * 如果标题与内容存在联系方式，记录联系方式log日志

        * 基本数据入库

        * 记录房源操作日志(api发布)

        * 返回房源id

```
<?php 
    # 出现联系方式的log日志
    (Sublessor_Core_Rent_Service_ConnectFilterLogService)->recordFilterLog(
                $prop_id, $user_id, $original_title, $original_desc);
    # 保存房源信息
    (Biz_Sublessor_Rent_PostService)->saveRent($ins_params);
    # 记录房源操作日志
    (Sublessor_Core_Rent_Service_OptLogService)->recordOptLog($log_params);
 ?>
```

* 编辑房源信息

    * 主要流程：

        * 如果标题与内容存在联系方式，记录联系方式日志

        * 基本数据更新

        * 记录房源操作日志(api编辑)

        * 若为推广中房源，执行上下架队列流程

        * 返回房源更新状态

```php
<?php
    # 编辑房源信息
    (Biz_Sublessor_Rent_PostBiz)->updateRent($upd_params);
?>
```

* 删除房源信息

    * 主要流程：

        * 执行下架流程

        * 更新房源状态

        * 记录房源操作日志(api删除)

        * 返回操作结果

```php
<?php
    # 删除房源
    (Biz_Sublessor_Rent_PropertyBiz)->deleteProperty($this->user_id, $this->prop_id);
?>
```

* 上传图片
    ```
    1.base64获取的图片资源,通过curl进行post
    2.url地址http://upd1.ajkimg.com/upload
    3.伪代码
    class Sublessor_Api_Photo_Action extends Apf_BaseService{
        public static function photoUplade($params){
            $bs64 = base64_encode($file);

            $ch = curl_init();
            curl_setopt($ch, CURLOPT_URL,'http://upd1.ajkimg.com/upload');
            curl_setopt($ch, CURLOPT_POST, 1);
            curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
            curl_setopt($ch, CURLOPT_POSTFIELDS, array('file' => $bs64));
            $ret = curl_exec($ch);
            curl_close($ch);
            //put $res hash value to DB
        }
    }
    ```

* 设置默认图片
    ```
        public static function setDefaultImg($params){
            //Update DB DATA
        }
    ```
* 删除图片
    ```
        public static function delImg($params){
            //Update DB DATA
        }
    ```

* 上下架

    * 主要流程：

        * 判断用户目标操作

            * 若用户需要上架，检查用户是否可以上架

            * 若用户需要下架，直接下一步

        * 发送上架/下架队列

        * 记录上下架队列返回的日志

        * 记录房源操作日志(api上架/api下架)

        * 返回上架/下架结果

```php
<?php
    (Biz_Sublessor_Rent_PublishBiz)->postZmq(Sublessor_Core_Const_Middleware::FLAG_XXX, $prop_id);
?>
```

#### 出参设计文档 ####

[传送门](http://gitlab.corp.anjuke.com/_site/docs/tree/master/DesignDoc/Sublessor/Online_Api_20141105/Online_Api_Output_20141105)

#### 修改历史 ####
```
2014-11-06
1. 修改了domain的url
2. 修改了房源api的url
3. 不再对mobile加密
4. 使用https方式传递数据
5. 签名sign在header中传递
6. 简化了sign算法
```