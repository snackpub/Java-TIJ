package com.snackpub.core.io.bytestream;

import com.snackpub.core.lambda.fun.Java8Tester;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * 输入流
 * FileInputStream extends InputStream
 * FileInputStream 流用于从文件中读取数据
 *
 * @author snackpub
 * @date 2020/4/1  12:52
 */
public class FileInputStreamOperation {


    /**
     * 读取文本
     *
     * @param path 路径
     */
    public static void readText(String path) {
        try {
            FileInputStream fis = new FileInputStream(path);
            // 设置支持的字符集名称
            Reader isr = new InputStreamReader(fis, "gbk");
            int len;
            StringBuilder sbToStr = new StringBuilder();
            while ((len = isr.read()) != -1) {
                sbToStr.append((char) len);
            }
            //Consumer<T> 实现方式
            PrintInfo printInfo = System.out::println;
            // 数据类型可省略
            PrintInfo printInfo2 = infos -> System.out.println(infos);
            PrintInfo printInfo3 = (String infos) -> { System.out.println(infos);};
            printInfo.printText(sbToStr.toString());
            printInfo.printText("----------------------------");
            printInfo2.printText(sbToStr.toString());
            fis.close();
            isr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入文本
     *
     * @param path 路径
     */
    public static void outputText(String path) {
        try {
            /*
              其他构造方式：
              new FileOutputStream(File file)
              new FileOutputStream(File file,boolean append)
             */
            OutputStream fos = new FileOutputStream(path, true);
            Writer writer = new OutputStreamWriter(fos, Charset.forName("gbk"));
            writer.append("\r\n");
            // 写入到缓冲区
            writer.append("new text");
            // 换行
            writer.append("\r\n");
            // 刷新缓存冲,写入到文件,如果下面已经没有写入的内容了,直接close也会写入
            writer.append("snackpub " + new Date());
            // 刷新流。如果流在缓冲区中保存了来自各个write()方法的任何字符，则立即将它们写到预期的目的地。
            writer.flush();
            // 关闭写入流,同时会把缓冲区内容写入文件
            writer.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String filePath = "E:\\test.txt";
        FileInputStreamOperation.readText(filePath);
//        FileInputStreamOperation.outputText(filePath);
    }


    interface PrintInfo {
        void printText(String str);
    }


    private void print(String str, PrintInfo p) {
        p.printText(str);
    }
}

