package com.qunjie.jindie.aop;

import com.google.gson.Gson;
import com.qunjie.mysql.mapper.K3CloudLogMapper;
import com.qunjie.mysql.model.CrmCustLog;
import com.qunjie.mysql.model.K3CloudLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.jindie.aop.K3CloudAspect
 *
 * @author whs
 * Date:   2021/1/22  14:25
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Aspect
@Component
public class K3CloudAspect {

    @Autowired
    K3CloudLogMapper k3CloudLogMapper;

    @Pointcut("execution(* kingdee.bos.webapi.client.K3CloudApiClient.*(..)) " +
            "&& !execution(* kingdee.bos.webapi.client.K3CloudApiClient.view(..)) " +
            "&& !execution(* kingdee.bos.webapi.client.K3CloudApiClient.executeBillQuery(..))")
    public void K3CloudPointCut(){};

    @Around("K3CloudPointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String classname = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String arg = new Gson().toJson(args);
        Object proceed = null;
        K3CloudLog k3CloudLog = null;
        try {
            proceed = joinPoint.proceed(args);
            if (args != null && args.length == 2){
                k3CloudLog = new K3CloudLog(null,new Date(),String.valueOf(args[0]),new Gson().toJson(args[1]),new Gson().toJson(proceed),methodName,classname,"金蝶接口调用");
            }else {
                k3CloudLog = new K3CloudLog(null,new Date(),null,new Gson().toJson(arg),new Gson().toJson(proceed),methodName,classname,"金蝶接口调用");
            }
        } catch (Throwable throwable) {
            k3CloudLog = new K3CloudLog(null,new Date(),null,new Gson().toJson(arg),new Gson().toJson(proceed),methodName,classname,"金蝶接口访问不通！");
        }
        k3CloudLogMapper.add(k3CloudLog);
        return proceed;
    }

}
