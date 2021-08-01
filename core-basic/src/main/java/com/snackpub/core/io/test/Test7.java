package com.snackpub.core.io.test;

import com.snackpub.core.util.PPrint;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

/**
 * 练习7: (2)打开一个文本文件,每次读取一行内容。将每行作为一个String读人,并将那个String对象置入一个LinkedList中。按相反的顺序打印出LinkedList中的所有行。
 * F:\Work\JavaBasicStudy\core-basic\src\main\java\com\snackpub\core\io\test\Test7.java
 *
 * @author snackpub
 * @date 2021/8/1
 */
public class Test7 {

    public static void main(String[] args) throws IOException {
        if (args.length != 0)
            read(args[0]);
         else {
            String filepath = "F:\\Work\\JavaBasicStudy\\core-basic\\src\\main\\java\\com\\snackpub\\core\\io\\test\\Test7.java";
            read(filepath);
        }
    }

    public static void read(String filepath) throws IOException {
        BufferedReader bufferedReader
                = new BufferedReader(new FileReader(filepath));
        String s;
        LinkedList<String> chars = new LinkedList<>();
        while ((s = bufferedReader.readLine()) != null)
            chars.add(s);
        bufferedReader.close();

        Collections.reverse(chars);
        PPrint.pprint(chars);
    }
}
