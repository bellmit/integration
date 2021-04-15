package com.qunjie.crm.invoice.args;

import com.qunjie.axis.model.WorkflowRequestTableField;
import com.qunjie.common.annotation.Describe;
import com.qunjie.jindie.invoice.constants.FieldNameDetail;
import com.qunjie.jindie.invoice.constants.FieldNameMain;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.invoice.args.InvoiceDetail
 *
 * @author whs
 * Date:   2021/1/20  14:20
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class InvoiceDetail {

    @Describe("开票明细编号")
    private String name;

    @Describe("税金")
    private Double tax_amount;

    @Describe("开票小计")
    private Double invoiced_amount;

    @Describe("开票申请")
    private String invoice_id;

    @Describe("销售订单编号")
    private String order_id;

    @Describe("OA销售订单号")
    private String field_Eqx7e__c;

    @Describe("待开票金额")
    private String no_invoice_amount;

    @Describe("外部负责人")
    private String out_owner;

    @Describe("负责人")
    private List<String> owner;

    @Describe("税率")
    private Double tax_rate;

    @Describe("开票明细日期")
    private String invoice_date;

    @Describe("业务类型")
    private String record_type;

    @Describe("创建人")
    private List<String> created_by;


    public void valuesOf(String order_id,String oa_billno,Double bckpje) {
        this.order_id = order_id;
        this.field_Eqx7e__c = oa_billno;
        this.invoiced_amount = bckpje;
    }
}
