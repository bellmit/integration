package com.qunjie.jindie.saleorder.save.vo;/**
 * Created by whs on 2020/12/8.
 */

import com.qunjie.common.annotation.Describe;
import com.qunjie.jindie.saleorder.save.pojo.Entity1;
import com.qunjie.jindie.saleorder.save.pojo.Entity2;
import lombok.Data;

import java.util.List;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunjie.jindie.saleorder.save.vo.FSaleOrderPlan
 *
 * @author whs
 *         Date:   2020/12/8  14:52
 *         Description:
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
@Describe("收款计划")
@Data
public class FSaleOrderPlan {

    @Describe("实体主键")
    private Long FEntryID;

    @Describe(value = "是否预收",describe = "默认预收")
    private String FNeedRecAdvance = "true";

    @Describe("应收比例(%)")
    private Double FRecAdvanceRate;

    @Describe("到期日")
    private String FMustDate;

    @Describe("应收金额")
    private Double FRecAdvanceAmount;

    @Describe("实收金额")
    private Double FRecAmount;

    @Describe("关联单号")
    private String FRelBillNo;

    @Describe("备注")
    private String FReMark;

    @Describe("收款类型")
    private Entity2 FReceiveType;

    @Describe("控制环节")
    private String FControlSend;

    @Describe("物料编码")
    private Entity1 FPlanMaterialId;

    @Describe("物料名称")
    private String FPlanMaterialName;

    @Describe("物料行号")
    private Long FMaterialSeq;

    @Describe("订单明细行内码")
    private Long FOrderEntryId;

    @Describe("关联金额")
    private String FASSAMOUNTFOR;

    @Describe("关联扣减金额")
    private String FASSDEDAMOUNTFOR;

    @Describe("按实际预收控制发货")
    private String FIsOutStockByRecamount;

    @Describe("物料行标识")
    private String FMaterialRowID;

    @Describe("聚合收款金额")
    private String FAGGRecAmount;

    @Describe("收款计划子单据体")
    private List<FSaleOrderPlanEntry> FSaleOrderPlanEntry;

    public FSaleOrderPlan() {
    }

    public FSaleOrderPlan(List<FSaleOrderPlanEntry> FSaleOrderPlanEntry) {
        this.FSaleOrderPlanEntry = FSaleOrderPlanEntry;
    }
}
