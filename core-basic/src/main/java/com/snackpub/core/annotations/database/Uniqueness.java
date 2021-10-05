//: annotations/database/Uniqueness.java
// Sample of nested annotations
package com.snackpub.core.annotations.database;

public @interface Uniqueness {
  // 嵌入的@Constraints注解中的unique()元素为true,并以此作为constraints的默认值.
  Constraints constraints() default @Constraints(unique=true);
} ///:~
