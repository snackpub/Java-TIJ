package com.snackpub.core.lambda;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author snackpub
 * @date 2020/4/11 12:02
 */
public class Streampeek {

    //forEach 是 terminal 操作，因此它执行后，Stream 的元素就被“消费”掉了，
    // 你无法对一个 Stream 进行两次 terminal 运算。下面的代码是错误的：
    //stream.forEach(element -> doOneThing(element));
    //stream.forEach(element -> doAnotherThing(element));

    public static void main(String[] args) {
        // intermediate 操作 peek 对每个元素执行操作并返回一个新的 Stream
        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
    }
}
