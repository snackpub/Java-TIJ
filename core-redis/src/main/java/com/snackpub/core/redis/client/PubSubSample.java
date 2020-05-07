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
        // 将给定的消息发布到给定的通道
        redisTemplate.convertAndSend("news", "需要发布的消息");
        redisTemplate.convertAndSend("news2","国家主席下达最高指令：将对台独高级首领下达斩首行动，代号为0057");

        // news 与 news2 运行时，news2 会优先于news运行，但是我把news2 也改成news代码就会从上到下正常运行；这是什么问题呢？
    }


}
