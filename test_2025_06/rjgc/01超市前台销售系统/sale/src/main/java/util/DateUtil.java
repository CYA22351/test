package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");

    /**
     * 格式化日期
     */
    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        return DATE_FORMAT.format(date);
    }

    /**
     * 格式化日期时间
     */
    public static String formatDateTime(Date date) {
        if (date == null) {
            return "";
        }
        return DATETIME_FORMAT.format(date);
    }

    /**
     * 格式化时间
     */
    public static String formatTime(Date date) {
        if (date == null) {
            return "";
        }
        return TIME_FORMAT.format(date);
    }

    /**
     * 获取当前日期时间
     */
    public static Date getCurrentDateTime() {
        return new Date();
    }

    /**
     * 获取当前日期字符串
     */
    public static String getCurrentDateString() {
        return formatDate(new Date());
    }

    /**
     * 获取当前日期时间字符串
     */
    public static String getCurrentDateTimeString() {
        return formatDateTime(new Date());
    }
}