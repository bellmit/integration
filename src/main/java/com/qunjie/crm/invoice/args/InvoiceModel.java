package com.qunjie.crm.invoice.args;

import lombok.Data;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.invoice.args.InvoiceModel
 *
 * @author whs
 * Date:   2021/1/20  14:13
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class InvoiceModel {

    private InvoiceObjectData object_data;

    private InvoiceProductObj details;

    public InvoiceModel(InvoiceObjectData object_data, InvoiceProductObj details) {
        this.object_data = object_data;
        this.details = details;
    }
}
