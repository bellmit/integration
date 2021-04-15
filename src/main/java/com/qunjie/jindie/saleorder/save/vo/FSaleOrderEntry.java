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
 * FileName: com.qunjie.jindie.saleorder.save.vo.FSaleOrderEntry
 *
 * @author whs
 *         Date:   2020/12/8  13:44
 *         Description:
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
@Data
@Describe("订单明细")
public class FSaleOrderEntry {

    @Describe("实体主键")
    private Long FEntryID;

    @Describe(value = "物料编码",describe = "必填")
    private Entity2 FMaterialId;

    @Describe("物料名称")
    private String FMaterialName;

    @Describe("规格型号")
    private String FMaterialModel;

    @Describe("物料类别")
    private String FMaterialType;

    @Describe(value = "销售单位",describe = "必填")
    private Entity2 FUnitID;

    @Describe("单价")
    private Double FPrice;

    @Describe("含税单价")
    private Double FTaxPrice;

    @Describe("BOM版本")
    private String FBomId;

    @Describe("计价单位")
    private String FPriceUnitId;

    @Describe("计价数量")
    private String FPriceUnitQty;

    @Describe("价格系数")
    private String FPriceCoefficient;

    @Describe("折扣率%")
    private Double FDiscountRate;

    @Describe("折扣额")
    private String FDiscount;

    @Describe("税率%")
    private Double FEntryTaxRate;

    @Describe("税额")
    private String FEntryTaxAmount;

    @Describe("价税合计")
    private String FAllAmount;

    @Describe("净价")
    private String FTaxNetPrice;

    @Describe("销售基本数量")
    private String FBaseUnitQty;

    @Describe("控制发货数量")
    private String FDeliveryControl;

    @Describe("发货上限")
    private String FDeliveryMaxQty;

    @Describe("发货下限")
    private String FDeliveryMinQty;

    @Describe("运输提前期")
    private String FTransportLeadTime1;

    @Describe("折前价税合计")
    private String FBefDisAllAmt;

    @Describe("折前金额")
    private String FBefDisAmt;

    @Describe("税额（本位币）")
    private String FTaxAmount_LC;

    @Describe("金额（本位币）")
    private String FAmount_LC;

    @Describe("价税合计（本位币）")
    private String FAllAmount_LC;

    @Describe("业务关闭")
    private String FMrpCloseStatus;

    @Describe("业务冻结")
    private String FMrpFreezeStatus;

    @Describe("冻结人")
    private String FFreezerId;

    @Describe("冻结日期")
    private String FFreezeDate;

    @Describe("业务终止")
    private String FMrpTerminateStatus;

    @Describe("终止人")
    private String FTerminaterId;

    @Describe("终止日期")
    private String FTerminateDate;

    @Describe("（代码有用）关联发货通知数量（销售基本）")
    private String FBaseDeliJoinQty;

    @Describe("累计发货通知数量")
    private String FDeliQty;

    @Describe("累计出库数量")
    private String FStockOutQty;

    @Describe("累计退货通知数量（销售）")
    private String FRetNoticeQty;

    @Describe("累计退货数量（销售）")
    private String FReturnQty;

    @Describe("剩余未出数量（销售）")
    private String FRemainOutQty;

    @Describe("关联开票数量（基本）")
    private String FBaseInvoiceJoinQty;

    @Describe("关联开票数量")
    private String FInvoiceJoinQty;

    @Describe("累计开票数量")
    private String  FInvoiceQty;

    @Describe("累计开票金额")
    private String FInvoiceAmount;

    @Describe("累计收款金额")
    private String FReceiveAmount;

    @Describe("关联采购/生产数量（销售基本）")
    private String FBasePurJoinQty;

    @Describe("关联采购/生产数量")
    private String FPurJoinQty;

    @Describe("累计采购申请数量")
    private String FPurReqQty;

    @Describe("累计采购订单数量")
    private String FPurOrderQty;

    @Describe("收款组织")
    private String FReceiptOrgId;

    @Describe(value = "结算组织",describe = "必填")
    private Entity2 FSettleOrgIds;

    @Describe("金额")
    private String FAmount;

    @Describe("备注")
    private String FEntryNote;

    @Describe("销售数量")
    private Long FQty;

