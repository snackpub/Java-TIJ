package com.snackpub.core.containers;
import com.snackpub.core.util.CollectionData;
import com.snackpub.core.util.Generator;

import java.util.*;

class Government implements Generator<String> {
  private String[] foundation = ("strange women lying in ponds " +
    "distributing swords is no basis for a system of " +
    "government").split(" ");
  private int index;
  @Override
  public String next() { return foundation[index++]; }
}

public class CollectionDataTest {
  public static void main(String[] args) {
    //LinkedHashSet 维持了插入顺序的连接列表
    Set<String> set = new LinkedHashSet<>(
            new CollectionData<>(new Government(), 15));
    // Using the convenience method:
    set.addAll(CollectionData.list(new Government(), 15));
    System.out.println(set);
  }
} /* Output:
[strange, women, lying, in, ponds, distributing, swords, is, no, basis, for, a, system, of, government]
*///:~
