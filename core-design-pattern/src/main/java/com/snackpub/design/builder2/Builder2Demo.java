package com.snackpub.design.builder2;

/**
 * @author snackpub
 * @date 2020/8/2
 */
public class Builder2Demo {

    public static void main(String[] args) {
        Builder2 builder2 = new MacBookBuilder();

        Director director = new Director(builder2);

        director.construct("英特尔主板", "Retina显示器");

        Computer computer = builder2.builder();

        System.out.println(computer.toString());
    }
}
