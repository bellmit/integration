package com.qunjie.crm.achievement.controller;

import com.qunjie.axis.model.WorkflowRequestTable;
import com.qunjie.axis.service.invokeWebservice.InvokeFanWeiGetWorkflowRequestWebservice;
import com.qunjie.axis.utils.WorkflowResponseToBean;
import com.qunjie.common.response.ApiResult;
import com.qunjie.crm.achievement.event.AchievementEvent;
import com.qunjie.crm.achievement.service.AchievementCrmService;
import com.qunjie.crm.exception.AccessTokenException;
import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.achievement.controller.AchievementController
 *
 * @author whs
 * Date:   2021/2/25  14:13
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@RestController
@RequestMapping("/crm/achievement")
public class AchievementController {

    @Autowired
    private AchievementCrmService achievementCrmService;
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
        ApiResult apiResult = achievementCrmService.AchievementAdd(workflowRequestTable);
        System.out.println("AchievementAdd==================="+apiResult.toString());
        return 1;
    }

    @GetMapping("/delete")
    public int delete(String ids) throws AccessTokenException {
        AchievementEvent event = new AchievementEvent(this,ids);
        applicationContext.publishEvent(event);
        return 1;
    }
}
