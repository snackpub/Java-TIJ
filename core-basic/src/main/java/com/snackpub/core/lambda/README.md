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



*  结束语
保持每行代码都简短紧凑是一种不错的做法，但是走极端可能导致代码变得生硬难读。要提高表达能力，可以问自己代码是否容易理解。
要提高可读性，可采用 Java 8 垂直对齐各点的约定。使用这些简单技巧，就能创建出简洁、富于表达且可读的函数式代码。