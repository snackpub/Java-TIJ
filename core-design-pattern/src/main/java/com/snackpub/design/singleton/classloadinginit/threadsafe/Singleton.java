package com.snackpub.design.singleton.classloadinginit.threadsafe;

import lombok.SneakyThrows;

import java.lang.reflect.Constructor;

/**
 * 饿汉式、线程安全
 *
 * @author snackpub
 * @date 2020/7/30
 */
public class Singleton {

    //===============================================
    /**
     * 是否 Lazy 初始化：否
     * <p>
     * 是否多线程安全：是
     * <p>
     * 实现难度：易
     * <p>
     * 描述：这种方式比较常用，但容易产生垃圾对象。
     * 优点：没有加锁，执行效率会提高。
     * 缺点：类加载时就初始化，浪费内存。
     * 它基于 classloader 机制避免了多线程的同步问题，不过，instance 在类装载时就实例化，
     * 虽然导致类装载的原因有很多种，在单例模式中大多数都是调用 getInstance 方法，
     * 但是也不能确定有其他的方式（或者其他的静态方法）导致类装载，这时候初始化 instance 显然没有达到 lazy loading 的效果。
     */

    private static Singleton instance = new Singleton();

    private Singleton() {
        //在构造器中加个逻辑判断,多次调用抛出异常
//        if (instance != null) {
//            throw new RuntimeException();
//        }
    }


    public static Singleton getInstance() {
        return instance;
    }


    @SneakyThrows
    public static void main(String[] args) {
        // 反射机制破解单例模式
        Class clazz = Class.forName("com.snackpub.design.singleton.classloadinginit.threadsafe.Singleton");
        Constructor c = clazz.getDeclaredConstructor(null);
        c.setAccessible(true);

        Singleton s1 = (Singleton) c.newInstance();
        Singleton s2 = (Singleton) c.newInstance();
        //通过反射，得到的两个不同对象
        System.out.println(s1);
        System.out.println(s2);
    }


}
