package com.qunjie.crm.invoice.controller;

import com.qunjie.axis.model.WorkflowRequestTable;
import com.qunjie.axis.service.invokeWebservice.InvokeFanWeiGetWorkflowRequestWebservice;
import com.qunjie.axis.utils.WorkflowResponseToBean;
import com.qunjie.crm.beans.results.BaseResult;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.invoice.results.InvoiceResult;
import com.qunjie.crm.invoice.service.InvoiceService;
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
 * FileName: com.qunjie.crm.invoice.controller.InvoiceController
 *
 * @author whs
 * Date:   2021/1/20  15:55
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@RestController
@RequestMapping("/crm/invoice")
public class InvoiceController {
    @Autowired
    CrmQueryService crmQueryService;

    @GetMapping("/saveInvoice")
    public int test(String requestid) throws AccessTokenException {
        InvoiceService invoiceService = new InvoiceService();
        String result = "";
        if (StringUtils.isNotBlank(requestid)) {
            JSONObject response = InvokeFanWeiGetWorkflowRequestWebservice.invokeGetWorkflowRequestWebservice(requestid);
            result = response.toString();
        }
        WorkflowRequestTable workflowRequestTable = WorkflowResponseToBean.responseToBean(result);
        InvoiceResult invoiceResult = invoiceService.invoiceAdd(workflowRequestTable);
        System.out.println("invoiceResult==================="+invoiceResult.toString());
        return 1;
    }

    @GetMapping("/deleteInvoice")
    public int delete(String dataId) throws AccessTokenException {
        InvoiceService saleOrderService = new InvoiceService();
        BaseResult baseResult = saleOrderService.invoiceDelete(dataId);
        System.out.println(baseResult);
        return 1;
    }

    @GetMapping("/invalidInvoice")
    public int invalid(String dataId) throws AccessTokenException {
        InvoiceService invoiceService = new InvoiceService();
        BaseResult baseResult = invoiceService.invoiceInvalid(dataId);
        System.out.println(baseResult);
        return 1;
    }

    @PostMapping("queryInvoice")
    public QueryResult getSaleOrder(@RequestBody Map<String,String> map) throws AccessTokenException {
//        InvoiceService invoiceService = new InvoiceService();
//        QueryResult queryResult = invoiceService.InvoiceQueryBy(map);
        QueryResult queryResult = crmQueryService.QueryBy(map, DefaultValues.INVOICEAPPLICATIONOBJ);
        return queryResult;
    }
}
