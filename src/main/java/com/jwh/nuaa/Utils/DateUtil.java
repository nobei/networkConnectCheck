package com.jwh.nuaa.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ：jwh
 * @description：TODO
 * @date ：2022/4/21 20:25
 */
public class DateUtil {
    private static final long oneDaySecond = 24*60*60*1000;

    public static String getTime(){
        Date date = new Date();
        String nowtime = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss").format(date);
        return nowtime;
    }

    public static long getTimeStamp(){
        Date date = new Date();
        long timeStamp = date.getTime();
        return timeStamp;
    }

    public static long getTomrrowTime(Date date){
        long nowTimestamp = date.getTime();
        return nowTimestamp+oneDaySecond;

    }
}
