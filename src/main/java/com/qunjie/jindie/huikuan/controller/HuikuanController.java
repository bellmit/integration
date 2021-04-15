package com.qunjie.jindie.huikuan.controller;

import com.alibaba.fastjson.JSON;
import com.qunjie.axis.model.WorkflowRequestTable;
import com.qunjie.axis.service.invokeWebservice.InvokeFanWeiGetWorkflowRequestWebservice;
import com.qunjie.axis.utils.WorkflowResponseToBean;
import com.qunjie.jindie.huikuan.service.HuikuanService;
import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.jindie.huikuan.controller.HuikuanController
 *
 * @author whs
 * Date:   2021/1/25  16:31
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@RestController
@RequestMapping("/huikuan")
public class HuikuanController {

    @Autowired
    private HuikuanService huikuanService;

    @GetMapping("/add")
    public int add(String requestId) throws Exception {
        String result = "";
        if (StringUtils.isNotBlank(requestId)) {
            JSONObject response = InvokeFanWeiGetWorkflowRequestWebservice.invokeGetWorkflowRequestWebservice(requestId);
            result = response.toString();
        }
        com.alibaba.fastjson.JSONObject objects = JSON.parseObject(result);
        if (objects.isEmpty()) {
            return 0;
        }
        WorkflowRequestTable workflowRequestTable = WorkflowResponseToBean.responseToBean(result);
        int save = huikuanService.save(workflowRequestTable);
        return save;
    }
}
