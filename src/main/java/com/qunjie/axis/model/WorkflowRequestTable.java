package com.qunjie.axis.model;/**
 * Created by whs on 2020/12/14.
 */

import lombok.Data;

import java.util.List;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunjie.axis.model.WorkflowRequestTable
 *
 * @author whs
 *         Date:   2020/12/14  11:10
 *         Description:
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
@Data
public class WorkflowRequestTable {

    List<List<WorkflowRequestTableField>> details;

    List<WorkflowRequestTableField> mains;

    public WorkflowRequestTable(List<List<WorkflowRequestTableField>> details, List<WorkflowRequestTableField> mains) {
        this.details = details;
        this.mains = mains;
    }


}
