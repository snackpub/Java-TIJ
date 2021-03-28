package com.snackpub.core.collection.queue;

import java.util.*;

/**
 * PriorityQueue 优先级队列
 * <p>
 * PriorityQueue可以确保当你调用peek(), poll()和remove()方法时,获取的元素将是队列中优先級最高的元素
 *
 * @author snackpub
 * @date 2021/3/28
 */
public class PriorityQueueExample {

    /**
     * 你可以看到,重复是允许的,
     * 最小的值拥有最高的优先级(如果是String,空格也可以算作值,并且比字母的优先级高)。
     * 为了展示你可以使用怎样的方法通过提供自己的Comparator对对象来改变排序
     */
    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        Random random = new Random(47);
        for (int i = 0; i < 10; i++)
            priorityQueue.offer(random.nextInt(i + 10));
        QueueDemo.printQueue(priorityQueue);

        List<Integer> ints = Arrays.asList(25, 22, 20, 18, 14, 9, 3, 1, 1, 2, 3, 9, 14, 18, 21, 23, 25);
        priorityQueue = new PriorityQueue<>(ints);
        QueueDemo.printQueue(priorityQueue);

        priorityQueue = new PriorityQueue<>(ints.size(), Collections.reverseOrder());
        priorityQueue.addAll(ints);
        QueueDemo.printQueue(priorityQueue);

        String fact = "EDUCATION SHOULD ESCHEW OBFUSCATION";
        List<String> strings = Arrays.asList(fact.split(" "));
        PriorityQueue<String> stringPQ = new PriorityQueue<>(strings);
        QueueDemo.printQueue(stringPQ);

        stringPQ = new PriorityQueue<>(strings.size(), Collections.reverseOrder());
        stringPQ.addAll(strings);
        QueueDemo.printQueue(stringPQ);

        HashSet<Character> charSet = new HashSet<>();
        for (char c : fact.toCharArray())
            charSet.add(c);
        PriorityQueue<Character> characterPQ = new PriorityQueue<>(charSet);
        QueueDemo.printQueue(characterPQ);
    }
}
