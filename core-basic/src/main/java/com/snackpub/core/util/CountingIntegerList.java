package com.snackpub.core.util;

import java.util.AbstractList;

/**
 * 为了从AbstractList创建只读的List,你必须实现get()和size(),
 * 这里再次使用了亨元解决方案：当你寻找值时，get()将产生它，因此这个List实际上并不必组装.
 */
public class CountingIntegerList extends AbstractList<Integer> {
  private int size;
  public CountingIntegerList(int size) {
    this.size = size < 0 ? 0 : size;
  }
  public Integer get(int index) {
    return Integer.valueOf(index);
  }
  public int size() { return size; }
  public static void main(String[] args) {
    System.out.println(new CountingIntegerList(30));
  }
} /* Output:
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29]
*///:~