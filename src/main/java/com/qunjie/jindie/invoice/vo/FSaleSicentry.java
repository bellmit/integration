package com.qunjie.jindie.invoice.vo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qunjie.axis.model.WorkflowRequestTableField;
import com.qunjie.common.annotation.Describe;
import com.qunjie.jindie.invoice.constants.FieldNameDetail;
import com.qunjie.jindie.saleorder.save.pojo.Entity2;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.jindie.invoice.vo.FSaleSicentry
 *
 * @author whs
 * Date:   2021/1/18  15:40
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
@Describe("销售增值税专用发票明细")
public class FSaleSicentry {

    @Describe("实体主键")
    private Long FEntryID;

    @Describe("源单内码")
    private String FSRCBILLID;

    @Describe("源单行内码")
    private String FSRCROWID;

    @Describe("源单行号")
    private String FSRCSEQ;

    @Describe(value = "物料编码",describe = "必填项")
    private Entity2 FMATERIALID;

    @Describe("物料名称")
    private String FMATERIALNAME;

    @Describe("规格型号")
    private Entity2 FMODEL;

    @Describe("货物类别")
    private String FMATERIALTYPE;

    @Describe("单价")
    private Double FAUXPRICE;

    @Describe("含税单价")
    private Double FAUXTAXPRICE;

    @Describe("销售订单")
    private String FSALESORDERNO;

    @Describe("价格系数")
    private String FPRICECOEFFICIENT;

    @Describe("计价数量")
    private Double FPRICEQTY;

    @Describe("不含税金额")
    private Double FAMOUNTFOR;

    @Describe("税率%")
    private Double FTAXRATE;

    @Describe("税额")
    private Double FDETAILTAXAMOUNTFOR;

    @Describe("价税合计")
    private Double FALLAMOUNTFOR;

    @Describe("备注")
    private String FCOMMENT;

    @Describe("表体明细-开票核销状态")
    private String FOPENSTATUS_D;

    @Describe("表体明细-已开票核销金额")
    private Double FOPENAMOUNTFOR_D;

    @Describe("表体明细-未开票核销金额")
    private Double FNOTOPENAMOUNTFOR_D;

    @Describe("勾稽状态")
    private String FHOOKSTATUS;

    @Describe("已钩稽金额")
    private Double FHOOKAMOUNTFOR;

    @Describe("已钩稽基本单位数量")
    private Integer FHOOKQTY;

    @Describe("未钩稽基本单位数量")
    private Integer FUNHOOKQTY;

    @Describe("未钩稽金额")
    private Double FUNHOOKAMOUNTFOR;

    @Describe(value = "基本单位",describe = "必填项")
    private Entity2 FBASICUNITID;

    @Describe(value = "计价单位")
    private Entity2 FPRICEUNITID;

    @Describe("基本单位数量")
    private String FBASICUNITQTY;

    @Describe("源单类型")
    private String FSRCBILLTYPEID = "SAL_SaleOrder";

    @Describe("源单编号")
    private String FSRCBILLNO;

    @Describe("源单基本单位数量")
    private Integer FSRCBASICQTY;

    @Describe("源单已开票基本单位数量")
    private Integer FSRCIVBASICQTY;

    @Describe("计划计价数量")
    private Integer FPLANPRICEQTY;

    @Describe("表体明细-未开票核销数量")
    private Integer FNOTOPENQTY_D;

    @Describe("表体明细-已开票核销数量")
    private Integer FOPENQTY_D;

    @Describe("上游销售发票价税合计")
    private Double FTempAmount;

    @Describe("已下推发票数量")
    private Integer FPUSHREDQTY;

    @Describe("基本单位单价")
    private Double FBASICUNITPRICEFOR;

    @Describe("已核销不含税金额")
    private Double FMatchNotTaxAmtFor;

    @Describe("上游特殊核销金额")
    private Double FSPECIALAMOUNTFOR;

    @Describe("物料说明")
    private String FMaterialDesc;

    @Describe("已下推应收数量")
    private Integer FPUSHRECABLEQTY;

    @Describe("免征额")
    private Double FDEDUCTION;

    @Describe("免征税额")
    private Double FTAXDEDUCTION;

    @Describe("辅助属性")
    private Object FAUXPROPID;

    @Describe("批号")
    private Entity2 FLot;

    @Describe("折扣率(%)")
    private Double FENTRYDISCOUNTRATE;

    @Describe("折扣额")
    private Double FDISCOUNTAMOUNTFOR;

    @Describe("折扣额本位币")
    private String FDISCOUNTAMOUNT;

    @Describe("税额本位币")
    private String FDETAILTAXAMOUNT;

    @Describe("不含税金额本位币")
    private String FNOTAXAMOUNT;

    @Describe("价税合计本位币")
    private String FALLAMOUNT;

    @Describe("成本金额")
    private Double FCOSTAMTSUM;

    @Describe("客户物料编码")
    private Entity2 FCustMatID;

    @Describe("客户物料名称")
    private String FCustMatName;

    @Describe("关联关系表")
    private List<FSaleSicentryLink> FSALESICENTRY_Link = new ArrayList<>();

    public void valueOf(List<WorkflowRequestTableField> details, String htbh, JSONObject view) {
        String Id = null;
        AtomicReference<String> Fsid = new AtomicReference<>("");
        Map<String,String> map = new HashMap<>();
        try {
            Id = view.getJSONObject("Result").getJSONObject("Result").getString("Id");
            JSONArray jsonArray = view.getJSONObject("Result").getJSONObject("Result").getJSONArray("SaleOrderEntry");
            if (jsonArray != null && jsonArray.size() > 0){
                for (int i = 0; i < jsonArray.size(); i++) {
                    String key = jsonArray.getJSONObject(i).getJSONObject("MaterialId").getString("Number");
                    String value = jsonArray.getJSONObject(i).getString("Id");
                    map.put(key,value);
                }
            }
        }catch (Exception e){
            return;
        }

        if (!StringUtils.isBlank(htbh)){
            this.FSRCBILLNO = htbh;
        }
        if (!CollectionUtils.isEmpty(details)){
            details.forEach(e->{
                if (!StringUtils.isBlank(e.getFieldName()) && null != FieldNameDetail.valuesOf(e.getFieldName())){
                    switch (FieldNameDetail.valuesOf(e.getFieldName())){
                        case CPBM:
                            if (!StringUtils.isBlank(e.getFieldValue())) {
                                this.FMATERIALID = new Entity2(e.getFieldValue());
                                Fsid.set(map.get(e.getFieldValue()));
                            }
                            break;
                        case CPMC:
                            break;
                        case GGXH:
                            break;
                        case BCKPSL:
                            this.FPRICEQTY = Double.valueOf(StringUtils.isBlank(e.getFieldValue()) ? "0":e.getFieldValue());
                            break;
                        case HSDJY:
                            this.FAUXTAXPRICE = Double.valueOf(StringUtils.isBlank(e.getFieldValue()) ? "0":e.getFieldValue());
                            break;
                        case JSHJY:
                            break;
                        case BZ:
                            this.FCOMMENT = e.getFieldValue();
                            break;
                        default:
                            break;
                    }
                }
            });
        }
        if (!StringUtils.isBlank(Id) && !StringUtils.isBlank(Fsid.get())) {
            FSaleSicentryLink fSaleSicentryLink = new FSaleSicentryLink();
            fSaleSicentryLink.setFSALESICENTRY_Link_FSBillId(Id);
            fSaleSicentryLink.setFSALESICENTRY_Link_FSId(Fsid.get());
            this.FSALESICENTRY_Link.add(fSaleSicentryLink);
        }
    }
}
