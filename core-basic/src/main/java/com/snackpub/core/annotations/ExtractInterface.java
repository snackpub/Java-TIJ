//: annotations/ExtractInterface.java
// APT-based annotation processing.
package com.snackpub.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE) // 保留策略是SOURCE,我们从注解的类中抽取出接口之后，没必要在保留这些信息.
public @interface ExtractInterface {
  String value();
} ///:~
