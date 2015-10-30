# 写字楼出租出售列表页底部新盘推荐
### 开关:

```
/home/www/workspace/jinpu-site/app-common/config/multicity.php city_xinpan_open数组

$config['city_xinpan_open'] = array(
    Const_MultiCity::CITY_ID_SHANGHAI => true,
    Const_MultiCity::CITY_ID_BEIJING => true,
    Const_MultiCity::CITY_ID_GUANGZHOU => true,
    Const_MultiCity::CITY_ID_SHENZHEN => true,
    Const_MultiCity::CITY_ID_CHONGQING => true,
    Const_MultiCity::CITY_ID_CHENGDU => true,
    Const_MultiCity::CITY_ID_HANGZHOU => true,
    Const_MultiCity::CITY_ID_TIANJIN => true,
    Const_MultiCity::CITY_ID_NANJING => false,
    Const_MultiCity::CITY_ID_SUZHOU => true,
    Const_MultiCity::CITY_ID_QINGDAO => false,
    Const_MultiCity::CITY_ID_XIAN => true
);

```
### 获取数据流程:

```
使用Global_Ifx 异步请求例:http://sh.sp.anjuke.com/s?p=48&ci=11&f1=0&f2=1,该请求会路由到Ajax_Ad_IfxReplaceController.该控制器再请求ifx,获取数据返回

```