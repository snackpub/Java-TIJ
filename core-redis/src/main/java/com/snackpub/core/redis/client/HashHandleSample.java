package com.snackpub.core.redis.client;

import com.snackpub.core.redis.base.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * 操作Hash
 * <p>
 * Redis 中的 Hash 数据结构实际上与 Java 中的 HashMap 是非常类似的，提供的 API 也很类似
 *
 * @author snackpub
 * @date 2020/4/28
 */
public class HashHandleSample extends BaseTest {


    @Test
    public void testPut() {
        // hash 新增元素
        redisTemplate.opsForHash().put("TestHash", "FirstElement", "Hello,Redis Hash");

        // 判断指定 key 对应的 Hash 中是否存在指定的 map 键
        Assert.assertTrue(redisTemplate.opsForHash().hasKey("TestHash", "FirstElement"));
    }


    @Test
    public void testGet() {
        // 获取指定 key 对应的 Hash 中指定键的值。
        Object hashV = redisTemplate.opsForHash().get("TestHash", "FirstElement");
        System.out.println(hashV);
    }


    @Test
    public void testDel() {
        redisTemplate.opsForHash().delete("TestHash", "FirstElement");
        Assert.assertFalse(redisTemplate.opsForHash().hasKey("TestHash", "FirstElement"));
    }


}
