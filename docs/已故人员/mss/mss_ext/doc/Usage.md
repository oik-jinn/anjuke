业务划分
=================

## 一、 违禁词服务

+ 违禁词服务与 Infosec 共用一套数据存储，此处是 Infosec 的词库拉出存入到redis中，因此 **对违禁词的增、删请还使用 Infosec**，因为若调用 mss 服务，只是更新了 mss 词库（即redis内容），会下一次 Infosec 词库同步覆盖掉。

### infosec 词库增删改

+ 详情见页面： infosec.corp.anjuke.com
+ 词典下载： infosec.a.ajkdns.com/infosec/dict/download?type={{ type }}&source={{ source }}
+ 字典说明：
    + 分级(source)： 公安局等级为 1, 安居客问答等级为 2
    + 类型(type): 公安局类型为 11,安居客问答分 21,22

### mss 使用

#### 1. 使用违禁词服务

+ http://{{ URL }}/mss/match?dicname=banwords-dict&text={{ content }}
    + 测试地址: http://10.20.6.40/banwords/mss/match?dicname=banwords-dict&text=test

#### 2. 从 infosec 下载字典并重载

+ **下载完毕会立即更新内存中的字典树，非常耗时**
+ 字典更新。
  + http://{{ URL }}/reload/dict?dicname=banwords-dict&type=download

------------

## 二、 微聊违禁词

+ 微聊的词库比违禁词多了一些常用词，词库管理策略多了一份文件，见 [issue #14](http://gitlab.corp.anjuke.com/_incubator/mss/issues/14)

------------

## 三、 关键词搜索服务( **尚未部署** )
