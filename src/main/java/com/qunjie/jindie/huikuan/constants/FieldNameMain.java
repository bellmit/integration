package com.qunjie.jindie.huikuan.constants;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.jindie.huikuan.constants.FieldNameMain
 *
 * @author whs
 * Date:   2021/1/25  17:11
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
public enum  FieldNameMain {

    requestname,                //标题
    requestlevel,               //状态
    messageType,                //短信提醒
    khmc,                       //直销客户名称
    xshtbh,                     //销售合同编号
    qdddbh,                     //渠道订单编号
    skje,                       //收款总额
    zxqd,                       //直销/渠道？
    dq,                         //大区
    xsry,                       //销售人员
    djr,                        //登记人员
    djsj,                       //登记时间
    khmc1,                      //回款单位名称
    qddls,                      //渠道代理商
    xsht,                       //销售合同
    kh,                         //客户
    bz,                         //备注
    yf;                         //月份


    public static FieldNameMain valuesOf(String value) {
        for (FieldNameMain fieldNameMain : FieldNameMain.values()) {
            if (fieldNameMain.name().equalsIgnoreCase(value)){
                return fieldNameMain;
            }
        }
        return null;
    }
}
