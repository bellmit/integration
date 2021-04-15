package com.qunjie.crm.invoice.results;
/**
 * Created by whs on 2021/1/12.
 */

import com.google.common.base.MoreObjects;
import com.qunjie.crm.beans.results.BaseResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.oacrmbridge.crm.saleorder.results.SaleOrderResult
 *
 * @author whs
 *         Date:   2021/1/12  10:40
 *         Description: 添加保存后返回值
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class InvoiceResult extends BaseResult {

    private String dataId;

    private String errorDescription;

    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("dataId",this.dataId)
                .add("errorCode", this.errorCode)
                .add("errorMessage", this.errorMessage)
                .add("errorDescription",this.errorDescription)
                .toString();
    }



}
