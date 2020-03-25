package com.snackpub.core.lambda.fun;

import java.util.Comparator;
import java.util.function.Consumer;

public class Java8Tester {

    final static String var1 = "张三";


    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operation(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

    public interface Converter<T1, T2> {
        void converter(int i);
    }

    public static void main(String[] args) {
        Java8Tester tester = new Java8Tester();
        // 类型声明
        MathOperation addition = (int a, int b) -> a + b;

        //不用类型声明
        MathOperation subtraction = (a, b) -> a - b;

        // 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> {
            return a * b;
        };

        // 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;

        int operation = tester.operation(10, 5, (int a, int b) -> a / b);
        int operation1 = tester.operation(10, 5, (a, b) -> {
            return a * b;
        });
        int operation2 = tester.operation(10, 5, (a, b) -> a - b);
        int operation3 = tester.operation(10, 5, Integer::sum);
        System.out.println("10/5 = " + operation);
        System.out.println("10*5 = " + operation1);
        System.out.println("10-5 = " + operation2);
        System.out.println("10+5 = " + operation3);

        // 不用括号
        GreetingService greetingService = message -> System.out.println("hello " + message);

        // 有括号,数据类型可省略
        GreetingService greetingService1 = (String message) -> System.out.println("hello " + message);

        //Consumer<T> 实现方式
        GreetingService greetingService2 = System.out::println;

        greetingService.sayMessage("中国通信服务");
        greetingService1.sayMessage("湖南正东通信科技公司");
        greetingService2.sayMessage("Consumer 消费接口");

        // lambda 调用成员变量,只能访问标记了 static 的外层局部变量
        GreetingService greetingService3 = message -> {
            System.out.println(var1 + message);
        };
        greetingService3.sayMessage(" Hello");

        // 直接在 lambda 表达式中访问外层的局部变量
        final int num = 1;
        Converter<Integer, String> converter = param -> {
            System.out.println(String.valueOf(param + num));
        };
        converter.converter(10);

        String first2 = "";
        Comparator<String> a =  (first, second) -> Integer.compare(first.length(), second.length());

//        String first = "";
//        Comparator<String> exceptionHandler = (first, second) -> Integer.compare(first.length(), second.length());//编译会出错

    }
}
