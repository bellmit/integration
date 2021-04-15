package com.qunjie.common.email.controller;

import com.qunjie.common.email.service.EmailServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class Controller {
    @Autowired
    private EmailServiceHelper service;

    @RequestMapping("/send")
    public void sendJava(){
        String[] s = {"704378949@qq.com"};
        service.sendSimpleMail(s,"测试","销售订单不存在\nfsfsdf");
    }
}
