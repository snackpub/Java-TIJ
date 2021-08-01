package com.snackpub.core.io;//: io/BufferedInputFile.java
import java.io.*;


/**
 * 缓冲输入文件
 * 为了提高速度，我们希望对那个文件进行缓冲，我们把FileReader传递给一个BufferedReader进行构造
 *
 * @date 2021/8/1
 * @author snackpub
 */
public class BufferedInputFile {
  // Throw exceptions to console:
  public static String
  read(String filename) throws IOException {
    // Reading input by lines:
    BufferedReader in = new BufferedReader(
      new FileReader(filename));
    String s;
    StringBuilder sb = new StringBuilder();
    // readLine()为null，你就达到了文件的末尾
    while((s = in.readLine())!= null)
      sb.append(s).append("\n");
    in.close();
    return sb.toString();
  }
  public static void main(String[] args)
  throws IOException {
    System.out.print(read("F:\\Work\\JavaBasicStudy\\core-basic\\src\\main\\java\\com\\snackpub\\core\\io\\BufferedInputFile.java"));
  }
} /* (Execute to see output) *///:~
