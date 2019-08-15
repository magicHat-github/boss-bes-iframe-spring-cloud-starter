package com.bes.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Song
 * @date 2019/8/12
 * 对各类型时间格式的常见操作
 */
public final class DateUtil {

    /**
     * 注意SimpleDateFormat不是线程安全的
     */
    private static ThreadLocal<SimpleDateFormat> ThreadDateTime = new ThreadLocal<SimpleDateFormat>();
    private static ThreadLocal<SimpleDateFormat> ThreadDate = new ThreadLocal<SimpleDateFormat>();
    private static ThreadLocal<SimpleDateFormat> ThreadTime = new ThreadLocal<SimpleDateFormat>();

    private static SimpleDateFormat DateTimeInstance() {
        SimpleDateFormat df = ThreadDateTime.get();
        if (df == null) {
            df = new SimpleDateFormat(Opslab.DATETIME_FORMAT);
            ThreadDateTime.set(df);
        }
        return df;
    }

    private static SimpleDateFormat DateInstance() {
        SimpleDateFormat df = ThreadDate.get();
        if (df == null) {
            df = new SimpleDateFormat(Opslab.DATE_FORMAT);
            ThreadDate.set(df);
        }
        return df;
    }

    private static SimpleDateFormat TimeInstance() {
        SimpleDateFormat df = ThreadTime.get();
        if (df == null) {
            df = new SimpleDateFormat(Opslab.TIME_FORMAT);
            ThreadTime.set(df);
        }
        return df;
    }

    /**
     * 获取当前日期时间
     *
     * @return 返回当前时间的字符串值
     */
    public static String currentDateTime() {
        return DateTimeInstance().format(new Date());
    }

    /**
     * 将指定的时间格式化成出返回
     *
     * @param date
     * @return
     */
    public static String dateTime(Date date) {
        return DateTimeInstance().format(date);
    }

    /**
     * 将指定的字符串解析为时间类型（日期）
     *
     * @param datestr
     * @return
     * @throws ParseException
     */
    public static Date dateTime(String datestr) throws ParseException {
        return DateTimeInstance().parse(datestr);
    }


    /**
     * 获取当前的日期
     *
     * @return
     */
    public static String currentDate() {
        return DateInstance().format(new Date());
    }

    /**
     * 将指定的时间格式化成出返回
     *
     * @param date
     * @return
     */
    public static String date(Date date) {
        return DateInstance().format(date);
    }

    /**
     * 将指定的字符串解析为时间类型（日期）
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date date(String dateStr) throws ParseException {
        return DateInstance().parse(dateStr);
    }


    /**
     * 获取当前的时间
     *
     * @return
     */
    public static String currentTime() {
        return TimeInstance().format(new Date());
    }

    /**
     * 讲指定的时间格式化成出返回
     *
     * @param date
     * @return
     */
    public static String time(Date date) {
        return TimeInstance().format(date);
    }

    /**
     * 将指定的字符串解析为时间类型(日期）
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date time(String dateStr) throws ParseException {
        return TimeInstance().parse(dateStr);
    }


}
