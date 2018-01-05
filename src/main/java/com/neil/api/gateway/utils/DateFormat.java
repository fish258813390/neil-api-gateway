package com.neil.api.gateway.utils;

import java.text.SimpleDateFormat;

/**
 * J开头为java中日期格式。由于java系统限制，精度只能到微秒
 * O开头为oracle中日期格式。
 * oracle日期格式秒和微秒之前的X为本地分隔符Local radix character.
 */
public class DateFormat {

    public static final String SHORT_DATE = "yyyyMMdd";

    // Grace style
    public static final String PATTERN_GRACE = "yyyy/MM/dd HH:mm:ss";
    public static final String PATTERN_GRACE_NORMAL = "yyyy/MM/dd HH:mm";
    public static final String PATTERN_GRACE_SIMPLE = "yyyy/MM/dd";

    // Classical style
    public static final String PATTERN_CLASSICAL = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_CLASSICAL_NORMAL = "yyyy-MM-dd HH:mm";
    public static final String PATTERN_CLASSICAL_SIMPLE = "yyyy-MM-dd";

    /**
     * 标准日期格式
     */
    public static final SimpleDateFormat sdfYearMonthDay = new SimpleDateFormat("yyyy-MM-dd");

    public static final SimpleDateFormat sdfYearMonth = new SimpleDateFormat("yyyy-MM");

    /**
     * 标准日期时间格式
     */
    public static final SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * JAVA标准日期
     */
    public static final String JStandardDate = "yyyy-MM-dd";
    /**
     * JAVA标准时间
     */
    public static final String JStandardTime = "yyyy-MM-dd HH:mm:ss";
    /**
     * JAVA标准时间高精度
     */
    public static final String JStandardTimestamp = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     *ORACLE标准日期
     */
    public static final String OstandardDate = "YYYY-MM-DD";
    /**
     * ORACLE标准日期时间
     */
    public static final String OstandardTime = "YYYY-MM-DD HH24:MI:SS";
    /**
     * ORACLE标准timestamp
     */
    public static final String OstandardTimestamp = "YYYY-MM-DD HH24:MI:SSXFF";
    public static final String OstandardTimestamp3 = "YYYY-MM-DD HH24:MI:SSXFF3";
    public static final String OstandardTimestamp6 = "YYYY-MM-DD HH24:MI:SSXFF6";
}
