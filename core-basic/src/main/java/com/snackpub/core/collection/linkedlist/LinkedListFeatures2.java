package com.snackpub.core.collection.linkedlist;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * 创建一个空的LinkedList<Integer>,通过使用Listiterator,将若干个Integer插入这个List中,插入时,总是将它们插入到List的中间。
 *
 * @author snackpub
 * @date 2021/3/27
 */
public class LinkedListFeatures2 {


    public static void main(String[] args) {
        LinkedList<Integer> pets = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            ListIterator<Integer> it = pets.listIterator(pets.size() / 2);
            it.add(i);
        }
        System.out.println(pets);
    }
}

