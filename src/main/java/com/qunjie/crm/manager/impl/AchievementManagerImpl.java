package com.qunjie.crm.manager.impl;

import com.qunjie.crm.achievement.args.AchievementAddArg;
import com.qunjie.crm.achievement.args.AchievementInvalidArg;
import com.qunjie.crm.beans.CorpAccessToken;
import com.qunjie.crm.beans.results.BaseResult;
import com.qunjie.crm.beans.results.CrmAddResult;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.manager.AccessTokenManager;
import com.qunjie.crm.manager.AchievementManager;
import com.qunjie.crm.utils.OpenAPIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.manager.impl.AchievementManagerImpl
 *
 * @author whs
 * Date:   2021/2/25  10:23
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Service
public class AchievementManagerImpl implements AchievementManager {

    @Autowired
    private AccessTokenManager accessTokenManager;

    @Override
    public CrmAddResult saveAchievement(AchievementAddArg.AchievementModel achievementModel) throws AccessTokenException {
        AchievementAddArg arg = new AchievementAddArg();
        arg.setData(achievementModel);
        CorpAccessToken token = accessTokenManager.getCorpAccessToken();
        arg.setCorpAccessToken(token.getCorpAccessToken());
        arg.setCorpId(token.getCorpId());
        return OpenAPIUtils.saveCustomData(arg, CrmAddResult.class);
    }

    @Override
    public BaseResult deleteAchievement(AchievementInvalidArg.AchievementInvalidModel model) throws AccessTokenException {
        AchievementInvalidArg arg = new AchievementInvalidArg();
        arg.setData(model);
        CorpAccessToken token = accessTokenManager.getCorpAccessToken();
        arg.setCorpAccessToken(token.getCorpAccessToken());
        arg.setCorpId(token.getCorpId());
        return OpenAPIUtils.deleteCustomData(arg,BaseResult.class);
    }

    @Override
    public BaseResult invalidAchievement(AchievementInvalidArg.AchievementInvalidModel model) throws AccessTokenException {
        AchievementInvalidArg arg = new AchievementInvalidArg();
        arg.setData(model);
        CorpAccessToken token = accessTokenManager.getCorpAccessToken();
        arg.setCorpAccessToken(token.getCorpAccessToken());
        arg.setCorpId(token.getCorpId());
        return OpenAPIUtils.invalidCustomData(arg,BaseResult.class);
    }
}
