package com.rei.javaDemo.example;

import java.time.Duration;
import java.time.Instant;

/**
 * 日期API的使用
 */
public class DateExample {
    /**
     * 计算日期时间差值
     */
    public static void calcDate(){
        Instant start = Instant.now();
        Instant end = Instant.now();
        Duration duration = Duration.between(start,end);
        // 计算差值可以有秒天小时等
        long a = duration.toMillis();
        a = duration.toDays();
    }
}
