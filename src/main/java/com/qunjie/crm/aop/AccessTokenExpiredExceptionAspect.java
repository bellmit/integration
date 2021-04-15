package com.qunjie.crm.aop;

import com.google.gson.Gson;
import com.qunjie.crm.beans.results.BaseResult;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.manager.AccessTokenManager;
import com.qunjie.crm.utils.Constants;
import com.qunjie.mysql.mapper.CrmHuikuanLogMapper;
import com.qunjie.mysql.mapper.CrmLoggerMapper;
import com.qunjie.mysql.mapper.CrmSaleOrderLogMapper;
import com.qunjie.mysql.model.CrmHuikuanLog;
import com.qunjie.mysql.model.InvoiceLog;
import com.qunjie.mysql.model.LoggerEntity;
import com.qunjie.mysql.service.CrmInvoiceLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;


@Aspect
@Component
@Order
public class AccessTokenExpiredExceptionAspect {

    private static final Logger LOG = LoggerFactory.getLogger(AccessTokenExpiredExceptionAspect.class);

    @Autowired
    private AccessTokenManager accessTokenManager;
    
    @Autowired
    private CrmLoggerMapper crmLoggerMapper;

    @Autowired
    private CrmSaleOrderLogMapper crmSaleOrderLogMapper;

    @Autowired
    private CrmInvoiceLogService crmInvoiceLogService;

    @Autowired
    CrmHuikuanLogMapper crmHuikuanLogMapper;

    @Pointcut("execution(* com.qunjie.crm.manager.AAAManager.*(..))")
    public void pointcut1(){}

    @Pointcut("execution(* com.qunjie.crm.manager.AccessTokenManager.*(..))")
    public void pointcut2(){};

    @Pointcut("execution(* com.qunjie.crm.manager.AddressBookManager.*(..))")
    public void pointcut3(){};

    @Pointcut("execution(* com.qunjie.crm.manager.SaleOrderManager.*(..))")
    public void pointcut4(){}

    @Pointcut("execution(* com.qunjie.crm.manager.InvoiceManager.*(..))")
    public void pointcutInvoice(){};

    @Pointcut("execution(* com.qunjie.crm.manager.AddressBookManager.add*(..))")
    public void CrmAdd(){}

    @Pointcut("execution(* com.qunjie.crm.manager.AddressBookManager.modify*(..))")
    public void CrmModify(){}

    @Pointcut("execution(* com.qunjie.crm.manager.AddressBookManager.Canceled*(..))")
    public void CrmCanceled(){}

    @Pointcut("execution(* com.qunjie.crm.manager.HuikuanManager.*(..))")
    public void PointcutHuikuan(){}

    @Pointcut("execution(* com.qunjie.crm.manager.AchievementManager.*(..))")
    public void PointcutAchievement(){}

    @Pointcut("execution(* com.qunjie.crm.manager.LeadsObjManager.*(..))")
    public void PointcutLeadsObj(){}

    @Pointcut("execution(* com.qunjie.crm.manager.CustomDataManager.*(..))")
    public void PointcutCustomData(){}



    /**
     * 返回结果拦截，如果是token过期，清理token缓存
     * 
     * @param joinPoint
     * @param result
     */
    @AfterReturning(value = "pointcut1()||pointcut2()||pointcut3()||pointcut4()" +
            "||pointcutInvoice()||PointcutHuikuan()||PointcutAchievement()||PointcutLeadsObj()||PointcutCustomData()",returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String typeName = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();

        if (result instanceof BaseResult) {
            BaseResult baseResult = (BaseResult) result;
            try {
                if (baseResult.getErrorCode() == Constants.interfaceResponseCode.APP_ACCESS_TOKEN_EXPIRED.code) {
                    accessTokenManager.resetAppAccessToken();
                    accessTokenManager.resetCorpAccessToken();
                } else if (baseResult.getErrorCode() == Constants.interfaceResponseCode.CORP_ACCESS_TOKEN_EXPIRED.code) {
                    accessTokenManager.resetCorpAccessToken();
                }
            } catch (Exception e) {
                LOG.error("afterReturn error, service:{} method:{}, details:", typeName, methodName, e);
            }
        }
    }