    @Describe("最低限价")
    private String FLimitDownPrice;

    @Describe("系统定价")
    private String FSysPrice;

    @Describe("库存组织")
    private Entity2 FStockOrgId;

    @Describe("累计出库数量（销售基本）")
    private String FBaseStockOutQty;

    @Describe("累计发货通知数量（销售基本）")
    private String FBaseDeliQty;

    @Describe("累计退货通知数量（销售基本）")
    private String FBaseRetNoticeQty;

    @Describe("累计退货数量（销售基本）")
    private String FBaseReturnQty;

    @Describe("累计采购申请数量（销售基本）")
    private String FBasePurReqQty;

    @Describe("累计采购订单数量（销售基本）")
    private String FBasePurOrderQty;

    @Describe("基本单位")
    private String FBaseUnitId;

    @Describe("变更标志")
    private String FChangeFlag;

    @Describe("客户物料编码")
    private Entity2 FMapId;

    @Describe("货主类型")
    private String FOwnerTypeId;

    @Describe("货主")
    private Entity2 FOwnerId;

    @Describe("是否赠品")
    private String FIsFree;

    @Describe("锁库/预留数量(库存单位)")
    private String FLOCKQTY;

    @Describe("锁库/预留标识")
    private String FLOCKFLAG;

    @Describe("生产日期")
    private String FProduceDate;

    @Describe("有效期至")
    private String FExpiryDate;

    @Describe("保质期单位")
    private String FExpUnit;

    @Describe("保质期")
    private Long FExpPeriod;

    @Describe("物料允许生产属性")
    private String FMaterialIsProduce;

    @Describe("税组合")
    private Entity2 FTaxCombination;

    @Describe("批号")
    private Entity2 FLot;

    @Describe("客户物料名称")
    private String FMapName;

    @Describe("辅助属性")
    private Object FAuxPropId;

    @Describe("退补类型")
    private String FReturnType;

    @Describe(value = "要货日期",describe = "必填")
    private String FDeliveryDate;

    @Describe("关联调拨数量")
    private String FTransJoinQty;

    @Describe("关联调拨数量(销售基本)")
    private String FBaseTransJoinQty;

    @Describe("源单类型")
    private String FSrcType;

    @Describe("源单编号")
    private String FSrcBillNo;

    @Describe("发货上限（基本）")
    private String FBaseDeliveryMaxQty;

    @Describe("发货下限（基本）")
    private String FBaseDeliveryMinQty;

    @Describe("关联受托材料入库套数")
    private String FOEMInStockJoinQty;

    @Describe("关联受托材料入库套数(库存基本)")
    private String FBaseOEMInStockJoinQty;

    @Describe("关联应收数量（计价基本）")
    private String FBaseARJoinQty;

    @Describe("物料允许退货属性")
    private String FIsReturn;

    @Describe("当前库存")
    private Long FInventoryQty;

    @Describe("业务流程")
    private String FBFLowId;

    @Describe("物料允许库存属性")
    private String FIsInventory;

    @Describe("累计应收数量（销售基本）")
    private String FBASEARQTY;

    @Describe("关联应收金额")
    private String FARJOINAMOUNT;

    @Describe("累计应收金额")
    private String FARAMOUNT;

    @Describe("剩余未出数量（销售基本）")
    private String FBaseRemainOutQty;

    @Describe("累计退货补货数量（销售）")
    private String FReBackQty;

    @Describe("累计退货补货数量（销售基本）")
    private String FBaseReBackQty;

    @Describe("累计应收数量（销售）")
    private String FARQTY;

    @Describe("物料允许委外属性")
    private String FMaterialIsSubContract;

    @Describe("可出数量（销售）")
    private String FCanOutQty;

    @Describe("可出数量（销售基本）")
    private String FBaseCanOutQty;

    @Describe("可退数量（销售）")
    private String FCanReturnQty;

    @Describe("可退数量（销售基本）")
    private String FBaseCanReturnQty;

    @Describe("累计应付数量(基本单位)")
    private String FBASEAPQTY;

    @Describe("累计应付金额")
    private String FAPAMOUNT;

    @Describe("计划跟踪号")
    private String FMtoNo;

    @Describe("需求优先级")
    private Long FPriority;

    @Describe(value = "预留类型",describe = "必填")
    private String FReserveType;

