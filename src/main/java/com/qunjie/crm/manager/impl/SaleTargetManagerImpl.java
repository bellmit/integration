package com.qunjie.crm.manager.impl;

import com.qunjie.crm.beans.CorpAccessToken;
import com.qunjie.crm.beans.results.BaseResult;
import com.qunjie.crm.beans.results.CrmAddResult;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.manager.AccessTokenManager;
import com.qunjie.crm.manager.CustomDataManager;
import com.qunjie.crm.manager.args.BaseModelArg;
import com.qunjie.crm.saleTarget.model.SaleTargetModel;
import com.qunjie.crm.manager.args.ModifyCustomArg;
import com.qunjie.crm.utils.OpenAPIUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.manager.impl.SaleTargetManagerImpl
 *
 * @author whs
 * Date:   2021/3/19  10:33
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Slf4j
@Service
public class SaleTargetManagerImpl implements CustomDataManager {

    @Autowired
    private AccessTokenManager accessTokenManager;

    @Override
    public CrmAddResult saveCustomData(BaseModelArg model) throws AccessTokenException {
        return null;
    }

    @Override
    public BaseResult deleteCustomData(BaseModelArg arg) throws AccessTokenException {
        return null;
    }

    @Override
    public BaseResult invalidCustomData(BaseModelArg arg) throws AccessTokenException {
        return null;
    }

    @Override
    public BaseResult modifyCustomData(BaseModelArg arg) throws AccessTokenException {
        ModifyCustomArg<SaleTargetModel> modelModifyCustomArg = new ModifyCustomArg<>();
        modelModifyCustomArg.setData((ModifyCustomArg.Object_data<SaleTargetModel>) arg);
        CorpAccessToken token = accessTokenManager.getCorpAccessToken();
        modelModifyCustomArg.setCorpAccessToken(token.getCorpAccessToken());
        modelModifyCustomArg.setCorpId(token.getCorpId());
        return OpenAPIUtils.modifyCustomData(modelModifyCustomArg);
    }
}
