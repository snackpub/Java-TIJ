package com.snackpub.core.lambda.fun.predicate;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * Predicate 接口
 *
 * @author snackpub
 * @date 2021/7/10
 */
public class PredicateExample {


    /**
     * 自定义一个Predicate接口
     */
    @FunctionalInterface
    interface DefinitionPredicate<T> {
        boolean test(T t);
    }

    /**
     * 自定义一个排序方法
     */
    static class DefComparable implements Comparator<Integer> {

        @Override
        public int compare(java.lang.Integer o1, java.lang.Integer o2) {
            return o1.compareTo(o2);
        }

        @Override
        public Comparator<Integer> reversed() {
            return null;
        }
    }

    // 通过参数传递一个Predicate
    public static <T> void foreach(Collection<T> collection, DefinitionPredicate<T> definitionPredicate) {
        collection.forEach(item -> {
            if (definitionPredicate.test(item)) {
                System.out.println(item + " ");
            }
        });
    }

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        foreach(integers, n -> true);
        System.out.println("---------");
        foreach(integers, n -> n > 1);
        System.out.println("---------");
        foreach(integers, n -> n % 2 == 0);
    }


}
