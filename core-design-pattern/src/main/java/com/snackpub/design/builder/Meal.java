package com.snackpub.design.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author snackpub
 * @date 2020/7/31
 */
public class Meal {

    private List<Item> items = new ArrayList<>();


    public void addItem(Item item) {
        items.add(item);
    }

    private float cost = 0.0f;

    public float getCost() {
        items.forEach(item -> {
            cost += item.price();
        });

        return cost;
    }

    public void showItems() {
        items.forEach(it->{
            System.out.print("item:" + it.name());
            System.out.print(" ,Packing: " + it.packing().pack());
            System.out.println(", Price: " + it.price());
        });
    }


}
