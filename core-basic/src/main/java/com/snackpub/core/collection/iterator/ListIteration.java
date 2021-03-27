package com.snackpub.core.collection.iterator;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/**
 * @author snackpub
 * @date 2021/3/27
 */
public class ListIteration {

    private Random random = new Random();
    private String[] randElement = ("f,g,h,i,j,k").split(",");

    public String randomElement() {
        return randElement[random.nextInt(randElement.length - 1)];
    }

    public static void main(String[] args) {
        List<String> strs = Arrays.asList("a,b,c,d,e".split(","));
        ListIterator<String> it = strs.listIterator();
        while (it.hasNext())
            System.out.println(it.next() + " , " + it.nextIndex() + ", " + it.previousIndex());
        System.out.println();
        // 向后
        while (it.hasPrevious())
            System.out.println(it.previous());
        System.out.println();
        System.out.println(strs);
        it = strs.listIterator(3);
        while (it.hasNext()) {
            it.next();
            it.set(new ListIteration().randomElement()); // 替换在列表中从第3位开始向前的所有元素
        }
        System.out.println(strs);
    }
}
