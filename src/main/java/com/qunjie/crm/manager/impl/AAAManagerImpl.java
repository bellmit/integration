package com.qunjie.crm.manager.impl;

import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.manager.AAAManager;
import com.qunjie.crm.manager.AccessTokenManager;
import com.qunjie.crm.beans.args.BindAccountArg;
import com.qunjie.crm.beans.args.OpenUserIdArg;
import com.qunjie.crm.beans.results.BindAccountResult;
import com.qunjie.crm.beans.results.OpenUserIdResult;
import com.qunjie.crm.utils.OpenAPIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("aaaManager")
public class AAAManagerImpl implements AAAManager {

    @Autowired
    private AccessTokenManager accessTokenManager;

    @Override
    public OpenUserIdResult getOpenUserId(String code) throws AccessTokenException {
        OpenUserIdArg arg = new OpenUserIdArg();
        arg.setCode(code);
        arg.setAppAccessToken(accessTokenManager.getAppAccessToken());

        return OpenAPIUtils.getOpenUserId(arg);
    }

    @Override
    public BindAccountResult bindAccount(String openUserId, String appAccount) throws AccessTokenException {
        BindAccountArg bindAccountArg = new BindAccountArg();
        bindAccountArg.setOpenUserId(openUserId);
        bindAccountArg.setAppAccessToken(accessTokenManager.getAppAccessToken());
        bindAccountArg.setAppAccount(appAccount);
        return OpenAPIUtils.bindAccount(bindAccountArg);
    }

}
