package com.qunjie.crm.query.args;

import com.qunjie.crm.beans.args.BaseArg;
import lombok.Data;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.oacrmbridge.crm.query.args.QueryArg
 *
 * @author whs
 * Date:   2021/1/14  9:10
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class QueryArg extends BaseArg {

    private static final long serialVersionUID = -8559999156557789779L;
    private String currentOpenUserId;

    private QueryData data;

}
