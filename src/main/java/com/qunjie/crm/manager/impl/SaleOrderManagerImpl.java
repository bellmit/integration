package com.qunjie.crm.manager.impl;/**
 * Created by whs on 2021/1/12.
 */

import com.google.gson.Gson;
import com.qunjie.crm.saleorder.args.SaleOrderAddArg;
import com.qunjie.crm.saleorder.args.SaleOrderModel;
import com.qunjie.crm.saleorder.results.SaleOrderResult;
import com.qunjie.crm.beans.CorpAccessToken;
import com.qunjie.crm.beans.results.BaseResult;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.manager.AccessTokenManager;
import com.qunjie.crm.query.args.QueryArg;
import com.qunjie.crm.query.args.QueryData;
import com.qunjie.crm.query.results.QueryResult;
import com.qunjie.crm.saleorder.args.SaleOrderDeleteDataArg;
import com.qunjie.crm.saleorder.args.SaleOrderInvalidArg;
import com.qunjie.crm.manager.SaleOrderManager;
import com.qunjie.crm.utils.DefaultValues;
import com.qunjie.crm.utils.OpenAPIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.oacrmbridge.crm.manager.impl.SaleOrderManagerImpl
 *
 * @author whs
 *         Date:   2021/1/12  13:56
 *         Description:
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
@Service
public class SaleOrderManagerImpl implements SaleOrderManager {

    @Autowired
    private AccessTokenManager accessTokenManager;

    @Override
    public SaleOrderResult saveSaleOrder(SaleOrderModel saleOrderModel) throws AccessTokenException {
        SaleOrderAddArg arg = new SaleOrderAddArg();
        arg.setData(saleOrderModel);

        CorpAccessToken token = accessTokenManager.getCorpAccessToken();
        arg.setCorpAccessToken(token.getCorpAccessToken());
        arg.setCorpId(token.getCorpId());
        arg.setCurrentOpenUserId(DefaultValues.CURRENTOPENUSERID);
        System.out.println("arg======="+new Gson().toJson(arg));
        return OpenAPIUtils.saveData(arg,SaleOrderResult.class);
    }

    @Override
    public BaseResult deleteSaleOrder(SaleOrderDeleteDataArg.SaleOrderDeleteModel saleOrderDeleteModel) throws AccessTokenException {
        SaleOrderDeleteDataArg arg = new SaleOrderDeleteDataArg();
        arg.setData(saleOrderDeleteModel);
        CorpAccessToken token = accessTokenManager.getCorpAccessToken();
        arg.setCorpAccessToken(token.getCorpAccessToken());
        arg.setCorpId(token.getCorpId());
        arg.setCurrentOpenUserId(DefaultValues.CURRENTOPENUSERID);
        return OpenAPIUtils.deleteData(arg,BaseResult.class);
    }

    @Override
    public BaseResult invalidSaleOrder(SaleOrderInvalidArg.SaleOrderInvalidData saleOrderInvalidData) throws AccessTokenException {
        SaleOrderInvalidArg arg = new SaleOrderInvalidArg();
        arg.setData(saleOrderInvalidData);
        CorpAccessToken token = accessTokenManager.getCorpAccessToken();
        arg.setCorpAccessToken(token.getCorpAccessToken());
        arg.setCorpId(token.getCorpId());
        arg.setCurrentOpenUserId(DefaultValues.CURRENTOPENUSERID);
        return OpenAPIUtils.invalidData(arg,BaseResult.class);
    }
}
