package com.snackpub.core.redis.lock;

import com.snackpub.core.redis.base.BaseTest;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * redis 分布式锁测试
 *
 * @author snackpub
 * @date 2020/4/28
 */
public class LockClientTest extends BaseTest {

    @Test
    public void executeLock() {
        DistributedLock distributedLock = new DistributedLock(stringRedisTemplate);
        boolean lockStat = distributedLock.getLock("key", "snackpub", 1L, TimeUnit.MINUTES);
        System.out.println(lockStat);
    }

    @Test
    public void releaseLock() {
        DistributedLock distributedLock = new DistributedLock(stringRedisTemplate);
        boolean lockStat = distributedLock.releaseLock("key", "zs");
        System.out.println(lockStat);
    }

    @Test
    public void getStr() {
        String name = stringRedisTemplate.opsForValue().get("key");
        System.out.println(name);
        stringRedisTemplate.opsForValue().set("key", "zs");
    }


}
