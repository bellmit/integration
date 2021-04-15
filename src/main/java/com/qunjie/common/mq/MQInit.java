package com.qunjie.common.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.common.mq.MQInit
 *
 * @author whs
 * Date:   2021/4/6  16:48
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@SuppressWarnings("rawtypes")
@Component
@Slf4j
public class MQInit {

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event){
        Map<String, BaseQueue> queues = event.getApplicationContext().getBeansOfType(BaseQueue.class);
        if (queues.size() > 0){
            log.info("MQ启动初始化 - 开始");
            for (String key : queues.keySet()){
                log.info("MQ初始化队列" + key);
                MQBaseQueue mqBaseQueue = queues.get(key);
                mqBaseQueue.initQueue();
            }
            log.info("MQ启动初始化 - 完成");
        }
    }

}
