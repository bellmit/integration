package com.qunjie.tt.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.tt.cglib.CglibHelper
 *
 * @author whs
 * Date:   2021/2/24  10:44
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
public class HttpHelperCglibHelper implements MethodInterceptor {

    private Object target;

    public Object getInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        // 设置回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("事务开始。。。");
        Object result = proxy.invokeSuper(object, args);
        System.out.println("事务结束。。。");
        return result;
    }
}
