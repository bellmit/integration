package com.qunjie.crm.saleorder.controller;
/**
 * Created by whs on 2021/1/12.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.qunjie.axis.service.invokeWebservice.InvokeFanWeiGetWorkflowRequestWebservice;
import com.qunjie.axis.utils.WorkflowResponseToBean;
import com.qunjie.crm.saleorder.results.SaleOrderResult;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.axis.model.WorkflowRequestTable;
import com.qunjie.axis.model.WorkflowRequestTableField;

import com.qunjie.crm.saleorder.service.SaleOrderService;
import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.oacrmbridge.crm.saleorder.controller.test
 *
 * @author whs
 *         Date:   2021/1/12  15:13
 *         Description:
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
@Service
public class Test {

    public void test(String requestid) throws AccessTokenException {

        String result = null;
        if (StringUtils.isNotBlank(requestid)) {
            JSONObject response = InvokeFanWeiGetWorkflowRequestWebservice.invokeGetWorkflowRequestWebservice(requestid);
            result = response.toString();
        }
        WorkflowRequestTable workflowRequestTable = WorkflowResponseToBean.responseToBean(result);
        Integer workflowId = WorkflowResponseToBean.getWorkflowId(result);
        SaleOrderService saleOrderService = new SaleOrderService();
        SaleOrderResult saleOrderResult = saleOrderService.saleOrderAdd(workflowRequestTable,workflowId);
        System.out.println("=========================================" + saleOrderResult);
    }
}
