package com.qunjie.common.mq;

import com.qunjie.mysql.model.UserValue;
import org.springframework.stereotype.Component;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.common.mq.TestMq
 *
 * @author whs
 * Date:   2021/4/6  17:52
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Component
public class TestMq extends BaseQueue<UserValue> {


    @Override
    protected boolean isStartListening() {
        return false;
    }

    @Override
    protected void onMessage(UserValue entity) throws Exception {
        System.out.println("" + entity);
    }
}
