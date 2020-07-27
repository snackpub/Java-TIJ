package com.snackpub.design.factory.sample;

/**
 * @author snackpub
 * @date 2020/7/27
 */
public class Bike implements Vehicle {
    @Override
    public void up() {
        System.out.println("Bike up up up up.");
    }

    @Override
    public void dn() {
        System.out.println("Bike dn dn dn dn.");
    }
}
