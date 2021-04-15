package com.qunjie.crm.saleorder.results;
/**
 * Created by whs on 2021/1/12.
 */

import com.google.common.base.MoreObjects;
import com.qunjie.crm.beans.results.BaseResult;
import com.qunjie.crm.saleTarget.event.SaleTargetEvent;
import lombok.Data;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.oacrmbridge.crm.saleorder.results.SaleOrderResult
 *
 * @author whs
 *         Date:   2021/1/12  10:40
 *         Description:
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
@Data
public class SaleOrderResult extends BaseResult {
    private static final long serialVersionUID = 1431509086838485413L;
    private String dataId;

    private SaleTargetEvent saleTargetEvent;

    private String errorDescription;

    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("dataId",this.dataId)
                .add("errorCode", this.errorCode)
                .add("errorMessage", this.errorMessage)
                .add("errorDescription",this.errorDescription)
                .add("saleTargetEvent",this.saleTargetEvent)
                .toString();
    }

}
