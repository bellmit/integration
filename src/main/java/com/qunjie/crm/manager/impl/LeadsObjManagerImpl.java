package com.qunjie.crm.manager.impl;

import com.qunjie.crm.beans.CorpAccessToken;
import com.qunjie.crm.beans.results.CrmAddResult;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.leadsObj.args.LeadsObjAddArg;
import com.qunjie.crm.leadsObj.args.LeadsObjModel;
import com.qunjie.crm.manager.AccessTokenManager;
import com.qunjie.crm.manager.LeadsObjManager;
import com.qunjie.crm.utils.DefaultValues;
import com.qunjie.crm.utils.OpenAPIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.manager.impl.LeadsObjManagerImpl
 *
 * @author whs
 * Date:   2021/3/5  17:43
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Service
public class LeadsObjManagerImpl implements LeadsObjManager {

    @Autowired
    AccessTokenManager accessTokenManager;

    @Override
    public CrmAddResult saveLeadsObj(LeadsObjModel model) throws AccessTokenException {
        LeadsObjAddArg arg = new LeadsObjAddArg();
        arg.setData(model);
        CorpAccessToken token = accessTokenManager.getCorpAccessToken();
        arg.setCorpAccessToken(token.getCorpAccessToken());
        arg.setCorpId(token.getCorpId());
        arg.setCurrentOpenUserId(DefaultValues.CURRENTOPENUSERID);
        return OpenAPIUtils.saveData(arg, CrmAddResult.class);
    }
}
