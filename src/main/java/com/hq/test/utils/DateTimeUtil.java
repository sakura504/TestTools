package com.hq.test.utils;

import java.math.BigDecimal;

/**
 * @author lichaojie
 * @date 2022/3/18 10:03
 * @ClassName DateTimeUtil
 **/
public class DateTimeUtil {

    private static String formatDateTime(Integer mss) {
        String DateTimes = null;
        long hours = (mss / (60 * 60));
        long minutes = (mss % ( 60 * 60)) /60;
        long seconds = mss % 60;
        if(hours>0){
            DateTimes=hours + ":" + minutes + ":"
                    + seconds;
        }else if(minutes>0){
            DateTimes=minutes + ":"
                    + seconds;
        }else{
            if (seconds < 10){
                DateTimes="00:0" + seconds;
            }else {
                DateTimes="00:" + seconds;
            }
        }

        return DateTimes;
    }

    public static void main(String[] args) {
        BigDecimal a = new BigDecimal(1);
        BigDecimal b = new BigDecimal(1);
        System.out.println(a.compareTo(b) >= 0);

        System.out.println(formatDateTime(2591881));
    }
}
