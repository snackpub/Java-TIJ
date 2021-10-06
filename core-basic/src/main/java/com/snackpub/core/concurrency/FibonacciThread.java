package com.snackpub.core.concurrency;
// Generate a Fibonacci sequence.
import com.snackpub.core.util.Generator;
import com.snackpub.core.util.Print;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

class Fibonacci extends Thread implements Generator<Integer> {
  private int count = 0;
  private final int loopcount;

  Fibonacci(int loopcount) {
     this.loopcount = loopcount;
   }

   @Override
   public void run() {
     IntStream.range(0 , loopcount).forEach(index->{
        Print.printnb(next() +" ");
     });
   }

  public Integer next() { return fib(count++); }
  private int fib(int n) {
    if(n < 2) return 1;
    return fib(n-2) + fib(n-1);
  }
}

public class FibonacciThread  {
  public static void main(String[] args) throws InterruptedException {
    ExecutorService executorService = Executors.newCachedThreadPool();
    executorService.execute(new Fibonacci(18));
    executorService.execute(new Fibonacci(18));
    executorService.execute(new Fibonacci(18));
    executorService.execute(new Fibonacci(18));
    executorService.execute(new Fibonacci(18));
    executorService.execute(new Fibonacci(18));
    executorService.execute(new Fibonacci(18));
    TimeUnit.MILLISECONDS.sleep(500);
    executorService.shutdown();
  }
} /* Output:
1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 1597 2584
*///:~
