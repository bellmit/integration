package com.qunjie.crm.saleTarget.model;

import com.qunjie.common.annotation.Describe;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.saleTarget.model.SaleTargetModel
 *
 * @author whs
 * Date:   2021/3/18  17:05
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class SaleTargetModel {

    private boolean is_deleted;
    @Describe("年度")
    private String field_J186E__c;
    @Describe("月份")
    private String field_c2id0__c;
    @Describe("业绩指标")
    private Double field_Ta3Qg__c;
    @Describe("实际净业绩")
    private Double field_762N2__c;
    @Describe("新签客户数指标")
    private Double field_MCN6T__c;
    @Describe("实际新签客户数")
    private Double field_q4r9M__c;
    @Describe("实际回款业绩")
    private Double field_4yaGI__c;
    @Describe("负责人")
    private List<String> owner =  new ArrayList<>();
    @Describe("目标类型")
    private String field_mm2gw__c;
    @Describe("dataObjectApiName")
    private String dataObjectApiName;
    @Describe("_id")
    private String _id;
    @Describe("归属部门")
    private String data_own_department;

}
