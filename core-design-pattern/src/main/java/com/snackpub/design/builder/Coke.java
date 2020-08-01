package com.snackpub.design.builder;

/**
 * 可口可乐
 *
 * @author snackpub
 * @date 2020/7/31
 */
public class Coke extends AbstractColdDrink {


    @Override
    public float price() {
        return 5.0f;
    }

    @Override
    public String name() {
        return "Coke";
    }
}
