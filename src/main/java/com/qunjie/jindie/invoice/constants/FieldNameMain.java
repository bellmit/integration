package com.qunjie.jindie.invoice.constants;/**
 * Created by whs on 2020/12/10.
 */

import com.qunjie.jindie.saleorder.save.enums.FieldName;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunjie.jindie.saleorder.save.enums.FieldName
 *
 * @author whs
 *         Date:   2020/12/10  16:33
 *         Description: 开票标题
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
public enum FieldNameMain {
    REQUESTNAME,            //标题
    MESSAGETYPE,            //短信提醒
    SQRBH,                  //申请人编号-
    SQRXM,                  //申请人姓名
    HTBH,                   //合同编号-
    QYRQ,                   //签约日期
    QDDDH,                  //订单单号
    DLS,                    //代理商
    KH,                     //客户
    XM,                     //项目名称
    FPLX,                   //发票类型-
    KPNR,                   //开票内容
    GSQC,                   //发票抬头
    ZCDZ,                   //客户公司注册地址
    SBH,                    //客户公司纳税人识别号
    KHDH,                   //客户公司电话
    KHH,                    //客户公司开户行
    ZH,                     //客户银行账号
    LQFS,                   //发票领取方式
    SJDZ,                   //收件地址
    SJR,                    //收件人
    SJRLXFS,                //收件人联系方式
    HTJE,                   //合同金额(元)-
    YKPJE,                  //已开票金额（元）
    BCKPJE,                 //本次开票金额（元）
    KPRQ,                   //开票日期-
    SQR,                    //申请人
    SQBM,                   //申请部门
    XSXMTSDDSQ,             //销售项目特殊订单申请
    GLXGHTDDLC,             //关联相关合同/订单流程
    KHBH,                   //客户编码
    KHMC,                   //客户名称
    FPH,                    //发票号码
    FPHM,                   //发票号码

    GLQDHZXYLC;             //关联渠道合作协议流程

    public static FieldNameMain valuesOf(String value) {
        for (FieldNameMain fieldNameMain : FieldNameMain.values()) {
            if (fieldNameMain.name().equalsIgnoreCase(value)){
                return fieldNameMain;
            }
        }
        return null;
    }
}