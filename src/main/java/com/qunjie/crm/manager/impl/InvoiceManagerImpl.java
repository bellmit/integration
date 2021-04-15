package com.qunjie.crm.manager.impl;

import com.qunjie.crm.beans.CorpAccessToken;
import com.qunjie.crm.beans.results.BaseResult;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.invoice.args.InvoiceAddArg;
import com.qunjie.crm.invoice.args.InvoiceDeleteDataArg;
import com.qunjie.crm.invoice.args.InvoiceInvalidArg;
import com.qunjie.crm.invoice.args.InvoiceModel;
import com.qunjie.crm.invoice.results.InvoiceResult;
import com.qunjie.crm.manager.AccessTokenManager;
import com.qunjie.crm.manager.InvoiceManager;
import com.qunjie.crm.query.args.QueryArg;
import com.qunjie.crm.query.args.QueryData;
import com.qunjie.crm.query.results.QueryResult;
import com.qunjie.crm.saleorder.args.SaleOrderDeleteDataArg;
import com.qunjie.crm.saleorder.args.SaleOrderInvalidArg;
import com.qunjie.crm.utils.DefaultValues;
import com.qunjie.crm.utils.OpenAPIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.manager.impl.InvoiceManagerImpl
 *
 * @author whs
 * Date:   2021/1/20  14:04
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Service
public class InvoiceManagerImpl implements InvoiceManager {

    @Autowired
    private AccessTokenManager accessTokenManager;

    @Override
    public InvoiceResult saveInvoice(InvoiceModel invoiceModel) throws AccessTokenException {
        InvoiceAddArg arg = new InvoiceAddArg();
        arg.setData(invoiceModel);

        CorpAccessToken token = accessTokenManager.getCorpAccessToken();
        arg.setCorpAccessToken(token.getCorpAccessToken());
        arg.setCorpId(token.getCorpId());
        arg.setCurrentOpenUserId(DefaultValues.CURRENTOPENUSERID);
        return OpenAPIUtils.saveData(arg, InvoiceResult.class);
    }

    @Override
    public BaseResult deleteInvoice(InvoiceDeleteDataArg.InvoiceDeleteModel model) throws AccessTokenException {
        InvoiceDeleteDataArg arg = new InvoiceDeleteDataArg();
        arg.setData(model);
        CorpAccessToken token = accessTokenManager.getCorpAccessToken();
        arg.setCorpAccessToken(token.getCorpAccessToken());
        arg.setCorpId(token.getCorpId());
        arg.setCurrentOpenUserId(DefaultValues.CURRENTOPENUSERID);
        return OpenAPIUtils.deleteData(arg,BaseResult.class);
    }

    @Override
    public BaseResult invalidInvoice(InvoiceInvalidArg.InvoiceInvalidData model) throws AccessTokenException {
        InvoiceInvalidArg arg = new InvoiceInvalidArg();
        arg.setData(model);
        CorpAccessToken token = accessTokenManager.getCorpAccessToken();
        arg.setCorpAccessToken(token.getCorpAccessToken());
        arg.setCorpId(token.getCorpId());
        arg.setCurrentOpenUserId(DefaultValues.CURRENTOPENUSERID);
        return OpenAPIUtils.invalidData(arg,BaseResult.class);
    }
}
