package com.snackpub.design.decorator.game;


import java.util.logging.Logger;

/**
 * @author snackpub
 * @date 2020/9/19
 */
public class CricketDecorator implements Game {
    Game game;

    public CricketDecorator(Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        Logger logger = Logger.getLogger("Cricket");
        logger.info("板球游戏开始");
        this.game.execute();
        logger.info("板球游戏结束了");
    }
}
