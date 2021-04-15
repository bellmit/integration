package com.qunjie.jindie.saleorder.submit.model;/**
 * Created by whs on 2020/12/10.
 */

import com.qunjie.common.annotation.Describe;
import lombok.Data;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunjie.jindie.saleorder.submit.model.SubmitEntity
 *
 * @author whs
 *         Date:   2020/12/10  11:24
 *         Description:
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
@Data
public class SubmitEntity {

    @Describe(value = "创建者组织内码，字符串类型",describe = "非必录")
    private Long CreateOrgId;

    @Describe(value = "单据编码集合，数组类型",describe = "使用编码时必录")
    private String[] Numbers;

    @Describe(value = "单据内码集合，字符串类型",describe = "使用内码时必录")
    private String Ids;

    @Describe(value = "工作流发起员工岗位内码，整型",describe = "非必录 注（员工身兼多岗时不传参默认取第一个岗位）")
    private Long SelectedPostId;

    @Describe(value = "是否启用网控，布尔类型，默认false",describe = "非必录")
    private String NetworkCtrl = "false";

    public SubmitEntity() {
    }

    public SubmitEntity(Long createOrgId, String ids) {
        CreateOrgId = createOrgId;
        Ids = ids;
    }
}
