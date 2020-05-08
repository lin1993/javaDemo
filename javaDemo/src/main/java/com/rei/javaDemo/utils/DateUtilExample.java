package com.rei.javaDemo.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

public class DateUtilExample {

    public static void main(String[] args) {
        Date n = DateUtil.parse("");
        Date utc = DateUtil.parse("2020-04-15T13:15:16");
        Date date1 = DateUtil.parse("20200404");
        DateTime now = DateUtil.date();
        String cc = now.toString("yyyy-MM-dd");
        Date datetime = DateUtil.parse("20200404131502");
        Date datetime2 = DateUtil.parse("2020-04-04 13:15:02");
        int a =0;
    }


}
