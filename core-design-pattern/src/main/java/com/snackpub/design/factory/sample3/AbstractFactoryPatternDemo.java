package com.snackpub.design.factory.sample3;

import com.snackpub.design.factory.sample3.factory.AbstractFactory;
import com.snackpub.design.factory.sample3.factory.builder.FactoryProducer;
import com.snackpub.design.factory.sample3.interfaces.Color;
import com.snackpub.design.factory.sample3.interfaces.Shape;

/**
 * 使用 FactoryProducer 来获取 AbstractFactory，通过传递类型信息来获取实体类的对象
 *
 * @author snackpub
 * @date 2020/7/27
 */
public class AbstractFactoryPatternDemo {

    public static void main(String[] args) {

        //获取形状工厂
        AbstractFactory shapeFactory = FactoryProducer.getFactory("SHAPE");
        //获取形状为 Square的对象
        Shape square = shapeFactory.getShape("SQUARE");
        //调用Square的draw方法
        square.draw();

        Shape rectangle = shapeFactory.getShape("RECTANGLE");
        rectangle.draw();

        AbstractFactory color = FactoryProducer.getFactory("COLOR");
        Color red = color.getColor("REDa");
        red.fill();

        Color blue = color.getColor("BLUE");
        blue.fill();

    }

}
