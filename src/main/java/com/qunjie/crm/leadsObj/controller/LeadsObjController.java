package com.qunjie.crm.leadsObj.controller;

import com.alibaba.fastjson.JSONObject;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.leadsObj.service.LeadsObjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.leadsObj.controller.LeadsObjController
 *
 * @author whs
 * Date:   2021/3/11  11:25
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@RestController
@RequestMapping("/crm/leadsObj")
public class LeadsObjController {

    @Autowired
    LeadsObjService leadsObjService;

    @RequestMapping("/queryByTel")
    public JSONObject queryLeadsObj(String tel) throws AccessTokenException {
        return leadsObjService.queryLeadsObj(tel);
    }
}
