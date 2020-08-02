package com.snackpub.design.builder.builders;

import com.snackpub.design.builder.*;
import com.snackpub.design.builder.model.ChickenBurger;
import com.snackpub.design.builder.model.Coke;
import com.snackpub.design.builder.model.Pepsi;
import com.snackpub.design.builder.model.VegBurger;

/**
 * 负责构建meal
 *
 * @author snackpub
 * @date 2020/7/31
 */
public class MealBuilder {


    public Meal prepareVegMeal() {
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }

    public Meal prepareNonVegMeal() {
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }

}
