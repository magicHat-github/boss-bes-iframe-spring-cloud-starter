package com.boss.bes.common.logging.util;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具
 * @author longquanxiao
 * @date 2019/8/12
 */
@Component
public class ApiLogDateUtil {
    private static Date date = new Date();
    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>(){
       @Override
       protected DateFormat initialValue() {
           return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       }
    };
    /**
     * 获得当前时间的字符串
     * @return 返回时间字符串
     */
    public String getStringTime(){
        return threadLocal.get().format(date);
    }
    /**
     * 获得时间搓
     * @return 返回当前的时间搓
     */
    public long getTime(){
        return date.getTime();
    }
}
