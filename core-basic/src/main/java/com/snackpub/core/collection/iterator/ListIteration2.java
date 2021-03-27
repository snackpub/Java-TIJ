package com.snackpub.core.collection.iterator;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 练习12: (3)创建并组装一个List<Integer>,然后创建第二个具有相同尺寸的List<Integer>,
 * 并使用Listlterator读取第一个List中的元素,
 * 然后再将它们以反序插入到第二个列表中(你可能想探索该问题的各种不同的解决之道)。
 *
 * @author snackpub
 * @date 2021/3/27
 */
public class ListIteration2 {

    public static void main(String[] args) {
//        method2();
    }

    private final List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
    private final List<Integer> list2 = new ArrayList<>(list1.size());

    public void method1() {
        ListIterator<Integer> it = list1.listIterator();
        while (it.hasNext())
            it.next();
//            System.out.println(it.next() + " , " + it.nextIndex() + ", " + it.previousIndex());
        while (it.hasPrevious()) {
            Integer previous = it.previous();
            list2.add(previous);
        }
        System.out.println(list2);
    }

    private static void method2() {
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = new ArrayList<>(list1.size());
        Collections.reverse(list1);
        list2.addAll(list1);
        System.out.println(list2);
    }



}
