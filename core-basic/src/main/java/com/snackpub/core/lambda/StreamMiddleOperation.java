package com.snackpub.core.lambda;

import java.util.stream.Stream;

/**
 * 相关中间操作函数
 *
 * @author snackPub
 * @date 2021/4/7
 */
public class StreamMiddleOperation {

    public static void main(String[] args) {
        // 转换为int类型
        Stream<String> stringStream = Stream.of("1", "2", "3");
        stringStream.mapToInt(Integer::parseInt).forEach(System.out::println);

        // 映射字符串的长度
        Stream<String> stringStream2 = Stream.of("1", "22", "333");
        stringStream2.mapToInt(String::length).forEach(System.out::println);

    }
}
