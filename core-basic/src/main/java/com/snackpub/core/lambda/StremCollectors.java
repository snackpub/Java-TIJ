package com.snackpub.core.lambda;

import com.snackpub.core.lambda.model.Person;
import com.snackpub.core.lambda.supplier.PersonSupplier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 用 Collectors 来进行 reduction 操作
 * java.util.stream.Collectors 类的主要重要就是辅助进行各类有用的reduction操作
 * 例如转变输出为Collection，把Stream元素进行归组
 *
 * @author snackpub
 * @date 2020/4/11 22:55
 */
public class StremCollectors {

    public static void main(String[] args) {
//        group();
//        partitioningBy();
        groupIP();
    }

    /**
     * groupingBy/partitioningBy
     * 按照年龄归组
     * 首先生成 100 人的信息，然后按照年龄归组，相同年龄的人放到同一个 list 中
     */

    public static void group() {
        Map<Integer, List<Person>> personGroups = Stream
                .generate(new PersonSupplier())
                .limit(100)
                .collect(Collectors.groupingBy(Person::getAge));
        Iterator it = personGroups.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, List<Person>> persons = (Map.Entry) it.next();
            System.out.println("Age " + persons.getKey() + " = " + persons.getValue().size());

        }
    }

    public static void groupIP() {
        // 内部类方式add默认初始值，这种方式性能很差
        List<PlayList> ips = new ArrayList<>(new ArrayList<PlayList>() {{
            add(new PlayList("192.168.1.1", 0));
        }});
        for (int i = 0; i < 5; i++) {
            ips.add(new PlayList("192.168.1." + i, i));
        }
        String ip = "192.168.1.1";
        Map<Boolean, List<PlayList>> collect = ips.stream().collect(
                Collectors.partitioningBy(m -> m.getIp().equals(ip))
        );

        int size = collect.get(true).size();
        System.out.println(size);

        System.out.println("-------------------------");
        Iterator iterator = collect.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Boolean, List<PlayList>> next = (Map.Entry) iterator.next();
            System.out.println("ip " + next.getKey() + " = " + next.getValue());
        }
    }

    @Data
    @AllArgsConstructor
    private static class PlayList {
        private String ip;
        private Integer id;
    }


    /**
     * partitioningBy
     * 按照未成年人和成年人归组
     * 在使用条件“年龄小于 18”进行分组后可以看到，不到 18 岁的未成年人是一组，
     * 成年人是另外一组。partitioningBy 其实是一种特殊的 groupingBy，
     * 它依照条件测试的是否两种结果来构造返回的数据结构，get(true) 和 get(false) 能即为全部的元素对象
     */

    public static void partitioningBy() {
        Map<Boolean, List<Person>> children = Stream.generate(new PersonSupplier())
                .limit(100)
                .collect(Collectors.partitioningBy(p -> p.getAge() < 18));
        System.out.println("Children number: " + children.get(true).size());
        System.out.println("Adult number: " + children.get(false).size());
    }

}
