package com.snackpub.core.containers;//: containers/SpringDetector.java
// What will the weather be?
import java.lang.reflect.*;
import java.util.*;

import static com.snackpub.core.util.Print.print;

public class SpringDetector {
  // Uses a Groundhog or class derived from Groundhog:
  public static <T extends Groundhog>
  void detectSpring(Class<T> type) throws Exception {
    Constructor<T> ghog = type.getConstructor(int.class);
    Map<Groundhog,Prediction> map =
            new HashMap<>();
    for(int i = 0; i < 10; i++)
      map.put(ghog.newInstance(i), new Prediction());
    print("map = " + map);
    // 使用反射实例化及使用Groundhog类，或任何从Groundhog派生出来的类，使用标识数字为3的Groundhog作为键来查找与之对应的预报内容
    // （可以看到它一定实在Map中）
    Groundhog gh = ghog.newInstance(3);
    print("Looking up prediction for " + gh);
    // 很显然根本在Map中找不到，因为Groundhog自动的继承Object类，所以这里使用的是Object的hashCode()方法生成的散列码.
    // 而它默认使用对象的地址计算散列码.你必须同时覆盖hashCode()和equals()
    if(map.containsKey(gh))
      print(map.get(gh));
    else
      print("Key not found: " + gh);
  }
  public static void main(String[] args) throws Exception {
    detectSpring(Groundhog.class);
  }
} /* Output:
map = {Groundhog #3=Early Spring!, Groundhog #7=Early Spring!, Groundhog #5=Early Spring!, Groundhog #9=Six more weeks of Winter!, Groundhog #8=Six more weeks of Winter!, Groundhog #0=Six more weeks of Winter!, Groundhog #6=Early Spring!, Groundhog #4=Six more weeks of Winter!, Groundhog #1=Six more weeks of Winter!, Groundhog #2=Early Spring!}
Looking up prediction for Groundhog #3
Key not found: Groundhog #3
*///:~
