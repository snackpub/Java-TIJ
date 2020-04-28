package com.snackpub.core.redis.lock;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.Expiration;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * redis 分布式锁
 *
 * @author snackpub
 * @date 2020/4/28
 */
@AllArgsConstructor
public class DistributedLock {

    private StringRedisTemplate stringRedisTemplate;


    public boolean getLock(String key, String value, long expirationTime, TimeUnit timeUnit) {
        Boolean lockStat = stringRedisTemplate.execute((RedisCallback<Boolean>) connection ->
                connection.set(key.getBytes(Charset.forName("UTF-8")), value.getBytes(Charset.forName("UTF-8")),
                        Expiration.from(expirationTime, timeUnit), RedisStringCommands.SetOption.SET_IF_ABSENT));

        /*stringRedisTemplate.execute(new RedisCallback<Boolean>() {

            @Override
            public Boolean doInRedis(RedisConnection connection) {
                return connection.set(key.getBytes(Charset.forName("UTF-8")), value.getBytes(Charset.forName("UTF-8")),
                        Expiration.from(expirationTime, timeUnit), RedisStringCommands.SetOption.SET_IF_ABSENT);)
            }
        });*/

        return lockStat;
    }

    public boolean releaseLock(String key, String value) {
        // script Lua 脚本
        // KEYS[1] 表示加锁的key
        // ARGV[1] value 只有当线程上下文对象中的值与redis保存的值一致时,才能删掉这个key,释放成功
        // eval()方法是将Lua代码交给Redis服务端执行
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        boolean unLockStat = stringRedisTemplate.execute((RedisCallback<Boolean>) connection ->
                connection.eval(script.getBytes(), ReturnType.BOOLEAN, 1,
                        key.getBytes(Charset.forName("UTF-8")), value.getBytes(Charset.forName("UTF-8"))));
        return unLockStat;
    }


}
