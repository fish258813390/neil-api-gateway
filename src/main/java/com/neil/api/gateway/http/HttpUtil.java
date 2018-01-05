package com.neil.api.gateway.http;

import com.neil.commons.exception.BusinessException;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtil {
    private static Logger log = LoggerFactory.getLogger(HttpUtil.class);

    private static String ConnectTimeoutMessage = "上游接口地址无法访问";

    public static String httpPost(String Url) throws BusinessException {
        String html = "";
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();
            httpclient.getParams().setParameter(
                    CoreConnectionPNames.CONNECTION_TIMEOUT, 15000);
            httpclient.getParams().setParameter(
                    CoreConnectionPNames.SO_TIMEOUT, 18000);
            HttpPost httpost = new HttpPost(Url);
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            httpost.setEntity(new UrlEncodedFormEntity(nvps, "gb2312"));
            HttpResponse response = httpclient.execute(httpost);
            html = EntityUtils.toString(response.getEntity());
            EntityUtils.consume(response.getEntity());
        } catch (ConnectTimeoutException e) {
            log.error("连接超时：", e);
            throw new BusinessException(ConnectTimeoutMessage);
        } catch (Exception e) {
            log.error("操作异常：", e);
        }
        return html;
    }

    public static String httpGet(String Url) throws BusinessException {
        String html = "";
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();
            httpclient.getParams().setParameter(
                    CoreConnectionPNames.CONNECTION_TIMEOUT, 15000);
            httpclient.getParams().setParameter(
                    CoreConnectionPNames.SO_TIMEOUT, 18000);
            HttpGet httGet = new HttpGet(Url);
            HttpResponse response = httpclient.execute(httGet);
            html = EntityUtils.toString(response.getEntity());
            EntityUtils.consume(response.getEntity());
        } catch (ConnectTimeoutException e) {
            log.error("连接超时：", e);
            throw new BusinessException(ConnectTimeoutMessage);
        } catch (Exception e) {
            log.error("操作异常：", e);
        }
        return html;
    }

    public static String httpPost(String Url, List<NameValuePair> nvps)
            throws BusinessException {
        String html = "";
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();
            httpclient.getParams().setParameter(
                    CoreConnectionPNames.CONNECTION_TIMEOUT, 15000);
            httpclient.getParams().setParameter(
                    CoreConnectionPNames.SO_TIMEOUT, 18000);
            HttpPost httpost = new HttpPost(Url);
            httpost.setEntity(new UrlEncodedFormEntity(nvps, "gb2312"));
            HttpResponse response = httpclient.execute(httpost);
            html = EntityUtils.toString(response.getEntity());
            EntityUtils.consume(response.getEntity());
        } catch (ConnectTimeoutException e) {
            log.error("连接超时：", e);
            throw new BusinessException(ConnectTimeoutMessage);
        } catch (Exception e) {
            log.error("操作异常：", e);
        }
        return html;
    }


    @SuppressWarnings("deprecation")
    public static String httpPost(String Url, String body)
            throws BusinessException {
        String html = "";
        try {
            HttpClient httpClient = new HttpClient();
            PostMethod postMethod = new PostMethod(Url);
            postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                    new DefaultHttpMethodRetryHandler());
            postMethod.setRequestBody(body);
            int statusCode = httpClient.executeMethod(postMethod);
            // log.info("statusCode " + statusCode);
            if (statusCode != HttpStatus.SC_OK) {
                log.info("Method failed: " + postMethod.getStatusLine());

            }
            // 读取返回内容
            html = new String(postMethod.getResponseBodyAsString().getBytes(
                    "utf-8"));
        } catch (ConnectException e) {
            log.error("连接超时：", e);
            throw new BusinessException(ConnectTimeoutMessage);
        } catch (Exception e) {
            log.error("操作异常：", e);
        }
        return html;
    }

    /**
     * POST请求，Map形式数据
     *
     * @param url     请求地址
     * @param param   请求数据
     * @param charset 编码方式
     */
    public static String sendPost(String url, Map<String, String> param,
                                  String charset) {

        StringBuffer buffer = new StringBuffer();
        if (param != null && !param.isEmpty()) {
            for (Map.Entry<String, String> entry : param.entrySet()) {
                buffer.append(entry.getKey()).append("=")
                        .append(URLEncoder.encode(entry.getValue()))
                        .append("&");

            }
        }
        buffer.deleteCharAt(buffer.length() - 1);
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(buffer);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(), charset));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            log.error("发送POST请求出现异常", e);
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                log.error("关闭发送POST请求输入输出流出现异常", ex);
            }
        }
        return result;
    }
}
