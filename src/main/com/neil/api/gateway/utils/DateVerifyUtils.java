package neil.api.gateway.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateVerifyUtils {

    /**
     * 根据SimpleDateFormat检查是否有效日期
     *
     * @param str
     * @param sdfDateTime
     * @return
     */
    public static boolean isValidDateTimeBySDF(String str, SimpleDateFormat sdfDateTime) {
        boolean convertSuccess = true;
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            sdfDateTime.setLenient(false);
            sdfDateTime.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }

    /**
     * 检查是否正常日期
     *
     * @param str
     * @return
     */
    public static boolean isCorrectYearMonthDayFormat(String str) {
        boolean convertSuccess = true;
        try {
            SimpleDateFormat format = DateFormat.sdfYearMonthDay;
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }
    /**
     * 检查是否正常日期
     *
     * @param str
     * @return
     */
    public static boolean isCorrectYearMonthFormat(String str) {
        boolean convertSuccess = true;
        try {
            SimpleDateFormat format = DateFormat.sdfYearMonth;
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }

    /**
     * 检查是否正常日期时间
     *
     * @param str
     * @return
     */
    public static boolean isValidDateTime(String str) {
        boolean convertSuccess = true;
        try {
            SimpleDateFormat format = DateFormat.sdfDateTime;
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }

    public static void main(String[] args) {
        System.out.println(DateVerifyUtils.isCorrectYearMonthFormat("1"));
        System.out.println(DateVerifyUtils.isCorrectYearMonthFormat("2015-06"));
        System.out.println(DateVerifyUtils.isCorrectYearMonthFormat("2445"));
        System.out.println(DateVerifyUtils.isCorrectYearMonthFormat("1"));
    }
}
