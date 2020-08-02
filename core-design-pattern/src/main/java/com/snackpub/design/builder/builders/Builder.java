package com.snackpub.design.builder.builders;


import com.snackpub.design.builder.Meal;

/**
 * 抽象建造者
 *
 * @author snack
 * @date 2020/8/1 17:02
 */
public /*abstract*/ class Builder {

    public Meal meal = new Meal();

    public void prepareVegMeal() {

    }

    public void prepareNonVegMeal() {
    }

    public Meal getMeal() {
        return null;
    }

}
