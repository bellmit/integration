package com.qunjie.jindie.invoice.constants;

import com.qunjie.common.annotation.Describe;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.jindie.invoice.constants.DefaultValue
 *
 * @author whs
 * Date:   2021/1/19  10:18
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
public class DefaultValue {

    //销售组织
    public static final String FSALEORGID = com.qunjie.jindie.saleorder.save.constants.DefaultValue.FSALEORGID;

    //订单类型
    public static final String FBILLTYPEID = "XSZZSZYFP01_SYS";

    @Describe("结算币别")
    public static final String FSETTLECURRID = com.qunjie.jindie.saleorder.save.constants.DefaultValue.FSETTLECURRID;

    @Describe("开票方式:1手动，0税控")
    public static final String FBILLINGWAY = "1";

    @Describe("红蓝字,默认蓝字")
    public static final String FREDBLUE = "0";
}
