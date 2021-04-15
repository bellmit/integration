package com.qunjie.axis.model;/**
 * Created by whs on 2020/12/10.
 */

import lombok.Data;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunjie.axis.model.WorkflowRequestTableField
 *
 * @author whs
 *         Date:   2020/12/10  16:05
 *         Description:
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
@Data
public class WorkflowRequestTableField {

    private String fieldName;
    private Boolean edit;
    private String browserurl;
    private String fieldShowName;
    private Long fieldOrder;
    private String fieldShowValue;
    private String fieldValue;
    private Object selectnames;
    private Object selectvalues;
    private String fieldDBType;
    private String filedHtmlShow;
    private Boolean view;
    private Boolean mand;
    private Integer fieldHtmlType;
    private Integer fieldType;
    private String fieldFormName;
    private Long fieldId;


}
