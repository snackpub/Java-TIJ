package com.snackpub.core.redis.client;

import com.snackpub.core.redis.base.BaseTest;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * 操作数组
 * <p>
 * 我们经常使用 Redis 的 lpush/rpush/lpop/rpop 四条指令来实现一个队列
 *
 * @author snackpub
 * @date 2020/4/28
 */
public class ListHandleSample extends BaseTest {


    @Test
    public void setLeftPush() {
        // leftPush 实际上是往数组的头部新增一个元素
        redisTemplate.opsForList().leftPush("TestList", "TestLeftPush");
    }


    @Test
    public void setRightPush() {
        // 那么 rightPush 就是往数组尾部插入一个元素
        redisTemplate.opsForList().rightPush("TestList", "TestRightPush");
    }


    @Test
    public void getRightPop() {
        // 从list右侧取出第一个元素,并移除
        Object rightFirstElement = redisTemplate.opsForList().rightPop("TestList");
        System.out.println(rightFirstElement);

        Object leftFirstElement = redisTemplate.opsForList().leftPop("TestList");
        System.out.println(leftFirstElement);
    }

    @Test
    public void getIndexValue() {
        // 根据下标获取值
        Object value = redisTemplate.opsForList().index("TestList", 1L);
        System.out.println(value);

    }


}
