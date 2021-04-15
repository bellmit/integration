package com.qunjie.jindie.saleorder.save.enums;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.jindie.saleorder.save.enums.QDXSDDFieldName
 *
 * @author whs
 * Date:   2021/1/29  16:25
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
public enum QDXSDDFieldName {
    /**
     * mains
     */
    KHMC,           //客户名称
    KHBM,           //客户编码
    DLSMC,          //合作伙伴名称
    XS,             //销售
    SQRQ,           //申请日期
    DDJE,           //订单金额
    ZE,             //总额
    QYLX,           //签约类型
    DH,             //单号
    XSYBM,          //销售员编码
    XSYXM,          //销售员姓名
    /**
     *  details
     */
    SL,             //数量
    DJ,             //含税单价
    FHRQ,           //发货日期
    SFZP,           //是否赠品
    QDJLBM, //销售员部门
    QDJLSZDQ,   //销售员所属大区
    CPBM;           //产品编码



    public static QDXSDDFieldName valuesOf(String value) {
        for (QDXSDDFieldName fieldName : QDXSDDFieldName.values()) {
            if (fieldName.name().equalsIgnoreCase(value)){
                return fieldName;
            }
        }
        return null;
    }
}
