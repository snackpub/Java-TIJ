package com.snackpub.design.builder2;

/**
 * @author snackpub
 * @date 2020/8/2
 */
public class Director {

    Builder2 builder = null;

    public Director(Builder2 builder2) {
        this.builder = builder2;
    }


    public void construct(String board, String display) {
        builder.builderBoard(board);
        builder.builderDisplay(display);
        builder.builderOs();
    }
}
