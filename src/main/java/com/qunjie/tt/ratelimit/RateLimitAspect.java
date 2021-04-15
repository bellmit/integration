package com.qunjie.tt.ratelimit;

import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
public class RateLimitAspect {

    /**日志对象*/
    private static final Logger logger = LoggerFactory.getLogger(RateLimitAspect.class);

    private ConcurrentHashMap<String, RateLimiter> RATE_LIMITER  = new ConcurrentHashMap<>();
    private RateLimiter rateLimiter;

    @Pointcut("@annotation(com.qunjie.tt.ratelimit.Limit)")
    public void serviceLimit() {
    }

    @Around("serviceLimit()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object obj = null;
        //获取拦截的方法名
        Signature sig = point.getSignature();
        //获取拦截的方法名
        MethodSignature msig = (MethodSignature) sig;
        //返回被织入增加处理目标对象
        Object target = point.getTarget();
        //为了获取注解信息
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        //获取注解信息
        Limit annotation = currentMethod.getAnnotation(Limit.class);
        double limitNum = annotation.limitNum(); //获取注解每秒加入桶中的token
        String functionName = msig.getName(); // 注解所在方法名区分不同的限流策略

        if(RATE_LIMITER.containsKey(functionName)){
            rateLimiter=RATE_LIMITER.get(functionName);
        }else {
            RATE_LIMITER.put(functionName, RateLimiter.create(limitNum));
            rateLimiter=RATE_LIMITER.get(functionName);
        }
        if(rateLimiter.tryAcquire()) {
            logger.info("执行成功！！！...做一些业务处理");
            return point.proceed(point.getArgs());
        } else {
            logger.info("请求繁忙...做一些业务处理");
            return null;
        }
    }
}

