package com.snackpub.core.collection.list;

import java.util.*;

/**
 * @author snackpub
 * @date 2021/3/27
 */
public class AddingGroups {
    public static void main(String[] args) {
        Collection<Integer> collection = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Integer[] moreInts = {6, 7, 8, 9, 10};
        collection.addAll(Arrays.asList(moreInts));
        //运行速度快得多，但是你不能这样构造一个集合:
        Collections.addAll(collection, 11, 12, 13, 14, 15);
        Collections.addAll(collection, moreInts);
        System.out.println(collection);
        //生成一个由数组“支持”的列表:
        List<Integer> list = Arrays.asList(16, 17, 18, 19, 20);
        list.set(1, 99); // OK -- modify an element
        // System.out.println(list);
        // list.add(21); // 运行时错误，因为无法调整基础数组的大小
    }
}
