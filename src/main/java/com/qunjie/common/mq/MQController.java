package com.qunjie.common.mq;

import com.qunjie.mysql.model.UserValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.common.mq.MQController
 *
 * @author whs
 * Date:   2021/4/6  18:05
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@RestController
@RequestMapping("mq")
public class MQController {

    @Autowired
    TestMq testMq;

    @RequestMapping("test")
    public String test(){
        UserValue userValue = new UserValue();
        userValue.setUsernm("王宏声");
        userValue.setMobile("18795816172");
        testMq.sendMessage(userValue);
        return "1";
    }
}
