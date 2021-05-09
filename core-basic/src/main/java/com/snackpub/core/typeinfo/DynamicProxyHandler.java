package com.snackpub.core.typeinfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author snackpub
 * @date 2021/5/9
 */
class DynamicProxyHandler implements InvocationHandler {
    private Object proxied;

    // 通常向调用处理器的构造器传递一个“实际”的对象引用，从而使得调用处理器在执行其中任务时，可将请求转发。
    public DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("**** proxy: " + proxy.getClass() + ", method: " + method +
                " ,args: " + Arrays.toString(args));
        if (args != null)
            for (Object arg : args)
                System.out.println("    " + arg);
        return method.invoke(proxied, args); //  invoke方法中传递进来了代理对象，以防你区分请求的资源. invoke将请求转发给代理对象
    }
}

class SimpleDynamicProxy {
    public static void consumer(Interface iface) {
        iface.doSomething();
        iface.somethingElse("bonobo");
    }

    public static void main(String[] args) {
        RealObject realObject = new RealObject();
        consumer(realObject);

        Interface proxy = (Interface) Proxy.newProxyInstance( // 创建动态代理
                Interface.class.getClassLoader(), // param 1. 类加载器
                new Class[]{Interface.class}, // param 2. 一个你希望代理实现的接口列表（不是类或抽象类）
                new DynamicProxyHandler(realObject) // param 3. InvocationHandler接口的一个实现，动态代理可以将所有
                // 处理重定向到调用处理器.
        );
        consumer(proxy);
    }
}
