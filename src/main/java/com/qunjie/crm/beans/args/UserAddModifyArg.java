package com.qunjie.crm.beans.args;

import com.google.common.base.MoreObjects;
import com.qunjie.crm.beans.results.User;
import lombok.Data;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunje.oacrmbridge.crm.beans.args.UserAddModifyArg
 *
 * @author whs
 * Date:   2020/12/22  10:43
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class UserAddModifyArg extends BaseArg {

    private static final long serialVersionUID = -4304912261838013222L;
    private User user;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("corpAccessToken", corpAccessToken)
                .add("corpId", corpId)
                .add("user", user)
                .toString();
    }
}
