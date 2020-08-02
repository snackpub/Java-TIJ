package com.snackpub.design.builder2;

import com.snackpub.design.builder2.Computer;

/**
 * 具体产品类
 *
 * @author snackpub
 * @date 2020/8/2
 */
public class MacBook extends Computer {

    protected MacBook() {

    }

    @Override
    public void setOs() {
        mOs = "Max OS x 15";
    }

}
