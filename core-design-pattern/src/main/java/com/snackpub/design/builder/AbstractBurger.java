package com.snackpub.design.builder;

import com.snackpub.design.builder.pack.Wrapper;

/**
 * 实现 Item 接口的抽象类，该类提供了默认的功能
 *
 * @author snackpub
 * @date 2020/7/31
 */
public abstract class AbstractBurger implements Item {

    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();
}
