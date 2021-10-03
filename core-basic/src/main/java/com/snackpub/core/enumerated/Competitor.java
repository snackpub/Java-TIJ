//: enumerated/Competitor.java
// Switching one enum on another.
package com.snackpub.core.enumerated;

/**
 * Competitor 定义了一种类型，该类型的对象与另一个Competitor相竞争.
 * @param <T>
 */
public interface Competitor<T extends Competitor<T>> {
  Outcome compete(T competitor);
} ///:~
