package com.qunjie.jindie.invoice.vo;/**
 * Created by whs on 2020/12/8.
 */

import com.qunjie.axis.model.WorkflowRequestTableField;
import com.qunjie.common.annotation.Describe;
import com.qunjie.jindie.invoice.constants.DefaultValue;
import com.qunjie.jindie.invoice.constants.FieldNameMain;
import com.qunjie.jindie.saleorder.save.pojo.Entity1;
import com.qunjie.jindie.saleorder.save.pojo.Entity2;
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
@Describe("开票单据头")
public class FBillHead {

    @Describe("实体主键")
    private Long FID;

    @Describe("单据编号")
    private String FBillNo;

    @Describe("单据状态")
    private String FDOCUMENTSTATUS;

    @Describe(value = "业务日期",describe = "必填,2021-01-09")
    private String FDATE;

    @Describe(value = "币别",describe = "必填项")
    private Entity2 FCURRENCYID = new Entity2(DefaultValue.FSETTLECURRID);

    @Describe(value = "开票方式",describe = "必填项")
    private String FBILLINGWAY = DefaultValue.FBILLINGWAY;

    @Describe("发票号")
    private String FINVOICENO;

    @Describe("发票日期")
    private String FINVOICEDATE;

    @Describe(value = "结算组织",describe = "必填")
    private Entity2 FSETTLEORGID = new Entity2(DefaultValue.FSALEORGID);

    @Describe(value = "销售组织")
    private Entity2 FSALEORGID = new Entity2(DefaultValue.FSALEORGID);

    @Describe("销售部门")
    private Entity2 FSALEDEPTID;

    @Describe("销售组")
    private Entity2 FSALEGROUPID;

    @Describe("表头基本-开票核销状态")
    private String FOPENSTATUS;

    @Describe("结算方式")
    private Entity2 FSETTLETYPEID;

    @Describe("汇率")
    private String FEXCHANGERATE;

    @Describe("汇率类型")
    private String FEXCHANGETYPE;

    @Describe(value = "本位币",describe = "必填项")
    private Entity2 FMAINBOOKSTDCURRID = new Entity2(DefaultValue.FSETTLECURRID);

    @Describe("不含税金额")
    private Double FHEADAUXPRICEFOR;

    @Describe("税额")
    private Double FTAXAMOUNTFOR;

    @Describe("价税合计")
    private Double FAFTERTOTALTAXFOR;

    @Describe("税额本位币")
    private Double FTAXAMOUNT;

    @Describe("价税合计本位币")
    private String FAFTERTOTALTAX;

    @Describe("不含税金额本位币")
    private String FHEADAUXPRICE;

    @Describe("修改日期")
    private String FModifyDate;

    @Describe("创建日期")
    private String FCreateDate;

    @Describe("审核人")
    private String FAPPROVERID;

    @Describe("创建人")
    private String FCREATORID;

    @Describe("审核日期")
    private String FApproveDate;

    @Describe("修改人")
    private String FModifierId;

    @Describe("销售员")
    private Entity2 FSALEERID;

    @Describe(value = "客户",describe = "必填项")
    private Entity2 FCUSTOMERID;

    @Describe(value = "单据类型",describe = "必填项")
    private Entity1 FBillTypeID = new Entity1(DefaultValue.FBILLTYPEID);

    @Describe("会计核算体系")
    private Entity2 FACCOUNTSYSTEM;

    @Describe("按含税单价录入")
    private String FISTAX = "true";

    @Describe("作废人")
    private String FCancellerId;

    @Describe(value = "作废状态",describe = "必填项")
    private String FCancelStatus;

    @Describe("作废日期")
    private String FCancelDate;

    @Describe("红蓝字")
    private String FRedBlue = DefaultValue.FREDBLUE;

    @Describe("金税引出次数")
    private Integer FEXPORTCOUNT;

    @Describe("发票生成方式")
    private Integer FGenType;

    @Describe("开票流水号")
    private String FSerialNumber;

    @Describe("税控状态（已弃用）")
    private String FElectronicStatus;

    @Describe("发票抬头")
    private String FINVOICETITLE;

    @Describe("纳税人识别号")
    private String FTAXREGISTERCODE;

    @Describe("B2C业务")
    private String FISB2C;

    @Describe("开户银行")
    private String FOPENBANKNAME;

    @Describe("银行账号")
    private String FBANKCODE;

    @Describe("联系电话")
    private String FTEL;

    @Describe("通讯地址")
    private String FADDRESS;

    @Describe("发票代码")
    private String FIVCODE;

    @Describe("原发票代码")
    private String FSOURCEIVCODE;

    @Describe("发票号码")
    private String FIVNUMBER;

