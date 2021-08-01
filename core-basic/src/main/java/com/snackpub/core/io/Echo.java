package com.snackpub.core.io;
// How to read from standard input.
// {RunByHand}
import java.io.*;

/**
 * 重标准输入中读取
 */
public class Echo {
  public static void main(String[] args)
  throws IOException {
    BufferedReader stdin = new BufferedReader(
      new InputStreamReader(System.in));
    String s;
    while((s = stdin.readLine()) != null && s.length()!= 0)
      System.out.println(s);
    // An empty line or Ctrl-Z terminates the program
  }
} ///:~
