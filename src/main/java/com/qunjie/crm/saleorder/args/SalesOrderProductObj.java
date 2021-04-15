package com.qunjie.crm.saleorder.args;

import lombok.Data;

import java.util.List;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.oacrmbridge.crm.saleorder.args.SalesOrderProductObj
 *
 * @author whs
 * Date:   2021/1/13  9:36
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class SalesOrderProductObj {
    private List<SaleOrderDetail> SalesOrderProductObj;

    public SalesOrderProductObj(List<SaleOrderDetail> salesOrderProductObj) {
        SalesOrderProductObj = salesOrderProductObj;
    }
}
