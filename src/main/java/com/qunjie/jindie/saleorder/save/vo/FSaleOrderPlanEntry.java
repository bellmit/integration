package com.qunjie.jindie.saleorder.save.vo;/**
 * Created by whs on 2020/12/8.
 */

import com.qunjie.common.annotation.Describe;
import com.qunjie.jindie.saleorder.save.pojo.Entity2;
import lombok.Data;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunjie.jindie.saleorder.save.vo.FSaleOrderPlanEntry
 *
 * @author whs
 *         Date:   2020/12/8  14:58
 *         Description:
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
@Describe("收款计划子单据体")
@Data
public class FSaleOrderPlanEntry {

    private Long FDETAILID;

    @Describe("实收金额")
    private String FActRecAmount;

    @Describe("预收单号")
    private String FADVANCENO;

    @Describe("预收单分录行号")
    private String FADVANCESEQ;

    @Describe("预收单未关联金额")
    private String FRemainAmount;

    @Describe("预收单分录内码")
    private String FADVANCEENTRYID;

    @Describe("预收单内码")
    private String FADVANCEID;

    @Describe("预收已核销金额")
    private String FPreMatchAmountFor;

    @Describe("结算组织")
    private Entity2 FPESettleOrgId;

    public FSaleOrderPlanEntry() {
    }

    public FSaleOrderPlanEntry(Long FDETAILID, String FActRecAmount, String FADVANCENO, String FADVANCESEQ, String FRemainAmount, String FADVANCEENTRYID, String FADVANCEID, String FPreMatchAmountFor, Entity2 FPESettleOrgId) {
        this.FDETAILID = FDETAILID;
        this.FActRecAmount = FActRecAmount;
        this.FADVANCENO = FADVANCENO;
        this.FADVANCESEQ = FADVANCESEQ;
        this.FRemainAmount = FRemainAmount;
        this.FADVANCEENTRYID = FADVANCEENTRYID;
        this.FADVANCEID = FADVANCEID;
        this.FPreMatchAmountFor = FPreMatchAmountFor;
        this.FPESettleOrgId = FPESettleOrgId;
    }
}
