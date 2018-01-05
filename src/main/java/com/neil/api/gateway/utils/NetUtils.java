package com.neil.api.gateway.utils;

import com.neil.commons.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class NetUtils {

    private static final Logger log = LoggerFactory.getLogger(NetUtils.class);

    public static List<String> getIpAddresses() {
        try {
            List<String> ipList = new ArrayList<String>();
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            int i = 0;
            StringBuffer logStr = new StringBuffer();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = allNetInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    ip = addresses.nextElement();
                    if (ip != null && ip instanceof Inet4Address) {
                        ipList.add(ip.getHostAddress());
                        i++;
                        logStr.append("localIP " + i + " = [" + ip.getHostAddress() + "],");
                    }
                }
            }
            log.info(logStr.toString());
            return ipList;
        } catch (SocketException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean checkLocalHasIp(String ip) throws BusinessException {
        if (StringUtils.isEmpty(ip)) {
            throw new BusinessException("传入参数异常!");
        }
        List<String> ipList = getIpAddresses();
        if (ipList != null && ipList.size() > 0) {
            for (int i = 0; i < ipList.size(); i++) {
                if (ip.equals(ipList.get(i))) {
                    return true;
                }
            }
        }
        return false;
    }
}
