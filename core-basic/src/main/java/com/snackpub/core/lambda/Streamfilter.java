package com.snackpub.core.lambda;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author snackpub
 * @date 2020/4/11 11:17
 */
public class Streamfilter {

    public static void main(String[] args) {
//        outEven();
        regexp();
    }


    /**
     * 留下偶数
     */
    private static void outEven() {
        Integer[] nums = {1, 2, 3, 4, 5, 6};
        Stream.of(nums).filter(i -> i % 2 == 0).forEach(System.out::print);
        // 经过“被 2 整除”条件过滤，只留下了2,4,6被filter下来了
        // filter 只留下符合条件的
    }

    /**
     * 挑选单词
     */
    @SneakyThrows
    public static void regexp() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        System.out.println("Enter lines of text.");
        System.out.println("Enter 'end' to quit.");
        List<String> readLins = new ArrayList<>();
        do {
            str = br.readLine();
            readLins.add(str);
        } while (!str.equals("end"));
        readLins.stream().filter(word -> word.length() > 0).forEach(System.out::print);
    }
}
