package com.snackpub.core.typeinfo;

import java.util.Arrays;
import java.util.List;

/**
 * @author snackpub
 * @date 2021/5/22
 */
public class SnowMakeRobot implements Robot {

    private String name;

    public SnowMakeRobot(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String model() {
        return "Snow Series 12";
    }

    @Override
    public List<Operation> operations() {
        return Arrays.asList(
                new Operation() {
                    @Override
                    public String description() {
                        return "寻找积雪";
                    }

                    @Override
                    public void command() {
                        System.out.println(name + " 正在寻找积雪");
                    }
                },
                new Operation() {
                    @Override
                    public String description() {
                        return "建立雪人模型";
                    }

                    @Override
                    public void command() {
                        System.out.println(name + " 建立雪人模型");
                    }
                },
                new Operation() {
                    @Override
                    public String description() {
                        return "成功造人";
                    }

                    @Override
                    public void command() {
                        System.out.println(name + " 成功造人");
                    }
                }
        );
    }

    public static void main(String[] args) {
        Robot.Test.test(new SnowMakeRobot("Snackpub"));
    }
}
