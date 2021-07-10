package com.snackpub.core.lambda.characteristics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 并行
 * 所有的流操作都可以串行执行或者并行执行。
 * 除非显示地创建并行流，否则Java库中创建的都是串行流
 *
 * @author snackPub
 * @date 2021/4/1
 */
public class Parallelism {

    public static void main(String[] args) {

        //------- 1. 并行 Parallelism
        ArrayList<String> list = new ArrayList<>(Arrays.asList("1", "2", "3"));
        list.parallelStream().forEach(System.out::println); // 并行流

        list.stream().forEach(System.out::println); // 串行流


        // 通过parallel()方法可以将串行流转换成并行流,从并行流通过sequential转换为串行流
        IntStream.range(0, 3).parallel().sequential().forEach(System.out::println);


        //------- 2. 不能干扰 Non-interference

        // 流可以从非线程安全的集合中创建，当流的管道执行的时候，非concurrent数据源不应该被改变
        // java.util.ConcurrentModificationException异常
        /*List<String> l = new ArrayList<>(Arrays.asList("one", "two"));
        Stream<String> sl = l.stream();
        sl.forEach(s -> l.add("three"));*/

        // concurrent数据源，不会有这样的问题
        List<String> list1 = new CopyOnWriteArrayList<>(Arrays.asList("4", "5", "6"));
        Stream<String> stream = list1.stream();
        stream.forEach(s -> list1.add("7"));

        //------- 3. 无状态 Stateless behaviors

        // 大部分流的操作的参数都是函数式接口，可以使用Lambda表达式实现。它们用来描述用户的行为，称之为行为参数(behavioral parameters)。
        // 如果这些行为参数有状态，则流的操作的结果可能是不确定的，比如下面的代码:
        List<String> list2 = new ArrayList<>(Arrays.asList("one", "two"));
        final State state = new State();
        Stream<String> sl = list2.stream().map(e -> {
            if (state.s)
                return "OK";
            else {
                state.s = true;
                return e;
            }
        });

        sl.forEach(System.out::println);

        // 4. 副作用 Side-effects
        // 有副作用的行为参数是被不鼓励使用的，事实上，除了打印之外其他场景都应避免使用副作用。
        List<Object> list3 = new ArrayList<>();
        List<Object> list4 = new ArrayList<>();
        IntStream.range(0, 1000).forEach(index -> list3.add(index + ""));
        // 副作用代码
        list3.parallelStream().forEach(list4::add);
        System.out.println(list4);


    }

    static class State {
        boolean s;
    }

}
