package com.qunjie.tt.components;

import com.qunjie.common.email.DefaultEmailAddress;
import com.qunjie.common.email.event.SendEmailEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.tt.TService
 *
 * @author whs
 * Date:   2021/2/24  10:23
 * Description:     项目加载时加载必要数据进内存
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Component
@Order(1)
@Slf4j
public class AccessTokenComponent implements ApplicationRunner {

    @Autowired
    ApplicationContext applicationContext;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("=====================项目启动完毕========================");
        applicationContext.publishEvent(new SendEmailEvent(this,"飞鱼销售线索未授权，到如下地址:\nhttps://ad.oceanengine.com/openapi/appid/list.html?rid=7ag3hu8nekn#/external/\n" +
                "登录后点APPID管理--->点击test那个猫--->点击下方的'点击跳转'-->完成授权\n账户：qjwl1@gooddian.cn\n" +
                "密码：Gd123456!", DefaultEmailAddress.SENDTODev,"注意！！！飞鱼销售线索请授权！"));
    }
}
