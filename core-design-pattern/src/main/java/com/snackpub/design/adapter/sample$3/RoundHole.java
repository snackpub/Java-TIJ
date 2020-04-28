package com.snackpub.design.adapter.sample$3;

/**
 * 圆孔
 *
 * @author snackpub
 * @date 2020/4/27
 */
public class RoundHole {

    /**
     * 半径
     */
    private double radius;

    public RoundHole(double radius) {
        this.radius = radius;
    }

    /**
     * 判断桩可完全塞进圆孔中
     *
     * @param peg
     * @return bool
     */
    public boolean getFits(Circularity peg) {
        return peg.getRadius() <= radius;
    }
}
