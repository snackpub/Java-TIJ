package com.snackpub.core.redis.client;

import com.snackpub.core.redis.base.BaseTest;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * 操作字符串
 *
 * @author snackpub
 * @date 2020/4/28
 */
public class StringHandleSample extends BaseTest {


    @Test
    public void setStr() {
        stringRedisTemplate.opsForValue().set("test-string-value", "Hello Redis");
    }

    @Test
    public void getStr() {
        String value = stringRedisTemplate.opsForValue().get("test-string-value");
        System.out.println(value);
    }

    @Test
    public void setTimeOut() {
        // 设置过期时间
        stringRedisTemplate.opsForValue().set("test-string-value", "Hello Redis", 1, TimeUnit.MINUTES);
    }


}
