//: enumerated/Burrito.java
package com.snackpub.core.enumerated;

import static com.snackpub.core.enumerated.Spiciness.*;

/**
 * 使用 import static能够将enum实例的标识符带入当前的命名空间。这种方式如果不增加代码的复杂度，还是有好处的。
 * 在enum实例同一文件中这种技巧无法使用。
 *
 * @date 2021/8/22
 * @author snackpub
 */
public class Burrito {
  Spiciness degree;
  public Burrito(Spiciness degree) { this.degree = degree;}
  public String toString() { return "Burrito is "+ degree;}
  public static void main(String[] args) {
    System.out.println(new Burrito(NOT));
    System.out.println(new Burrito(MEDIUM));
    System.out.println(new Burrito(HOT));
  }
} /* Output:
Burrito is NOT
Burrito is MEDIUM
Burrito is HOT
*///:~
