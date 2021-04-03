package com.snackpub.core.collection.holding;

import com.snackpub.core.collection.iterable.IterableClass;

import java.util.*;

/**
 * 在IterableClass.java示例中添加两种适配器方法:
 *
 * @author snackpub
 * @date 2021/4/3
 */
public class MultiIterableClass extends IterableClass {


    public Iterable<String> reversed() {
        return () -> new Iterator<String>() {

            int current = words.length - 1;

            @Override
            public boolean hasNext() {
                return current > -1;
            }

            @Override
            public String next() {
                return words[current--];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public Iterable<String> randomized() {
        return () -> {

            List<String> shuffled = new ArrayList<>(Arrays.asList(words));
            // 不会影响到原来的数组，只是打乱了shuffled中的引用
            Collections.shuffle(shuffled, new Random(47));
            return shuffled.iterator();
        };
    }

    public static void main(String[] args) {
        MultiIterableClass mtc = new MultiIterableClass();
        for (String s : mtc.reversed())
            System.out.print(s + " ");
        System.out.println();
        for (String s : mtc.randomized())
            System.out.print(s + " ");
        System.out.println(" ");

        for (String s : mtc)
            System.out.print(s + " ");
    }




}
