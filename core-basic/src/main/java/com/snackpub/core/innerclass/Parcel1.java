package com.snackpub.core.innerclass;

/**
 * @author snackPub
 * @date 2021/3/16
 */
public class Parcel1 {

    class Contents {
        private int i = 11;

        public int value() {
            return i;
        }


    }

    class Destination {
        private String label;

        Destination(String whereTo) {
            label = whereTo;
        }

        String readLabel() {
            return label;
        }

    }

    // 在Parcel1中，使用内部类就像使用其他类一样
    public void ship(String dest) {
        Contents c = new Contents();
        Destination d = new Destination(dest);
        System.out.println(d.readLabel());
    }


    public static void main(String[] args) {
        Parcel1 p = new Parcel1();
        p.ship("Tasmania");
    }

}
