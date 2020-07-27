package com.snackpub.design.factory.sample3.interfaces.impl;

import com.snackpub.design.factory.sample3.interfaces.Shape;

/**
 * @author snackpub
 * @date 2020/7/27
 */
public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("inside Rectangle::draw() method.");
    }
}
