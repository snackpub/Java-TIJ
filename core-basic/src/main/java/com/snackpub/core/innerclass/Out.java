package com.snackpub.core.innerclass;

/**
 * @author snackPub
 * @date 2021/3/17
 */
public class Out {

    class Inner {

        Inner(String s){
            System.out.println(s);
        }
    }


    public Inner p(String s) {
        return new Inner(s);
    }

    public static void main(String[] args) {
        Out o = new Out();
        Out.Inner hello = o.p("hello");
    }

}
