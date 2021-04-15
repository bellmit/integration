package com.qunjie.crm.huikuan.args;

import com.qunjie.axis.model.WorkflowRequestTableField;
import com.qunjie.common.annotation.Describe;
import com.qunjie.common.util.SpringBeanUtils;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.partnerObj.model.PartnerObjModel;
import com.qunjie.crm.partnerObj.service.PartnerObjService;
import com.qunjie.crm.utils.DefaultValues;
import com.qunjie.jindie.huikuan.constants.HuikuanFieldName;
import com.qunjie.mysql.mapper.UserValueMapper;
import com.qunjie.mysql.model.UserValue;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.huikuan.args.HuikuanObjectData
 *
 * @author whs
 * Date:   2021/1/27  10:05
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class HuikuanObjectData {

    private String dataObjectApiName = DefaultValues.PAYMENTOBJ;

    @Describe("回款编号")
    private String name;
    @Describe("财务确认人")
    private String finance_employee_id;
    @Describe("合作伙伴")
    private String partner_id;
    @Describe("附件")
    private String attachment;
    @Describe("财务确认时间")
    private Long finance_confirm_time;
    @Describe("回款日期")
    private Long payment_time;
    @Describe("备注")
    private String remark;
    @Describe("回款方式")
    private String payment_term;
    @Describe("本次回款总金额（元）")
    private Double payment_amount;
    @Describe("提醒日期")
    private Long notification_time;
    @Describe("客户名称")
    private String account_id;
    @Describe("订单编号")
    private String order_id;
    @Describe("款项类别")
    private String field_oeGdb__c;
    @Describe("客户业务类型")
    private String field_aIzb3__c;
    @Describe("锁定状态")
    private String lock_status;
    @Describe("负责人")
    private List<String> owner = new ArrayList<>();
    @Describe("负责人所在部门")
    private String owner_department;
    @Describe("状态")
    private String life_status;
    @Describe("回款提交时间")
    private Long submit_time;
    @Describe("业务类型")
    private String record_type;
    @Describe("外部负责人")
    private String out_owner;
    @Describe("最后修改时间")
    private Long last_modified_time;
    @Describe("创建时间")
    private Long create_time;
    @Describe("最后修改人")
    private String last_modified_by;
    @Describe("创建人")
    private String created_by;
    @Describe("归属部门")
    private String data_own_department;
    @Describe("外部来源")
    private String out_resources;


    public Map<String,String> valuesOf(List<WorkflowRequestTableField> details) {
        Map<String,String> map = new HashMap<>();
        if (!CollectionUtils.isEmpty(details)) {
            details.forEach(e -> {
                if (!StringUtils.isBlank(e.getFieldName()) && null != HuikuanFieldName.valuesOf(e.getFieldName())) {
                    switch (HuikuanFieldName.valuesOf(e.getFieldName())) {
                        case KHBM:
                           break;
                        case HKDWMC:
                            /**
                             * 因回款中的客户采用的时crm中的销售订单的客户，故原本从oa回款流程中获取回款客户的赋值注释掉；
                             * 在获取销售订单后，会对此属性account_id赋值
                             */
//                            try {
//                                AccountService accountService = SpringBeanUtils.getBean(AccountService.class);
//                                List<AccountModel> accountModels = accountService.queryAccountByName(e.getFieldValue());
//                                if (!CollectionUtils.isEmpty(accountModels)){
//                                    this.account_id = accountModels.get(0).get_id();
//                                }else {
//                                    map.put(HuikuanFieldName.HKDWMC.name(),e.getFieldValue());
//                                }
//                            } catch (AccessTokenException ex) {
//                                ex.printStackTrace();
//                            }
                            break;
                        case HKRQ:
                            if (!StringUtils.isBlank(e.getFieldValue())){
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                try {
                                    this.payment_time = sdf.parse(e.getFieldValue()).getTime();
                                } catch (ParseException ex) {
                                    ex.printStackTrace();
                                }
                            }else {
                                this.payment_time = new Date().getTime();
                            }
                            break;
                        case KHJL1:
                            if (!StringUtils.isBlank(e.getFieldValue())) {
                                UserValueMapper userValueMapper = SpringBeanUtils.getBean(UserValueMapper.class);
                                UserValue userValue = new UserValue();
                                userValue.setUsernm(e.getFieldValue());
                                List<UserValue> userValues = userValueMapper.findByCondition2(userValue);
                                if (!CollectionUtils.isEmpty(userValues)){
                                    this.owner.add(userValues.get(0).getOpenuserid());
                                }
                            }
                            break;
                        case HZHB:
                            if (!StringUtils.isBlank(e.getFieldValue())) {
                                try {
                                    PartnerObjService partnerObjService = SpringBeanUtils.getBean(PartnerObjService.class);
                                    List<PartnerObjModel> partnerObjModels = partnerObjService.queryPartnerObjByName(e.getFieldValue());
                                    if (!CollectionUtils.isEmpty(partnerObjModels)) {
                                        this.partner_id = partnerObjModels.get(0).get_id();
                                    }else {
                                        map.put(HuikuanFieldName.HZHB.name(),e.getFieldValue());
                                    }
                                } catch (AccessTokenException ex) {
                                    ex.printStackTrace();
                                }
                            }else {
                                map.put(HuikuanFieldName.HZHB.name(),HuikuanFieldName.HZHB.name());
                            }
                            break;
                        case LXWB:
                            if (!StringUtils.isBlank(e.getFieldValue())){
                                HashMap<String, String> hashMap = new HashMap<String, String>() {
                                    {
                                        put("直销", "option1");
                                        put("渠道", "ORcWgu26e");
                                        put("联营", "4n27weR02");
                                        put("厂商", "ol51USrbR");
                                    }
                                };
                                this.field_oeGdb__c = hashMap.get(e.getFieldValue());
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
