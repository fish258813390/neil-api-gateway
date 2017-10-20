package neil.api.gateway.utils;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

import java.util.*;


public class JodaTimeUtils {

    public static final int WEEK_DAYS = 7;
    private static final String format_default = "yyyy-MM-dd HH:mm:ss";
    private static final String format_ymd = "yyyy-MM-dd";
    private static final String format_ym = "yyyy-MM";

    public static DateTime calendarToDateTime(Calendar c) {
        return new DateTime(c);
    }

    public static DateTime dateToDateTime(Date date) {
        return new DateTime(date);
    }

    /**
     * 日期转为YYYY-MM-DD格式
     *
     * @param c
     * @return
     */
    public static String dateToYMD(Calendar c) {
        String pattern = format_ymd;
        return calendarToDateTime(c).toString(pattern);
    }

    /**
     * 日期转为YYYY-MM格式
     *
     * @param c
     * @return
     */
    public static String dateToYM(Calendar c) {
        String pattern = format_ym;
        return calendarToDateTime(c).toString(pattern);
    }

    /**
     * 日期转为YYYY-MM-DD格式
     *
     * @param c
     * @return
     */
    public static String dateToYMD(DateTime c) {
        String pattern = format_ymd;
        return c.toString(pattern);
    }

    /**
     * 日期转为YYYY-MM格式
     *
     * @param c
     * @return
     */
    public static String dateToYM(DateTime c) {
        String pattern = format_ym;
        return c.toString(pattern);
    }

    /**
     * 转换时间到指定字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String dateToCusString(Date date, String format) {
        String pattern = format_default;
        if (StringUtils.isNotEmpty(format)) {
            pattern = format;
        }
        return dateToDateTime(date).toString(pattern);
    }

    public static DateTime stringToDateTime(String time) {
        return DateTime.parse(time, DateTimeFormat.forPattern(format_default));
    }

    public static Calendar stringToCalendar(String time) {
        return stringToDateTime(time).toCalendar(Locale.getDefault());
    }

    /**
     * 二时间差值
     *
     * @param startCal
     * @param endCal
     * @param type     　d:天数，h:小时，m:分钟，s:秒数，默认:毫秒
     * @return
     */
    public static long compareCalendarByType(Calendar startCal, Calendar endCal, String type) {
        String key = "d";
        if (StringUtils.isNotEmpty(type)) {
            key = type;
        }
        long diffNum = 0;
        DateTime begin = setDateTimeToStart(calendarToDateTime(startCal), key);
        DateTime end = setDateTimeToStart(calendarToDateTime(endCal), key);

        Duration duration = new Duration(begin, end);
        switch (key) {
            case "d":
                diffNum = duration.getStandardDays();
                break;
            case "h":
                diffNum = duration.getStandardHours();
                break;
            case "m":
                diffNum = duration.getStandardMinutes();
                break;
            case "s":
                diffNum = duration.getStandardSeconds();
                break;
            default:
                diffNum = duration.getMillis();
                break;
        }
        return diffNum;
    }

