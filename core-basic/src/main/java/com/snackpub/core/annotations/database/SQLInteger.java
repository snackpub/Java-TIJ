//: annotations/database/SQLInteger.java
package com.snackpub.core.annotations.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLInteger {
  String name() default "";
  // 嵌套注解,注意默认值是@Constraints,没有在括号中声明@Constraints的值
  // 因此，constraints()元素的默认值实际上就是一个所有元素都为默认值的@Constraints注解.
  Constraints constraints() default @Constraints;
} ///:~
