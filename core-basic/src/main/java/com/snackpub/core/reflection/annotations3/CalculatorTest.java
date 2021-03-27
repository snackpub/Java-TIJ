package com.snackpub.core.reflection.annotations3;

//import static net.mindview.util.Print.*;

import java.util.Stack;

/**
 * @author snackpub
 * @date 2020/9/24
 */
public class CalculatorTest {


    @Test
    public void testAdd() {
        Stack stack = new Stack();
        System.out.println("test add method");
    }

    @Test(ignore = true)
    public void testSubtract() {
        System.out.println("test subtract method");
    }

}
