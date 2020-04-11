package com.snackpub.core.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.snackpub.core.lambda.model.Person;

public class Streamlimit {

    public static void main(String[] args) {
        // limit 返回由该流的元素组成的流，截断后长度不超过{@code maxSize}。
        // 这是一个短路有状态的中间操作
        // 如果{@code maxSize}为负数，则抛出IllegalArgumentException
        int sum = IntStream.iterate(1, e -> e + 10).limit(2).sum();
//        System.out.println(sum);
        // 该代码会执行输出4,6，截断后的长度不超过2
//        Stream.of(1, 4, 6, 7, 10, 33).filter(i -> i % 2 == 0).limit(2).forEach(System.out::println);
        // print 4 6

        // skip 跳过{@code n}个元素
        // 在丢弃流的第一个{@code n}元素后，返回一个由该流的其余元素组成的流。
        // 如果这个流包含的元素少于{@code n}，那么将返回一个空流。
        // 如果 {@code n} 为负数，则抛出IllegalArgumentException
        Stream.of(1, 2, 5, 6, 32, 20, 23).filter(i -> i % 2 == 0).skip(2).forEach(System.out::println);
        // print 32 20


//        String reduce = Stream.of("1", "2", "4", "5", "6").reduce("", String::concat);
//        System.out.println(reduce);
        Stream<String> snackpub = Stream.of("1", "2", "4", "5", "6", null, "snackpub");
        List<String> collect = snackpub.filter(Objects::nonNull)
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        shortCircuiting();
        shortCircuiting2();
    }


    /**
     * 这是一个有 10，000 个元素的 Stream，但在 short-circuiting 操作 limit 和 skip 的作用下，
     * 管道中 map 操作指定的 getName() 方法的执行次数为 limit 所限定的 10 次，
     * 而最终返回结果在跳过前 3 个元素后只有后面 7 个返回
     */
    public static void shortCircuiting() {
        List<Person> persons = new ArrayList();
        for (int i = 1; i <= 10000; i++) {
            Person person = new Person(i, "name" + i, i);
            persons.add(person);
        }
        List<String> personList2 = persons.stream().map(Person::getName).limit(10).skip(3).collect(Collectors.toList());
        System.out.println(personList2);
    }

    /**
     * limit 和 skip 对 sorted 后的运行次数无影响
     * 首先对 5 个元素的 Stream 排序，然后进行 limit 操作
     * 即虽然最后的返回元素数量是 2，但整个管道中的 sorted 表达式执行次数没有像前面例子相应减少
     */
    public static void shortCircuiting2() {
        List<Person> persons = new ArrayList();
        for (int i = 1; i <= 5; i++) {
            Person person = new Person(i, "name" + i, i);
            persons.add(person);
        }
        List<Person> collect = persons.stream().sorted(Comparator.comparing(Person::getName)).limit(2).collect(Collectors.toList());

        System.out.println(collect);
    }

    // 最后有一点需要注意的是，对一个 parallel 的 Steam 管道来说，
    // 如果其元素是有序的，那么 limit 操作的成本会比较大，
    // 因为它的返回对象必须是前 n 个也有一样次序的元素。取而代之的策略是取消元素间的次序，
    // 或者不要用 parallel Stream。
}
