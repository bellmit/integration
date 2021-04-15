package com.qunjie.crm.confirmAchievement.event;

import com.qunjie.crm.exception.AccessTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.confirmAchievement.event.ConfirmAchievementListener
 *
 * @author whs
 * Date:   2021/3/10  9:55
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Component
@Slf4j
public class ConfirmAchievementListener implements ApplicationListener<ConfirmAchievementEvent> {

    @Async
    @Override
    public void onApplicationEvent(ConfirmAchievementEvent confirmAchievementEvent) {
        try {
            confirmAchievementEvent.deleteConfirmAchievement();
            log.info("=================销售净业绩已删除已插入的数据==================================");
        } catch (AccessTokenException e) {
            e.printStackTrace();
        }
    }
}
