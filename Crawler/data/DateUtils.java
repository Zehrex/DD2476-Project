2
https://raw.githubusercontent.com/visayang/wechatdev/master/src/main/java/com/chad/wechatdev/commons/utils/DateUtils.java
package com.chad.wechatdev.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class DateUtils {

    /**
     * 这个月第一天    example:2019-11-01 00:00:00
     * @return
     */
    public static String getMonthFirstDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar
                .getActualMinimum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        return sdf.format(calendar.getTime());
    }

    /**
     * 这个月最后一天  example：2019-11-30 23:59:59
     * @return
     */
    public static  String getMonthLastDay(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar
                .getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR, 23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.HOUR_OF_DAY,23);
        return sdf.format(calendar.getTime());
    }

}
