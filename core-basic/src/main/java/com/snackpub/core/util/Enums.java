//: net/mindview/util/Enums.java
package com.snackpub.core.util;
import java.util.*;

/**
 * 从enum实例中进行随机选取，我们可以利用泛型，使得这个工作更一般化。
 */
public class Enums {
  private static Random rand = new Random(47);

  /**
   * 通过class对象得到enum实例的数组
   *
   * @param ec enum 实例
   * @param <T> 泛型
   * @return random return an enum instance.
   */
  public static <T extends Enum<T>> T random(Class<T> ec) {
    return random(ec.getEnumConstants());
  }
  public static <T> T random(T[] values) {
    return values[rand.nextInt(values.length)];
  }
} ///:~
