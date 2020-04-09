package com.snackpub.core.lambda;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * 传统 for 循环的函数式替代方案
 *
 * @author snakcpub
 * @date 2020/4/9
 */
public class Streamrange {

    public static void main(String[] args) {
        // 清单1
        System.out.println("Get for");
        for (int i = 0; i < 5; i++) {
            System.out.println(i + "....");
        }
        // Java 8 提供了一种更简单、更优雅的替代方法：IntStream 的 range 方法
        // 清单2
        IntStream.range(1, 5).forEach(System.out::println);

        // 在清单 2 中，我们看到并没有显著减少代码量，但降低了它的复杂性。这样做有两个重要原因：
        // 1. 不同于for，range不会强迫我们初始化某个变量
        // 2. 迭代会自动执行，不需要我们像循环一样定义增量
        // 在语义上，最初的 for 循环中的变量 i 是一个可变变量。理解 range 和类似方法的价值对理解该设计的结果很有帮助。


        // 可变变量与参数
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 5; i++) {
            int temp = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    // 如果取消注释下一行将导致一个错误
                    // System.out.println("Running task " + i);
                    // local variables referenced from an inner class must be final or effectively final

                    System.out.println("Running task " + temp);
                }
            });
        }
//        executorService.shutdown();
        // 我们有一个匿名的内部类实现了 Runnable 接口。我们想在 run 方法中访问索引变量 i，但编译器不允许这么做
        // 作为此限制的解决办法，我们可以创建一个局部临时变量，比如 temp，它是索引变量的一个副本。
        // 每次新的迭代都会创建变量 temp。在 Java 8 以前，我们需要将该变量标记为 final。
        // 从 Java 8 开始，可以将它视为实际的最终结果，因为我们不会再更改它。
        // 无论如何，由于事实上索引变量是一个在迭代中改变的变量，for 循环中就会出现这个额外变量。

        // 现在尝试使用 range 函数解决同一个问题
        // 在内部类中使用拉姆达参数
        // Runnable 是一个函数接口，所以我们可以轻松地将匿名的内部类替换为拉姆达表达式
        IntStream.range(0, 5).forEach(i -> executorService.submit(() -> {
            System.out.println("Running task " + i);
        }));
        executorService.shutdown();

        // 封闭范围
        // 迭代此范围时，我们会获得包含边界值 5 在内的值  0、1、……5
        IntStream.rangeClosed(0, 5).forEach(System.out::print);


    }
}
