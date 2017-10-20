package neil.api.gateway.utils;

/**
 * J开头为java中日期格式。由于java系统限制，精度只能到微秒
 * O开头为oracle中日期格式。
 * oracle日期格式秒和微秒之前的X为本地分隔符Local radix character.
 */
public class PunctuationFormat {

    // Grace style
    public static final String SingleQuotationMarks = "'";// PunctuationFormat.SingleQuotationMarks
    public static final String DoubleQuotationMarks = "\""; //  PunctuationFormat.DoubleQuotationMarks
    public static final String DOT = ".";// PunctuationFormat.DOT
    public static final String Comma = ",";// PunctuationFormat.Comma

}
