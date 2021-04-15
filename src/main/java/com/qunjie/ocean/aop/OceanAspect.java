package com.qunjie.ocean.aop;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.qunjie.mysql.mapper.OceanLogMapper;
import com.qunjie.mysql.model.OceanLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.ocean.aop.OceanAspect
 *
 * @author whs
 * Date:   2021/3/5  10:02
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Aspect
@Component
public class OceanAspect {

    @Autowired
    OceanLogMapper oceanLogMapper;

    @Pointcut("execution(* com.qunjie.ocean.utils.HttpRequestUtil.*(..))")
    public void pointcut(){};

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String clazz = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String s = new Gson().toJson(args);
        Object proceed = joinPoint.proceed(joinPoint.getArgs());
        if (proceed instanceof JSONObject){
            JSONObject jsonObject = (JSONObject) proceed;
            int Code = jsonObject.getInteger("code");
            while (Code == 40100){
                Thread.sleep(1*1000);
                proceed = joinPoint.proceed(joinPoint.getArgs());
                if (proceed instanceof JSONObject){
                    JSONObject proceed1 = (JSONObject) proceed;
                    Code = proceed1.getInteger("code");
                }else {
                    throw new Exception("未知错误！！");
                }
            }
            oceanLogMapper.insert(new OceanLog(null,new Date(),s,String.valueOf(Code),methodName,clazz));
        }else {
            oceanLogMapper.insert(new OceanLog(null,new Date(),s,"请求失败",methodName,clazz));
        }
        return proceed;
    }
}
