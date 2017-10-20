package neil.api.gateway.utils;

import com.neil.commons.exception.BusinessException;

import java.util.List;

/**
 * 扩展org.apache.commons.beanutils.BeanUtils<br>
 *
 * @author Wesley<br>
 */
public class MybatisStringUtils {

    /**
     * 将 xxx,xxx,xxx形式数据转为'xxx','xxx','xxx'
     * 避免遍历数组for (..in ..)直接构造('xxx','xxx','xxx')
     *
     * @param input
     * @return
     * @throws
     */
    public static String converInStr(String input) throws BusinessException {
        if (input.isEmpty()) {
            throw new BusinessException("传入参数为空!");
        }
        return PunctuationFormat.SingleQuotationMarks + input.replace(PunctuationFormat.Comma, "','") + PunctuationFormat.SingleQuotationMarks;
    }

    /**
     * 将传入List<Long> ids转为'xxx','xxx','xxx'
     *
     * @param sourceList
     * @return
     * @throws BusinessException
     */
    public static String converLongListToStrWithSingleQuotationMarks(List<Long> sourceList) throws BusinessException {
        String returnStr = "";
        if (sourceList.size() < 1) {
            throw new BusinessException("");
        }
        if (sourceList.size() == 1) {
            return String.valueOf(sourceList.get(0));
        } else {
            for (int i = 0; i < sourceList.size(); i++) {
                if (i == 0) {
                    returnStr = sourceList.get(i) + PunctuationFormat.SingleQuotationMarks + PunctuationFormat.Comma;
                } else if (i < sourceList.size() - 1) {
                    returnStr = returnStr + PunctuationFormat.SingleQuotationMarks + sourceList.get(i) + PunctuationFormat.SingleQuotationMarks + PunctuationFormat.Comma;
                } else {
                    returnStr = returnStr + PunctuationFormat.SingleQuotationMarks + sourceList.get(i);
                }
            }
            return returnStr;
        }
    }

    /**
     * 将传入List<String> ids转为'xxx','xxx','xxx'
     *
     * @param sourceList
     * @return
     * @throws BusinessException
     */
    public static String converStringListToStrWithSingleQuotationMarks(List<String> sourceList) throws BusinessException {
        String returnStr = "";
        if (sourceList.size() < 1) {
            throw new BusinessException("");
        }
        if (sourceList.size() == 1) {
            return PunctuationFormat.SingleQuotationMarks + String.valueOf(sourceList.get(0)) + PunctuationFormat.SingleQuotationMarks;
        } else {
            for (int i = 0; i < sourceList.size(); i++) {
                if (i == 0) {
                    returnStr = PunctuationFormat.SingleQuotationMarks + sourceList.get(i) + PunctuationFormat.SingleQuotationMarks + PunctuationFormat.Comma;
                } else if (i < sourceList.size() - 1) {
                    returnStr = returnStr + PunctuationFormat.SingleQuotationMarks + sourceList.get(i) + PunctuationFormat.SingleQuotationMarks + PunctuationFormat.Comma;
                } else {
                    returnStr = returnStr + PunctuationFormat.SingleQuotationMarks + sourceList.get(i) + PunctuationFormat.SingleQuotationMarks;
                }
            }
            return returnStr;
        }
    }

    /**
     * 将传入List<Long> ids转为"xxx","xxx","xxx"
     *
     * @param ids
     * @return
     * @throws BusinessException
     */
    public static String converToStrWithDoubleQuotationMarks(List<Long> ids) throws BusinessException {
        String idsStr = "";
        if (ids.size() < 1) {
            throw new BusinessException("");
        }
        for (int i = 0; i < ids.size(); i++) {
            if (i < ids.size() - 1) {
                idsStr = idsStr + PunctuationFormat.DoubleQuotationMarks + ids.get(i) + PunctuationFormat.DoubleQuotationMarks + PunctuationFormat.Comma;
            } else {
                idsStr = idsStr + PunctuationFormat.DoubleQuotationMarks + ids.get(i) + PunctuationFormat.DoubleQuotationMarks;
            }
        }
        return idsStr;
    }

    /**
     * 将传入List<Long> ids转为xxx,xxx,xxx
     *
     * @param ids
     * @return
     * @throws BusinessException
     */
    public static String converToStrWithNoQuotationMarks(List<Long> ids) throws BusinessException {
        String idsStr = "";
        if (ids.size() < 1) {
            throw new BusinessException("");
        }
        for (int i = 0; i < ids.size(); i++) {
            if (i < ids.size() - 1) {
                idsStr = idsStr + ids.get(i) + PunctuationFormat.Comma;
            } else {
                idsStr = idsStr + ids.get(i);
            }
        }
        return idsStr;
    }

    /**
     * 将传入long[] ids转为xxx,xxx,xxx
     *
     * @param ids
     * @return
     * @throws BusinessException
     */
    public static String converToStrWithNoQuotationMarksForArray(long[] ids) throws BusinessException {
        String idsStr = "";
        if (ids.length < 1) {
            throw new BusinessException("");
        }
        for (int i = 0; i < ids.length; i++) {
            if (i < ids.length - 1) {
                idsStr = idsStr + ids[i] + PunctuationFormat.Comma;
            } else {
                idsStr = idsStr + ids[i];
            }
        }
        return idsStr;
    }

    /**
     * 将字符串拆分添加oracle like %.
     * 形式为 张俊辉 转%Z%J%H%
     *
     * @param str
     * @return
     */
    public static String converToLIKEPY(String str) {
        if (str.isEmpty()) {
            return "%";
        }
        char[] capPY = PinYinUtils.getPinYinHeadChar(str).toUpperCase().toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < capPY.length; i++) {
            sb.append("%" + capPY[i]);
        }
        sb.append("%");
        return sb.toString();
    }
}