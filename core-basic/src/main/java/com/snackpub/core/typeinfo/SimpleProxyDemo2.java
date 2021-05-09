package com.snackpub.core.typeinfo;//: typeinfo/SimpleProxyDemo.java

import static com.snackpub.core.util.Print.print;

interface Interface2 {
    void doSomething();

    void somethingElse(String arg);
}

class RealObject2 implements Interface2 {

    @Override
    public void doSomething() {
        System.out.println("doSomething");
    }

    @Override
    public void somethingElse(String arg) {
        System.out.println("somethingElse:" + arg);
    }
}

class SimpleProxy2 implements Interface2 {

  private Interface2 interface2;

  public SimpleProxy2(Interface2 interface2) {
    this.interface2 = interface2;
  }

  @Override
  public void doSomething() {
    System.out.println("SimpleProxy2.doSomething");
    interface2.doSomething();
  }

  @Override
  public void somethingElse(String arg) {
    System.out.println("SimpleProxy2.somethingElse");
    interface2.somethingElse(arg);
  }
}


class SimpleProxyDemo2 {
    public static void consumer(Interface2 iface) {
        iface.doSomething();
        iface.somethingElse("bonobo");
    }

    public static void main(String[] args) {
        consumer(new RealObject2());
        consumer(new SimpleProxy2(new RealObject2()));
    }
} /* Output:
doSomething
somethingElse bonobo
SimpleProxy doSomething
doSomething
SimpleProxy somethingElse bonobo
somethingElse bonobo
*///:~
