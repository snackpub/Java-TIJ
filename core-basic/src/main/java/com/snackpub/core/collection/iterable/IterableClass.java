package com.snackpub.core.collection.iterable;

import java.util.Iterator;

/**
 * Iterable 接口，该接口能够产生一个Iterator的iterator()方法,并且Iterable接口被foreach用来在序列中移动。因此如果你创建了
 * 任何实现Iterable的类,都可以将它用于foreach语句中
 *
 * @author snackpub
 * @date 2021/3/27
 */
public class IterableClass implements Iterable<String> {

    protected String[] words = ("And that is how we know the Earth to be banana-shaped.").split(" ");

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < words.length;
            }

            @Override
            public String next() {
                return words[index++];
            }

            @Override
            public void remove() { // not implemented
                throw new UnsupportedOperationException();
            }
        };
    }

    public static void main(String[] args) {
        for (String s : new IterableClass()) {
            System.out.println(s + " ");
        }
    }
}
