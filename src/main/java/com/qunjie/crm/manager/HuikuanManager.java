package com.qunjie.crm.manager;

import com.qunjie.crm.beans.results.BaseResult;
import com.qunjie.crm.beans.results.CrmAddResult;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.huikuan.args.HuikuanDeleteDataArg.HuikuanDeleteModel;
import com.qunjie.crm.huikuan.args.HuikuanInvalidArg;
import com.qunjie.crm.huikuan.args.HuikuanModel;
import com.qunjie.crm.invoice.args.InvoiceInvalidArg;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.manager.HuikuanManager
 *
 * @author whs
 * Date:   2021/1/27  14:20
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
public interface HuikuanManager {

    public CrmAddResult savePayment(HuikuanModel invoiceModel) throws AccessTokenException;

    public BaseResult deletePayment(HuikuanDeleteModel arg) throws AccessTokenException;

    public BaseResult invalidPayment(HuikuanInvalidArg.HuikuanInvalidData arg) throws AccessTokenException;
}
