package com.snackpub.core.enumerated;//: enumerated/NonEnum.java


/**
 * getEnumConstants()方法属于Class，所以可以对非枚举类型的类调用该方法
 */
public class NonEnum {
  public static void main(String[] args) {
    Class<Integer> intClass = Integer.class;
    try {
      for(Object en : intClass.getEnumConstants())
        System.out.println(en);
    } catch(Exception e) {
      System.out.println(e);
    }
  }
} /* Output:
java.lang.NullPointerException
*///:~
