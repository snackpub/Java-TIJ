package com.snackpub.design.template;

/**
 * @author snackpub
 * @date 2020/9/19
 */
public class Cricket extends Game {

    @Override
    void initialize() {
        System.out.println("板球游戏初始化!开始播放。");
    }

    @Override
    void startPlay() {
        System.out.println("板球比赛开始了。享受游戏!");
    }

    @Override
    void endPlay() {
        System.out.println("板球比赛结束了!");
    }
}
