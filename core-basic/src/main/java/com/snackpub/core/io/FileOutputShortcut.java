package com.snackpub.core.io;
import java.io.*;

/**
 * PrintWriter 添加一个辅助构造器，使得你不必每次希望创建文本文件向其中写入时，都去执行所有的装饰工作。
 * 你仍旧在进行缓存，只不过不需要自己去实现。
 */
public class FileOutputShortcut {
  static String file = "FileOutputShortcut.out";
  public static void main(String[] args)
  throws IOException {
    BufferedReader in = new BufferedReader(
      new StringReader(
       BufferedInputFile.read("F:\\Work\\JavaBasicStudy\\core-basic\\src\\main\\java\\com\\snackpub\\core\\io\\FileOutputShortcut.java")));
    // Here's the shortcut:
    PrintWriter out = new PrintWriter(file);
    int lineCount = 1;
    String s;
    while((s = in.readLine()) != null )
      out.println(lineCount++ + ": " + s);
    out.close();
    // Show the stored file:
    System.out.println(BufferedInputFile.read(file));
  }
} /* (Execute to see output) *///:~
