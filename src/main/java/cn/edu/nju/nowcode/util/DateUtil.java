package cn.edu.nju.nowcode.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by cong on 2018-05-20.
 */
public class DateUtil {

    private static final String DATE_FOMART="yyyy-MM-dd";

    private static final int ONE_DAY=1000*60*60*24;

    private static final long ONE_MONTH=1000l*60*60*24*30;

    public static Date add(Date date,long millis){
        return new Date(date.getTime()+millis);
    }

    public static Date addMonth(Date date,int num){
        return new Date(date.getTime()+num*ONE_MONTH);
    }

    public static Date addDay(Date date,int num){
        return new Date(date.getTime()+num*ONE_DAY);
    }

    /**
     * 获取timestamp的日期
     * @param timestamp
     * @return
     */
    public static String getDate(long timestamp){
        return getFormatString(timestamp,DATE_FOMART);
    }

    public static boolean isBefore(Date before,Date after){
        return before.getTime()<after.getTime();
    }


    private static String getFormatString(long timestamp,String format){
        Date date=new Date(timestamp);
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

}
