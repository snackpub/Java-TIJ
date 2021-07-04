//: net/mindview/util/CollectionData.java
// A Collection filled with data using a generator object.
package com.snackpub.core.util;
import java.util.*;


/**
 * CollectionData 是适配器设计模式的一个实例，它将Generator适配到Collection的构造器上
 * @param <T>
 */
public class CollectionData<T> extends ArrayList<T> {
  public CollectionData(Generator<T> gen, int quantity) {
    for(int i = 0; i < quantity; i++)
      add(gen.next());
  }
  // A generic convenience method:
  public static <T> CollectionData<T>
  list(Generator<T> gen, int quantity) {
    return new CollectionData<T>(gen, quantity);
  }
} ///:~
