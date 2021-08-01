package com.snackpub.core.io;
// Testing for end of file while reading a byte at a time.
import java.io.*;

/**
 * 用 DataInputStream 的readByte()一次一个字节地读取字符，那么任何字节的值都是合法的的结果，因此
 * 返回值不能用来检测输入是否结束。相反，我们可以用available()方法查看还有多少可供存取的字节。
 *
 * 注意：available()会随着所读取的媒介类型不同而不同；字面意思就是“在没有阻塞的情况下所能读取的字节数”。对于文件意味着整个文件；
 * 但是对于不同类型的流，可能就不是这样了，因此要谨慎使用。
 */
public class TestEOF {
  public static void main(String[] args)
  throws IOException {
    DataInputStream in = new DataInputStream(
      new BufferedInputStream(
        new FileInputStream("F:\\Work\\JavaBasicStudy\\core-basic\\src\\main\\java\\com\\snackpub\\core\\io\\TestEOF.java")));
    while(in.available() != 0)
      System.out.print((char)in.readByte());
  }
} /* (Execute to see output) *///:~
