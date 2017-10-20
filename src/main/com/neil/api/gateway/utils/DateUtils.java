package neil.api.gateway.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {

    /**
     * 根据年月获取最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayByYearMonth(String year, String month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, Integer.parseInt(year));
        //设置月份
        cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());

        return lastDayOfMonth;
    }

    /**
     * 根据年月获取最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayByYearMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());

        return lastDayOfMonth;
    }

    /**
     * 获取当前年月CHS
     *
     * @return
     */
    public static String getCurrentYearMonthCHS() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        return year + " 年 " + month + " 月";
    }

    /**
     * 获取当前年月CHS
     *
     * @return
     */
    public static String getCurrentYearMonth() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        return year + "-" + month;
    }

    public static void main(String[] args) {
        String lastDay = getLastDayByYearMonth(2015, 1);
        System.out.println("获取当前月的最后一天：" + lastDay);
        lastDay = getLastDayByYearMonth("2015", "11");
        System.out.println("获取当前月的最后一天：" + lastDay);
    }

    public static boolean isFirstDayOfTheMonth() {
        // 获得一个日历对象
        Calendar c = Calendar.getInstance();
        // 得到本月的哪一天
        int today = c.get(c.DAY_OF_MONTH);
        // 然后判断是不是本月的第一天
        if (today == 1) {
            return true;
        } else {
            return false;
        }
    }
}
