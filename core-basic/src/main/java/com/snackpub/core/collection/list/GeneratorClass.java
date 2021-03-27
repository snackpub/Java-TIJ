package com.snackpub.core.collection.list;

import java.util.*;

/**
 * 练习4: (3)创建一个生成器类,它可以在每次调用其next()方法时,
 * 产生你由你最喜欢的电影(你可以使用Snow white或Star Wars)的字符构成的名字(作为String对象)。
 * 在字符列表中的电影名用完之后,循环到这个字符列表的开始处。
 * 使用这个生成器来填充数组、ArrayList,LinkedList, HashSet, LinkedHashSet和TreeSet,然后打印每一个容器。
 *
 * @author snackpub
 * @date 2021/3/27
 */
public class GeneratorClass implements Iterable<String> {

    private String[] movieName = ("天鹅湖,复仇者联盟,三体,数学之美").split(",");
    private int maxLength = movieName.length;
    private int i = 0;

    public String next() {
        return movieName[(i++)/* % maxLength*/];
    }

    public static void main(String[] args) {
        System.out.println("ArrayList " + new GeneratorClass().fill(new ArrayList<>()));
        System.out.println("LinkedList " + new GeneratorClass().fill(new LinkedList<>()));
        System.out.println("HashSet " + new GeneratorClass().fill(new HashSet<>()));
        System.out.println("LinkedHashSet " + new GeneratorClass().fill(new LinkedHashSet<>()));
        System.out.println("TreeSet " + new GeneratorClass().fill(new TreeSet<>()));
    }


    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < movieName.length;
            }

            @Override
            public String next() {
                return movieName[index++];
            }
        };
    }

    public Collection<String> fill(Collection<String> collection) {
        for (int j = 0; j < movieName.length; j++) {
            collection.add(next());
        }
//        for (String m : new GeneratorClass()) {
//            collection.add(m);
//        }
        return collection;
    }
}
