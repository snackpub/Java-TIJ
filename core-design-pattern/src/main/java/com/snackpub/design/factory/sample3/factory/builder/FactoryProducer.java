package com.snackpub.design.factory.sample3.factory.builder;

import com.snackpub.design.factory.sample3.factory.AbstractFactory;
import com.snackpub.design.factory.sample3.factory.ext.ColorFactory;
import com.snackpub.design.factory.sample3.factory.ext.ShapeFactory;

/**
 * 工厂创造器/生成器类
 *
 * @author snackpub
 * @date 2020/7/27
 */
public class FactoryProducer {

    /**
     * 通过传递形状或颜色信息来获取工厂
     *
     * @param choice color or shape or null
     * @return AbstractFactory or null
     */
    public static AbstractFactory getFactory(String choice) {
        if (choice.equalsIgnoreCase("SHAPE")) {
            return new ShapeFactory();
        } else if (choice.equalsIgnoreCase("COLOR")) {
            return new ColorFactory();
        }
        return null;
    }

}
