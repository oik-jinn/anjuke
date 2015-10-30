#开发环境php所需扩展 mac
#### 2014-04-22 by chennyli

###系统需求

apache 2.2

	php module
	rewrite module
	macro module
php5.4

	memcahce
	redis
	zmq
	solr
	xhprof
	msgpack
	xdebug

git

svn (可选）
ZeroMQ

##### 配置ssh key
> ssh-keygen -t rsa -C "chennyli@anjuke.com"
> 
> cd ~/.ssh
> 
> ssh-add ~/.ssh/id_rsa
> 
> pbcopy < ~/.ssh/id_rsa.pub

### 上传ssh 到git
<http://git.corp.anjuke.com/settings/ssh>

### install apache mod_macro 
> wget http://people.apache.org/\~fabien/mod_macro/mod_macro-latest.tar.gz
> 
> tar xzvf mod_macro-1.2.1.tar.gz
> 
> cd mod_macro-1.2.1
> 
> apxs -cia mod_macro-1.2.1/mod_macro.c

### 如果编译出错  mac版本补充执行
> sudo ln -s /Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/ /Applications/Xcode.app/Contents/Developer/Toolchains/OSX10.9.xctoolchain 
>  apxs -cia mod_macro-1.2.1/mod_macro.c
>  
> vi /etc/apache2/httpd.conf # 开启模块  LoadModule macro_module       libexec/apache2/mod_macro.so
> 
> vi /etc/apache2/extra/httpd-vhosts.conf # 参考 httpd-vhosts.conf macro 配置
> 
> sudo apachectl restart

### 由于公司网络动态,动态绑定调试更便捷
绑定动态域名:<http://ddns.corp.anjuke.com/>

binddev域名:<http://ddns.corp.anjuke.com/dev.php>
### pull code
> sudo git clone git@git.corp.anjuke.com:shjjia/site
> 
> sh initialize.sh  #里面包含3个项目的代码
> 

##环境配置
mac 自带php 5.4.24 不再安装

扩展目录:/usr/lib/php/extensions/no-debug-non-zts-20100525
### php memcahce
> wget http://pecl.php.net/get/memcache-2.2.7.tgz
> 
> tar -zxvf memcache-2.2.7.tgz
> 
> cd memcache-2.2.7
> 
> phpize && ./configure --enable-memcache && make
> 
> sodu vi /etc/php.ini  #extension=memcache.so
> sudo apachectl restart


### php redis
> git clone git://github.com/nicolasff/phpredis.git
> 
> cd ./phpredis
> 
> phpize
> 
> make
> 
> make test
> 
> make install
> 
> sudo make install 
> 
> sudo vi /etc/php.ini #extension=redis.so
> sudo apachectl restart


### php zmq
> git clone git://github.com/mkoppanen/php-zmq.git
> 
> cd php-zmq
> 
> phpize && ./configure
> 
> make test
> 
> make install
> 
> sudo make install 
> 
> sudo vi /etc/php.ini #extension=zmq.so
> sudo apachectl restart

### php solr
> wget http://pecl.php.net/get/solr-1.1.0b.tgz
> 
>   tar xzf solr-1.1.0b.tgz
>   
>   cd solr-1.1.0b
>   
>   phpize
>   
>   ./configure 
>   
>  make
>  
>   make test
>   
>   sudo make install
>   
>   sudo vi /etc/php.ini
>   
>   sudo apachectl restart
### php Msgpack
> wget http://pecl.php.net/get/msgpack-0.5.5.tgz
> 
>   tar xzf msgpack-0.5.5.tgz
>   
>   cd msgpack-0.5.5.tgz
>      
>   phpize
>   
>   ./configure 
>   
>  make
>  
>   make test
>   
>   sudo make install
>   
>   sudo vi /etc/php.ini
>   
>   sudo apachectl restart
### php xhprof
>  wget http://pecl.php.net/get/xhprof-0.9.4.tgz
> 
>   tar xzf xhprof-0.9.4.tgz
>      
>   cd xhprof-0.9.2/extension/
>      
>   phpize
>   
>   ./configure 
>   
>  make
>  
>   make test
>   
>   sudo make install
>   
>   sudo vi /etc/php.ini 
>   
>   #extension=xhprof.so; #xhprof.output_dir=/tmp/xhprof
>   
>   sudo apachectl restart
>   
### php xdebug
> git clone git://github.com/xdebug/xdebug.git
> cd xdebug 
>     
>   phpize
>   
>   ./configure  --enable-xdebug
>   
>  make
>  
>   make test
>   
>   sudo make install
>   
>   sudo vi /etc/php.ini 
>   
>   #extension=xdebug.so;
>   
>   sudo apachectl restart
>   
### redis 服务端 可选
> wget http://download.redis.io/releases/redis-2.8.9.tar.gz
> 
> tar xzf redis-2.8.9.tar.gz
>
> cd redis-2.8.9

>make


#### redis 服务器启动

/Users/chenny/Downloads/redis-2.8.9/src/redis-server
#### redis 客户端启动
/Users/chenny/Downloads/redis-2.8.9/src/redis-cli