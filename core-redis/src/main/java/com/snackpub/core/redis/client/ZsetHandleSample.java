package com.snackpub.core.redis.client;

import com.snackpub.core.redis.base.BaseTest;
import org.junit.Test;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations;

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
        // 不管score大小，后者都会替换前者
        redisTemplate.opsForZSet().add("Zset", "testZset3", 9.50);
        redisTemplate.opsForZSet().add("Zset", "testZset3", 9.49);

        redisTemplate.opsForZSet().add("Zset", "testZset6", 9.48);

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
        // 使用scan命令对redis中指定key进行扫描;匹配获取键值对，ScanOptions.NONE为获取全部键值对；
        Cursor<ZSetOperations.TypedTuple<Object>> cursor = redisTemplate.opsForZSet().scan("Zset", ScanOptions.NONE);
        while (cursor.hasNext()) {
            ZSetOperations.TypedTuple<Object> typedTuple = cursor.next();
            System.out.println(typedTuple.getValue() + " " + typedTuple.getScore());
        }
//        System.out.println("-------------");
//        ScanOptions.ScanOptionsBuilder scanOption = ScanOptions.scanOptions().match("t*");
//        Cursor<ZSetOperations.TypedTuple> tupleCursor = redisTemplate.opsForZSet().scan("Zset", scanOption.build());
//        while (tupleCursor.hasNext()) {
//            ZSetOperations.TypedTuple next = tupleCursor.next();
//            System.out.println(next.getValue() + " " + next.getScore());
//        }
    }


}
