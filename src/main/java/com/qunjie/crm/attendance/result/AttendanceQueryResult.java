package com.qunjie.crm.attendance.result;

import com.alibaba.fastjson.JSONArray;
import com.qunjie.crm.beans.results.BaseResult;
import lombok.Data;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.attendance.result.AttendanceQueryResult
 *
 * @author whs
 * Date:   2021/2/20  11:16
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class AttendanceQueryResult extends BaseResult {
    private String errorDescription;

    private Integer totalCount;

    private JSONArray datas;
}
