package com.snackpub.core.containers;

import java.util.LinkedList;

/**
 * 单项链表
 *
 * @author snackpub
 * @date 2021/7/3
 */
public class SList {


    public SListIterator iterator() {

        return null;
    }

    public String toString() {
        return "";
    }

}
class SListIterator<E> {

    private static class Node<E> {
        E item;
       Node<E> next;

        Node(E element,Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }

    private int index;


    SListIterator(int index) {
        this.index = index;
    }

    public void add(E e) {

    }
}
