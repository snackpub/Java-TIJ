# Stream 特性归纳
* 不是数据结构
* 它没有内部结构，它只是用操作管道从source(数据结构/数组/generator function/IO channel)抓取数据
* 它也绝不修改自己所封装的底层数据结构的数据。例如Stream的filter操作会产生一个不包含被过滤元素的新Stream，而不是从source删除那些元素
* 所有的Stream的操作必须以lambda表达式为参数
* 不支持索引访问
* 你可以请求第一个元素，但无法请求第二个，第三个，或最后一个。不过请参阅下一项
* 很容易生成数组或List
* 惰性化 （lazy）
* 很多Stream操作是向后延迟的，一直到它弄清楚了最后需要多少数据才开始。
* Intermediate 操作永远你是惰性化的。
* 并行能力
* 当一个Stream是并行化的，就不需要写多线程代码，所有对它的操作会并行进行的。
* 可以是无限的
    1. 集合有固定大小，Stream则不必。limit(n)和findFirst()这类的 short-circuiting操作可以对无限的Stream进行运行并很快完成。
    


## 提倡使用有帮助的编码
### java 8 约定令人惊喜的好处 
表达能力是函数式编程的优势之一
* Java 8 对于函数组合中的垂直对齐点的约定
```
// Java 8 中的函数组合
System.out.println(
 names.stream()
      .filter(name -> name.startsWith("J"))
      .filter(name -> name.length() > 3)
      .map(name -> name.toUpperCase())
      .collect(Collectors.joining(", ")))
// 各个点已在垂直方向上对齐，而且我们抵抗住了将多个条件组合到一个参数中的诱惑。
// 结果，每行都具有凝聚力：范围狭小而专注，仅含一个明确的任务.
```


* Predicate 谓词:在计算机的语言环境下，谓词是指条件表达式的求值返回真或假的过程


*  结束语
保持每行代码都简短紧凑是一种不错的做法，但是走极端可能导致代码变得生硬难读。要提高表达能力，可以问自己代码是否容易理解。
要提高可读性，可采用 Java 8 垂直对齐各点的约定。使用这些简单技巧，就能创建出简洁、富于表达且可读的函数式代码。

## 相关主题
* Oracle Java 8 官方文档对 java.util.stream package 的说明。
https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html
* Java 8 中的 Streams API 详解 https://www.ibm.com/developerworks/cn/java/j-lo-java8streamapi/