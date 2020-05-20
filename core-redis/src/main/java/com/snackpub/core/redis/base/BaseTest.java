package com.snackpub.core.redis.base;

import lombok.AllArgsConstructor;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试基类
 *
 * @author snackpub
 * @date 2020/4/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseTest {


    /**
     * RedisTemplate的以字符串为中心的扩展。由于针对Redis的大多数操作都是基于字符串的，这个类提供了一个专用的类，
     * 它可以最小化其更通用的{RedisTemplate模板}的配置，特别是在序列化器方面。
     */
    @Autowired
    protected StringRedisTemplate stringRedisTemplate;
    /**
     * 注意，此模板将{@link RedisCallback}使用的{@link RedisConnection}公开为
     * {@link StringRedisConnection}。
     */
    @Autowired
    protected RedisTemplate redisTemplate;


}
