package com.snackpub.core.lambda.datetimeapi;

import java.time.*;

/**
 * Java 8  java.time
 *
 * @author snackpub
 * @version 2021/7/10
 */
public class LocalDateTimeExample {


    public static void main(String[] args) {

        //=========== 本地化日期时间 API
        // 获取当前的日期时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println("LocalDateTime.now(): " + now);

        LocalDate localDate = now.toLocalDate();
        System.out.println("LocalDateTime.toLocalDate(): " + localDate);
        LocalTime localTime = now.toLocalTime();
        System.out.println("LocalDateTime.toLocalTime(): " + localTime);

        Month month = now.getMonth();
        int dayOfMonth = now.getDayOfMonth();
        int second = now.getSecond();
        System.out.println("月: " + month +", 日: " + dayOfMonth +", 秒: " + second);

        LocalDateTime localDateTime = now.withDayOfMonth(10).withYear(2021);
        System.out.println("now.withDayOfMonth(10).withYear(2021): " + localDateTime);

        // 12 december 2014
        LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12);
        System.out.println("date3: " + date3);

        // 22 小时 15 分钟
        LocalTime date4 = LocalTime.of(22, 15);
        System.out.println("date4: " + date4);

        // 解析字符串
        LocalTime date5 = LocalTime.parse("20:15:30");
        System.out.println("date5: " + date5);



        // =========== 使用时区的日期时间API
        // 获取当前时间日期
        ZonedDateTime date1 = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
        System.out.println("date1: " + date1);

        ZoneId id = ZoneId.of("Europe/Paris");
        System.out.println("ZoneId: " + id);

        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("当期时区: " + currentZone);




    }

}
