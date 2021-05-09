package com.snackpub.design.template;

/**
 * @author snackpub
 * @date 2020/9/19
 */
public abstract class Game {

    abstract void initialize();

    abstract void startPlay();

    abstract void endPlay();

    /**
     * 模板
     */
    public final void play() {
        initialize();
        startPlay();
        endPlay();
    }

}
