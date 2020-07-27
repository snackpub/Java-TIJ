package com.snackpub.design.factory.sample;

/**
 * @author snackpub
 * @date 2020/7/27
 */
public class Car implements Vehicle {
    @Override
    public void up() {
        System.out.println("Car up up up up");
    }

    @Override
    public void dn() {
        System.out.println("Car dn dn dn dn");
    }
}
