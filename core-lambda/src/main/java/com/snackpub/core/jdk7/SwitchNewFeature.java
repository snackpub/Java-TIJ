package com.snackpub.core.jdk7;

/**
 * Switch 新特性
 *
 * @author snackpub
 * @date 2020/3/27 12:40
 */
public class SwitchNewFeature {

    public static String test(String str) {
        String value;
        switch (str) {
            case "snackpub":
                value = "my snackpub";
                break;
            case "lzq":
                value = "my lzq";
                break;
            case "qhj":
            case "honey":
                value = "my honey";
                break;
            default:
                value = "my default";
        }
        return value;
    }

    public static void main(String[] args) {
        String snackpub = SwitchNewFeature.test("snackpub");
        System.out.println(snackpub);
    }
}
