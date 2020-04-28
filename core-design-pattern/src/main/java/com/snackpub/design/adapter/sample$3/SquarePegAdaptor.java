package com.snackpub.design.adapter.sample$3;

/**
 * 方桩适配
 *
 * @author snackpub
 * @date 2020/4/27
 */
public class SquarePegAdaptor implements Circularity {

    /**
     * 被适配者
     */
    private SquarePeg adaptee;

    public SquarePegAdaptor(SquarePeg adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public double getRadius() {
        // 公式将方形的边长除以 2，并求该数的平方，再将结果乘以 2，最后再将所得结果开二次方根
        // 如果该值小于圆形的半径，则桩可完全塞进圆孔中
        return Math.sqrt(Math.pow(adaptee.getWidth() / 2, 2) * 2);
    }
}
