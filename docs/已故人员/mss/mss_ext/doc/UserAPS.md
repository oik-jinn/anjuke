User APS API
=================

## **词库匹配**

match(dicname, text)

```
参数：["banwords-dict", "testestest"]
结果：
    {
        dicname: "banwords-dict",
        match: "true",
        details:
        [
            {
                keyword:"test",
                start:0,
                end:3,
            },
            {
                keyword:"test",
                start:6,
                end:9
            }
        ]
    }
或者结果：
    {
        dicname: "banword-dict",
        match: False,
        details: []
    }
```

### Parameters

+ dicname (required) - 字符串类型, 字典名称。
+ text (required) - 字符串类型, 目标文段。

### Result

+ aps status: 200
+ aps reply body:
    + dicname, 字符串类型, 字典名称。
    + match, 布尔类型, 匹配与否结果。
        + True
        + False
    + details, list 类型, 匹配详情。
        + keyword
        + start
        + end

-----------

## **词库信息搜索**

search(dicname, text)

```
参数：["banwords-dict", "balabala"]
结果：
    {
        status: "ok",
        dicname: "banwords-dict",
        match: "true",
        details:
        [
            {
                keyword:"test",
                type:"ban",
                start:0,
                end:3
            },
            {
                keyword:"test",
                type:"ban",
                start:6,
                end:9
            }
        ]
    }
或者结果：
    {
        dicname: "banword-dict",
        match: False,
        details: []
    }
```

### Parameters

+ dicname (required) - 字符串类型, 字典名称。
+ text (required) - 字符串类型, 目标文段。


### Result

+ aps status: 200
+ aps reply body:
    + status, 字符串类型，请求成功与否。
    + dicname, 字符串类型, 字典名称。
    + match, 布尔类型, 匹配与否结果。
        + True
        + False
    + details, list 类型, 匹配详情。

-----------

## 多匹配

multiMatch(dict, text[])

```
参数：["banwords-dict", ["balabala", "balabala"]]
结果：[ match_reulst, match_reulst ]
```

-----------

## 多搜索

multiSearch(dict, text[])

```
参数：["banwords-dict", ["balabala", "balabala"]]
结果：[ search_reulst, seach_reulst ]
```

-----------

## 替换

replace(dict, text, replacement)

```
参数：　["banwords-dict", "testestest", "**"]
结果：　"**es**"
```

### Parameters

+ dicname (required) - 字符串类型, 字典名称。
+ text (required) - 字符串类型, 目标文段。
+ replacement (required) - 字符串类型，替换后内容

### Result

+ aps status: 200
+ aps reply body:
    + String 类型，替换后的结果

-----------

## 多替换

multiReplace(dict, text[], replacement)

```
参数：　["banwords-dict", ["testestest", "test"] , "***"]
结果：　["***es***", "***"]
```

### Parameters

+ dicname (required) - 字符串类型, 字典名称。
+ text[] (required) - 字符串数组, 目标文段。
+ replacement (required) - 字符串类型，替换后内容

### Result

+ aps status: 200
+ aps reply body:
    + String[] 类型，替换后的结果
