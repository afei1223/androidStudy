# ToJson

[博客链接](https://www.cnblogs.com/afei123/p/14298321.html)

## 用法

只需要将需要转为json的类继承ToJson即可，然后调用该类的toJson方法可以获得JsonObject对象

```java
public class TestClassClazz extends ToJson {
    TestClassMap testClassMap;
    TestClass testClass;
    TestClassList testClassList;

    public TestClassClazz(TestClassMap testClassMap, TestClass testClass, TestClassList testClassList) {
        this.testClassMap = testClassMap;
        this.testClass = testClass;
        this.testClassList = testClassList;
    }
}
```

```java
TestClassClazz testClassClazz = new TestClassClazz(test4(),test6(),test2());
JSONObject jsonObject = testClassClazz.toJson();
```

以上例子只是示范如何使用，可参见ToJsonActivity中的示例代码。

将ToJson文件导入并继承即可。



## 支持的数据类型

对一下数据类型即对应数组做了适配，其余java自带的类型，如果没有实现了toString的话，可能会出现哈希值的形式。

```java 
/**
* int Integer
* boolean Boolean
* float Float
* double Double
* long Long
* short Short
* byte Byte
* char
*
* String
* map hashmap
* List
* arrarylist
* queue
*/
```

自定义类也可以解析，如果该自定义类的包名不是java开头，会将内部参数一一分开存入JsonObject中。若是java开头，请更改源代码。（ps:源码中修改过一部分，这些类型是测过没有问题的）

## 特性?bug!

不知道算不算特性，就先列出来

1. 使用private修饰的参数无法获取到，可能对javabean类型的不是很友好。
2. 当参数为空时，会存入字符串“null”

## end

也不知道这算不算工具类，主要觉得class转json的话用到的地方可能还挺多的，简单点的话可以用接口实现，但是还需要自己实现，这种方式的话只要继承这个类就好了。
要是有帮助，万分希望能有个star
