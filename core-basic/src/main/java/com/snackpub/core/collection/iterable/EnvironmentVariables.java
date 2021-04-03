package com.snackpub.core.collection.iterable;

import java.util.Map;

/**
 * @author snackpub
 * @date 2021/4/3
 */
public class EnvironmentVariables {


    public static void main(String[] args) {
        for (Map.Entry entry : System.getenv().entrySet()) {
            System.out.println(entry.getKey() + ": "
                    + entry.getValue());
        }
    }


}
