package com.qunjie.jindie.saleorder.save.vo;/**
 * Created by whs on 2020/12/8.
 */

import com.qunjie.jindie.saleorder.save.enums.FieldName;
import com.qunjie.axis.model.WorkflowRequestTable;
import com.qunjie.axis.model.WorkflowRequestTableField;
import com.qunjie.common.annotation.Describe;
import com.qunjie.jindie.saleorder.save.constants.DefaultValue;
import com.qunjie.jindie.saleorder.save.enums.QDXSDDFieldName;
import com.qunjie.jindie.saleorder.save.pojo.Entity1;
import com.qunjie.jindie.saleorder.save.pojo.Entity2;
import com.qunjie.jindie.saleorder.save.pojo.Entity3;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunjie.jindie.saleorder.save.vo.FBillHead
 *
 * @author whs
 *         Date:   2020/12/8  11:25
 *         Description:
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
@Data
@Describe("基本信息")
public class FBillHead {

    @Describe("实体主键")
    private Long FID;

    @Describe("单据编号")
    private String FBillNo;

    @Describe("单据状态")
    private String FDocumentStatus;

    @Describe(value = "销售组织",describe = "必填")
    private Entity2 FSaleOrgId;

    @Describe(value = "日期",describe = "必填")
    private String FDate;

    @Describe(value = "客户",describe = "必填")
    private Entity2 FCustId;

    @Describe("销售部门")
    private Entity2 FSaleDeptId;

    @Describe("销售组")
    private Entity2 FSaleGroupId;

    @Describe(value = "销售员",describe = "必填")
    private Entity2 FSalerId;

    @Describe("收货方")
    private Entity2 FReceiveId;

    @Describe("结算方")
    private Entity2 FSettleId;

    @Describe("结算方地址")
    private String FSettleAddress;

    @Describe("付款方")
    private Entity2 FChargeId;

    @Describe("创建人")
    private String FCreatorId;

    @Describe("创建日期")
    private String FCreateDate;

    @Describe("最后修改人")
    private String FModifierId;

    @Describe("最后修改日期")
    private String FModifyDate;

    @Describe("审核人")
    private String FApproverId;

    @Describe("审核日期")
    private String FApproveDate;

    @Describe("关闭状态")
    private String FCloseStatus;

    @Describe("关闭人")
    private String FCloserId;

    @Describe("关闭日期")
    private String FCloseDate;

    @Describe("作废状态")
    private String FCancelStatus;

    @Describe("作废人")
    private String FCancellerId;

    @Describe("作废日期")
    private String FCancelDate;

    @Describe("版本号")
    private String FVersionNo;

    @Describe("变更人")
    private String FChangerId;

    @Describe("变更日期")
    private String FChangeDate;

    @Describe("变更原因")
    private String FChangeReason;

    @Describe(value = "单据类型",describe = "必填")
    private Entity1 FBillTypeID;

    @Describe("业务类型")
    private String FBusinessType;

    @Describe("交货方式")
    private Entity2 FHeadDeliveryWay;

    @Describe("收货方地址")
    private String FReceiveAddress;

    @Describe("交货地点")
    private Entity2 FHEADLOCID;

    @Describe("信用检查结果")
    private String FCreditCheckResult;

    @Describe("对应组织")
    private Entity2 FCorrespondOrgId;

    @Describe("收货方联系人")
    private Entity3 FReceiveContact;

    @Describe("移动销售订单编号")
    private String FNetOrderBillNo;

    @Describe("移动销售订单ID")
    private Long FNetOrderBillId;

    @Describe("商机内码")
    private Long FOppID;

    @Describe("销售阶段")
    private Entity1 FSalePhaseID;

    @Describe("是否期初单据")
    private String FISINIT;

    @Describe("备注")
    private String FNote;

    @Describe("来自移动")
    private String FIsMobile;

    @Describe("签收状态")
    private String FSignStatus;

