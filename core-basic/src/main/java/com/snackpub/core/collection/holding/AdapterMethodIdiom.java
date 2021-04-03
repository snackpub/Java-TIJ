package com.snackpub.core.collection.holding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * 适配器方法，提供特定的接口满足foreach语句
 *
 *
 * @author snackpub
 * @date 2021/4/3
 */

class ReversibleArrayList<T> extends ArrayList<T> {
    public ReversibleArrayList(Collection<T> c) {
        super(c);
    }

    public Iterable<T> reversed() {
        return () -> new Iterator<T>() {
            int current = size() - 1;

            @Override
            public boolean hasNext() {
                return current > -1;
            }

            @Override
            public T next() {
                return get(current--);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}

public class AdapterMethodIdiom {

    public static void main(String[] args) {
        // 如果直接将ral对象置于foreach语句中上调用reversed0方法,就会产生不同的行为。,将得到(默认的)前向迭代器。但是如果在该对象,
        ReversibleArrayList<String> ral = new ReversibleArrayList<>(Arrays.asList("To be or not to be".split(" ")));
        // 通过iterator()获取普通迭代器:
        for (String s : ral)
            System.out.println(s + " ");
        System.out.println();

        // 你可以选择将它作为可迭代对象
        for (String s : ral.reversed())
            System.out.println(s + " ");
    }


}
