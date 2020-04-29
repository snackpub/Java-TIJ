package com.snackpub.core.redis.client;

import com.snackpub.core.redis.base.BaseTest;
import org.junit.Test;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;

import java.util.Set;

/**
 * 操作有序集合
 * <p>
 * 与 Set 不一样的地方是，ZSet 对于集合中的每个元素都维护了一个权重值(score)
 * 与 Set 相同的是它们都是string类型元素的集合，且不允许元素重复
 * <p>
 * redis 正是通过 score 来为集合中的成员进行从大到小的排序,score 可重复
 *
 * @author snackpub
 * @date 2020/4/28
 * @date 2020/4/29 22:34
 */
public class ZsetHandleSample extends BaseTest {


    @Test
    public void testAdd() {
        redisTemplate.opsForZSet().add("Zset", "testZset1", 9.45);
        redisTemplate.opsForZSet().add("Zset", "testZset2", 9.46);
        redisTemplate.opsForZSet().add("Zset", "testZset3", 9.47);
        redisTemplate.opsForZSet().add("Zset", "testZset4", 9.47);

        // range 通过索引之间返回有序集合指定区间的大小
        Set zset = redisTemplate.opsForZSet().range("Zset", 0, 10);
        zset.forEach(System.out::println);
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

    @Test
    public void testZscan() {
        // 第一个参数总是一个数据库键
        Cursor zset = redisTemplate.opsForZSet().scan("Zset", ScanOptions.scanOptions().build());
        boolean b = zset.hasNext();
        if (b) {
            Object next = zset.next();
        }
    }


}
