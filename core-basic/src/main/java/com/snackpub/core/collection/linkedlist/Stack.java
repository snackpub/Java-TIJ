package com.snackpub.core.collection.linkedlist;

import java.util.LinkedList;

/**
 * “栈”通常是指“后进先出” (LIFO)的容器。有时栈也被称为叠加栈,因为最后“压入”栈的元素,第一个“弹出”栈。
 *
 * @author snackpub
 * @date 2021/3/27
 */
public class Stack<T> {

    private LinkedList<T> storage = new LinkedList<>();

    // 接受一个T类型的对象 在列表的开头插入指定的元素。
    public void push(T v) {
        storage.addFirst(v);
    }

    // 提供栈顶元素，并不将其从栈顶移除
    public T peek(T v) {
        return storage.getFirst();
    }

    // 移出并返回栈顶元素
    public T pop() {
        return storage.removeFirst();
    }

    public boolean empty() {
        return storage.isEmpty();
    }

    public int size() {
        return storage.size();
    }

    public String toString() {
        return storage.toString();
    }
}
