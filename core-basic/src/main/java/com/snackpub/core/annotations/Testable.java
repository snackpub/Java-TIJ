//: annotations/Testable.java
package com.snackpub.core.annotations;
import com.snackpub.core.atunit.*;

public class Testable {
  public void execute() {
    System.out.println("Executing..");
  }
  @Test void testExecute() { execute(); }
} ///:~
