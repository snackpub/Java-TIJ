package com.snackpub.core.innerclass;

/**
 * @author snackPub
 * @date 2021/3/16
 */
public class Parcel3 {

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

    public Destination to(String s) {
        return new Destination(s);
    }

    public Contents contents() {
        return new Contents();
    }




    public static void main(String[] args) {
        Parcel3 p = new Parcel3();
        // 必须使用外部类实例来创建内部类的实例:
        Parcel3.Contents contents = p.new Contents();
        Parcel3.Destination destination = p.new Destination("Tasmania");
    }

}
