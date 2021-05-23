package com.snackpub.core.generics;

import java.util.*;

interface Processor<T,E extends Exception> {
  // process 的结果存储在resultCollector中
  void process(List<T> resultCollector) throws E;
}

class ProcessRunner<T,E extends Exception>
extends ArrayList<Processor<T,E>> {
  // 执行所持有的每个Process对象，并返回resultCollector
  List<T> processAll() throws E {
    List<T> resultCollector = new ArrayList<T>();
    for(Processor<T,E> processor : this)
      processor.process(resultCollector);
    return resultCollector;
  }
}

class Failure1 extends Exception {}

class Processor1 implements Processor<String,Failure1> {
  static int count = 3;
  public void
  process(List<String> resultCollector) throws Failure1 {
    if(count-- > 1)
      resultCollector.add("Hep!");
    else
      resultCollector.add("Ho!");
    if(count < 0)
       throw new Failure1();
  }
}

class Failure2 extends Exception {}

class Processor2 implements Processor<Integer,Failure2> {
  static int count = 2;
  public void
  process(List<Integer> resultCollector) throws Failure2 {
    if(count-- == 0)
      resultCollector.add(47);
    else {
      resultCollector.add(11);
    }
    if(count < 0)
       throw new Failure2();
  }
}

class Failure3 extends RuntimeException {}

class Processor3 implements Processor<String, Failure3> {
    static int count = 2;

  @Override
  public void process(List<String> resultCollector) throws Failure3 {
    if(count-- == 0)
      resultCollector.add("47");
    else {
      resultCollector.add("11");
  }
    if(count < 0)
      throw new Failure3();
  }
}

public class ThrowGenericException {
  public static void main(String[] args) {
    ProcessRunner<String,Failure1> runner =
            new ProcessRunner<>();
    for(int i = 0; i < 3; i++)
      runner.add(new Processor1());
    try {
      System.out.println(runner.processAll());
    } catch(Failure1 e) {
      System.out.println(e);
    }

    ProcessRunner<Integer,Failure2> runner2 =
            new ProcessRunner<>();
    for(int i = 0; i < 3; i++)
      runner2.add(new Processor2());
    try {
      System.out.println(runner2.processAll());
    } catch(Failure2 e) {
      System.out.println(e);
    }

    ProcessRunner<String, Failure3> runner3 = new ProcessRunner<>();
    for(int i=0; i<3;i++)
      runner3.add(new Processor3());
    try {
      System.out.println(runner3.processAll());
    } catch (Failure3 failure3) {
      System.out.println(failure3);
    }
  }
} ///:~
