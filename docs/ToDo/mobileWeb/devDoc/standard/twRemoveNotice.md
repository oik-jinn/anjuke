##分步迁移方案 - LB
* LB把所有TouchWeb的url转发到PHPweb服务器
* 根据URL在WEB服务器NGINX做分发（已经迁移到PHP的留下，未迁移的转发到JAVA服务器）
* WEB服务器NGINX git地址：
http://git.corp.anjuke.com/_user_site_ops/usersite-ops-ansible/browse/master/roles/touchweb/templates/tw.anjuke.com.conf
* 所有ajax统一出口

<pre>
    location /ajax/ {
        if ($cookie_ANJUKE_PHP_VERSION_USER ~ "^95[0-9_]+") {
            proxy_pass http://10.10.3.190:1700; #app10-137
            break;
        }
        add_header m-anjuke-proxy "php";
        proxy_pass http://m-anjuke-php;
    }
</pre>

##cookie测试方案 - app10-137
* LB根据cookie 9528_**_**分发到app10-137，否则分发到正常web服务器
* eg:

<pre>
        location / {
        if ($cookie_ANJUKE_PHP_VERSION_USER ~ "^95[0-9_]+") {
            proxy_pass http://10.10.3.190:1700; #app10-137
            break;
        }
        add_header m-anjuke-proxy "php";
        proxy_pass http://m-anjuke-php;
        proxy_temp_path /home/www/tmp/v2/temp;
    }
</pre>

##迁移URL的开发步骤 - SART
* 1、设计to do list：
* 2、发SART给张弦，立即执行app10-137NGINX修改，上线后执行web服务器修改（`禁止精确时间`）
* 3、check张弦执行app10-137更改，提测
* 4、`上线后`通知张弦执行WEB服务器nginx修改
* eg:

<pre>  
    hi,张弦
    因TouchWeb 新迁移了一个收藏/取消收藏功能 故需添加LB规则.具体规则如下
    增加
    location ~* ^/[a-z]+/sale/[0-9]+ {
        rewrite . /index.php last;
    }
    
    修改COOKIE环境app10-137的NGINX配置，立即生效。
    
    线上服务器NGINX修改：4月17日 17：00左右,具体执行时间再沟通!
    谢谢
</pre>
##新代码库注意事项
* 1、单例APF::get_instance()改用getInitObjectMap代替
* 2、ajax请求，写在相应业务目录，以Ajax结尾
* 3、严格项目设计(类图，类名，方法名，调用关系)，了解清楚业务，
* 4、测试之前找ray，view代码
* 5、自测：自测过后，跟线上做对比
* 6、传值给前端写在`固定元素`内，禁止乱写属性传值。
* 7、禁止写死url，统一用`buildurl`方法

##TW数据流图
![mahua](tw_data_flow.jpg)


