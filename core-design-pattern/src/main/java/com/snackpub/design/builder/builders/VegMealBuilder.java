package com.snackpub.design.builder.builders;

import com.snackpub.design.builder.builders.Builder;
import com.snackpub.design.builder.model.Coke;
import com.snackpub.design.builder.Meal;
import com.snackpub.design.builder.model.VegBurger;

/**
 * 素食汉堡建造者
 *
 * @author snackpub
 * @date 2020/8/1
 */
public class VegMealBuilder extends Builder {

    @Override
    public void prepareVegMeal() {
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
    }

    @Override
    public void prepareNonVegMeal() {
    }

    @Override
    public Meal getMeal() {
        return meal;
    }
}
