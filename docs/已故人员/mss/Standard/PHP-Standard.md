
# 规范
   规范不是特殊的个人风格，它对本地改良是完全开放的

## 优点
    程序员可以更好的了解代码码，弄清程序的状况
    新人可以很快的适应环境
    防止新接触php的人，自创一套风格并养成习惯
    防止新接触php的人一次次的犯同样的错误
    在一致的环境下，人们可以减少犯错的机会
 
## 缺点   
    标准降低了创造力
    标准在长期互相合作的人群中是没有必要的
    总之人们忽视标准

## PHP基础规范
  业界标准[PSR-0][PSR-0]、[PSR-1][PSR-1]、[PSR-2][PSR-2]
  [PSR-0]: https://github.com/php-fig/fig-standards/blob/master/accepted/PSR-0.md
  [PSR-1]: https://github.com/php-fig/fig-standards/blob/master/accepted/PSR-1-basic-coding-standard.md
  [PSR-2]: https://github.com/php-fig/fig-standards/blob/master/accepted/PSR-2-coding-style-guide.md

### 编辑器设置
    1. 字符集使用 UTF-8
    2. 缩进使用 4个字符的空格 ，不使用TAB制表符
    3. 使用`\n`作为行结束符


## 用户端开发规范

#### 一、PHP 命名规范

##### 1.1 目录的规范

    每个app下面的文件都要遵守目录的包结构，
    例如 app-user-core/下面的目录结构应该是app-user-core/classes/user/core/foo** 
    不能出现 app-user-core/classes/foo**
    项目内部的目录名，全部使用小写字母，如果有多个单词组成的直接拼写在一起。    
    如：datacenter等等

##### 1.2 文件名的规范

    类文件名的规范，采用首字母大写命名方式。
    如：GetNewsList.php。
    配置（非类文件）文件的命名由“小写字母”拼接组成。如：config.php。

##### 1.3  方法名的规范

    方法名都以动词开头，且首字母小写。其他单词首字母大写，方法名必须显式声明访问级别。
    如 ：public function getDataById () 。


##### 1.4 变量规范

    使用小写字母，并用“_”进行词的分割，变量命名需有实际意义。
    如：$data_list


##### 1.5 常量的约定

    常量均采用  “大写字母”  +  “_”来表示。
    如：APP_PATH。

##### 1.6 变量初始化
    
    所有用到的变量均要用该变量类型进行初始化  
   
#### 二、PHP 安全规范

##### 2.1 传参

###### 2.1.2 参数验证 

    传入的表单参数，必须在controller中进行验证

##### 2.1.3 URL规范

    URL中传递的参数采用小写加下划线分隔符的方式 **/foo/bar?param_foo=?&param_bar=?**

##### 2.2 对外API返回值必须是数组，必须有状态位 数据 输出信息

     return array(‘status’ => 0, ‘data’ => array(),‘msg’ => ‘显示给用户的错误信息’)

##### 2.3 访问控制

    1. 不希望外部访问该方法，那么用private；
    2. 如果希望子类能访问，不允许外部直接访问用protected；    
    3. 只有要开放给外部访问方法才用public；

##### 2.4 mysql安全

    1. 不准对数据做硬删除操作（特殊情况除外）
    2. update操作的where条件不准为空
    3. V2 中不准允许直接写SQL语句（job除外）
    4. sql语句写法，不允许条件值直接拼接，条件值使用“?”代替
    5. sql语句中不允许使用mysql的函数库，原因是会增加mysql运算压力和降低缓存使用率。

#### 三、PHP 性能规范

    1. 不准出现大块注释掉的代码  
    2. while循环不允许用while(true)｛break;｝  
    3. for,foreach 超过3层的，讨论尽量缩减层次


#### 四、V2框架下开发规范

    1. Controller层
        一个controller只做一件事（post/get除外）表单数据验证统一在controller中做

    2. Bll层
        Bll层决定怎么取数据，业务逻辑如何处理。
        Bll调用提供单例模式

    3. Dao层
        正常情况下不封装方法

##### 控制器规范
    1. 访问底层DB的方式 Controller->Service->Dao,控制器中不允许直接使用Dao
    2. 不允许直接new xxxService(),要使用继承的控制器提供的方法初始化;
    3. 必须继承 Apf_BaseController 或 Apf_ApiBaseController
    4. 不能出现public的方法、成员变量
    5. 声明的成员变量，如果是对象，必须使用@var 标记是什么对象
    
## 讨论
    许多项目的经验能得出这样的结论：采用编程规范可以使项目更加顺利地完成。 
