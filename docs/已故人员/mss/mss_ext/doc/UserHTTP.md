User HTTP API
=========

## **词库匹配**

```
POST/GET /mss/match
```

### Parameters

+ dicname (required) - 字符串类型, 辞典名称。
+ text (required) - 字符串类型, 目标文段。(如果是post数据，需要对数据进行base64加码)

### Result(JSON)

+ status, 字符串类型。值为"ok","fail"。
+ dicname, 字符串类型。字典名称。
+ match, 字符串类型。匹配与否结果。
+ details, 字典类型, 匹配详情。

> + keyword : 字符串类型, 匹配词。
> + start : 整型, 起始位置。
> + end : 整型, 终止位置。

```
{
    status:"ok",
    dicname:"anjuke_prop",
    match:"true"
    details:
    [
        { 
            keyword:"安居客大学",
            start:1,
            end:36
        },
        {
            keyword:"安居客",
            start:6,
            end:32
        }
    ]
}
```

## **词库信息搜索**

```
POST/GET /mss/search
```

### Parameters

+ dicname (required) - 字符串类型, 字典名称。
+ text (required) - 字符串类型, 目标文段。(如果是post，需要对text进行base64加码。)

### Result(JSON)

+ status, 字符串类型, 值为"ok", "fail"。
+ dicname, 字符串类型, 字典名称。
+ match, 字符串类型, 匹配与否结果。
+ details, 字典类型, 匹配详情。

> + keyword : 字符串类型, 匹配词。
> + type: 字符串类型, 词描述。
> + start : 整型, 起始位置。
> + end : 整型, 终止位置。



```
{
    status:"ok"
    dicname:"anjuke_comm"
    match:"true",
    details:
    [
        { 
            keyword:"安居客大学",
            type:"常规词",
            start:1,
            end:36
        },
        {
            keyword:"安居客",
            type:"常规词",
            start:6,
            end:32
        }
    ]
}

```

or

```

{
    status: "ok",
    dicname: "touchweb",
    match: "false",
    details: [ ]
}

```
