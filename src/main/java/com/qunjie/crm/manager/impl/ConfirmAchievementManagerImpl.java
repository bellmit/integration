package com.qunjie.crm.manager.impl;

import com.qunjie.crm.beans.CorpAccessToken;
import com.qunjie.crm.beans.results.BaseResult;
import com.qunjie.crm.beans.results.CrmAddResult;
import com.qunjie.crm.confirmAchievement.args.ConfirmAchievementAddArg;
import com.qunjie.crm.confirmAchievement.args.ConfirmAchievementInvalidArg;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.manager.AccessTokenManager;
import com.qunjie.crm.manager.CustomDataManager;
import com.qunjie.crm.manager.args.BaseModelArg;
import com.qunjie.crm.utils.OpenAPIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.manager.impl.CustomDataManagerImpl
 *
 * @author whs
 * Date:   2021/3/10  9:33
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Service
public class ConfirmAchievementManagerImpl implements CustomDataManager {

    @Autowired
    private AccessTokenManager accessTokenManager;

    @Override
    public CrmAddResult saveCustomData(BaseModelArg model) throws AccessTokenException {
        ConfirmAchievementAddArg arg = new ConfirmAchievementAddArg();
        arg.setData((ConfirmAchievementAddArg.ConfirmAchievementModel) model);
        CorpAccessToken token = accessTokenManager.getCorpAccessToken();
        arg.setCorpAccessToken(token.getCorpAccessToken());
        arg.setCorpId(token.getCorpId());
        return OpenAPIUtils.saveCustomData(arg, CrmAddResult.class);
    }

    @Override
    public BaseResult deleteCustomData(BaseModelArg model) throws AccessTokenException {
        ConfirmAchievementInvalidArg arg = new ConfirmAchievementInvalidArg();
        arg.setData((ConfirmAchievementInvalidArg.ConfirmAchievementInvalidModel)model);
        CorpAccessToken token = accessTokenManager.getCorpAccessToken();
        arg.setCorpAccessToken(token.getCorpAccessToken());
        arg.setCorpId(token.getCorpId());
        return OpenAPIUtils.deleteCustomData(arg,BaseResult.class);
    }

    @Override
    public BaseResult invalidCustomData(BaseModelArg model) throws AccessTokenException {
        ConfirmAchievementInvalidArg arg = new ConfirmAchievementInvalidArg();
        arg.setData((ConfirmAchievementInvalidArg.ConfirmAchievementInvalidModel)model);
        CorpAccessToken token = accessTokenManager.getCorpAccessToken();
        arg.setCorpAccessToken(token.getCorpAccessToken());
        arg.setCorpId(token.getCorpId());
        return OpenAPIUtils.invalidCustomData(arg,BaseResult.class);
    }

    @Override
    public BaseResult modifyCustomData(BaseModelArg arg) throws AccessTokenException {
        return null;
    }
}
