package com.snackpub.core.containers;
// A simple Map.Entry for sample Map implementations.
import java.util.*;

/**
 * Map.entrySet()必须产生一个Map.Entry()对象集.但如何Map.Entry()是一个接口，用来描述依赖于的实现结构，
 * 如果你想创建自己的Map类型，就必须同事定义Map.Entry来实现.
 * @param <K> 泛型key
 * @param <V> 泛型value
 */
public class MapEntry<K,V> implements Map.Entry<K,V> {
  private K key;
  private V value;
  public MapEntry(K key, V value) {
    this.key = key;
    this.value = value;
  }
  public K getKey() { return key; }
  public V getValue() { return value; }
  public V setValue(V v) {
    V result = value;
    value = v;
    return result;
  }
  public int hashCode() {
    return (key==null ? 0 : key.hashCode()) ^
      (value==null ? 0 : value.hashCode());
  }
  public boolean equals(Object o) {
    if(!(o instanceof MapEntry)) return false;
    MapEntry me = (MapEntry)o;
    return
      (key == null ?
       me.getKey() == null : key.equals(me.getKey())) &&
      (value == null ?
       me.getValue()== null : value.equals(me.getValue()));
  }
  public String toString() { return key + "=" + value; }
} ///:~
