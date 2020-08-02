package com.snackpub.design.builder;

import com.snackpub.design.builder.builders.MealBuilder;
import com.snackpub.design.builder.builders.NonVegMealBuilder;
import com.snackpub.design.builder.builders.VegMealBuilder;

/**
 * @author snackpub
 * @date 2020/7/31
 */
public class BuilderPatternDemo {




    public static void main(String[] args) {
        MealBuilder mealBuilder = new MealBuilder();
        Meal vegMeal = mealBuilder.prepareVegMeal();
        System.out.println("Veg Meal");
        vegMeal.showItems();
        System.out.println("Total Cost: " +vegMeal.getCost());


        Meal nonVegMeal = mealBuilder.prepareNonVegMeal();
        System.out.println("\n\nNon-Veg Meal");
        nonVegMeal.showItems();
        System.out.println("Total Cost: " +nonVegMeal.getCost());

        //=======================================================
        System.err.println("===========================");
        Director director = new Director();
        VegMealBuilder b1 = new VegMealBuilder();
        NonVegMealBuilder b2 = new NonVegMealBuilder();

        director.construct(b1);
        director.construct(b2);

        Meal vegMeal2 = b1.getMeal();
        System.out.println("Veg Meal");
        vegMeal2.showItems();
        System.out.println("Total Cost: " +vegMeal2.getCost());


        Meal nonVegMeal2 = b2.getMeal();
        System.out.println("\n\nNon-Veg Meal");
        nonVegMeal2.showItems();
        System.out.println("Total Cost: " +nonVegMeal2.getCost());
    }

}
