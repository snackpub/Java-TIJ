package com.snackpub.core.collection.set;

import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.IntStream;

/**
 * TreeSet将元素存储在红-黑树数据结构中;
 *
 * @author snackpub
 * @date 2021/3/27
 */
public class SortedSetOfInteger {

    // 如果你想对结果排序,一种方式是使用TreeSet来代替HashSet;
    public static void main(String[] args) {
        Random rd = new Random(47);  // 当种子是47的时候，随机率是最大的
        SortedSet<Integer> objects = new TreeSet<>();
        IntStream.range(0, 10000).forEach(index -> {
            objects.add(rd.nextInt(30));
        });
        System.out.println(objects);
    }

}
