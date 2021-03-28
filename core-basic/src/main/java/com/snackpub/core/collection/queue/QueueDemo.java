package com.snackpub.core.collection.queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * @author snackpub
 * @date 2021/3/28
 */
public class QueueDemo {

    public static void printQueue(Queue queue) {
        while (queue.peek() != null)
            System.out.print(queue.remove() + " ");
        System.out.println();
    }

    public static void printQueuePoll(Queue queue) {
        while (queue.peek() != null) //
            System.out.println(queue.poll());
        System.out.println();
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        Random random = new Random(47);
        for (int i = 0; i < 10; i++)
            queue.offer(random.nextInt(i + 10));
        printQueue(queue);

        Queue<Character> characters = new LinkedList<>();
        for (char c : "Brontosaurus".toCharArray())
            characters.offer(c);
        printQueue(characters);

    }
}
