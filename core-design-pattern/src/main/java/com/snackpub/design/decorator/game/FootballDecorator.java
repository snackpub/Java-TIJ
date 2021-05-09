package com.snackpub.design.decorator.game;

import lombok.extern.slf4j.Slf4j;

/**
 * @author snackpub
 * @date 2020/9/19
 */
@Slf4j
public class FootballDecorator implements Game {
    Game game;

    public FootballDecorator(Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        log.info("足球游戏开始了");
        this.game.execute();
        log.info("足球游戏结束了");
    }
}
