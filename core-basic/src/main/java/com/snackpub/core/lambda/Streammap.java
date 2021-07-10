package com.snackpub.core.lambda;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * map(映射)/flatMap(映射汇总)
 *
 * @author snackpub
 * @date 2020/4/11 10:28
 */
public class Streammap {


    public static void main(String[] args) {

        // squareCompute();
        // toUpperCase();
        // 上面的方法都是1：1映射，按照特定的规则转换为另一个元素
        // 其他场景就是1：N 映射，需要flatMap
        flatMapTest();
        flatMapTest2();
        flatMapTest3();
    }

    /**
     * map 映射大小写转换
     */
    public static void toUpperCase() {
        String[] str = new String[]{"snackpub", "qhj", "lzq"};
        Stream<String> stream = Stream.of(str);
        // map将 input Stream 的每一个元素，映射成 output Stream 的另外一个元素
        // 把所有名字转换为大写，并且每个名字用逗号分隔输出
        String collect = stream.map(String::toUpperCase).collect(Collectors.joining(","));
        Streammap.printInfo(collect, System.out::print);
    }

    /**
     * 平方计算
     */
    public static void squareCompute() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
        nums.stream().map(n -> n * n).forEach(System.out::println);
    }

    /**
     * 平方计算
     */
    public static void flatMapTest() {
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1, 2),
                Arrays.asList(3, 4, 5),
                Arrays.asList(6, 7)
        );

        Stream<Integer> outputStream = inputStream.flatMap(Collection::stream);
        outputStream.forEach(System.out::println);
        // flatMap把inputStream中的结构扁平化，就是将最底层元素抽取出来放到一起；最终output出新的stream
        // 里面已经没有List了，都是直接的数字 Stream<Integer>
    }


    public static void flatMapTest2() {
        List<String> list = Arrays.asList("aaa", "bbb", "ddd", "eee", "ccc");
        //采用flatMap来做  体会一下flatMap的魅力吧
        System.out.println("------ flatMapTest2 ----------");
        list.stream().flatMap(x -> {
            List<Character> characterList = new ArrayList<>();
            char[] chars = x.toCharArray();
            for (char c : chars)
                characterList.add(c);
            return characterList.stream();
        }).forEach(System.out::print); //aaabbbdddeeeccc
        // flatMap把inputStream中的结构扁平化，就是将最底层元素抽取出来放到一起；最终output出新的stream
        // 里面已经没有List了，都是直接的String Stream<String>
    }


    /**
     * 统计
     */
    public static void flatMapTest3() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        IntSummaryStatistics intSummaryStatistics = numbers.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("\n------ flatMapTest3 ----------");
        System.out.println("最大值" + intSummaryStatistics.getMax());
        System.out.println("最小值" + intSummaryStatistics.getMin());
        System.out.println("所有数之和" + intSummaryStatistics.getSum());
        System.out.println("平均数" + intSummaryStatistics.getAverage());
    }

    interface Print {
        void printInfo(String info);
    }

    private static void printInfo(String str, Print print) {
        print.printInfo(str);
    }

}
