package com.snackpub.core.reflection.annotations3;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author snackpub
 * @date 2020/9/24
 */
public class DoMain {


    public static void main(String[] args) {

        CalculatorTest calculatorTest = new CalculatorTest();
        try {
            run(calculatorTest);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public static void run(Object obj) throws InvocationTargetException, IllegalAccessException {
        for (Method m : obj.getClass().getMethods()) {
            Test test = m.getDeclaredAnnotation(Test.class);
            if (test != null && !test.ignore()) {
                m.invoke(obj);
            }
        }


        Test annotation = obj.getClass().getAnnotation(Test.class);

    }

    public static void run2() {
        Method[] methods = Test.class.getMethods();
        for (Method method : methods) {
            Test declaredAnnotation = method.getDeclaredAnnotation(Test.class);
        }

        // 获取类上面的注解

    }



}
