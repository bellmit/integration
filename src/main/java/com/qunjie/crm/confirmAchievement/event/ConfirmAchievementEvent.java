package com.qunjie.crm.confirmAchievement.event;

import com.qunjie.common.util.SpringBeanUtils;
import com.qunjie.crm.confirmAchievement.args.ConfirmAchievementInvalidArg;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.manager.impl.ConfirmAchievementManagerImpl;
import org.springframework.context.ApplicationEvent;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.confirmAchievement.event.ConfirmAchievementEvent
 *
 * @author whs
 * Date:   2021/3/10  9:55
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
public class ConfirmAchievementEvent extends ApplicationEvent {

    public List<String> AddSuccessIds;

    public ConfirmAchievementEvent(Object source) {
        super(source);
    }

    public ConfirmAchievementEvent(Object source, List<String> addSuccessIds) {
        super(source);
        AddSuccessIds = addSuccessIds;
    }

    public ConfirmAchievementEvent(Object source,String id){
        super(source);
        AddSuccessIds = new ArrayList<>();
        AddSuccessIds.add(id);
    }

    public void deleteConfirmAchievement() throws AccessTokenException {
        if (!CollectionUtils.isEmpty(AddSuccessIds)){
            ConfirmAchievementManagerImpl bean = SpringBeanUtils.getBean(ConfirmAchievementManagerImpl.class);
            bean.invalidCustomData(new ConfirmAchievementInvalidArg.ConfirmAchievementInvalidModel(AddSuccessIds));
            bean.deleteCustomData(new ConfirmAchievementInvalidArg.ConfirmAchievementInvalidModel(AddSuccessIds));
        }
    }
}
