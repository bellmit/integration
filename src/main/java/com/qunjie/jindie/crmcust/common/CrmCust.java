package com.qunjie.jindie.crmcust.common;/**
 * Created by whs on 2020/12/25.
 */

import com.qunjie.common.annotation.Describe;
import lombok.Data;

import java.util.Map;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunjie.jindie.crmcust.common.CrmCust
 *
 * @author whs
 *         Date:   2020/12/25  16:53
 *         Description:
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
@Data
public class CrmCust {

    @Describe("客户名称")
    private String name;

    @Describe("客户编号")
    private String account_no;

    @Describe("手机号")
    private String tel;

    @Describe("地址")
    private String address;

    @Describe("备注")
    private String remark;

    @Describe("来源")
    private String account_source;

    @Describe("市场活动名称")
    private String field_ip1yc__c;

    @Describe("登陆用户明细")
    private Map<String,Object> userDetail;

    @Describe("客户经理明细")
    private Map<String,Object> managerDetail;


}
