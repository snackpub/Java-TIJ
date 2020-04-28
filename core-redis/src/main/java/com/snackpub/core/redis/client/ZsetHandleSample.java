package com.snackpub.core.redis.client;

import com.snackpub.core.redis.base.BaseTest;
import org.junit.Test;

import java.util.Set;

/**
 * 操作有序集合
 * <p>
 * 与 Set 不一样的地方是，ZSet 对于集合中的每个元素都维护了一个权重值
 *
 * @author snackpub
 * @date 2020/4/28
 */
public class ZsetHandleSample extends BaseTest {


    @Test
    public void testAdd() {
        Boolean bool = redisTemplate.opsForZSet().add("Zset", "testZset511", 9.45);
        System.out.println(bool);
    }


    @Test
    public void testGet() {
        Set zset = redisTemplate.opsForZSet().range("Zset", 0, 1);
        zset.forEach(System.out::println);
    }


    @Test
    public void testDel() {
        redisTemplate.opsForZSet().remove("Zset", "testZset");
    }

    @Test
    public void testGetScore() {
        Set zset = redisTemplate.opsForZSet().rangeByScore("Zset", 9.0, 10.0);
        zset.forEach(System.out::println);

//        Set zset1 = redisTemplate.opsForZSet().rangeWithScores("Zset", 0, 3);
//        zset1.forEach(System.out::println);
    }


}
