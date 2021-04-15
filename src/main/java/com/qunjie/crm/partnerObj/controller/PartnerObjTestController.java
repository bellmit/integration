package com.qunjie.crm.partnerObj.controller;

import com.qunjie.crm.account.model.AccountModel;
import com.qunjie.crm.account.service.AccountService;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.partnerObj.model.PartnerObjModel;
import com.qunjie.crm.partnerObj.service.PartnerObjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.oacrmbridge.crm.account.controller.TestController
 *
 * @author whs
 * Date:   2021/1/14  11:05
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@RestController
@RequestMapping("/partnerObj")
public class PartnerObjTestController {

    @Autowired
    PartnerObjService partnerObjService;
    @GetMapping("query")
    public PartnerObjModel test(String name) throws AccessTokenException {
        List<PartnerObjModel> partnerObjModels = partnerObjService.queryPartnerObjByName(name);
        return CollectionUtils.isEmpty(partnerObjModels) ? null : partnerObjModels.get(0);
    }
}
