package com.qunjie.crm.manager.impl;

import com.qunjie.crm.beans.CorpAccessToken;
import com.qunjie.crm.beans.results.BaseResult;
import com.qunjie.crm.beans.results.CrmAddResult;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.huikuan.args.HuikuanAddArg;
import com.qunjie.crm.huikuan.args.HuikuanDeleteDataArg;
import com.qunjie.crm.huikuan.args.HuikuanInvalidArg;
import com.qunjie.crm.huikuan.args.HuikuanModel;
import com.qunjie.crm.invoice.args.InvoiceAddArg;
import com.qunjie.crm.invoice.args.InvoiceDeleteDataArg;
import com.qunjie.crm.invoice.args.InvoiceInvalidArg;
import com.qunjie.crm.invoice.results.InvoiceResult;
import com.qunjie.crm.manager.AccessTokenManager;
import com.qunjie.crm.manager.HuikuanManager;
import com.qunjie.crm.utils.DefaultValues;
import com.qunjie.crm.utils.OpenAPIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.manager.impl.HuikuanManagerImpl
 *
 * @author whs
 * Date:   2021/1/27  14:31
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Service
public class HuikuanManagerImpl implements HuikuanManager {

    @Autowired
    private AccessTokenManager accessTokenManager;

    @Override
    public CrmAddResult savePayment(HuikuanModel invoiceModel) throws AccessTokenException {
        HuikuanAddArg arg = new HuikuanAddArg();
        arg.setData(invoiceModel);

        CorpAccessToken token = accessTokenManager.getCorpAccessToken();
        arg.setCorpAccessToken(token.getCorpAccessToken());
        arg.setCorpId(token.getCorpId());
        arg.setCurrentOpenUserId(DefaultValues.CURRENTOPENUSERID);
        return OpenAPIUtils.saveData(arg, CrmAddResult.class);
    }

    @Override
    public BaseResult deletePayment(HuikuanDeleteDataArg.HuikuanDeleteModel model) throws AccessTokenException {
        HuikuanDeleteDataArg arg = new HuikuanDeleteDataArg();
        arg.setData(model);
        CorpAccessToken token = accessTokenManager.getCorpAccessToken();
        arg.setCorpAccessToken(token.getCorpAccessToken());
        arg.setCorpId(token.getCorpId());
        arg.setCurrentOpenUserId(DefaultValues.CURRENTOPENUSERID);
        return OpenAPIUtils.deleteData(arg,BaseResult.class);
    }

    @Override
    public BaseResult invalidPayment(HuikuanInvalidArg.HuikuanInvalidData model) throws AccessTokenException {
        HuikuanInvalidArg arg = new HuikuanInvalidArg();
        arg.setData(model);
        CorpAccessToken token = accessTokenManager.getCorpAccessToken();
        arg.setCorpAccessToken(token.getCorpAccessToken());
        arg.setCorpId(token.getCorpId());
        arg.setCurrentOpenUserId(DefaultValues.CURRENTOPENUSERID);
        return OpenAPIUtils.invalidData(arg,BaseResult.class);
    }
}
