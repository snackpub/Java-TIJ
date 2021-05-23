package com.snackpub.core.generics.decorator;

/**
 * 练习38: (4)从基本的咖啡入手,创建一个简单的装饰器系统,然后提供可以到倒入牛奶、泡沫、巧克力、焦糖和生奶油的装饰器。
 *
 * @author snackpub
 * @date 2021/5/23
 */

interface Component {
    void method();
}

class Coffee implements Component {

    @Override
    public void method() {
        System.out.println("倒入咖啡");
    }
}

class Decorator2 implements Component {
    private Component component;

    public Decorator2(Component component) {
        this.component = component;
    }

    @Override
    public void method() {
        component.method();
    }
}

class ConcreteDecorateA extends Decorator2 {
    private Component component;

    public ConcreteDecorateA(Component component) {
        super(component);
        this.component = component;
    }

    public void method1() {
        System.out.println("倒入牛奶");

    }

    public void method2() {
        System.out.println("加入糖 ");

    }

    @Override
    public void method() {
        super.method();
        method1();
        method2();
    }
}

class ConcreteDecorateB extends Decorator2 {
    private Component component;

    public ConcreteDecorateB(Component component) {
        super(component);
        this.component = component;
    }

    public void method1() {
        System.out.println("加入巧克力");

    }

    @Override
    public void method() {
        super.method();
        method1();
    }
}


public class Decoration2 {

    public static void main(String[] args) {
        Component coffee = new Coffee();
        coffee.method();
        System.out.println("--------------------------------------------------");
        Component concreteDecorateA = new ConcreteDecorateA(coffee);
        concreteDecorateA.method();
        System.out.println("--------------------------------------------------");

        ConcreteDecorateB concreteDecorateB = new ConcreteDecorateB(concreteDecorateA);
        concreteDecorateB.method();

        System.out.println("--------------------------------------------------");
        ConcreteDecorateB comp3 = new ConcreteDecorateB(new ConcreteDecorateA(new Coffee()));
        comp3.method();

        System.out.println("--------------------------------------------------");
        ConcreteDecorateA comp4 = new ConcreteDecorateA(new ConcreteDecorateB(new Coffee()));
        comp4.method();
    }

}
