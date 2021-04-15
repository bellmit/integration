package com.qunjie.jindie.saleorder.save.vo;/**
 * Created by whs on 2020/12/8.
 */

import com.qunjie.common.annotation.Describe;
import com.qunjie.jindie.saleorder.save.pojo.Entity2;
import lombok.Data;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunjie.jindie.saleorder.save.vo.FOrderEntryPlan
 *
 * @author whs
 *         Date:   2020/12/8  14:30
 *         Description:
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
@Data
@Describe("交货明细")
public class FOrderEntryPlan {

    @Describe("实体主键")
    private Long FDetailID;

    @Describe("仓库")
    private String FStockId;

    @Describe("销售单位")
    private String FPlanUnitId;

    @Describe("数量")
    private Long FPlanQty;

    @Describe("已出库数量")
    private String FDeliCommitQty;

    @Describe("剩余未出数量")
    private String FDeliRemainQty;

    @Describe("交货地点")
    private Entity2 FDetailLocId;

    @Describe("交货地址")
    private String FDetailLocAddress;

    @Describe("要货日期")
    private String FPlanDate;

    @Describe("计划发货日期")
    private String FPlanDeliveryDate;

    @Describe("运输提前期")
    private Long FTransportLeadTime;

    @Describe("数量（基本单位）")
    private Long FBasePlanQty;

    @Describe("已出库数量计划（基本单位）")
    private String FBaseDeliCommitQty;

    @Describe("剩余未出数量计划（基本单位）")
    private String FBaseDeliRemainQty;

    @Describe("基本单位")
    private String FPlanBaseUnitId;

    @Describe("已发货通知数量")
    private String FNOTICEQTY;

    @Describe("剩余未通知数量")
    private String FNOTICEREMAINQTY;

    @Describe("已发货通知数量（基本）")
    private String FNOTICEBASEQTY;

    @Describe("剩余未通知数量（基本）")
    private String FNOTICEREMAINBASEQTY;
}
