package com.qunjie.crm.manager;

import com.qunjie.crm.beans.results.BaseResult;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.invoice.args.InvoiceDeleteDataArg.InvoiceDeleteModel;
import com.qunjie.crm.invoice.args.InvoiceInvalidArg;
import com.qunjie.crm.invoice.args.InvoiceModel;
import com.qunjie.crm.invoice.results.InvoiceResult;
import com.qunjie.crm.query.args.QueryData;
import com.qunjie.crm.query.results.QueryResult;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.manager.InvoiceManager
 *
 * @author whs
 * Date:   2021/1/20  14:03
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
public interface InvoiceManager {

    public InvoiceResult saveInvoice(InvoiceModel invoiceModel) throws AccessTokenException;

    public BaseResult deleteInvoice(InvoiceDeleteModel arg) throws AccessTokenException;

    public BaseResult invalidInvoice(InvoiceInvalidArg.InvoiceInvalidData arg) throws AccessTokenException;
}
