package com.snackpub.core.lambda.higher_order_fun;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * 只要一个函数接口显示为一个函数的参数的类型，您看到的就是一个高阶函数
 *
 * @author snackpub
 * @date 2020/4/20 13:00
 */
public class HigherOrderFunction {


    /**
     * 示例1. 一个接收函数的函数
     *
     * @param values   对象
     * @param selector 函数接口
     * @return int
     */
    public static int totalSelectedValues(List<Integer> values, Predicate<Integer> selector) {
        return values.stream()
                .filter(selector)
                .reduce(0, Integer::sum);
    }

    /**
     * 清单2：
     * 创建并返回 Predicate 来验证给定值是否为奇数的函数
     *
     * @return Predicate<Integer>
     */
    public static Predicate<Integer> createIsOdd() {
        return number -> number % 2 != 0;
    }

    /**
     * 清单3
     * 创建可重复的函数
     * 设想我们有两个列表 numbers1 和 numbers2。
     * 假设我们想从第一个列表中仅提取大于 50 的数，然后从第二个列表中提取大于 50 的值并乘以 2。
     */
    public static void createRepeatFun() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 10, 11, 12, 80, 70, 55);
        List<Integer> numbers2 = Arrays.asList(1, 2, 3, 4, 10, 11, 12, 80, 70, 50);

        // 重用一个Predicate，让代码更富裕表达
        Predicate<Integer> isGreaterThan50 = n -> n > 50;

        List<Integer> collect = numbers1.stream()
                .filter(isGreaterThan50)
                .collect(toList());
        List<Integer> collect1 = numbers1.stream()
                .filter(isGreaterThan50)
                .map(m -> m * 2)
                .collect(toList());

        // 示例2
        // 现在假设我们想从列表 numbers1 中提取大于 25、50 和 75 的值。我们可以首先编写 3 个不同的 lambda 表达式
        List<Integer> valuesOver25 = numbers1.stream()
                .filter(e -> e > 25)
                .collect(toList());

        List<Integer> valuesOver50 = numbers1.stream()
                .filter(e -> e > 50)
                .collect(toList());

        List<Integer> valuesOver75 = numbers1.stream()
                .filter(e -> e > 75)
                .collect(toList());
        // 尽管上面每个 lambda 表达式将输入与一个不同的值比较，但它们做的事情完全相同。如何以较少的重复来重写此代码？

        // 函数接口 Function<T, R> 将一个 T 类型的输入转换为 R 类型的输出
        // 引用 isGreaterThan 引用了一个表示 Function<T, R>— 或更准确地讲表示 Function<Integer, Predicate<Integer>> 的 lambda 表达式。
        // 输入是一个 Integer，输出是一个 Predicate<Integer>
        // 在 lambda 表达式的主体中（外部 {} 内），我们创建了另一个引用 isGreaterThanPivot，
        // 它包含对另一个 lambda 表达式的引用。这一次，该引用是一个 Predicate 而不是 Function。
        // 最后，我们返回该引用。
        // isGreaterThan 是一个 lambda 表达式的引用，该表达式在调用时返回另一个 lambda 表达式 — 换言之，这里隐藏着一种 lambda 表达式级联关系。
        Function<Integer, Predicate<Integer>> isGreaterThan = (Integer pivot) -> {
            Predicate<Integer> isGreaterThanPivot = (Integer candidate) -> {
                return candidate > pivot;
            };
            return isGreaterThanPivot;
        };
        // 可以使用新创建的外部 lambda 表达式来解决代码中的重复问题
        List<Integer> valuesOver25$1 = numbers1.stream()
                .filter(isGreaterThan.apply(25))
                .collect(toList());

        List<Integer> valuesOver50$2 = numbers1.stream()
                .filter(isGreaterThan.apply(50))
                .collect(toList());

        List<Integer> valuesOver75$3 = numbers1.stream()
                .filter(isGreaterThan.apply(75))
                .collect(toList());
        // 在 isGreaterThan调用 apply 返回一个Predicate作为参数传递给filter
        // 尽管整个过程非常简单（作为示例），但是能够抽象为一个函数对于谓词更加复杂的场景来说尤其有用。

        // =====================================

        // 保持简单的秘诀
        // 我们已从代码中成功删除了重复的 lambda 表达式，但 isGreaterThan 的定义看起来仍然很杂乱。
        // 幸运的是，我们可以组合一些 Java 8 约定来减少杂乱，让代码更简短。

        // 可以使用类型引用来从外部和内部 lambda 表达式的参数中删除类型细节
        Function<Integer, Predicate<Integer>> isGreaterThan$1 = (pivot) -> {
            Predicate<Integer> isGreaterThanPivot = (candidate) -> {
                return candidate > pivot;
            };
            return isGreaterThanPivot;
        };

        // 目前，我们从代码中删除了两个单词，改进不大。
        // 接下来，我们删除多余的 ()，以及外部 lambda 表达式中不必要的临时引用：
        Function<Integer, Predicate<Integer>> isGreaterThan$2 = pivot -> {
            return candidate -> candidate > pivot;
        };

        // 现在可以看到，外部 lambda 表达式的主体也只有一行，所以 {} 和 return 在这里也是多余的。在这里，我们应用最后一次重构：
        Function<Integer,Predicate<Integer>> isGreaterThan$3 = pivot -> candidate -> candidate > pivot;
        // 现在可以看到 — 这是我们的级联 lambda 表达式
    }

    public static void main(String[] args) {

        // 清单3
        createRepeatFun();
        print("------------------", System.out::println);

        // 清单2
        Predicate<Integer> isOdd = createIsOdd();
        boolean test = isOdd.test(4);
        print(test, System.out::println);
        print("----------------", System.out::println);

        IntStream intStream = IntStream.of(1, 2, 4, 5, 6);
        // boxed 返回一个Stream流，它由这个流的元素组成，每个元素都装箱为Integer，这是一个中间操作，返回
        // 一个一致的Stream元素
        List<Integer> numbers = intStream.boxed().collect(toList());
        // 参数2是函数式接口（Predicate），我们可以将一个lambda表达式作为第二个参数传递给totalSelectedValues
        // 例如，如果我们想仅对一个 numbers 列表中的偶数值求和
        int i = totalSelectedValues(numbers, e -> e % 2 == 0);
        print(i, System.out::print);
    }

    interface Outprint {
        void printInfo(Object info);
    }


    static void print(Object info, Outprint outprint) {
        outprint.printInfo(info);
    }


}
