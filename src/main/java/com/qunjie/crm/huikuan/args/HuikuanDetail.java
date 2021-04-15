package com.qunjie.crm.huikuan.args;

import com.qunjie.axis.model.WorkflowRequestTableField;
import com.qunjie.common.annotation.Describe;
import com.qunjie.common.util.SpringBeanUtils;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.query.results.QueryResult;
import com.qunjie.crm.saleorder.service.SaleOrderService;
import com.qunjie.jindie.huikuan.constants.HuikuanFieldName;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.huikuan.args.HuikuanDetail
 *
 * @author whs
 * Date:   2021/1/27  10:04
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class HuikuanDetail {

    @Describe("回款明细编号")
    private String name;
    @Describe("审批提交时间")
    private Long submit_time;
    @Describe("提醒日期")
    private Long notification_time;
    @Describe("回款计划编号")
    private String payment_plan_id;
    @Describe("回款日期")
    private Long payment_time;
    @Describe("回款编号")
    private String payment_id;
    @Describe("附件")
    private String attachment;
    @Describe("销售订单编号")
    private String order_id;
    @Describe("客户名称")
    private String account_id;
    @Describe("备注")
    private String remark;
    @Describe("本次回款金额（元）")
    private Double payment_amount;
    @Describe("财务确认人")
    private String finance_employee_id;
    @Describe("财务确认时间")
    private String finance_confirm_time;
    @Describe("回款方式")
    private String payment_term;
    @Describe("锁定状态")
    private String lock_status;
    @Describe("负责人")
    private List<String> owner = new ArrayList<>();
    @Describe(value = "回款类别",describe = "option1,qsy9vl28k,17qU3x2Jc,11pmr6j4u  || '直销','渠道','联营','产商'")
    private String field_q002q__c;
    @Describe("OA销售订单")
    private String field_yCujp__c;

    public Map<String,String> valuesOf(List<WorkflowRequestTableField> details) {
        Map<String,String > map = new HashMap<>();
        if (!CollectionUtils.isEmpty(details)) {
            details.forEach(e -> {
                if (!StringUtils.isBlank(e.getFieldName()) && null != HuikuanFieldName.valuesOf(e.getFieldName())) {
                    switch (HuikuanFieldName.valuesOf(e.getFieldName())) {
                        case HKJE:
                            Double hkje = Double.valueOf(StringUtils.isBlank(e.getFieldValue())?"0":e.getFieldValue());
                            this.payment_amount = hkje;
                            break;
                        case HTBHX:
                            if (!StringUtils.isBlank(e.getFieldValue())){
                                this.field_yCujp__c = e.getFieldValue();
                                SaleOrderService saleOrderService = SpringBeanUtils.getBean(SaleOrderService.class);
                                try {
                                    QueryResult queryResult = saleOrderService.saleOrderQueryBy(e.getFieldValue());
                                    if (queryResult != null && queryResult.getErrorCode() == 0) {
                                        String id = queryResult.getData().getJSONArray("dataList").getJSONObject(0).getString("_id");
                                        String account_id = queryResult.getData().getJSONArray("dataList").getJSONObject(0).getString("account_id");
                                        this.order_id = id;
                                        map.put("account_id",account_id);
                                    }
                                } catch (AccessTokenException ex) {
                                    ex.printStackTrace();
                                }catch (Exception e1){
                                    e1.printStackTrace();
                                }
                                map.put(HuikuanFieldName.HTBHX.name(),e.getFieldValue());
                            }
                            break;
                        case BZ2:
                            if (!StringUtils.isBlank(e.getFieldValue())) {
                                this.remark = e.getFieldValue();
                            }
                            break;
                        case BZ:
                            if (!StringUtils.isBlank(e.getFieldValue())){
                                this.remark = e.getFieldValue();
                            }
                            break;
                        case LXWB:
                            if (!StringUtils.isBlank(e.getFieldValue())){
                                HashMap<String, String> hashMap = new HashMap<String, String>() {
                                    {
                                        put("直销", "option1");
                                        put("渠道", "qsy9vl28k");
                                        put("联营", "17qU3x2Jc");
                                        put("厂商", "11pmr6j4u");
                                    }
                                };
                                this.field_q002q__c = hashMap.get(e.getFieldValue());
                            }
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
