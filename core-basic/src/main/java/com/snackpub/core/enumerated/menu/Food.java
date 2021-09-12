//: enumerated/menu/Food.java
// Subcategorization of enums within interfaces.
package com.snackpub.core.enumerated.menu;


/**
 * 使用接口组织枚举，达到将枚举元素分类组织的目的。
 */
public interface Food {
  enum Appetizer implements Food {
    SALAD, SOUP, SPRING_ROLLS
  }
  enum MainCourse implements Food {
    LASAGNE, BURRITO, PAD_THAI,
    LENTILS, HUMMOUS, VINDALOO
  }
  enum Dessert implements Food {
    TIRAMISU, GELATO, BLACK_FOREST_CAKE,
    FRUIT, CREME_CARAMEL
  }
  enum Coffee implements Food {
    BLACK_COFFEE, DECAF_COFFEE, ESPRESSO,
    LATTE, CAPPUCCINO, TEA, HERB_TEA
  }

  enum Sugar implements Food {
      BLACK_SUGAR, WHITE_SUGAR;
  }

} ///:~
