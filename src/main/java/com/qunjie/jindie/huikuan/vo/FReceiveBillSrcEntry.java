package com.qunjie.jindie.huikuan.vo;

import com.qunjie.axis.model.WorkflowRequestTableField;
import com.qunjie.common.annotation.Describe;
import com.qunjie.jindie.huikuan.constants.DefaultValue;
import com.qunjie.jindie.huikuan.constants.HuikuanFieldName;
import com.qunjie.jindie.saleorder.save.pojo.Entity2;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.jindie.huikuan.vo.FreceiveBillSrcEntry
 *
 * @author whs
 * Date:   2021/1/25  16:01
 * Description: 收款单源单明细
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class FReceiveBillSrcEntry {

    @Describe("实体主键")
    private Long FEntryID;
    @Describe("源单内码")
    private String FSRCBILLID;
    @Describe("应收金额")
    private String FAFTTAXTOTALAMOUNT;
    @Describe("结算方式")
    private String FSRCSETTLETYPEID;
    @Describe("源单币别")
    private String FSRCCURRENCYID;
    @Describe("到期日")
    private String FEXPIRY;
    @Describe("计划收款金额")
    private String FPLANRECAMOUNT;
    @Describe("本次收款金额")
    private String FREALRECAMOUNT;
    @Describe("源单类型")
    private String FSRCBILLTYPEID = "SAL_SaleOrder";
    @Describe("源单编号")
    private String FSRCBILLNO;
    @Describe("源单行号")
    private String FSRCSEQ;
    @Describe("源单行内码")
    private String FSRCROWID;
    @Describe("销售订单号")
    private String FORDERBILLNO;
    @Describe("物料编码")
    private Entity2 FSRCMATERIALID;
    @Describe("物料名称")
    private String FSRCMATERIALNAME;
    @Describe("订单行号")
    private Long FSRCMATERIALSEQ;
    @Describe("销售订单明细内码")
    private Long FSRCORDERENTRYID;
    @Describe("结算金额")
    private String FSETTLEAMOUNT;
    @Describe("本次收款金额本位币")
    private Double FREALRECAMOUNTFOR_S;

    public void valueOf(List<WorkflowRequestTableField> details) {
        if (!CollectionUtils.isEmpty(details)) {
            details.forEach(e -> {
                if (!StringUtils.isBlank(e.getFieldName()) && null != HuikuanFieldName.valuesOf(e.getFieldName())) {
                    switch (HuikuanFieldName.valuesOf(e.getFieldName())) {
                        case HTBHX:
                            this.FSRCBILLNO  = e.getFieldValue();
                            this.FORDERBILLNO  = e.getFieldValue();
                            break;
                        default:
                            break;
                    }
                }
            });
        }
    }
}
