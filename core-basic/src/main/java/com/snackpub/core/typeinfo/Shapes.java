package com.snackpub.core.typeinfo;//: typeinfo/Shapes.java

import com.snackpub.core.util.Print;

import java.util.Arrays;
import java.util.List;

abstract class Shape {
    void draw() {
        System.out.println(this + ".draw()");
    }

    abstract public String toString();
}

class Circle extends Shape {
    public String toString() {
        return "Circle";
    }
}

class Square extends Shape {
    public String toString() {
        return "Square";
    }
}

class Triangle extends Shape {
    public String toString() {
        return "Triangle";
    }
}

/**
 * 菱形
 */
class Rhomboid extends Shape {
    public String toString() {
        return "Triangle";
    }
}

public class Shapes {
    public static void main(String[] args) {
        List<Shape> shapeList = Arrays.asList(
                new Circle(), new Square(), new Triangle()
        );
        for (Shape shape : shapeList)
            shape.draw();

        /*
         *练习3: (2)将Rhomboid (菱形)加入Shapes.java中。
         * 创建一个Rhomboid,将其向上转型为Shape,然后向下转型回Rhomboid,试着将其向下转型成Circle,看看会发生什么。
         */
        // cast up
        Shape shape = new Circle();
        Print.print(shape);
      // cast dw
//      Rhomboid rh = (Rhomboid) shape;
//      Print.print(rh);

      rotate(shape);
      /*if (shape instanceof Circle) {
        Circle circle = (Circle) shape; // ClassCastException
        Print.print(circle);
      }*/


    }

  /**
   * 练习5: (3)实现Shapes.java中的rotate(Shape)方法,让它能判断它所旋转的是不是Circle(如果是,就不执行)
   * @param shape
   */
  static void rotate(Shape shape) {
    if (shape instanceof Circle) {

    } else {
      Print.print(shape);
    }
  }

} /* Output:
Circle.draw()
Square.draw()
Triangle.draw()
*///:~
