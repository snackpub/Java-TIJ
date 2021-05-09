package com.snackpub.design.template;

/**
 * @author snackpub
 * @date 2020/9/19
 */
public class Football extends Game {
    @Override
    void initialize() {
        System.out.println("足球游戏初始化!开始播放。");
    }

    @Override
    void startPlay() {
        System.out.println("足球游戏开始了。");
    }

    @Override
    void endPlay() {
        System.out.println("足球游戏结束了");
    }
}
