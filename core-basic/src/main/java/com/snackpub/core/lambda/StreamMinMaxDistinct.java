package com.snackpub.core.lambda;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * min 和 max 的功能也可以通过对 Stream 元素先排序，再 findFirst 来实现，
 * 但前者的性能会更好，为 O(n)，而 sorted 的成本是 O(n log n)。
 * 同时它们作为特殊的 reduce 方法被独立出来也是因为求最大最小值是很常见的操作。
 *
 * @author snackpub
 * @date 2020/4/11 17:27
 */
public class StreamMinMaxDistinct {

    public static void main(String[] args) {
        max();
        distinct();
    }







    /**
     * 找出最长一行的长度
     */
    @SneakyThrows
    public static void max() {
        BufferedReader br = new BufferedReader(new FileReader("D:\\SUService.log"));
        int longest = br.lines().
                mapToInt(String::length).
                max().
                getAsInt();
        br.close();
        System.out.println(longest);
    }

    /**
     * 找出全文的单词，转小写，去除重复，并排序
     */
    @SneakyThrows
    public static void distinct() {
        BufferedReader br = new BufferedReader(new FileReader("D:\\SUService.log"));
        List<String> words = br.lines().flatMap(line -> Stream.of(line.split(" ")))
                .map(String::toUpperCase).distinct().sorted().collect(Collectors.toList());

        br.close();
        System.out.println(words);
    }

}
