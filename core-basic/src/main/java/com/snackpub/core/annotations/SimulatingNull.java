package com.snackpub.core.annotations;//: annotations/SimulatingNull.java

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SimulatingNull {
  int id() default -1; // 默认值限制
  String description() default "";
} ///:~
