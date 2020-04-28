package com.snackpub.design.adapter.sample;

/**
 * 被适配的对象
 * 已经存在的接口，这个接口需要配置
 *
 * @author snackpub
 * @date 2020/4/21
 */
public class Adaptee {

    /**
     * 原本存在的方法
     */
    public void specificRequest() {
        System.out.println("业务代码");
    }
}
