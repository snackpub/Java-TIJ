package com.snackpub.core.test;

/**
 * @author snackpub
 * @date 2021/4/2
 */
public class Incrementable {
    private static void increment() {
        StaticTest.i++;
    }

    public static void main(String[] args) {
//        Incrementable sf = new Incrementable();
//        sf.increment();
        Incrementable.increment();
    }
}
