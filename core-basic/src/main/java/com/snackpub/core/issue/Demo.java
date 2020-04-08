package com.snackpub.core.issue;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Demo {

    /**
     * 数据处理题：
     * 用C或Java或JavaScript语言写一个方法，入参为N*4的二维数组（
     * 第一列学生学号第二列数学成绩第三列物理成绩第四列化学成绩），返回值为N*5的二维数组（前四列相同，第五列为总分列），要求返回的二维数组按照总分列倒序。
     */
    public static void main(String[] args) {
        String[][] scoreArr = new String[][]{{"学号", "数学", "化学", "生物"}, {"1", "200", "100", "600"}, {"2", "400", "100", "100"}};
        calculateScore(scoreArr);
    }

    public static void calculateScore(String[][] arr) {
        final String[][] newArr = new String[arr.length][5];
        for (int i = 0; i < arr.length; i++) {
            int totalScore = 0;
            for (int i1 = 0; i1 < arr[i].length; i1++) {
                newArr[i][i1] = arr[i][i1];
                // 表头，并给数组最后节点追加元素
                if (i == 0 && i1 == arr[i].length - 1) {
                    newArr[i][newArr[i].length - 1] = "总分";
                }
                // 非数组第一个元素，非第一行；计算总分
                if (i1 != 0 && i != 0) {
                    int score = Integer.parseInt(arr[i][i1]);
                    totalScore += score;
                }
                // 将总分追加到数组尾部
                if (i1 == arr[i].length - 1 && i != 0) {
                    newArr[i][newArr[i].length - 1] = totalScore + "";
                }
            }
        }

        Integer[][] sortScore = new Integer[arr.length - 1][5];
        for (int i = 1; i < newArr.length; i++) {
            for (int i1 = 0; i1 < newArr[i].length; i1++) {
                sortScore[i - 1][i1] = Integer.parseInt(newArr[i][i1]);
            }
        }

        // 对数据进行排序
        Arrays.sort(sortScore, Comparator.comparingInt(o -> o[o.length - 1]));


        for (Integer[] string : sortScore) {
            for (Integer s : string) {
                System.out.println(s);
            }
        }
    }
}
