package com.snackpub.core.collection.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * H练习27: (2)写一个称为Command的类,它包含一个String域和一个显示该String的operation0方法。
 * 写第二个类,它具有一个使用Command对象来填充一个Queue并返回这个对象的方法。
 * 将填充后的Queue传递给第三个类的一个方法,该方法消耗掉Queue中的对象,
 * 并调用它们的operation()方法。
 *
 * @author snackpub
 * @date 2021/3/28
 */
public class Command {
    private String s;

    public Command(String s) {
        this.s = s;
    }

    public void operation() {
        System.out.println(s);
    }

    static class Second {
        Queue<Command> makeQ() {
            Queue<Command> queue2 = new LinkedList<>();
            for (int i = 0; i < 5; i++)
                queue2.offer(new Command(i + " "));
            return queue2;
        }
    }

    public static class Test {
        public static void main(String[] args) {
            Second second = new Second();
            temp(second.makeQ());
        }

        private static void temp(Queue<Command> qc) {
            while (qc.peek() != null)
                qc.poll().operation();
        }
    }

    public static void main(String[] args) {

    }

}
