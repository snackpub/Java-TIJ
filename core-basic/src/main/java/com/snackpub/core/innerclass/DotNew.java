package com.snackpub.core.innerclass;

/**
 * 使用.new语法直接创建内部类。
 *
 * @author snackPub
 * @date 2021/3/19
 */
public class DotNew {
    public class Inner {
    }

    public static void main(String[] args) {
        DotNew dn = new DotNew();
        DotNew.Inner inner = dn.new Inner();
    }

}
