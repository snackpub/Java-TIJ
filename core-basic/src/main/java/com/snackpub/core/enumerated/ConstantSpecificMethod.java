package com.snackpub.core.enumerated;//: enumerated/ConstantSpecificMethod.java

import java.text.DateFormat;
import java.util.Date;

/**
 * 实现常量相关的方法，你需要为enum定义一个或多个抽象方法，然后为每个enum实例实现该抽象方法。
 *
 * 这通常也称为表驱动的代码（table driven code),去注意它与命令模式的相同之处。
 * @author snackpub
 * @date 2021/9/20
 */
public enum ConstantSpecificMethod {
  DATE_TIME {
    String getInfo() {
      return
        DateFormat.getDateInstance().format(new Date());
    }
  },
  CLASSPATH {
    String getInfo() {
      return System.getenv("CLASSPATH");
    }
  },
  VERSION {
    String getInfo() {
      return System.getProperty("java.version");
    }
  };
  abstract String getInfo();
  public static void main(String[] args) {
    for(ConstantSpecificMethod csm : values())
      System.out.println(csm.getInfo());
  }
} /* (Execute to see output) *///:~
