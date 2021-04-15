package com.qunjie.jindie.huikuan.vo;

import com.qunjie.common.annotation.Describe;
import com.qunjie.jindie.saleorder.save.pojo.Entity1;
import com.qunjie.jindie.saleorder.save.pojo.Entity2;
import lombok.Data;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.jindie.huikuan.vo.FbillSkdrecEntry
 *
 * @author whs
 * Date:   2021/1/25  16:14
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class FBillSkdrecEntry {

    @Describe("实体主键")
    private Long FEntryID;
    @Describe("内部账户")
    private Entity1 FInnerActId;
    @Describe("票据流水号")
    private Entity1 FReceivebleBillId;
    @Describe("付款用途")
    private Entity1 FPayPurse;
    @Describe("背书退回金额")
    private Double FReturnAmount;
    @Describe("票据类型")
    private String FKDBPARBILLTYPE;
    @Describe("客户")
    private String FKDBPCUSTOMER;
    @Describe("票面金额")
    private Double FParAmount;
    @Describe("往来单位类型")
    private String FBCONTACTUNITTYPE;
    @Describe("往来单位")
    private Entity2 FBCONTACTUNIT;
    @Describe("票据号")
    private String FKDBPARBILLNO;
    @Describe("票面金额本位币")
    private Double FPARAMOUNTSTD;
    @Describe("背书退回金额本位币")
    private Double FReturnAmountStd;
}
