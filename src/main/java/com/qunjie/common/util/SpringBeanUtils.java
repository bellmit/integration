package com.qunjie.common.util;/**
 * Created by whs on 2020/12/9.
 */

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunjie.common.util.SpringUtils
 *
 * @author whs
 *         Date:   2020/12/9  17:57
 *         Description:
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
@Component
public class SpringBeanUtils<T> implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    public static <T> T getBean(Class<T> tClass){
        return applicationContext != null ? applicationContext.getBean(tClass) : null;
    }

    public static Object getBean(String className){
        if (StringUtils.isBlank(className)){
            throw new IllegalArgumentException("className为空");
        }

        String beanName;
        if (className.length() >1){
            beanName = className.substring(0,1).toLowerCase() + className.substring(1);
        }else {
            beanName = className.toLowerCase();
        }
        return applicationContext != null ? applicationContext.getBean(beanName) : null;
    }
}
