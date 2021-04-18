package com.snackpub.core.typeinfo.practice;


import com.snackpub.core.typeinfo.pets.Cat;
import com.snackpub.core.typeinfo.pets.LiteralPetCreator;
import com.snackpub.core.typeinfo.pets.Pet;
import com.snackpub.core.util.Print;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Objects;

/**
 * @author snackpub
 * @date 2021/4/18
 */
public class Pra1 {

    public static void main(String[] args) {
       /* char[] a = {'a', 'b'};
        p1(a);*/

        p2(new LiteralPetCreator());
//        demo(new Cat());
    }

    // 练习10: (3)写一个程序,使它能判断char数组究竟是个基本类型,还是一个对象。
    public static void p1(char[] chars) {
        if (Objects.nonNull(chars.getClass())) {
            System.out.println("yes,Object");
        }
    }

    //    练习8: (5)写一个方法,令它接受任意对象作为参数,并能够递归打印出该对象所在的继承体系中的所有类。
    public static void p2(Object object) {
        Class<?> aClass = object.getClass(); // 获取类的引用
        Class<?> superclass = aClass.getSuperclass();//获取直接继承的超类
     /*   if (Modifier.isAbstract(superclass.getModifiers())) { // 判断是否是抽象类
            return;
        }*/
        if (Objects.nonNull(superclass)) {
            if (superclass.isInstance(Object.class)/*getSimpleName().equals("Object")*/) {
                Print.print(superclass.getCanonicalName());
                return;
            }
            Print.print(superclass.getCanonicalName());
            try {
                if (Modifier.isAbstract(superclass.getModifiers())) {  // 判断是否是抽象类
//                    p2(superclass.);
                } else {
                    p2(superclass.newInstance());
                }
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        /*Class<?>[] interfaces = aClass.getInterfaces();// 获取所有的接口
        if (Objects.nonNull(interfaces) && interfaces.length > 0) {
            for (Class<?> anInterface : interfaces) {
                anInterface
            }
        }*/
    }


    public static void demo(Object obj) {
        Class<?> superclass = obj.getClass().getSuperclass();
        System.out.println(superclass);
        if (!(superclass.getSimpleName().equals("Object"))) {
            try {
                demo(superclass.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
