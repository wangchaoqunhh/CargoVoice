package com.cargo.basecommon.utils;

import android.text.TextUtils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * com.air.airlogistics.utils
 * (c)2018 AIR Times Inc. All rights reserved.
 *
 * @author WangJQ
 * @version 1.0
 * @date 2018/9/3 13:49
 */
public class TimeUtils {

    /**
     * 将时间戳转换为时间
     */
    public static String stampToDateTime(long s) {
        if (s == 0) {
            return "";
        }
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(s);
        res = simpleDateFormat.format(date);
        return res;
    }

    public static String stampToDate(String stamp) {
        return stampToDate(stamp, "yyyy-MM-dd");
    }

    public static String stampToDate(String stamp, String style) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(style);
        long lt = new Long(stamp);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 将String转换为时间
     */
    public static String stringToDateTime(String s) {
        if (TextUtils.isEmpty(s)) {
            return null;
        }
        try {
            String res;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = simpleDateFormat.parse(s);
            SimpleDateFormat simpleDateFormatTo = new SimpleDateFormat("yyyy-MM-dd");
            res = simpleDateFormatTo.format(date);
            return res;
        } catch (ParseException e) {
            return "";
        }
    }

    /**
     * 根据时间获取时间
     *
     * @param string
     * @return
     */
    public static long getMillsByDate(String string) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = simpleDateFormat.parse(string);
            return date.getTime();
        } catch (ParseException e) {
            return 0;
        }
    }

    /**
     * 格式化
     *
     * @param num
     * @return
     */
    public static String formatSingleDigit(int num) {
        DecimalFormat df = new DecimalFormat("00");
        String str2 = df.format(num);
        return str2;
    }

    /**
     * 格式化
     *
     * @param num
     * @return
     */
    public static String formatToSingleDigit(String num) {
        DecimalFormat df = new DecimalFormat("0");
        String str2 = df.format(Integer.parseInt(num));
        return str2;
    }

    /**
     * 格式化
     *
     * @param num
     * @return
     */
    public static String double2SingleDigit(String num) {
        try {
            DecimalFormat df = new DecimalFormat("0");
            String str2 = df.format(Long.parseLong(num));
            return str2;
        } catch (NumberFormatException e) {
            return num;
        }
    }

    /**
     * 将String转换为开始时间
     */
    public static String stringToBeginTime(String s) {
        if (TextUtils.isEmpty(s)) {
            return "";
        }
        return s + " 00:00:00";
    }

    /**
     * 将String转换为结束时间
     */
    public static String stringToEndTime(String s) {
        if (TextUtils.isEmpty(s)) {
            return "";
        }
        return s + " 23:59:59";
    }

    /**
     * String判空
     */
    public static String nullToString(String s) {
        if (TextUtils.isEmpty(s)) {
            return "";
        }
        return s;
    }

    /**
     * 获取今日日期yyyy-MM-dd
     */
    public static String getTodayDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        return simpleDateFormat.format(calendar.getTime());
    }

    /**
     * 获取几日后日期yyyy-MM-dd
     */
    public static String getAfterDayDate(int day) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + day);
        return simpleDateFormat.format(calendar.getTime());
    }

    /**
     * 获取几日前日期yyyy-MM-dd
     */
    public static String getBeforeDayDate(int day) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - day);
        return simpleDateFormat.format(calendar.getTime());
    }

    public static boolean matchArouter(String str) {
        String format = "/.+/.+";
        Pattern p = Pattern.compile(format);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * string转long
     *
     * @param str ...
     * @return long
     */
    public static long stringToLong(String str) {
        long value = 0;
        try {
            value = Long.parseLong(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }


    /**
     * 判断是否在当前时间之后
     *
     * @param time
     * @return
     */
    public static boolean isAfterCurrentTime(long time) {
        if (time < System.currentTimeMillis()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 比较两个日期大小
     *
     * @param beginDate  开始日期
     * @param endDate    结束日期
     * @param formatType 转换的格式yyyy-MM-dd
     * @return true：开始时间大于等于结束时间 false：开始时间小于结束时间
     */
    public static boolean compareDate(String beginDate, String endDate, String formatType) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatType);
        try {
            Date dt1 = sdf.parse(beginDate);
            Date dt2 = sdf.parse(endDate);
            if (dt1.getTime() >= dt2.getTime()) {
                return true;
            } else if (dt1.getTime() < dt2.getTime()) {
                return false;
            } else {
                return false;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    /**
     * 根据时间获取时间
     *
     * @param string
     * @return
     */
    public static long getMillsByFormat(String string) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
            Date date = simpleDateFormat.parse(string);
            return date.getTime();
        } catch (ParseException e) {
            return 0;
        }
    }

    /**
     * 格式化时间
     *
     * @param strTime    要转换的string类型的时间
     * @param formatType 转换的格式yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public static String stringToString(String strTime, String formatType) {
        if (TextUtils.isEmpty(strTime)) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(formatType);
        String datetiem = "";
        try {
            Date date = sdf.parse(strTime);
            datetiem = sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return datetiem;
    }

    /**
     * list字符串集合转为逗号拼接String字符串
     *
     * @param list      字符串集合
     * @param separator 拼接符
     * @return 字符串
     */
    public static String listToString1(List list, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    /**
     * string转double
     *
     * @param s String
     * @return double
     */
    public static double string2Double(String s) {
        try {
            return Double.parseDouble(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * 根据年月日 得到周几
     * @param time  yyyy-MM-dd 格式
     */
    public static String getWeekByTMD(String time) throws ParseException {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = new String[]{"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date date = f.parse(time);
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;// 指示一个周中的某天。
        if (w < 0) w = 0;
        String week = weekDays[w];
        return week;
    }


    public static final String DATE_TYPE_1 = "yyyy-MM-dd HH:mm";

}

