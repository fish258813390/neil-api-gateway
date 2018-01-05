package com.neil.api.gateway.base;

import com.neil.commons.dto.GeneralResponse;
import com.neil.commons.dto.RequestHead;
import com.neil.integration.remote.api.user.model.User;
import com.neil.integration.remote.api.user.remote.RemoteUserOperateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by neil on 2017/11/17 0017
 */
public class BaseUserController extends BaseController {

    private static  final Logger logger = LoggerFactory.getLogger(BaseUserController.class);

    @Autowired
    private RemoteUserOperateService remoteUserOperateService;

    public String loginApp(RequestHead requestHead, String loginName, String loginPassword) {
        User user = remoteUserOperateService.login(requestHead, loginName, loginPassword);
        logger.info("用户是否存在:" + user);
        if (null != user) {
            return new GeneralResponse(true, user).toSuccessJSONString();
        }
        return new GeneralResponse(false, "用户不存在!").toSuccessJSONString();
    }

}
