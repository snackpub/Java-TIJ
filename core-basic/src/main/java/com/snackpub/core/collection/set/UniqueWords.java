package com.snackpub.core.collection.set;

import com.snackpub.core.util.TextFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * SetOperations.Java文件中所有的单词, 本例中排序是按字典进行的，因此大小和小写被划分到不同的组
 *
 * @author snackpub
 * @date 2021/3/27
 */
public class UniqueWords {

    public static void main(String[] args) {
        Set<String> words = new TreeSet<>(
                new TextFile("F:\\Work\\JavaBasicStudy\\core-basic\\src\\main\\java\\com\\snackpub\\core\\collection\\set\\SortedSetOfInteger.java"
                        , "\\W+"));  // \\W+ 根据正则将其断开为单词，该正则表示一个或多个字母
        // aeiou
        Map<Character, Integer> mapSum = new HashMap<>(); // 记录元音字母总数
        Map<String, Map<Character, Integer>> wordSingleSumMap = new HashMap<>(); // 记录每一个单词中元音字母总数
        words.forEach(item -> {
            char[] chars = item.toCharArray();
            int c1 = 0, c2 = 0, c3 = 0, c4 = 0, c5 = 0;
            Map<Character, Integer> wordSingleSum = new HashMap<>();  // 统计一个单词中的元音字母个数
            for (char aChar : chars) {
                switch (aChar) {
                    case 'a':
                        c1 += 1;
                        break;
                    case 'e':
                        c2 += 1;
                        break;
                    case 'i':
                        c3 += 1;
                        break;
                    case 'o':
                        c4 += 1;
                        break;
                    case 'u':
                        c5 += 1;
                        break;
                    default:
                        break;
                }
                wordSingleSum.put('a', c1);
                wordSingleSum.put('e', c2);
                wordSingleSum.put('i', c3);
                wordSingleSum.put('o', c4);
                wordSingleSum.put('u', c5);
                if (aChar == 'a' || aChar == 'e' || aChar == 'i' || aChar == 'o' || aChar == 'u') {
                    if (mapSum.containsKey(aChar)) mapSum.put(aChar, mapSum.get(aChar) + 1);
                    else mapSum.put(aChar, 1);
                }
            }
            wordSingleSumMap.put(item, wordSingleSum);
        });

//        System.out.println(map1);
        wordSingleSumMap.forEach((k, v) -> {
            System.out.println(k + ": ");
            v.forEach((charKey, charValue) -> System.out.print(charKey + ": " + charValue + " 个，"));
            System.out.println();
        });

        mapSum.forEach((k, v) -> System.out.println(k + "共" + v + "个"));
//        System.out.println(map2);
    }

}
