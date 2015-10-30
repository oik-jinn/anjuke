Deployment
==========

## 打包

```
git clone git@gitlab.corp.anjuke.com:_incubator/mss.git
mvn clean package
```

## MSS 环境

```
cd mss/ansible
./bootstrap
ansible-playbook -i hosts.prod playbook.yml
```

## 部署的目录结构

```
/home/mss
  |----bin
  |     |----start.sh / stop.sh / restart.s   
  |     |----mss.jar
  |
  |----apps
  |      |--- banwords
  |      |        |-----conf
  |      |--- banwords-1(横向扩展)
  |      |--- banwords-2(横向扩展)
  |      |--- wechat
  |
  |----logs
  |----pids
```

## 部署应用的配置文件

+ $MSS_HOME/apps/$APP_HOME/conf/
    + logback.xml
    + mss.properties
        + Redis 的地址
        + 默认启动的 Dictionary
        + APS 配置文件


这里是一份线上配置文件

```
# Redis
redis.library=3
redis.ip=10.10.3.101
redis.port=6468

# Dicts load on start
# use ',' to split
dict=banwords-dict,wenda-dict,wenda-prompt-dict
banwords.dict=banwords-dict,wenda-dict,wenda-prompt-dict

# APS
aps.sp.ip=10.10.9.36
aps.sp.port=8965
```

## Deploy Mss Application

+ bash bin/start.sh -h
+ bash bin/stop.sh -h

```
bash bin/start.sh $APP_HOME 1.1.0-SNAPSHOT 8080
```
