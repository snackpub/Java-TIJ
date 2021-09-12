package com.snackpub.core.enumerated;//: enumerated/TrafficLight.java
// Enums in switch statements.

import static com.snackpub.core.util.Print.print;

// Define an enum type:
enum Signal { GREEN, YELLOW, RED, }

/**
 * 枚举天生就具备整数值的次序，并且通过ordinal()方法取得次序（显然编译器帮我们做了很多工作）
 */
public class TrafficLight {
  private Signal color = Signal.RED;
  private void change() {
    switch(color) {
      // Note that you don't have to say Signal.RED
      // in the case statement:
      case RED:    color = Signal.GREEN;
                   break;
      case GREEN:  color = Signal.YELLOW;
                   break;
      case YELLOW: color = Signal.RED;
                   break;
    }
  }
  public String toString() {
    return "The traffic light is " + color;
  }
  public static void main(String[] args) {
    TrafficLight t = new TrafficLight();
    for(int i = 0; i < 7; i++) {
      print(t);
      t.change();
    }
  }
} /* Output:
The traffic light is RED
The traffic light is GREEN
The traffic light is YELLOW
The traffic light is RED
The traffic light is GREEN
The traffic light is YELLOW
The traffic light is RED
*///:~
