package com.snackpub.design.builder.model;

import com.snackpub.design.builder.AbstractColdDrink;

/**
 * 百事可乐
 *
 * @author snackpub
 * @date 2020/7/31
 */
public class Pepsi extends AbstractColdDrink {

    @Override
    public String name() {
        return "Pepsi";
    }

    @Override
    public float price() {
        return 6.0f;
    }
}
