package com.neil.api.gateway.base;

import com.neil.api.gateway.commons.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by neil on 2017/10/20 0020
 */
@Component
public class BaseController {

    protected static final Logger log = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    protected AppConfig appConfig;

}
