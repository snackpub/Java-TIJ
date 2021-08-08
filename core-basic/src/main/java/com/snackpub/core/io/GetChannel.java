package com.snackpub.core.io;
// Getting channels from streams
import java.nio.*;
import java.nio.channels.*;
import java.io.*;

/**
 * java.nio.* 包中引入了新的Java I/O库，其目的在于提升速度。
 */
public class GetChannel {
  private static final int BSIZE = 1024; // 1k
  public static void main(String[] args) throws Exception {
    // Write a file:
    FileChannel fc =
      new FileOutputStream("data.txt").getChannel(); // 获取一个通道
    // 使用warp对填充一个字节数组
    fc.write(ByteBuffer.wrap("Some text ".getBytes()));
    fc.close();
    // Add to the end of the file:
    fc =
      new RandomAccessFile("data.txt", "rw").getChannel();
    fc.position(fc.size()); // Move to the end，使用RandomAccessFile打开文件我们可以随便移动
    fc.write(ByteBuffer.wrap("Some more".getBytes()));
    fc.close();
    // Read the file:
    fc = new FileInputStream("data.txt").getChannel();
    // 对于只读访问我们必须显示的使用allocate来分配ByteBuffer的大小
    ByteBuffer buff = ByteBuffer.allocate(BSIZE);
    fc.read(buff); // 一旦调用read告知向ByteBuffer存储字节，就必须调用ByteBuffer.flip();
    buff.flip(); // 调用缓冲器上的flip()，让它做好让别人读取字节的准备.
    while(buff.hasRemaining())
      System.out.print((char)buff.get());
  }
} /* Output:
Some text Some more
*///:~
