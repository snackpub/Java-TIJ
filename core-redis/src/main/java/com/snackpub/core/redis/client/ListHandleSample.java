package com.snackpub.core.redis.client;

import com.snackpub.core.redis.base.BaseTest;
import org.junit.Test;
import org.springframework.data.redis.connection.RedisListCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.support.collections.RedisCollection;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 操作数组 (链表结构)
 * <p>
 * 我们经常使用 Redis 的 lpush/rpush/lpop/rpop 四条指令来实现一个队列
 *
 * @author snackpub
 * @date 2020/4/28
 * @date 2020/5/26 22:59
 */
public class ListHandleSample extends BaseTest {


    @Test
    public void setLeftPush() {
        // 在 key 对应 list 的头部添加字符串元素  lpush
        Long size = 0L;
        size = redisTemplate.opsForList().leftPush("TestList", "TestLeftPush");
        System.out.println("TestList len: " + size);
        size = redisTemplate.opsForList().leftPush("TestList", "TestLeftPush2");
        System.out.println("TestList len: " + size);

        //  lrange 是用于取 TestList 的内容
        List<String> range = redisTemplate.opsForList().range("TestList", 0, -1);
        assert range != null;
        range.forEach(System.out::println);
    }


    @Test
    public void setRightPush() {
        // 那么 rightPush 就是往数组尾部插入一个元素
        Long size = redisTemplate.opsForList().rightPush("TestList", "TestRightPush");
        System.out.println("size: " + size);

        // 获取最后一个元素
        List<String> range = redisTemplate.opsForList().range("TestList", -1, -1);
        assert range != null;
        range.forEach(System.out::println);
    }

    @Test
    public void linsert() {
        Long size = (Long) redisTemplate.execute((RedisCallback) connection ->
                // redis 存储的key 跟 value 都 加了 "" ，所以这里转换成byte数组是需要加双引号转义
                connection.lInsert("\"TestList\"".getBytes(), RedisListCommands.Position.BEFORE, "\"TestRightPush\"".getBytes(), "\"BeforeTestRightPush\"".getBytes())
        );
        System.out.println(size);
        // linsert
        Long size2 = redisTemplate.opsForList().leftPush("TestList", "BeforeTestRightPush", "BeforeTestRightPush2");
        System.out.println(size2);
    }

    @Test
    public void getRightPop() {
        // 从list右侧取出第一个元素,并移除    rpop
        Object rightFirstElement = redisTemplate.opsForList().rightPop("TestList");
        System.out.println(rightFirstElement);

        // 从 list 的头部删除元素，并返回删除元素  lpop
        Object leftFirstElement = redisTemplate.opsForList().leftPop("TestList");
        System.out.println(leftFirstElement);


        // 阻塞 rpop
        redisTemplate.opsForList().rightPop("TestList", 10, TimeUnit.SECONDS);
    }


    @Test
    public void rpoplpush() {
        //从第一个 list 的尾部移除元素并添加到第二个 list 的头部,最后返回被移除的元素值，整个操
        //作是原子的.如果第一个 list 是空或者不存在返回 nil
        redisTemplate.opsForList().leftPush("TestList2", "key1");

        // llen
        Long beforeSize = redisTemplate.opsForList().size("TestList");
        System.out.println("beforeSize :" + beforeSize);

        redisTemplate.opsForList().rightPopAndLeftPush("TestList2", "TestList");
        Long afterSize = redisTemplate.opsForList().size("TestList");
        System.out.println("afterSize :" + afterSize);
    }

    @Test
    public void getIndexValue() {
        // 根据下标获取值
        Object value = redisTemplate.opsForList().index("TestList", 1L);
        System.out.println(value);

    }


    @Test
    public void lset() {
        // 设置 list 中指定下标的元素值(下标从 0 开始)
        redisTemplate.opsForList().set("TestList", 0, "firstValue");
        redisTemplate.opsForList().set("TestList", -1, "lastValue");
    }

    @Test
    public void lrem() {
        // 从 key 对应 list 中删除 count 个和 value 相同的元素。
        // count>0 时，按从头到尾的顺序删除，
        Long size = redisTemplate.opsForList().remove("TestList", 2, "TestLeftPush2");
        System.out.println(size);

        // count<0 时，按从尾到头的顺序删除
        Long size2 = redisTemplate.opsForList().remove("TestList", -2, "TestRightPush");
        System.out.println(size2);

    }


    @Test
    public void ltrim() {
        // 保留指定 key 的值范围内的数据
        redisTemplate.opsForList().trim("TestList", 1, -1);

    }

}
