# 判断爬虫访问

## http请求中的user agent(HTTP_USER_AGENT)
HTTP_USER_AGENT是用来检查浏览页面的访问者在用什么操作系统（包括版本号）浏览器（包括版本号）和用户个人偏好的代码。

## 各服务端语言都提供了相应的API 去获得user agent

### Javascript

```
//获取浏览器发送的userAgent信息  
var userAgentInfo = navigator.userAgent.toLowerCase();
document.write(userAgentInfo + '<br />');

/**
 * 输出运行的浏览器信息：
 * Chrome: mozilla/5.0 (windows nt 6.1; wow64) applewebkit/537.36 (khtml, like gecko) chrome/27.0.1453.94 safari/537.36
 * IE10: mozilla/5.0 (windows nt 6.1; wow64) applewebkit/537.36 (khtml, like gecko) chrome/27.0.1453.94 safari/537.36
 */ 
// 通过正则匹配获取浏览类型和版本
// 例如可以这样获取IE的
var agent = {};
if (window.ActiveXObject)
    agent.ie = userAgentInfo.match(/msie ([\d.]+)/)[1];
// 下面就输出信息
if(agent.ie)
    document.write(agent.ie);  // IE浏览器下输出浏览下版本号，其他浏览器没有输出信息
```

### PHP
$_SERVER 中存放着很多服务器的变量，其中$_SERVER['HTTP_USER_AGENT'] #当前请求的 User_Agent: 头部的内容。



## 问题

* 野蜘蛛爬取：

　１．    网络爬虫在发送http请求获取网页数据时也会在头部附加 User-Agent信息，特别注意的一点就是有些野蜘蛛 User-Agent信息为空，这样就需要在程序中做是否为空的判断，防止robots.txt 文件也对它的限制无效，导致不断的爬去你的网站。
可以向下面这样，判断到访问者的User-Agent为空，则返回404：

　２.说明（有兴趣可以自己查一下）
　robots.txt是一个协议，而不是一个命令。robots.txt是搜索引擎中访问网站的时候要查看的第一个文件。robots.txt文件告诉蜘蛛程序在服务器上什么文件是可以被查看的。
　
* user agent是可以被伪造的

解方法：通过请求者的ip对应的host主机名是否是搜索引擎自己家的host的方式来判断。


### 判断是否属于所列举的spider的方法：

```
function isSpider(){
    $ua = strtolower($_SERVER['HTTP_USER_AGENT']);
    if(!empty($ua)){
        $spiderAgentArr = array(
            "Baiduspider",
            "Googlebot",
            "360Spider",
            "Sosospider",
            "sogou spider"
        );
        foreach($spiderAgentArr as $val){
            $spiderAgent = strtolower($val);
            if(strpos($ua, $spiderAgent) !== false){
                return true;
            }
        }
        return false;
    } else {
        return false;
    }
}



```

## 官方给出的一些user agent信息：

百度：http://www.baidu.com/search/spider.htm

google：https://support.google.com/webmasters/answer/1061943

360：http://www.so.com/help/help_3_2.html

soso：http://help.soso.com/webspider.htm

sogou：http://www.sogou.com/docs/help/webmasters.htm#07

