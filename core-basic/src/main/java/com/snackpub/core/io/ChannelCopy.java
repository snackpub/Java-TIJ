package com.snackpub.core.io;
// Copying a file using channels and buffers
// javac -encoding utf-8 com\snackpub\core\io\ChannelCopy.java
// java  com.snackpub.core.io.ChannelCopy  ChannelCopy.java test.txt
// {Args: ChannelCopy.java test.txt}
import java.nio.*;
import java.nio.channels.*;
import java.io.*;

public class ChannelCopy {
  private static final int BSIZE = 1024; // 1k
  public static void main(String[] args) throws Exception {
    if(args.length != 2) {
      System.out.println("arguments: sourcefile destfile");
      System.exit(1);
    }
    FileChannel
      in = new FileInputStream(args[0]).getChannel(),
      out = new FileOutputStream(args[1]).getChannel();
    ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
    while(in.read(buffer) != -1) {
      buffer.flip(); // Prepare for writing，准备缓冲器的信息，由write提取
      out.write(buffer); // write()后信息仍在缓冲中
      buffer.clear();  // Prepare for reading，调用clear，对所有内部指针从新安排，以便缓冲器在另一个read()操作期间能够做好接受数据的准备.
    }
  }
} ///:~
