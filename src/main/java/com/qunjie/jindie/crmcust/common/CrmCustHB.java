package com.qunjie.jindie.crmcust.common;

import com.qunjie.common.annotation.Describe;
import lombok.Data;

import java.util.Map;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.jindie.crmcust.common.CrmCustHB
 *
 * @author whs
 * Date:   2021/2/5  10:01
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class CrmCustHB {

    @Describe("合作伙伴名称")
    private String name;

    @Describe("伙伴类型")
    private String partner_category;

    @Describe("手机号")
    private String tel;

    @Describe("传真")
    private String fax;

    @Describe("email")
    private String email;

    @Describe("统一社会信用代码")
    private String uniform_social_credit_code;

    @Describe("伙伴等级")
    private String credit_rating;

    @Describe("地址")
    private String address;

    @Describe("备注")
    private String remark;

    @Describe("伙伴来源")
    private String field_u66jB__c;

    @Describe("行业分类")
    private String field_gD4kN__c;

    @Describe("细分行业")
    private String field_a19ok__c;

    @Describe("登陆用户明细")
    private Map<String,Object> userDetail;

    @Describe("客户经理明细")
    private Map<String,Object> managerDetail;

    @Describe("内码id")
    private String _id;

}
