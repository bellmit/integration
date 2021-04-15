package com.qunjie.crm.account.model;

import lombok.Data;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.oacrmbridge.crm.account.model.AccountModel
 *
 * @author whs
 * Date:   2021/1/14  10:54
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class AccountModel {

    private String _id;

    private String name;

    private Integer lock_status;
}
