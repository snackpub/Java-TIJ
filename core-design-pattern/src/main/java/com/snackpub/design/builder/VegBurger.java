package com.snackpub.design.builder;

/**
 * 素食汉堡
 *
 * @author snackpub
 * @date 2020/7/31
 */
public class VegBurger extends AbstractBurger {

    @Override
    public String name() {
        return "Veg Burger";
    }

    @Override
    public float price() {
        return 20.4f;
    }
}
