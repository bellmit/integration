package com.qunjie.crm.beans.results;

import lombok.Data;

import java.io.Serializable;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunje.oacrmbridge.crm.beans.results.DepartDetail
 *
 * @author whs
 * Date:   2020/12/22  14:14
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class DepartDetail implements Serializable {
    private static final long serialVersionUID = -7630335718114867302L;

    private Integer departmentId;

    private Integer parentDepartmentId;

    private String name;

    private Boolean isStop;

}
