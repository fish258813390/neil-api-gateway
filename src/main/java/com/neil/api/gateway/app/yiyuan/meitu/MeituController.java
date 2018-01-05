package com.neil.api.gateway.app.yiyuan.meitu;


import com.neil.commons.dto.RequestHead;
import com.neil.api.gateway.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by neil on 2017/10/20 0020
 */
@Controller("meituController")
@RequestMapping("/app/yiyuan/meitu")
public class MeituController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(MeituController.class);


    protected Object getHotPictureList(RequestHead requestHead, String data) {
        String url = "http://route.showapi.com/852-1";

        return null;
    }

}
