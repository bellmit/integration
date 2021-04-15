package com.qunjie.jindie.invoice.vo;

import com.qunjie.common.annotation.Describe;
import com.qunjie.jindie.saleorder.save.pojo.Entity2;
import lombok.Data;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.jindie.invoice.vo.FcostEntry
 *
 * @author whs
 * Date:   2021/1/18  16:08
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Describe("成本明细")
@Data
public class FcostEntry {

    @Describe("实体主键")
    private Long FEntryID;

    @Describe("货物编码")
    private String FMETRIALID;

    @Describe("货物名称")
    private String FMETRIALNAME;

    @Describe("规格型号")
    private String FMetrialModel;

    @Describe("货物类别")
    private String FMETRIALTYPE;

    @Describe("计价单位")
    private String FPriceUnit;

    @Describe("明细计价数量")
    private String FPriceUnitQty;

    @Describe("源出库单类型")
    private String FOUTSTOCKTYPE;

    @Describe("源单单号")
    private String FBILLNOSRC;

    @Describe("源单内码")
    private String FROWNOSRC;

    @Describe("核算体系")
    private String FACCTSYS;

    @Describe("会计政策")
    private String FACCTPOLICY;

    @Describe("成本价")
    private String FCOSTPrice;

    @Describe("成本金额")
    private String FCOSTAMT;

    @Describe("基本单位数量")
    private String FBaseQty;

    @Describe("源单行号")
    private String FSEQSRC;

    @Describe("明细内码")
    private String FDEntryID;

    @Describe("基本单位")
    private Entity2 FBASICUNIT;

}
