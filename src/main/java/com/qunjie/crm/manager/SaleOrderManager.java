package com.qunjie.crm.manager;
/**
 * Created by whs on 2021/1/12.
 */

import com.qunjie.crm.saleorder.args.SaleOrderModel;
import com.qunjie.crm.saleorder.results.SaleOrderResult;
import com.qunjie.crm.beans.results.BaseResult;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.saleorder.args.SaleOrderDeleteDataArg.SaleOrderDeleteModel;
import com.qunjie.crm.query.args.QueryData;
import com.qunjie.crm.query.results.QueryResult;
import com.qunjie.crm.saleorder.args.SaleOrderInvalidArg;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.saleorder.manager.SaleOrderManager
 *
 * @author whs
 *         Date:   2021/1/12  13:56
 *         Description:
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
public interface SaleOrderManager {

    public SaleOrderResult saveSaleOrder(SaleOrderModel saleOrderModel) throws AccessTokenException;

    public BaseResult deleteSaleOrder(SaleOrderDeleteModel arg) throws AccessTokenException;

    public BaseResult invalidSaleOrder(SaleOrderInvalidArg.SaleOrderInvalidData arg) throws AccessTokenException;
}
