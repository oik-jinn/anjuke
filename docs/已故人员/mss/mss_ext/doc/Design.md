Design
================

服务名称: **多关键词的文本精确匹配搜索** **mss**

------------------------------------

## 1. 使用者 API 设计

---------------------

### 1.1 **match(dicname, text)**

+ **参数**
    + dicname **required** 词典名称
    + text **required** 目标文段

+ **返回结果**
    + dicname
    + match
        + **true** 文本中 **包含有** 词典关键词列表中的任何一个关键词
        + **false** 文本中 **不包含** 词典关键词列表中的任何一个关键词
    + details
        + keyword
        + start
        + end

---------------------

### 1.2 **search(dicname, text)**

+ **参数**
    + dicname **required** 词典名称
    + text **required** 目标文段

+ **返回结果**
    + dicname
    + match
        + **true** 文本中 **包含有** 关键词列表中的任何一个关键词
        + **false** 文本中 **不包含** 关键词列表中的任何一个关键词
    + details
        + keyword
        + type
        + start
        + end

+ 举个返回结果的栗子

```
{
    dicname: "anjuke_comm",
    match: "true",
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

## 1.3 其他方法(暂时只提供给APS)

### 1.3.1 多次请求，一次性返回结果

+ multiMatch(dict, text[])
+ multiSearch(dict, text[])
+ multiDictMatch(dict[], text)
+ multiDictSearch(dict[], text)

结果为多个结果

```
[
    {
        dicname: "anjuke_comm",
        match: "true",
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
    },
    {
        dicname: "anjuke_comm",
        match: "false",
        details: []
    }
]
```

### 1.3.2 替换字符串

+ replace(dict, text, replacement)

```
"string1"
```

返回结果为替换后的字符串

### 1.3.3 多次替换字符串

+ multiReplace(dict, text[], replacement)

```
[
    "string1",
    "string2"
]
```

------------------------------------

## 2. 管理员 API

------------------------------------

## 2.1 **dict 操作**

------------------------------------

### 2.1.0 **list** 列出所有词库及相关信息

+ **返回结果**
    + 所有词典名及最后更新时间

### 2.1.1 **reload(dict[, type])** 重载词库

+ **参数**
    + dicname **required** 词典名称
    + type **optional**
        + banwords 可选 download，从 infosec 载入词库
        + 搜索词不需要该参数

+ **特殊说明**
    + 重载，即内存中的字典树会重新生成，该操作非常耗时

------------------------------------

### 2.1.2 **delete(dict)** 删除词库

+ **参数**
    + dicname **required** 词典名称

------------------------------------

## 2.2 **keyword 增删**

------------------------------------

### 2.2.1 **upload(dicname, keyword[, type])** 导入词

+ **参数**
    + dicname **required** 词典名称
    + keyword **required** 关键词
    + type **optional** 类型

+ **特殊说明**
    + 重载词库非常耗时，因此 upload 之后不会立刻重载

------------------------------------

### 2.2.2 **upload(dicname, file[, append])** 导入文件

+ **参数**
    + dicname **required** 词典名称
    + file **required** 文件
    + append **optional** 追加/或者是覆盖，true/false

+ **特殊说明**
    + 重载词库非常耗时，因此 upload 之后不会立刻重载

------------------------------------

### 2.2.3 **delete(dicname, word)** 删除词

+ **参数**
    + dicname **required** 词典名称
    + keyword **required** 关键词

+ **特殊说明**
    + 重载词库非常耗时，因此 delete 之后不会立刻重载
