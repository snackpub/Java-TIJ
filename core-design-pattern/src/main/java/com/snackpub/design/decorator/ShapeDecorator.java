package com.snackpub.design.decorator;

/**
 * 实现了 Shape 接口的抽象装饰类
 *
 * @author snack
 * @date 2020/7/27 23:11
 */
public abstract class ShapeDecorator implements Shape {
    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape) {
        this.decoratedShape = decoratedShape;
    }

    public void draw() {
        decoratedShape.draw();
    }
}