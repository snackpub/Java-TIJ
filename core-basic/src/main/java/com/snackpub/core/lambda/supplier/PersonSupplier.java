package com.snackpub.core.lambda.supplier;

import com.snackpub.core.lambda.model.Person;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * 自定义Supplier
 * 例如在构造海量测试数据的时候，用某种自动的规则给每一个变量赋值；
 * 或者依据公式计算 Stream 的每个元素值。这些都是维持状态信息的情形。
 *
 * @author snackpub
 * @date 2020/4/11 22:25
 */
public class PersonSupplier implements Supplier<Person> {

    private int index = 0;
    private Random random = new Random();

    @Override
    public Person get() {
        return new Person(index++, "StormTestUser" + index, random.nextInt(100));
    }

    public static void main(String[] args) {
        // Stream.generate 接受自定义实现的Supperlier
        Stream.generate(new PersonSupplier()).limit(10)
                .forEach(p -> System.out.println(p.getName() + ", " + p.getAge()));
    }
}
