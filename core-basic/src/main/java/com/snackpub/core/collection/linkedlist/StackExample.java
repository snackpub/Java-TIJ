package com.snackpub.core.collection.linkedlist;

import java.util.Stack;
import java.util.stream.IntStream;

/**
 * 栈在编程语言中经常用来对表达式求值。
 * 请使用net.mindview.util.Stack对下面表达式求值，其中“+”表示“将后面的字母压进栈”，
 * 而“-”表示“弹出栈顶字母并打印它”：“+U+n+c---+e+r+t---+a-+i-+n+t+y---+ -+r+u--+l+e+s---”。
 *
 * @author snackpub
 * @date 2021/3/27
 */
public class StackExample {

    public static void main(String[] args) {
        String s = "+U+n+c---+e+r+t---+a-+i-+n+t+y---+ -+r+u--+l+e+s---";
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            System.out.println(i);
            if(s.charAt(i) == '+') stack.push(s.charAt(++i));
            else System.out.print(stack.pop());
        }

        System.out.println();

       /* String s2 = "+U+n+c---+e+r+t---+a-+i-+n+t+y---+ -+r+u--+l+e+s---";
        Stack<Character> stack2 = new Stack<>();
        IntStream.range(0, s2.length()).forEach(index -> {  // index 不会变动，每次都是1的步增量
            System.out.println(index);
            if (s2.charAt(index) == '+'){
                stack2.push(s2.charAt(++index));
                index ++;
            }
            else System.out.print(stack2.pop());

        });*/
    }
}
