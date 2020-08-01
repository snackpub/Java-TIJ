package com.snackpub.design.builder;

/**
 * 鸡肉汉堡
 *
 * @author snackpub
 * @date 2020/7/31
 */
public class ChickenBurger extends AbstractBurger {

    @Override
    public String name() {
        return "Chicken Burger";
    }


    @Override
    public float price() {
        return 30.3f;
    }
}
