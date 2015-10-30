#### 原pg：

##### web:xapp20-061（停止使用）
##### job: pg20-001（停止使用）

#### 变更为:
##### web:xapp20-069 
##### job:xapp20-091（环境php5.5，job命令与线上一致）

#### 变更后的PG环境，与线上环境相似度90%，能让大家更容易的了解线上的环境。

### 目录结构参考：[用户端线上环境](http://gitlab.corp.anjuke.com/_site/docs/blob/master/Ops/%E7%94%A8%E6%88%B7%E7%AB%AF%E7%BA%BF%E4%B8%8A%E7%8E%AF%E5%A2%83.md)
### 服务器上所有代码路径下，都有一个release目录（代表是master代码，每半小时自动更新）

### 举例：
#### 假设项目id为12345，新建项目时，选择仓库为anjuke,anjuke_usersite，
#### 那么新建项目后会生成：pmt-12345-site-anjuke pmt-12345-site-anjuke_usersite
#### 备注：如果本次项目修改user-site之后需要二手房单页和租房单页都能访问到最新代码，那么还需要选择zufang_usersite（1个分支代表一个目录）。
#### 可以点击deploy项目详情页中的漏选仓库，勾选缺失的仓库（自动创建分支）。
#### 最终哪些分支代码会同步到pg，是根据漏选仓库中勾选的（记得刷新项目详情页之后点击更新代码）。

### 访问规则：类似线上，差异为：url中.com变为.test
#### 本次pg兼容2种访问形式

#### 1、cookie形式访问：
#### 访问url: shanghai.anjuke.test 默认访问release代码，如需访问某个项目代码，点击（http://my.anjuke.test/ajkbroker/version/switch/ ，在user处输入：pmt-12345-site，点击切换）
#### 2、shanghai.pmt-12345-site.anjuke.test（原pg访问方式，如果服务器代码存在，则访问pmt-12345-site，不存在，则访问release）
#### 建议使用cookie形式，
#### 第2种访问方式主要是给未合并到master的api使用（http://site-api.pmt-12345-site.anjuke.test/esf/spread-info/?pro_id=233817339） 
#### touchWeb(java)，只支持cookie形式访问，需要自己手动加cookie（cookie名：TW_ID，内容为id，例如：123456，不区分是pmt还是hotfix）。
#### 如果TW_ID不存在，则默认访问8080端口。
#### 浏览器头信息中有一条：port:8080（当前访问的端口）

### jockjs
#### 1、fp01~fp10 site使用
#### 2、fp11~fp20 pad使用
#### 3、fp21~fp30 touch使用
#### http://deploy.corp.anjuke.com/update/ 这里更新jockjs之后会自动重启相应的Jockjs

### 查看当前页面所用代码：
#### 可以在浏览器头信息中查看
#### 类似：ajk:m=xapp20-069, v=pmt-12345-site
#### v代表的是当前访问的是那个项目

### job待测试 
#### 本次变动为：php版本升级为php5.5  job命令改为与线上一致
