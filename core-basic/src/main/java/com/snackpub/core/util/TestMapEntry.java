package com.snackpub.core.util;

import java.util.Iterator;
import java.util.Map;

/**
 * 测试Iterator运行
 * @author snackpub
 * @date 2021/7/3
 */
public class TestMapEntry {
    private static String[] chars =
            "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z"
                    .split(" ");

    private static class Entry implements Map.Entry<Integer, String> {
        int index;

        Entry(int index) {
            this.index = index;
        }

        public boolean equals(Object o) {
            return Integer.valueOf(index).equals(o);
        }

        public Integer getKey() {
            return index;
        }

        public String getValue() {
            return   chars[index % chars.length] +
                    index / chars.length;
        }

        public String setValue(String value) {
            throw new UnsupportedOperationException();
        }

        public int hashCode() {
            return Integer.valueOf(index).hashCode();
        }
    }

    public static class Iter implements Iterator<Map.Entry<Integer, String>> {
        // Only one Entry object per Iterator:
        private Entry entry = new Entry(-1);

        public Iter() {
            System.out.println("construct init ");
        }

        @Override
        public boolean hasNext() {
            return entry.index < chars.length;
        }

        @Override
        public Map.Entry<Integer, String> next() {
            entry.index++;
            return entry;
        }
    }

    public static void main(String[] args) {
        Iter iter = new Iter();
        while (iter.hasNext()) {
            Map.Entry<Integer, String> next = iter.next();
            System.out.println(next.getKey() +" " + next.getValue());
        }
    }
}
