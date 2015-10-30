Admin API (增/删/改)
==========

## **列出词库**

```
GET /dict
```

## **重载词库**

```
POST/GET /dict/reload
```

### Parameters

+ dicname (required) - 字符串类型，辞典名称。
+ type (optional) - 字符串类型，重载方式，可以选择"download"（从 infosec 导入数据）。

### Result (JSON)

+ status, 字符串类型。值为"ok","fail"。
+ messages, 字符串类型。返回建立信息。

```
{
status:"ok",
messages:"reload successful"
}
```

----------------

## **删除词库**

```
POST/GET /dict/delete
```

### Parameters

+ dicname (required) - 字符串类型，字典名称。

### Result(JSON)

+ status, 字符串类型。值为"ok""fail"。

```
{
status:"ok"
}
```

----------------

## **录入词库**

### 逐词录入

```
GET /word/upload
```

#### Parameters

+ dicname (required) - 字符串类型, 辞典名称。
+ keyword (required) - 字符串类型, 关键词名称。
+ type (optional) - 字符串类型, 关键词相关描述。

#### Result (JSON)

+ status, 字符串类型。值为"ok","fail"。
+ messages, 字符串类型。返回导入信息。

```
{
status:"ok"
messages:"import successful"
}
```

### 文件录入

```
POST /word/upload
```

#### Parameters

+ dicname (required)
+ file (required) - 文本类型, 指代录入文本。(example:'虹景花园:347660,虹景花园,31.78427,119.99634,翠虹路')
+ append (optional) - 是追加/还是覆盖

#### Result

+ status, 字符串类型。值为"ok","fail", 以 JSON 格式封装。

```
{
status:"ok"
}
```

----------------

## **删除词**

### 逐词删除

```
POST/GET /word/delete
```

#### Parameters

+ dicname (required) - 字符串类型, 字典名称。
+ keyword (required) - 字符串类型, 关键词。

#### Result (JSON)

+ status, 字符串类型。值为"ok","fail"。
+ messages, 字符串类型。返回删除结果信息。

```
{
status:"ok",
messages:"delete successful"
}
```
