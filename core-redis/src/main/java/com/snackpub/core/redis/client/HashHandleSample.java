package com.snackpub.core.redis.client;

import com.alibaba.fastjson.serializer.JSONSerializableSerializer;
import com.snackpub.core.redis.base.BaseTest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.redis.core.RedisCallback;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 操作Hash
 * <p>
 * Redis 中的 Hash 数据结构实际上与 Java 中的 HashMap 是非常类似的，提供的 API 也很类似
 * redis hash 是一个string类型的filed和value的映射表，hash特别适合用于存储对象
 *
 * @author snackpub
 * @date 2020/4/28
 */
public class HashHandleSample extends BaseTest {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class User {

        private Double height;
        private Double width;
    }


    @Test
    public void testPut() {
        // 判断指定 key 对应的 Hash 中是否存在指定的 map 键
        Assert.assertTrue(redisTemplate.opsForHash().hasKey("TestHash", "FirstElement"));
        // hash 新增元素
        redisTemplate.opsForHash().put("TestHash", "FirstElement", new User(170.0, 130.0));

    }


    @Test
    public void testGet() {
        // 获取指定 key 对应的 Hash 中指定键的值。
        User user = (User) redisTemplate.opsForHash().get("TestHash", "FirstElement");
        System.out.println(user);

        List<User> testHash = redisTemplate.opsForHash().values("TestHash");
        System.out.println(testHash);

        // 不推荐使用内部类初始化数据
        Map<Object, Object> hvs = new HashMap<Object, Object>(new HashMap(3) {{
            put("user-one", new User(100.0, 1000.0));
            put("user-two", new User(200.0, 2000.0));
            put("user-three", new User(300.0, 3000.0));
        }});
        redisTemplate.opsForHash().putAll("putAllTest", hvs);

    }


    @Test
    public void mSet() {
        // Redis Hmset 命令用于同时将多个 field-value (字段-值)对设置到哈希表中。
        // 此命令会覆盖哈希表中已存在的字段。
        // 如果哈希表不存在，会创建一个空哈希表，并执行 HMSET 操作。
        String key = "hmset";
        Map<byte[], byte[]> hashes = new LinkedHashMap<>(3);
        hashes.put("key1".getBytes(), "value1".getBytes());
        hashes.put("key2".getBytes(), "value2".getBytes());
        hashes.put("key3".getBytes(), "value3".getBytes());
        // 第一个true：是否强制将本机Redis连接公开到回调代码
        // 第二个true：开启管道式连接; 管道的结果被丢弃(这使得它适合于只写的场景)。

        redisTemplate.execute(connection -> {
            connection.hMSet(key.getBytes(), hashes);
            // 当pipeline设置为true后不需要关闭管道连接，至于为啥自己看源码
            // connection.closePipeline();
            return null;
        }, true, true);
    }


    @Test
    public void hdel() {
        // Hdel 命令用于删除哈希表 key 中的一个或多个指定字段，不存在的字段将被忽略。

        Long delSize = (Long) redisTemplate.execute((RedisCallback) connection ->
                connection.hDel("hmset".getBytes(), "123".getBytes())
        );

        //对应 redisTemplate.opsForHash.delete

        System.out.println("hdel successfully count: " + delSize);

    }

    @Test
    public void testDel() {
        redisTemplate.opsForHash().delete("TestHash", "FirstElement");
        Assert.assertFalse(redisTemplate.opsForHash().hasKey("TestHash", "FirstElement"));
    }


    @SneakyThrows
    @Test
    public void hExists() {
        // Redis Hexists 命令用于查看哈希表的指定字段是否存在。

        String hkey = "hmset";

        boolean bool = (boolean) redisTemplate.execute((RedisCallback) connection ->
                connection.hExists(hkey.getBytes(StandardCharsets.UTF_8), "key1".getBytes(StandardCharsets.UTF_8))
        );
        if (bool) System.out.println("given hash " + hkey + " exists");
        else System.out.println("given hash " + hkey + " no exists");
    }

    @SneakyThrows
    @Test
    public void hGetAll() {

        String hkey = "hmset";
        /*Redis Hgetall 命令用于返回哈希表中，所有的字段和值。
        在返回值里，紧跟每个字段名(field name)之后是字段的值(value)，所以返回值的长度是哈希表大小的两倍*/

        Map<byte[], byte[]> hasKyes = (Map<byte[], byte[]>) redisTemplate.execute((RedisCallback) connection -> {
            Map<byte[], byte[]> map = connection.hGetAll(hkey.getBytes());
            return map;
        });

        System.out.println("Hgetall " + hkey + " size " + hasKyes.size());

        Set<Map.Entry<byte[], byte[]>> entries = hasKyes.entrySet();
        Iterator<Map.Entry<byte[], byte[]>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<byte[], byte[]> next = iterator.next();
            System.out.println("filed " + new String(next.getKey()) + " value: " + new String(next.getValue()));
        }


    }


}