    @Describe("是否直接变更过程中（不存储）")
    private String FIsDirectChange;

    @Describe("是否手工关闭")
    private String FManualClose;

    @Describe("收货人姓名")
    private String FLinkMan;

    @Describe("联系电话")
    private String FLinkPhone;

    @Describe("上传文件")
    private String F_TOM_FileUpdate ;

    @Describe("合作伙伴名称")
    private String F_TOM_TEXT;

    @Describe("财务信息")
    private FSaleOrderFinance FSaleOrderFinance;

    @Describe("订单条款")
    private List<FSaleOrderClause> FSaleOrderClause = new ArrayList<>();

    @Describe("订单明细")
    private List<FSaleOrderEntry> FSaleOrderEntry = new ArrayList<>();

    @Describe("收款计划")
    private List<FSaleOrderPlan> FSaleOrderPlan = new ArrayList<>();

    @Describe("物流跟踪明细")
    private List<FSalOrderTrace> FSalOrderTrace = new ArrayList<>();

    public FBillHead() {
    }

    public FBillHead(Entity2 FSaleOrgId, String FDate, Entity2 FCustId, Entity2 FSalerId, Entity1 FBillTypeID, FSaleOrderFinance FSaleOrderFinance, List<FSaleOrderClause> FSaleOrderClause, List<FSaleOrderEntry> FSaleOrderEntry, List<FSaleOrderPlan> FSaleOrderPlan, List<FSalOrderTrace> FSalOrderTrace) {
        this.FSaleOrgId = FSaleOrgId;
        this.FDate = FDate;
        this.FCustId = FCustId;
        this.FSalerId = FSalerId;
        this.FBillTypeID = FBillTypeID;
        this.FSaleOrderFinance = FSaleOrderFinance;
        this.FSaleOrderClause = FSaleOrderClause;
        this.FSaleOrderEntry = FSaleOrderEntry;
        this.FSaleOrderPlan = FSaleOrderPlan;
        this.FSalOrderTrace = FSalOrderTrace;
    }

