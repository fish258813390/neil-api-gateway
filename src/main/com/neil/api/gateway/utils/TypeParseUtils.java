package neil.api.gateway.utils;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * 类型转换辅助工具类
 */
public class TypeParseUtils {
    /**
     * 转换核心实现方法
     *
     * @param value
     * @param type   目标数据类型
     * @param format 输入/输出字符串格式
     * @return Object
     * @throws DataParseException
     */
    public static Object convert(Object value, Class<?> type, String format)
            throws DataParseException {
        return convert(value, type.getName(), format);
    }

    /**
     * 转换核心实现方法
     *
     * @param value
     * @param type   目标数据类型
     * @param format 输入/输出字符串格式
     * @return Object
     * @throws DataParseException
     */
    public static Object convert(Object value, String type, String format)
            throws DataParseException {
        Locale locale = Locale.getDefault();
        String message = "Could not convert %1$s to %2$s";
        String support = "Convert from %1$s to %2$s not currently supported";
        if (value == null) {
            return null;
        }
        if (value.getClass().getName().equalsIgnoreCase(type)
                || value.getClass().getSimpleName().equalsIgnoreCase(type)) {
            return value;
        }
        if ("Object".equalsIgnoreCase(type) || "java.lang.Object".equals(type)) {
            return value;
        }
        String fromType = null;
        if (value instanceof String) {
            fromType = "String";
            String str = (String) value;
            if ("String".equalsIgnoreCase(type) || "java.lang.String".equalsIgnoreCase(type)) {
                return value;
            }
            if (str.length() == 0) {
                return null;
            }
            if ("Boolean".equalsIgnoreCase(type) || "java.lang.Boolean".equals(type)) {
                return (str.equalsIgnoreCase("TRUE"));
            }
            if ("Double".equalsIgnoreCase(type) || "java.lang.Double".equalsIgnoreCase(type))
                try {
                    Number tempNum = getNf(locale).parse(str.replaceAll(",", ""));
                    return new Double(tempNum.doubleValue());
                } catch (ParseException e) {
                    throw new DataParseException(String.format(message, str, type), e);
                }
            if ("BigDecimal".equalsIgnoreCase(type) || "java.math.BigDecimal".equalsIgnoreCase(type))
                try {
                    BigDecimal retBig = new BigDecimal(str.replaceAll(",", ""));
                    int iscale = str.indexOf(".");
                    int keylen = str.length();
                    if (iscale > -1) {
                        iscale = keylen - (iscale + 1);
                        return retBig.setScale(iscale, 5);
                    } else {
                        return retBig.setScale(0, 5);
                    }
                } catch (Exception e) {
                    throw new DataParseException(String.format(message, str, type), e);
                }
            if ("Float".equalsIgnoreCase(type) || "java.lang.Float".equalsIgnoreCase(type))
                try {
                    Number tempNum = getNf(locale).parse(str.replaceAll(",", ""));
                    return new Float(tempNum.floatValue());
                } catch (ParseException e) {
                    throw new DataParseException(String.format(message, str, type), e);
                }
            if ("Long".equalsIgnoreCase(type) || "java.lang.Long".equalsIgnoreCase(type))
                try {
                    NumberFormat nf = getNf(locale);
                    nf.setMaximumFractionDigits(0);
                    Number tempNum = nf.parse(str.replaceAll(",", ""));
                    return new Long(tempNum.longValue());
                } catch (ParseException e) {
                    throw new DataParseException(String.format(message, str, type), e);
                }
            if ("Integer".equalsIgnoreCase(type) || "java.lang.Integer".equalsIgnoreCase(type))
                try {
                    NumberFormat nf = getNf(locale);
                    nf.setMaximumFractionDigits(0);
                    Number tempNum = nf.parse(str.replaceAll(",", ""));
                    return new Integer(tempNum.intValue());
                } catch (ParseException e) {
                    throw new DataParseException(String.format(message, str, type), e);
                }
            if ("Date".equalsIgnoreCase(type) || "java.util.Date".equalsIgnoreCase(type)) {
                if (format == null || format.length() == 0) {
                    String separator = String.valueOf(str.charAt(4));
                    StringBuilder pattern;
                    if (separator.matches("\\d*")) {
                        pattern = new StringBuilder("yyyyMMdd HH:mm:ss");
                        format = pattern.substring(0, str.length());
                    } else {
                        pattern = new StringBuilder("yyyy").append(separator).append("MM").append(separator).append("dd HH:mm:ss");
                        format = pattern.substring(0, str.length());
                    }
                }
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat(format);
                    java.util.Date fieldDate = sdf.parse(str);
                    return new Date(fieldDate.getTime());
                } catch (ParseException e) {
                    throw new DataParseException(String.format(message, str, type), e);
                }
            }
            if ("java.sql.Date".equalsIgnoreCase(type)) {
                if (format == null || format.length() == 0)
                    try {
                        return Date.valueOf(str);
                    } catch (Exception e) {
                        try {
                            DateFormat df = null;
                            if (locale != null) {
                                df = DateFormat.getDateInstance(3, locale);
                            } else {
                                df = DateFormat.getDateInstance(3);
                            }
                            java.util.Date fieldDate = df.parse(str);
                            return new Date(fieldDate.getTime());
                        } catch (ParseException e1) {
                            throw new DataParseException(String.format(message, str, type), e);
                        }
                    }
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat(format);
                    java.util.Date fieldDate = sdf.parse(str);
                    return new Date(fieldDate.getTime());
                } catch (ParseException e) {
                    throw new DataParseException(String.format(message, str, type), e);
                }
            }
            if ("Timestamp".equalsIgnoreCase(type) || "java.sql.Timestamp".equalsIgnoreCase(type)) {
                if (str.length() == 10){
                    str = str + " 00:00:00";}
                if (format == null || format.length() == 0)
                    try {
                        return Timestamp.valueOf(str);
                    } catch (Exception e) {
                        try {
                            DateFormat df = null;
                            if (locale != null){
                                df = DateFormat.getDateTimeInstance(3, 3, locale);}
                            else{
                                df = DateFormat.getDateTimeInstance(3, 3);}
                            java.util.Date fieldDate = df.parse(str);
                            return new Timestamp(fieldDate.getTime());
                        } catch (ParseException e1) {
                            throw new DataParseException(String.format(message, str, type), e);
                        }
                    }
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat(format);
                    java.util.Date fieldDate = sdf.parse(str);
                    return new Timestamp(fieldDate.getTime());
                } catch (ParseException e) {
                    throw new DataParseException(String.format(message, str, type), e);
                }
            } else {
                throw new DataParseException(String.format(support, fromType, type));
            }
        }
        if (value instanceof BigDecimal) {
            fromType = "BigDecimal";
            BigDecimal bigD = (BigDecimal) value;
            if ("String".equalsIgnoreCase(type) || "java.lang.String".equalsIgnoreCase(type))
                return getNf(locale).format(bigD.doubleValue());
            if ("BigDecimal".equalsIgnoreCase(type)
                    || "java.math.BigDecimal".equalsIgnoreCase(type))
                return value;
            if ("Double".equalsIgnoreCase(type) || "java.lang.Double".equalsIgnoreCase(type))
                return new Double(bigD.doubleValue());
            if ("Float".equalsIgnoreCase(type) || "java.lang.Float".equalsIgnoreCase(type))
                return new Float(bigD.floatValue());
            if ("Long".equalsIgnoreCase(type) || "java.lang.Long".equalsIgnoreCase(type))
                return new Long(Math.round(bigD.doubleValue()));
            if ("Integer".equals(type) || "java.lang.Integer".equalsIgnoreCase(type))
                return new Integer((int) Math.round(bigD.doubleValue()));
            else
                throw new DataParseException(String.format(support, fromType, type));
        }
        if (value instanceof Double) {
            fromType = "Double";
            Double dbl = (Double) value;
            if ("String".equalsIgnoreCase(type) || "java.lang.String".equalsIgnoreCase(type))
                return getNf(locale).format(dbl.doubleValue());
            if ("Double".equalsIgnoreCase(type) || "java.lang.Double".equalsIgnoreCase(type))
                return value;
            if ("Float".equalsIgnoreCase(type) || "java.lang.Float".equalsIgnoreCase(type))
                return new Float(dbl.floatValue());
            if ("Long".equalsIgnoreCase(type) || "java.lang.Long".equalsIgnoreCase(type))
                return new Long(Math.round(dbl.doubleValue()));
            if ("Integer".equalsIgnoreCase(type) || "java.lang.Integer".equalsIgnoreCase(type))
                return new Integer((int) Math.round(dbl.doubleValue()));
            if ("BigDecimal".equalsIgnoreCase(type)
                    || "java.math.BigDecimal".equalsIgnoreCase(type))
                return new BigDecimal(dbl.toString());
            else
                throw new DataParseException(String.format(support, fromType, type));
        }
        if (value instanceof Float) {
            fromType = "Float";
            Float flt = (Float) value;
            if ("String".equalsIgnoreCase(type) || "java.lang.String".equalsIgnoreCase(type))
                return getNf(locale).format(flt.doubleValue());
            if ("BigDecimal".equalsIgnoreCase(type)
                    || "java.math.BigDecimal".equalsIgnoreCase(type))
                return new BigDecimal(flt.doubleValue());
            if ("Double".equalsIgnoreCase(type) || "java.lang.Double".equalsIgnoreCase(type))
                return new Double(flt.doubleValue());
            if ("Float".equalsIgnoreCase(type) || "java.lang.Float".equalsIgnoreCase(type))
                return value;
            if ("Long".equalsIgnoreCase(type) || "java.lang.Long".equalsIgnoreCase(type))
                return new Long(Math.round(flt.doubleValue()));
            if ("Integer".equalsIgnoreCase(type) || "java.lang.Integer".equalsIgnoreCase(type))
                return new Integer((int) Math.round(flt.doubleValue()));
            else
                throw new DataParseException(String.format(support, fromType, type));
        }
        if (value instanceof Long) {
            fromType = "Long";
            Long lng = (Long) value;
            if ("String".equalsIgnoreCase(type) || "java.lang.String".equalsIgnoreCase(type))
                return getNf(locale).format(lng.longValue());
            if ("Double".equalsIgnoreCase(type) || "java.lang.Double".equalsIgnoreCase(type))
                return new Double(lng.doubleValue());
            if ("Float".equalsIgnoreCase(type) || "java.lang.Float".equalsIgnoreCase(type))
                return new Float(lng.floatValue());
            if ("BigDecimal".equalsIgnoreCase(type)
                    || "java.math.BigDecimal".equalsIgnoreCase(type))
                return new BigDecimal(lng.toString());
            if ("Long".equalsIgnoreCase(type) || "java.lang.Long".equalsIgnoreCase(type))
                return value;
            if ("Integer".equalsIgnoreCase(type) || "java.lang.Integer".equalsIgnoreCase(type))
                return new Integer(lng.intValue());
            if ("Date".equalsIgnoreCase(type) || "java.util.Date".equalsIgnoreCase(type))
                return new java.util.Date(lng);
            if ("java.sql.Date".equalsIgnoreCase(type))
                return new Date(lng);
            if ("Time".equalsIgnoreCase(type) || "java.sql.Time".equalsIgnoreCase(type))
                return new Time(lng);
            if ("Timestamp".equalsIgnoreCase(type) || "java.sql.Timestamp".equalsIgnoreCase(type))
                return new Timestamp(lng);
            else
                throw new DataParseException(String.format(support, fromType, type));
        }
        if (value instanceof Integer) {
            fromType = "Integer";
            Integer intgr = (Integer) value;
            if ("String".equalsIgnoreCase(type) || "java.lang.String".equalsIgnoreCase(type))
                return getNf(locale).format(intgr.longValue());
            if ("Double".equalsIgnoreCase(type) || "java.lang.Double".equalsIgnoreCase(type))
                return new Double(intgr.doubleValue());
            if ("Float".equalsIgnoreCase(type) || "java.lang.Float".equalsIgnoreCase(type))
                return new Float(intgr.floatValue());
            if ("BigDecimal".equalsIgnoreCase(type)
                    || "java.math.BigDecimal".equalsIgnoreCase(type)) {
                String str = intgr.toString();
                BigDecimal retBig = new BigDecimal(intgr.doubleValue());
                int iscale = str.indexOf(".");
                int keylen = str.length();
                if (iscale > -1) {
                    iscale = keylen - (iscale + 1);
                    return retBig.setScale(iscale, 5);
                } else {
                    return retBig.setScale(0, 5);
                }
            }
            if ("Long".equalsIgnoreCase(type) || "java.lang.Long".equalsIgnoreCase(type))
                return new Long(intgr.longValue());
            if ("Integer".equalsIgnoreCase(type) || "java.lang.Integer".equalsIgnoreCase(type))
                return value;
            else
                throw new DataParseException(String.format(support, fromType, type));
        }
        if (value instanceof java.util.Date) {
            fromType = "Date";
            java.util.Date dte = (java.util.Date) value;
            if ("String".equalsIgnoreCase(type) || "java.lang.String".equalsIgnoreCase(type))
                if (format == null || format.length() == 0) {
                    return dte.toString();
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat(format);
                    return sdf.format(dte);
                }
            if ("Date".equalsIgnoreCase(type) || "java.util.Date".equalsIgnoreCase(type))
                return value;
            if ("java.sql.Date".equalsIgnoreCase(type))
                return new Date(dte.getTime());
            if ("Time".equalsIgnoreCase(type) || "java.sql.Time".equalsIgnoreCase(type))
                return new Time(dte.getTime());
            if ("Timestamp".equalsIgnoreCase(type) || "java.sql.Timestamp".equalsIgnoreCase(type))
                return new Timestamp(dte.getTime());
            else
                throw new DataParseException(String.format(support, fromType, type));
        }
        if (value instanceof Date) {
            fromType = "Date";
            Date dte = (Date) value;
            if ("String".equalsIgnoreCase(type) || "java.lang.String".equalsIgnoreCase(type))
                if (format == null || format.length() == 0) {
                    return dte.toString();
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat(format);
                    return sdf.format(new java.util.Date(dte.getTime()));
                }
            if ("Date".equalsIgnoreCase(type) || "java.util.Date".equalsIgnoreCase(type))
                return new java.util.Date(dte.getTime());
            if ("java.sql.Date".equalsIgnoreCase(type))
                return value;
            if ("Time".equalsIgnoreCase(type) || "java.sql.Time".equalsIgnoreCase(type))
                throw new DataParseException("Conversion from " + fromType + " to " + type
                        + " not currently supported");
            if ("Timestamp".equalsIgnoreCase(type) || "java.sql.Timestamp".equalsIgnoreCase(type))
                return new Timestamp(dte.getTime());
            else
                throw new DataParseException(String.format(support, fromType, type));
        }
        if (value instanceof Timestamp) {
            fromType = "Timestamp";
            Timestamp tme = (Timestamp) value;
            if ("String".equalsIgnoreCase(type) || "java.lang.String".equalsIgnoreCase(type))
                if (format == null || format.length() == 0) {
                    return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(tme).toString();
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat(format);
                    return sdf.format(new java.util.Date(tme.getTime()));
                }
            if ("Date".equalsIgnoreCase(type) || "java.util.Date".equalsIgnoreCase(type))
                return new java.util.Date(tme.getTime());
            if ("java.sql.Date".equalsIgnoreCase(type))
                return new Date(tme.getTime());
            if ("Time".equalsIgnoreCase(type) || "java.sql.Time".equalsIgnoreCase(type))
                return new Time(tme.getTime());
            if ("Timestamp".equalsIgnoreCase(type) || "java.sql.Timestamp".equalsIgnoreCase(type))
                return value;
            else
                throw new DataParseException(String.format(support, fromType, type));
        }
        if (value instanceof Boolean) {
            fromType = "Boolean";
            Boolean bol = (Boolean) value;
            if ("Boolean".equalsIgnoreCase(type) || "java.lang.Boolean".equalsIgnoreCase(type))
                return bol;
            if ("String".equalsIgnoreCase(type) || "java.lang.String".equalsIgnoreCase(type))
                return bol.toString();
            if ("Integer".equalsIgnoreCase(type) || "java.lang.Integer".equalsIgnoreCase(type)) {
                if (bol.booleanValue())
                    return new Integer(1);
                else
                    return new Integer(0);
            } else {
                throw new DataParseException(String.format(support, fromType, type));
            }
        }
        if ("String".equalsIgnoreCase(type) || "java.lang.String".equalsIgnoreCase(type))
            return value.toString();
        else
            throw new DataParseException(String.format(support, fromType, type));
    }

    private static NumberFormat getNf(Locale locale) {
        NumberFormat nf = null;
        if (locale == null)
            nf = NumberFormat.getNumberInstance();
        else
            nf = NumberFormat.getNumberInstance(locale);
        nf.setGroupingUsed(false);
        return nf;
    }

    /**
     * 转换为布尔值
     */
    public static Boolean convertToBoolean(Object obj) throws DataParseException {
        return (Boolean) convert(obj, "Boolean", null);
    }

    /**
     * 转换为整型
     */
    public static Integer convertToInteger(Object obj) throws DataParseException {
        return (Integer) convert(obj, "Integer", null);
    }

    /**
     * 转换为字符串
     */
    public static String convertToString(Object obj) throws DataParseException {
        return (String) convert(obj, "String", null);
    }

    /**
     * 转换为字符串
     */
    public static String convertToString(Object obj, String defaultValue) throws DataParseException {
        Object s = convert(obj, "String", null);
        if (s != null)
            return (String) s;
        else
            return "";
    }

    /**
     * 转换为长整型
     */
    public static Long convertToLong(Object obj) throws DataParseException {
        return (Long) convert(obj, "Long", null);
    }

    /**
     * 转换为双精度型
     */
    public static Double convertToDouble(Object obj) throws DataParseException {
        return (Double) convert(obj, "Double", null);
    }

    /**
     * 转换为浮点型
     */
    public static Double convertToFloat(Object obj) throws DataParseException {
        return (Double) convert(obj, "Float", null);
    }

    /**
     * 转换为数值型
     */
    public static BigDecimal convertToBigDecimal(Object obj, int scale) throws DataParseException {
        return ((BigDecimal) convert(obj, "BigDecimal", null)).setScale(scale, 5);
    }

    /**
     * 转换为日期型
     */
    public static java.util.Date convertToDate(Object obj, String format) throws DataParseException {
        return (java.util.Date) convert(obj, "Date", format);
    }

    /**
     * 转换为日期型
     */
    public static Date convertToSqlDate(Object obj, String format) throws DataParseException {
        return (Date) convert(obj, "java.sql.Date", format);
    }

    /**
     * 转换为日期型
     */
    public static Timestamp convertToTimestamp(Object obj, String format) throws DataParseException {
        return (Timestamp) convert(obj, "Timestamp", format);
    }
}

class DataParseException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -2687839831605219566L;

    public DataParseException() {
    }

    public DataParseException(Throwable ex) {
        super(ex);
    }

    public DataParseException(String message) {
        super(message);
    }

    public DataParseException(String message, Throwable ex) {
        super(message, ex);
    }
}