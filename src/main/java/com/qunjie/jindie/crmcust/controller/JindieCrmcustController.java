package com.qunjie.jindie.crmcust.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import kingdee.bos.webapi.client.K3CloudApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.jindie.crmcust.controller.JindieCrmcustController
 *
 * @author whs
 * Date:   2021/1/27  13:36
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@RestController
@RequestMapping("/jindie/crmcust")
public class JindieCrmcustController {
    @Autowired
    K3CloudApiClient k3CloudApiClient;
    @Value("${k3cloud.url}")
    private String K3CloudURL ;
    @Value("${k3cloud.dbId}")
    private String dbId ;
    @Value("${k3cloud.uid}")
    private String uid ;
    @Value("${k3cloud.pwd}")
    private String pwd;
    static int lang = 2052;
    static String sFormId = "BD_Customer";

    @GetMapping("view")
    public String view(String Number) throws Exception {
        Boolean result = k3CloudApiClient.login(dbId, uid, pwd, lang);
        if (result) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("CreateOrgId", 0);
            jsonObject.put("Number", Number);
            String view = k3CloudApiClient.view(sFormId, new Gson().toJson(jsonObject));
            return view;
        }
        return null;
    }
}
