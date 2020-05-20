package com.snackpub.core.redis.client;

import com.snackpub.core.redis.base.BaseTest;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        stringRedisTemplate.opsForValue().set("test-string-value2", "Hello Redis2");
    }

    @Test
    public void getStr() {
        String value = stringRedisTemplate.opsForValue().get("test-string-value");
        System.out.println(value);
    }


    /**
     * 一次获取多个 key 的值，如果对应 key 不存在，则对应返回 nil。
     */
    @Test
    public void mgetStr() {
        List<String> keys = Stream.of("test-string-value", "test-string-value2").collect(Collectors.toList());
        List<String> multiGet = stringRedisTemplate.opsForValue().multiGet(keys);
        assert multiGet != null;
        multiGet.forEach(System.out::println);
    }

    /**
     * 获取{@code begin}和{@code end}之间的值{@code key}的子字符串
     */
    @Test
    public void getRange() {
        // 字符串左面下标从0开始
        String leftSubValue = stringRedisTemplate.opsForValue().get("test-string-value", 0L, 5L);
        System.out.println(leftSubValue);

        // 右面下标从-1开始
        String rightSubValue = stringRedisTemplate.opsForValue().get("test-string-value", -5L, -1L);
        System.out.println(rightSubValue);

    }

    @Test
    public void setTimeOut() {
        // 设置过期时间
        stringRedisTemplate.opsForValue().set("test-string-value", "Hello Redis", 1, TimeUnit.MINUTES);
    }


}
