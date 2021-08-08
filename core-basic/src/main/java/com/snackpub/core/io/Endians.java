package com.snackpub.core.io;//: io/Endians.java
// Endian differences and data storage.
import java.nio.*;
import java.util.*;

import static com.snackpub.core.util.Print.print;

/**
 * 改变ByteBuffer的字节排序方式，ByteBuffer是以big endian存储的
 *
 * big endian 高位优先，将最重要的字节存放在地址最低的存储器单元
 * little endian 低位优先，将最重要的字节存放在地址最高的存储器单元
 *
 * for example：
 *  用ByteBuffer.asShortBuffer()形式读取一个数据，得到97（二进制为：00000000 01100001），
 *  但是如果ByteBuffer的存储方式更改为低位优先，那么值是 01100001 00000000
 *
 */
public class Endians {
  public static void main(String[] args) {
    ByteBuffer bb = ByteBuffer.wrap(new byte[12]);
    bb.asCharBuffer().put("abcdef");
    print(Arrays.toString(bb.array()));
    bb.rewind();
    bb.order(ByteOrder.BIG_ENDIAN);
    bb.asCharBuffer().put("abcdef");
    print(Arrays.toString(bb.array()));
    bb.rewind();
    bb.order(ByteOrder.LITTLE_ENDIAN);
    bb.asCharBuffer().put("abcdef");
    print(Arrays.toString(bb.array()));
  }
} /* Output:
[0, 97, 0, 98, 0, 99, 0, 100, 0, 101, 0, 102]
[0, 97, 0, 98, 0, 99, 0, 100, 0, 101, 0, 102]
[97, 0, 98, 0, 99, 0, 100, 0, 101, 0, 102, 0]
*///:~
