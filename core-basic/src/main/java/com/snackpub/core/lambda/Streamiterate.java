package com.snackpub.core.lambda;

import java.util.stream.Stream;

/**
 * iterate跟reduce很像，接受一个种子(seed),和一个UnaryOperator（例如：f）。
 * 然后种子成为Stream的第一个元素，f(seed)为第二个，f(f(seed))第三个，以此类推。
 *
 * @author snackpub
 * @date 2020/4/11 22:40
 */
public class Streamiterate {


    public static void main(String[] args) {
        arithmeticProgression();
    }

    /**
     * 生成一个等差数列
     */
    public static void arithmeticProgression() {
        Stream.iterate(0, n -> n + 3).limit(10).forEach(x -> System.out.println(x + " "));
        // 与 Stream.generate 相仿，在 iterate 时候管道必须有 limit 这样的操作来限制 Stream 大小
    }
}
