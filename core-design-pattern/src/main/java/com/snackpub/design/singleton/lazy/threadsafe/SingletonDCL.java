package com.snackpub.design.singleton.lazy.threadsafe;

/**
 * 双检锁/双重校验锁(DLC: Double-checked locking)
 *
 * @author snackpub
 * @date 2020/7/30
 */
public class SingletonDCL {

    //===============================================
    /**
     * JDK 版本：JDK1.5 起
     * <p>
     * 是否 Lazy 初始化：是
     * <p>
     * 是否多线程安全：是
     * <p>
     * 实现难度：较复杂
     * <p>
     * 描述：这种方式采用双锁机制，安全且在多线程情况下能保持高性能。
     * getInstance() 的性能对应用程序很关键。
     */

    private volatile static SingletonDCL instance;

    private SingletonDCL() {


    }

    public static SingletonDCL getInstance() {
        if (instance == null) {
            synchronized (SingletonDCL.class) {
                if (instance == null) {
                    instance = new SingletonDCL();
                }
            }
        }
        return instance;
    }

}
