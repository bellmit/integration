package com.qunjie.crm.invoice.args;

import com.qunjie.axis.model.WorkflowRequestTableField;
import com.qunjie.common.annotation.Describe;
import com.qunjie.crm.saleorder.args.SaleOrderObjectData;
import com.qunjie.crm.utils.DefaultValues;
import com.qunjie.jindie.invoice.constants.FieldNameMain;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.invoice.args.InvoiceObjectData
 *
 * @author whs
 * Date:   2021/1/20  14:14
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class InvoiceObjectData {

    private String dataObjectApiName = DefaultValues.INVOICEAPPLICATIONOBJ;

    @Describe("联系方式")
    private String contact_tel;

    @Describe("开户行")
    private String account_bank;

    @Describe(value = "抬头类型",describe = "1:个人，2：单位（默认）")
    private String title_type = "2";

    @Describe("电话")
    private String invoice_tel;

    @Describe("开票地址")
    private String invoice_add;

    @Describe("纳税识别号")
    private String tax_id;

    @Describe("开户账号")
    private String account_bank_no;

    @Describe("销售订单编号")
    private String order_id;

    @Describe("开票抬头")
    private String invoice_title;

    @Describe("备注")
    private String remark;

    @Describe("开票日期")
    private Long invoice_date;

    @Describe("开票类型")
    private String invoice_type;

    @Describe("发票号码")
    private String invoice_no;

    @Describe("客户名称")
    private String account_id;

    @Describe("寄送地址")
    private String contact_add;

    @Describe("开票金额（元）")
    private Double invoice_applied_amount;

    @Describe("联系人")
    private String recipient;

    @Describe("提交时间")
    private String submit_time;

    @Describe("财务确认人姓名")
    private String finance_employee_id;

    @Describe("负责人主属部门")
    private String owner_department;

    @Describe("负责人")
    private List<String> owner = new ArrayList<>();

    @Describe("创建人")
    private List<String> created_by = new ArrayList<>();

    @Describe("业务类型")
    private String record_type;

    public Map<String,String> valuesOf(List<WorkflowRequestTableField> mains) {
        Map<String,String> map = new HashMap<>();
        if (CollectionUtils.isEmpty(mains)){
            return null;
        }
        mains.forEach(e->{
            if (!StringUtils.isBlank(e.getFieldName()) && null != FieldNameMain.valuesOf(e.getFieldName())) {
                switch (FieldNameMain.valuesOf(e.getFieldName())) {
                    case HTBH:
                        if (!StringUtils.isBlank(e.getFieldValue())) {
                            map.put(FieldNameMain.HTBH.name(), e.getFieldValue());
                        }
                        break;
                    case QDDDH:
                        if (!StringUtils.isBlank(e.getFieldValue())) {
                            map.put(FieldNameMain.HTBH.name(), e.getFieldValue());
                        }
                        break;
                    case BCKPJE:
                        this.invoice_applied_amount = Double.valueOf(StringUtils.isBlank(e.getFieldValue()) ? "0":e.getFieldValue());
                        map.put(FieldNameMain.BCKPJE.name(),StringUtils.isBlank(e.getFieldValue()) ? "0":e.getFieldValue());
                        break;
                    case KHMC:
                        /**
                         * 因开票中的客户采用的是crm中的销售订单的客户，故原本从oa开票流程中获取回款客户的赋值注释掉；
                         * 在获取销售订单后，会用销售订单里的客户对此属性account_id赋值
                         */
//                        try {
//                            AccountService accountService = SpringBeanUtils.getBean(AccountService.class);
//                            List<AccountModel> accountModels = accountService.queryAccountByName(e.getFieldValue());
//                            if (!CollectionUtils.isEmpty(accountModels)){
//                                this.account_id = accountModels.get(0).get_id();
//                            }
//                        } catch (AccessTokenException ex) {
//                            ex.printStackTrace();
//                        }
                        break;
                    case SQRXM:
                        String crmOpenUserId = SaleOrderObjectData.getCrmOpenUserIdByNm(e.getFieldValue());
                        this.created_by.add(crmOpenUserId) ;
                        this.owner.add(crmOpenUserId) ;
                        break;
                    case FPLX:
                        if (!StringUtils.isBlank(e.getFieldValue()) && "1".equals(e.getFieldValue())){//泛微中：1-普票,0-专票;crm中:1,专票,2普票
                            this.invoice_type = "2";
                        }else {
                            this.invoice_type = "1";
                        }
                        break;
                    case KPRQ:
                        if (!StringUtils.isBlank(e.getFieldValue())){
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            try {
                                this.invoice_date = sdf.parse(e.getFieldValue()).getTime();
                            } catch (ParseException ex) {
                                ex.printStackTrace();
                            }
                        }else {
                            this.invoice_date = new Date().getTime();
                        }
                        break;
                    case SJR:
                        this.recipient = e.getFieldValue();
                        break;
                    case SJRLXFS:
                        if (null != e.getFieldValue())
                            this.contact_tel = e.getFieldValue().replaceAll("[\\s+]","");
                        break;
                    case SJDZ:
                        this.contact_add = e.getFieldValue();
                        break;
                    case GSQC:
                        this.invoice_title = e.getFieldValue();
                        break;
                    case SBH:
                        if (null != e.getFieldValue())
                            this.tax_id = e.getFieldValue().replaceAll("[\\s+]","");
                        break;
                    case KHH:
                        this.account_bank = e.getFieldValue();
                        break;
                    case ZH:
                        if (null != e.getFieldValue())
                            this.account_bank_no = e.getFieldValue().replaceAll("[\\s+]","");
                        break;
                    case ZCDZ:
                        this.invoice_add = e.getFieldValue();
                        break;
                    case KHDH:
                        if (null != e.getFieldValue())
                            this.invoice_tel = e.getFieldValue().replaceAll("[\\s+]","");
                        break;
                    case FPHM:
                        this.invoice_no = e.getFieldValue();
                        break;
                    default:
                        break;
                }
            }
        });
        return map;
    }
}
