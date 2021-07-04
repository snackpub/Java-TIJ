package com.snackpub.core.util;

import java.util.LinkedList;

/**
 * 双向队列（双端队列）就像是一个队列，但是你可以再任何一端添加或移除元素.
 *
 * @param <T> 泛型
 * @date 2021/7/4
 */
public class Deque<T> {
  private LinkedList<T> deque = new LinkedList<>();
  public void addFirst(T e) { deque.addFirst(e); }
  public void addLast(T e) { deque.addLast(e); }
  public T getFirst() { return deque.getFirst(); }
  public T getLast() { return deque.getLast(); }
  public T removeFirst() { return deque.removeFirst(); }
  public T removeLast() { return deque.removeLast(); }
  public int size() { return deque.size(); }
  public String toString() { return deque.toString(); }
  // And other methods as necessary...
} ///:~
