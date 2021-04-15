package com.qunjie.jindie.invoice.model;

import com.qunjie.common.annotation.Describe;
import com.qunjie.jindie.args.BaseArg;
import com.qunjie.jindie.invoice.vo.FBillHead;
import lombok.Data;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.jindie.invoice.model.InvoiceEntity
 *
 * @author whs
 * Date:   2021/1/18  16:20
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class InvoiceEntity extends BaseArg {

    @Describe("表单数据包，JSON类型（必录）")
    private FBillHead Model;

    public InvoiceEntity(FBillHead model) {
        Model = model;
    }
}
