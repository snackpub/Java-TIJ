package com.snackpub.core.lambda;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    }
}
