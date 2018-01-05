package com.neil.api.gateway.app.usercenter;

import com.neil.api.gateway.base.BaseUserController;
import com.neil.api.gateway.commons.ApplicationContextData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by neil on 2017/11/17 0017
 */
@Controller("userController")
@RequestMapping("/main/usercenter/user/")
public class UserController extends BaseUserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @ResponseBody
    @RequestMapping(value = "login")
    public String loginApp(HttpServletRequest request, String loginName, String loginPassword) {
        return loginApp(ApplicationContextData.requestHead(), loginName, loginPassword);
    }

}
