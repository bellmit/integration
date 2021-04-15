package com.qunjie.crm.confirmAchievement.controller;

import com.qunjie.axis.model.WorkflowRequestTable;
import com.qunjie.axis.service.invokeWebservice.InvokeFanWeiGetWorkflowRequestWebservice;
import com.qunjie.axis.utils.WorkflowResponseToBean;
import com.qunjie.common.response.ApiResult;
import com.qunjie.crm.confirmAchievement.event.ConfirmAchievementEvent;
import com.qunjie.crm.confirmAchievement.service.ConfirmAchievementService;
import com.qunjie.crm.exception.AccessTokenException;
import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.confirmAchievement.controller.ConfirmAchievementController
 *
 * @author whs
 * Date:   2021/3/10  11:23
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@RestController
@RequestMapping("/crm/confirmAchieve")
public class ConfirmAchievementController {

    @Autowired
    private ConfirmAchievementService confirmAchievementService;
    @Autowired
    ApplicationContext applicationContext;

    @GetMapping("/save")
    public int test(String requestid) throws AccessTokenException, InterruptedException {
        String result = "";
        if (StringUtils.isNotBlank(requestid)) {
            JSONObject response = InvokeFanWeiGetWorkflowRequestWebservice.invokeGetWorkflowRequestWebservice(requestid);
            result = response.toString();
        }
        WorkflowRequestTable workflowRequestTable = WorkflowResponseToBean.responseToBean(result);
        ApiResult apiResult = confirmAchievementService.ConfirmAchievementAdd(workflowRequestTable);
        System.out.println("AchievementAdd==================="+apiResult.toString());
        return 1;
    }

    @GetMapping("/delete")
    public int delete(String ids) throws AccessTokenException {
        ConfirmAchievementEvent event = new ConfirmAchievementEvent(this,ids);
        applicationContext.publishEvent(event);
        return 1;
    }
}
