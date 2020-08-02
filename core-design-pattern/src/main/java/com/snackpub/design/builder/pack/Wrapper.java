package com.snackpub.design.builder.pack;

import com.snackpub.design.builder.Packing;

/**
 * 包装袋
 *
 * @author snackpub
 * @date 2020/7/31
 */
public class Wrapper implements Packing {

    @Override
    public String pack() {
        return "Wrapper";
    }
}
