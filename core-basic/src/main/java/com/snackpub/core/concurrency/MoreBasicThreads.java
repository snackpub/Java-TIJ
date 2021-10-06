package com.snackpub.core.concurrency;//: concurrency/MoreBasicThreads.java
// Adding more threads.

// 线程被混进换出混在了一起，每次运行都是不确定性的。这种交换是由线程调度器自动控制的。
// 如果你的computer有多个处理器，线程调度器会在这些处理器之间默默的分发线程。
// 你不能依赖于任何线程的一致性行为。最好的方法是在编写使用线程的代码时，尽可能地保守。
public class MoreBasicThreads {
  public static void main(String[] args) {
    for(int i = 0; i < 5; i++)
      new Thread(new LiftOff()).start();
    System.out.println("Waiting for LiftOff");
  }
} /* Output: (Sample)
Waiting for LiftOff
#0(9), #1(9), #2(9), #3(9), #4(9), #0(8), #1(8), #2(8), #3(8), #4(8), #0(7), #1(7), #2(7), #3(7), #4(7), #0(6), #1(6), #2(6), #3(6), #4(6), #0(5), #1(5), #2(5), #3(5), #4(5), #0(4), #1(4), #2(4), #3(4), #4(4), #0(3), #1(3), #2(3), #3(3), #4(3), #0(2), #1(2), #2(2), #3(2), #4(2), #0(1), #1(1), #2(1), #3(1), #4(1), #0(Liftoff!), #1(Liftoff!), #2(Liftoff!), #3(Liftoff!), #4(Liftoff!),
*///:~
