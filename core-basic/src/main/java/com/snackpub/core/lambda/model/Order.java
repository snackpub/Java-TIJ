package com.snackpub.core.lambda.model;

import com.snackpub.core.lambda.fun.Transformer;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Order {

    List<OrderItem> items;

    public Order(List<OrderItem> orderItems) {
        items = orderItems;
    }

    /**
     * 接受 Transform<Stream<OrderItem> 作为参数，调用 transform 方法来转换属于 Order 实例的订单项，
     * 然后按转换后的顺序输出这些订单项。
     *
     * @param transformOrderItems
     */
    public void transformAndPrint(Transformer<Stream<OrderItem>> transformOrderItems) {
        transformOrderItems.transformer(items.stream()).forEach(System.out::println);
    }

}

class Sample {
    public static void main(String[] args) {
        Order order = new Order(Arrays.asList(
                new OrderItem(1, 1225),
                new OrderItem(2, 983),
                new OrderItem(3, 1554)
        ));
        //传递一个匿名内部类作为 transformAndPrint 方法的参数。
        // 在 transform 方法内，调用给定流的 sorted 方法，这会对订单项进行排序
        order.transformAndPrint(new Transformer<Stream<OrderItem>>() {
            @Override
            public Stream<OrderItem> transformer(Stream<OrderItem> orderItems) {
                return orderItems.sorted(Comparator.comparing(OrderItem::getPrice));
            }
        });
    }
}
