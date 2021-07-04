package com.snackpub.core.containers;
// What you can do with a LinkedHashMap.

import java.util.*;

import com.snackpub.core.util.CountingMapData;

import static com.snackpub.core.util.Print.print;

/**
 * 为了提高速度, LinkedHashMap散列化所有的元素,但是在遍历键值对时,
 * 却又以元素的插入顺序返回键值对(System.out.println()会迭代遍历该映射,
 * 因此可以看到遍历的结果)。此外,可以在构造器中设定LinkedHashMap,
 * 使之采用基于访问的最近最少使用(LRU)算法,
 * 于是没有被访问过的(可被看作需要删除的)元素就会出现在队列的前面。
 * 对于需要定期清理元素以节省空间的程序来说,此功能使得程序很容易得以实现。
 * 下面就是一个简单的例子,它演示了LinkedHashMap的这两种特点:
 */
public class LinkedHashMapDemo {
    public static void main(String[] args) {
        LinkedHashMap<Integer, String> linkedMap =
                new LinkedHashMap<>(
                        new CountingMapData(9));
        print(linkedMap);
        // Least-recently-used order: 最近最少使用顺序:
        linkedMap =
                new LinkedHashMap<>(16, 0.75f, true);
        linkedMap.putAll(new CountingMapData(9));
        print(linkedMap);
        for (int i = 0; i < 6; i++) // Cause accesses:
            linkedMap.get(i);  // 测试LRU，限制到下标>=6的元素，看>=6的元素是否排列到队列的前面.
        print(linkedMap);
        linkedMap.get(0);
        print(linkedMap);
    }
} /* Output:
{0=A0, 1=B0, 2=C0, 3=D0, 4=E0, 5=F0, 6=G0, 7=H0, 8=I0}
{0=A0, 1=B0, 2=C0, 3=D0, 4=E0, 5=F0, 6=G0, 7=H0, 8=I0}
{6=G0, 7=H0, 8=I0, 0=A0, 1=B0, 2=C0, 3=D0, 4=E0, 5=F0} // 很显然，结果是这样的
{6=G0, 7=H0, 8=I0, 1=B0, 2=C0, 3=D0, 4=E0, 5=F0, 0=A0}
*///:~
