package com.qunjie.crm.huikuan.args;

import lombok.Data;

import java.util.List;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.huikuan.args.HuikuanProductObj
 *
 * @author whs
 * Date:   2021/1/27  10:05
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class HuikuanProductObj {

    private List<HuikuanDetail> OrderPaymentObj;

    public HuikuanProductObj(List<HuikuanDetail> orderPaymentObj) {
        OrderPaymentObj = orderPaymentObj;
    }
}
