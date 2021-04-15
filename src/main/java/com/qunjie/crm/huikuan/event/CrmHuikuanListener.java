package com.qunjie.crm.huikuan.event;

import com.qunjie.crm.exception.AccessTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.huikuan.event.CrmHuikuanListener
 *
 * @author whs
 * Date:   2021/3/1  16:54
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Component
@Slf4j
public class CrmHuikuanListener implements ApplicationListener<CrmHuikuanEvent> {
    @Override
    public void onApplicationEvent(CrmHuikuanEvent crmHuikuanEvent) {
        try {
            crmHuikuanEvent.deleteHuikuan();
            log.info("=================crm回款已删除已插入的数据==================================");
        } catch (AccessTokenException e) {
            e.printStackTrace();
        }
    }
}
