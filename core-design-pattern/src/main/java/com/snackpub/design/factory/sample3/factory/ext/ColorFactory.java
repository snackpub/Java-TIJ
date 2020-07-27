package com.snackpub.design.factory.sample3.factory.ext;

import com.snackpub.design.factory.sample3.factory.AbstractFactory;
import com.snackpub.design.factory.sample3.interfaces.Color;
import com.snackpub.design.factory.sample3.interfaces.Shape;
import com.snackpub.design.factory.sample3.interfaces.impl.Blue;
import com.snackpub.design.factory.sample3.interfaces.impl.Green;
import com.snackpub.design.factory.sample3.interfaces.impl.Red;

/**
 * @author snackpub
 * @date 2020/7/27
 */
public class ColorFactory extends AbstractFactory {

    @Override
    public Shape getShape(String shapeType) {
        return null;
    }

    @Override
    public Color getColor(String color) {
        if (color == null) {
            return null;
        }
        if (color.equalsIgnoreCase("RED")) {
            return new Red();
        } else if (color.equalsIgnoreCase("GREEN")) {
            return new Green();
        } else if (color.equalsIgnoreCase("BLUE")) {
            return new Blue();
        }
        return null;
    }
}
