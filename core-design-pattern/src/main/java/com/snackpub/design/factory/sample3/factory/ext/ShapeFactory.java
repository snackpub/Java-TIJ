package com.snackpub.design.factory.sample3.factory.ext;

import com.snackpub.design.factory.sample3.factory.AbstractFactory;
import com.snackpub.design.factory.sample3.interfaces.Color;
import com.snackpub.design.factory.sample3.interfaces.Shape;
import com.snackpub.design.factory.sample3.interfaces.impl.Circle;
import com.snackpub.design.factory.sample3.interfaces.impl.Rectangle;
import com.snackpub.design.factory.sample3.interfaces.impl.Square;

/**
 * 扩展了 AbstractFactory 的工厂类，基于给定的信息生成实体类的对象
 *
 * @author snackpub
 * @date 2020/7/27
 */
public class ShapeFactory extends AbstractFactory {
    @Override
    public Color getColor(String colorType) {
        return null;
    }

    @Override
    public Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle();
        } else if (shapeType.equalsIgnoreCase("SQUARE")) {
            return new Square();
        }
        return null;
    }
}
