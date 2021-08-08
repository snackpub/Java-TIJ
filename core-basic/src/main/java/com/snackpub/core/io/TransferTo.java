package com.snackpub.core.io;
// Using transferTo() between channels
// {Args: TransferTo.java TransferTo.txt}
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.io.*;

/**
 * 从一个通道和另一个通道直接相连接
 *
 * @author snackpub
 * @date 2021/8/8
 */
public class TransferTo {
  private static final int BSIZE = 1024; // 1k
  public static void main(String[] args) throws Exception {
    if(args.length != 2) {
      System.out.println("arguments: sourcefile destfile");
      System.exit(1);
    }
    FileChannel
      in = new FileInputStream(args[0]).getChannel(),
      out = new FileOutputStream(args[1]).getChannel();
    in.transferTo(0, in.size(), out);
    // Or:
    // out.transferFrom(in, 0, in.size());
  }
} ///:~
