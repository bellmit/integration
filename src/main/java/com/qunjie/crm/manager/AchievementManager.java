package com.qunjie.crm.manager;

import com.qunjie.crm.achievement.args.AchievementAddArg.AchievementModel;
import com.qunjie.crm.achievement.args.AchievementInvalidArg;
import com.qunjie.crm.beans.results.BaseResult;
import com.qunjie.crm.beans.results.CrmAddResult;
import com.qunjie.crm.exception.AccessTokenException;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.manager.AchievementManager
 *
 * @author whs
 * Date:   2021/2/25  10:20
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
public interface AchievementManager {

    public CrmAddResult saveAchievement(AchievementModel achievementModel) throws AccessTokenException;

    public BaseResult deleteAchievement(AchievementInvalidArg.AchievementInvalidModel arg) throws AccessTokenException;

    public BaseResult invalidAchievement(AchievementInvalidArg.AchievementInvalidModel arg) throws AccessTokenException;
}
