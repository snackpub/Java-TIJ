//: annotations/database/DBTable.java
package com.snackpub.core.annotations.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) // Applies to classes only
@Retention(RetentionPolicy.RUNTIME)
public @interface DBTable {
  // 通过name元素为处理器创建数据库表提供表的名字.
  String name() default "";
} ///:~
