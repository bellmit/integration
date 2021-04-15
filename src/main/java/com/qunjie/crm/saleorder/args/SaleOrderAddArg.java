package com.qunjie.crm.saleorder.args;
/**
 * Created by whs on 2021/1/12.
 */

import com.google.common.base.MoreObjects;
import com.qunjie.crm.saleorder.args.SaleOrderModel;
import com.qunjie.crm.beans.args.BaseArg;
import com.qunjie.crm.utils.DefaultValues;
import lombok.Data;

import java.util.List;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.oacrmbridge.crm.saleorder.args.SaleOrderAddArg
 *
 * @author whs
 *         Date:   2021/1/12  10:58
 *         Description:
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
@Data
public class SaleOrderAddArg extends BaseArg {
    private static final long serialVersionUID = 4710297898085621687L;

    private String currentOpenUserId = DefaultValues.CURRENTOPENUSERID;

    private SaleOrderModel data;

}
