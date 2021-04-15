package com.qunjie.jindie.saleorder.save.constants;
/**
 * Created by whs on 2020/12/10.
 */

import com.qunjie.common.annotation.Describe;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunjie.jindie.saleorder.save.enums.DefaultValue
 *
 * @author whs
 *         Date:   2020/12/10  17:07
 *         Description:  推送金蝶数据中的默认值
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
public class DefaultValue {
    @Describe("单据类型")
    public static final String FBILLTYPEID = "XSDD01_SYS";
    @Describe("结算组织")
    public static final String FSETTLEORGIDS = "100";
    @Describe("结算币别")
    public static final String FSETTLECURRID = "PRE001";
    @Describe(value = "汇率",describe = "和结算币别相关")
    public static final String FEXCHANGERATE = "1";
    @Describe("销售组织")
    public static final String FSALEORGID = "100";

    @Describe("本位币")
    public static final String FLOCALCURRID = "PRE001";
    @Describe("汇率类型")
    public static final String FEXCHANGETYPEID = "HLTX01_SYS";

    @Describe("纳税登记号")
    public static final String FTAXREGISTERCODE = "未知";

    @Describe("开户银行")
    public static final String FOPENBANKNAME = "FOPENBANKNAME";

    @Describe("客户类型")
    public static final String FCUSTTYPEID = "KHLB004_SYS";

    @Describe("客户类别-渠道客户")
    public static final String FCUSTTYPEID2 = "KHLB005_SYS";
}
