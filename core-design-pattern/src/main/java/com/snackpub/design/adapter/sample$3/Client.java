package com.snackpub.design.adapter.sample$3;

/**
 * 测试适配性
 *
 * @author snackpub
 * @date 2020/4/27
 */
public class Client {


    public static void main(String[] args) {
        /*
         * 针对于每个假设的宽度，我使用 SquarePegAdaptor 包装 SquarePeg 的创建，
         * 启用 hole 的 pegFits() 方法来返回一个关于桩合适性的智能评估。
         */
        RoundHole hole = new RoundHole(4.0);

        Circularity peg;

        for (int i = 3; i <= 10; i++) {
            peg = new SquarePegAdaptor(new SquarePeg(i));
            if (i < 6) {
                boolean fist = hole.getFits(peg);
                System.out.println(fist + " " + i);
            } else {
                boolean fist = hole.getFits(peg);
                System.out.println(fist + " " + i);
            }
        }
    }
}
