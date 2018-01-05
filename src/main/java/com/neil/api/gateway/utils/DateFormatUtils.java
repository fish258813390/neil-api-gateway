package com.neil.api.gateway.utils;

import com.neil.commons.exception.BusinessException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtils {

    /**
     * 返回中文xxxx年xx月xx日
     *
     * @param object
     * @return
     */
    public static String getChsYMDByObject(Object object) {

        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(object.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String now = new SimpleDateFormat("yyyy年MM月dd日").format(date);
        return now;
    }


    /**
     * YYYY-MM-DD
     *
     * @param inStr
     * @return
     * @throws BusinessException
     */
    public static String convertYYYYMMDD2CHS(String inStr) throws BusinessException {
        String[] source = inStr.split("-");
        if (source.length == 3) {
            return source[0] + "年" + source[1] + "月" + source[2] + "日";
        } else {
            throw new BusinessException("传入格式异常!");
        }
    }

    /**
     * YYYY-MM-DD HH24:MI
     *
     * @param inStr
     * @return
     * @throws BusinessException
     */
    public static String convertYYYYMMDDHH24MI2CHS(String inStr) throws BusinessException {
        String[] all = inStr.split(" ");
        if (all.length == 2) {
            String[] date = all[0].split("-");
            String[] time = all[1].split(":");
            if (date.length == 3 && time.length == 2) {
                return date[0] + "年" + date[1] + "月" + date[2] + "日" + time[0] + "时";
            } else {
                throw new BusinessException("传入格式异常!");
            }
        } else {
            throw new BusinessException("传入格式异常!");
        }
    }

    public static void main(String[] args) {
        Object obj = "2015-05-05 00:00:00";
        String lastDay = getChsYMDByObject(obj);
        System.out.println(lastDay);
    }
}
