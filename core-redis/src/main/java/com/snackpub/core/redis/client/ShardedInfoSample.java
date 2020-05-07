package com.snackpub.core.redis.client;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * Jedis客户端操作分片模式（ShardedJedis）
 * <p>
 * 分区是分割数据到多个redis实例的处理过程，因此每个实例只保存一个key的子集
 * <p>
 * 分区的优势：
 * 1. 通过利用多台计算机内存的和值，允许我们构造更大的数据库
 * 2. 通过多核和多台计算机，允许我们扩展计算能力；通过多台计算机和网络适配器，允许我们扩展网络宽带
 * 分区的不足：
 * 1. 涉及多个key的操作通常是不被支持。举例来说，当两个set映射到不同的redis实例上时，你就不能对这两个set执行交集操作。
 * 2. 涉及多个key的redis事务不能被使用；
 * 3. 当使用分区时，数据处理较为复杂，比如你需要处理多个rdb/aof文件，并且从多个实例和主机备份持久化文件
 * 4. 增加或删除容量也比较复杂。redis集群大多数支持在运行时增加、删除节点的透明数据平衡的能力，
 * 但是类似于客户端分区、代理等其他系统则不支持这项特性。然而，一种叫做presharding的技术对此是有帮助的
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

        list.add(new JedisShardInfo("192.168.25.1", 6379));
        list.add(new JedisShardInfo("192.168.25.1", 6387));

        // ShardedJedisPool 是基于一致性哈希算法实现的分片连接池
        ShardedJedisPool shardedJedisPool = new ShardedJedisPool(config, list);


        // 获取连接操作redis
        // Redis服务器节点划分：将每台服务器节点采用hash算法划分为160个虚拟节点(可以配置划分权重)
        // (1)将划分虚拟节点采用TreeMap存储
        // (2)对每个Redis服务器的物理连接采用LinkedHashMap存储
        // (3)对Key or KeyTag 采用同样的hash算法，然后从TreeMap获取大于等于键hash值得节点，取最邻近节点存储；当key的hash值大于虚拟节点hash值得最大值时，存入第一个虚拟节点
        // sharded采用的hash算法：MD5 和 MurmurHash两种；默认采用64位的MurmurHash算法；MurmurHash是一种高效，低碰撞的hash算法.
        ShardedJedis shardedJedis = shardedJedisPool.getResource();

        for (int i = 0; i < 10; i++) {
            shardedJedis.set(i + "_sp", "snack" + i);
            shardedJedis.sadd("sharded" + i, "shard1", "shard2", "shard3");
        }
        System.out.println("successfully");
    }
}
