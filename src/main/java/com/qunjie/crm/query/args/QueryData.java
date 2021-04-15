package com.qunjie.crm.query.args;

import lombok.Data;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.oacrmbridge.crm.query.args.QueryData
 *
 * @author whs
 * Date:   2021/1/14  9:11
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class QueryData {

    private String dataObjectApiName;

    private SearchQueryInfo search_query_info;
}
