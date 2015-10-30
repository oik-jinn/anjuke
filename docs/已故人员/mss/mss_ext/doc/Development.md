## 快速启动

```
git clone git@gitlab.corp.anjuke.com:_incubator/mss.git
cd mss
mvn clean install
```

**注意 mss.properties 已经在项目根目录了，运行之前确保本地 Redis 已经部署好了，或者修改配置文件，指向外部 Redis。**

```
mvn jetty:run
```

如果 test 词库已经存在在 Redis 里面，就可以运行以下命令查询。

```
curl "http://127.0.0.1:8080/mss/search?dicname=test&text=created"
```

如果没有，则需要导入词典，之后才可以查询。

## 打包

```
mvn clean package
```

### mss-x.x.x-SNAPSHOT.jar

> You can find mss-x.x.x-SNAPSHOT.jar under target directory
