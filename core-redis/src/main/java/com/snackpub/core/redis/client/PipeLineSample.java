package com.snackpub.core.redis.client;

import com.snackpub.core.redis.base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;

import java.util.List;

/**
 * redis 管道流测试
 * <p>
 * Redis 管道技术可以在服务端未响应时，客户端可以继续向服务端发送请求，并最终一次性读取所有服务端的响应
 * pipeline 是redis提高吞吐量的机制，适用于多key读写场景，会占用大量内存
 * <p>
 * pipeline：管道本身就是能够承载流式数据的一个长链路
 *
 * @author snackpub
 * @date 2020/4/30
 * @lastdate 2020/5/5 17:50
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

                // doInredi 必须返回null
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
            for (int i = 0; i < 1000000; i++) {
                connection.zAdd((i + "").getBytes(), i + 1, ("123" + i).getBytes());
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
        redisTemplate.execute((RedisCallback) connection -> {
//            connection.incr()
            connection.get("10004".getBytes());
            return null;
        });
    }


}
