package com.snackpub.design.builder;

/**
 * 食物条目接口
 *
 * @author snackpub
 * @date 2020/7/31
 */
public interface Item {

    String name();

    /**
     * 包装
     *
     * @return packing
     */
    Packing packing();

    float price();

}
