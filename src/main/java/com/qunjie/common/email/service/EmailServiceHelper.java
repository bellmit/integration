package com.qunjie.common.email.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailServiceHelper {

    //springboot自动集成JavaMailSender
    @Autowired
    private JavaMailSender mailSender;

    private String from = "250353401@qq.com";

    @Async
    public void sendSimpleMail(String[] address, String title, String content) {
        try {
            SimpleMailMessage mailMessage=new SimpleMailMessage();
            mailMessage.setFrom(from);
            //发送地址
            mailMessage.setTo(address);
            //邮件标题
            mailMessage.setSubject(title);
            //邮件内容
            mailMessage.setText(content);
            mailSender.send(mailMessage);
        } catch (MailException e) {
            log.info("邮件发送失败。。。。");
        }
    }
}
