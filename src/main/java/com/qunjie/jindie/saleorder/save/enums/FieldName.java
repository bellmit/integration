package com.qunjie.jindie.saleorder.save.enums;/**
 * Created by whs on 2020/12/10.
 */

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunjie.jindie.saleorder.save.enums.FieldName
 *
 * @author whs
 *         Date:   2020/12/10  16:33
 *         Description: 泛微oa返回的xml标签WorkflowRequestTableField中的值含义标志
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
public enum FieldName {
    /**
     * 标准销售合同+非标销售合同
     */
    KH,     //客户           workflowMainTableInfo
    KEHUMC, //客户名称       workflowMainTableInfo
    /**
     * 渠道非标:        伙伴客户编码 -> 客户
     *                  伙伴客户名称 -> 客户名称
     */
    HZHBBH, //合作伙伴编号       workflowMainTableInfo
    HZHBMC, //合作伙伴名称       workflowMainTableInfo
    HBKHBM, //伙伴客户编码        workflowMainTableInfo
    HBKHMC, //伙伴客户名称        workflowMainTableInfo

    XSY,    //销售员编号     workflowMainTableInfo
    BILLNO, //合同编号      workflowMainTableInfo
    QYRQ,   //签约日期      workflowMainTableInfo
    HTZT,   //合同作废      workflowMainTableInfo
    HTJE,   //合同金额(元)   workflowMainTableInfo
    SFKJXY,                 //是否框架协议 0：是，1：否
    QYR,    //签约人       workflowMainTableInfo
    SFZHT,  //是否子合同  0:是/1:否
    ZHTBH,  //主合同编号
    JHRQ,   //交货日期  workflowDetailTableInfos
    CPBM,   //产品编码  workflowDetailTableInfos
    SL,     //数量    workflowDetailTableInfos
    JDSFZP, //是否赠品   workflowDetailTableInfos
    JDHSDJ, //含税单价(元)   WorkflowDetailTableInfo
    HTLX,   //签约类型      workflowMainTableInfo

    XSYBM,  //销售员部门
    XSYSZDQ;    //销售员所属大区


    public static FieldName valuesOf(String value) {
        for (FieldName fieldName : FieldName.values()) {
            if (fieldName.name().equalsIgnoreCase(value)){
                return fieldName;
            }
        }
        return null;
    }
}