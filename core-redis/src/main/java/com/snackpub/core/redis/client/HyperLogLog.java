package com.snackpub.core.redis.client;

import com.snackpub.core.redis.base.BaseTest;
import org.junit.Test;

/**
 * Redis HyperLogLog 是用来做基数统计的算法，HyperLogLog 的优点是，在输入元素的数量或者体积非常非常大时，计算基数所需的空间总是固定 的、并且是很小的。
 * <p>
 * 在 Redis 里面，每个 HyperLogLog 键只需要花费 12 KB 内存，就可以计算接近 2^64 个不同元素的基 数。这和计算基数时，元素越多耗费内存就越多的集合形成鲜明对比。
 * <p>
 * 但是，因为 HyperLogLog 只会根据输入元素来计算基数，而不会储存输入元素本身，所以 HyperLogLog 不能像集合那样，返回输入的各个元素
 *
 * @author snackpub
 * @date 2020/4/29
 */
public class HyperLogLog extends BaseTest {


    @Test
    public void testAdd() {
        redisTemplate.opsForHyperLogLog().add("snackpub", "snack_1");
        redisTemplate.opsForHyperLogLog().add("snackpub", "snack_2");
        redisTemplate.opsForHyperLogLog().add("snackpub", "snack_3");
        redisTemplate.opsForHyperLogLog().add("snackpub", "snack_4");
        redisTemplate.opsForHyperLogLog().add("snackpub", "snack_5");
        redisTemplate.opsForHyperLogLog().add("snackpub", "snack_5");
    }

    @Test
    public void testGet() {
        Long snackpub = redisTemplate.opsForHyperLogLog().size("snackpub");
        System.out.println(snackpub);
    }


}
