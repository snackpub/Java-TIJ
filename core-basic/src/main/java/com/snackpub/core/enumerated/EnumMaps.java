//: enumerated/EnumMaps.java
// Basics of EnumMaps.
package com.snackpub.core.enumerated;

import java.util.EnumMap;
import java.util.Map;

import static com.snackpub.core.enumerated.AlarmPoints.*;
import static com.snackpub.core.util.Print.*;

interface Command { void action(); }

/**
 * EnumMap是一种特殊的Map，它要求其中的key必须来自一个enum。由于enum本身的限制，所以EnumMap在内部可由数组实现。因此
 * EnumMap的速度很快，我们可以放心的使用enum实例在EnumMap中进行查找操作。
 * 不过，put()方法和普通put()没什么区别。
 *
 * 下例子中演示了命令模式的用法。一般来说，命令模式首先需要一个只有单一方法的接口，然后从该接口实现了各自
 * 不同的行为的多个子类。  程序员就可以用构造命令对象，并在需要的时候使用他们。
 *
 * @author snackpub
 * @date 2021/9/20
 */
public class EnumMaps {
  public static void main(String[] args) {
    EnumMap<AlarmPoints,Command> em =
      new EnumMap<AlarmPoints,Command>(AlarmPoints.class);
    em.put(KITCHEN, new Command() {
      public void action() { print("Kitchen fire!"); }
    });
    em.put(BATHROOM, new Command() {
      public void action() { print("Bathroom alert!"); }
    });
    for(Map.Entry<AlarmPoints,Command> e : em.entrySet()) {
      printnb(e.getKey() + ": ");
      e.getValue().action();
    }
    try { // If there's no value for a particular key:
      em.get(UTILITY).action();
    } catch(Exception e) {
      print(e);
    }
  }
} /* Output:
BATHROOM: Bathroom alert!
KITCHEN: Kitchen fire!
java.lang.NullPointerException
*///:~
