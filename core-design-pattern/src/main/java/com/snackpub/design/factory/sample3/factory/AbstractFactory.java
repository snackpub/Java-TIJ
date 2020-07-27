package com.snackpub.design.factory.sample3.factory;

import com.snackpub.design.factory.sample3.interfaces.Color;
import com.snackpub.design.factory.sample3.interfaces.Shape;

/**
 * 为 Color 和 Shape 对象创建抽象类来获取工厂
 *
 * @author snackpub
 * @date 2020/7/27
 */
public abstract class AbstractFactory {

    public abstract Color getColor(String color);

    public abstract Shape getShape(String shape);

}
