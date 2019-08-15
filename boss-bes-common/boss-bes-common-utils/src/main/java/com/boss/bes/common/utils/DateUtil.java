package com.boss.bes.common.utils;

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

    private static SimpleDateFormat dateTimeInstance() {
        SimpleDateFormat df = ThreadDateTime.get();
        if (df == null) {
            df = new SimpleDateFormat(Opslab.DATETIME_FORMAT);
            ThreadDateTime.set(df);
        }
        return df;
    }

    private static SimpleDateFormat dateInstance() {
        SimpleDateFormat df = ThreadDate.get();
        if (df == null) {
            df = new SimpleDateFormat(Opslab.DATE_FORMAT);
            ThreadDate.set(df);
        }
        return df;
    }

    private static SimpleDateFormat timeInstance() {
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
        return dateTimeInstance().format(new Date());
    }

    /**
     * 将指定的时间格式化成出返回
     *
     * @param date
     * @return
     */
    public static String dateTime(Date date) {
        return dateTimeInstance().format(date);
    }

    /**
     * 将指定的字符串解析为时间类型（日期）
     *
     * @param datestr
     * @return
     * @throws ParseException
     */
    public static Date dateTime(String datestr) throws ParseException {
        return dateTimeInstance().parse(datestr);
    }


    /**
     * 获取当前的日期
     *
     * @return
     */
    public static String currentDate() {
        return dateInstance().format(new Date());
    }

    /**
     * 将指定的时间格式化成出返回
     *
     * @param date
     * @return
     */
    public static String date(Date date) {
        return dateInstance().format(date);
    }

    /**
     * 将指定的字符串解析为时间类型（日期）
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date date(String dateStr) throws ParseException {
        return dateInstance().parse(dateStr);
    }


    /**
     * 获取当前的时间
     *
     * @return
     */
    public static String currentTime() {
        return timeInstance().format(new Date());
    }

    /**
     * 讲指定的时间格式化成出返回
     *
     * @param date
     * @return
     */
    public static String time(Date date) {
        return timeInstance().format(date);
    }

    /**
     * 将指定的字符串解析为时间类型(日期）
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date time(String dateStr) throws ParseException {
        return timeInstance().parse(dateStr);
    }


}
