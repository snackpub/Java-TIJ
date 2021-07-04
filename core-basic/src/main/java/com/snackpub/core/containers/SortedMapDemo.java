package com.snackpub.core.containers;

import com.snackpub.core.util.CountingMapData;

import java.util.Iterator;
import java.util.TreeMap;

import static com.snackpub.core.util.Print.print;

/**
 * SortMap的唯一实现 TreeMap,可以确保键处于排序状态.
 * @date 2021/7/4
 */
public class SortedMapDemo {
  public static void main(String[] args) {
    TreeMap<Integer,String> sortedMap =
            new TreeMap<>(new CountingMapData(10));
    print(sortedMap);
    // 返回map中的第一个键
    Integer low = sortedMap.firstKey();
    // 返回map中最末一个键
    Integer high = sortedMap.lastKey();
    print(low);
    print(high);
    Iterator<Integer> it = sortedMap.keySet().iterator();
    for(int i = 0; i <= 6; i++) {
      if(i == 3) low = it.next();
      if(i == 6) high = it.next();
      else it.next();
    }
    print(low);
    print(high);
    // 生成Map的子集，范围由fromKey(包含)到toKey(不包含)的键确定.
    print(sortedMap.subMap(low, high));
    // 生成Map的子集，小于toKey的所有键值对组成.
    print(sortedMap.headMap(high));
    // 生成Map的子集，大于或等于fromKey的所有键值对组成.
    print(sortedMap.tailMap(low));
  }
} /* Output:
{0=A0, 1=B0, 2=C0, 3=D0, 4=E0, 5=F0, 6=G0, 7=H0, 8=I0, 9=J0}
0
9
3
7
{3=D0, 4=E0, 5=F0, 6=G0}
{0=A0, 1=B0, 2=C0, 3=D0, 4=E0, 5=F0, 6=G0}
{3=D0, 4=E0, 5=F0, 6=G0, 7=H0, 8=I0, 9=J0}
*///:~
