package com.qunjie.crm.account.controller;

import com.qunjie.crm.account.model.AccountModel;
import com.qunjie.crm.account.service.AccountService;
import com.qunjie.crm.controller.CrmcustController;
import com.qunjie.crm.exception.AccessTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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
@RequestMapping("/account")
public class AccountTestController {

    @Autowired
    AccountService accountService;
    @GetMapping("query")
    public AccountModel test(String name) throws AccessTokenException {
        List<AccountModel> productModels = accountService.queryAccountByName(name);
        return CollectionUtils.isEmpty(productModels) ? null : productModels.get(0);
    }

    @Autowired
    CrmcustController crmcustController;
    /**
     * 某个时间段，crm客户同步到oa和金蝶
     * @param start 13位时间戳
     * @param end   13位时间戳
     * @return
     * @throws AccessTokenException
     */
    @GetMapping("queryByTime")
    public int testQuery(@RequestParam("start") String start, @RequestParam("end") String end) throws AccessTokenException {
        List<Map<String, Object>> maps = accountService.queryAccouontByTime(start, end);
        maps.forEach(e->{
            try {
                crmcustController.add(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        return 1;
    }
}
