### LB
##### 外网LB：`git@gitlab.corp.anjuke.com:_infra/ngx_conf_lb_external.git` (常用)
##### 内网LB：`git@gitlab.corp.anjuke.com:_infra/ngx_conf_lb_internal.git` (主要是a.ajkdns.com)

##### 为什么访问http://sh.zu.anjuke.com 和访问http://shanghai.anjuke.com，头信息中看到的服务器不一样？

#####为什么执行了http://shanghai.anjuke.com/version/switch?f1=9528_11_11之后 访问所有服务器，头信息显示的都是app10-137? 

##### 看以下代码，你应该就瞬间懂了。

    server {
        listen 80;
        server_name .zu.anjuke.com;

        location / {
            if ($cookie_ANJUKE_PHP_VERSION_USER ~ "^95[0-9_]+") {
                proxy_pass http://user-cookie;
            }
            proxy_pass http://user-anjuke-other;
        }
    }

    upstream user-cookie {
        server  10.10.3.190:1700; # app10-137
    #    server  10.10.9.59:1700; # xapp10-104
    }
    upstream user-anjuke-other{ 
        server  10.10.3.117:1700 weight=10; #app10-118
        server  10.10.3.161:1700 weight=20; #app10-200
        server  10.10.3.162:1700 weight=20; #app10-201
    }
##### 好了 写个for循环 curl100次 抓取头信息 头信息的是不是都是显示的app10-118 app10-200 app10-201？

##### 在这里这不一一举例了，Please 举一反三。。 不要问我为什么。。。please use google

##### 用户端&经纪人线上配置http://gitlab.corp.anjuke.com/groups/_online-config

##### 查看服务器使用情况等等 传送门:http://ops.corp.anjuke.com

##### 查看线上userlog http://ops.corp.anjuke.com/logger/
