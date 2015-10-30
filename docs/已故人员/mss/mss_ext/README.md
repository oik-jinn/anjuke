多关键词的文本精确匹配搜索（MSS）设计
=====

## 功能

MSS 即 Multiple String Search, 该服务会根据词库中的词源对目标文段进行匹配，返回
是否命中结果及命中词的相关信息。

### 关键词列表

```
# 关键字  相关信息（可选）
  安居客: 公司名称
  二手房: 通用名称
```

### 待查询文本(input)

```
安居客，是安居客集团旗下国内最大的专业二手房网站。自2007年上线至今的短短4年时
间里，安居客凭借其“专业二手房搜索引擎”的核心竞争力在业内独树一帜。通过对用户需
求的精准把握、海量的二手房房源、精准的搜索功能、强大的产品研发能力，为用户提供
最佳找房体验。目前，安居客的足迹已经遍布全国超过29个城市，注册用户超过1000万。
```

### 输出信息(output)

```
{
  "match": True,
  "details": [
   {"keyword": 安居客, "type": 公司名称, "start": 0, "end": 2},
   {"keyword": 二手房, "type": 通用名词, "start": 69, "end": 71},
   {"keyword": 安居客, "type": 公司名称, "start": 134, "end": 136},
   {"keyword": 二手房, "type": 通用名词, "start": 161, "end": 163},
   {"keyword": 二手房, "type": 通用名词, "start": 275, "end": 277},
   {"keyword": 安居客, "type": 公司名称, "start": 392, "end": 394}
  ]
}

```

----------------------

## **重要说明**

### MSS 应用到的几个业务

+ 敏感词(违禁词)过滤
+ 微聊敏感词过滤(比敏感词多出来一些词库)
+ 二手房、租房用来匹配关键词(比如小区名、学校名等)

### 实例与词典的关系

+ 每一个 mss 服务实例(ip:port 唯一确定一个实例)，可能管理有多个词典
    + 举例说明，二手房 mss 服务可能有
        + 北京二手房词典(anjuke_sale_12)
        + 上海二手房词典(anjuke_sale_11)
        + ...
    + 再举例说明，违禁词可能有
        + 公安局违禁词(banwords-dict)
        + 问答违禁词(wenda-dict)

----------------------

## Documents

### [WIKI](http://gitlab.corp.anjuke.com/_incubator/mss/wikis/home) (包含线上线下地址，状态监控等内容)

### [设计文档](doc/Design.md)

### [业务划分](doc/Usage.md)

### 使用者 API

+ [HTTP 方式调用](doc/UserHTTP.md)
+ [APS 方式调用](doc/UserAPS.md)

### 管理员 API

+ [HTTP 方式调用](doc/AdminHTTP.md)

### 开发相关文档

+ [快速开始及打包](doc/Development.md)

### 部署相关

+ [如何部署](doc/Deployment.md)
