package com.snackpub.core.redis.client;

import com.snackpub.core.redis.base.BaseInterface;
import com.snackpub.core.redis.base.BaseTest;
import org.junit.Test;
import org.springframework.data.redis.core.RedisCallback;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis 服务器相关命令
 *
 * @author snackpub
 * @date 2020/6/3 23:03
 * @date 2020/6/11 22:11
 */
public class OtherCommondSample extends BaseTest implements BaseInterface {

    @Test
    public void keys() {
        Set<byte[]> keys = (Set<byte[]>) redisTemplate.execute((RedisCallback) connection -> connection.keys("mylist*".getBytes()));
    }

    @Test
    public void expire() {
        Runnable runnable = () -> {
            try {
                redisTemplate.opsForHash().put("snackExpire", "key1", "love you");
                String snackExpire = (String) redisTemplate.opsForHash().get("snackExpire", "key1");
                System.out.println("snackExpire: " + snackExpire);
                //设置 snackExpire 预期时间 10 seconds
                redisTemplate.expire("snackExpire", 10L, TimeUnit.SECONDS);
                // 休眠 11 秒 1000 ms = 1 s
                Thread.sleep(1000L);
                String snackExpire2 = (String) redisTemplate.opsForHash().get("snackExpire", "key1");
                System.out.println("snackExpire2: " + snackExpire2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }


    @Override
    public void add() {

    }

    @Override
    public void del() {

    }

    @Override
    public void get() {

    }
}
