package com.snackpub.core.io;//: io/FileLocking.java
import java.nio.channels.*;
import java.util.concurrent.*;
import java.io.*;

/**
 * 文件加锁，允许我们同时访问一个作为共享资源的文件。可能竞争同一资源文件的线程在不同的进程中，在不同的java虚拟中。
 * java的文件锁对其它的操作系统进程是可见的，因为java文件加锁直接映射到了本地操作系统的加锁工具。
 */
public class FileLocking {
  public static void main(String[] args) throws Exception {
    FileOutputStream fos= new FileOutputStream("file.txt");
    FileLock fl = fos.getChannel().tryLock(); // 调用tryLock(非阻塞)或者lock(阻塞)
    //  tryLock(0L, Long.MAX_VALUE, false); 给出参数，对部分内容加锁
    //  fos.getChannel().lock() 无参数的方式调用会对整个文件加锁
    if(fl != null) {
      // 共享或独占锁必须由操作系统提供支持，如果操作系统不支持共享锁并为每一个请求创建一个锁，那么它就会使用独占锁
      System.out.format("锁的类型（共享或独占）isShared: {%s}%n",fl.isShared());
      System.out.println("Locked File");
      TimeUnit.MILLISECONDS.sleep(100);
      fl.release();
      System.out.println("Released Lock");
    }
    fos.close();
  }
} /* Output:
Locked File
Released Lock
*///:~
