package com.snackpub.core.lambda.fun.defInterface;


/**
 * JDK1.8 接口新增特性
 *
 * @author snackpub
 * @date 2021//7/10
 */
public interface Java8Interface {


    default void defaultMethod() {
        System.out.println("hello, default a method");
    }

    /**
     * 当然你也可以定义静态方法
     */
    static String staticMethod() {
        return "She's really gone.";
    }

    static void main(String[] args) {
        String info = Java8Interface.staticMethod();
        System.out.println(info);
    }

}

class DerivedClass implements Java8Interface{

    public static void main(String[] args) {
        Java8Interface obj = new DerivedClass();
        obj.defaultMethod();
    }
}
