package com.qunjie.common.email.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.common.email.event.SendEmailListener
 *
 * @author whs
 * Date:   2021/3/1  17:15
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Component
@Slf4j
public class SendEmailListener implements ApplicationListener<SendEmailEvent> {

    @Async
    @Override
    public void onApplicationEvent(SendEmailEvent sendEmailEvent) {
        sendEmailEvent.sendMsg();
        log.info("======================email发送完毕========================");
    }
}
