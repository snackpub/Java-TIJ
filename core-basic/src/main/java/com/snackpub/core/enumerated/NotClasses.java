package com.snackpub.core.enumerated;//: enumerated/NotClasses.java
// {Exec: javap -c LikeClasses}
// \src\main\java>javap  com.snackpub.core.enumerated.LikeClasses
import static com.snackpub.core.util.Print.*;
import static com.snackpub.core.util.Print.print;

enum LikeClasses {
  WINKEN { void behavior() { print("Behavior1"); } },
  BLINKEN { void behavior() { print("Behavior2"); } },
  NOD { void behavior() { print("Behavior3"); } };
  abstract void behavior();
}

public class NotClasses {
  // void f1(LikeClasses.WINKEN instance) {} // Nope
} /* Output:
Compiled from "NotClasses.java"
abstract class LikeClasses extends java.lang.Enum{
public static final LikeClasses WINKEN;

public static final LikeClasses BLINKEN;

public static final LikeClasses NOD;
...
*///:~
