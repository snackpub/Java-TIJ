package com.snackpub.core.enumerated;//: enumerated/UpcastEnum.java
// No values() method if you upcast an enum

enum Search { HITHER, YON }

public class UpcastEnum {
  public static void main(String[] args) {
    Search[] vals = Search.values();
    // values()由编译器插入到enum定义中的static方法，所以你将enum向上转型为Enum，那么values就不可访问了
    Enum e = Search.HITHER; // Upcast
    // e.values(); // No values() in Enum
    // 即便Upcast转型没有values()，但是我们可以使用Class中有一个getEnumConstants()方法，我们可以通过Class
    // 对象获取Enum实例。
    for(Enum en : e.getClass().getEnumConstants())
      System.out.println(en);
  }
} /* Output:
HITHER
YON
*///:~
