package com.snackpub.core.redis.client;

import com.snackpub.core.redis.base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.ScanOptions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * redis 管道流测试
 * <p>
 * Redis 管道技术可以在服务端未响应时，客户端可以继续向服务端发送请求，并最终一次性读取所有服务端的响应
 * pipeline 是redis提高吞吐量的机制，适用于多key读写场景，会占用大量内存（用pipeline方式打包发送命令，redis必须
 * 在处理完所有命令前先缓存所有命令的处理结果。打包的越多，缓存消耗的内存也越多）。
 * pipeline 的实现原理是队列，而队列的原理是先进先出，这样就保证数据的循序性。
 * Pipeline 的默认的同步的个数为53个，也就是说arges中累加到53条数据时会把数据提交。
 * <p>
 * pipeline：管道本身就是能够承载流式数据的一个长链路
 *
 * @author snackpub
 * @date 2020/4/30
 */
@Slf4j
public class PipeLineSample extends BaseTest {


    @Test
    public void pipeLineExecute() {
        long start = System.currentTimeMillis();
        List<Object> execute = redisTemplate.executePipelined(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                // 可调用可不调用
                connection.openPipeline();
                for (int i = 0; i < 100000; i++) {
                    connection.zAdd((i + "").getBytes(), i + 1, ("123" + i).getBytes());
//                    String key = "123" + i;
//                    connection.zCount(key.getBytes(), 0, Integer.MAX_VALUE);
                }

                // 但是connection.closePipeline()不能调用，调用了拿不到返回值。因为调用的时候会直接将结果返回，同时也不会对代码进行反序列化

                // doInredis 必须返回null
                return null;
            }
        });
        long end = System.currentTimeMillis();
        log.info("the redis pipeLine total time is: {} seconds", ((end - start) % (1000 * 60)) / 1000);

    }

    @Test
    public void common() {
        long start = System.currentTimeMillis();
        redisTemplate.execute((RedisCallback) connection -> {
            for (int i = 0; i < 10; i++) {
                // connection.zAdd((i + "").getBytes(), i + 1, ("123" + i).getBytes());
                connection.lPush(("test:" + i).getBytes(), (i + 1 + "").getBytes());
            }
            return null;
        });
        long end = System.currentTimeMillis();
        log.info("the total time is: {} seconds", ((end - start) % (1000 * 60)) / 1000);
    }

    // 通过上面的测试发现，使用redis pipeline 性能明显大幅度提升;我在本机测试1000000数据的时使用pipeline写入数据
    // 大概用了 15 秒，普通插入方式用了 40 秒，在大量数据写入的时候性能还是差距很大
    // 这里使用的是本机测试，后续增加内网测试,异地测试


    /**
     * 从管道流获取数据
     *
     */
    @Test
    public void getPipeline() {
        long start1 = System.currentTimeMillis();

        Set keys = redisTemplate.keys("test*");
        System.out.println(keys);

        Set<byte[]> list = (Set<byte[]>) redisTemplate.execute((RedisCallback) connection ->
                connection.keys("test*".getBytes())
        );

        long start = System.currentTimeMillis();
        Set<byte[]> list1 = (Set<byte[]>)  redisTemplate.executePipelined((RedisCallback) connection -> {
            // 管道一次只能处理一批相同的操作！
            connection.keys("test*".getBytes()); // 这里会返回所有拿到的数据，不能将这里作为返回值return
            return null; // 管道里面只能返回null
        });
        System.out.println(list1);
        long end = System.currentTimeMillis();
        log.info("the total time is: {} seconds", ((end - start) % (1000 * 60)) / 1000);

    }


    /**
     * 生产环境数据量比较多的时候容易造成阻塞！，禁用！
     *
     * @param pattern 匹配的模式
     * @return set
     */
    public Set<String> keys(String pattern) {
        try {
            Set<String> keys = redisTemplate.keys(pattern);
            return keys;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Scan 命令用于迭代数据库中的数据库键
     *
     * @param pattern 匹配的模式
     * @param count   指定从数据集里返回多少元素，默认值为 10
     * @return
     */
    public Set<String> scan(String pattern, Long count) {
        try {
            return (Set<String>) redisTemplate.execute((RedisCallback<Set<String>>) connection -> {
                ScanOptions.ScanOptionsBuilder scanOptionsBuilder = ScanOptions.scanOptions();
                scanOptionsBuilder.match(pattern);
                scanOptionsBuilder.count(count);
                Cursor<byte[]> scan = connection.scan(scanOptionsBuilder.build());
                Set<String> result = new HashSet<>();
                while (scan.hasNext()) {
                    byte[] key = scan.next();
                    result.add(new String(key));
                }
                return result;
            });
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
