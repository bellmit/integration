package com.qunjie.jindie.huikuan.vo;

import com.qunjie.axis.model.WorkflowRequestTableField;
import com.qunjie.common.annotation.Describe;
import com.qunjie.common.util.SpringBeanUtils;
import com.qunjie.jindie.huikuan.constants.DefaultValue;
import com.qunjie.jindie.huikuan.constants.HuikuanFieldName;
import com.qunjie.jindie.saleorder.save.pojo.Entity1;
import com.qunjie.jindie.saleorder.save.pojo.Entity2;
import com.qunjie.jindie.saleorder.save.service.SaveService;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.jindie.huikuan.vo.FreceiveBillEntry
 *
 * @author whs
 * Date:   2021/1/25  15:35
 * Description: 收款单明细
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class FReceiveBillEntry {

    @Describe("实体主键")
    private Long FEntryID;
    @Describe(value = "结算方式",describe = "必填项")
    private Entity2 FSETTLETYPEID = new Entity2(DefaultValue.JSFS);
    @Describe("折后金额")
    private String FSETTLERECAMOUNTFOR;
    @Describe("现金折扣")
    private Double FSETTLEDISTAMOUNTFOR;
    @Describe("表体-应收金额")
    private Double FRECTOTALAMOUNTFOR;
    @Describe("折后金额本位币")
    private String FSETTLERECAMOUNT;
    @Describe("现金折扣本位币")
    private String FSETTLEDISTAMOUNT;
    @Describe("表体-应收金额本位币")
    private String FRECTOTALAMOUNT;
    @Describe("表体明细-核销状态")
    private String FWRITTENOFFSTATUS_D;
    @Describe("表体明细-已核销金额")
    private String FWRITTENOFFAMOUNTFOR_D;
    @Describe("备注")
    private String FCOMMENT;
    @Describe("对方银行账号")
    private String FOPPOSITEBANKACCOUNT;
    @Describe("对方账户名称")
    private String FOPPOSITECCOUNTNAME;
    @Describe("预收销售订单")
    private String FRECEIVEITEM;
    @Describe("手续费")
    private Double FHANDLINGCHARGEFOR;
    @Describe("手续费本位币")
    private String FHANDLINGCHARGE;
    @Describe("表体-实收金额")
    private Double FREALRECAMOUNTFOR_D;
    @Describe("表体-实收金额本位币")
    private String FREALRECAMOUNT_D;
    @Describe("关联总金额")
    private String FASSTOTALAMOUNTFOR;
    @Describe("预收项目类型")
    private String FRECEIVEITEMTYPE = DefaultValue.YSXMLX;
    @Describe("预收销售订单号内码")
    private Integer FSaleOrderID;
    @Describe("我方银行账号")
    private Entity2 FACCOUNTID = new Entity2(DefaultValue.WFYHZH);
    @Describe("我方账户名称")
    private String FRECACCOUNTNAME;
    @Describe("我方开户行")
    private String FRECBANKID;
    @Describe("长短款")
    private Double FOVERUNDERAMOUNTFOR;
    @Describe("对方开户行")
    private String FOPPOSITEBANKNAME;
    @Describe("结算号")
    private String FSETTLENO;
    @Describe("勾对")
    private String FBLEND;
    @Describe(value = "收款用途",describe = "必填项")
    private Entity2 FPURPOSEID = new Entity2(DefaultValue.SKYT);
    @Describe("长短款本位币")
    private String FOVERUNDERAMOUNT;
    @Describe("内部账号")
    private Entity1 FINNERACCOUNTID;
    @Describe("内部账户名称")
    private String FINNERACCOUNTNAME;
    @Describe("退款关联金额")
    private String FReFundAmount;
    @Describe("现金账号")
    private Entity1 FCashAccount;
    @Describe("收款金额")
    private Double FRECAMOUNTFOR_E;
    @Describe("收款金额本位币")
    private Double FRECAMOUNT_E;
    @Describe(value = "登账日期",describe = "必填项")
    private String FPOSTDATE;
    @Describe("是否登账")
    private String FISPOST;
    @Describe("物料编码")
    private Entity1 FMATERIALID;
    @Describe("物料名称")
    private String FMATERIALNAME;
    @Describe("销售订单号")
    private String FSALEORDERNO;
    @Describe("订单行号")
    private Long FMATERIALSEQ;
    @Describe("销售订单明细内码")
    private Long FORDERENTRYID;
    @Describe("保证金转货款金额")
    private String FTOPAYMENTAMOUNTFOR;
    @Describe("已核销金额本位币")
    private String FWRITTENOFFAMOUNT;
    @Describe("关联销售订单")
    private List<FAsssalesOrder> FASSSALESORDER = new ArrayList<>();

    public void valueOf(List<WorkflowRequestTableField> details) throws Exception {
        if (!CollectionUtils.isEmpty(details)) {
            AtomicReference<String> billno = new AtomicReference<>("");
            AtomicReference<Double> hkje = new AtomicReference<>(null);
            details.forEach(e -> {
                if (!StringUtils.isBlank(e.getFieldName()) && null != HuikuanFieldName.valuesOf(e.getFieldName())) {
                    switch (HuikuanFieldName.valuesOf(e.getFieldName())) {
                        case HKJE:
                            Double aDouble = Double.valueOf(StringUtils.isBlank(e.getFieldValue()) ? "0" : e.getFieldValue());
                            this.FRECTOTALAMOUNTFOR = aDouble;
                            hkje.set(aDouble);
                            break;
                        case BZ2:
                            this.FCOMMENT = e.getFieldValue();
                            break;
                        case HTBHX:
                            this.FRECEIVEITEM = e.getFieldValue();
                            this.FSALEORDERNO  = e.getFieldValue();
                            billno.set(e.getFieldValue());
                            break;
                        default:
                            break;
                    }
                }
            });

            SaveService saveService = SpringBeanUtils.getBean(SaveService.class);
            Integer id = (Integer) saveService.findId(billno.get()).get("id");
            if (null != id){
                FAsssalesOrder fAsssalesOrder = new FAsssalesOrder();
                fAsssalesOrder.setFASSBILLNO(billno.get());
                fAsssalesOrder.setFASSBILLID(String.valueOf(id));
                fAsssalesOrder.setFASSAMOUNTFOR(String.valueOf(hkje.get()));
                this.FSaleOrderID = id;
                this.FASSSALESORDER.add(fAsssalesOrder);
            }

        }
    }
}
