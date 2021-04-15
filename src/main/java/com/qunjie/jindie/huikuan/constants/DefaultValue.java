package com.qunjie.jindie.huikuan.constants;

import com.qunjie.common.annotation.Describe;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.jindie.huikuan.constants.DefaultValue
 *
 * @author whs
 * Date:   2021/1/25  14:48
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
public class DefaultValue {

    @Describe("结算组织")
    public static final String JSZZ = com.qunjie.jindie.saleorder.save.constants.DefaultValue.FSALEORGID;
    @Describe("收款组织")
    public static final String SKZZ = com.qunjie.jindie.saleorder.save.constants.DefaultValue.FSALEORGID;
    @Describe("销售组织")
    public static final String XSZZ = com.qunjie.jindie.saleorder.save.constants.DefaultValue.FSALEORGID;
    @Describe("币别")
    public static final String BB = com.qunjie.jindie.saleorder.save.constants.DefaultValue.FSETTLECURRID;
    @Describe("结算币别")
    public static final String JSBB = com.qunjie.jindie.saleorder.save.constants.DefaultValue.FSETTLECURRID;
    @Describe("结算本位币")
    public static final String JSBWB = com.qunjie.jindie.saleorder.save.constants.DefaultValue.FSETTLECURRID;

    @Describe("单据类型")
    public static final String DJLX = "SKDLX01_SYS";
    @Describe("付款单位类型")
    public static final String FKDJLX = "BD_Customer";
    @Describe("往来单位类型")
    public static final String WLDWLX = "BD_Customer";

    @Describe("结算方式")
    public static final String JSFS = "JSFS04_SYS";
    @Describe("收款用途")
    public static final String SKYT = "SFKYT01_SYS";

    @Describe(value = "预收项目类型",describe = "1:销售订单，2:客户")
    public static final String YSXMLX = "1";

    @Describe("我方银行账号")
    public static final String WFYHZH = "4301012819100044958";
}
