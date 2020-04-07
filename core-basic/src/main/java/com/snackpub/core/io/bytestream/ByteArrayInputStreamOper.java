package com.snackpub.core.io.bytestream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 字节数组输入流
 *
 * @author snackpub
 * @date 2020/4/1  22:19
 */
public class ByteArrayInputStreamOper {


    public static void read() throws IOException {
        ByteArrayOutputStream bOutput = new ByteArrayOutputStream(12);
        while (bOutput.size() != 10) {
            // 获取用户输入值
            bOutput.write(System.in.read());
        }
        byte[] b = bOutput.toByteArray();
        System.out.println("Print the content");
        for (byte value : b) {
            // 打印字符
            System.out.print((char) value + "   ");
        }
        System.out.println("   ");

        ByteArrayInputStream byteArrInp = new ByteArrayInputStream(b);
        int len;
        System.out.println("Converting characters to Upper case ");
        for (int y = 0; y < 1; y++) {
            while ((len = byteArrInp.read()) != -1) {
                System.out.println(Character.toUpperCase((char) len));
            }
            byteArrInp.reset();
        }
//        byteArrInp.reset();
    }

    public static void main(String[] args) {
        try {
            read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