    @Describe("原发票号码")
    private String FSOURCEIVNUMBER;

    @Describe("开票状态")
    private String FGTSTATUS;

    @Describe("打印次数")
    private Integer FGTPRINTCOUNT;

    @Describe("打印清单次数")
    private Integer FGTPRINTLISTCOUNT;

    @Describe("清单标识")
    private String FHAVEIVLIST;

    @Describe("原开票流水号")
    private String FSOURCESERIALNUM;

    @Describe("源单立账类型")
    private String FSrcSetAccountType;

    @Describe("收款人")
    private String FPAYEE;

    @Describe("开票人")
    private String FDRAWER;

    @Describe("复核人")
    private String FREVIEWER;

    @Describe("金税盘机号")
    private String FGTDISKNUMBER;

    @Describe("开票客户")
    private Entity2 FIVCUSTOMERID;

    @Describe("获取成本时间")
    private String FGetCostLastedDate;

    @Describe("获取到的成本核算时间")
    private String FCOSTCALDATE;

    @Describe("不参与开票核销")
    private String FISHookMatch;

    @Describe("扫描点")
    private Entity1 FScanPoint;

    @Describe("归档号")
    private String FARCHIVENO;

    @Describe("信息表编号")
    private String FINFOTABLENUMBER;

    @Describe("校验码")
    private String FCHECKCODE;

    @Describe("销售增值税专用发票明细")
    private List<FSaleSicentry> FSALESICENTRY = new ArrayList<>();

    @Describe("成本明细")
    private List<FcostEntry> FCOSTENTRY;

    @Describe(value = "收票人",describe = "必填")
    private String F_TOM_Text;

    @Describe(value = "收票人地址",describe = "必填")
    private String F_TOM_Text1;

    @Describe(value = "收票人电话",describe = "必填")
    private String F_TOM_Text2;

    @Describe("扫描信息")
    private List<FSaleSicscan> FSALESICSCAN = new ArrayList<>();

    public Map valueOf(List<WorkflowRequestTableField> mains) {
        Map<String,String> map = new HashMap<>();
        if (!CollectionUtils.isEmpty(mains)){
            mains.forEach(e->{
                if (!StringUtils.isBlank(e.getFieldName()) && null != FieldNameMain.valuesOf(e.getFieldName())){
                    switch (FieldNameMain.valuesOf(e.getFieldName())){
                        case SQRBH:
                            this.FSALEERID = new Entity2(e.getFieldValue());
                            break;
                        case HTBH://标准开票信息中销售订单号为合同编号
                            if (!StringUtils.isBlank(e.getFieldValue())) {
                                map.put(FieldNameMain.HTBH.name(), e.getFieldValue());
                            }
                            break;
                        case QDDDH://渠道开票信息中销售订单为渠道订单号
                            if (!StringUtils.isBlank(e.getFieldValue())) {
                                map.put(FieldNameMain.HTBH.name(), e.getFieldValue());
                            }
                            break;
                        case FPLX:
                            map.put(FieldNameMain.FPLX.name(),e.getFieldValue());
                            break;
                        case HTJE:
                            break;
                        case KHBH:
                            this.FCUSTOMERID = new Entity2(e.getFieldValue());
                            break;
                        case KPRQ:
                            this.FDATE = e.getFieldValue();
                            break;
                        case REQUESTNAME:
                            break;
                        case KHH:
                            this.FOPENBANKNAME = e.getFieldValue();
                            break;
                        case ZH:
                            if (null != e.getFieldValue())
                                this.FBANKCODE = e.getFieldValue().replaceAll("[\\s+]","");
                            break;
                        case ZCDZ:
                            this.FADDRESS = e.getFieldValue();
                            this.F_TOM_Text1 = e.getFieldValue();
                            break;
                        case KHDH:
                            if (null != e.getFieldValue())
                                this.FTEL = e.getFieldValue().replaceAll("[\\s+]","");
                            break;
                        case GSQC:
                            this.FINVOICETITLE = e.getFieldValue();
                            break;
                        case SBH:
                            if (null != e.getFieldValue())
                                this.FTAXREGISTERCODE = e.getFieldValue().replaceAll("[\\s+]","");
                            break;
                        case SJRLXFS:
                            if (null != e.getFieldValue())
                                this.F_TOM_Text2 = e.getFieldValue().replaceAll("[\\s+]","");
                            break;
                        case SJR:
                            this.F_TOM_Text = e.getFieldValue();
                            break;
                        case FPHM:
                            this.FINVOICENO = e.getFieldValue();
                            break;
                        default:
                            break;
                    }
                }
            });
        }
        return map;
    }
}
