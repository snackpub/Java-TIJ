package com.snackpub.core.enumerated;//: enumerated/OverrideConstantSpecific.java

import static com.snackpub.core.util.Print.*;

/**
 * 除了实现enum中的abstract方法之外，我们也可以覆盖该方法
 *
 * @author snackpub
 * @date 2021/9/20 21:35
 */
public enum OverrideConstantSpecific {
  NUT, BOLT,
  WASHER {
    // 重写方法
    void f() { print("Overridden method"); }
  };
  // 默认的行为
  void f() { print("default behavior"); }
  public static void main(String[] args) {
    for(OverrideConstantSpecific ocs : values()) {
      printnb(ocs + ": ");
      ocs.f();
    }
  }
} /* Output:
NUT: default behavior
BOLT: default behavior
WASHER: Overridden method
*///:~
