package com.qunjie.crm.invoice.args;

import com.qunjie.crm.beans.args.BaseArg;
import com.qunjie.crm.utils.DefaultValues;
import lombok.Data;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.invoice.args.InvoiceAddArg
 *
 * @author whs
 * Date:   2021/1/20  14:08
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class InvoiceAddArg extends BaseArg {

    private String currentOpenUserId = DefaultValues.CURRENTOPENUSERID;

    private InvoiceModel data;
}
