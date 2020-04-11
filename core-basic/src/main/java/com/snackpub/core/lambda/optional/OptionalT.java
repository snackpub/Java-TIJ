package com.snackpub.core.lambda.optional;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author snackpub
 * @date 2020/4/11 13:14
 */
public class OptionalT {

    public static void main(String[] args) {
//        print("admin");
//        print("");

//        findFirst();
        getLength("snackpub");
        getLength(null);

    }

    public static void print(String text) {
        Optional.ofNullable(text).ifPresent(System.out::println);
        // Pre-Java 8
        // if (text != null) {
        // System.out.println(text);
        // }
        // }

    }

    public static void getLength(String text) {
        // Java 8
        Integer n = Optional.ofNullable(text).map(String::length).orElse(-1);
        OptionalT.print(n + "");
        // Pre-Java 8
        // return if(text!=null) ? text.length() : -1;
    }

    /**
     * findFirst 是一个 terminal 兼 short-circuiting 操作，它总是返回stream的第一个元素，或者空
     * 这里的重点是它的返回值 Optional。作为一个容器，它可能含有某值，或者不包含。使用它的目的是尽可能
     * 避免 NullPointerException
     */
    public static void findFirst() {
        Stream<String> names = Stream.of("admin", "snack", "snackpub", "lzq");
        Optional<String> first = names.findFirst();
        first.ifPresent(System.out::print);
    }

}
