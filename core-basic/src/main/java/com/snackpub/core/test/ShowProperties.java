package com.snackpub.core.test;

/**
 * @author snackpub
 * @date 2021/4/2
 */
public class ShowProperties {
    /* This is a comment
   that continues across lines */
    public static void main(String[] args) {
        System.getProperties().list(System.out);
        System.out.println(System.getProperty("user.name"));
        System.out.println(System.getProperty("java.library.path"));
    }
}
