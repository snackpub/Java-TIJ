package com.snackpub.design.adapter.sample$3;

/**
 * @author snackpub
 * @date 2020/4/27
 */
public class RoundPeg implements Circularity {


    private double radius;

    public RoundPeg(double radius) {
        this.radius = radius;
    }

    @Override
    public double getRadius() {
        return radius;
    }
}
