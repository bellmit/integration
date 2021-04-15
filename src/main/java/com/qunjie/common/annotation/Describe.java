package com.qunjie.common.annotation;/**
 * Created by whs on 2020/12/8.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunjie.common.annotation.Describe
 *
 * @author whs
 *         Date:   2020/12/8  11:27
 *         Description:
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
@Target({ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Describe {
    String value();

    String describe() default "";
}
