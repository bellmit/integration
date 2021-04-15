package com.qunjie.jindie.huikuan.vo;

import com.qunjie.axis.model.WorkflowRequestTableField;
import com.qunjie.common.annotation.Describe;
import com.qunjie.jindie.huikuan.constants.DefaultValue;
import com.qunjie.jindie.huikuan.constants.HuikuanFieldName;
import com.qunjie.jindie.invoice.constants.FieldNameMain;
import com.qunjie.jindie.saleorder.save.enums.FieldName;
import com.qunjie.jindie.saleorder.save.pojo.Entity1;
import com.qunjie.jindie.saleorder.save.pojo.Entity2;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.jindie.huikuan.vo.FBillHead
 *
 * @author whs
 * Date:   2021/1/25  14:50
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class FBillHead {

    private Long FID;
    @Describe("单据编号")
    private Entity1 FBillNo;
    @Describe(value = "单据状态",describe = "必填")
    private String FDOCUMENTSTATUS;
    @Describe("创建人")
    private String FCreatorId;
    @Describe("审核人")
    private String FAPPROVERID;
    @Describe("创建日期")
    private String FCreateDate;
    @Describe("结算组织")
    private Entity2 FSETTLEORGID = new Entity2(DefaultValue.JSZZ);
    @Describe("审核日期")
    private String FApproveDate;
    @Describe("销售组织")
    private Entity2 FSALEORGID = new Entity2(DefaultValue.XSZZ);
    @Describe("汇率类型")
    private String FEXCHANGETYPE;
    @Describe("表头-应收金额")
    private String FRECEIVEAMOUNTFOR_H;
    @Describe("本位币")
    private String FMAINBOOKCURID;
    @Describe("表头-应收金额本位币")
    private String FRECEIVEAMOUNT_H;
    @Describe(value = "币别",describe = "必填项")
    private Entity2 FCURRENCYID = new Entity2(DefaultValue.BB);
    @Describe(value = "业务日期",describe = "必填项")
    private String FDATE;
    @Describe("修改日期")
    private String FModifyDate;
    @Describe("修改人")
    private String FModifierId;
    @Describe("汇率")
    private Double FEXCHANGERATE;
    @Describe("核销状态")
    private String FWRITTENOFFSTATUS;
    @Describe("销售员")
    private Entity2 FSALEERID;
    @Describe("销售组")
    private Entity2 FSALEGROUPID;
    @Describe("销售部门")
    private Entity2 FSALEDEPTID;
    @Describe(value = "单据类型",describe = "必填项")
    private Entity1 FBillTypeID = new Entity1(DefaultValue.DJLX);
    @Describe("表头-实收金额")
    private Double FREALRECAMOUNTFOR;
    @Describe("表头-实收金额本位币")
    private String FREALRECAMOUNT_H;
    @Describe("会计核算体系")
    private String FACCOUNTSYSTEM;
    @Describe("作废日期")
    private String FCancelDate;
    @Describe(value = "作废状态",describe = "必填项")
    private String FCancelStatus;
    @Describe("作废人")
    private String FCancellerId;
    @Describe(value = "往来单位类型",describe = "必填项")
    private String FCONTACTUNITTYPE = DefaultValue.WLDWLX;
    @Describe(value = "往来单位",describe = "必填项")
    private Entity2 FCONTACTUNIT;
    @Describe(value = "付款单位类型",describe = "必填项")
    private String FPAYUNITTYPE = DefaultValue.FKDJLX;
    @Describe(value = "付款单位",describe = "必填项")
    private Entity2 FPAYUNIT ;
    @Describe(value = "业务类型",describe = "必填项")
    private String FBUSINESSTYPE;
    @Describe("信用检查结果")
    private String FCreditCheckResult;
    @Describe("是否期初单据")
    private String FISINIT;
    @Describe("部门")
    private Entity2 FDepartment;
    @Describe(value = "收款组织",describe = "必填项")
    private Entity2 FPAYORGID = new Entity2(DefaultValue.SKZZ);
    @Describe("是否相同组织")
    private String FIsSameOrg;
    @Describe("来源系统")
    private String FSOURCESYSTEM;
    @Describe("现销")
    private String FCASHSALE;
    @Describe(value = "结算币别",describe = "必填项")
    private Entity1 FSETTLECUR = new Entity1(DefaultValue.JSBB);
    @Describe("结算汇率")
    private Double FSETTLERATE;
    @Describe("收款金额")
    private Double FRECAMOUNTFOR;
    @Describe("表头-收款金额本位币")
    private String FRECAMOUNT;
    @Describe("B2C业务")
    private String FISB2C;
    @Describe("流水号/对账码")
    private String FWBSETTLENO;
    @Describe("是否转销")
    private String FIsWriteOff;
    @Describe("核销方式")
    private Integer FMatchMethodID;
    @Describe("扫描点")
    private Entity1 FScanPoint;
    @Describe("金蝶支付流水号")
    private String FKDPAYNO;
    @Describe("备注")
    private String FREMARK;
    @Describe("第三方单据编号")
    private String FTHIRDBILLNO;
    @Describe(value = "结算本位币",describe = "必填项")
    private Entity1 FSETTLEMAINBOOKID = new Entity1(DefaultValue.JSBWB);
    @Describe("结算汇率类型")
    private String FSETTLEEXCHANGETYPE;
    @Describe("转出往来单位")
    private String FOUTCONTACTID;
    @Describe("转出往来单位类型")
    private String FOUTCONTACTTYPE;
    @Describe("管易财务流水内码")
    private String FGYACCOUNTWATERID;
    @Describe("是否下推携带汇率到结算汇率")
    private String FISCARRYRATE;
    @Describe("应收金额")
    private Double FAFTTAXTOTALAMOUNT;

    @Describe("收款单明细")
    private List<FReceiveBillEntry> FRECEIVEBILLENTRY;

    @Describe("收款单源单明细")
    private List<FReceiveBillSrcEntry> FRECEIVEBILLSRCENTRY;

    @Describe("应收票据明细")
    private List<FBillReceivableEntry> FBILLRECEIVABLEENTRY;

    @Describe("应收票据背书")
    private List<FBillSkdrecEntry> FBILLSKDRECENTRY;

    /**
     * 明细
     * @param details   泛微明细数据
     */
    public void valueOf(List<WorkflowRequestTableField> details) {
        if (!CollectionUtils.isEmpty(details)) {
            details.forEach(e -> {
                if (!StringUtils.isBlank(e.getFieldName()) && null != HuikuanFieldName.valuesOf(e.getFieldName())) {
                    switch (HuikuanFieldName.valuesOf(e.getFieldName())) {
                        case KHBM:
                            this.FPAYUNIT = new Entity2(e.getFieldValue());
                            this.FCONTACTUNIT = new Entity2(e.getFieldValue());
                            break;
                        case HKRQ:
                            this.FDATE = e.getFieldValue();
                            break;
                        case XSYBM:
                            this.FSALEERID = new Entity2(e.getFieldValue());
                            break;
                        case BZ:
                            this.FREMARK = e.getFieldValue();
                            break;
                        default:
                            break;
                    }
                }
            });
            return;
        }
    }

}
