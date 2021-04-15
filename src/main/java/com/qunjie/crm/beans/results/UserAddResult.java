package com.qunjie.crm.beans.results;

import com.google.common.base.MoreObjects;
import lombok.Data;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunje.oacrmbridge.crm.beans.results.UserAddResult
 *
 * @author whs
 * Date:   2020/12/21  11:08
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class UserAddResult extends BaseResult {
    private static final long serialVersionUID = 5774539099630605690L;

    private String openUserId;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("errorCode", errorCode)
                .add("errorMessage", errorMessage)
                .add("openUserId", openUserId)
                .toString();
    }
}
