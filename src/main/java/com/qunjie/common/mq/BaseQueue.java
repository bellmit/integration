package com.qunjie.common.mq;

import com.alibaba.fastjson.JSON;
import org.springframework.core.GenericTypeResolver;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.common.mq.BaseQueue
 *
 * @author whs
 * Date:   2021/4/6  17:53
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
public abstract class BaseQueue<T> extends MQBaseQueue {
    @Override
    protected void onMessage(String message) throws Exception {
        T entity = (T)JSON.parseObject(message, getEntityType());
        onMessage(entity);
    }

    protected abstract void onMessage(T entity) throws Exception;
    /**
     * 获取模板类型
     * @return
     */
    private Class<?> getEntityType() {
        return GenericTypeResolver.resolveTypeArgument(getClass(),BaseQueue.class);
    }

    protected void sendMessage(T message){
        rabbitTemplate.convertAndSend(exchange.getName(), routeKey,message);
    }
}
