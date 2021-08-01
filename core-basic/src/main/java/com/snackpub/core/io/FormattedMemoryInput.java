package com.snackpub.core.io;
import java.io.*;

/**
 * 格式化的内存输入
 * 读取格式化的数据，可以使用DataInputStream，它是一个面向字节的I/O类（不是面向字符的）。
 * 我们可以用InputStream以字节形式读取任何数据（例如一个文件），不过，在这里我们使用的时字符串。
 *
 * @author snackpub
 * @date 2021/8/1
 */
public class FormattedMemoryInput {
  public static void main(String[] args)
  throws IOException {
    try {
      DataInputStream in = new DataInputStream(
        new ByteArrayInputStream(
         BufferedInputFile.read(
          "F:\\Work\\JavaBasicStudy\\core-basic\\src\\main\\java\\com\\snackpub\\core\\io\\FormattedMemoryInput.java").getBytes()));
      while(true)
        System.out.print((char)in.readByte());
    } catch(EOFException e) {
      System.err.println("End of stream");
    }
  }
} /* (Execute to see output) *///:~
