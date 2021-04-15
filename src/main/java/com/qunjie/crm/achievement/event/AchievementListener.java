package com.qunjie.crm.achievement.event;

import com.qunjie.crm.exception.AccessTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.achievement.event.AchievementListener
 *
 * @author whs
 * Date:   2021/2/25  12:02
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Component
@Slf4j
public class AchievementListener implements ApplicationListener<AchievementEvent> {
    @Override
    public void onApplicationEvent(AchievementEvent achievementEvent) {
        try {
            achievementEvent.deleteAchievement();
            log.info("=================业绩拆分已删除已插入的数据==================================");
        } catch (AccessTokenException e) {
            e.printStackTrace();
        }
    }
}
