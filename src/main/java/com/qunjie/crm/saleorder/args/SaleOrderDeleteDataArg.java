package com.qunjie.crm.saleorder.args;

import com.qunjie.crm.beans.args.BaseArg;
import com.qunjie.crm.utils.DefaultValues;
import lombok.Data;

import java.util.List;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.oacrmbridge.crm.saleorder.args.SaleOrderDeleteData
 *
 * @author whs
 * Date:   2021/1/14  16:33
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class SaleOrderDeleteDataArg extends BaseArg {

    private static final long serialVersionUID = 6772790814533624181L;

    private String currentOpenUserId;

    private SaleOrderDeleteModel data;

    @Data
    public static class SaleOrderDeleteModel{
        private List<String> idList;

        private String dataObjectApiName = DefaultValues.SALESORDEROBJ;
    }
}
