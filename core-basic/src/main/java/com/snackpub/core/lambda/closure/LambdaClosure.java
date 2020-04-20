package com.snackpub.core.lambda.closure;

/**
 * 使用闭包捕获状态
 *
 * @author snackpub
 * @date 2020/4/20
 */
public class LambdaClosure {

    public static void main(String[] args) {
        //level 1 of stack
        int value = 4;
        //level 3 of stack
//        Sample.call(() -> System.out.println(value));

        Runnable runnable = Sample.create();

        System.out.println("In main");
        runnable.run();
    }


    public static void print() {
        String location = "World";
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Hello " + location);
//            }
//        };
        Runnable runnable = () -> System.out.println("Hello " + location);
        runnable.run();

    }
}

class Sample {
    public static void call(Runnable runnable) {
        System.out.println("calling runnable");
        //level 2 of stack
        runnable.run();
    }


    public static Runnable create() {
        int value = 4;
        Runnable runnable = () -> System.out.println(value);

        System.out.println("exiting create");
        return runnable;
    }
}
