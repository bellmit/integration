package com.qunjie.crm.query.results;

import com.alibaba.fastjson.JSONObject;
import com.qunjie.crm.beans.results.BaseResult;
import lombok.Data;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.oacrmbridge.crm.query.results.QueryResult
 *
 * @author whs
 * Date:   2021/1/14  9:20
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class QueryResult extends BaseResult {

    private static final long serialVersionUID = 8284002156588488662L;
    private String errorDescription;

    private JSONObject data;
}