    public Map<String,String> valueOf(WorkflowRequestTable workflowRequestTable){


        Map<String,String> map = new HashMap<>();
        List<List<WorkflowRequestTableField>> details = workflowRequestTable.getDetails();
        List<WorkflowRequestTableField> mains = workflowRequestTable.getMains();
        //赋值默认值
        this.setFBillTypeID(new Entity1(DefaultValue.FBILLTYPEID));
        this.setFSaleOrgId(new Entity2(DefaultValue.FSALEORGID));

        //财务信息
        FSaleOrderFinance fSaleOrderFInance = new FSaleOrderFinance();
        fSaleOrderFInance.setFSettleCurrId(new Entity2(DefaultValue.FSETTLECURRID));
        fSaleOrderFInance.setFExchangeRate(DefaultValue.FEXCHANGERATE);
        fSaleOrderFInance.setFExchangeTypeId(new Entity2(DefaultValue.FEXCHANGETYPEID));
        fSaleOrderFInance.setFLocalCurrId(new Entity2(DefaultValue.FLOCALCURRID));

        //单据主数据赋值动态值
        mains.forEach(e->{
            if (!StringUtils.isBlank(e.getFieldName()) && null != FieldName.valuesOf(e.getFieldName())){
                switch (FieldName.valuesOf(e.getFieldName())){
                    case KH:
                        if (!StringUtils.isBlank(e.getFieldValue())) {
                            this.setFCustId(new Entity2(e.getFieldValue()));
                        }
                        break;
                    case HBKHBM:
                        if (!StringUtils.isBlank(e.getFieldValue())) {
                            this.setFCustId(new Entity2(e.getFieldValue()));
                        }
                        break;
                    case HZHBMC:
                        if (!StringUtils.isBlank(e.getFieldValue())) {
                            this.F_TOM_TEXT = e.getFieldValue();
                        }
                        break;
                    case XSY:
                        this.setFSalerId(new Entity2(e.getFieldValue()));
                        break;
                    case BILLNO:
                        this.setFBillNo(e.getFieldValue());
                        break;
                    case QYRQ:
                        this.setFDate(e.getFieldValue());
                        break;
                    case SFKJXY:
                        map.put(FieldName.SFKJXY.name(),e.getFieldValue());
                    default:
                        break;
                }
            }
        });
        //单据明细赋值--遍历明细条数
        if (!CollectionUtils.isEmpty(details)) {
            details.forEach(detail -> {
                //订单明细
                FSaleOrderEntry fSaleOrderEntry = new FSaleOrderEntry();
                fSaleOrderEntry.setFSettleOrgIds(new Entity2(DefaultValue.FSETTLEORGIDS));
                //订单明细-----交货明细
                List<FOrderEntryPlan> lists = new ArrayList<>();
                FOrderEntryPlan fOrderEntryPlan = new FOrderEntryPlan();
                //遍历每条明细属性
                if (!CollectionUtils.isEmpty(detail)) {
                    detail.forEach(e -> {
                        if (!StringUtils.isBlank(e.getFieldName()) && null != FieldName.valuesOf(e.getFieldName())){
                            switch (FieldName.valuesOf(e.getFieldName())){
                                case JHRQ:
                                    fSaleOrderEntry.setFDeliveryDate(e.getFieldValue());
                                    break;
                                case CPBM:
                                    fSaleOrderEntry.setFMaterialId(new Entity2(e.getFieldValue()));
                                    break;
                                case SL:
                                    Long num = 0L;
                                    try {
                                        num = Long.valueOf(e.getFieldValue());
                                    } catch (NumberFormatException e1) {
                                        e1.printStackTrace();
                                    }
                                    fOrderEntryPlan.setFPlanQty(num);
                                    fSaleOrderEntry.setFQty(num);
                                    break;
                                case JDHSDJ:
                                    Double price = 0.0;
                                    try {
                                        price = Double.valueOf(e.getFieldValue());
                                    }catch (NumberFormatException e2){
                                        e2.printStackTrace();
                                    }
                                    fSaleOrderEntry.setFTaxPrice(price);
                                    break;
                                case JDSFZP:
                                    int i = Integer.valueOf(e.getFieldValue()).intValue();
                                    if (i == 0)
                                        fSaleOrderEntry.setFIsFree("1");
                                    break;
                                default:
                                    break;
                            }
                        }
                    });
                }
                //明细数量为0的不传
                if (fOrderEntryPlan.getFPlanQty() != null && fOrderEntryPlan.getFPlanQty().intValue() != 0) {
                    lists.add(fOrderEntryPlan);
                    fSaleOrderEntry.setFOrderEntryPlan(lists);
                    this.FSaleOrderEntry.add(fSaleOrderEntry);
                }
            });
        }
        this.FSaleOrderFinance = fSaleOrderFInance;
        this.FSaleOrderPlan.add(new FSaleOrderPlan());
        return map;
    }

