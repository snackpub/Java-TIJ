package com.snackpub.core.generics;
import java.util.*;

public class ErasedTypeEquivalence {
  public static void main(String[] args) {
    Class c1 = new ArrayList<String>().getClass();
    Class c2 = new ArrayList<Integer>().getClass();
    Class c3 = ArrayList.class; // You can do this.
//    Class c3 = new ArrayList<Integer>().class;  But you can't do this.
    System.out.println(c1 == c2);
  }
} /* Output:
true
*///:~
