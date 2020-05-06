package com.snackpub.core.redis.config;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.nio.charset.StandardCharsets;

/**
 * redis pub/sub 消息监听配置
 *
 * @author snackpub
 * @date 2020/5/6
 */
@Configuration
@Slf4j
public class MessageListenerConfig {

    @Bean
    @ConditionalOnMissingBean(name = "messageListenerAdapter")
    public MessageListenerAdapter messageListenerAdapter() {
        log.info("MessageListenerAdapter init...");
        return new MessageListenerAdapter(new MyRedisChannelListener());
    }

    @Bean
    @ConditionalOnMissingBean
    RedisMessageListenerContainer container(RedisConnectionFactory connFactory, MessageListenerAdapter msgAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connFactory);
        // news 频道名称
        container.addMessageListener(msgAdapter, new PatternTopic("news"));
        return container;
    }


    static class MyRedisChannelListener implements MessageListener {
        @SneakyThrows
        @Override
        public void onMessage(Message message, byte[] pattern) {
            byte[] channel = message.getChannel();
            byte[] body = message.getBody();
            String content = new String(body, StandardCharsets.UTF_8);
            String address = new String(channel, StandardCharsets.UTF_8);

            System.out.println("get " + content + " from " + address);
        }
    }
}
