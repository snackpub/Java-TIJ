package com.snackpub.core.lambda;

import java.util.stream.Stream;

/**
 * reduce 方法的主要作用是把 Stream 元素组合起来
 * 第一个参数（空白字符）即为起始值(seed 种子)，第二个参数（String::concat）为 BinaryOperator。
 * 这类有起始值的 reduce() 都返回具体的对象。而对于第四个示例没有起始值的 reduce()，
 * 由于可能没有足够的元素，返回的是 Optional，请留意这个区别。
 *
 * @author snackpub
 * @date 2020/4/11 15:48
 */
public class Streamreduce {


    public static void main(String[] args) {
        // 字符串连接
        String strConcat = Stream.of("lzq", "snackpub").reduce("", String::concat);
        // 求最小值
        double minValue = Stream.of(1.1, 1.2, 0.4).reduce(Double.MAX_VALUE, Double::min);
        // 求和,sum = 10, 有起始值
        int sum = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
        // 求和，sumValue = 10, 无起始值
        int sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        // 过滤，字符串连接，concat = "ace"
        String filterStrConcat = Stream.of("a", "B", "c", "D", "e", "F")
                .filter(x -> x.compareTo("Z") > 0)
                .reduce("", String::concat);
        System.out.println("sum: " + sum);
        System.out.println("sumValue: " + sumValue);
        System.out.println("minValue: " + minValue);
        System.out.println("strConcat：" + strConcat);
        System.out.println("filterStrConcat：" + filterStrConcat);
    }
}
