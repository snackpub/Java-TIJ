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
import org.springframework.data.redis.listener.Topic;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        //向(可能正在运行的)容器添加消息侦听器。如果容器正在运行，则侦听器将尽快开始接收(匹配)消息
        //@param1 消息侦听适配器
        //@param2 消息主题
        // container.addMessageListener(msgAdapter, new PatternTopic("news"));
        List<Topic> topics = new ArrayList<>(new ArrayList<PatternTopic>() {
            {
                // 根据正则匹配订阅一个或多个频道
                add(new PatternTopic("news*"));
                // add(new PatternTopic("news2"));
            }
        });
        container.addMessageListener(msgAdapter, Collections.singletonList(new PatternTopic("news*")));
        log.info("RedisMessageListenerContainer init...");

        return container;
    }


    static class MyRedisChannelListener implements MessageListener {

        /**
         * 通过Redis处理接收对象的回调
         *
         * @param message 封装Redis消息体及其属性的类
         * @param pattern 模式匹配的通道(如果指定)-可以是null
         */
        @SneakyThrows
        @Override
        public void onMessage(Message message, byte[] pattern) {
            // 返回与消息关联的通道
            byte[] channel = message.getChannel();
            // 返回消息主题或有效的负载
            byte[] body = message.getBody();
            String content = new String(body, StandardCharsets.UTF_8);
            String address = new String(channel, StandardCharsets.UTF_8);

            System.out.println("pattern: " + new String(pattern, StandardCharsets.UTF_8) + " get " + content + " from " + address);
        }
    }
}
