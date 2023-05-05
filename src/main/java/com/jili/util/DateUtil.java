package com.jili.util;

import com.jili.entity.enums.DateStyleEnum;
import com.jili.entity.enums.WeekEnum;

import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {
    private static final String DEFAULT_DATE_STYLE = "yyyy-MM-dd HH:mm:ss";

    public DateUtil() {
    }

    public static String DateToTimestamp(String date) {
        Date d = StringToDate(date, DateStyleEnum.YYYY_MM_DD_HH_MM_SS);
        return String.valueOf(d.getTime());
    }

    public static String DateToTimestamp(Date date) {
        return String.valueOf(date.getTime());
    }

    public static Date TimestampToDate(String date) {
        Long time = Long.parseLong(date);
        String str = getDateFormat(DateStyleEnum.YYYY_MM_DD_HH_MM_SS.getValue()).format(time);
        return StringToDate(str);
    }

    public static Timestamp StringToTimestamp(String date) {
        Long time = Long.parseLong(date);
        String str = getDateFormat(DateStyleEnum.YYYY_MM_DD_HH_MM_SS.getValue()).format(time);
        return Timestamp.valueOf(str);
    }

    private static SimpleDateFormat getDateFormat(String pattern) throws RuntimeException {
        return new SimpleDateFormat(pattern);
    }

    private static int getInteger(Date date, int dateType) {
        int num = 0;
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
            num = calendar.get(dateType);
        }

        return num;
    }

    private static String addInteger(String date, int dateType, int amount) {
        String dateString = null;
        DateStyleEnum dateStyleEnum = getDateStyle(date);
        if (dateStyleEnum != null) {
            Date myDate = StringToDate(date, dateStyleEnum);
            myDate = addInteger(myDate, dateType, amount);
            dateString = DateToString(myDate, dateStyleEnum);
        }

        return dateString;
    }

    private static Date addInteger(Date date, int dateType, int amount) {
        Date myDate = null;
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(dateType, amount);
            myDate = calendar.getTime();
        }

        return myDate;
    }

    private static Date getAccurateDate(List<Long> timestamps) {
        Date date = null;
        long timestamp = 0L;
        Map<Long, long[]> map = new HashMap();
        List<Long> absoluteValues = new ArrayList();
        if (timestamps != null && timestamps.size() > 0) {
            if (timestamps.size() > 1) {
                for(int i = 0; i < timestamps.size(); ++i) {
                    for(int j = i + 1; j < timestamps.size(); ++j) {
                        long absoluteValue = Math.abs((Long)timestamps.get(i) - (Long)timestamps.get(j));
                        absoluteValues.add(absoluteValue);
                        long[] timestampTmp = new long[]{(Long)timestamps.get(i), (Long)timestamps.get(j)};
                        map.put(absoluteValue, timestampTmp);
                    }
                }

                long minAbsoluteValue = -1L;
                if (!absoluteValues.isEmpty()) {
                    minAbsoluteValue = (Long)absoluteValues.get(0);

                    for(int i = 1; i < absoluteValues.size(); ++i) {
                        if (minAbsoluteValue > (Long)absoluteValues.get(i)) {
                            minAbsoluteValue = (Long)absoluteValues.get(i);
                        }
                    }
                }

                if (minAbsoluteValue != -1L) {
                    long[] timestampsLastTmp = (long[])map.get(minAbsoluteValue);
                    long dateOne = timestampsLastTmp[0];
                    long dateTwo = timestampsLastTmp[1];
                    if (absoluteValues.size() > 1) {
                        timestamp = Math.abs(dateOne) > Math.abs(dateTwo) ? dateOne : dateTwo;
                    }
                }
            } else {
                timestamp = (Long)timestamps.get(0);
            }
        }

        if (timestamp != 0L) {
            date = new Date(timestamp);
        }

        return date;
    }

    public static boolean isDate(String date) {
        boolean isDate = false;
        if (date != null && getDateStyle(date) != null) {
            isDate = true;
        }

        return isDate;
    }

    public static DateStyleEnum getDateStyle(String date) {
        DateStyleEnum dateStyleEnum = null;
        Map<Long, DateStyleEnum> map = new HashMap();
        List<Long> timestamps = new ArrayList();
        DateStyleEnum[] var4 = DateStyleEnum.values();
        int var5 = var4.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            DateStyleEnum style = var4[var6];
            if (!style.isShowOnly()) {
                Date dateTmp = null;
                if (date != null) {
                    try {
                        ParsePosition pos = new ParsePosition(0);
                        dateTmp = getDateFormat(style.getValue()).parse(date, pos);
                        if (pos.getIndex() != date.length()) {
                            dateTmp = null;
                        }
                    } catch (Exception var10) {
                        throw new RuntimeException(var10);
                    }
                }

                if (dateTmp != null) {
                    timestamps.add(dateTmp.getTime());
                    map.put(dateTmp.getTime(), style);
                }
            }
        }

        Date accurateDate = getAccurateDate(timestamps);
        if (accurateDate != null) {
            dateStyleEnum = (DateStyleEnum)map.get(accurateDate.getTime());
        }

        return dateStyleEnum;
    }

    public static Date StringToDate(String date) {
        DateStyleEnum dateStyleEnum = getDateStyle(date);
        return StringToDate(date, dateStyleEnum);
    }

    public static Date StringToDate(String date, String pattern) {
        Date myDate = null;
        if (date != null) {
            try {
                myDate = getDateFormat(pattern).parse(date);
            } catch (Exception var4) {
                throw new RuntimeException(var4);
            }
        }

        return myDate;
    }

    public static Date StringToDate(String date, DateStyleEnum dateStyleEnum) {
        Date myDate = null;
        if (dateStyleEnum != null) {
            myDate = StringToDate(date, dateStyleEnum.getValue());
        }

        return myDate;
    }

    public static String DateToString(Date date, String pattern) {
        String dateString = null;
        if (date != null) {
            try {
                dateString = getDateFormat(pattern).format(date);
            } catch (Exception var4) {
                throw new RuntimeException(var4);
            }
        }

        return dateString;
    }

    public static String DateToString(Date date, DateStyleEnum dateStyleEnum) {
        String dateString = null;
        if (dateStyleEnum != null) {
            dateString = DateToString(date, dateStyleEnum.getValue());
        }

        return dateString;
    }

    public static String DateToString(Date date) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static String StringToString(String date, String newPattern) {
        DateStyleEnum oldDateStyle = getDateStyle(date);
        return StringToString(date, oldDateStyle, newPattern);
    }

    public static String StringToString(String date, DateStyleEnum newDateStyle) {
        DateStyleEnum oldDateStyle = getDateStyle(date);
        return StringToString(date, oldDateStyle, newDateStyle);
    }

    public static String StringToString(String date, String olddPattern, String newPattern) {
        return DateToString(StringToDate(date, olddPattern), newPattern);
    }

    public static String StringToString(String date, DateStyleEnum olddDteStyle, String newParttern) {
        String dateString = null;
        if (olddDteStyle != null) {
            dateString = StringToString(date, olddDteStyle.getValue(), newParttern);
        }

        return dateString;
    }

    public static String StringToString(String date, String olddPattern, DateStyleEnum newDateStyle) {
        String dateString = null;
        if (newDateStyle != null) {
            dateString = StringToString(date, olddPattern, newDateStyle.getValue());
        }

        return dateString;
    }

    public static String StringToString(String date, DateStyleEnum olddDteStyle, DateStyleEnum newDateStyle) {
        String dateString = null;
        if (olddDteStyle != null && newDateStyle != null) {
            dateString = StringToString(date, olddDteStyle.getValue(), newDateStyle.getValue());
        }

        return dateString;
    }

    public static String addYear(String date, int yearAmount) {
        return addInteger((String)date, 1, yearAmount);
    }

    public static Date addYear(Date date, int yearAmount) {
        return addInteger((Date)date, 1, yearAmount);
    }

    public static String addMonth(String date, int monthAmount) {
        return addInteger((String)date, 2, monthAmount);
    }

    public static Date addMonth(Date date, int monthAmount) {
        return addInteger((Date)date, 2, monthAmount);
    }

    public static String addDay(String date, int dayAmount) {
        return addInteger((String)date, 5, dayAmount);
    }

    public static Date addDay(Date date, int dayAmount) {
        return addInteger((Date)date, 5, dayAmount);
    }

    public static String addHour(String date, int hourAmount) {
        return addInteger((String)date, 11, hourAmount);
    }

    public static Date addHour(Date date, int hourAmount) {
        return addInteger((Date)date, 11, hourAmount);
    }

    public static String addMinute(String date, int minuteAmount) {
        return addInteger((String)date, 12, minuteAmount);
    }

    public static Date addMinute(Date date, int minuteAmount) {
        return addInteger((Date)date, 12, minuteAmount);
    }

    public static String addSecond(String date, int secondAmount) {
        return addInteger((String)date, 13, secondAmount);
    }

    public static Date addSecond(Date date, int secondAmount) {
        return addInteger((Date)date, 13, secondAmount);
    }

    public static int getYear(String date) {
        return getYear(StringToDate(date));
    }

    public static int getYear(Date date) {
        return getInteger(date, 1);
    }

    public static int getMonth(String date) {
        return getMonth(StringToDate(date));
    }

    public static int getMonth(Date date) {
        return getInteger(date, 2) + 1;
    }

    public static int getDay(String date) {
        return getDay(StringToDate(date));
    }

    public static int getDay(Date date) {
        return getInteger(date, 5);
    }

    public static int getHour(String date) {
        return getHour(StringToDate(date));
    }

    public static int getHour(Date date) {
        return getInteger(date, 11);
    }

    public static int getMinute(String date) {
        return getMinute(StringToDate(date));
    }

    public static int getMinute(Date date) {
        return getInteger(date, 12);
    }

    public static int getSecond(String date) {
        return getSecond(StringToDate(date));
    }

    public static int getSecond(Date date) {
        return getInteger(date, 13);
    }

    public static String getDate(String date) {
        return StringToString(date, DateStyleEnum.YYYY_MM_DD);
    }

    public static String getDate(Date date) {
        return DateToString(date, DateStyleEnum.YYYY_MM_DD);
    }

    public static String getDatetime(Date date) {
        return DateToString(date, DateStyleEnum.YYYY_MM_DD_HH_MM_SS);
    }

    public static String getTime(String date) {
        return StringToString(date, DateStyleEnum.HH_MM_SS);
    }

    public static String getTime(Date date) {
        return DateToString(date, DateStyleEnum.HH_MM_SS);
    }

    public static WeekEnum getWeek(String date) {
        WeekEnum weekEnum = null;
        DateStyleEnum dateStyleEnum = getDateStyle(date);
        if (dateStyleEnum != null) {
            Date myDate = StringToDate(date, dateStyleEnum);
            weekEnum = getWeek(myDate);
        }

        return weekEnum;
    }

    public static WeekEnum getWeek(Date date) {
        WeekEnum weekEnum = null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int weekNumber = calendar.get(7) - 1;
        switch(weekNumber) {
            case 0:
                weekEnum = WeekEnum.SUNDAY;
                break;
            case 1:
                weekEnum = WeekEnum.MONDAY;
                break;
            case 2:
                weekEnum = WeekEnum.TUESDAY;
                break;
            case 3:
                weekEnum = WeekEnum.WEDNESDAY;
                break;
            case 4:
                weekEnum = WeekEnum.THURSDAY;
                break;
            case 5:
                weekEnum = WeekEnum.FRIDAY;
                break;
            case 6:
                weekEnum = WeekEnum.SATURDAY;
        }

        return weekEnum;
    }

    public static Integer getIntervalDays(String date, String otherDate) {
        return getIntervalDays(StringToDate(date), StringToDate(otherDate));
    }

    public static Integer getIntervalDays(Date date, Date otherDate) {
        Integer num = null;
        Date dateTmp = StringToDate(getDate(date), DateStyleEnum.YYYY_MM_DD);
        Date otherDateTmp = StringToDate(getDate(otherDate), DateStyleEnum.YYYY_MM_DD);
        if (dateTmp != null && otherDateTmp != null) {
            long time = dateTmp.getTime() - otherDateTmp.getTime();
            num = (int)(time / 86400000L);
        }

        return num;
    }

    public static String TimeToDate(String date) {
        Long time = Long.parseLong(date);
        return getDateFormat(DateStyleEnum.YYYY_MM_DD.getValue()).format(time);
    }

    public static Date getCurrentDate(DateStyleEnum dateStyleEnum) {
        String date = DateToString(new Date(), dateStyleEnum);
        return StringToDate(date, dateStyleEnum);
    }

    public static String getCurrentDateStr(DateStyleEnum dateStyleEnum) {
        return DateToString(new Date(), dateStyleEnum);
    }

    public static String getCurrentDateStr() {
        return DateToString(new Date(), DateStyleEnum.YYYY_MM_DD_HH_MM_SS);
    }

    public static Date getBeforeTime(int n, int type) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(type, -n);
        return calendar.getTime();
    }

    public static Date string2Date(String d, String style) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(style);
        return sdf.parse(d);
    }

    public static Date string2Date(String d) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(d);
    }

    public static String formatString(String d, DateStyleEnum style) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return DateToString(sdf.parse(d), style);
    }

    public static String getCurrentTime() {
        return DateToString(new Date(), DateStyleEnum.YYYY_MM_DD_HH_MM_SS);
    }

    public static Date DateFormat(Date d) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        return cal.getTime();
    }

    public static String getCurrentTime(String style) throws Exception {
        return DateToString(new Date(), style);
    }

    public static Date backYear(int n) throws Exception {
        Calendar c = Calendar.getInstance();
        c.add(1, -n);
        return c.getTime();
    }

    public static Long DateToLong(Date d, DateStyleEnum dateStyleEnum) {
        String s = DateToString(d, dateStyleEnum.getValue());
        return Long.parseLong(s);
    }

    public static Long getCurrentDateTime() {
        return DateToLong(new Date(), DateStyleEnum.YYYYMMDDHHMMSS);
    }

    public static Date getCurrYearFirst() {
        Calendar yearCal = Calendar.getInstance();
        int year = yearCal.get(1);
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(1, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    public static Date getCurrYearLast() {
        Calendar yearCal = Calendar.getInstance();
        int year = yearCal.get(1);
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(1, year);
        calendar.roll(6, -1);
        calendar.set(11, calendar.getActualMaximum(11));
        calendar.set(12, calendar.getActualMaximum(12));
        calendar.set(13, calendar.getActualMaximum(13));
        calendar.set(14, calendar.getActualMaximum(14));
        Date currYearLast = calendar.getTime();
        return currYearLast;
    }

    public static Date getCurrMonthFirst() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(5, 1);
        calendar.set(5, calendar.getActualMinimum(5));
        calendar.set(11, calendar.getActualMinimum(11));
        calendar.set(12, calendar.getActualMinimum(12));
        calendar.set(13, calendar.getActualMinimum(13));
        calendar.set(14, calendar.getActualMinimum(14));
        return calendar.getTime();
    }

    public static Date getCurrMonthLast() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(5, calendar.getActualMaximum(5));
        calendar.set(11, calendar.getActualMaximum(11));
        calendar.set(12, calendar.getActualMaximum(12));
        calendar.set(13, calendar.getActualMaximum(13));
        calendar.set(14, calendar.getActualMaximum(14));
        return calendar.getTime();
    }

    public static Date getCurrDayFirst() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, calendar.getActualMinimum(11));
        calendar.set(12, calendar.getActualMinimum(12));
        calendar.set(13, calendar.getActualMinimum(13));
        calendar.set(14, calendar.getActualMinimum(14));
        return calendar.getTime();
    }

    public static Date getCurrDayLast() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        calendar.set(14, calendar.getActualMaximum(14));
        return calendar.getTime();
    }

    public static Date getYearFirstTime(int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, calendar.get(1) + n);
        calendar.set(2, calendar.getActualMinimum(2));
        calendar.set(5, calendar.getActualMinimum(5));
        calendar.set(11, calendar.getActualMinimum(11));
        calendar.set(12, calendar.getActualMinimum(12));
        calendar.set(13, calendar.getActualMinimum(13));
        calendar.set(14, calendar.getActualMinimum(14));
        return calendar.getTime();
    }

    public static Date getMonthFirstTime(int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2, calendar.get(2) + n);
        calendar.set(5, calendar.getActualMinimum(5));
        calendar.set(11, calendar.getActualMinimum(11));
        calendar.set(12, calendar.getActualMinimum(12));
        calendar.set(13, calendar.getActualMinimum(13));
        calendar.set(14, calendar.getActualMinimum(14));
        return calendar.getTime();
    }

    public static Date getDayFirstTime(int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(5, calendar.get(5) + n);
        calendar.set(11, calendar.getActualMinimum(11));
        calendar.set(12, calendar.getActualMinimum(12));
        calendar.set(13, calendar.getActualMinimum(13));
        calendar.set(14, calendar.getActualMinimum(14));
        return calendar.getTime();
    }

    public static Date addTime(Date date, int n, int calendarType) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendarType, n);
        return calendar.getTime();
    }

    public static Date getLastTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(11, calendar.getActualMaximum(11));
        calendar.set(12, calendar.getActualMaximum(12));
        calendar.set(13, calendar.getActualMaximum(13));
        calendar.set(14, calendar.getActualMaximum(14));
        return calendar.getTime();
    }

    public static Date getFirstTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(11, calendar.getActualMinimum(11));
        calendar.set(12, calendar.getActualMinimum(12));
        calendar.set(13, calendar.getActualMinimum(13));
        calendar.set(14, calendar.getActualMinimum(14));
        return calendar.getTime();
    }

    public static void main(String[] arg) throws Exception {
        String dateStyle = DateStyleEnum.YYYY_MM_DD_HH_MM_SS.getValue();
        System.out.println(DateToString(getBeforeTime(4, 11), dateStyle));
        System.out.println("当年第一时间：" + DateToString(getCurrYearFirst(), dateStyle));
        System.out.println("当年最后时间：" + DateToString(getCurrYearLast(), dateStyle));
        System.out.println("当月第一时间：" + DateToString(getCurrMonthFirst(), dateStyle));
        System.out.println("当月最后时间：" + DateToString(getCurrMonthLast(), dateStyle));
        System.out.println("当天第一时间：" + DateToString(getCurrDayFirst(), dateStyle));
        System.out.println("当天最后时间：" + DateToString(getCurrDayLast(), dateStyle));
        System.out.println("N年第一时间：" + DateToString(getYearFirstTime(-1), dateStyle));
        System.out.println("N月第一时间：" + DateToString(getMonthFirstTime(0), dateStyle));
        System.out.println("N天第一时间：" + DateToString(getDayFirstTime(-1), dateStyle));
//        System.out.println(getWeek(new Date()).getNumber());
    }
}
