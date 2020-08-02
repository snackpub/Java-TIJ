package com.snackpub.design.builder;

import com.snackpub.design.builder.builders.Builder;

/**
 * 指挥者/监工
 *
 * @author snackpub
 * @date 2020/8/1
 */
public class Director {

    public void construct(Builder builder) {
        builder.prepareNonVegMeal();
        builder.prepareVegMeal();
    }
}
