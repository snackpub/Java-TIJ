package com.snackpub.core.thread;

public class ThreadState implements Runnable {

    public synchronized void waitForMoment() throws InterruptedException {
        wait(500);
        //使用 wait() 方法使当前线程等待 500 毫秒
        //或者等待其他线程调用 notify() 或 notifyAll() 方法来唤醒
    }

    public synchronized void waitForever() throws InterruptedException {
        wait();
        //不填入时间就意味着使当前线程永久等待，
        //只能等到其他线程调用 notify() 或 notifyAll() 方法才能唤醒
    }

    public synchronized void notifyNow() throws InterruptedException {
        notify();
        //使用 notify() 方法来唤醒那些因为调用了 wait() 方法而进入等待状态的线程
    }

    @Override
    public void run() {
        //这里用异常处理是为了防止可能的中断异常
        //如果任何线程中断了当前线程，则抛出该异常
        try {
            // 在新线程中运行 waitMoment() 方法
            waitForMoment();
            // 在新线程中运行 waitForever() 方法
            waitForever();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
