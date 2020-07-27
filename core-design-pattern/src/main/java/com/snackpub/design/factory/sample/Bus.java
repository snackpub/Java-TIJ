package com.snackpub.design.factory.sample;

/**
 * @author snackpub
 * @date 2020/7/27
 */
public class Bus implements Vehicle {

    @Override
    public void up() {
        System.out.println("Bus up up up up");
    }

    @Override
    public void dn() {
        System.out.println("Bus dn dn dn dn");
    }
}
