package com.snackpub.core.lambda.base64;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

/**
 * Java 1.8 内置base64
 *
 * @author Hao
 * @version 2021/7/10
 */
public class Base64Test {

    public static void main(String[] args) {
        String base64encodedString = Base64.getEncoder().encodeToString("snackpub".getBytes(StandardCharsets.UTF_8));
        System.out.println("Base64 编码字符串 (基本) :" + base64encodedString);

        byte[] decode = Base64.getDecoder().decode(base64encodedString);
        String s = new String(decode, StandardCharsets.UTF_8);
        System.out.println("原始字符串: " + s);

        base64encodedString = Base64.getUrlEncoder().encodeToString("runoob?java8".getBytes(StandardCharsets.UTF_8));
        System.out.println("Base64 编码字符串 (URL) :" + base64encodedString);

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 10; ++i) {
            stringBuilder.append(UUID.randomUUID().toString());
        }

        /*
          MIME
          编码后的输出必须以不超过76个字符的行表示，
          并使用回车符'\r'紧跟着换行符'\n'作为行分隔符。
          编码输出的末尾不会添加行分隔符。
          在解码操作中，将忽略所有在base64字母表表中没有找到的行分隔符或其他字符。
            NDk3OTlhNjctNmEwZS00ODdlLTkzOTUtMTIyYmEyYjFkMDI5MzQ5MjFjMDEtNDMxNS00NjY0LTk5
            MDUtY2JmZmQ5YmZmYjhjZDM4MTc0OWEtNDNjOC00ZDdmLWIyMjgtMzU0OTcyNGJlYTEzNmVhZjQ0
            OTYtYmRiMC00MzZhLWJiNTItZDkzZGU5OGZmMjhlMjcwMGVhMGEtNmU1Ni00MWMyLTg2OGItMDYw
            MWQ4Y2MyMGJkY2VjOTE0NzktOWE5Mi00ZmEzLTg4MmQtNWQzYWY5MGNhZmY1NGY5OTgwNTktYjRm
            ZC00MGY2LWJmZTUtZDM5YzI3ZTg3YTBjNWY1OWZmZWYtZTcyMi00NmI5LTg5NzUtMzU0ZmJkMDQy
            M2QxNTIyODUwODEtMWNmMi00OTcwLWE1NzktOTJhODhkNTY4MzMwMDM4OTQyMTktM2FiMi00Zjdk
            LThjN2EtZWMyNDQ2YmQ0MjEz
         */
        byte[] mimeBytes = stringBuilder.toString().getBytes(StandardCharsets.UTF_8);
        String mimeEncodedString = Base64.getMimeEncoder().encodeToString(mimeBytes);
        System.out.println("Base64 编码字符串 (MIME) :" + mimeEncodedString);
    }

}
