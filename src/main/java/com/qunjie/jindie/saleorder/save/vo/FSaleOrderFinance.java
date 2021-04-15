package com.qunjie.jindie.saleorder.save.vo;/**
 * Created by whs on 2020/12/8.
 */

import com.qunjie.common.annotation.Describe;
import com.qunjie.jindie.saleorder.save.pojo.Entity2;
import lombok.Data;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunjie.jindie.saleorder.save.vo.FSaleOrderFinance
 *
 * @author whs
 *         Date:   2020/12/8  14:38
 *         Description:
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
@Describe("财务信息")
@Data
public class FSaleOrderFinance {

    @Describe("实体主键")
    private Long FEntryId;

    @Describe("本位币")
    private Entity2 FLocalCurrId;

    @Describe("汇率类型")
    private Entity2 FExchangeTypeId;

    @Describe("汇率")
    private String FExchangeRate;

    @Describe("预收比例%")
    private String FPayAdvanceRate;

    @Describe("预收金额")
    private String FPayAdvanceAmount;

    @Describe("折扣表")
    private Entity2 FDiscountListId;

    @Describe(value = "结算币别",describe = "必填")
    private Entity2 FSettleCurrId;

    @Describe("税额（本位币）")
    private String FBillTaxAmount_LC;

    @Describe("金额（本位币）")
    private String FBillAmount_LC;

    @Describe("价税合计")
    private String FBillAllAmount;

    @Describe("价税合计（本位币）")
    private String FBillAllAmount_LC;

    @Describe("价目表")
    private Entity2 FPriceListId;

    @Describe("税额")
    private String FBillTaxAmount;

    @Describe("金额")
    private String FBillAmount;

    @Describe("是否含税")
    private String FIsIncludedTax;

    @Describe("需要预收")
    private String FNeedPayAdvance;

    @Describe("收款单号")
    private String FRecBillId;

    @Describe("收款条件")
    private Entity2 FRecConditionId;

    @Describe("结算方式")
    private Entity2 FSettleModeId;

    @Describe("关联应收金额（出库）")
    private String FJoinStockAmount;

    @Describe("关联应收金额（订单）")
    private String FJoinOrderAmount;

    @Describe("工作流信用检查状态")
    private String FCreChkStatus;

    @Describe("工作流信用超标天数")
    private String FCreChkDays;

    @Describe("工作流信用超标金额")
    private String FCreChkAmount;

    @Describe("审批流信用压批月结检查")
    private String FCrePreBatAndMonStatus;

    @Describe("信用压批超标")
    private String FCrePreBatchOver;

    @Describe("信用月结超标")
    private String FCreMonControlOver;

    @Describe("价外税")
    private String FIsPriceExcludeTax;

    @Describe("保证金比例（%）")
    private Long FMarginLevel;

    @Describe("关联保证金")
    private String FAssociateMargin;

    @Describe("保证金")
    private Long FMargin;

    @Describe("关联退款保证金")
    private String FAssRefundMargin;

    @Describe("工作流信用逾期超标额度")
    private String FCreChkOverAmount;

    @Describe("寄售生成跨组织调拨")
    private String FOverOrgTransDirect;

    @Describe("收款通知单号")
    private String FRecNoticeNo;

    @Describe("收款二维码链接")
    private String FRecBarcodeLink;

    @Describe("订单来源")
    private String FSOFrom;

    public FSaleOrderFinance() {
    }

    public FSaleOrderFinance(Entity2 FSettleCurrId) {
        this.FSettleCurrId = FSettleCurrId;
    }
}
