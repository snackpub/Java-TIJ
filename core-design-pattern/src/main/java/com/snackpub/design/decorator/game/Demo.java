package com.snackpub.design.decorator.game;

/**
 * @author snackpub
 * @date 2020/9/19
 */
public class Demo {

    public static void main(String[] args) {

        Game football = new FootballDecorator(new CricketDecorator(new RunGame()));
        football.execute();
    }
}