    @Describe("计划交货日期")
    private String FMinPlanDeliveryDate;

    @Describe("发货状态")
    private String FDeliveryStatus;

    @Describe("原数量")
    private Long FOldQty;

    @Describe("促销匹配类型")
    private String FPromotionMatchType;

    @Describe("行价目表")
    private String FPriceListEntry;

    @Describe("待发数量")
    private Long FAwaitQty;

    @Describe("可发库存")
    private Long FAvailableQty;

    @Describe("供应组织")
    private Entity2 FSupplyOrgId;

    @Describe("供应商协同平台订单分录ID")
    private Long FNetOrderEntryId;

    @Describe("计价基本数量")
    private Long FPriceBaseQty;

    @Describe("定价单位")
    private String FSetPriceUnitID;

    @Describe("库存单位")
    private Entity2 FStockUnitID;

    @Describe("库存数量")
    private Long FStockQty;

    @Describe("库存基本数量")
    private Long FStockBaseQty;

    @Describe("可出数量（库存基本）")
    private String FStockBaseCanOutQty;

    @Describe("可退数量（库存基本）")
    private String FStockBaseCanReturnQty;

    @Describe("关联应收数量（库存基本）")
    private String FStockBaseARJoinQty;

    @Describe("关联调拨数量（库存基本）")
    private String FStockBaseTransJoinQty;

    @Describe("服务上下文")
    private String FServiceContext;

    @Describe("关联采购/生产数量（库存基本）")
    private String FStockBasePurJoinQty;

    @Describe("源单基本分子")
    private String FSalBaseNum;

    @Describe("库存基本分母")
    private String FStockBaseDen;

    @Describe("携带主单位")
    private String FSRCBIZUNITID;

    @Describe("采购基本数量")
    private String FPurBaseQty;

    @Describe("采购单位")
    private String FPurUnitID;

    @Describe("采购数量")
    private String FPurQty;

    @Describe("关联应收数量（销售基本）")
    private String FSalBaseARJoinQty;

    @Describe("累计出库数量（库存基本）")
    private String FSTOCKBASESTOCKOUTQTY;

    @Describe("累计退货补货数量（库存基本）")
    private String FSTOCKBASEREBACKQTY;

    @Describe(value = "超发控制单位类型",describe = "必填")
    private String FOUTLMTUNIT;

    @Describe("超发控制单位")
    private Entity2 FOutLmtUnitID;

    @Describe("累计调拨退货数量")
    private String FTRANSRETURNQTY;

    @Describe("累计调拨退货数量（销售基本）")
    private String FTRANSRETURNBASEQTY;

    @Describe("寄售结算数量")
    private String FCONSIGNSETTQTY;

    @Describe("寄售结算数量（销售基本）")
    private String FCONSIGNSETTBASEQTY;

    @Describe("待锁库/待预留数量(库存单位)")
    private String FLeftQty;

    @Describe("可用库存")
    private Long FCurrentInventory;

    @Describe("产品类型")
    private String FRowType;

    @Describe("父项产品")
    private Entity1 FParentMatId;

    @Describe("行标识")
    private String FRowId;

    @Describe("父行标识")
    private String FParentRowId;

    @Describe("最新采购入库价")
    private Double FInStockPrice;

    @Describe("仓库")
    private Entity1 FSOStockId;

    @Describe("仓位")
    private Object FSOStockLocalId;

    @Describe("采购计价单位")
    private Entity2 FPurPriceUnitId;

    @Describe("已计划运算")
    private String FISMRP;

    @Describe("零售条形码")
    private String FBarcode;

    @Describe("发货门店")
    private String FBranchId;

    @Describe("是否零售促销")
    private String FRetailSaleProm;

    @Describe("先开票数量（计价基本）")
    private String FBASEFINARQTY;

    @Describe("先开票数量（销售基本）")
    private String FSALBASEFINARQTY;

    @Describe("行折扣表")
    private String FEntryDiscountList;

    @Describe("单价折扣")
    private Double FPriceDiscount;

    @Describe("交货明细")
    private List<FOrderEntryPlan> FOrderEntryPlan;

    @Describe("税务明细")
    private List<FTaxDetailSubEntity> FTaxDetailSubEntity;
}
