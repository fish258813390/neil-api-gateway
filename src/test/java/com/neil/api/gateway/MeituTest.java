package com.neil.api.gateway;

import com.neil.commons.dto.RequestHead;
import junit.framework.TestCase;
import neil.api.gateway.http.HttpUtil;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neil on 2017/10/21 0021
 */
public class MeituTest extends TestCase {

    private static final Logger log = LoggerFactory.getLogger(MeituTest.class);

    public static void main(String[] args) {

//        List<NameValuePair> nvps = new Li

        String baseUrl = "https://route.showapi.com/738-1";

        // 表单提交
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("n", "10"));
        params.add(new BasicNameValuePair("showapi_appid", "47526"));
        params.add(new BasicNameValuePair("showapi_sign", "c05733048bb9427f8ae9b8ede645ff23"));

//        params.add(new NameValuePair("showapi_appid","47526"));
//        params.add(new NameValuePair("showapi_sign","c05733048bb9427f8ae9b8ede645ff23"));
//        params.add(new NameValuePair("page","10"));


//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("showapi_appid", "47526");
//        map.put("showapi_sign", "c05733048bb9427f8ae9b8ede645ff23");
//        map.put("page", 10);
//        String result = HttpUtil.httpPost("https://route.showapi.com/738-1", JSON.toJSONString(map));
//        String result =  HttpUtil.httpPost("https://route.showapi.com/738-1",params);

//        Map<String, String> map = new HashMap<String, String>();
//        map.put("showapi_appid", "47526");
//        map.put("n", 10 +"");
//        map.put("showapi_sign", "c05733048bb9427f8ae9b8ede645ff23");
        String result = HttpUtil.httpPost("https://route.showapi.com/738-1", params);
        int n = 15;

//        String result = HttpUtil.httpGet("http://route.showapi.com/738-1?showapi_appid=47526" + "&n=" + n + "&showapi_sign=c05733048bb9427f8ae9b8ede645ff23");

        log.info("返回数据：" + result);

    }


    public static RequestHead sendHead() {
        return RequestHead.getRequestHead();
    }

}
