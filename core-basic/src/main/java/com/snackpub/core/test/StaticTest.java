package com.snackpub.core.test;

/**
 * @author snackpub
 * @date 2021/4/2
 */
public class StaticTest {


    static int i = 47;

    public static void main(String[] args) {
        StaticTest.i++;
        StaticTest s1 = new StaticTest();
        StaticTest s2 = new StaticTest();


        System.out.println(i);
    }
}
