package com.qunjie.crm.saleorder.args;
/**
 * Created by whs on 2021/1/12.
 */

import lombok.Data;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.oacrmbridge.crm.saleorder.args.SaleOrderModel
 *
 * @author whs
 *         Date:   2021/1/12  11:01
 *         Description:
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
@Data
public class SaleOrderModel{

    private SaleOrderObjectData object_data;

    private SalesOrderProductObj details;

    public SaleOrderModel(SaleOrderObjectData object_data,SalesOrderProductObj details) {
        this.object_data = object_data;
        this.details = details;
    }

    public SaleOrderModel() {
    }
}
