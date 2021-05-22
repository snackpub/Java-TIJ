package com.snackpub.core.typeinfo;//: typeinfo/SimpleProxyDemo.java


// 修改SimpleProxyDemo2.java,使其可以度量方法调用的次数

interface Interface2 {
    void doSomething();

    void somethingElse(String arg);
}

class RealObject2 implements Interface2 {
    public static long counter = 0;
    public static long counter2 = 0;

    @Override
    public void doSomething() {
        // 统计次数
        // System.out.println(new Count("doSomething"));
        counter++;
        System.out.println("doSomething");
    }

    @Override
    public void somethingElse(String arg) {
        // 统计次数
        //System.out.println(new Count("somethingElse"));
        counter2++;
        System.out.println("somethingElse:" + arg);
    }

}

class SimpleProxy2 implements Interface2 {

    private Interface2 interface2;

    public SimpleProxy2(Interface2 interface2) {
        this.interface2 = interface2;
    }

    @Override
    public void doSomething() {
        RealObject2.counter++;
        System.out.println("SimpleProxy2.doSomething ");
        interface2.doSomething();
    }

    @Override
    public void somethingElse(String arg) {
        RealObject2.counter2++;
        System.out.println("SimpleProxy2.somethingElse ");
        interface2.somethingElse(arg);
    }
}


class SimpleProxyDemo2 {
    public static void consumer(Interface2 iface) {
        iface.doSomething();
        iface.somethingElse("bonobo");
    }

    public static void main(String[] args) {
        consumer(new RealObject2());
        consumer(new SimpleProxy2(new RealObject2()));
        System.out.println();
        System.out.println("doSomething count: " + RealObject2.counter);
        System.out.println("somethingElse count: " + RealObject2.counter2);
    }
} /* Output:
doSomething
somethingElse bonobo
SimpleProxy doSomething
doSomething
SimpleProxy somethingElse bonobo
somethingElse bonobo
*///:~
