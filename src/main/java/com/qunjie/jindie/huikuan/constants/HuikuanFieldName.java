package com.qunjie.jindie.huikuan.constants;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.jindie.huikuan.constants.HuikuanFieldName
 *
 * @author whs
 * Date:   2021/1/26  9:30
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
public enum  HuikuanFieldName {

    //main
    REQUESTNAME,                //标题
    REQUESTLEVEL,               //状态
    MESSAGETYPE,                //短信提醒
    KHMC,                       //直销客户名称
    XSHTBH,                     //销售合同编号
    QDDDBH,                     //渠道订单编号
    ZXQD,                       //直销/渠道？
    DQ,                         //大区
    XSRY,                       //销售人员
    DJR,                        //登记人员
    DJSJ,                       //登记时间
    KHMC1,                      //回款单位名称
    QDDLS,                      //渠道代理商
    XSHT,                       //销售合同
    KH,                         //客户
    BZ,                         //备注
    YF,                         //月份
    HZHB,                       //合作伙伴
    //detail
    HTBH,                   //合同编号
    HKDWMC,                 //客户名称
    HKJE,                   //回款金额
    BZ2,                    //备注
    HKRQ,                   //回款时间
    SZZZ,                   //所属组织
    KHJL,                   //客户经理
    LX,                     //销售类型
//    BZ,                     //备注
//    KHMC,                   //客户名称
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

    public static HuikuanFieldName valuesOf(String value) {
        for (HuikuanFieldName huikuanFieldName : HuikuanFieldName.values()) {
            if (huikuanFieldName.name().equalsIgnoreCase(value)){
                return huikuanFieldName;
            }
        }
        return null;
    }
}
