package com.chanjet.openapi.demo.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: zsc
 * @create: 2020/11/11 4:18 下午
 **/
public class DateUtil {
    /**
     * 获取年月日格式的当前时间
     *
     * @return
     */
    public static String getDate() {
        DateFormat bf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return bf.format(date);
    }

}
