package com.qunjie.common.email.event;

import com.qunjie.common.email.service.EmailServiceHelper;
import com.qunjie.common.util.SpringBeanUtils;
import org.springframework.context.ApplicationEvent;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.common.email.event.SendEmailEvent
 *
 * @author whs
 * Date:   2021/3/1  17:14
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
public class SendEmailEvent extends ApplicationEvent {

    private String message;

    private String[] sendTo;

    private String title;

    public SendEmailEvent(Object source, String message, String[] sendTo, String title) {
        super(source);
        this.message = message;
        this.sendTo = sendTo;
        this.title = title;
    }

    public SendEmailEvent(Object source) {
        super(source);
    }


    public void sendMsg(){
        EmailServiceHelper bean = SpringBeanUtils.getBean(EmailServiceHelper.class);
        bean.sendSimpleMail(sendTo,title,message);
    }
}
