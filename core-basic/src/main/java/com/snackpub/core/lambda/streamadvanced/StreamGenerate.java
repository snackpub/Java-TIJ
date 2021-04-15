package com.snackpub.core.lambda.streamadvanced;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 通过实现 Supplier 接口，你可以自己来控制流的生成。这种情形通常用于随机数、常量的 Stream，
 * 或者需要前后元素间维持着某种状态信息的 Stream。
 * 把 Supplier 实例传递给 Stream.generate() 生成的 Stream，
 * 默认是串行（相对 parallel 而言）但无序的（相对 ordered 而言）。由于它是无限的，在管道中，
 * 必须利用 limit 之类的操作限制 Stream 大小。
 *
 * @author snackpub
 * @date 2020/4/11 18:59
 */
public class StreamGenerate {

    public static void main(String[] args) {
        random();
    }


    /**
     * 生成 10 个随机整数
     */
    public static void random() {
        // test
        Random seed = new Random();
        Supplier<Integer> random = seed::nextInt;
        Stream.generate(random).limit(10).forEach(System.out::println);
        // Another way
        IntStream.generate(() -> (int) (System.nanoTime()) % 100)
                .limit(10).forEach(System.out::println);

    }
}
