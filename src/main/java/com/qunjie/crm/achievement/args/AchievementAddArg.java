package com.qunjie.crm.achievement.args;

import com.qunjie.axis.model.WorkflowRequestTableField;
import com.qunjie.common.annotation.Describe;
import com.qunjie.common.util.SpringBeanUtils;
import com.qunjie.crm.achievement.constants.AchievementFieldName;
import com.qunjie.crm.beans.args.BaseArg;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.partnerObj.model.PartnerObjModel;
import com.qunjie.crm.partnerObj.service.PartnerObjService;
import com.qunjie.crm.query.results.QueryResult;
import com.qunjie.crm.saleorder.service.SaleOrderService;
import com.qunjie.crm.utils.DefaultValues;
import com.qunjie.jindie.huikuan.constants.HuikuanFieldName;
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
public class AchievementAddArg extends BaseArg {
    private String currentOpenUserId = DefaultValues.CURRENTOPENUSERID;

    private boolean triggerWorkFlow = false;

    private AchievementModel data;

    public AchievementAddArg(AchievementModel data) {
        this.data = data;
    }

    public AchievementAddArg() {
    }

    @Data
    public static class AchievementModel {
        private AchievementObjectData object_data;

        public AchievementModel(AchievementObjectData object_data) {
            this.object_data = object_data;
        }

        public AchievementModel() {
        }
    }

    @Data
    public static class AchievementObjectData{

        private String dataObjectApiName = "object_n5KJ2__c";

        @Describe("OA订单编号")
        private String field_147k2__c;
        @Describe("客户")
        private String field_j1qWy__c;
        @Describe("销售订单")
        private String field_xH1XV__c;
        @Describe("合作伙伴")
        private String field_I24W5__c;
        @Describe("回款业绩")
        private Double field_8GE9P__c;
        @Describe("回款金额")
        private Double field_125Ca__c;
        @Describe("销售类型")
        private String field_Sk215__c;
        @Describe("回款日期")
        private Long field_2a6kX__c;
        @Describe("负责人")
        private List<String> owner = new ArrayList<>();

        public Map<String,String> valueOf(List<WorkflowRequestTableField> detail){
            Map<String,String> map = new HashMap<>();
            if (!CollectionUtils.isEmpty(detail)) {
                detail.forEach(e -> {
                    if (!StringUtils.isBlank(e.getFieldName()) && null != AchievementFieldName.valuesOf(e.getFieldName())) {
                        switch (AchievementFieldName.valuesOf(e.getFieldName())) {
                            case HTBHX:
                                if (!StringUtils.isBlank(e.getFieldValue())){
                                    SaleOrderService saleOrderService = SpringBeanUtils.getBean(SaleOrderService.class);
                                    try {
                                        QueryResult queryResult = saleOrderService.saleOrderQueryBy(e.getFieldValue());
                                        if (queryResult != null && queryResult.getErrorCode() == 0) {
                                            String id = queryResult.getData().getJSONArray("dataList").getJSONObject(0).getString("_id");
                                            String account_id = queryResult.getData().getJSONArray("dataList").getJSONObject(0).getString("account_id");
                                            String OaHTBH = queryResult.getData().getJSONArray("dataList").getJSONObject(0).getString("field_Clved__c");
                                            this.field_j1qWy__c = account_id;
                                            this.field_xH1XV__c = id;
                                            this.field_147k2__c = OaHTBH;
                                        }
                                    } catch (AccessTokenException ex) {
                                        ex.printStackTrace();
                                    }catch (Exception e1){
                                        e1.printStackTrace();
                                    }
                                    map.put(HuikuanFieldName.HTBHX.name(),e.getFieldValue());
                                }
                                break;
                            case HZHB:
                                if (!StringUtils.isBlank(e.getFieldValue())) {
                                    try {
                                        PartnerObjService partnerObjService = SpringBeanUtils.getBean(PartnerObjService.class);
                                        List<PartnerObjModel> partnerObjModels = partnerObjService.queryPartnerObjByName(e.getFieldValue());
                                        if (!CollectionUtils.isEmpty(partnerObjModels)) {
                                            this.field_I24W5__c = partnerObjModels.get(0).get_id();
                                        }
                                    } catch (AccessTokenException ex) {
                                        ex.printStackTrace();
                                    }
                                    map.put(AchievementFieldName.HZHB.name(),e.getFieldValue());
                                }
                                break;
                            case HKJE:
                                this.field_125Ca__c = Double.valueOf(StringUtils.isEmpty(e.getFieldValue()) ? "0" : e.getFieldValue());
                                break;
                            case YJY:
                                this.field_8GE9P__c = Double.valueOf(StringUtils.isEmpty(e.getFieldValue()) ? "0" : e.getFieldValue());
                                break;
                            case LXWB:
                                this.field_Sk215__c = e.getFieldValue();
                                break;
//                            case HKRQ:
//                                if (!StringUtils.isBlank(e.getFieldValue())){
//                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                                    try {
//                                        this.field_2a6kX__c = sdf.parse(e.getFieldValue()).getTime();
//                                    } catch (ParseException ex) {
//                                        ex.printStackTrace();
//                                    }
//                                }else {
//                                    this.field_2a6kX__c = new Date().getTime();
//                                }
//                                break;
                            case KHJL1:
                                if (!StringUtils.isBlank(e.getFieldValue())){
                                    UserValueService userValueService = SpringBeanUtils.getBean(UserValueService.class);
                                    UserValue userValue = new UserValue();
                                    userValue.setUsernm(e.getFieldValue());
                                    List<UserValue> userValues = userValueService.findByCondition2(userValue);
                                    if (!CollectionUtils.isEmpty(userValues)){
                                        this.owner.add(userValues.get(0).getOpenuserid());
                                    }
                                    map.put(AchievementFieldName.KHJL1.name(),e.getFieldShowValue());
                                }
                                break;
                            case SZTD:
                                if (!StringUtils.isBlank(e.getFieldValue())){
                                    map.put(AchievementFieldName.SZTD.name(),e.getFieldValue());
                                }
                                break;
                            case SZDQ:
                                if (!StringUtils.isBlank(e.getFieldValue())){
                                    map.put(AchievementFieldName.SZDQ.name(),e.getFieldValue());
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

        public void valueOfMain(List<WorkflowRequestTableField> main){
            if (!CollectionUtils.isEmpty(main)) {
                main.forEach(e->{
                    if (!StringUtils.isBlank(e.getFieldName()) && null != AchievementFieldName.valuesOf(e.getFieldName())) {
                        switch (AchievementFieldName.valuesOf(e.getFieldName())) {
                            case HKRQ:
                                if (!StringUtils.isBlank(e.getFieldValue())){
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                    try {
                                        this.field_2a6kX__c = sdf.parse(e.getFieldValue()).getTime();
                                    } catch (ParseException ex) {
                                        ex.printStackTrace();
                                    }
                                }else {
                                    this.field_2a6kX__c = new Date().getTime();
                                }
                                break;
                        }
                    }
                });
            }
        }

    }
}
