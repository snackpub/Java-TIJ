package com.snackpub.design.builder.builders;

import com.snackpub.design.builder.builders.Builder;
import com.snackpub.design.builder.model.ChickenBurger;
import com.snackpub.design.builder.Meal;
import com.snackpub.design.builder.model.Pepsi;

/**
 * 非素食汉堡构建者
 *
 * @author snackpub
 * @date 2020/8/1
 */
public class NonVegMealBuilder extends Builder {


    @Override
    public void prepareVegMeal() {
    }

    @Override
    public void prepareNonVegMeal() {
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
    }

    @Override
    public Meal getMeal() {
        return meal;
    }
}
