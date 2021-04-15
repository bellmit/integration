package com.qunjie.fanwei.aop;/**
 * Created by whs on 2021/1/8.
 */

import com.google.gson.Gson;
import com.qunjie.mysql.model.CrmCustLog;
import com.qunjie.mysql.service.CrmCustLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.fanwei.aop.CrmCustLogAspect
 *
 * @author whs
 *         Date:   2021/1/8  14:57
 *         Description: 客户信息推送泛微+金蝶日志
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
@Aspect
@Component
public class FanweiLogAspect {

    @Autowired
    private CrmCustLogService crmCustLogService;


    @Pointcut("execution(* com.qunjie.fanwei.crmcust.service.OACrmcustService.crmcust*(..))")
    public void pointcut2(){};

    @Around("pointcut2()")
    public Object doAround2(ProceedingJoinPoint joinPoint) throws Throwable {
        String classname = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String arg = new Gson().toJson(args);
        Object proceed = joinPoint.proceed(args);
        CrmCustLog crmCustLog = new CrmCustLog(null,new Date(),arg,new Gson().toJson(proceed) ,methodName,classname,"客户推送泛微");
        crmCustLogService.AddLog(crmCustLog);
        return proceed;
    }
}
