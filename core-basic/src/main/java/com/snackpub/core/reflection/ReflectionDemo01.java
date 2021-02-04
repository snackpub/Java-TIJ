package com.snackpub.core.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射01
 *
 * @author snackpub
 * @date 2021/1/11
 */
public class ReflectionDemo01 {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        // 通过Class.ForName方式加载,返回一个Class对象
        Class<?> aClass = Class.forName("com.snackpub.core.reflection.Person");
        System.out.println(aClass); //  print: class com.snackpub.core.reflection.Person

        // 分别通过该类的有参构造和无参构造来实例化对象
        Class<?> studentClass = Class.forName("com.snackpub.core.reflection.Person"); //加载类
        Person person = (Person) studentClass.newInstance();// 通过无参构造函数来实例化对象
        person.setAge(10);
        person.setSex("男");
        person.setName("snackpub");
        System.out.println(person); // print: Person{age=10, name='snackpub', sex='男'}

        // 通过有参构造函数来构造对象
        try {
            /*
             * 返回一个构造函数对象，该构造函数对象反映由这个类对象表示的类的指定公共构造函数。parameterTypes形参是一个类对象数组，
             * 以声明的顺序标识构造函数的形式形参类型。如果这个类对象表示在非静态上下文中声明的内部类，
             * 则形式参数类型将显式的外围实例作为第一个参数。
             * 要反映的构造函数是由这个类对象表示的类的公共构造函数，这个类对象的形参类型与参数类型指定的形参类型匹配。
             */
            Constructor<?> constructor = studentClass.getConstructor(int.class, String.class, String.class);
            Person person2 = (Person) constructor.newInstance(20, "女", "lzq");
            System.out.println(person2); // Person{age=20, name='女', sex='lzq'}
        } catch (NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

        // 通过反射来使用类中的方法
        Method[] declaredMethods = studentClass.getDeclaredMethods(); // 获取所有方法
        try {
            // 根据方法名、参数类型获取公开的方法
            Method setName = studentClass.getDeclaredMethod("setName", String.class);
            String name = setName.getName();
            //  obj – 调用的对象
            //  args – 用于方法调用的参数
            setName.invoke(person, "qiuhaijun"); // 调用
            String name1 = person.getName();
            System.out.println(name1); //  print: qiuhaijun
        } catch (NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

        // 使用反射来操作属性
        // 获取公开的属性
        try {
            Field name = studentClass.getDeclaredField("name");
            //抛出异常 Class com.snackpub.core.reflection.ReflectionDemo01 can not access a member of class com.snackpub.core.reflection.Person with modifiers "private"
            /*
             * 不能对私有的属性直接反射,解决方法就是抑制Java语言访问检查
             * 将该对象的可访问标志设置为指定的布尔值。true表示被反射的对象在使用时应该抑制Java语言访问检查。false表示被反射的对象应该执行Java语言访问检查。
             * 首先，如果有一个安全管理器，则使用ReflectPermission(“suppressAccessChecks”)权限调用其checkPermission方法。
             */
            name.setAccessible(true);
            name.set(person, "snackpub2");

            System.out.println(person.getName()); // print: snackpub2
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

}
