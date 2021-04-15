package com.qunjie.crm.controller;

import com.qunjie.crm.utils.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/view")
public class AppViewController {

    @Resource(name = "configuration")
    private Configuration configuration;

    @RequestMapping("/bindAccount")
    public String bindAccount(HttpSession session, Map<String, Object> model) {
        StringBuilder sb = new StringBuilder("");

        Random random = new Random();
        for (int i = 0; i < 18; i++) {
            sb.append(random.nextInt(9));
        }

        model.put("appId", configuration.getAppId());
        model.put("fsRegisterUrl", "aaa/register");
        model.put("fsLoginUrl", "aaa/login");
        model.put("fsAuthorizeUrl", configuration.getFsAuthorizeUrl());

        session.setAttribute("tempState", sb.toString());

        return "bindAccount";
    }

    @RequestMapping("/addressBook")
    public String addressBook() {
        return "addressBook";
    }

}
