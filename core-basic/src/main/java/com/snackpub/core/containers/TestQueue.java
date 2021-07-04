package com.snackpub.core.containers;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * @author snackpub
 * @date 2021/7/4
 */
public class TestQueue implements Comparable<TestQueue> {

    private final Integer rand = new Random().nextInt(100);


    @Override
    public int compareTo(TestQueue o) {
        if (rand > o.rand)
            return +1;
        if (rand.equals(o.rand))
            return 0;
        return -1;
    }

    @Override
    public String toString() {
        return "TestQueue{" +
                "rand=" + rand +
                '}';
    }

    public static void main(String[] args) {
        PriorityQueue<TestQueue> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new TestQueue());
        priorityQueue.add(new TestQueue());
        priorityQueue.add(new TestQueue());
        priorityQueue.add(new TestQueue());

        while (!priorityQueue.isEmpty()) {
            // poll为空时不会抛出异常，而是返回null
            TestQueue poll = priorityQueue.poll();
            System.out.println(poll);
        }
    }
}
