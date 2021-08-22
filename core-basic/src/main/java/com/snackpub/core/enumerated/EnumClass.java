package com.snackpub.core.enumerated;//: enumerated/EnumClass.java
// Capabilities of the Enum class

import static com.snackpub.core.util.Print.print;
import static com.snackpub.core.util.Print.printnb;

enum Shrubbery { GROUND, CRAWLING, HANGING }

public class EnumClass {
  public static void main(String[] args) {
    for(Shrubbery s : Shrubbery.values()) {
      print(s + " ordinal: " + s.ordinal()); // ordinal返回int值，表示enum实例在声明时候的顺序，0开始，
      printnb(s.compareTo(Shrubbery.CRAWLING) + " "); // Enum实现了Comparable接口，同时还实现了序列化。
      printnb(s.equals(Shrubbery.CRAWLING) + " ");
      print(s == Shrubbery.CRAWLING); // 可以用==判断两个enum实例是否相等，编译器会帮助我们提供equals()和hashcode()
      print(s.getDeclaringClass());
      print(s.name()); // 返回enum声明时的名字
      print("----------------------");
    }
    // Produce an enum value from a string name:
    for(String s : "HANGING CRAWLING GROUND".split(" ")) {
      Shrubbery shrub = Enum.valueOf(Shrubbery.class, s); // Enum中定义的静态方法，根据给定的名字返回对应的实例，不存在则会抛出异常
      print(shrub);
    }
  }
} /* Output:
GROUND ordinal: 0
-1 false false
class Shrubbery
GROUND
----------------------
CRAWLING ordinal: 1
0 true true
class Shrubbery
CRAWLING
----------------------
HANGING ordinal: 2
1 false false
class Shrubbery
HANGING
----------------------
HANGING
CRAWLING
GROUND
*///:~
