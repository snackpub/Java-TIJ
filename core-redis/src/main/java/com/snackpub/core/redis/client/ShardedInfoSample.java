package com.snackpub.core.redis.client;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import org.springframework.data.redis.connection.PoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * 分区
 *
 * @author snackpub
 * @date 2020/4/30
 */
public class ShardedInfoSample {

    @Test
    public void shardZone() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        //表示redis的最大连接数——最大1000个线程
        config.setMaxTotal(1000);
        //表示最小空闲数量
        config.setMinIdle(5);

        //定义redis的多个节点机器
        List<JedisShardInfo> list = new ArrayList<>();

        list.add(new JedisShardInfo("192.168.25.1", "6379"));
        list.add(new JedisShardInfo("192.168.25.1", "6380"));

        // 定义redis分片连接池
        ShardedJedisPool shardedJedisPool = new ShardedJedisPool(config, list);


        //获取连接操作redis
        ShardedJedis shardedJedis = shardedJedisPool.getResource();

        for (int i = 0; i < 20; i++) {
            shardedJedis.set(i + "_sp", "snack" + i);
        }
        System.out.println("successfully");
    }
}
