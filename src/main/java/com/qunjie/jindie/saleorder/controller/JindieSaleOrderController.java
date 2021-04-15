package com.qunjie.jindie.saleorder.controller;

import com.alibaba.fastjson.JSONObject;
import com.qunjie.axis.model.WorkflowRequestTable;
import com.qunjie.axis.service.invokeWebservice.InvokeFanWeiGetWorkflowRequestWebservice;
import com.qunjie.axis.utils.WorkflowResponseToBean;
import com.qunjie.common.response.ApiResult;
import com.qunjie.jindie.saleorder.save.service.SaveService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.jindie.saleorder.controller.SaleOrderController
 *
 * @author whs
 * Date:   2021/1/15  15:11
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@RestController
@RequestMapping("/jindie/saleorder")
public class JindieSaleOrderController {

    @Autowired
    SaveService saveService;

    @GetMapping("deleteByBillno")
    public int deleteSaleOrder(String billno) throws Exception {
        SaveService saveService = new SaveService();
        saveService.delete(billno);
        return 1;
    }

    @GetMapping("view")
    public JSONObject view(String billno) throws Exception {
        SaveService saveService = new SaveService();
        return saveService.view(billno);
    }

    @GetMapping("addSaleOrder")
    public int addSaleOrder(String requestid) throws Exception {
        String result = null;
        if (StringUtils.isNotBlank(requestid)) {
            org.activiti.engine.impl.util.json.JSONObject response = InvokeFanWeiGetWorkflowRequestWebservice.invokeGetWorkflowRequestWebservice(requestid);
            result = response.toString();
        }
        WorkflowRequestTable workflowRequestTable = WorkflowResponseToBean.responseToBean(result);
        Integer workflowId = WorkflowResponseToBean.getWorkflowId(result);
        ApiResult save = saveService.save(workflowRequestTable,workflowId);
        return save.getCode();
    }
}
