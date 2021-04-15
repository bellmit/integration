package com.qunjie.crm.beans.args;

import com.google.common.base.MoreObjects;
import lombok.Data;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunje.oacrmbridge.crm.beans.args.DeptArg
 *
 * @author whs
 * Date:   2020/12/22  13:47
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class DeptArg extends BaseArg{

    private static final long serialVersionUID = 8639294368932292369L;
    private Integer departmentId;

    private String currentOpenUserId;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("corpAccessToken", corpAccessToken)
                .add("corpId", corpId)
                .add("departmentId", departmentId)
                .add("currentOpenUserId",currentOpenUserId)
                .toString();
    }
}
