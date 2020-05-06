package com.snackpub.core.redis.client;

import com.snackpub.core.redis.base.BaseTest;
import lombok.SneakyThrows;
import org.junit.Test;


/**
 * redis Pub / Sub 消息订阅
 *
 * @author snackpub
 * @date 2020/5/6 22:36
 */
public class PubSubSample extends BaseTest {

    @Test
    @SneakyThrows
    public void publish() {

        redisTemplate.convertAndSend("news", "发送消息");
       /* String s;
        // 读取控制台字符
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("输入字符, 按下 'end' 键退出。");
        // 读取字符
        do {
            s = reader.readLine();
            redisTemplate.convertAndSend("news", s);
            System.out.println(s);
        } while (!s.equals("end"));*/
    }


}
