//: enumerated/menu/TypeOfFood.java
package com.snackpub.core.enumerated.menu;
import static com.snackpub.core.enumerated.menu.Food.*;

/**
 * 对于enum而言，实现接口是使其子类化的唯一办法
 */
public class TypeOfFood {
  public static void main(String[] args) {
    // 所有的东西都是某种类型的Food.
    Food food = Food.Appetizer.SALAD;
    food = MainCourse.LASAGNE;
    food = Dessert.GELATO;
    food = Coffee.CAPPUCCINO;
  }
} ///:~
