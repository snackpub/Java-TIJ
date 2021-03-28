package com.snackpub.core.collection.queue;

import java.util.*;

/**
 * PriorityQueue 优先级队列
 *
 * @author snackpub
 * @date 2021/3/28
 */
public class PriorityQueueExample2 {

    /**
     * 你可以看到,重复是允许的,
     * 最小的值拥有最高的优先级(如果是String,空格也可以算作值,并且比字母的优先级高)。
     * 为了展示你可以使用怎样的方法通过提供自己的Comparator对对象来改变排序
     */
    public static void main(String[] args) {
        PriorityQueue<Double> priorityQueue = new PriorityQueue<>();
        Random random = new Random(47);
        for (int i = 0; i < 10; i++)
            priorityQueue.offer(random.nextDouble()); //调用offer()方法来插入一个而对象时，这个对象会在队列中被排序，默认使用队列中的自然排序
        QueueDemo.printQueuePoll(priorityQueue);


    }
}
