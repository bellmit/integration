package com.qunjie.mysql.model;

import lombok.Data;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunje.oacrmbridge.mysql.model.UserValue
 *
 * @author whs
 * Date:   2020/12/21  16:30
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class UserValue {

    private Integer indocno;

    private Integer userid;

    private String openuserid;

    private String usernm;

    private String mobile;
}
