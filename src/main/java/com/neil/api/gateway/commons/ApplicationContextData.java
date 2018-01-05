package com.neil.api.gateway.commons;

import com.neil.commons.dto.RequestHead;
import com.neil.commons.exception.BusinessException;
import com.neil.commons.util.UUID32;
import com.neil.core.util.MemcachedClient;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.spel.ast.Operator;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * Created by aladdin on 2015-12-18.
 */
public class ApplicationContextData {
    private static final Logger log = LoggerFactory
            .getLogger(ApplicationContextData.class);

    private static String getHeader(HttpServletRequest request, String headName) {
        String value = request.getHeader(headName);
        return !StringUtils.isBlank(value) && !"unknown".equalsIgnoreCase(value) ? value : "";
    }

    public static String getIP() throws Exception {
        InetAddress addr = InetAddress.getLocalHost();
        return addr.getHostAddress().toString();
    }

    public static String getRemoteAddrIp(HttpServletRequest request) {
        String ipFromNginx = getHeader(request, "X-Real-IP");
        return StringUtils.isEmpty(ipFromNginx) ? request.getRemoteAddr()
                : ipFromNginx;
    }

//    public static void setUserInfo(String id, UserInfo userInfo)
//            throws Exception {
//        MemcachedClient.getClient().set(id, 60 * 60 * 24, userInfo);
//    }
//
//    public static void removeLoginUser(String id) throws Exception {
//        MemcachedClient.getClient().delete(id);
//    }
//
//    public static UserInfo initUserInfo(HttpServletRequest request) throws Exception {
//        ClientInfo clientInfo = new ClientInfo();
//        clientInfo.setClientIp(getRemoteAddrIp(request));
//        UserInfo userInfo = new UserInfo();
//        userInfo.setClientInfo(clientInfo);
//        return userInfo;
//    }
//
//    public static UserInfo getUserInfo(String id) {
//        try {
//            Object obj = MemcachedClient.getClient().get(id);
//            return (UserInfo) obj;
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    public static UserInfo getTestUserInfo() {
//        UserInfo userInfo = new UserInfo();
//        KeyInfo keyInfo = new KeyInfo();
//        keyInfo.setDesKey("0175070A77020F05");
//        keyInfo.setSignKey("7872760A71757E7701050703720F0373");
//        userInfo.setKeyInfo(keyInfo);
//        return userInfo;
//    }

//    public static Head setSocketHead(RequestHead requestHead, String component,String service) {
//        Head head = null;
//        try {
//            head = new Head();
//            head.setComponent(component);
//            head.setService(service);
//            head.setAgentId(Long.parseLong(requestHead.getAgentId()));
//            head.setOperatorName(requestHead.getUserAccount());
//            // if (getOperator(request) == null) {
//            // head.setAgentId(-1);
//            // head.setOperatorId(-1);
//            // head.setOperatorName("no session");
//            // } else {
//            // head.setAgentId(getAgent(request).getId());
//            // head.setOperatorId(getOperator(request).getId());
//            // head.setOperatorName(getOperator(request).getOperatorName());
//            // }
//            head.setRequestId(UUID.randomUUID().toString());
//            head.setIp(getIP());
//            head.setClientIp(requestHead.getRemoteIp());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return head;
//    }

//    public static Head getOldHead(String id, String component, String service)
//            throws Exception {
//        Head head = new Head();
//        head.setComponent(component);
//        head.setService(service);
//        UserInfo userInfo = ApplicationContextData.getUserInfo(id);
//        Operator operator = userInfo.getOperator();
//        if (operator == null) {
//            head.setAgentId(-1);
//            head.setOperatorId(-1);
//            head.setOperatorName("no session");
//        } else {
//            head.setAgentId(userInfo.getOperator().getAgentId());
//            head.setOperatorId(operator.getId());
//            head.setOperatorName(operator.getOperatorName());
//        }
//        head.setRequestId(UUID.randomUUID().toString());
//        head.setIp(getIP());
//        head.setClientIp(userInfo.getClientInfo().getClientIp());
//        return head;
//    }

//    public static RequestHead requestHead(String id) {
//        UserInfo userInfo = ApplicationContextData.getUserInfo(id);
//        return requestHead(userInfo);
//    }

    public static RequestHead requestHead() {
        try {
            RequestHead head = new RequestHead();
            head.setAgentId("-1");
            head.setUserId("-1");
            head.setUserAccount("SYSTEM");
            head.setUserRealName("系统");
            head.setLocalIp(getIP());
//            head.setRemoteIp(userInfo.getClientInfo().getClientIp());
            head.setRequestId("api-" + UUID32.getUUID());
            head.setTenantId("yolly");
            return head;
        } catch (Exception e) {
            log.error("构建RequestHead异常：", e);
            return null;
        }
    }

    public static void validateRequestHead(RequestHead requestHead)
            throws BusinessException {
        if (requestHead == null) {
            throw new BusinessException("构建请求头数据异常");
        }
    }

    /**
     * 存储短信验证码信息,smsMap中包括验证码和发送时间
     *
     * @param cacheKey
     * @param smsMap
     */
//    public static void saveSmsInfoByKey(String cacheKey, Map<String, Long> smsMap) {
//        try {
//            MemcachedClient.setWithNamespace(SMS_NAME_SPACE, cacheKey, 0, smsMap);
//        } catch (Exception e) {
//            log.error("保存短信信息异常");
//        }
//    }

//    public static void saveSmsInfoByKey(String cacheKey, Long smsCode, Long smsSendTime) {
//        try {
//            Map smsMap = new HashedMap();
//            smsMap.put(SMS_CODE, smsCode);
//            smsMap.put(SMS_SEND_TIME, smsSendTime);
//            MemcachedClient.setWithNamespace(SMS_NAME_SPACE, cacheKey, 0, smsMap);
//        } catch (Exception e) {
//            log.error("保存短信信息异常");
//        }
//    }

    /**
     * 查询缓存中的短信信息
     *
     * @param cacheKey 缓存key
     * @param smsKey   对象中的key,
     * @return
     */
//    public static Long getSmsBySmsKey(String cacheKey, String smsKey) {
//        try {
//            Map<String, Long> map = (Map<String, Long>) MemcachedClient.getWithNamespace(SMS_NAME_SPACE, cacheKey);
//            System.out.println(map.get(smsKey));
//            return map.get(smsKey);
//        } catch (Exception e) {
//            log.error("未发送验证码,验证码在缓存中不存在");
//            return 9223372036854775806L;
//        }
//    }

    // 缓存操作员对象
//    public static void saveLoginAccountByKey(String cacheKey, String loginAccount, Long smsSendTime) {
//        try {
//            Map loginAcountMap = new HashedMap();
//            loginAcountMap.put(OPERATOR_ACCOUNT_KEY, loginAccount);
//            loginAcountMap.put(SMS_SEND_TIME, smsSendTime);
//            MemcachedClient.setWithNamespace(OPERATOR_ACCOUNT_NAME_SPACE, cacheKey, 60 * 5, loginAcountMap);
//        } catch (Exception e) {
//            log.error("保存操作员账号异常");
//        }
//    }

    // 查询操作员对象
//    public static String getLoginAccountKey(String cacheKey, String loginAcountKey) {
//        try {
//            Map<String, String> map = (Map<String, String>) MemcachedClient
//                    .getWithNamespace(OPERATOR_ACCOUNT_NAME_SPACE, cacheKey);
//            log.info("获取缓存中的操作员登录账号：" + map.get(loginAcountKey));
//            return map.get(loginAcountKey);
//        } catch (Exception e) {
//            log.error("未保存操作员账号,验证码不存在操作员账号");
//            return "";
//        }
//    }

    /**
     * 删除缓存中的短信信息
     *
     * @param cacheKey
     * @return
     */
//    public static boolean deleteSmsInfoBySmsKey(String cacheKey) {
//        try {
//            MemcachedClient.deleteWithNamespace(SMS_NAME_SPACE, cacheKey);
//            return true;
//        } catch (Exception e) {
//            log.error("删除缓存中的短信信息异常");
//        }
//        return false;
//    }


    /**
     * 通联存款银行列表
     *
     * @return
     */
    public static List<String> getAlinpayBankList() {
        List<String> list = new ArrayList<>();
        list.add("{\"bankKey\":\"01030000\",\"bankName\":\"农业银行\"}");
        list.add("{\"bankKey\":\"01040000\",\"bankName\":\"中国银行\"}");
        list.add("{\"bankKey\":\"01050000\",\"bankName\":\"建设银行\"}");
        list.add("{\"bankKey\":\"01000000\",\"bankName\":\"邮政储蓄银行\"}");
        list.add("{\"bankKey\":\"03080000\",\"bankName\":\"招商银行\"}");
        list.add("{\"bankKey\":\"03030000\",\"bankName\":\"光大银行\"}");
        list.add("{\"bankKey\":\"63100000\",\"bankName\":\"浦发银行\"}");
        list.add("{\"bankKey\":\"03090000\",\"bankName\":\"兴业银行\"}");
        list.add("{\"bankKey\":\"03040000\",\"bankName\":\"华夏银行\"}");
        list.add("{\"bankKey\":\"14105200\",\"bankName\":\"湖北农信社\"}");
        return list;
    }


    /**
     * 线下存款银行列表
     *
     * @return
     */
    public static List<String> getLineBankList() {
        List<String> list = new ArrayList<>();
        //  new
        list.add("{\"bankId\":\"1\",\"bankName\":\"建设银行\",\"customerName\":\"郭芬\",\"bankCard\":\"6217002870064278201\"}");
        list.add("{\"bankId\":\"3\",\"bankName\":\"工商银行\",\"customerName\":\"郭芬\",\"bankCard\":\"6212253202011063691\"}");
        list.add("{\"bankId\":\"4\",\"bankName\":\"农业银行\",\"customerName\":\"郭芬\",\"bankCard\":\"6228480059718640671\"}");
        list.add("{\"bankId\":\"2\",\"bankName\":\"邮政\",\"customerName\":\"郭芬\",\"bankCard\":\"6217995200162368192\"}");
        list.add("{\"bankId\":\"1\",\"bankName\":\"建设银行\",\"customerName\":\"武汉永乐生活通讯有限公司\",\"bankCard\":\"42001206308053007263\"}");
        list.add("{\"bankId\":\"5\",\"bankName\":\"招行\",\"customerName\":\"武汉永乐生活通讯有限公司\",\"bankCard\":\"127908022610102\"}");
        list.add("{\"bankId\":\"4\",\"bankName\":\"农业银行\",\"customerName\":\"武汉永乐生活通讯有限公司\",\"bankCard\":\"17080101040011610\"}");
        list.add("{\"bankId\":\"6\",\"bankName\":\"上海浦东发展银行股份有限公司武汉分行营业部\",\"customerName\":\"武汉永乐云商电子商务有限公司\",\"bankCard\":\"70160155200001906\"}");
        return list;
    }



    public static List<String> getCreditBankList() {
        List<String> list = new ArrayList<>();
        list.add("{\"bankId\":\"1\",\"bankName\":\"农业银行\"}");
        list.add("{\"bankId\":\"2\",\"bankName\":\"交通银行\"}");
        list.add("{\"bankId\":\"3\",\"bankName\":\"建设银行\"}");
        list.add("{\"bankId\":\"4\",\"bankName\":\"光大银行\"}");
        list.add("{\"bankId\":\"5\",\"bankName\":\"招商银行\"}");
        list.add("{\"bankId\":\"6\",\"bankName\":\"中信银行\"}");
        list.add("{\"bankId\":\"7\",\"bankName\":\"工商银行\"}");
        list.add("{\"bankId\":\"8\",\"bankName\":\"浦发银行\"}");
        list.add("{\"bankId\":\"9\",\"bankName\":\"民生银行\"}");
        list.add("{\"bankId\":\"10\",\"bankName\":\"广发银行\"}");
        list.add("{\"bankId\":\"11\",\"bankName\":\"兴业银行\"}");
        list.add("{\"bankId\":\"11\",\"bankName\":\"中国银行\"}");
        list.add("{\"bankId\":\"11\",\"bankName\":\"邮政储蓄银行\"}");
        list.add("{\"bankId\":\"11\",\"bankName\":\"华夏银行\"}");
        list.add("{\"bankId\":\"11\",\"bankName\":\"平安银行\"}");
        list.add("{\"bankId\":\"11\",\"bankName\":\"渤海银行\"}");
        list.add("{\"bankId\":\"11\",\"bankName\":\"徽商银行\"}");
        list.add("{\"bankId\":\"11\",\"bankName\":\"汉口银行\"}");
        list.add("{\"bankId\":\"11\",\"bankName\":\"湖北银行\"}");
        list.add("{\"bankId\":\"11\",\"bankName\":\"湖北农信社\"}");
        list.add("{\"bankId\":\"11\",\"bankName\":\"武汉农村商业银行\"}");
        return list;
    }

    // 湖北公库 号段
    public static List<String> getSectionNoList() {
        List<String> list = new ArrayList<>();
        list.add("{\"sectionNoId\":\"\",\"sectionNoName\":\"所有\"}");
        list.add("{\"sectionNoId\":\"186\",\"sectionNoName\":\"186\"}");
        list.add("{\"sectionNoId\":\"185\",\"sectionNoName\":\"185\"}");
        list.add("{\"sectionNoId\":\"155\",\"sectionNoName\":\"155\"}");
        list.add("{\"sectionNoId\":\"156\",\"sectionNoName\":\"156\"}");
        list.add("{\"sectionNoId\":\"130\",\"sectionNoName\":\"130\"}");
        list.add("{\"sectionNoId\":\"131\",\"sectionNoName\":\"131\"}");
        list.add("{\"sectionNoId\":\"132\",\"sectionNoName\":\"132\"}");
        return list;
    }

    // 获取广告位
    public static List<String> getHnBannerList() {
        List<String> list = new ArrayList<>();
        list.add("{\"uri\":\"http://www.yolly.net.cn/hunan-card-banner/banner1.jpg\",\"title\":\"免费流量玩直播，快来抢购..\"}");
        return list;
    }

}
