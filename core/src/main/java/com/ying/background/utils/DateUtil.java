package com.ying.background.utils;


import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yingsy on 2018/5/22.
 */
public class DateUtil {

    /** 日期格式*/
    public final static String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /** 短日期格式*/
    public final static String SHORT_WEB_FORMAT = "yyyy-MM-dd";

    public final static String SHORT_WEB_YEAR_FORMAT = "yyyy";

    /**
     * 获取指定格式的日期
     * @param dateStr  日期字符串
     * @param formatStr  格式
     * @return  日期
     */
    public static Date getDayByFormat(String dateStr, String formatStr) {
        try {
            if (!StringUtils.isEmpty(dateStr)) {
                SimpleDateFormat format = new SimpleDateFormat(formatStr);
                return format.parse(dateStr);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException();
    }

    /**
     * 将日期转换为指定格式的字符串
     * @param date  日期
     * @param formatStr  格式
     * @return  日期字符串
     */
    public static String getStrByDate(Date date, String formatStr) {
        try {
            if (date != null && formatStr!=null&&!formatStr.isEmpty()) {
                SimpleDateFormat format = new SimpleDateFormat(formatStr);
                return format.format(date);
            }
        } catch (Exception e) {
            return "";
        }
        return "";
    }
}
