package neil.api.gateway.utils;

import com.neil.commons.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DirectoryUtils {

    /**
     * 在当前目录下创建当日文件夹并返回文件夹路径
     */
    public static String createDir(String path) throws BusinessException {
        if (StringUtils.isEmpty(path)) {
            throw new BusinessException("传入参数异常!");
        }
        SimpleDateFormat df = new SimpleDateFormat(DateFormat.SHORT_DATE);//设置日期格式
        String ret = path + "/" + df.format(new Date());
        String retEncode = null;
        try {
            String encoding = System.getProperty("file.encoding");
            retEncode = new String(ret.getBytes("utf-8"), encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        File file = new File(retEncode);
        if (!file.exists()) {
            file.mkdirs();
        }
        return retEncode;
    }
}
