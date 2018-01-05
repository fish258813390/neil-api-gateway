package com.neil.api.gateway.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by neil on 2017/10/27 0027
 */
@RequestMapping(value = "/usercenter/main")
@Controller
public class MainController {

    @RequestMapping(value = "/desktop")
    public String desktop(HttpServletRequest request, HttpServletResponse response) {
//        RequestHead requestHead = ApplicationContextData.requestHead(request);
//        Operator operator = ApplicationContextData.getOperatorSession(request);
//        if (operator == null) {
//            Operator operatorTemp = ApplicationContextData.getOperatorSessionTemp(request);
//            request.setAttribute("redirectUrl", appConfigForUsercenter.platformLoginPageUrl);
//            if (operatorTemp == null) {
//                request.setAttribute("loginError", "登录超时，请重新登录");
//                return "usercenter/main/loginError";
//            } else {
//                if (operatorTemp.getLoginType() == 0) {
//                    ApplicationContextData.setOperatorSession(request, operatorTemp);
//
//                } else if (operatorTemp.getLoginType() == 1) {
//                    return "usercenter/main/login";
//                } else {
//                    request.setAttribute("loginError", "登录验证类型未定义，无法登录，请联系客服");
//                    return "usercenter/main/loginError";
//                }
//            }
//        }
//
//        request.setAttribute("operator", ApplicationContextData.getOperatorSession(request));
//        try {
//            List<Menu> list = remoteMenuService.queryModuleByParentIdOperatorId(
//                    requestHead, 0, ApplicationContextData.getOperatorSession(request).getId());
//            request.setAttribute("menus", list);
//
//            List<Menu> listMenu = remoteMenuService.queryByOperatorId(requestHead, ApplicationContextData.getOperatorSession(request).getId());
//            ApplicationContextData.setOperatorMenuUrl(request, listMenu);
//            Agent agent = remoteAgentService.queryById(requestHead, ApplicationContextData.getOperatorSession(request).getAgentId());
//            ApplicationContextData.setAgentSession(request, agent);
//        } catch (BusinessException e) {
//            log.error("获取用户菜单异常：", e);
//        } catch (Exception e) {
//            log.error("获取用户菜单异常：", e);
//        }

        return "usercenter/main/desktop";
    }

}
