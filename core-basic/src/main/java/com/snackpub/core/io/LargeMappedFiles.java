package com.snackpub.core.io;//: io/LargeMappedFiles.java
// Creating a very large file using mapping.
// {RunByHand}

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import static com.snackpub.core.util.Print.print;
import static com.snackpub.core.util.Print.printnb;


/**
 * MappedByteBuffer,特殊类型的直接缓冲器，注意我们必须指定映射文件的初始位置和映射区域的长度，
 * 意味着我们可以映射大文件的较小部分
 *
 * @date 2021/8/15
 * @author snackpub
 */
public class LargeMappedFiles {
  static int length = 0x8FFFFFF; // 128 MB  // 143 MB (150,994,943 字节)
  public static void main(String[] args) throws Exception {
    MappedByteBuffer out =
      new RandomAccessFile("rtest.dat", "rw").getChannel()
      .map(FileChannel.MapMode.READ_WRITE, 0, length);
    for(int i = 0; i < length; i++)
      out.put((byte)'x');
    print("Finished writing");
    for(int i = length/2; i < length/2 + 6; i++)
      printnb((char)out.get(i));
  }
} ///:~
