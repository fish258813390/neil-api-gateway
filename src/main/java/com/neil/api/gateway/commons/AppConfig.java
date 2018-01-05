package com.neil.api.gateway.commons;

import com.ryantenney.zookeeper.spring.ZooKeeper;
import org.springframework.stereotype.Component;

/**
 * Created by neil on 2017/10/20
 */
@Component
public class AppConfig {

    /**
     * 上传崩溃日志路径
     */
    public static String UPDATE_CRASH_LOG_PATH = "/neil/tomcat/logs/yolly-api-gateway/crash/";


    /**
     * 话费订单分页查询方式-1.话费订单 2.代理商话费订单
     */
    @ZooKeeper("/neil/neil-api/conf/queryPhoneOrderType")
    public int queryPhoneOrderType;

}

