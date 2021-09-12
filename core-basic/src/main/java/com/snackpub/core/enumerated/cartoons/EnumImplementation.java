//: enumerated/cartoons/EnumImplementation.java
// An enum can implement an interface
package com.snackpub.core.enumerated.cartoons;


import com.snackpub.core.util.Generator;

import java.util.Random;

/**
 * enum 实现，而非继承
 *
 * @author snackpub
 * @date  2021/9/12
 */
enum CartoonCharacter
    implements Generator<CartoonCharacter> {
  SLAPPY, SPANKY, PUNCHY, SILLY, BOUNCY, NUTTY, BOB;
  private static Random rand = new Random(47);
  public CartoonCharacter next() {
    return values()[rand.nextInt(values().length)];
  }

    /**
     * 尝试替代next();
     *
     * @param cartoonCharacter choose any instance.
     * @return random return CartoonCharacter instance.
     */
//  public static CartoonCharacter next2(CartoonCharacter cartoonCharacter) {
//        return values()[cartoonCharacter.rand.nextInt(values().length)];
//  }

  public static CartoonCharacter next3() {
        return values()[rand.nextInt(values().length)];
    }

}

public class EnumImplementation {
  public static <T> void printNext(Generator<T> rg) {
    System.out.print(rg.next() + ", ");
  }
    public static <T> void printNext2() {
        System.out.print(CartoonCharacter.next3() + ", ");
    }
  public static void main(String[] args) {
    // Choose any instance:
    CartoonCharacter cc = CartoonCharacter.BOB;
      for (int i = 0; i < 10; i++) {
//          printNext(cc);
          printNext2();
      }

  }
} /* Output:
BOB, PUNCHY, BOB, SPANKY, NUTTY, PUNCHY, SLAPPY, NUTTY, NUTTY, SLAPPY,
*///:~
