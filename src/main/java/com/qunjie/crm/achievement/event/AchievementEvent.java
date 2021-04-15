package com.qunjie.crm.achievement.event;

import com.qunjie.common.util.SpringBeanUtils;
import com.qunjie.crm.achievement.args.AchievementInvalidArg;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.manager.impl.AchievementManagerImpl;
import org.springframework.context.ApplicationEvent;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.achievement.event.AchievementEvent
 *
 * @author whs
 * Date:   2021/2/25  11:58
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
public class AchievementEvent extends ApplicationEvent {

    public List<String> AddSuccessIds;

    public AchievementEvent(Object source) {
        super(source);
    }

    public AchievementEvent(Object source, List<String> addSuccessIds) {
        super(source);
        AddSuccessIds = addSuccessIds;
    }

    public AchievementEvent(Object source, String Id) {
        super(source);
        AddSuccessIds = new ArrayList<>();
        AddSuccessIds.add(Id);
    }

    public void deleteAchievement() throws AccessTokenException {
        if (!CollectionUtils.isEmpty(AddSuccessIds)){
            AchievementManagerImpl bean = SpringBeanUtils.getBean(AchievementManagerImpl.class);
            bean.invalidAchievement(new AchievementInvalidArg.AchievementInvalidModel(AddSuccessIds));
            bean.deleteAchievement(new AchievementInvalidArg.AchievementInvalidModel(AddSuccessIds));
        }
    }

}
