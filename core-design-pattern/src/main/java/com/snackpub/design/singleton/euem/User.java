package com.snackpub.design.singleton.euem;

/**
 * 枚举-> 单例模式
 *
 * @author snackpub
 * @date 2020/7/30
 */
public class User {


    private User() {

    }

    /**
     * 定义一个静态枚举类
     */
    static enum SingletonEnum {
        // 创建一个枚举对象，改对象天生为单例
        INSTANCE;

        private User user;

        //私有化枚举的构造函数
        private SingletonEnum() {
            user = new User();
        }

        public User getInstance() {
            return user;
        }

    }

    /**
     * 对外暴露一个获取User对象的静态方法
     *
     * @return User
     */
    public static User getInstance() {
        return SingletonEnum.INSTANCE.getInstance();
    }

    public static void main(String[] args) {
        System.out.println(User.getInstance());
        System.out.println(User.getInstance());


        System.out.println(User.getInstance() == User.getInstance());

    }

}
