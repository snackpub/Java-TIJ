package com.snackpub.design.builder2;

/**
 * 计算机抽象类
 *
 * @author snackpub
 * @date 2020/8/2 23:08
 */
public abstract class Computer {

    /**
     * 主板
     */
    protected String mBoard;
    protected String mDisplay;
    protected String mOs;

    public Computer() {
    }

    public String getmBoard() {
        return mBoard;
    }

    public void setmBoard(String mBoard) {
        this.mBoard = mBoard;
    }

    public String getmDisplay() {
        return mDisplay;
    }

    public void setmDisplay(String mDisplay) {
        this.mDisplay = mDisplay;
    }



    public abstract void setOs();

    @Override
    public String toString() {
        return "Computer{" +
                "mBoard='" + mBoard + '\'' +
                ", mDisplay='" + mDisplay + '\'' +
                ", mOs='" + mOs + '\'' +
                '}';
    }
}
