package com.snackpub.core.redis.client;

import com.snackpub.core.redis.base.BaseTest;
import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author snackpub
 * @date 2020/5/6
 */
public class PubSubSample extends BaseTest {

    private static RedisTemplate redisTemplate = BaseTest.rt;

    @SneakyThrows
    @Test
    public static void publish(final RedisTemplate redisTemplate) {
        String s;
        // 读取控制台字符
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("输入字符, 按下 'end' 键退出。");
        // 读取字符
        do {
            s = reader.readLine();
            redisTemplate.convertAndSend("news", s);
            System.out.println(s);
        } while (!s.equals("end"));
    }


    public static void main(String[] args) {

        publish(redisTemplate);
    }

}
