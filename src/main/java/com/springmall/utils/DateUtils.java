package com.springmall.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fwj on 2019-11-21.
 */
public class DateUtils {


    /**
     * 返回当前时间的字符串形式
     * @return
     */
    public static String getNowTimeForString() {
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strTime = simpleDateFormat.format(now);
        return strTime;
    }
}
