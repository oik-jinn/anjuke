##二手房&租房&商铺配置说明
---

### 说明

这里的配置是playground上的config和local的config,大家可以可以统一用这里的配置，pg环境的配置如有变动
可以直接在这里修改，方便他人使用。

* 二手房：
    * pg:ershou/pg/...
    * local:ershou/local/...
* 租房：
    * pg:zufang/pg/...
    * local:zufang/local/...
* 商铺：
    * pg:shangpu/pg/...
    * local:shangpu/local/...
    
（注：由于local配置其实已经在代码中，所以local是空的）


## 配置转换小技巧
如何在pg和local环境中快速切换呢？如果我们在开发里用的是local的配置，开发完成后想在pg环境中预先看一下，你还要将你顶层的配置全部替换成pg的，这样很麻烦，你可以如下操作：

* 定义代码运行环境：
    * 在网站根目录的config文件夹中增加APP_ENV文件，这里对应写的值对应的值为pg或local
    * 其中pg和local对应你不同环境配置的文件夹
    
* 修改你的index.php入口文件：
    * 加入定义的变量：
        * define("APP_ENV", trim(file_get_contents(WEB_PATH."config/APP_ENV")));
        * 用来读取APP_ENV中的运行环境
    * 修改$G_CONF_PATH：
        * 在这个数组后面增加一行APP_INDEX_PATH."config/".APP_ENV."/",
        * 根据APP_EVN定义的环境来确定加载哪种config