    /**
     * 异常拦截，如果是AccessTokenException异常，清理token缓存
     * 
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(value = "pointcut1()||pointcut2()||pointcut3()||pointcut4()" +
            "||pointcutInvoice()||PointcutHuikuan()||PointcutAchievement()||PointcutLeadsObj()||PointcutCustomData()",throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        String typeName = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();

        if (e instanceof AccessTokenException) {
            try {
                accessTokenManager.resetCorpAccessToken();
            } catch (AccessTokenException ae) {
                LOG.error("afterThrowing error, service:{} method:{}, details:", typeName, methodName, ae);
            }
        }
    }

    @Around("CrmAdd()||CrmCanceled()||CrmModify()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String s = new Gson().toJson(args);
        Object proceed = joinPoint.proceed(joinPoint.getArgs());
        if (proceed instanceof BaseResult){
            BaseResult baseResult = (BaseResult) proceed;
            int errorCode = baseResult.getErrorCode();
            String errorMessage = baseResult.getErrorMessage();
            while (errorCode == 30004){//20s内请求超过60次
                Thread.sleep(2*1000);
                proceed = joinPoint.proceed(joinPoint.getArgs());
                if (proceed instanceof BaseResult){
                    BaseResult proceed1 = (BaseResult) proceed;
                    errorCode = proceed1.getErrorCode();
                    errorMessage = proceed1.getErrorMessage();
                }else {
                    throw new Exception("未知错误！！");
                }
            }
            crmLoggerMapper.add(new LoggerEntity(null,new Date(),s,errorCode,errorMessage,methodName));
        }else {
            crmLoggerMapper.add(new LoggerEntity(null,new Date(),s,404,"crm访问失败",methodName));
        }
        return proceed;
    }


    @Around("pointcut4()")
    public Object around4(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String s = new Gson().toJson(args);
        Object proceed = joinPoint.proceed(joinPoint.getArgs());
        if (proceed instanceof BaseResult){
            BaseResult baseResult = (BaseResult) proceed;
            int errorCode = baseResult.getErrorCode();
            String errorMessage = baseResult.getErrorMessage();
            while (errorCode == 30004){//20s内请求超过60次
                Thread.sleep(2*1000);
                proceed = joinPoint.proceed(joinPoint.getArgs());
                if (proceed instanceof BaseResult){
                    BaseResult proceed1 = (BaseResult) proceed;
                    errorCode = proceed1.getErrorCode();
                    errorMessage = proceed1.getErrorMessage();
                }else {
                    throw new Exception("未知错误！！");
                }
            }
            crmSaleOrderLogMapper.add(new LoggerEntity(null,new Date(),s,errorCode,errorMessage,methodName));
        }else {
            crmSaleOrderLogMapper.add(new LoggerEntity(null,new Date(),s,404,"crm访问失败",methodName));
        }
        return proceed;
    }

    @Around("pointcutInvoice()")
    public Object aroundInvoice(ProceedingJoinPoint joinPoint) throws Throwable {
        Object target = joinPoint.getTarget();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String s = new Gson().toJson(args);
        Object proceed = joinPoint.proceed(joinPoint.getArgs());
        if (proceed instanceof BaseResult){
            BaseResult baseResult = (BaseResult) proceed;
            int errorCode = baseResult.getErrorCode();
            String errorMessage = baseResult.getErrorMessage();
            while (errorCode == 30004){//20s内请求超过60次
                Thread.sleep(2*1000);
                proceed = joinPoint.proceed(joinPoint.getArgs());
                if (proceed instanceof BaseResult){
                    BaseResult proceed1 = (BaseResult) proceed;
                    errorCode = proceed1.getErrorCode();
                    errorMessage = proceed1.getErrorMessage();
                }else {
                    throw new Exception("未知错误！！");
                }
            }
            crmInvoiceLogService.AddLog(new InvoiceLog(null,new Date(),s,errorMessage,methodName,target.getClass().getName(),"开票推送纷享销客"));
        }else {
            crmInvoiceLogService.AddLog(new InvoiceLog(null,new Date(),s,"crm访问失败",methodName,target.getClass().getName(),"开票推送纷享销客"));
        }
        return proceed;
    }

    @Around("PointcutHuikuan()")
    public Object aroundHuikuan(ProceedingJoinPoint joinPoint) throws Throwable {
        Object target = joinPoint.getTarget();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String s = new Gson().toJson(args);
        Object proceed = joinPoint.proceed(joinPoint.getArgs());
        if (proceed instanceof BaseResult){
            BaseResult baseResult = (BaseResult) proceed;
            int errorCode = baseResult.getErrorCode();
            String errorMessage = baseResult.getErrorMessage();
            while (errorCode == 30004){//20s内请求超过60次
                Thread.sleep(2*1000);
                proceed = joinPoint.proceed(joinPoint.getArgs());
                if (proceed instanceof BaseResult){
                    BaseResult proceed1 = (BaseResult) proceed;
                    errorCode = proceed1.getErrorCode();
                    errorMessage = proceed1.getErrorMessage();
                }else {
                    throw new Exception("未知错误！！");
                }
            }
            crmHuikuanLogMapper.add(new CrmHuikuanLog(null,new Date(),s,errorMessage,methodName,target.getClass().getName(),"回款推送纷享销客"));
        }else {
            crmHuikuanLogMapper.add(new CrmHuikuanLog(null,new Date(),s,"crm访问失败",methodName,target.getClass().getName(),"回款推送纷享销客"));
        }
        return proceed;
    }

    @Around("PointcutAchievement()")
    public Object aroundAchievement(ProceedingJoinPoint joinPoint) throws Throwable {
        Object target = joinPoint.getTarget();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String s = new Gson().toJson(args);
        Object proceed = joinPoint.proceed(joinPoint.getArgs());
        if (proceed instanceof BaseResult){
            BaseResult baseResult = (BaseResult) proceed;
            int errorCode = baseResult.getErrorCode();
            String errorMessage = baseResult.getErrorMessage();
            while (errorCode == 30004){//20s内请求超过60次
                Thread.sleep(2*1000);
                proceed = joinPoint.proceed(joinPoint.getArgs());
                if (proceed instanceof BaseResult){
                    BaseResult proceed1 = (BaseResult) proceed;
                    errorCode = proceed1.getErrorCode();
                    errorMessage = proceed1.getErrorMessage();
                }else {
                    throw new Exception("未知错误！！");
                }
            }
            crmHuikuanLogMapper.add(new CrmHuikuanLog(null,new Date(),s,errorMessage,methodName,target.getClass().getName(),"业绩拆分推送纷享销客"));
        }else {
            crmHuikuanLogMapper.add(new CrmHuikuanLog(null,new Date(),s,"crm访问失败",methodName,target.getClass().getName(),"业绩拆分推送纷享销客"));
        }
        return proceed;
    }

    @Around("PointcutLeadsObj()")
    public Object aroundLeadsObj(ProceedingJoinPoint joinPoint) throws Throwable {
        Object target = joinPoint.getTarget();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String s = new Gson().toJson(args);
        Object proceed = joinPoint.proceed(joinPoint.getArgs());
        if (proceed instanceof BaseResult){
            BaseResult baseResult = (BaseResult) proceed;
            int errorCode = baseResult.getErrorCode();
            String errorMessage = baseResult.getErrorMessage();
            while (errorCode == 30004){//20s内请求超过60次
                Thread.sleep(2*1000);
                proceed = joinPoint.proceed(joinPoint.getArgs());
                if (proceed instanceof BaseResult){
                    BaseResult proceed1 = (BaseResult) proceed;
                    errorCode = proceed1.getErrorCode();
                    errorMessage = proceed1.getErrorMessage();
                }else {
                    throw new Exception("未知错误！！");
                }
            }
            crmHuikuanLogMapper.add(new CrmHuikuanLog(null,new Date(),s,errorMessage,methodName,target.getClass().getName(),"销售线索送纷享销客"));
        }else {
            crmHuikuanLogMapper.add(new CrmHuikuanLog(null,new Date(),s,"crm访问失败",methodName,target.getClass().getName(),"销售线索推送纷享销客"));
        }
        return proceed;
    }

    @Around("PointcutCustomData()")
    public Object aroundCustomData(ProceedingJoinPoint joinPoint) throws Throwable {
        Object target = joinPoint.getTarget();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String s = new Gson().toJson(args);
        Object proceed = joinPoint.proceed(joinPoint.getArgs());
        if (proceed instanceof BaseResult){
            BaseResult baseResult = (BaseResult) proceed;
            int errorCode = baseResult.getErrorCode();
            String errorMessage = baseResult.getErrorMessage();
            while (errorCode == 30004){//20s内请求超过60次
                Thread.sleep(2*1000);
                proceed = joinPoint.proceed(joinPoint.getArgs());
                if (proceed instanceof BaseResult){
                    BaseResult proceed1 = (BaseResult) proceed;
                    errorCode = proceed1.getErrorCode();
                    errorMessage = proceed1.getErrorMessage();
                }else {
                    throw new Exception("未知错误！！");
                }
            }
            crmHuikuanLogMapper.add(new CrmHuikuanLog(null,new Date(),s,errorMessage,methodName,target.getClass().getName(),"自定义接口"));
        }else {
            crmHuikuanLogMapper.add(new CrmHuikuanLog(null,new Date(),s,"crm访问失败",methodName,target.getClass().getName(),"自定义接口"));
        }
        return proceed;
    }
}
