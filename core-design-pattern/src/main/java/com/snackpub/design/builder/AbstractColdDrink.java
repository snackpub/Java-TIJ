package com.snackpub.design.builder;

import com.snackpub.design.builder.pack.Bottle;

/**
 * 抽象出冷饮
 *
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
