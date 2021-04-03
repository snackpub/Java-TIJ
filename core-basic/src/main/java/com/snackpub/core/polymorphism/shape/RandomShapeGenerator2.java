package com.snackpub.core.polymorphism.shape;


import java.util.Iterator;
import java.util.Random;

/**
 * 练习31：修改polymorphism/shape/RandomShapeGenerator.java，
 * 使其成为一个Iterable。
 * 你需要添加一个接受元素数量为参数的构造器，这个数量是指在停止之前，你想用迭代器生成的元素的数量。
 * 验证这个程序可以工作。
 */
public class RandomShapeGenerator2 implements Iterable<Shape> {
    private int size;
    private Random rand = new Random(47);

    public RandomShapeGenerator2(int size) {
        this.size = size;
    }

    @Override
    public Iterator<Shape> iterator() {
        return new Iterator<Shape>() {
            @Override
            public boolean hasNext() {
                return size > 0;
            }

            @Override
            public Shape next() {
                int index = size;
                size--;
                switch (rand.nextInt(index)) {
                    default:
                    case 0:
                        return new Circle();
                    case 1:
                        return new Square();
                    case 2:
                        return new Triangle();
                }
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static void main(String[] args) {
        RandomShapeGenerator2 randomShapeGenerator2 = new RandomShapeGenerator2(3);
        for (Shape next : randomShapeGenerator2)
            System.out.println(next);

    }
} ///:~