    /**
     * 设置时间：月日时分秒为2000-01-01 00:00:00
     *
     * @param dateTime
     * @param key      y:年 Ｍ:月 d:天 h:小时 m:分钟
     * @return
     */
    public static DateTime setDateTimeToStart(DateTime dateTime, String key) {
        if (dateTime == null) {
            dateTime = DateTime.now();
        }
        switch (key) {
            case "y":
                dateTime = dateTime.withMonthOfYear(1).withDayOfMonth(1).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0);
                break;
            case "M":
                dateTime = dateTime.withDayOfMonth(1).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0);
                break;
            case "d":
                dateTime = dateTime.withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0);
                break;
            case "h":
                dateTime = dateTime.withMinuteOfHour(0).withSecondOfMinute(0);
                break;
            case "m":
                dateTime = dateTime.withSecondOfMinute(0);
                break;
        }
        return dateTime;
    }

    /**
     * 加时间
     *
     * @param num
     * @param key
     * @param c
     * @return
     */
    public static Calendar plusNumToCalendar(int num, String key, Calendar c) {
        Calendar calendar = null;
        if (c != null) {
            calendar = c;
        } else {
            calendar = Calendar.getInstance();
        }
        return plusNumToDateTime(num, key, calendarToDateTime(calendar)).toCalendar(Locale.getDefault());
    }

    public static DateTime plusNumToDateTime(int num, String key, DateTime dt) {
        DateTime dateTime = DateTime.now();
        if (dt != null) {
            dateTime = dt;
        }
        switch (key) {
            case "y":
                dateTime = dateTime.plusYears(num);
                break;
            case "M":
                dateTime = dateTime.plusMonths(num);
                break;
            case "w":
                dateTime = dateTime.plusWeeks(num);
                break;
            case "d":
                dateTime = dateTime.plusDays(num);
                break;
            case "h":
                dateTime = dateTime.plusHours(num);
                break;
            case "m":
                dateTime = dateTime.plusMinutes(num);
                break;
            case "s":
                dateTime = dateTime.plusSeconds(num);
                break;
        }
        return dateTime;
    }

    /**
     * 返回23:59:59
     *
     * @param c
     * @return
     */
    public static Calendar getCalendarMidnight(Calendar c) {
        return getDateTimeMidnight(c).toCalendar(Locale.getDefault());
    }

    public static DateTime getDateTimeMidnight(Calendar c) {
        DateTime dateTime = calendarToDateTime(c);
        dateTime = dateTime.withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59);
        return dateTime;
    }

    /**
     * 一周中的第几天。周一：１　周二：２
     *
     * @param c
     * @return
     */
    public static int getDayInWeek(Calendar c) {
        Calendar calendar = null;
        if (c != null) {
            calendar = c;
        } else {
            calendar = Calendar.getInstance();
        }
        return new DateTime(calendar).getDayOfWeek();
    }

    /**
     * 时间段内　日期列表
     *
     * @param c1
     * @param c2
     * @param format
     * @return
     */
    public static List<String> getDayBetweenStr(Calendar c1, Calendar c2, String format) {
        List<String> list = new ArrayList<String>(15);
        String pattern = format_ymd;
        if (StringUtils.isNotEmpty(format)) {
            pattern = format;
        }
        long days = compareCalendarByType(c1, c2, "d");
        DateTime start = calendarToDateTime(c1);
        for (int i = 0; i <= days; i++) {
            list.add(plusNumToDateTime(i, "d", start).toString(pattern));
        }
        return list;
    }

    /**
     * 日期在年中第几周
     *
     * @param c
     * @return
     */
    public static int getWeekInYear(Calendar c) {
        Calendar calendar = Calendar.getInstance();
        if (c != null) {
            calendar = c;
        }
        return new DateTime(calendar).getWeekOfWeekyear();
    }

    public static int getWeekOfYMD(int year, int monthOfYear, int dayOfMonth) {
        LocalDate date = new LocalDate(year, monthOfYear, dayOfMonth);
        return date.weekOfWeekyear().get();
    }

    /**
     * 某年某周第一天
     *
     * @param year
     * @param weekOfWeekyear
     * @return
     */
    public static DateTime getFirstDayOfYearWeek(int year, int weekOfWeekyear) {
        DateTime dateTime = DateTime.now();
        dateTime = dateTime.withYear(year).withWeekOfWeekyear(weekOfWeekyear).dayOfWeek().withMinimumValue();
        return dateTime;
    }

    public static DateTime getLastDayOfYearWeek(int year, int weekOfWeekyear) {
        return DateTime.now().withYear(year).withWeekOfWeekyear(weekOfWeekyear).dayOfWeek().withMaximumValue();
    }

    public static DateTime getFirstDayInNowWeek() {
        return DateTime.now().dayOfWeek().withMinimumValue();
    }

    public static DateTime getLastDayInNowWeek() {
        return DateTime.now().dayOfWeek().withMaximumValue();
    }

    public static DateTime getLastDayOfYearMonth(int year, int monthOfYear) {
        return DateTime.now().withYear(year).withMonthOfYear(monthOfYear).dayOfMonth().withMaximumValue();
    }

    public static DateTime getLastDayOfNowMonth() {
        return DateTime.now().dayOfMonth().withMaximumValue();
    }

    /**
     * 某周的七天日期
     *
     * @param format
     * @return
     */
    public static Map<String, String> getDateOfWeekMap(String format) {
        DateTime dateTime = DateTime.now();
        return getDateOfWeekMap(dateTime.getYear(), dateTime.getWeekOfWeekyear(), format);
    }

    public static Map<String, String> getDateOfWeekMap(int year, int week, String format) {
        String pattern = format_ymd;
        if (StringUtils.isNotEmpty(format)) {
            pattern = format;
        }
        Map<String, String> map = new LinkedHashMap<String, String>();
        DateTime dateTime = DateTime.now().withYear(year).withWeekOfWeekyear(week).dayOfWeek().withMinimumValue();
        for (int i = 0; i < WEEK_DAYS; i++) {
            map.put((i + 1) + "", dateTime.plusDays(i).toString(pattern));
        }
        return map;
    }

    public static Map<String, String> getWeekAssertDateMap(String format) {
        return getWeekAssertDateMap(2, true, format);
    }

    public static Map<String, String> getWeekAssertDateMap(int type, String format) {
        return getWeekAssertDateMap(type, true, format);
    }

    /**
     * 今日起一周内对应日期
     *
     * @param type               　１：数字(1-7) 2:周几　default:星期几
     * @param isChangeWeekToDate 默认true{key:type，value:日期}; false:{key:日期，value:type}
     * @param format
     * @return
     */
    public static Map<String, String> getWeekAssertDateMap(int type, boolean isChangeWeekToDate, String format) {
        Map<String, String> map = new LinkedHashMap<String, String>();
        String pattern = "yyMMdd";
        if (StringUtils.isNotEmpty(format)) {
            pattern = format;
        }
        String[] weekArr = new String[]{};
        switch (type) {
            case 1:
                weekArr = new String[]{"1", "2", "3", "4", "5", "6", "7"};
                break;
            case 2:
                weekArr = new String[]{"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
                break;
            default:
                weekArr = new String[]{"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
                break;
        }
        DateTime dateTime = DateTime.now();
        for (int i = 0; i < weekArr.length; i++) {
            int day = dateTime.getDayOfWeek();
            if (isChangeWeekToDate) {
                map.put(weekArr[day - 1], dateTime.toString(pattern));
            } else {
                map.put(dateTime.toString(pattern), weekArr[day - 1]);
            }
            dateTime = dateTime.plusDays(1);
        }
        return map;
    }


    public static int getTimeDiff(String timeStr) {
        int days = Days.daysBetween(DateTime.now(), new DateTime(timeStr)).getDays();
        return days;
    }
}
