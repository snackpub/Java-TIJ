package com.snackpub.design.adapter.sample;

/**
 * 适配器类
 *
 * @author snackpub
 * @date 2020/4/21
 */
public class Adapter implements Target {

    /**
     * 持有需要被适配的接口对象
     */
    private Adaptee adaptee;

    /**
     * 构造方法，传入需要被适配的对象
     *
     * @param adaptee 被适配的对象
     */
    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        adaptee.specificRequest();
    }
}
