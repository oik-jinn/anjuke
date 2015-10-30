php升级
1.download:
http://cn2.php.net/distributions/php-5.5.16.tar.gz
解压缩:
tar -xvf php-5.5.16.tar.gz
2.install:
./configure \
--prefix=/usr/local/php-5.5.16 \
--with-config-file-path=/usr/local/php-5.5.16/etc \
--with-mysql=mysqlnd \
--with-gd \
--with-jpeg-dir \
--with-png-dir \
--with-zlib \
--with-freetype-dir \
--enable-gd-native-ttf \
--enable-mbstring \
--enable-sockets \
--enable-fpm \
--with-curl \
--with-pdo-mysql=mysqlnd \
--with-apxs2=/usr/local/httpd-2.2.17/bin/apxs \

make

make install

3. 添加php.ini文件 
cp php.ini-production /usr/local/php-5.5.16/etc/php.ini
修改php.ini

expose_php = off
error_reporting = E_ALL
display_errors = On
short_open_tag = On
;log_errors = on
error_log = syslog
date.timezone = Asia/Shanghai
enable_dl = On

4. 扩展安装:
memcache 下载:
http://pecl.php.net/get/memcache-2.2.7.tgz
tar -xvf memcache-2.2.7.tgz
/usr/local/php-5.5.16/bin/phpize
./configure --with-php-config=/usr/local/php-5.5.16/bin/php-config 
make
sudo make install
echo php.ini

redis 下载:
http://pecl.php.net/get/redis-2.2.5.tgz
amqp:(dl plz)
http://pecl.php.net/get/amqp-1.4.0.tgz
zmq:
http://pecl.php.net/get/zmq-1.1.2.tgz
msgpack:
http://pecl.php.net/get/msgpack-0.5.5.tgz


代码中需要注意的地方:
1.
调用时传引用参数会报错.
/anjuke/app-anjuke/controller/finding/Sale.php Line:246
2.
继承时参数不一致导致E_STRICT
3.
废弃的函数:
define_syslog_variables()
ereg() => preg_match()
...



