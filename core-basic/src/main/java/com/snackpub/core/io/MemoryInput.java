package com.snackpub.core.io;
import java.io.*;

/**
 * 从内存输入
 *
 * @date 2021/8/1
 * @author snackpub
 */
public class MemoryInput {
  public static void main(String[] args)
          throws IOException {
    // 从BufferedInputFile.read()读取的结果中创建一个StringReader
    StringReader in = new StringReader(
            BufferedInputFile.read("F:\\Work\\JavaBasicStudy\\core-basic\\src\\main\\java\\com\\snackpub\\core\\io\\MemoryInput.java"));
    int c;
    while((c = in.read()) != -1)
      // read()是以int返回下一个字节，因此必须类型转换为char才能正确打印。
      System.out.print((char)c);
  }
} /* (Execute to see output) *///:~
