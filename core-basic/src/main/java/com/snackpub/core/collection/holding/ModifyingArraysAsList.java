package com.snackpub.core.collection.holding;

import java.util.*;

/**
 * 在第一种情况中, Arrays.asList()的输出被传递给了ArrayList()的构造器,
 * 这将创建一个引用ia的元素的ArrayList,因此打乱这些引用不会修改该数组。
 * 但是,如果直接使用Arrays.asList()的结果,这种打乱就会修改ia的顺序。
 * 意识到Arrays.aslList()产生的List对象会使用底层数组作为其物理实现是很重要的。
 * 只要你执行的操作会修改这个List,并且你不想原来的数组被修改,那么你就应该在另一个容器中创建一个副本。
 *
 * @author snackpub
 * @date 2021/4/3
 */
public class ModifyingArraysAsList {
    public static void main(String[] args) {

        Random rd = new Random(47);
        Integer[] ia = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // 用一个ArrayList将Arrays.asList()方法的结果包装起来，Collections.shuffle 没有影响到原来的数组
        List<Integer> list1 = new ArrayList<>(Arrays.asList(ia));
        System.out.println("Before shuffling: " + list1);
        Collections.shuffle(list1, rd);
        System.out.println("After shuffling: " + list1);
        System.out.println("Array: " + Arrays.toString(ia));

        // 如果这个由Arrays.asList()方法产生的List被直接打乱,那么它就会修改底层的数组,就像下面这样:
        List<Integer> list2 = Arrays.asList(ia);
        System.out.println("Before shuffling: " + list2);
        Collections.shuffle(list2, rd);
        System.out.println("After shuffling: " + list2);
        System.out.println("Array: " + Arrays.toString(ia));
    }
}
/* Output:
Before shuffling: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
After shuffling: [4, 6, 3, 1, 8, 7, 2, 5, 10, 9]
Array: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
Before shuffling: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
After shuffling: [9, 1, 6, 3, 7, 2, 5, 10, 4, 8]
Array: [9, 1, 6, 3, 7, 2, 5, 10, 4, 8]
*///:~
