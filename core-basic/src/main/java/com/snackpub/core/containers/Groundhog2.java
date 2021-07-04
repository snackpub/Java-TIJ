package com.snackpub.core.containers;// A class that's used as a key in a HashMap
// must override hashCode() and equals().

/**
 * hashCode()并不需要总是返回唯一的标识码但
 * equals()方法必须严格的判断，两个对象是否相同
 */
public class Groundhog2 extends Groundhog {
  public Groundhog2(int n) { super(n); }
  public int hashCode() { return number; }
  public boolean equals(Object o) {
    return o instanceof Groundhog2 && // instanceof悄悄检查了此对象是否为null,并检查是否是Groundhog2的实例
            //
            (number == ((Groundhog2)o).number);
  }
} ///:~