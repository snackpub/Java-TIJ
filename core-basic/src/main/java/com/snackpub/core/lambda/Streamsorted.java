package com.snackpub.core.lambda;

import com.snackpub.core.lambda.model.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 对 Stream 的排序通过 sorted 进行，
 * 它比数组的排序更强之处在于你可以首先对 Stream 进行各类
 * map、filter、limit、skip 甚至 distinct 来减少元素数量后，
 * 再排序，这能帮助程序明显缩短执行时间。
 *
 * @author snackpub
 * @date 2020/4/11 17:20
 */
public class Streamsorted {

    public static void main(String[] args) {
        sorted();
    }

    /**
     * 排序前进行 limit 和 skip
     * 当然，这种优化是有 business logic 上的局限性的：即不要求排序后再取值。
     */
    public static void sorted() {
        List<Person> persons = new ArrayList();
        for (int i = 1; i <= 5; i++) {
            Person person = new Person(i, "name" + i, i);
            persons.add(person);
        }
        List<Person> collect = persons.stream().limit(2).sorted(Comparator.comparing(Person::getName)).limit(2).collect(Collectors.toList());

        System.out.println(collect);
    }
}
