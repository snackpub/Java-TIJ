package com.snackpub.core.collection.set;

import com.snackpub.core.util.TextFile;

import java.util.Set;
import java.util.TreeSet;

/**
 * 按照字母序排序
 *
 * @author snackpub
 * @date 2021/3/27
 */
public class UniqueWordsAlphabetic {

    public static void main(String[] args) {
        Set<String> words = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        words.addAll(new TextFile("F:\\Work\\JavaBasicStudy\\core-basic\\src\\main\\java\\com\\snackpub\\core\\collection\\set\\SortedSetOfInteger.java"
                , "\\W+"));

        System.out.println(words);

    }

}
