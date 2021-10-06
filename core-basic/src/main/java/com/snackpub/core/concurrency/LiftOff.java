package com.snackpub.core.concurrency;//: concurrency/LiftOff.java
// Demonstration of the Runnable interface.

public class LiftOff implements Runnable {
  protected int countDown = 10; // Default
  private static int taskCount = 0;
  private final int id = taskCount++;
  public LiftOff() {}
  public LiftOff(int countDown) {
    this.countDown = countDown;
  }
  public String status() {
    return "#" + id + "(" +
      (countDown > 0 ? countDown : "Liftoff!") + "), ";
  }
  public void run() {
    while(countDown-- > 0) {
      System.out.print(status());
      // 调用yield(),对线程调度器（java线程机制的一部分，可以将cpu从一个线程转移给另一个线程）的一种建议。
      // 它在声明：我已经执行完生命周期中最重要的部分，此刻正是切换给其他任务执行的大好时机.
      Thread.yield();
    }
  }
} ///:~
