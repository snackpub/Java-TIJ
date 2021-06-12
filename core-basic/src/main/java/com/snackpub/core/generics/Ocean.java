package com.snackpub.core.generics;

import com.snackpub.core.util.Generator;

import java.util.*;

/**
 * 大鱼吃小鱼
 *
 * @author snackpub
 * @date 2021/6/12
 */

class BigFish {
    private static long counter = 1;
    private final long id = counter++;

    private BigFish() {
    }

    @Override
    public String toString() {
        return "BigFish " + id;
    }

    static Generator<BigFish> bigFishGenerator() {
        return BigFish::new;
    }
}

class LittleFish {
    private static long counter = 1;
    private final long id = counter++;

    private LittleFish() {
    }

    @Override
    public String toString() {
        return "LittleFish " + id;
    }

    static Generator<LittleFish> littleFishGenerator = LittleFish::new;
}

public class Ocean {
    private static void serve(BigFish b, LittleFish l) {
        System.out.println(b + " cat " + l);
    }

    public static void main(String[] args) {
        Random rand = new Random(47);
        Queue<BigFish> line = new LinkedList<>();
        Generators.fill(line, BigFish.bigFishGenerator(), 15);

        List<LittleFish> littleFishs = new ArrayList<>();
        Generators.fill(littleFishs, LittleFish.littleFishGenerator, 5);

        for (BigFish b : line)
            serve(b, littleFishs.get(rand.nextInt(littleFishs.size())));

    }
}
