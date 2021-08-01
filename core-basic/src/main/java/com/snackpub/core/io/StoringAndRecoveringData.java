package com.snackpub.core.io;
import java.io.*;

/**
 *  PrintWriter可以对数据进行格式化，以便人们阅读。但是为了输出一个可供另一个“流”恢复的数据，我们需要用到DataOutputStream
 *  写入数据，DataInputStream恢复数据。
 *  DataOutputStream、DataInputStream是面向字节的，因此要用InputStream和OutputStream.
 *
 *  通过DataOutputStream写字符串能够恢复它的唯一可靠做法是使用UTF-8编码，该示例使用writeUTF()和readUTF()实现。
 *
 *  UTF-8是一种多字节格式，其编码长度会根据实际使用的字符集有所变化。如果我们使用的是ASCII或者几乎都是ASCII编码（只占7位），
 *  那么就显得极为浪费空间和宽带，所以UTF-8将ASCII字符编码成单一字节的形式，而非ASCII字符则编码成两到三个字节的形式。
 *
 * @date 2021/8/1
 * @author snackpub
 */
public class StoringAndRecoveringData {
  public static void main(String[] args)
  throws IOException {
    DataOutputStream out = new DataOutputStream(
      new BufferedOutputStream( // 使用BufferedOutputStream装饰，包装成一个输出缓冲流
        new FileOutputStream("Data.txt")));
    out.writeDouble(3.14159);
    out.writeUTF("That was pi"); //
    out.writeDouble(1.41413);
    out.writeUTF("Square root of 2");
    out.close();
    DataInputStream in = new DataInputStream( // DataInputStream可以准确的读取数据——无论读和写数据的平台多么不同。这一点很有价值。
      new BufferedInputStream(
        new FileInputStream("Data.txt")));
    System.out.println(in.readDouble());
    // Only readUTF() will recover the
    // Java-UTF String properly:
    System.out.println(in.readUTF());
    System.out.println(in.readDouble());
    System.out.println(in.readUTF());
  }
} /* Output:
3.14159
That was pi
1.41413
Square root of 2
*///:~
