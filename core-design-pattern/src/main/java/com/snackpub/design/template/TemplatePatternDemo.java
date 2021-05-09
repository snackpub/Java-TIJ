package com.snackpub.design.template;

/**
 * @author snackpub
 * @date 2020/9/19
 */
public class TemplatePatternDemo {

    public static void main(String[] args) {
        Game football = new Football();
        football.play();

        System.err.println("-------------");

        Game cricket = new Cricket();
        cricket.play();
    }
}
