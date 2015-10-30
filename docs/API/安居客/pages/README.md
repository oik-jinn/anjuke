## API整理—pages

### 说明
* pages是指api自己的一套存放html、js、css的仓库，主要用来做一些app的运营活动和app内部的一些h5页面等作用

### 代码仓库地址
* git@gitlab.corp.anjuke.com:_mobile-api/pages.git

### URL地址
* online:http://app.anjuke.com/m/

    ```
    eg:
    http://app.anjuke.com/m/wechat/broker/prop.html?brokerId=1558434&propId=21242973&from=singlemessage&isappinstalled=0
    ```
* pg:http://page.mp.dev.anjuke.com/

    ```
    eg:
    http://page.mp.dev.anjuke.com/wechat/broker/prop.html?brokerId=1558434&propId=21242973&from=singlemessage&isappinstalled=0
    ```

### 代码环境说明
* online
* pg

    ```
    服务器：
		ssh -p2222 code@app20-011.i.ajkdns.com
		密码：code
    代码路径：
		/data1/release/mobile-api/pages
    ```