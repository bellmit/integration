package com.qunjie.crm.confirmAchievement.args;

import com.qunjie.axis.model.WorkflowRequestTableField;
import com.qunjie.common.annotation.Describe;
import com.qunjie.common.util.SpringBeanUtils;
import com.qunjie.crm.beans.args.BaseArg;
import com.qunjie.crm.confirmAchievement.constants.ConfirmAchievementFieldName;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.manager.args.BaseModelArg;
import com.qunjie.crm.query.results.QueryResult;
import com.qunjie.crm.saleorder.service.SaleOrderService;
import com.qunjie.crm.utils.DefaultValues;
import com.qunjie.mysql.model.UserValue;
import com.qunjie.mysql.service.UserValueService;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.achievement.args.AchievementAddArg
 *
 * @author whs
 * Date:   2021/2/25  9:50
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class ConfirmAchievementAddArg extends BaseArg {
    private String currentOpenUserId = DefaultValues.CURRENTOPENUSERID;

    private boolean triggerWorkFlow = false;

    private ConfirmAchievementModel data;

    public ConfirmAchievementAddArg(ConfirmAchievementModel data) {
        this.data = data;
    }

    public ConfirmAchievementAddArg() {
    }

    @Data
    public static class ConfirmAchievementModel extends BaseModelArg {
        private ConfirmAchievementObjectData object_data;

        public ConfirmAchievementModel(ConfirmAchievementObjectData object_data) {
            this.object_data = object_data;
        }

        public ConfirmAchievementModel() {
        }
    }

    @Data
    public static class ConfirmAchievementObjectData{

        private String dataObjectApiName = "object_vuck5__c";

        @Describe("OA订单编号")
        private String field_K5CmU__c;
        @Describe("客户")
        private String field_1FpqE__c;
        @Describe("销售订单")
        private String field_96zxh__c;
        @Describe("合作伙伴")
        private String field_XY1DI__c;
        @Describe("回款业绩")
        private Double field_s6y96__c;
        @Describe("回款金额")
        private Double field_g1z4T__c;
        @Describe("工作量占比(%)")
        private Double field_41n2o__c;
        @Describe("业务费")
        private Double field_1bP2M__c;
        @Describe("招待费")
        private Double field_M0lB1__c;
        @Describe("个人净业绩")
        private Double field_g0wfA__c;
        @Describe("净业绩")
        private Double field_eo9K1__c;
        @Describe("回款日期")
        private Long field_dp1Ex__c;
        @Describe("销售类型")
        private String field_7t2uG__c;
        @Describe("签约类型")
        private String field_Xao0l__c;
        @Describe("负责人")
        private List<String> owner = new ArrayList<>();

        public Map<String,String> valueOf(List<WorkflowRequestTableField> detail){
            Map<String,String> map = new HashMap<>();
            if (!CollectionUtils.isEmpty(detail)) {
                detail.forEach(e -> {
                    if (!StringUtils.isBlank(e.getFieldName()) && null != ConfirmAchievementFieldName.valuesOf(e.getFieldName())) {
                        switch (ConfirmAchievementFieldName.valuesOf(e.getFieldName())) {
                            case XSHT:
                                if (!StringUtils.isBlank(e.getFieldValue())){
                                    SaleOrderService saleOrderService = SpringBeanUtils.getBean(SaleOrderService.class);
                                    try {
                                        QueryResult queryResult = saleOrderService.saleOrderQueryBy(e.getFieldValue());
                                        if (queryResult != null && queryResult.getErrorCode() == 0) {
                                            String id = queryResult.getData().getJSONArray("dataList").getJSONObject(0).getString("_id");
                                            String account_id = queryResult.getData().getJSONArray("dataList").getJSONObject(0).getString("account_id");
                                            String OaHTBH = queryResult.getData().getJSONArray("dataList").getJSONObject(0).getString("field_Clved__c");
                                            String partner_id = queryResult.getData().getJSONArray("dataList").getJSONObject(0).getString("partner_id");
                                            this.field_1FpqE__c = account_id;
                                            this.field_96zxh__c = id;
                                            this.field_K5CmU__c = OaHTBH;
                                            if (!StringUtils.isBlank(partner_id))
                                                this.field_XY1DI__c = partner_id;
                                        }
                                    } catch (AccessTokenException ex) {
                                        ex.printStackTrace();
                                    }catch (Exception e1){
                                        e1.printStackTrace();
                                    }
                                    map.put(ConfirmAchievementFieldName.XSHT.name(),e.getFieldValue());
                                }
                                break;
                            case HKJE:
                                this.field_g1z4T__c = Double.valueOf(StringUtils.isEmpty(e.getFieldValue()) ? "0" : e.getFieldValue());
                                break;
                            case HKYJ:
                                this.field_s6y96__c = Double.valueOf(StringUtils.isEmpty(e.getFieldValue()) ? "0" : e.getFieldValue());
                                break;
                            case QYLX:
                                this.field_Xao0l__c = e.getFieldShowValue();
                                break;
                            case XSLX:
                                this.field_7t2uG__c = e.getFieldShowValue();
                                break;
                            case GZLZB:
                                this.field_41n2o__c = Double.valueOf(StringUtils.isEmpty(e.getFieldValue()) ? "0" : e.getFieldValue());
                                break;
                            case YWF:
                                this.field_1bP2M__c = Double.valueOf(StringUtils.isEmpty(e.getFieldValue()) ? "0" : e.getFieldValue());
                                break;
                            case ZDF:
                                this.field_M0lB1__c = Double.valueOf(StringUtils.isEmpty(e.getFieldValue()) ? "0" : e.getFieldValue());
                                break;
                            case JYJ:
                                this.field_eo9K1__c = Double.valueOf(StringUtils.isEmpty(e.getFieldValue()) ? "0" : e.getFieldValue());
                                break;
                            case GRZZYJ:
                                this.field_g0wfA__c = Double.valueOf(StringUtils.isEmpty(e.getFieldValue()) ? "0" : e.getFieldValue());
                                break;
                            case HKSJ:
                                if (!StringUtils.isBlank(e.getFieldValue())){
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                    try {
                                        this.field_dp1Ex__c = sdf.parse(e.getFieldValue()).getTime();
                                    } catch (ParseException ex) {
                                        ex.printStackTrace();
                                    }
                                }else {
                                    this.field_dp1Ex__c = new Date().getTime();
                                }
                                break;
                            case KHJL:
                                if (!StringUtils.isBlank(e.getFieldValue())){
                                    UserValueService userValueService = SpringBeanUtils.getBean(UserValueService.class);
                                    UserValue userValue = new UserValue();
                                    userValue.setUsernm(e.getFieldShowValue());
                                    List<UserValue> userValues = userValueService.findByCondition2(userValue);
                                    if (!CollectionUtils.isEmpty(userValues)){
                                        this.owner.add(userValues.get(0).getOpenuserid());
                                    }
                                    map.put(ConfirmAchievementFieldName.KHJL.name(),e.getFieldShowValue());
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
}
