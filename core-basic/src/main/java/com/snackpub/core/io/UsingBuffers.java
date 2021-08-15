package com.snackpub.core.io;//: io/UsingBuffers.java
import java.nio.*;

import static com.snackpub.core.util.Print.print;

/**
 * 在缓冲器中插入和提取数据的方法会更新这些索引，用于反映发生的变化
 *
 * 下面是一个简单的交换相邻字符算法，已对CharBuffer中的字符进行编码（scramble）和译码（unscramble）
 *
 * @date 2021/8/15
 * @auth snackpub
 */
public class UsingBuffers {
  private static void symmetricScramble(CharBuffer buffer){
    while(buffer.hasRemaining()) { // position < limit，介于之间的值
      buffer.mark(); // 将make设置为position当前的值
      char c1 = buffer.get(); // 从缓冲中获取一个字符，position指针会发生改变
      char c2 = buffer.get();
      buffer.reset();
      // 顺序转换一下，在position为0时写入c2，position=1时写入c1，当然你也可以指定位置put(int index, char c);
      buffer.put(c2).put(c1);
    }
  }
  public static void main(String[] args) {
    char[] data = "UsingBuffers".toCharArray();
    ByteBuffer bb = ByteBuffer.allocate(data.length * 2);
    CharBuffer cb = bb.asCharBuffer(); // CharBuffer只是ByteBuffer上的一个视图
    cb.put(data);
    print(cb.rewind()); // rewind()把position设置到缓冲器的开始位置，mark为-1，
    symmetricScramble(cb);
    print(cb.rewind());
    symmetricScramble(cb);
    print(cb.rewind());
  }
} /* Output:
UsingBuffers
sUniBgfuefsr
UsingBuffers
*///:~
