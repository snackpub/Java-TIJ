//: enumerated/RoShamBo1.java
// Demonstration of multiple dispatching.
package com.snackpub.core.enumerated;

import java.util.Random;

import static com.snackpub.core.enumerated.Outcome.*;

// Item有几种类型的接口，将会被用做多路分发.
interface Item {
  Outcome compete(Item it);
  Outcome eval(Paper p);
  Outcome eval(Scissors s);
  Outcome eval(Rock r);
}

class Paper implements Item {
  // compete将起到分发作用
  public Outcome compete(Item it) {
    // 通过调用另一个eval()为另个一类型实现第二次分发.
    // 将自身（this) 作为参数调用eval(),能够调用重载过的eval()方法，这能够保留第一次分发的类型信息.
    // 当第二次分发完成时，你就能够知道这两个item对象的具体类型.
    return it.eval(this);
  }
  public Outcome eval(Paper p) { return DRAW; }
  public Outcome eval(Scissors s) { return WIN; }
  public Outcome eval(Rock r) { return LOSE; }
  public String toString() { return "Paper"; }
}

class Scissors implements Item {
  public Outcome compete(Item it) { return it.eval(this); }
  public Outcome eval(Paper p) { return LOSE; }
  public Outcome eval(Scissors s) { return DRAW; }
  public Outcome eval(Rock r) { return WIN; }
  public String toString() { return "Scissors"; }
}

class Rock implements Item {
  public Outcome compete(Item it) { return it.eval(this); }
  public Outcome eval(Paper p) { return WIN; } // 胜利
  public Outcome eval(Scissors s) { return LOSE; } // 输掉
  public Outcome eval(Rock r) { return DRAW; } // 平局
  public String toString() { return "Rock"; }
}


// 多路分发的好处在于调用时的优雅语法，这避免了在一个地方判定多个类型对象的丑陋代码。
// 你只需说，“嘿， 你们两个，我不在乎你们是什么类型，请你们自己交流!”,不过在使用多路分发之前
// 确保这种优雅的代码对你确实有重要的意义.
public class RoShamBo1 {
  static final int SIZE = 20;
  private static Random rand = new Random(47);
  public static Item newItem() {
    switch(rand.nextInt(3)) {
      default:
      case 0: return new Scissors();
      case 1: return new Paper();
      case 2: return new Rock();
    }
  }
  public static void match(Item a, Item b) {
    System.out.println(
      a + " vs. " + b + ": " +  a.compete(b));
  }
  public static void main(String[] args) {
    for(int i = 0; i < SIZE; i++)
      // 调用match，传递两个参数开始两路分发
      match(newItem(), newItem());
  }
} /* Output:	
Rock vs. Rock: DRAW
Paper vs. Rock: WIN
Paper vs. Rock: WIN
Paper vs. Rock: WIN
Scissors vs. Paper: WIN
Scissors vs. Scissors: DRAW
Scissors vs. Paper: WIN
Rock vs. Paper: LOSE
Paper vs. Paper: DRAW
Rock vs. Paper: LOSE
Paper vs. Scissors: LOSE
Paper vs. Scissors: LOSE
Rock vs. Scissors: WIN
Rock vs. Paper: LOSE
Paper vs. Rock: WIN
Scissors vs. Paper: WIN
Paper vs. Scissors: LOSE
Paper vs. Scissors: LOSE
Paper vs. Scissors: LOSE
Paper vs. Scissors: LOSE
*///:~