    public Map<String, String> QDXSDDvalueOf(WorkflowRequestTable workflowRequestTable) {
        Map<String,String> map = new HashMap<>();
        List<List<WorkflowRequestTableField>> details = workflowRequestTable.getDetails();
        List<WorkflowRequestTableField> mains = workflowRequestTable.getMains();
        //赋值默认值
        this.setFBillTypeID(new Entity1(DefaultValue.FBILLTYPEID));
        this.setFSaleOrgId(new Entity2(DefaultValue.FSALEORGID));

        //财务信息
        FSaleOrderFinance fSaleOrderFInance = new FSaleOrderFinance();
        fSaleOrderFInance.setFSettleCurrId(new Entity2(DefaultValue.FSETTLECURRID));
        fSaleOrderFInance.setFExchangeRate(DefaultValue.FEXCHANGERATE);
        fSaleOrderFInance.setFExchangeTypeId(new Entity2(DefaultValue.FEXCHANGETYPEID));
        fSaleOrderFInance.setFLocalCurrId(new Entity2(DefaultValue.FLOCALCURRID));

        //单据主数据赋值动态值
        mains.forEach(e->{
            if (!StringUtils.isBlank(e.getFieldName()) && null != QDXSDDFieldName.valuesOf(e.getFieldName())){
                switch (QDXSDDFieldName.valuesOf(e.getFieldName())){
                    case KHBM:
                        if (!StringUtils.isBlank(e.getFieldValue())) {
                            this.setFCustId(new Entity2(e.getFieldValue()));
                        }
                        break;
                    case DLSMC:
                        if (!StringUtils.isBlank(e.getFieldValue())) {
                            this.F_TOM_TEXT = e.getFieldValue();
                        }
                        break;
                    case XSYBM:
                        this.setFSalerId(new Entity2(e.getFieldValue()));
                        break;
                    case DH:
                        this.setFBillNo(e.getFieldValue());
                        break;
                    case SQRQ:
                        this.setFDate(e.getFieldValue());
                        break;
                    default:
                        break;
                }
            }
        });
        //单据明细赋值--遍历明细条数
        if (!CollectionUtils.isEmpty(details)) {
            details.forEach(detail -> {
                //订单明细
                FSaleOrderEntry fSaleOrderEntry = new FSaleOrderEntry();
                fSaleOrderEntry.setFSettleOrgIds(new Entity2(DefaultValue.FSETTLEORGIDS));
                //订单明细-----交货明细
                List<FOrderEntryPlan> lists = new ArrayList<>();
                FOrderEntryPlan fOrderEntryPlan = new FOrderEntryPlan();
                //遍历每条明细属性
                if (!CollectionUtils.isEmpty(detail)) {
                    detail.forEach(e -> {
                        if (!StringUtils.isBlank(e.getFieldName()) && null != QDXSDDFieldName.valuesOf(e.getFieldName())){
                            switch (QDXSDDFieldName.valuesOf(e.getFieldName())){
                                case FHRQ:
                                    fSaleOrderEntry.setFDeliveryDate(e.getFieldValue());
                                    break;
                                case CPBM:
                                    fSaleOrderEntry.setFMaterialId(new Entity2(e.getFieldValue()));
                                    break;
                                case SL:
                                    Long num = 0L;
                                    try {
                                        num = Long.valueOf(e.getFieldValue());
                                    } catch (NumberFormatException e1) {
                                        e1.printStackTrace();
                                    }
                                    fOrderEntryPlan.setFPlanQty(num);
                                    fSaleOrderEntry.setFQty(num);
                                    break;
                                case DJ:
                                    Double price = 0.0;
                                    try {
                                        price = Double.valueOf(e.getFieldValue());
                                    }catch (NumberFormatException e2){
                                        e2.printStackTrace();
                                    }
                                    fSaleOrderEntry.setFTaxPrice(price);
                                    break;
                                case SFZP:
                                    int i = Integer.valueOf(e.getFieldValue()).intValue();
                                    if (i == 0)
                                        fSaleOrderEntry.setFIsFree("1");
                                    break;
                                default:
                                    break;
                            }
                        }
                    });
                }
                //明细数量为0的不传
                if (fOrderEntryPlan.getFPlanQty() != null && fOrderEntryPlan.getFPlanQty().intValue() != 0) {
                    lists.add(fOrderEntryPlan);
                    fSaleOrderEntry.setFOrderEntryPlan(lists);
                    this.FSaleOrderEntry.add(fSaleOrderEntry);
                }
            });
        }
        this.FSaleOrderFinance = fSaleOrderFInance;
        this.FSaleOrderPlan.add(new FSaleOrderPlan());
        return map;
    }
}
