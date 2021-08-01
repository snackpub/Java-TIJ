package com.snackpub.core.io;
import java.io.*;

/**
 * FileWriter对象可以向文件写入数据。
 */
public class BasicFileOutput {
  private static String file = "BasicFileOutput.out";
  public static void main(String[] args)
  throws IOException {
    BufferedReader in = new BufferedReader(
      new StringReader(
        BufferedInputFile.read("F:\\Work\\JavaBasicStudy\\core-basic\\src\\main\\java\\com\\snackpub\\core\\io\\BasicFileOutput.java")));
    PrintWriter out = new PrintWriter(
      new BufferedWriter(new FileWriter(file))); // 通常会用BufferedWriter将其包装起来用以缓冲输出（尝试移除来感受对性能的影响——缓冲往往能够显著增加对性能的影响）
    int lineCount = 1;
    String s;
    while((s = in.readLine()) != null )
      out.println(lineCount++ + ": " + s);
    out.close(); // 显示调用close(),如果不为所有的输出文件调用close(),就会发现缓冲区内容不会被刷新清空，那么它们也就不完整。
    // Show the stored file:
    System.out.println(BufferedInputFile.read(file));
  }
} /* (Execute to see output) *///:~
