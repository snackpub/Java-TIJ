//: enumerated/EnumSets.java
// Operations on EnumSets
package com.snackpub.core.control;

import com.snackpub.core.enumerated.AlarmPoints;

import java.util.EnumSet;

import static com.snackpub.core.enumerated.AlarmPoints.*;
import static com.snackpub.core.util.Print.print;

/**
 * EnumSet中的元素必须来自一个enum。
 * of()方法被重载了多次，不但为可变数量参数进行了重载，而且为接收2至5个显式的参数的情况都进行了重载。
 * 这从侧面反映了EnumSet对性能的关注。因为，其实只使用可变参数已经可以解决整个问题了，但是对比显示的参数，会有一点性能损失。
 * 采用现在这种设计，当你使用2到5个参数的of时，你可以调用对应的重载方法。如果你只是用1个参数，或者超过5个参数的of()时，
 * 你将调用的是可变参数的of()方法。注意，如果你只使用1个参数，编译器并不会构造可变参数数组，所以与调用只有一个参数的方法
 * 相比，也不会有额外的性能损耗。
 *
 * @date 2021/9/12
 */
public class EnumSets {
  public static void main(String[] args) {
    EnumSet<AlarmPoints> points =
      EnumSet.noneOf(AlarmPoints.class); // Empty set
    points.add(BATHROOM);
    print(points);
    points.addAll(EnumSet.of(STAIR1, STAIR2, KITCHEN));
    print(points);
    points = EnumSet.allOf(AlarmPoints.class); // 将enum实例的所有元素转换成EnumSet
    points.removeAll(EnumSet.of(STAIR1, STAIR2, KITCHEN)); // 移除特定的元素
    print(points);
    points.removeAll(EnumSet.range(OFFICE1, OFFICE4)); // 将OFFICE1 - OFFICE4 位置内的元素移除，包含开头结尾.
    print(points);
    points = EnumSet.complementOf(points); // 初始包含的元素将不会包含在新生成的集合中
    print(points);
  }
} /* Output:
[BATHROOM]
[STAIR1, STAIR2, BATHROOM, KITCHEN]
[LOBBY, OFFICE1, OFFICE2, OFFICE3, OFFICE4, BATHROOM, UTILITY]
[LOBBY, BATHROOM, UTILITY]
[STAIR1, STAIR2, OFFICE1, OFFICE2, OFFICE3, OFFICE4, KITCHEN]
*///:~
