package com.snackpub.test;

/**
 * @author snackpub
 * @date 2021/8/15
 */
public class StringBufferTest {

    public static void main(String[] args) {
        String s = new StringBuilder("hello").append("word").toString();
        System.out.println(s.intern() == s);

        String s2 = new StringBuilder("jav").toString();
        System.out.println(s2.intern().hashCode());
        System.out.println(s2.hashCode());
        System.out.println(s2.equals(s2.intern()));
        System.out.println(s2.intern() == s2);
    }
}
