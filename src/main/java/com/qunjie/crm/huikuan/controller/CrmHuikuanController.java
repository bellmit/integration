package com.qunjie.crm.huikuan.controller;

import com.qunjie.axis.model.WorkflowRequestTable;
import com.qunjie.axis.service.invokeWebservice.InvokeFanWeiGetWorkflowRequestWebservice;
import com.qunjie.axis.utils.WorkflowResponseToBean;
import com.qunjie.common.response.ApiResult;
import com.qunjie.crm.beans.results.BaseResult;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.huikuan.service.CrmHuikuanService;
import com.qunjie.crm.query.results.QueryResult;
import com.qunjie.crm.query.service.CrmQueryService;
import com.qunjie.crm.utils.DefaultValues;
import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.huikuan.controller.CrmHuikuanController
 *
 * @author whs
 * Date:   2021/1/27  15:04
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@RestController
@RequestMapping("/crm/payment")
public class CrmHuikuanController {

    @Autowired
    CrmQueryService crmQueryService;
    @Autowired
    CrmHuikuanService crmHuikuanService;

    @GetMapping("/save")
    public int test(String requestid) throws AccessTokenException, InterruptedException {
        String result = "";
        if (StringUtils.isNotBlank(requestid)) {
            JSONObject response = InvokeFanWeiGetWorkflowRequestWebservice.invokeGetWorkflowRequestWebservice(requestid);
            result = response.toString();
        }
        WorkflowRequestTable workflowRequestTable = WorkflowResponseToBean.responseToBean(result);
        ApiResult apiResult = crmHuikuanService.huikuanAdd(workflowRequestTable);
        System.out.println("invoiceResult==================="+apiResult.toString());
        return 1;
    }

    @GetMapping("/delete")
    public int delete(String dataId) throws AccessTokenException {
        BaseResult baseResult = crmHuikuanService.huikuanDelete(dataId);
        System.out.println(baseResult);
        return 1;
    }

    @GetMapping("/invalid")
    public int invalid(String dataId) throws AccessTokenException {
        BaseResult baseResult = crmHuikuanService.huikuanInvalid(dataId);
        System.out.println(baseResult);
        return 1;
    }

    @PostMapping("queryInvoice")
    public QueryResult getSaleOrder(@RequestBody Map<String,String> map) throws AccessTokenException {
        QueryResult queryResult = crmQueryService.QueryBy(map, DefaultValues.INVOICEAPPLICATIONOBJ);
        return queryResult;
    }
}
