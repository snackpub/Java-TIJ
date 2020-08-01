package com.snackpub.design.builder;

/**
 * @author snackpub
 * @date 2020/7/31
 */
public abstract class AbstractColdDrink implements Item {


    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract float price();
}
