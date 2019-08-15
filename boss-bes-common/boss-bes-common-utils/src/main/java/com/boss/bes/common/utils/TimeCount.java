package com.boss.bes.common.utils;

/**
 * @author Song
 * @date 2019/8/13 17:38
 * 计时功能
 */
public final class TimeCount {
    /**
     * 定义开始时间和结束时间以及二者之差
     */
    private static long startTime;
    private static long endTime;
    private static long time;

    /**
     * 开始计时的方法
     * @return  计时开始的瞬时时间
     */
    public static long start(){
        return startTime=System.nanoTime();
    }

    /**
     * 结束计时的方法
     * @return 计时结束的瞬时时间
     */
    public static long end(){
        return endTime=System.nanoTime();
    }

    /**
     * 计算中间的运行时间，单位s
     * @return 运行时间
     */
    public static long getSecondTime(){
        return (endTime-startTime)/1000000000;
    }

    /**
     * 计算中间的运行时间，单位ms
     */
    public static void getMillisTime(){
        System.out.println("运行时间为"+(endTime-startTime)/1000000+"ms");
    }

    /**
     * 计算中间的运行时间，单位ns
     */
    public static void getNanoTime(){
        System.out.println("运行时间为"+(endTime-startTime)+"ns");
    }

    /**
     * 获取计时开始的瞬时时间
     * @return  开始时间
     */
    public static long getStartTime(){return startTime;}

    /**
     * 获取计时结束的瞬时时间
     * @return  结束时间
     */
    public static long getEndTime(){return endTime;}


}
