package com.qunjie.crm.beans.results;

import com.google.common.base.MoreObjects;
import lombok.Data;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunje.oacrmbridge.crm.beans.results.DeptDetailResult
 *
 * @author whs
 * Date:   2020/12/22  13:42
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class DeptDetailResult extends BaseResult{

    private static final long serialVersionUID = 6487870209689981002L;
    private DepartDetail department;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("errorCode", errorCode)
                .add("errorMessage", errorMessage)
                .add("department", department)
                .toString();
    }
}
