package com.snackpub.design.decorator.game;

/**
 * @author snackpub
 * @date 2020/9/19
 */
public class RunGame implements Game {



    @Override
    public void execute() {
        System.out.println("跑动逻辑");
    }
}
