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

    @Autowired
    protected StringRedisTemplate stringRedisTemplate;
    @Autowired
    protected RedisTemplate redisTemplate;

}
