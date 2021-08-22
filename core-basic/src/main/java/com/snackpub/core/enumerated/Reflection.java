package com.snackpub.core.enumerated;//: enumerated/Reflection.java
// Analyzing enums using reflection.
import com.snackpub.core.util.OSExecute;

import java.lang.reflect.*;
import java.util.*;

import static com.snackpub.core.util.Print.print;
import static com.snackpub.core.util.Print.printnb;

enum Explore { HERE, THERE }

/**
 * Enum类并没有values()方法，这是由编译器添加的static方法, 根据控制台反编译的结果，我们还看到添加valueOf(java.lang.String);
 * 看到这里你可能很迷惑，Enum类不是已经有了valuesOf吗，但是他需要两个参数valueOf(Class<T> enumType, String name)，编译器
 * 添加的只需要一个参数。
 *
 * 可以看到Explore被编译器标记为了final，所以无法被继承。
 */
public class Reflection {
  public static Set<String> analyze(Class<?> enumClass) {
    print("----- Analyzing " + enumClass + " -----");
    print("Interfaces:");
    for(Type t : enumClass.getGenericInterfaces())
      print(t);
    print("Base: " + enumClass.getSuperclass());
    print("Methods: ");
    Set<String> methods = new TreeSet<>();
    for(Method m : enumClass.getMethods())
      methods.add(m.getName());
    print(methods);
    return methods;
  }
  public static void main(String[] args) {
    Set<String> exploreMethods = analyze(Explore.class);
    Set<String> enumMethods = analyze(Enum.class);
    print("Explore.containsAll(Enum)? " +
      exploreMethods.containsAll(enumMethods));
    printnb("Explore.removeAll(Enum): ");
    exploreMethods.removeAll(enumMethods);
    print(exploreMethods);
    // Decompile the code for the enum:
    String workDir = "F:\\Work\\JavaBasicStudy\\core-basic\\src\\main\\java\\com\\snackpub\\core\\enumerated";
    // 需要先编译成.class文件
    OSExecute.command("javap Explore", workDir);
  }
} /* Output:
----- Analyzing class Explore -----
Interfaces:
Base: class java.lang.Enum
Methods:
[compareTo, equals, getClass, getDeclaringClass, hashCode, name, notify, notifyAll, ordinal, toString, valueOf, values, wait]
----- Analyzing class java.lang.Enum -----
Interfaces:
java.lang.Comparable<E>
interface java.io.Serializable
Base: class java.lang.Object
Methods:
[compareTo, equals, getClass, getDeclaringClass, hashCode, name, notify, notifyAll, ordinal, toString, valueOf, wait]
Explore.containsAll(Enum)? true
Explore.removeAll(Enum): [values]
Compiled from "Reflection.java"
final class Explore extends java.lang.Enum{
    public static final Explore HERE;
    public static final Explore THERE;
    public static final Explore[] values();
    public static Explore valueOf(java.lang.String);
    static {};
}
*///:~
