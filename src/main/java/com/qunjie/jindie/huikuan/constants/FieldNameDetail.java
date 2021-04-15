package com.qunjie.jindie.huikuan.constants;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.jindie.huikuan.constants.FieldNameDetail
 *
 * @author whs
 * Date:   2021/1/25  14:19
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
public enum FieldNameDetail {

    HTBH,                   //合同编号
    HKDWMC,                 //客户名称
    HKJE,                   //回款金额
    BZ2,                    //备注
    HKRQ,                   //回款时间
    SZZZ,                   //所属组织
    KHJL,                   //客户经理
    LX,                     //销售类型
    BZ,                     //备注
    KHMC,                   //客户名称
    SZTD,                   //所属团队
    BM,                     //部门
    KHJL1,                  //客户经理1
    BM1,                    //部门1
    HTBH1,                  //合同编号1
    HTBHX,                  //合同编号新
    SZTDWB,                 //所属团队1
    LXWB,                   //销售类型1
    KHBM,                   //客户编码
    XSYBM;                  //销售员编码

    public static FieldNameDetail valuesOf(String value) {
        for (FieldNameDetail fieldNameDetail : FieldNameDetail.values()) {
            if (fieldNameDetail.name().equalsIgnoreCase(value)){
                return fieldNameDetail;
            }
        }
        return null;
    }
}
