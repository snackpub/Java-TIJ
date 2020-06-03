package com.snackpub.core.redis.client;

import com.snackpub.core.redis.base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;

import java.util.Collections;
import java.util.LinkedHashSet;
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
        List execute = redisTemplate.executePipelined(new RedisCallback<Long>() {
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
            for (int i = 0; i < 100000; i++) {
                // connection.zAdd((i + "").getBytes(), i + 1, ("123" + i).getBytes());
                connection.set(("" + i).getBytes(), (i + 1 + "").getBytes());
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
     */
    @Test
    public void getPipeline() {
        long start1 = System.currentTimeMillis();
        Set<byte[]> list = (Set<byte[]>) redisTemplate.execute((RedisCallback) connection ->
                connection.keys("10*".getBytes())
        );
        //   Set<String> collect = list.stream().map(n -> new String(n)).collect(Collectors.toSet());
        //  换成构造器引用
        Set<String> collect = list.stream().map(String::new).collect(Collectors.toSet());
       /* list.forEach(n->{
            String s = new String(n);
            System.out.println(s);
        });*/
        System.out.println(collect);
        long end2 = System.currentTimeMillis();
        log.info("the total time is: {} seconds", ((end2 - start1) % (1000 * 60)) / 1000);
        System.out.println("---------------------------");

        long start = System.currentTimeMillis();
        List<String> list1 = (List<String>) redisTemplate.executePipelined((RedisCallback) connection ->
                connection.keys("10*".getBytes())
        );
        System.out.println(list1);
        long end = System.currentTimeMillis();
        log.info("the total time is: {} seconds", ((end - start) % (1000 * 60)) / 1000);

    }


}
