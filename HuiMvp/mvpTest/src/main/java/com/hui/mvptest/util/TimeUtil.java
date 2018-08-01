package com.hui.mvptest.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by walke.Z on 2017/7/31.
 */

public class TimeUtil extends AppUtil {

    private static long lastClickTime = 0;

    /**
     * 是否满足间隔时间 默认为0.5s
     *
     * @param intervalTime
     * @return
     */
    public synchronized static boolean isMeetIntervalTime(int intervalTime) {
        long time = System.currentTimeMillis();
        if (intervalTime < 500)
            intervalTime = 500;
        if (time - lastClickTime < intervalTime) {
            return false;
        }
        lastClickTime = time;
        return true;
    }

    public final static String[] week = {"", "周日", "周一", "周二", "周三", "周四", "周五", "周六"};

    public final static String[] week2 = {"", "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    public static String YYMMDD = "yyyy-MM-dd";

    public static SimpleDateFormat SDF_YYYY_MM_DD_HH_MM = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static SimpleDateFormat SDF_YYYY_MM_DD_HH_MM_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String formatDate_YYYY_MM_DD(Date date) {
        String dates = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(YYMMDD);
            dates = sdf.format(date);
        } catch (Exception e) {
            date = null;
        }
        return dates;

    }
    public static String formatDate_YYYY_MM_DD_HH_MM(Date date) {
        String dates = "";
        try {
            dates = SDF_YYYY_MM_DD_HH_MM.format(date);
        } catch (Exception e) {
            date = null;
        }
        return dates;

    }

    public static String formatDate_YYYY_MM_DD_HH_MM_SS(Date date) {
        String dates = "";
        try {
            dates = SDF_YYYY_MM_DD_HH_MM_SS.format(date);
        } catch (Exception e) {
            date = null;
        }
        return dates;

    }


}
