package com.snackpub.core.concurrency;//: concurrency/Restaurant.java
// The producer-consumer approach to task cooperation.

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.snackpub.core.util.Print.print;
import static com.snackpub.core.util.Print.printnb;

/**
 * 餐食
 */
class Meal {
  private final int orderNum;
  public Meal(int orderNum) { this.orderNum = orderNum; }
  public String toString() { return "Meal " + orderNum; }
}


class Busboy implements Runnable {
  private static int count;
  private final int id = count ++;
  private Restaurant restaurant;

  public Busboy(Restaurant restaurant) {
    this.restaurant = restaurant;
  }

  @Override
  public void run() {
    try {
      while (!Thread.interrupted()) {
        synchronized(this) {
          while(restaurant.meal == null)
            wait(); // ... for the chef to produce a meal
        }
        print("Busboy clean " + restaurant.meal);
      }
    } catch (InterruptedException e) {
      print("Busboy interrupted");
    }
  }
}

/**
 * 服务员
 */
class WaitPerson implements Runnable {
  private Restaurant restaurant;
  public WaitPerson(Restaurant r) { restaurant = r; }
  public void run() {
    try {
      while(!Thread.interrupted()) {
        synchronized(this) {
          // 该语句不断的测试正在等待的事务。乍看上去这有点奇怪——如果在等待一个订单，
          // 一旦你被唤醒，这个订单就必定是可获得的，对吗？
          // 问题是在并发应用中，某个其它的任务可能会在WaitPerson被唤醒时，
          // 会突然插足并拿走订单，唯一安全的方式是使用下面这种wait()
          // 的惯用法（当然要在恰当的同步内部，并采用防止可能信号错失的程序设计）
          // while(conditionIsNotMet)
          //    wait();
          // 这可以保证你在退出等待循环之前，条件将得到满足，并且如果你收到了关于某事物的通知，而它与这个条件并无
          // 关系（就像在使用notifyAll()时可能发生的情况一样），或者在你完全退出等待循环之前，这个条件发生了变化，
          // 都可以确保你重返等待状态.
          while(restaurant.meal == null)
            wait(); // ... for the chef to produce a meal
        }
        print("Waitperson got " + restaurant.meal);
          // 通知人员清理
        synchronized(restaurant.busboy) {
          restaurant.busboy.notifyAll();
        }
        synchronized(restaurant.chef) {
          restaurant.meal = null;
          // 通知厨师开始准备.
          restaurant.chef.notifyAll(); // Ready for another
        }
      }
    } catch(InterruptedException e) {
      print("WaitPerson interrupted");
    }
  }
}

/**
 * 厨师
 */
class Chef implements Runnable {
  private Restaurant restaurant;
  private int count = 0;
  public Chef(Restaurant r) { restaurant = r; }
  public void run() {
    try {
      while(!Thread.interrupted()) {
        synchronized(this) {
          while(restaurant.meal != null)
            wait(); // ... for the meal to be taken
        }
        if(++count == 10) {
          print("Out of food, closing");
          restaurant.exec.shutdownNow();
          // return; // return后西面的代码将不会执行,也不会进入run循环的顶部，WaitPerson抛出中断异常.
        }
        printnb("Order up! ");
        synchronized(restaurant.waitPerson) {
          restaurant.meal = new Meal(count);
          // 通知服务员取餐；只有一个任务在waitPerson的锁上等待，即waitPerson任务本身,处于这个原因，
          // 理论上我们可以调用notify()而不是notifyAll()。但是在更复杂的情况下，
          // 可能会有多个任务在某个特定对象上等待，因此你不知道那个任务将被唤醒. 因此，调用notifyAll()要更
          // 安全一些，这样可以唤醒等待这个锁的所有任务，而每个任务都必须决定这个通知是否与自己相关.
          restaurant.waitPerson.notifyAll();
        }
        // 移除掉sleep()调用，那么任务将会回到run()的顶部，
        // 并由于Thread.interrupted()测试而退出，同时并不抛异常.
        TimeUnit.MILLISECONDS.sleep(100);
      }
    } catch(InterruptedException e) {
      print("Chef interrupted");
    }
  }
}

/**
 * 餐馆
 */
public class Restaurant {
  Meal meal;
  ExecutorService exec = Executors.newCachedThreadPool();
  final Busboy busboy = new Busboy(this);
  final WaitPerson waitPerson = new WaitPerson(this);
  final Chef chef = new Chef(this);
  public Restaurant() {
    exec.execute(chef);
    exec.execute(waitPerson);
    exec.execute(busboy);
  }
  public static void main(String[] args) {
    new Restaurant();
  }
} /* Output:
Order up! Waitperson got Meal 1
Order up! Waitperson got Meal 2
Order up! Waitperson got Meal 3
Order up! Waitperson got Meal 4
Order up! Waitperson got Meal 5
Order up! Waitperson got Meal 6
Order up! Waitperson got Meal 7
Order up! Waitperson got Meal 8
Order up! Waitperson got Meal 9
Out of food, closing
WaitPerson interrupted
Order up! Chef interrupted
*///:~
