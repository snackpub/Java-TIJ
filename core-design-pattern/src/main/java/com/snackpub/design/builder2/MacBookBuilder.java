package com.snackpub.design.builder2;

/**
 * @author snackpub
 * @date 2020/8/2
 */
public class MacBookBuilder extends Builder2 {

    private Computer computer = new MacBook();

    @Override
    void builderBoard(String board) {
        computer.setmBoard(board);
    }

    @Override
    void builderDisplay(String display) {
        computer.setmDisplay(display);
    }

    @Override
    void builderOs() {
        computer.setOs();
    }

    @Override
    Computer builder() {
        return computer;
    }
}
